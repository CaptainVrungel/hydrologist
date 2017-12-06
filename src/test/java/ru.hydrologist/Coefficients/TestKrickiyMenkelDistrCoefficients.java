package ru.hydrologist.Coefficients;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hydrologist.coefficients.distributions.KrickyMenkelDistributionCoefficients;

import java.util.Map;

/**
 * Created by fedorovskiy on 08.03.2017.
 */
public class TestKrickiyMenkelDistrCoefficients {
    public Logger log = LogManager.getLogger(TestPirsonDistributionCoefficients.class);                  //Объект для логирования
    private double accuracy = 0.0000001;

    @Test
    public void testKrickiyMenkelDistributionCoefficients(){

        KrickyMenkelDistributionCoefficients coeff = KrickyMenkelDistributionCoefficients.getInstance();
        Map<Double, Map<Double, Map<Double, Double>>> distribution = coeff.getDistribution();

        Double ultimateTruth = 0.01;
        Double result = distribution.get(6.0).get(1.5).get(99.9);

        if(ultimateTruth - result > accuracy || ultimateTruth - result < -accuracy){
            log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
            Assert.fail();
        }
    }
}
