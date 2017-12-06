package ru.hydrologist.utils;

import ru.hydrologist.exceptions.InterpolatorException;

import java.util.*;

public class Interpolator {

    //Вычисляем между какими двумя значениями в ряду, находится заданное
    public static Double[] findBetweenWhich(Double value, List<Double> arr){
        Double[] result = new Double[2];
        ArrayList<Double> higher = new ArrayList<Double>();
        ArrayList<Double> lower = new ArrayList<Double>();
        int n = arr.size();
        for(int i=0; i<n; i++) {
            Double currVal = arr.get(i);
            if (value.equals(currVal) == true) {
                result[0] = result[1] = currVal;
                return result;
            } else {
                if (value < currVal) {
                    higher.add(currVal);
                }
                if (value > currVal) {
                    lower.add(currVal);
                }
                if (value == currVal) {
                    result[0] = result[1] = currVal;
                }

                if (higher.size() > 0) {
                    result[1] = Collections.min(higher);
                } else {
                    result[1] = Collections.max(lower);
                }
                if (lower.size() > 0) {
                    result[0] = Collections.max(lower);
                } else {
                    result[0] = Collections.min(higher);
                }
             }
        }
        return result;
    }

    //поиск между двух значений с округлением
    public static Double[] findBetweenWhichRounded( Double value, List<Double> arr, int rank){
        double val = DataConverter.DoubleRound(value, rank);
        List<Double> array = new ArrayList<Double>();

        for(int i = 0; i<arr.size(); i++){
            Double d  = DataConverter.DoubleRound(arr.get(i), rank);
            array.add(i, d);
        }
        return findBetweenWhich(val, array);
    }

    //Двухмерная интерполяция (между 4мя заданными значениями)
    public static double interpolation(double val, double min, double max, double minVal, double maxVal){
        double rezult;

        if(min == max || minVal == maxVal){
            rezult = minVal;
            return rezult;
        }else{
            rezult = (maxVal-minVal)*(val-min)/(max-min)+minVal;
            return rezult;
        }
    }


    //Двойная интерполяция
    public static Double doubleInterpolation(Double[] index1, Double[] index2, Double targetIndex,
                                             Double[] coeff, Double targetCoeff,
                                             Double[] value1, Double[] value2) throws InterpolatorException {

        if(index1.length != 2 || index2.length != 2 || coeff.length != 2 || value2.length != 2 || value1.length != 2){
            String msg = "Ошибка интерполяции. Все массивы должны иметь длину, равную двум. " + index1.length + " " + index2.length + " " + value1.length + " " + value2.length;
            throw new InterpolatorException(msg);
        }

        Double firstIteration = interpolation(targetIndex, index1[0], index1[1], value1[0], value1[1]);
        Double secondIteration = interpolation(targetIndex, index2[0], index2[1], value2[0], value2[1]);

        Double result = interpolation(targetCoeff, coeff[0], coeff[1], firstIteration, secondIteration);

        return result;
    }

    //Двойная интерполяция рядов
    public static Double[] rangeDoubleInterpolation(Double[] index1, Double[] index2, Double targetIndex,
                                                    Double[] coeff, Double targetCoeff,
                                                    Double[][] value1, Double[][] value2) throws InterpolatorException {

        if(value1.length != value2.length){
            String msg = "Ошибка интерполяции. При интерполяции радов, ряды должны иметь одинаковую длину.";
            throw new InterpolatorException(msg);
        }

        int len = value1.length;
        Double[] result = new Double[len];

            for(int i=0; i<len; i++){
                result[i] = doubleInterpolation(index1, index2, targetIndex, coeff, targetCoeff, value1[i], value2[i]);
            }

        return result;
    }

    //Интерполяция ряда распределения, если он пришёл в виде Map
    public static Map<Double, Double> distributionMapDoubleInterpolation(Double[] index1, Double[] index2, Double targetIndex,
                                                    Double[] coeff, Double targetCoeff,
                                                    Map<Double, Double> value11, Map<Double, Double> value12,
                                                    Map<Double, Double> value21, Map<Double, Double> value22) throws InterpolatorException {
        //Проверка на длину ряда
        if(value11.size() != value12.size() || value21.size() != value22.size() || value11.size() != value22.size()){
            throw  new InterpolatorException("Размеры интерполируемых рядов должны совпадать");
        }

        Map<Double, Double> resultMap = new HashMap<Double, Double>();

        for (Map.Entry<Double, Double> entry : value11.entrySet()) {
            Double firstIteration = interpolation(targetIndex, index1[0], index1[1], entry.getValue(), value12.get(entry.getKey()));
            Double secondIteration = interpolation(targetIndex, index2[0], index2[1], value21.get(entry.getKey()), value22.get(entry.getKey()));

            resultMap.put(entry.getKey(), interpolation(targetCoeff, coeff[0], coeff[1], firstIteration, secondIteration));
        }

        return resultMap;
    }

    //Интерполяция для MAP
    public static Map<Double, Double> distributionMapInterpolation(Double val, Double min, Double max, Map<Double, Double> minVal, Map<Double, Double> maxVal){
        Map<Double, Double> result = new HashMap<Double, Double>();

        for (Map.Entry<Double, Double> entry : minVal.entrySet()) {
            Double iteration = interpolation(val, min, max, entry.getValue(), maxVal.get(entry.getKey()));
            result.put(entry.getKey(), iteration);
        }

        return result;

    }

    //Находит между какими двумя ключами Map попало значение value
    public static Double[] findBetweenWhich(Double value, Map<Double, Double> map){
        List<Double> arr = new ArrayList<Double>();

        for (Map.Entry<Double, Double> entry : map.entrySet()) {
            arr.add(entry.getKey());
        }

        return findBetweenWhich(value, arr);
    }

}
