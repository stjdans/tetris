package com.example.tetris.env;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class TouchPad {
	public static final int NOTTHING = -1;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int BOTTOM = 2;
	public static final int ROTATION = 3;

	private static final int TOUCHPAD_LENGTH = 4;

	Rect[] touchPad;
	Paint p[];

	public TouchPad(int width, int height) {
		// TODO Auto-generated constructor stub
		p = new Paint[TOUCHPAD_LENGTH];
		for (int i = 0; i < TOUCHPAD_LENGTH; i++) {
			p[i] = new Paint();
		}
		p[0].setColor(Color.RED);
		p[0].setAlpha(30);
		p[1].setColor(Color.MAGENTA);
		p[1].setAlpha(30);
		p[2].setColor(Color.BLUE);
		p[2].setAlpha(30);
		p[3].setColor(Color.GREEN);
		p[3].setAlpha(30);

		int c_x = width / 4;
		int c_y = height / 5 * 4;
		int size_w = width / 5;
		int size_h = height / 6;

		touchPad = new Rect[TOUCHPAD_LENGTH];
		touchPad[0] = new Rect(c_x - size_w - 15, c_y - size_h - 15, c_x - 15,
				c_y - 15);
		touchPad[1] = new Rect(c_x + 15, c_y - size_h - 15, c_x + size_w + 15,
				c_y - 15);
		touchPad[2] = new Rect(c_x - size_w / 2, c_y, c_x + size_w / 2, c_y
				+ size_h);
		int r_x = width / 4 * 3;
		touchPad[3] = new Rect(r_x - size_w, c_y - size_h, r_x + size_w, c_y
				+ size_h);
	}

	public void draw(Canvas can) {
		for (int i = 0; i < TOUCHPAD_LENGTH; i++) {
			can.drawRect(touchPad[i], p[i]);
		}
		// can.drawCircle(c_x, c_y, 5, p[0]);
		// can.drawCircle(r_x, c_y, 5, p[0]);
	}

	public void changeColor(int where) {
		if (where == NOTTHING)
			return;
		p[where].setAlpha(100);
	}

	public void clearRect() {
		for (int i = 0; i < TOUCHPAD_LENGTH; i++) {
			p[i].setAlpha(30);
		}
	}

	public int isClickPad(int x, int y) {
		for (int where = 0; where < TOUCHPAD_LENGTH; where++) {
			if (touchPad[where].contains(x, y))
				return where;
		}
		return NOTTHING;
	}
}
