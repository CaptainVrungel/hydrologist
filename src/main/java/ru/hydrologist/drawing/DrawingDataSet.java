package ru.hydrologist.drawing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawingDataSet implements DrawableDataSet{
    private List<DrawableData> drawingDataSet = new ArrayList<DrawableData>();

    public void addGraphObject(DrawableData arg){
        drawingDataSet.add(arg);
    }

    public void removeGraphObject(DrawableData arg){
        drawingDataSet.remove(arg);
    }

    public List<DrawableData> getDrawingDataSet() {
        return Collections.unmodifiableList(drawingDataSet);
    }

    public Double getYPointsAverage(){
        List<Double> average = new ArrayList<Double>();
        for(DrawableData obj : drawingDataSet){
            average.add(obj.getYPointsAverage());
        }

        return calculateListSum(average)/((double) average.size());
    }

    public Double getXPointsAverage(){
        List<Double> average = new ArrayList<Double>();
        for(DrawableData obj : drawingDataSet){
            average.add(obj.getXPointsAverage());
        }

        return calculateListSum(average)/((double) average.size());
    }

    public Double getYPointsMaximum(){
        List<Double> max = new ArrayList<Double>();
        for(DrawableData obj : drawingDataSet){
            max.add(obj.getYPointsMaximum());
        }

        return Collections.max(max);
    }

    public double getXPointsMaximum(){
        List<Double> max = new ArrayList<Double>();
        for(DrawableData obj : drawingDataSet){
            max.add(obj.getXPointsMaximum());
        }

        return Collections.max(max);
    }

    public Double getYPointsMinimum(){
        List<Double> min = new ArrayList<Double>();
        for(DrawableData obj : drawingDataSet){
            min.add(obj.getYPointsMinimum());
        }

        return Collections.max(min);
    }

    public Double getXPointsMinimum(){
        List<Double> min = new ArrayList<Double>();
        for(DrawableData obj : drawingDataSet){
            min.add(obj.getYPointsMinimum());
        }

        return Collections.max(min);
    }

    private Double calculateListSum(List<Double> list){
        Double sum = 0.0;
        for(Double value : list){
            sum = sum + value ;
        }
        return sum;
    }

}
