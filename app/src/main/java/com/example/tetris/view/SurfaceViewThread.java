package com.example.tetris.view;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceViewThread extends Thread {
	private SurfaceHolder surHolder;
	private GameView gameView;

	private boolean isRunnig = false;

	public SurfaceViewThread(SurfaceHolder holder, GameView view) {
		// TODO Auto-generated constructor stub
		this.surHolder = holder;
		this.gameView = view;

	}

	public void setRunning(boolean bool) {
		isRunnig = bool;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Canvas canvas = null;

		while (isRunnig) {
			canvas = surHolder.lockCanvas();
			try {
				synchronized (gameView) {
					gameView.draw(canvas);

				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.i("error", "ViewThread : " + e.getMessage());
			} finally {
				if (canvas != null)
					surHolder.unlockCanvasAndPost(canvas);

			}

		}

	}
}
