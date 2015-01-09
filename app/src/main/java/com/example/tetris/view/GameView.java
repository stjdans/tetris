package com.example.tetris.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.tetris.R;
import com.example.tetris.activity.ResultActivity;
import com.example.tetris.config.GManager;
import com.example.tetris.config.GameConfig;
import com.example.tetris.env.Block;
import com.example.tetris.env.CollisionManager;
import com.example.tetris.env.GDisplay;
import com.example.tetris.env.GTable;
import com.example.tetris.env.NextShapeDisplay;
import com.example.tetris.env.SoundManager;
import com.example.tetris.env.TouchPad;
import com.example.tetris.shape.Shape;
import com.example.tetris.shape.ShapeFactory;

import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int GAME_READY = 0;
    public static final int GAME_START = 1;
    public static final int GAME_END = 2;

    SurfaceViewThread thread;
    ShapeFactory factory;
    GTable gMap;
    GDisplay display;
    NextShapeDisplay nsdisplay;
    CollisionManager colmanager;
    Shape sh;
    TouchPad pad;
    SoundManager sm;

    Paint[] p;
    List<Block> bList;
    Context context;
    ProgressDialog dialog;
    Bitmap block;

    public GameView(Context context) {
        // TODO Auto-generated constructor stub
        super(context);
        setBackgroundColor(Color.WHITE);
        getHolder().addCallback(this);

        this.context = context;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int dis_width = GameConfig.dis_width = dm.widthPixels;
        int dis_height = GameConfig.dis_height = dm.heightPixels;
        int initX = GameConfig.getInitMapX();
        int initY = GameConfig.getInitMapY();
        int size = GameConfig.size;

        setBackgroundColor(Color.LTGRAY);
        p = new Paint[2];
        p[0] = new Paint();
        p[0].setColor(Color.BLACK);
        p[0].setTextSize(25);
        p[1] = new Paint();
        p[1].setColor(Color.WHITE);
        block = BitmapFactory.decodeResource(getResources(), R.drawable.block);

        dialog = new ProgressDialog(context);
        dialog.setTitle("게임 로딩");
        dialog.setMessage("데이터 로딩중 ...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
//        dialog.dismiss();

        this.thread = new SurfaceViewThread(getHolder(), this);

        factory = ShapeFactory.getInstance(initX, initY, size);
        gMap = new GTable(hand);
        display = new GDisplay(initX + GameConfig.getMapWidth(), initY
                + GameConfig.getMapHeight() / 3);
        nsdisplay = NextShapeDisplay.getInstance(initX, initY - size * 2, size,
                factory, context);
        colmanager = CollisionManager.getInstance();
        pad = new TouchPad(dis_width, dis_height);
        sm = SoundManager.getInstance(context);
        GManager gm = GManager.getInstance();
        gm.setGd(display).setGmap(gMap).setGv(this).setNsd(nsdisplay)
                .setSf(factory).setTh(thread).setCm(colmanager).setTp(pad)
                .setSm(sm);
        sh = nsdisplay.getNextShape(); // 도형 생성
    }

    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            sh.moveDown();
            if (!update())
                return;
            invalidate();
            hand.postDelayed(runnable, display.getSpeed());
        }
    };

    Handler hand = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 1:
                    sm.playSound(com.example.tetris.R.raw.del);
                    int del = msg.arg1;
                    display.pointUp(del);
                    display.delLine(del);
                    if (display.clearStage())
                        sendEmptyMessage(3);
                    break;
                case 2:
                    sm.playSound(com.example.tetris.R.raw.block2, 1.5f);
                    display.pointUp();
                    break;
                case 3:
                    display.levelUp();
                    gMap = new GTable(hand);
                    invalidate();
                    break;
            }
            invalidate();
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        if (dialog != null)
            dialog.show();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub
        hand.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                startThread();
                dialog.dismiss();
            }
        }, 1500);
        hand.postDelayed(runnable, 1500);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        stopSurfaceThread();
        stopHandler();
    }

    protected void onDraw(Canvas canvas) {

    }

    ;

    /* 스레드에서만 호출 ~ */
    public void draw(Canvas canvas) {
        display.drawBackground(canvas, p[0]);
        display.drawDisplay(canvas, p[0]); // 전광판 그리기
        gMap.drawMapData(canvas, p[0],block); // 맵데이터 그리기
        nsdisplay.drawNextShapList(canvas, p[0]);
        pad.draw(canvas);
        sh.drawShape(canvas, p[0]);
    }

    public void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("패배").setMessage("게임을 종료합니다!").setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Intent in = new Intent(getContext(),
                                ResultActivity.class);
                        in.putExtra("stage", display.getStage());
                        in.putExtra("point", display.getPoint());
                        in.putExtra("where", "game");
                        in.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        getContext().startActivity(in);
                        Activity second = (Activity) getContext();
                        second.finish();
                    }
                });
        builder.show();
    }

    public boolean update() {
        bList = sh.getTableList();
        if (colmanager.isCollisionShape(bList, gMap.getBlocks())
                || colmanager.isCollisionPosition(bList,
                CollisionManager.BOTTOM)) {
            hand.sendEmptyMessage(2);
            Shape s = sh;
            s.moveUp(); // 복귀
            gMap.inputDataFromList(s.getTableList());
            if (gMap.isEnd()) {
                stopSurfaceThread();
                stopHandler();
                createDialog();
                return false;
            }
            gMap.DelLine();
            sh = nsdisplay.getNextShape();
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        int x, y;
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                pad.clearRect();
                invalidate();
                return true;

            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                int where = pad.isClickPad(x, y);
                pad.changeColor(where);

                switch (where) {
                    case TouchPad.BOTTOM:
                        sh.moveDown();
                        update();
                        break;
                    case TouchPad.LEFT:
                        sh.moveLeft();
                        bList = sh.getTableList();
                        if (colmanager
                                .isCollisionPosition(bList, CollisionManager.LEFT)
                                || colmanager.isCollisionShape(bList, gMap.getBlocks())) {
                            sh.moveRight();
                            return true;
                        }
                        break;
                    case TouchPad.RIGHT:
                        sh.moveRight();
                        bList = sh.getTableList();
                        if (colmanager.isCollisionPosition(bList,
                                CollisionManager.RIGHT)
                                || colmanager.isCollisionShape(bList, gMap.getBlocks())) {
                            sh.moveLeft();
                            return true;
                        }
                        break;
                    case TouchPad.ROTATION:
                        sh.rotateL();
                        bList = sh.getTableList();
                        if (colmanager
                                .isCollisionPosition(bList, CollisionManager.LEFT)
                                || colmanager.isCollisionPosition(bList,
                                CollisionManager.RIGHT)
                                || colmanager.isCollisionShape(bList, gMap.getBlocks())
                                || colmanager.isCollisionPosition(bList,
                                CollisionManager.BOTTOM)) {
                            sh.rotateR();
                            return true;
                        }
                        break;
                }// switch
                invalidate();
                return true;
        }
        return false;
    }

    public void startThread() {
        thread.setRunning(true);
        try {
            thread.start();
        } catch (Exception e) {
            Log.i("error", "startThread() : 쓰레드 다시 생성중..");
            thread = new SurfaceViewThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();
        }

    }

    public void stopSurfaceThread() {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.interrupt();
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                Log.i("error", "stopThread() : " + e.getMessage());
            }
        }
    }

    public void stopHandler() {
        hand.removeCallbacks(runnable);
    }
}
