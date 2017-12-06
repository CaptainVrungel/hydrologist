package ru.hydrologist.ranges;

import java.util.ArrayList;
import java.util.Map;

public interface EmpiricRangable {

    Double getCv();
    Double getCs();
    Double getCsCv();
    Double getR();
    Map<Double, Double> getRange();
    Double getRangeAverage();
    ArrayList<Double> getRangedRange();
    ArrayList<Double> getProbabilities();

}
