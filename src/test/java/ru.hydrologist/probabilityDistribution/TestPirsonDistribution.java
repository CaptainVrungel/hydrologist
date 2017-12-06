package ru.hydrologist.probabilityDistribution;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.hydrologist.DataSets.DataSetPirsonDistr;
import ru.hydrologist.coefficients.distributions.PirsonDistributionCoefficients;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.utils.DataConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestPirsonDistribution {

    private final int timeout = 3000;                                            //Таймаут
    private double accuracy = 0.0001;                                        //Точность вычислений
    private static ArrayList<Map<String, ArrayList<Double>>> DataSet;           //Набор тестовых значений.
    public Logger log = LogManager.getLogger(TestThreeParametrGammaDistribution.class);                  //Объект для логирования

    //Получаем наборы данных для тестирования
    @BeforeClass
    public static void getTestDataSet() {
            DataSetPirsonDistr dst = new DataSetPirsonDistr();
            DataSet = dst.getDataSet();
    }

    //Проверяем получение распределения вероятностей
    @Test(timeOut = timeout)
    public void testGetRangeDistribution() throws InterpolatorException {

        for (Map<String, ArrayList<Double>> dataSet : DataSet) {
            Map<Double, Double> ultimateTruth = DataConverter.ListToMap(dataSet.get("Distribution"), PirsonDistributionCoefficients.getInstance().getPirsonPValues());

            Map<Double, Double> result = new PirsonDistribution().getRangeDistribution(dataSet.get("Cs").get(0), 0.0);

            for (Map.Entry<Double, Double> entry : result.entrySet()) {
                if (entry.getValue() - ultimateTruth.get(entry.getKey()) > accuracy || entry.getValue() - ultimateTruth.get(entry.getKey()) < -accuracy) {
                    log.error("Полученное значение: " + entry.getValue() + "Фактическое значение: " + ultimateTruth.get(entry.getKey()) + "Ключ: " + entry.getKey());
                    Assert.fail();
                }
            }
        }
    }

    //Проверяем получение коэффициента для конкретной вероятности
    @Test(timeOut = timeout)
    public void testGetDistributionForProbability() throws InterpolatorException {
        PirsonDistribution distr = new PirsonDistribution();
        for( Map<String, ArrayList<Double>>dataSet : DataSet){


            Map<Double, Double> rangeDistrConverted  = new HashMap<Double, Double>();
            Double[] PValues = PirsonDistributionCoefficients.getInstance().getPirsonPValues();

            for(int i=0; i<PValues.length; i++){
                rangeDistrConverted.put(PValues[i], dataSet.get("Distribution").get(i));
            }

            Double ultimateTruth = dataSet.get("DistributionForProbability").get(0);
            Double result1 = distr.getDistributionForProbability(dataSet.get("P").get(0), rangeDistrConverted);

            if(result1 - ultimateTruth > accuracy || result1 - ultimateTruth < -accuracy){
                log.error("Полученное значение: " + result1 + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }

            Double result2 = distr.getDistributionForProbability(dataSet.get("P").get(0), dataSet.get("Cs").get(0), 0.0);

            if(result2 - ultimateTruth > accuracy || result2 - ultimateTruth < -accuracy){
                log.error("Полученное значение: " + result2 + "Фктическое значение: " + ultimateTruth);
                Assert.fail();
            }
        }

    }
}
