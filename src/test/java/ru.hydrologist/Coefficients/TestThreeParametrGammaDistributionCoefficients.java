package ru.hydrologist.Coefficients;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hydrologist.coefficients.distributions.ThreeParametrGammaDistributionCoefficients;

import java.util.Map;

/**
 * Created by fedorovskiy on 04.03.2017.
 */
public class TestThreeParametrGammaDistributionCoefficients {
    public Logger log = LogManager.getLogger(TestThreeParametrGammaDistributionCoefficients.class);                  //Объект для логирования
    private double accuracy = 0.0000001;

    @Test
    public void testThreeParamGammaDistrCoefficients(){

        ThreeParametrGammaDistributionCoefficients coeff = ThreeParametrGammaDistributionCoefficients.getInstance();
        Map<Double, Map<Double, Map<Double, Double>>> distr = coeff.getDistribution();

        Double ultimateTruth = 6.38;
        Double result = distr.get(1.5).get(0.9).get(0.01);

        if(ultimateTruth - result > accuracy || ultimateTruth - result < -accuracy){
            log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
            Assert.fail();
        }
    }
}
