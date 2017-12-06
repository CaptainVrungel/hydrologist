package ru.hydrologist.Coefficients;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hydrologist.coefficients.distributions.PirsonDistributionCoefficients;

import java.util.Map;

/**
 * Created by fedorovskiy on 07.03.2017.
 */
public class TestPirsonDistributionCoefficients {
    public Logger log = LogManager.getLogger(TestPirsonDistributionCoefficients.class);                  //Объект для логирования
    private double accuracy = 0.0000001;

    @Test
    public void testPirsonDistributionCoefficients(){

        PirsonDistributionCoefficients coeff = PirsonDistributionCoefficients.getInstance();
        Map<Double, Map<Double, Double>> distr = coeff.getDistribution();

        Double ultimateTruth = -0.38;
        Double result = distr.get(5.2).get(99.9);

        if(ultimateTruth - result > accuracy || ultimateTruth - result < -accuracy){
            log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
            Assert.fail();
        }
    }

}
