package com.example.tetris.activity;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tetris.env.GDisplay;
import com.example.tetris.env.GTable;
import com.example.tetris.view.GameView;

public class SecondActivity extends Activity {

	GameView mv;
	GTable gt;
	GDisplay gd;
	LinearLayout linear;
	TextView textView;
	ProgressBar pro;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 9) {
				textView.setText("로딩 완료!");
			} else if (msg.what == 10) {
				linear.setVisibility(View.INVISIBLE);
				mv.setVisibility(View.VISIBLE);
			} else {
				textView.setText("로딩중..." + msg.arg1);
				pro.incrementProgressBy(1);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		 mv = new GameView(this);
		setContentView(mv);

//		if (savedInstanceState != null) {
//			gt = (GTable) savedInstanceState.getParcelable("gt");
//			gd = (GDisplay) savedInstanceState.getSerializable("gd");
//			mv.setgMap(gt);
//			gt.setHan(mv.getHandler());
//			mv.setGDisplay(gd);
//		}

		// Log.i("생명", "Second_onCreate()실행");

	}
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mv.stopSurfaceThread();
		mv.stopHandler();
		super.onPause();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		Log.i("생명", "Second_onSaveInstanceState()실행");
//		gd = GManager.getInstance().getGd();
//		gt = GManager.getInstance().getGmap();
//		outState.putParcelable("gt", gt);
//		outState.putSerializable("gd", gd);
		super.onSaveInstanceState(outState);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
}
