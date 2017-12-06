package ru.hydrologist.ranges;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.hydrologist.DataSets.DataSetRange;
import ru.hydrologist.exceptions.InterpolatorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestAnalystRange {

    private final int timeout = 3000;                                            //Таймаут
    private double accuracy = 0.0000001;                                        //Точность вычислений
    private int accuracyRank = 6;
    private static ArrayList<Map<String, ArrayList<Double>>> EmpiricDataSet;           //Набор тестовых значений.
    public Logger log = LogManager.getLogger(TestAnalystRange.class);                  //Объект для логирования

    //Получаем наборы данных для тестирования
    @BeforeClass
    public static void getTestDataSet() {
        EmpiricDataSet = new DataSetRange().getDataSet();
    }

    @Test(timeOut = timeout)
    public void getDistributionName() throws InterpolatorException {
        for (Map<String, ArrayList<Double>> dataSet : EmpiricDataSet) {
            EmpiricRange empiricRange = new EmpiricRange(dataSet.get("range"));

            Map<String, String> names = new HashMap<String, String>();
            names.put("Распределение Крицкого-Менкеля", "ru.hydrologist.probabilityDistribution.KrickyMenkelDistribution");
            names.put("Распределение Пирсона", "ru.hydrologist.probabilityDistribution.PirsonDistribution");
            names.put("Трёхпараметрическое гамма распределение", "ru.hydrologist.probabilityDistribution.ThreeParametrGammaDistribution");

            for (Map.Entry<String, String> name : names.entrySet()) {
                AnalystRangable analystFactory = new AnalystRange(empiricRange, name.getValue());
                Assert.assertEquals(name.getKey(), analystFactory.getDistributionName());
            }
        }
    }

    //@Test(timeOut = timeout)
    public void getKrickiyMenkelAnalystRange() throws InterpolatorException {
        testGetRange("ru.hydrologist.probabilityDistribution.KrickyMenkelDistribution");
    }

    //@Test(timeOut = timeout)
    public void getPirsonAnalystRange() throws InterpolatorException {
        testGetRange("ru.hydrologist.probabilityDistribution.PirsonDistribution");
    }

    //@Test(timeOut = timeout)
    public void getThreeParametrGammaAnalystRange() throws InterpolatorException {
        testGetRange("ru.hydrologist.probabilityDistribution.ThreeParametrGammaDistribution");
    }



    private void testGetRange(String distributionType) throws InterpolatorException {
        for (Map<String, ArrayList<Double>> dataSet : EmpiricDataSet) {
            EmpiricRange empiricRange = new EmpiricRange(dataSet.get("range"));
            AnalystRangable analystFactory = new AnalystRange(empiricRange, distributionType);

            try {
                ProbabilityDistribution className = (ProbabilityDistribution) Class.forName(distributionType).newInstance();
                Map<Double, Double> ultimateTruth = className.getRangeDistribution(dataSet.get("Cv").get(0), dataSet.get("CsCv").get(0));
                Map<Double, Double> result = analystFactory.getAnalystRange();
                check(ultimateTruth, result);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void check(Map<Double, Double> ultimateTruth, Map<Double, Double> result){
        for (Map.Entry<Double, Double> entry : result.entrySet()) {
            if (entry.getValue() - ultimateTruth.get(entry.getKey()) > accuracy || entry.getValue() - ultimateTruth.get(entry.getKey()) < -accuracy) {
                log.error("Полученное значение: " + entry.getValue() + "Фактическое значение: " + ultimateTruth.get(entry.getKey()) + "Ключ: " + entry.getKey());
                Assert.fail();
            }
        }
    }
}
