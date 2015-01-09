package com.example.tetris.env;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.tetris.config.GameConfig;

public class GDisplay {
    private int line;
    private int point;
    private int stage;
    private int speed;

    private int c_x, c_y;
    private Bitmap background;

    public GDisplay(int c_x, int c_y) {
        // TODO Auto-generated constructor stub
        this.c_x = c_x;
        this.c_y = c_y;
        this.line = 3;
        this.point = 0;
        this.stage = 1;
        this.speed = GameConfig.speed;

        createBackgroundMap(GameConfig.dis_width, GameConfig.dis_height,
                GameConfig.getInitMapX(), GameConfig.getInitMapY());
    }

    public void createBackgroundMap(int width, int height, int initX, int initY) {
        background = Bitmap.createBitmap(width, height, Config.RGB_565);
        Canvas can = new Canvas(background);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        can.drawRect(0, 0, width, height, paint);

        int size = GameConfig.size;
        int col = GameConfig.col;
        int row = GameConfig.row;
        paint.reset();

        can.drawLine(initX, initY + row * size, GameConfig.getMapWidth() + initX, initY + row * size, paint);// 가로
        for (int j = initX; j <= col * size + initX; j += col * size) { // 세로
            can.drawLine(j, initY, j, GameConfig.getMapHeight() + initY, paint);
        }
//		for (int i = initY; i <= row * size + initY; i += size) { // 가로
//            can.drawLine(initX, i, GameConfig.getMapWidth() + initX, i, paint);
//        }
//		for (int j = initX; j <= col * size + initX; j += size) { // 세로
//			can.drawLine(j, initY, j, GameConfig.getMapHeight() + initY, paint);
//		}
    }

    public boolean clearStage() {
        if (this.line <= 0)
            return true;
        return false;
    }

    public void levelUp() {
        nextStage();
        speedUp();
        bonus();
        initLine();
    }

    public void pointUp() {
        this.point += (10 + stage - 1);
    }

    public void pointUp(int del) {
        this.point += 100 * del;
    }

    public void delLine(int del) {
        this.line -= del;
    }

    private void initLine() {
        this.line = 3 + stage - 1;
    }

    private void speedUp() {
        this.speed -= 200;
    }

    private void bonus() {
        this.point += 100 * stage;
    }

    private void nextStage() {
        this.stage++;
    }

    public void drawDisplay(Canvas can, Paint p) {
        String[] str = getDisPlayItem();
        for (int i = 0; i < str.length; i++) {
            can.drawText(str[i], c_x + GameConfig.size, c_y + GameConfig.size
                    * i * 2, p);
        }
    }

    private String[] getDisPlayItem() {
        return new String[]{"스테이지  :  " + stage, "점수  :  " + point,
                "남은 라인수  :  " + line, "게임 속도 : " + speed};
    }

    public void drawBackground(Canvas can, Paint p) {
        can.drawBitmap(background, 0, 0, p);
    }

    public int getPoint() {
        return point;
    }

    public int getStage() {
        return stage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // public int[] getDisplayXY() {
    // return new int[] { c_x, c_y };
    // }
}
