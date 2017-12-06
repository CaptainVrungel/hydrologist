package ru.hydrologist.ranges;

import ru.hydrologist.exceptions.InterpolatorException;

import java.util.Map;

public interface AnalystRangable {

    String getDistributionName();
    Map<Double, Double> getAnalystRange() throws InterpolatorException;

}
