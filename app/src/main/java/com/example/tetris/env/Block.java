package com.example.tetris.env;

import java.io.Serializable;

public class Block implements Serializable {
	int left, right, top, bottom;
	int row, col, size;

	public Block(int row, int col, int left, int top, int right, int bottom,
			int size) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		this.row = row;
		this.col = col;
		this.size = size;
	}

	public Block() {
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Block block) {
		// TODO Auto-generated method stub
		return this.row == block.row && this.col == block.col;
	}

	public void MoveDown() {
		top += size;
		bottom += size;
		row++;
	}
}
