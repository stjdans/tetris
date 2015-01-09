package com.example.tetris.config;

import java.io.Serializable;

public class ListViewItem implements Serializable{
	int point, stage, rank;
	public ListViewItem(int point, int stage) {
		super();
		
		this.point = point;
		this.stage = stage;
		this.rank  = 10;
	}

	public ListViewItem(int point, int stage, int rank) {
		super();
		this.point = point;
		this.stage = stage;
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
}

