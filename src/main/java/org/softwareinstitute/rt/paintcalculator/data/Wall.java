package org.softwareinstitute.rt.paintcalculator.data;

public class Wall {

    private int height;
    private int width;
    private int totalPaintArea;

    public Wall(int height, int width){
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTotalPaintArea() {
        return totalPaintArea;
    }

    public void setTotalPaintArea(int totalPaintableArea) {
        this.totalPaintArea = totalPaintableArea;
    }
}
