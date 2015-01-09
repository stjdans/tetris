package com.example.tetris.env;

import java.util.List;

import com.example.tetris.config.GameConfig;

public class CollisionManager {
	public static final int TOP = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM = 3;

	private Block[] rects;
	private static CollisionManager instance;
	public int c_y, c_x, size;

	private CollisionManager() {
		this.c_x = GameConfig.getInitMapX();
		this.c_y = GameConfig.getInitMapY();
		this.size = GameConfig.size;
		initRects();
	}

	public static CollisionManager getInstance() {
		if (instance == null) {
			instance = new CollisionManager();
		} else {
			instance.c_x = GameConfig.getInitMapX();
			instance.c_y = GameConfig.getInitMapY();
			instance.initRects();
		}
		return instance;
	}

	public boolean isCollisionShape(List<Block> shape, List<Block> map) {
		for (Block t1 : map) {
			for (Block t2 : shape) {
				if (t1.equals(t2))
					return true;
			}
		}
		return false;
	}

	public boolean isCollisionPosition(List<Block> list, int position) {
		for (Block data : list) {
			if (rects[position].col == data.col|| rects[position].row == data.row)
				return true;
		}
		return false;
	}

	private void initRects() {
		int m_width = GameConfig.getMapWidth();
		int m_heigth = GameConfig.getMapHeight();

		 this.rects = new Block[4];
		 rects[0] = new Block(-1, -1, c_x, c_y - size * 2, c_x + m_width, c_y, size);
		 rects[1] = new Block(-1, -1, c_x - size * 2, c_y, c_x, c_y + m_heigth, size);
		 rects[2] = new Block(-1, GameConfig.col, c_x + m_width, c_y, c_x + m_width + size * 2, c_y	+ m_heigth, size);
		 rects[3] = new Block(GameConfig.row, -1, c_x, c_y + m_heigth, c_x + m_width, c_y + m_heigth+ size * 2, size);
		
	}

}
