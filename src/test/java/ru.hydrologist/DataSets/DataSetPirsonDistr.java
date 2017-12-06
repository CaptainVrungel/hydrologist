package ru.hydrologist.DataSets;

import ru.hydrologist.utils.DataConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedorovskiy on 08.03.2017.
 */
public class DataSetPirsonDistr {


    private ArrayList<Map<String, ArrayList<Double>>> DataSet = new ArrayList<Map<String, ArrayList<Double>>>();

    //Данные для первого набора
    Double[] Distribution_1 = {3.32848,	2.8932,	2.44792,	2.12056,	1.68528,
            1.3,	0.83736,	0.65736,	0.5,	0.22736,	-0.02264,	-0.28,
            -0.54264,	-0.68264,	-0.85,	-1.26,	-1.59472,	-1.81208,	-2.20944,	-2.86152};

    Double[] Cs_1 = {0.163183694};
    Double[] P_1 = {0.5};
    Double[] DistributionForProbability_1 = {2.780218507462694};

    public DataSetPirsonDistr(){
        Map<String, ArrayList<Double>> DataSet1 = new HashMap<String, ArrayList<Double>>();

        DataSet1.put("Distribution",                DataConverter.toArrayList(Distribution_1));
        DataSet1.put("Cs",                          DataConverter.toArrayList(Cs_1));
        DataSet1.put("P",                           DataConverter.toArrayList(P_1));
        DataSet1.put("DistributionForProbability",  DataConverter.toArrayList(DistributionForProbability_1));

        DataSet.add(DataSet1);
    }


    public ArrayList<Map<String, ArrayList<Double>>> getDataSet() {
        return DataSet;
    }
}
