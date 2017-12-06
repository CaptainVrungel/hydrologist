package ru.hydrologist.ranges;

import ru.hydrologist.exceptions.InterpolatorException;

import java.util.Map;

public interface ProbabilityDistribution {

    String getDistributionName();
    Map<Double, Double> getRangeDistribution(Double Cv, Double CsCv) throws InterpolatorException;
    Double getDistributionForProbability(Double Probability, Double Cv, Double CsCv) throws InterpolatorException;
    Double getDistributionForProbability(Double Probability, Map<Double, Double> RangeDistribution);

}
