package ru.hydrologist.ranges;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.utils.Interpolator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fedorovskiy on 03.03.2017.
 */
public class TestInterpolator {

    public Logger log = LogManager.getLogger(TestInterpolator.class);                  //Объект для логирования
    private final int timeout = 3000;                                            //Таймаут
    private double accuracy = 0.000000001;                                        //Точность вычислений

    @Test
    public void testFindBetweenWhich(){
        double[] res = new double[2];
        res[0] = 10.0;
        res[1] = 12.0;

        List<Double> arr = new ArrayList<Double>();
        arr.add(8.0);
        arr.add(10.0);
        arr.add(12.0);
        arr.add(14.0);

        if(res[0] != Interpolator.findBetweenWhich(11.3, arr)[0] || res[1] != Interpolator.findBetweenWhich(11.3, arr)[1]){
            System.out.println("Target: " + res[0] + " " + res[1] + " Result: " + Interpolator.findBetweenWhich(12.3, arr)[0] + " " + Interpolator.findBetweenWhich(12.3, arr)[1]);
            Assert.fail();
        }
    }

    @Test
    public void testInterpolation(){
        double res1 = 11;
        if(res1 != Interpolator.interpolation(4.0, 3.0, 5.0, 10.0, 12.0)){
            System.out.println("Target: " + res1 + " " + Interpolator.interpolation(4.0, 3.0, 5.0, 10.0, 12.0));
            Assert.fail();
        }
        double res2 = 9;
        if(res2 != Interpolator.interpolation(4.0, 3.0, 5.0, 10.0, 8.0)){
            System.out.println("Target: " + res2 + " " + Interpolator.interpolation(4.0, 3.0, 5.0, 10.0, 12.0));
            Assert.fail();
        }
    }

    //Проверяем двойную интерполяцию
    @Test
    public void testDoubleInterpolation() throws InterpolatorException {

        Double[] index1 = {13.0, 15.0};    Double[] index2 = {13.0, 15.0};        Double targetIndex = 14.0;
        Double[] coeff = {2.0, 3.0};        Double targetCoeff = 2.5;
        Double[] value1 = {6.0, 7.0};      Double[] value2 = {8.0, 9.0};

        Double ultimateTruth = 7.5;

        Double result = Interpolator.doubleInterpolation(index1, index2, targetIndex, coeff, targetCoeff, value1, value2);

        if(result - ultimateTruth > accuracy || result - ultimateTruth < -accuracy){
            log.error("Полученное значение: " + result + "Фактическое значение: " + ultimateTruth);
            Assert.fail();
        }
    }

    //Проверяем двойную интерполяцию рядов
    @Test
    public void rangeDoubleInterpolation() throws InterpolatorException {
        Double[] index1 = {13.0, 15.0};    Double[] index2 = {13.0, 15.0};        Double targetIndex = 14.0;
        Double[] coeff = {2.0, 3.0};        Double targetCoeff = 2.5;
        Double[][] value1 = {{6.0, 7.0},{2.0, 4.0},{6.0, 7.0},{1.2, 3.3}};      Double[][] value2 = {{8.0, 9.0},{3.0, 5.0},{8.0, 9.0},{5.6, 9.1}};

        Double[] ultimateTruth = {7.5, 3.5, 7.5, 4.8};

        Double[] result = Interpolator.rangeDoubleInterpolation(index1, index2, targetIndex, coeff, targetCoeff, value1, value2);

        for(int i = 0; i<result.length; i++){
            if(result[i] - ultimateTruth[i] > accuracy || result[i] - ultimateTruth[i] < -accuracy){
                log.error("Полученное значение: " + result[i] + "Фактическое значение: " + ultimateTruth[i]);


                Assert.fail();
            }
        }

    }

    //Проверяем исключение в интерполяторе, если ему передать массив больше чем из двух элементов
    @Test(expectedExceptions = InterpolatorException.class)
    public void testDoubleInterpolationException() throws InterpolatorException {
        Double[] index1 = {13.0, 15.0, 16.0};    Double[] index2 = {13.0, 15.0};        Double targetIndex = 14.0;
        Double[] coeff = {2.0, 3.0};        Double targetCoeff = 2.5;
        Double[] value1 = {6.0, 7.0};      Double[] value2 = {8.0, 9.0};

        Double result = Interpolator.doubleInterpolation(index1, index2, targetIndex, coeff, targetCoeff, value1, value2);
    }
    //Проверяем интерполяцию распределений, пришедших в виде Map
    @Test
    public void distributionMapDoubleInterpolation() throws InterpolatorException {
        Double[] index1 = {13.0, 15.0};    Double[] index2 = {13.0, 15.0};        Double targetIndex = 14.0;
        Double[] coeff = {2.0, 3.0};        Double targetCoeff = 2.5;
        Map<Double, Double> value11 = new HashMap<Double, Double>();
        Map<Double, Double> value12 = new HashMap<Double, Double>();
        Map<Double, Double> value21 = new HashMap<Double, Double>();
        Map<Double, Double> value22 = new HashMap<Double, Double>();

        value11.put(0.1, 6.0);      value11.put(0.3, 2.0);
        value12.put(0.1, 7.0);      value12.put(0.3, 4.0);
        value21.put(0.1, 8.0);      value21.put(0.3, 3.0);
        value22.put(0.1, 9.0);      value22.put(0.3, 5.0);

        Map<Double, Double> ultimateTruth = new HashMap<Double, Double>();
        ultimateTruth.put(0.1, 7.5);    ultimateTruth.put(0.3, 3.5);
        Map<Double, Double> result = Interpolator.distributionMapDoubleInterpolation(index1, index2, targetIndex, coeff, targetCoeff, value11, value12, value21, value22);

        if(result.get(0.1) - ultimateTruth.get(0.1) > accuracy || result.get(0.1) - ultimateTruth.get(0.1) < -accuracy){
                log.error("Полученное значение: " + result.get(0.1) + "Фактическое значение: " + ultimateTruth.get(0.1));
                Assert.fail();
        }

        if(result.get(0.3) - ultimateTruth.get(0.3) > accuracy || result.get(0.3) - ultimateTruth.get(0.3) < -accuracy){
            log.error("Полученное значение: " + result.get(0.1) + "Фактическое значение: " + ultimateTruth.get(0.1));
            Assert.fail();
       }

    }

    @Test(expectedExceptions = InterpolatorException.class)
    public void distributionMapDoubleInterpolationException_1() throws InterpolatorException {
        Double[] index1 = {13.0, 15.0};    Double[] index2 = {13.0, 15.0};        Double targetIndex = 14.0;
        Double[] coeff = {2.0, 3.0};        Double targetCoeff = 2.5;
        Map<Double, Double> value11 = new HashMap<Double, Double>();
        Map<Double, Double> value12 = new HashMap<Double, Double>();
        Map<Double, Double> value21 = new HashMap<Double, Double>();
        Map<Double, Double> value22 = new HashMap<Double, Double>();

        value11.put(1.1, 2.3);
        value12.put(1.2, 4.1);
        value12.put(1.3, 5.1);
        value21.put(1.4, 2.3);
        value22.put(1.5, 4.1);
        value22.put(1.6, 5.1);
        value22.put(1.7, 3.1);

        Map<Double, Double> result = Interpolator.distributionMapDoubleInterpolation(index1, index2, targetIndex, coeff, targetCoeff, value11, value12, value21, value22);
    }

    @Test
    public void findBetweenWhichRounded(){
        double[] res = new double[2];
        res[0] = 10.0;
        res[1] = 12.0;

        List<Double> arr = new ArrayList<Double>();
        arr.add(8.0);
        arr.add(10.0);
        arr.add(12.0);
        arr.add(14.0);

        if(res[0] != Interpolator.findBetweenWhichRounded(11.3, arr, 8)[0] || res[1] != Interpolator.findBetweenWhichRounded(11.3, arr, 8)[1]){
            System.out.println("Target: " + res[0] + " " + res[1] + " Result: " + Interpolator.findBetweenWhichRounded(12.3, arr, 8)[0] + " " + Interpolator.findBetweenWhichRounded(12.3, arr, 8)[1]);
            Assert.fail();
        }
    }

    @Test
    public void distributionMapInterpolation(){
        Double index1 = 13.0;    Double index2 = 15.0;        Double targetIndex = 14.0;

        Map<Double, Double> minVal = new HashMap<Double, Double>();
        Map<Double, Double> maxVal = new HashMap<Double, Double>();

        minVal.put(0.3, 6.0);  minVal.put(0.5, 8.0);
        maxVal.put(0.3, 7.0);  maxVal.put(0.5, 9.0);

        Map<Double, Double> ultimateTruth = new HashMap<Double, Double>();
        ultimateTruth.put(0.3, 6.5);    ultimateTruth.put(0.5, 8.5);
        Map<Double, Double> result = Interpolator.distributionMapInterpolation(targetIndex, index1, index2, minVal, maxVal);

        for (Map.Entry<Double, Double> entry : ultimateTruth.entrySet()) {
            if(result.get(entry.getKey()) - entry.getValue() > accuracy || result.get(entry.getKey()) - entry.getValue() < -accuracy){
                log.error("Полученное значение: " + result.get(entry.getKey()) + "Фактическое значение: " + entry.getValue());
                Assert.fail();
            }
        }
    }

}
