package ru.hydrologist.drawing;

import java.awt.*;
import java.util.List;

public interface DrawableData {

    Double getYPointsAverage();
    Double getXPointsAverage();
    Double getYPointsMaximum();
    double getXPointsMaximum();
    Double getYPointsMinimum();
    Double getXPointsMinimum();

    List<GraphPoint> getPoints();
    Color getPointsColor();
    Boolean DrawInscription();
    Boolean DrawConnectionLine();
    Boolean fillPoints();
    void addPoint(GraphPoint arg);
    void removePoint(GraphPoint arg);
}
