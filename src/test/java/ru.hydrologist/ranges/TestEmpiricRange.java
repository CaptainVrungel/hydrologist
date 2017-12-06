package ru.hydrologist.ranges;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.hydrologist.DataSets.DataSetRange;
import ru.hydrologist.utils.DataConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

//Тестирование на деление на ноль, на пустой ряд
//Когда-нибудь подумать про парараметризованное тестирование через Iterator[Object]

/**
 * Тесты для ряда наблюдений
 */
public class TestEmpiricRange {
    private final int timeout = 3000;                                            //Таймаут
    private double accuracy = 0.0000001;                                        //Точность вычислений
    private int accuracyRank = 6;
    private static ArrayList<Map<String, ArrayList<Double>>> DataSet;           //Набор тестовых значений.
    public Logger log = LogManager.getLogger(TestEmpiricRange.class);                  //Объект для логирования


    //Получаем наборы данных для тестирования
    @BeforeClass
    public static void getTestDataSet() {
        DataSet = new DataSetRange().getDataSet();
    }

    //Проверяем правильность сортировки ряда
    @Test(timeOut = timeout)
    public void testSortRange(){

        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            ArrayList<Double> ultimateTruth = dataSet.get("rangedRange");
            ArrayList<Double> result = testingRange.getRangedRange();

            if(ultimateTruth.size() != result.size()){
                log.error("Длина ряда на совпадает: " + ultimateTruth.size() + " != " +result.size());
                Assert.fail();
            }else{
                int resultRangeLength = result.size();
                for(int i=0; i<resultRangeLength; i++){
                    if(ultimateTruth.get(i) - result.get(i) > accuracy || ultimateTruth.get(i) - result.get(i) < -accuracy){
                        log.error("Итерация: " + i + "Полученное значение: " + result.get(i) + "Ожидаемое значение: " +  ultimateTruth.get(i)+ "Полученный ряд" + result + "Ожидаемый ряд" + ultimateTruth);
                        Assert.fail();
                    }
                }
            }
        }
    }

    //Проверяем правильность вычисления длины ряда
    @Test(timeOut = timeout)
    public void testCalcRangLength(){

        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            int ultimateTruth = dataSet.get("range").size();
            int result = testingRange.getRangeLength();

            if(result != ultimateTruth){
                log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }
        }
    }

    //Проверяем правильность вычисления среднего значения ряда
    @Test(timeOut = timeout)
    public void testCalcRangeAverage() {

        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            ArrayList<Double> range = dataSet.get("range");
            int len = range.size();
            double sum = 0;
            for(int i=0; i<len; i++){
                sum = sum + range.get(i);
            }

            double ultimateTruth = sum/range.size();
            double result = testingRange.getRangeAverage();

            if (result - ultimateTruth > accuracy || result - ultimateTruth < -accuracy) {
                log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }
        }
    }

    //Проверяем правильность вычисления коэффициента К
    @Test(timeOut = timeout)
    public void testCalcK(){
        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            ArrayList<Double> ultimateTruth = dataSet.get("K");
            ArrayList<Double> result = testingRange.getK();

            int length = testingRange.getRangeLength();

            if(result.size() != ultimateTruth.size()){
                log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }else{
                for(int i=0; i<length; i++){
                    if(ultimateTruth.get(i) - result.get(i) > accuracy || ultimateTruth.get(i) - result.get(i) < -accuracy){
                        log.error("Полученное значение: " + result.get(i) + "Фактическое значение: " + ultimateTruth.get(i));
                        Assert.fail();
                    }
                }
            }
        }
    }

    //Проверяем правильность вычисления вероятностей
    @Test(timeOut = timeout)
    public void testCalcProbabilities(){
        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            ArrayList<Double> ultimateTruth = dataSet.get("probabilitys");
            ArrayList<Double> result = testingRange.getProbabilities();

            int length = testingRange.getRangeLength();

            if(ultimateTruth.size() != result.size()){
                log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }else{
                for(int i=0; i<length; i++){
                    if(ultimateTruth.get(i) - result.get(i) > accuracy || ultimateTruth.get(i) - result.get(i) < -accuracy){
                        log.error("Полученное значение: " + result.get(i) + "Фактическое значение: " + ultimateTruth.get(i));
                        Assert.fail();
                    }
                }
            }

        }
    }

    //Проверяем правильность вычисления смещенного Cv
    @Test(timeOut = timeout)
    public void testCalcOffsetCv(){
        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            Double ultimateTruth = dataSet.get("OffsetCv").get(0);
            Double result = testingRange.getOffsetCv();

            if(ultimateTruth - result > accuracy || ultimateTruth - result < -accuracy){
                log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }
        }
    }

    //Проверяем правильность вычисления смещенного Cs
    @Test(timeOut = timeout)
    public void testCalcOffsetCs(){
        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            Double ultimateTruth = dataSet.get("OffsetCs").get(0);
            Double result = testingRange.getOffsetCs();

            if(ultimateTruth - result > accuracy || ultimateTruth - result < -accuracy){
                log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }
        }
    }

    //Проверяем правильность вычисления смещенного соотношения Cs и Cv
    @Test(timeOut = timeout)
    public void testCalcOffsetCsCv(){
        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            Double ultimateTruth = dataSet.get("OffsetCsCv").get(0);
            Double result = testingRange.getOffsetCsCv();

            if(ultimateTruth - result > accuracy || ultimateTruth - result < -accuracy){
                log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }
        }
    }

    //Проверяем правильность вычисления смещенного и не смещенного r
    @Test(timeOut = timeout)
    public void testCalcR(){
        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            ArrayList<Double> arr = dataSet.get("OffsetR");
            Double ultimateTruthOffsetR = arr.get(0);
            Double ultimateTruthR = dataSet.get("R").get(0);
            Double resultOffsetR = testingRange.getOffsetR();
            Double resultR = testingRange.getR();

            if(ultimateTruthOffsetR - resultOffsetR > accuracy || ultimateTruthOffsetR - resultOffsetR < -accuracy){
                log.error("Полученное значение: " + resultOffsetR + "Фактическое значение: " + ultimateTruthOffsetR);
                Assert.fail();
            }else {
                if(ultimateTruthR - resultR > accuracy || ultimateTruthR - resultR < -accuracy){
                    log.error("Полученное значение: " + resultR + "Фактическое значение: " + ultimateTruthR);
                    Assert.fail();
                }
            }
        }
    }

    //Проверяем правильность вычисления несмещенного соотношения Cs и Cv
    @Test(timeOut = timeout)
    public void testCalcCvAndCs(){
        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            Double ultimateTruthCv = dataSet.get("Cv").get(0);
            Double ultimateTruthCs = dataSet.get("Cs").get(0);
            Double resultCv = testingRange.getCv();
            Double resultCs = testingRange.getCs();

            if(ultimateTruthCv - resultCv > accuracy || ultimateTruthCv - resultCv < -accuracy){
                log.error("Полученное значение: " + resultCv + "Фактическое значение: " + ultimateTruthCv);
                Assert.fail();
            }else if(ultimateTruthCs - resultCs > accuracy || ultimateTruthCs - resultCs < -accuracy){
                log.error("Полученное значение: " + resultCs + "Фактическое значение: " + ultimateTruthCs);
                Assert.fail();
            }
        }
    }

    //Проверяем правильность вычисления кожффициентов a
    @Test(timeOut = timeout)
    public void testCalcA(){
        try {
            EmpiricRange testingRange = new EmpiricRange(DataSet.get(0).get("range"));

            double[] ultimateTruth = {0.0, -0.0122861529176039, 2.80660915091393, 1.11057537625843, -22.7983930141731, -0.271150752516869, 43.8286675152731};
            double[] result = testingRange.calcA(1.60814458492125, 3.61430764588019);

            for (int i = 0; i < ultimateTruth.length; i++) {
                if (ultimateTruth[i] - result[i] > accuracy || ultimateTruth[i] - result[i] < -accuracy) {
                    log.error("Итерация: " +i+ " Полученное значение: " + result[i] + " Фактическое значение: " + ultimateTruth[i]);
                    Assert.fail();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Проверяем правильность расчёта несмещенного соотнощения Cs к Cv
    @Test(timeOut = timeout)
    public void testCalcCsCv(){
        for( Map<String, ArrayList<Double>>dataSet : DataSet){
            EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));

            Double ultimateTruth = dataSet.get("CsCv").get(0);
            Double result = testingRange.getCsCv();

            if(ultimateTruth - result > accuracy || ultimateTruth - result < -accuracy){
                log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
                Assert.fail();
            }
        }
    }

    @Test(enabled=false)
    public void testGetRangeParams(){
        Double testCv = 0.792174805037874;
        Double testCs = 0.163235113229675;
        Double testCsCv = 0.206059460855828;
        Double testR = 0.406942872968657;

        EmpiricRange testingRange = new EmpiricRange(DataSet.get(0).get("range"));

        Hashtable<String, Double> res = testingRange.getRangeParams();

        if(testCv - res.get("Cv") < - accuracy || testCv - res.get("Cv") > accuracy ||
                testCs - res.get("Cs") < - accuracy || testCs - res.get("Cs") > accuracy ||
                testCsCv - res.get("CsCv") < - accuracy || testCsCv - res.get("CsCv") > accuracy ||
                testR - res.get("r") < - accuracy || testR - res.get("r") > accuracy
                ){
            System.out.println("Target (Cv, Cs, CsCv, r ) : " + "0.792174805037874, 0.163235113229675, 0.206059460855828, 0.406942872968657 " + "Result: " + res.get("Cv") + res.get("Cs")+ res.get("CsCv") + res.get("r"));
            Assert.fail();
        }
    }

    @Test(timeOut = timeout)
    public void testSetRange() {

    for (Map<String, ArrayList<Double>> dataSet : DataSet) {

        EmpiricRange testingRange = new EmpiricRange(dataSet.get("range"));
        Map<Double, Double> ultimateTruth = new HashMap<Double, Double>();
        int len = dataSet.get("rangedRange").size();
        for (int i = 0; i < len; i++) {
            ultimateTruth.put(DataConverter.DoubleRound(dataSet.get("probabilitys").get(i)*100, accuracyRank), dataSet.get("rangedRange").get(i));
        }

        Map<Double, Double> result = testingRange.getRange();

        for (Map.Entry<Double, Double> entry : ultimateTruth.entrySet()) {
            if (entry.getValue() - result.get(entry.getKey()) > accuracy || entry.getValue() - result.get(entry.getKey()) < -accuracy) {
                log.error("Итерация: " + entry.getKey() + " Полученное значение: " + result.get(entry.getKey()) + " Фактическое значение: " + entry.getValue());
                Assert.fail();
            }
        }


    }

    }
}
