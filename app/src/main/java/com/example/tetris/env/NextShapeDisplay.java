package com.example.tetris.env;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tetris.R;
import com.example.tetris.config.GameConfig;
import com.example.tetris.shape.Shape;
import com.example.tetris.shape.ShapeFactory;

import java.util.ArrayList;

public class NextShapeDisplay {
    private static final int MAX = 5;
    private ShapeFactory factory;

    private ArrayList<Shape> list;
    private static NextShapeDisplay instance;

    private int c_x, c_y;
    private int size;
    private Bitmap[] images;

    private NextShapeDisplay(int c_x, int c_y, int size, ShapeFactory factory, Context context) {
        // TODO Auto-generated constructor stub
        this.factory = factory;
        this.c_x = c_x;
        this.c_y = c_y;
        this.size = size;
        this.list = new ArrayList<Shape>();
        for (int i = 0; i < MAX; i++) {
            list.add(factory.createRandom());
        }
        images = new Bitmap[GameConfig.TYPE];
        for (int i = 0; i < images.length; i++) {
            images[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sh0 + i);
        }
        initNextShaps(0);
    }

    public static NextShapeDisplay getInstance(int c_x, int c_y, int size,
                                               ShapeFactory factory, Context context) {
        if (instance == null)
            instance = new NextShapeDisplay(c_x, c_y, size, factory, context);
        else {
            instance.c_x = c_x;
            instance.c_y = c_y;
            instance.size = size;
        }
        return instance;
    }

    //	private void initNextShaps() {
//		Shape s = null;
//		for (int i = 0; i < MAX; i++) {
//			s = list.get(i);
//			s.setC_x(c_x + s.getSize() * 4 * i);
//			s.setC_y(c_y - s.getSize() * 4);
//			s.setC_x(c_x + images[0].getWidth() * i);
//			s.setC_y(c_y - images[0].getHeight());
//			s.setSize(this.size/3*2);
//			
//		}
//	}
    private void initNextShaps(int idx) {
        Shape s = null;
        for (int i = idx; i < MAX; i++) {
            s = list.get(i);
//			s.setC_x(c_x + s.getSize() * 4 * i);
//			s.setC_y(c_y - s.getSize() * 4);
            s.setC_x(c_x + images[0].getWidth() * i);
            s.setC_y(c_y - images[0].getHeight());
            s.setSize(this.size / 3 * 2);
        }

    }

    public void drawNextShapList(Canvas can, Paint p) {
        for (Shape s : list) {
            can.drawBitmap(images[s.getType()], s.getC_x(), s.getC_y(), p);
        }
    }

    /*public void drawNextShapList(Canvas can, Paint p) {
        for(Shape s : list){
            s.drawShape(can, p);
        }
    }
*/
    public Shape getNextShape() {
        int type = list.remove(0).getType();
        Shape s = factory.createRandom();
        list.add(s);
        initNextShaps(MAX - 1);
        initNextShaps(0);
        return factory.createShape(type);

    }

}
