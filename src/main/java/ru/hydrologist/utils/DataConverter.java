package ru.hydrologist.utils;

import java.util.*;

public class DataConverter {

    public DataConverter(){

    }

    public static Double[] toArrayDouble(Map<Double, Double> map){
        ArrayList<Double> list = new ArrayList<Double>();
        Double[] result = {};
        int size = map.size();

        for (Map.Entry<Double, Double> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);

        return toArrayDouble(list);
    }


    //Превращаем коллекцию ArrayList в массив Double
    public static Double[] toArrayDouble(List<Double> list){

        int len = list.size();
        Double[] arr = new Double[len];

        for(int i=0; i<len; i++){
            arr[i] = list.get(i);
        }

        return arr;
    }

    //Превращаем массив double в коллекцию ArrayList
    public static ArrayList<Double> toArrayList(Double[] dbl){
        ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(dbl));
        return arr;
    }

    public static Map<Double, Double> DoubleToMap(Double[] arr, Double[] keys){
        int len = arr.length;
        Map<Double, Double> map = new HashMap<Double, Double>();

        for(int i=0; i<len; i++){
            map.put(keys[i], arr[i]);
        }

        return map;
    }

    public static Map ListToMap(List list, Double[] keys){
        Map<Double, Double> map = new HashMap<Double, Double>();

        for(int i=0; i<keys.length; i++){
            Double value = (Double) list.get(i);
            map.put(keys[i], value);
        }

        return map;
    }

    //Округление
    public static Double DoubleRound(double value, int rank){
        double d = value * Math.pow(10, rank) ;
        int i = (int)Math.round(d);
        double result = (double) i / Math.pow(10, rank);
        return result;
    }

    //Округление до трёх значащих цифр после запятой, иначе до целого
    public static Double smartHydrologicalRound(double value){
        int length = Integer.toString(Math.abs((int) value)).length();
        int rank;

        if(length >= 3){
            return (double) Math.round(value);
        }else{
            if(value < 1 && value > -1){
                rank = 3;
            }else{
                rank = Math.abs(length - 3);
            }
        }

        return DoubleRound(value, rank);
    }
    public static String smartHydrologicalRoundToString(double value){
        String valueString = smartHydrologicalRound(value).toString();
        String[] valueParts = valueString.split("[.]");

        int maximumStringLength;
        if(value >=0 ){
            maximumStringLength = 3;
        }else{
            maximumStringLength = 4;
        }

        if(valueParts[0].length() >= maximumStringLength){
            return valueParts[0];
        }

        return valueString;
    }

}
