package ru.hydrologist.probabilityDistribution;

import ru.hydrologist.coefficients.distributions.KrickyMenkelDistributionCoefficients;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.ranges.ProbabilityDistribution;
import ru.hydrologist.utils.DataConverter;
import ru.hydrologist.utils.Interpolator;

import java.util.ArrayList;
import java.util.Map;

public class KrickyMenkelDistribution implements ProbabilityDistribution {

    final String DISTRIBUTIONNAME = "Распределение Крицкого-Менкеля";

    public Map<Double, Double> getRangeDistribution(Double Cv, Double CsCv) throws InterpolatorException {
        KrickyMenkelDistributionCoefficients coefficients = KrickyMenkelDistributionCoefficients.getInstance();
        Double[] CvBetween = Interpolator.findBetweenWhich(Cv, DataConverter.toArrayList(coefficients.getCvValues()));
        Double[] CsCvBetween = Interpolator.findBetweenWhich(CsCv, DataConverter.toArrayList(coefficients.getCsCvValues()));

        Map<Double, Double> distr11 = KrickyMenkelDistributionCoefficients.getInstance().getDistribution().get(CsCvBetween[0]).get(CvBetween[0]);
        Map<Double, Double> distr12 = KrickyMenkelDistributionCoefficients.getInstance().getDistribution().get(CsCvBetween[0]).get(CvBetween[1]);
        Map<Double, Double> distr21 = KrickyMenkelDistributionCoefficients.getInstance().getDistribution().get(CsCvBetween[1]).get(CvBetween[0]);
        Map<Double, Double> distr22 = KrickyMenkelDistributionCoefficients.getInstance().getDistribution().get(CsCvBetween[1]).get(CvBetween[1]);

        Map<Double, Double> distribution = Interpolator.distributionMapDoubleInterpolation(CvBetween, CvBetween, Cv, CsCvBetween, CsCv, distr11, distr12, distr21, distr22);

        return distribution;
    }

    //Получаем конкретное значение распределения для определённой вероятности
    public Double getDistributionForProbability(Double Probability, Double Cv,Double CsCv) throws InterpolatorException {
        return getDistributionForProbability(Probability, getRangeDistribution(Cv, CsCv));
    }

    //Получаем конкретное значение распределения для определённой вероятности
    public Double getDistributionForProbability(Double Probability, Map<Double, Double> RangeDistribution){

        ArrayList<Double> p = DataConverter.toArrayList(KrickyMenkelDistributionCoefficients.getInstance().getPValues());
        Double[] prob = Interpolator.findBetweenWhich(Probability, p);

        Double distr_1 = RangeDistribution.get(prob[0]);
        Double distr_2 = RangeDistribution.get(prob[1]);

        return Interpolator.interpolation(Probability, prob[0], prob[1], distr_1, distr_2);
    }

    public String getDistributionName() {
        return this.DISTRIBUTIONNAME;
    }
}
