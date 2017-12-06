package ru.hydrologist.DataSets;

//Нужно несколько датасетов, в которых CsCv между 2 и 4 и Cv от 0 до 2, чтоб была двойная интерполяция

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedorovskiy on 03.03.2017.
 */
public class DataSetThreeParamGammaDistr {

    private ArrayList<Map<String, ArrayList<Double>>> DataSet = new ArrayList<Map<String, ArrayList<Double>>>();   //Коллеция тестовых наборов данных


    //Данные для первого набора
    Double[] RangeDistribution_1 = {4.701046779, 4.253658596, 4.062611818, 3.953394337, 3.793394337, 3.504959376,
            3.356524415, 3.138089454, 2.730437013, 2.512002052, 2.146697169, 1.692174805, 1.515304883,
            1.366869922, 1.081565039, 0.840303857, 0.619651416, 0.420981699, 0.329216455, 0.244903447,
            0.097712392, 0.039521338, 0.020347558, 0.004860771};
    Double[] Cv_1 = {0.792174805};
    Double[] CsCv_1 = {0.206059460855828};
    Double[] P_1 = {1.0};
    Double[] DistributionForProbability_1 = {3.138089454};

    //Данные для второго набора
    Double[] RangeDistribution_2 = {4.523515002, 3.908191354, 3.894676353, 3.790257028, 3.630257028, 3.35141838,
            3.212579731, 3.003741082, 2.610483109, 2.40164446, 2.065128514, 1.644193244, 1.486515946, 1.347677297,
            1.091161351, 0.866693716, 0.660435743, 0.469922891, 0.379597094, 0.293364824, 0.132738932, 0.06111304,
            0.034742027, 0.010138743};
    Double[] Cv_2 = {0.744193243664237};
    Double[] CsCv_2 = {0.269861142765401};
    Double[] P_2 = {1.0};
    Double[] DistributionForProbability_2 = {3.00374108225986};

    //Данные для третьего набора
    Double[] RangeDistribution_3 = {3.81910286, 3.461964258, 3.267108817, 3.172253376, 3.037397935,
            2.805114774, 2.690259333, 2.515403892, 2.21083757, 2.048554408, 1.791415806, 1.486849484, 1.374566323,
            1.277138602, 1.09742772, 0.934687927, 0.782404766, 0.63034992, 0.552179428, 0.471751708, 0.293323987,
            0.184266164, 0.131436656, 0.064491501};
    Double[] Cv_3 = {0.574277204236927};
    Double[] CsCv_3 = {-0.247396348366233};
    Double[] P_3 = {1.0};
    Double[] DistributionForProbability_3 = {2.51540389228709};

    //Данные для четвертого набора
    Double[] RangeDistribution_4 = {3.090259763, 2.601458983, 2.389087238, 2.289287108, 2.156715494, 1.952858072, 1.861572265,
            1.733257812, 1.542171875, 1.445143229, 1.315343099, 1.17980013, 1.137028646, 1.092771484, 1.02, 0.96493138,
            0.91026849, 0.856605599, 0.82819987, 0.798794141, 0.727279818, 0.673022656, 0.64146836, 0.58606263};

    Double[] Cv_4 = {0.242571614421189};
    Double[] CsCv_4 = {5.77301601388915};
    Double[] P_4 = {1.0};
    Double[] DistributionForProbability_4 = {1.73325781191628};

    //Данные для пятого набора
    Double[] RangeDistribution_5 = {27.88832559, 20.03690473, 17.04527647, 15.46985405, 13.52403992, 10.83744238, 9.400791081,
            7.704139779, 5.221678185, 4.091397848, 2.721117511, 1.547764977, 1.226033027, 0.982982719, 0.6176755, 0.370742704,
            0.211005377, 0.106390937, 0.068083717, 0.040368664, 0.007826805, 0.001765361, 0.000812289, 0.000065922};
    Double[] Cv_5 = {2.48897086958286};
    Double[] CsCv_5 = {2.11731950729041};
    Double[] P_5 = {1.0};
    Double[] DistributionForProbability_5 = {7.70413977878498};


    public DataSetThreeParamGammaDistr(){
        Map<String, ArrayList<Double>> DataSet1 = new HashMap<String, ArrayList<Double>>();
        Map<String, ArrayList<Double>> DataSet2 = new HashMap<String, ArrayList<Double>>();
        Map<String, ArrayList<Double>> DataSet3 = new HashMap<String, ArrayList<Double>>();
        Map<String, ArrayList<Double>> DataSet4 = new HashMap<String, ArrayList<Double>>();
        Map<String, ArrayList<Double>> DataSet5 = new HashMap<String, ArrayList<Double>>();

        DataSet1.put("RangeDistribution",           toArrayList(RangeDistribution_1));
        DataSet1.put("Cv",                          toArrayList(Cv_1));
        DataSet1.put("CsCv",                        toArrayList(CsCv_1));
        DataSet1.put("P",                           toArrayList(P_1));
        DataSet1.put("DistributionForProbability",  toArrayList(DistributionForProbability_1));

        DataSet2.put("RangeDistribution",           toArrayList(RangeDistribution_2));
        DataSet2.put("Cv",                          toArrayList(Cv_2));
        DataSet2.put("CsCv",                        toArrayList(CsCv_2));
        DataSet2.put("P",                           toArrayList(P_2));
        DataSet2.put("DistributionForProbability",  toArrayList(DistributionForProbability_2));

        DataSet3.put("RangeDistribution",           toArrayList(RangeDistribution_3));
        DataSet3.put("Cv",                          toArrayList(Cv_3));
        DataSet3.put("CsCv",                        toArrayList(CsCv_3));
        DataSet3.put("P",                           toArrayList(P_3));
        DataSet3.put("DistributionForProbability",  toArrayList(DistributionForProbability_3));

        DataSet4.put("RangeDistribution",           toArrayList(RangeDistribution_4));
        DataSet4.put("Cv",                          toArrayList(Cv_4));
        DataSet4.put("CsCv",                        toArrayList(CsCv_4));
        DataSet4.put("P",                           toArrayList(P_4));
        DataSet4.put("DistributionForProbability",  toArrayList(DistributionForProbability_4));

        DataSet5.put("RangeDistribution",           toArrayList(RangeDistribution_5));
        DataSet5.put("Cv",                          toArrayList(Cv_5));
        DataSet5.put("CsCv",                        toArrayList(CsCv_5));
        DataSet5.put("P",                           toArrayList(P_5));
        DataSet5.put("DistributionForProbability",  toArrayList(DistributionForProbability_5));

       DataSet.add(DataSet1);
       DataSet.add(DataSet2);
       DataSet.add(DataSet3);
       DataSet.add(DataSet4);
       DataSet.add(DataSet5);
    }

    //Превращаем массив double в коллекцию ArrayList
    private ArrayList<Double> toArrayList(Double[] dbl){
        ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(dbl));
        return arr;
    }

    //Возвращаем колелкцию тестовых наборов данных
    public ArrayList<Map<String, ArrayList<Double>>> getDataSet(){
        return DataSet;
    }

}
