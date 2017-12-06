package ru.hydrologist.ranges;

import ru.hydrologist.exceptions.InterpolatorException;

import java.util.HashMap;
import java.util.Map;

public class AnalystRange implements AnalystRangable{
    EmpiricRangable empiricRange;
    ProbabilityDistribution distribution;
    Map<Double, Double> analystRange = new HashMap<Double, Double>();

    public AnalystRange(EmpiricRangable empiricRange, String distributionType) throws InterpolatorException {
        this.empiricRange = empiricRange;

        try{
            this.distribution = (ProbabilityDistribution) Class.forName(distributionType).newInstance();
        }catch (Exception e){
            throw new IllegalArgumentException("Unable to instantiate " + distributionType);
        }

        for(Map.Entry<Double, Double> entry : distribution.getRangeDistribution(empiricRange.getCv(), empiricRange.getCsCv()).entrySet()){
            this.analystRange.put(entry.getKey(), entry.getValue()*empiricRange.getRangeAverage());
        }
    }

    public AnalystRange(EmpiricRangable empiricRange, ProbabilityDistribution distribution) throws InterpolatorException {
        this.empiricRange = empiricRange;
        this.distribution = distribution;

        for(Map.Entry<Double, Double> entry : distribution.getRangeDistribution(empiricRange.getCv(), empiricRange.getCsCv()).entrySet()){
            this.analystRange.put(entry.getKey(), entry.getValue()*empiricRange.getRangeAverage());
        }
    }

    public Map<Double, Double> getAnalystRange() throws InterpolatorException {
        return analystRange;
    }

    public String getDistributionName() {
        return distribution.getDistributionName();
    }

}
