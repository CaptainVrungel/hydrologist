package ru.hydrologist.drawing;


import java.util.List;

public interface DrawableDataSet {

    Double getYPointsAverage();
    Double getXPointsAverage();
    Double getYPointsMaximum();
    double getXPointsMaximum();
    Double getYPointsMinimum();
    Double getXPointsMinimum();

    List<DrawableData> getDrawingDataSet();
    void addGraphObject(DrawableData arg);
    void removeGraphObject(DrawableData arg);
}
