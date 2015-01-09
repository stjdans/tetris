package com.example.tetris.config;

public class GameConfig {
	static public int col = 7; 			// 맵 테이블 로우
	static public int row = 12; 		// 맵 테이블 컬럼
	static public int size = 20; 		// 블록 사이즈
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
	
	
}
