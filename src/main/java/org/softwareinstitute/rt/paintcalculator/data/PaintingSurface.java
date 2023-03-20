package org.softwareinstitute.rt.paintcalculator.data;

import java.math.BigDecimal;

public class PaintingSurface {

    private String type;
    private int height;
    private int width;
    private int totalPaintArea;
    private BigDecimal cost;
    private String finishType;
    private String paintColour;

    public PaintingSurface(String type, int height, int width){
        this.type = type;
        this.height = height;
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getTotalPaintArea() {
        return totalPaintArea;
    }

    public void setTotalPaintArea(int totalPaintArea) {
        this.totalPaintArea = totalPaintArea;
    }

    public String getFinishType() {
        return finishType;
    }

    public void setFinishType(String finishType) {
        this.finishType = finishType;
    }

    public String getPaintColour() {
        return paintColour;
    }

    public void setPaintColour(String paintColour) {
        this.paintColour = paintColour;
    }
}
