package com.example.tetris.config;

import android.util.DisplayMetrics;

public class GameConfig {
    public static final int SIZE_DEFAULT = 15;
	static public int col = 7; 			// 맵 테이블 로우
	static public int row = 13; 		// 맵 테이블 컬럼
	static public int size = 15; 		// 블록 사이즈   밀도 1.5   240dpi 기준
	public static int speed = 3000;
	
	static public int dis_width = 0;	// 뷰 가로			<--주입
	static public int dis_height = 0;	// 뷰 세로			<--주입
	
	static public final int TYPE = 7;	//도형 종류
	
	static public int getMapWidth() {
		return col * size;
	}

	static public int getMapHeight() {
		return row * size;
	}
	static public int getInitMapX() {
		int c_x = dis_width / 2;
		c_x = c_x - getMapWidth() / 2;
		return c_x;
	}
	static public int getInitMapY() {
		int c_y = dis_height / 2;
		c_y = c_y - getMapHeight() / 2;
		return c_y;
	}

    public static int optimizeBlockSize(int density) throws UnsupportedOperationException {
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                return size * (DisplayMetrics.DENSITY_LOW / DisplayMetrics.DENSITY_DEFAULT);
            case DisplayMetrics.DENSITY_MEDIUM:
                return size;
            case DisplayMetrics.DENSITY_HIGH:
                return size * (DisplayMetrics.DENSITY_HIGH / DisplayMetrics.DENSITY_DEFAULT);
            case DisplayMetrics.DENSITY_XHIGH:
                return size * (DisplayMetrics.DENSITY_XHIGH / DisplayMetrics.DENSITY_DEFAULT);
            case DisplayMetrics.DENSITY_400:
                return size * (DisplayMetrics.DENSITY_400 / DisplayMetrics.DENSITY_DEFAULT);
            case DisplayMetrics.DENSITY_XXHIGH:
                return size * (DisplayMetrics.DENSITY_XXHIGH / DisplayMetrics.DENSITY_DEFAULT);
            case DisplayMetrics.DENSITY_560:
                return size * (DisplayMetrics.DENSITY_560 / DisplayMetrics.DENSITY_DEFAULT);
        }
        if (density > DisplayMetrics.DENSITY_560)
            throw new UnsupportedOperationException();
        return -1;
    }
	
	
}
