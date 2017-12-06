package ru.hydrologist.drawing;

public class GraphPoint {
    private Double xCoordinate;
    private Double yCoordinate;

    public GraphPoint(Double x, Double y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public Double getXCoordinate() {
        return xCoordinate;
    }

    public Double getYCoordinate() {
        return yCoordinate;
    }
}
