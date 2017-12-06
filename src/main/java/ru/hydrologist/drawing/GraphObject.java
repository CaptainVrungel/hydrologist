package ru.hydrologist.drawing;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphObject implements DrawableData{

    private List<GraphPoint> points = new ArrayList<GraphPoint>();
    private Color pointsColor  = Color.black;
    private Boolean fillPoints = true;              //Параметр заливки точек - если true, то заливаем, если false, то нет.
    private Boolean drawInscription = true;
    private Boolean drawConnectionLine = false;

    private List<Double> xPoints = new ArrayList<Double>();
    private List<Double> yPoints = new ArrayList<Double>();

    public GraphObject(Color pointsColor, Boolean drawInscription, Boolean drawConnectionLine, Boolean fillPoints){
        this();
        this.pointsColor = pointsColor;
        this.drawInscription = drawInscription;
        this.drawConnectionLine = drawConnectionLine;
        this.fillPoints = fillPoints;

    }

    public GraphObject(){
    }

    public void addPoint(GraphPoint arg){
        points.add(arg);
        xPoints.add(arg.getXCoordinate());
        yPoints.add(arg.getYCoordinate());
    }

    private Double calculatePointsAverage(List<Double> list){
        return calculatePointsSum(list)/((double) points.size());
    }

    private Double calculatePointsSum(List<Double> list){
        Double sum = 0.0;
        for(Double value : list){
            sum = sum + value ;
        }
        return sum;
    }

    private Double calculatePointsMaximum(List<Double> list){
       return Collections.max(list);
    }

    private Double calculatePointsMinimum(List<Double> list){
        return Collections.min(list);
    }

    private List<Double> getYCoordinates(){
        List<Double> yCoordinates = new ArrayList<Double>();
        for(GraphPoint point : points){
            yCoordinates.add(point.getYCoordinate());
        }
        return yCoordinates;
    }

    private List<Double> getXCoordinates(){
        List<Double> xCoordinates = new ArrayList<Double>();
        for(GraphPoint point : points){
            xCoordinates.add(point.getXCoordinate());
        }
        return xCoordinates;
    }

    public void removePoint(GraphPoint arg){
        points.remove(arg);
    }

    public Double getYPointsAverage(){
        return calculatePointsAverage(getYCoordinates());
    }

    public Double getXPointsAverage(){
        return calculatePointsAverage(getXCoordinates());
    }

    public Double getYPointsMaximum(){
        return calculatePointsMaximum(yPoints);
    }

    public double getXPointsMaximum(){
        return calculatePointsMaximum(xPoints);
    }

    public Double getYPointsMinimum(){
        return calculatePointsMinimum(yPoints);
    }

    public Double getXPointsMinimum(){
        return calculatePointsMinimum(xPoints);
    }

    public List<GraphPoint> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public Color getPointsColor() {
        return pointsColor;
    }

    public Boolean DrawInscription() {
        return drawInscription;
    }

    public Boolean DrawConnectionLine() {
        return drawConnectionLine;
    }


    public Boolean fillPoints() {
        return fillPoints;
    }
}
