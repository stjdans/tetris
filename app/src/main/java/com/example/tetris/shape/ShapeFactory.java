package com.example.tetris.shape;

import java.util.Random;

public class ShapeFactory {
    public static final int NEMO = 0;
    public static final int HAT = 1;
    public static final int LIGHTNING = 2;
    public static final int LIGHTNING2 = 3;
    public static final int STICK = 4;
    public static final int GIUK = 5;
    public static final int GIUK2 = 6;

    private static final Random ran = new Random(System.currentTimeMillis());
    private static ShapeFactory instance;
    private int c_x;
    private int c_y;
    private int size;

    public static ShapeFactory getInstance(int c_x, int c_y, int size) {
        if (instance == null) {
            instance = new ShapeFactory(c_x, c_y, size);
        } else {
            instance.c_x = c_x;
            instance.c_y = c_y;
            instance.size = size;
        }
        return instance;
    }

    private ShapeFactory(int c_x, int c_y, int size) {
        this.c_x = c_x;
        this.c_y = c_y;
        this.size = size;
    }

    public Shape createRandom() {
        return createShape(getType());
    }

    public Shape createShape(int type) {
        if (type == NEMO) {
            return createNemo();
        } else if (type == HAT) {
            return createHat();

        } else if (type == LIGHTNING) {
            return createLightning();

        } else if (type == LIGHTNING2) {
            return createLightning2();

        } else if (type == GIUK) {
            return createGiuk();

        } else if (type == GIUK2) {
            return createGiuk2();

        } else {
            return createStick();
        }
    }

    private int getType() {
        int num = ran.nextInt(1000);
        if (num >= 900)
            return STICK;
        else if (num >= 800)
            return NEMO;
        else if (num >= 650)
            return GIUK;
        else if (num >= 500)
            return GIUK2;
        else if (num >= 350)
            return HAT;
        else if (num >= 170)
            return LIGHTNING;
        else
            return LIGHTNING2;
    }

    private Shape createHat() {
        int[][] metric = new int[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0}};
        return new Hat(metric, c_x, c_y, size);
    }

    private Shape createStick() {
        int[][] metric = new int[][]{
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}};
        return new Stick(metric, c_x, c_y, size);
    }

    private Shape createLightning() {
        int[][] metric = new int[][]{
                {1, 0, 0},
                {1, 1, 0},
                {0, 1, 0}};
        return new Lightning(metric, c_x, c_y, size);
    }

    private Shape createLightning2() {
        int[][] metric = new int[][]{
                {0, 0, 1},
                {0, 1, 1},
                {0, 1, 0}};
        return new Lightning2(metric, c_x, c_y, size);
    }

    private Shape createGiuk() {
        int[][] metric = new int[][]{
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}};
        return new Giuk(metric, c_x, c_y, size);
    }

    private Shape createGiuk2() {
        int[][] metric = new int[][]{
                {0, 1, 1},
                {0, 1, 0},
                {0, 1, 0}};
        return new Giuk2(metric, c_x, c_y, size);
    }

    private Shape createNemo() {
        int[][] metric = new int[][]{
                {1, 1},
                {1, 1}
        };
        return new Nemo(metric, c_x, c_y, size);
    }

    static class Nemo extends Shape {
        public Nemo(int[][] metric, int c_x, int c_y, int size) {
            // TODO Auto-generated constructor stub
            super(metric, c_x, c_y, size, NEMO);
        }

        @Override
        public void rotateL() {
            // TODO Auto-generated method stub
        }

        @Override
        public void rotateR() {
            // TODO Auto-generated method stub
        }

    }

    static class Stick extends Shape {
        public Stick(int[][] metric, int c_x, int c_y, int size) {
            // TODO Auto-generated constructor stub
            super(metric, c_x, c_y, size, STICK);
        }
    }

    static class Lightning extends Shape {
        public Lightning(int[][] metric, int c_x, int c_y, int size) {
            // TODO Auto-generated constructor stub
            super(metric, c_x, c_y, size, LIGHTNING);
        }
    }

    static class Lightning2 extends Shape {
        public Lightning2(int[][] metric, int c_x, int c_y, int size) {
            // TODO Auto-generated constructor stub
            super(metric, c_x, c_y, size, LIGHTNING2);
        }
    }

    static class Giuk extends Shape {
        public Giuk(int[][] metric, int c_x, int c_y, int size) {
            // TODO Auto-generated constructor stub
            super(metric, c_x, c_y, size, GIUK);
        }
    }

    static class Giuk2 extends Shape {
        public Giuk2(int[][] metric, int c_x, int c_y, int size) {
            // TODO Auto-generated constructor stub
            super(metric, c_x, c_y, size, GIUK2);
        }
    }

    static class Hat extends Shape {
        public Hat(int[][] metric, int c_x, int c_y, int size) {
            // TODO Auto-generated constructor stub
            super(metric, c_x, c_y, size, HAT);
        }

        @Override
        public void rotateL() {
            // TODO Auto-generated method stub
            super.rotateR();
        }

        @Override
        public void rotateR() {
            // TODO Auto-generated method stub
            super.rotateL();
        }
    }

}
