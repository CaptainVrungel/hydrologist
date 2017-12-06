package ru.hydrologist.probabilityDistribution;

import ru.hydrologist.coefficients.distributions.PirsonDistributionCoefficients;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.ranges.ProbabilityDistribution;
import ru.hydrologist.utils.DataConverter;
import ru.hydrologist.utils.Interpolator;

import java.util.ArrayList;
import java.util.Map;

public class PirsonDistribution implements ProbabilityDistribution {

    final String DISTRIBUTIONNAME = "Распределение Пирсона";

    public Map<Double, Double> getRangeDistribution(Double Cs, Double Cv) throws InterpolatorException {

        Double[] CsBetween = Interpolator.findBetweenWhich(Cs, PirsonDistributionCoefficients.getInstance().getPirsonCsValuesAsList());

        Map<Double, Double> distr_1 = PirsonDistributionCoefficients.getInstance().getDistribution().get(CsBetween[0]);
        Map<Double, Double> distr_2 = PirsonDistributionCoefficients.getInstance().getDistribution().get(CsBetween[1]);

        return Interpolator.distributionMapInterpolation(Cs, CsBetween[0], CsBetween[1], distr_1, distr_2);
    }

    public Double getDistributionForProbability(Double Probability, Double Cs, Double Cv) throws InterpolatorException {
        return getDistributionForProbability(Probability, getRangeDistribution(Cs, Cv));
    }

    public Double getDistributionForProbability(Double Probability, Map<Double, Double> RangeDistribution){
        Double result = 0.0;

        ArrayList<Double> p = DataConverter.toArrayList(PirsonDistributionCoefficients.getInstance().getPirsonPValues());
        Double[] prob = Interpolator.findBetweenWhich(Probability, p);

        Double distr_1 = RangeDistribution.get(prob[0]);
        Double distr_2 = RangeDistribution.get(prob[1]);

        return Interpolator.interpolation(Probability, prob[0], prob[1], distr_1, distr_2);
    }

    public String getDistributionName() {
        return this.DISTRIBUTIONNAME;
    }

}
