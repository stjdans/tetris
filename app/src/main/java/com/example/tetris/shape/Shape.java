package com.example.tetris.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.tetris.config.GameConfig;
import com.example.tetris.env.Block;

import java.util.ArrayList;
import java.util.List;

public class Shape implements ShapeImp {
    private int[][] metric;
    private int c_x, c_y;
    private int size;
    private int type;

    private int initX, initY;
    private int length;

    List<Block> list;

    public Shape(int[][] metric, int c_x, int c_y, int size, int type) {
        // TODO Auto-generated constructor stub
        this.metric = metric;
        this.c_x = c_x;
        this.c_y = c_y;
        this.size = size;
        this.type = type;
        this.initX = GameConfig.getInitMapX();
        this.initY = GameConfig.getInitMapY();
        this.length = metric.length;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int getColFromCenter() {
        return (c_x - initX == 0) ? 0 : (c_x - initX) / size;
    }

    private int getRowFromCenter() {
        return (c_y - initY == 0) ? 0 : (c_y - initY) / size;
    }

    public void setC_x(int c_x) {
        this.c_x = c_x;
    }

    public void setC_y(int c_y) {
        this.c_y = c_y;
    }

    public int getC_x() {
        return c_x;
    }

    public int getC_y() {
        return c_y;
    }

    public int getSize() {
        return size;
    }

    public int getType() {
        return type;
    }

    @Override
    public void moveDown() {
        // TODO Auto-generated method stub
        c_y += size;
    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub
        c_x -= size;
    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub
        c_x += size;
    }

    @Override
    public void moveUp() {
        // TODO Auto-generated method stub
        c_y -= size;
    }

    @Override
    public void rotateL() {
        // TODO Auto-generated method stub
        int[][] temp = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                temp[i][j] = metric[length - 1 - j][i];
            }
        }
        metric = temp;
    }

    @Override
    public void rotateR() {
        // TODO Auto-generated method stub
        int[][] temp = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                temp[i][j] = metric[j][length - 1 - i];
            }
        }
        metric = temp;
    }

    public List<Block> getTableList() {
        // TODO Auto-generated method stub
        list = new ArrayList<Block>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (metric[i][j] == 1) {
                    list.add(new Block(getRowFromCenter() + i,
                            getColFromCenter() + j, c_x + size * j,
                            c_y + size * i, c_x + (size * j) + size,
                            c_y + (size * i) + size, size));
                    Log.i("위치", new Block(getRowFromCenter() + i,
                            getColFromCenter() + j, c_x + size * j,
                            c_y + size * i, c_x + (size * j) + size,
                            c_y + (size * i) + size, size).toString());
                }
            }
        }
        return list;
    }

    public void drawShape(Canvas can, Paint p) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (metric[i][j] == 1) {
                    can.drawRect(c_x + size * j, c_y + size * i, c_x
                            + (size * j) + size, c_y + (size * i) + size, p);
                }
            }
        }
    }

}
