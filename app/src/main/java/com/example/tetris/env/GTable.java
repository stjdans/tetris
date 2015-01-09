package com.example.tetris.env;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.tetris.config.GameConfig;

import java.util.ArrayList;
import java.util.List;

public class GTable implements Parcelable {
	private static final int YES = 1;

	private int[][] game_map;
	private Block[][] t_map;
	private int[] delLine;

	private int r_length;
	private int c_length;

	private List<Block> list = new ArrayList<Block>();

	Handler han;
	private boolean isChange = false;
	private boolean isEnd = false;

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public GTable(Handler hand) {
		// TODO Auto-generated constructor stub
		this.r_length = GameConfig.row;
		this.c_length = GameConfig.col;
		this.game_map = new int[r_length][c_length];
		this.t_map = new Block[r_length][c_length];
		this.delLine = new int[r_length];
		this.han = hand;
	}

	public Handler getHan() {
		return han;
	}

	public void setHan(Handler han) {
		this.han = han;
	}

	public void DelLine() {
		int count = 0;
		for (int row = 0; row < r_length; row++) {
			if (delLine[row] == c_length) {
				delRow(row);
				moveBlock(row);
				moveTable(row - 1);
				moveDelLineAR(row);
				delRow(0);
				count++;
			}
		}
		if (count > 0) {
			Message msg = han.obtainMessage();
			msg.arg1 = count;
			msg.what = 1;
			han.sendMessage(msg);
		}
	}

	public boolean isEnd() {
		return this.isEnd;
	}

	private void moveTable(int row) {
		for (int i = row; i >= 0; i--) {
			System.arraycopy(game_map[i], 0, game_map[i + 1], 0, c_length);
			System.arraycopy(t_map[i], 0, t_map[i + 1], 0, c_length);
		}
	}

	private void moveBlock(int row) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < c_length; j++) {
				if (t_map[i][j] != null)
					t_map[i][j].MoveDown();
			}
		}
	}

	private void moveDelLineAR(int col) {
		if (col == 0)
			return;
		System.arraycopy(delLine, 0, delLine, 1, col);
	}

	private void delRow(int row) {
		for (int col = 0; col < c_length; col++) {
			game_map[row][col] = 0;
			t_map[row][col] = null;
			delLine[row] = 0;
		}
	}

	public void inputDataFromList(List<Block> table) {
		for (Block t : table) {
			if (t.row == 0) {
				isEnd = true;
				return;
			}
			inputData(t.row, t.col, t);
		}
		isChange = true;
	}

	public void changeList() {
		if (!isChange)
			return;

		list.clear();
		for (int i = 0; i < r_length; i++) {
			for (int j = 0; j < c_length; j++) {
				if (t_map[i][j] != null)
					list.add(t_map[i][j]);
			}
		}
		isChange = false;
	}

	public List<Block> getBlocks() {
		changeList();
		return list;
	}

	private void inputData(int row, int col, Block t) {
		game_map[row][col] = YES;
		delLine[row] += YES;
		t_map[row][col] = t;
	}

	public void drawMapData(Canvas can, Paint p, Bitmap bitmap) {
		for (int i = 0; i < r_length; i++) {
			for (int j = 0; j < c_length; j++) {
				if (t_map[i][j] != null)
//					can.drawRect(t_map[i][j].left, t_map[i][j].top,
//							t_map[i][j].right, t_map[i][j].bottom, p);
                    can.drawBitmap(bitmap,null,new Rect(t_map[i][j].left, t_map[i][j].top,
							t_map[i][j].right, t_map[i][j].bottom),p);
			}
		}
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		for (int i = 0; i < r_length; i++) {
			dest.writeIntArray(game_map[i]);
		}

		for (int i = 0; i < r_length; i++) {
			dest.writeArray(t_map[i]);
		}
		for (int i = 0; i < r_length; i++) {
			dest.writeIntArray(delLine);
		}
		dest.writeInt(r_length);
		dest.writeInt(c_length);
		dest.writeList(list);
		dest.writeBooleanArray(new boolean[] { isChange });

	}

	public static final Parcelable.Creator<GTable> creator = new Creator<GTable>() {

		@Override
		public GTable[] newArray(int size) {
			// TODO Auto-generated method stub
			return new GTable[size];
		}

		@Override
		public GTable createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			GTable gt = new GTable(null);
			int r_length = gt.r_length;
			int c_length = gt.c_length;
			for (int i = 0; i < r_length; i++) {
				source.readIntArray(gt.game_map[i]);
			}
			for (int i = 0; i < r_length; i++) {
				gt.t_map[i] = (Block[]) source.readArray(getClass()
						.getClassLoader());
			}
			for (int i = 0; i < r_length; i++) {
				source.readIntArray(gt.delLine);
			}
			source.readList(gt.list, getClass().getClassLoader());
			boolean[] bool = new boolean[1];
			source.readBooleanArray(bool);
			gt.isChange = bool[0];

			return gt;
		}
	};
}
