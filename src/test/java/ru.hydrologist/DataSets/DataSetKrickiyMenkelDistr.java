package ru.hydrologist.DataSets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedorovskiy on 08.03.2017.
 */
public class DataSetKrickiyMenkelDistr {
    private static DataSetKrickiyMenkelDistr instance;
    private ArrayList<Map<String, Map<Double, Double>>> DataSet = new ArrayList<Map<String, Map<Double, Double>>>();
    private Double[] PValues = {0.1, 0.33, 0.5,	1.0, 2.0, 5.0, 10.0, 20.0, 25.0, 30.0, 40.0, 50.0, 60.0, 70.0, 75.0, 80.0, 90.0, 95.0, 97.0, 99.0, 99.5, 99.7, 99.9};

    //Делаем этот класс синглтоном
    public static DataSetKrickiyMenkelDistr getInstance(){
        if(instance == null){
            instance = new DataSetKrickiyMenkelDistr();
        }

        return instance;
    }

    private DataSetKrickiyMenkelDistr(){
        int len_P = PValues.length;

        Map<Double, Double> map = new HashMap<Double, Double>();
        Map<String, Map<Double, Double>> mapMap = new HashMap<String, Map<Double, Double>>();

        for(int i=0; i<rowDataSet.length; i++){
            for(int k=0; k<len_P; k++){
                 map.put(PValues[k], rowDataSet[i][0][k]);
            }
            mapMap.put("Distribution", map);

            map = new HashMap<Double, Double>();
            map.put(0.0, rowDataSet[i][1][0]);
            mapMap.put("Cv", map);

            map = new HashMap<Double, Double>();
            map.put(0.0, rowDataSet[i][2][0]);
            mapMap.put("CsCv", map);

            map = new HashMap<Double, Double>();
            map.put(0.0, rowDataSet[i][3][0]);
            mapMap.put("P", map);

            map = new HashMap<Double, Double>();
            map.put(0.0, rowDataSet[i][4][0]);
            mapMap.put("DistributionForProbability", map);

            this.DataSet.add(mapMap);

            map = new HashMap<Double, Double>();
            mapMap = new HashMap<String, Map<Double, Double>>();
        }
    }

    //Возвращаем колелкцию тестовых наборов данных
    public ArrayList<Map<String, Map<Double, Double>>> getDataSet(){
        return DataSet;
    }

    Double[][][] rowDataSet = {
           //Первый набор данных
            {
                    //Распределение вероятностей
                    {3.698173012, 3.452725301, 3.341816145, 3.124543614, 2.866361928,
                            2.500907711, 2.144544337, 1.680908434, 1.515454217, 1.376363373, 1.1, 0.83545494,
                            0.619091566, 0.419091566, 0.320000723, 0.250000723, 0.097273253, 0.043636627,
                            0.02272747, 0.000909157, 0.0, 0.0, 0.0},
                    //Cv
                    {0.790908433723779},
                    //CsCv
                    {0.206324381676423},
                    //Целевая вероятность
                    {0.4},
                    //Целевая ордината распредедения
                    {3.4070568249129}
            },

            //Второй набор данных
            {
                    //Распределение вероятностей
                    {3.698173012, 3.452725301, 3.341816145, 3.124543614, 2.866361928,
                            2.500907711, 2.144544337, 1.680908434, 1.515454217, 1.376363373, 1.1, 0.83545494,
                            0.619091566, 0.419091566, 0.320000723, 0.250000723, 0.097273253, 0.043636627,
                            0.02272747, 0.000909157, 0.0, 0.0, 0.0},
                    //Cv
                    {0.790908433723779},
                    //CsCv
                    {0.206324381676423},
                    //Целевая вероятность
                    {0.4},
                    //Целевая ордината распредедения
                    {3.4070568249129}
            }
    };
}
