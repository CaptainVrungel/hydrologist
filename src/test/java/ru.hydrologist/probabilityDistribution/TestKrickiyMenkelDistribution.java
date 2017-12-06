package ru.hydrologist.probabilityDistribution;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.hydrologist.DataSets.DataSetKrickiyMenkelDistr;
import ru.hydrologist.exceptions.InterpolatorException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by fedorovskiy on 08.03.2017.
 */
public class TestKrickiyMenkelDistribution {

    private final int timeout = 3000;                                            //Таймаут
    private double accuracy = 0.0000001;                                        //Точность вычислений
    private static ArrayList<Map<String, Map<Double, Double>>> DataSet;           //Набор тестовых значений.
    public Logger log = LogManager.getLogger(TestKrickiyMenkelDistribution.class);                  //Объект для логирования

    //Получаем наборы данных для тестирования
    @BeforeClass
    public static void getTestDataSet() {
        DataSet = DataSetKrickiyMenkelDistr.getInstance().getDataSet();
    }

    @Test(timeOut = timeout)
    public void testGetRangeDistribution() throws InterpolatorException {

            for (Map<String, Map<Double, Double>> dataSet : DataSet) {

                Map<Double, Double> ultimateTruth = dataSet.get("Distribution");
                Map<Double, Double> result = new KrickyMenkelDistribution().getRangeDistribution(dataSet.get("Cv").get(0.0), dataSet.get("CsCv").get(0.0));

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
        KrickyMenkelDistribution distribution = new KrickyMenkelDistribution();

        for (Map<String, Map<Double, Double>> dataSet : DataSet) {

            Double ultimateTruth = dataSet.get("DistributionForProbability").get(0.0);
            Double result1 = distribution.getDistributionForProbability(dataSet.get("P").get(0.0), dataSet.get("Distribution"));

            if (result1 - ultimateTruth > accuracy || result1 - ultimateTruth < -accuracy) {
                log.error("Полученное значение: " + result1 + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }

            Double result2 = distribution.getDistributionForProbability(dataSet.get("P").get(0.0), dataSet.get("Cv").get(0.0), dataSet.get("CsCv").get(0.0));

            if (result2 - ultimateTruth > accuracy || result2 - ultimateTruth < -accuracy) {
                log.error("Полученное значение: " + result2 + "Фктическое значение: " + ultimateTruth);
                Assert.fail();
            }
        }

    }

}
