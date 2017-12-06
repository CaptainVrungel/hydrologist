package ru.hydrologist.DataSets;

import ru.hydrologist.utils.DataConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Наборы данных для тестирования ряда наблюдений
 */
public class DataSetRange {

    private ArrayList<Map<String, ArrayList<Double>>> DataSet = new ArrayList<Map<String, ArrayList<Double>>>();   //Коллеция тестовых наборов данных

    //Значения для первого набора данных
    private Double[] range_1 = {	455.0, 343.0, 291.0, 82.0, 67.0, 153.0, 155.0, 9.0, 64.0, 320.0, 396.0, 412.0,
            106.0, 46.0, 22.0, 416.0, 110.0, 357.0, 383.0, 379.0, 0.0 };

    private Double[] rangedRange_1 = {	455.00, 416.00,	412.00,	396.00, 383.00,	379.00, 357.00,	343.00,	320.00,	291.00,	155.00,	153.00,
            110.00,	106.00,	82.00,	67.00,	64.00,	46.00,  22.00,	9.00, 0.00 };

    private Double[] K_1 = {	2.09264126, 1.91327201, 1.89487516, 1.82128778, 1.76149803,	1.74310118,	1.64191853,	1.57752957,
            1.47174770,	1.33837057,	0.71287779,	0.70367937, 0.50591327,	0.48751643,	0.37713535,	0.30814717,
            0.29434954,	0.21156373,	0.10118265,	0.04139290,	0.00000000 };

    private Double[] probabilitys_1  = {	0.045454545454545456, 0.09090909090909091, 0.13636363636363635, 0.18181818181818182, 0.22727272727272727, 0.2727272727272727,
            0.3181818181818182, 0.36363636363636365, 0.4090909090909091, 0.45454545454545453, 0.50000000, 0.5454545454545454,
            0.5909090909090909, 0.6363636363636364, 0.6818181818181818, 0.7272727272727273, 0.7727272727272727, 0.8181818181818182,
            0.8636363636363636,	0.9090909090909091,	0.9545454545454546 };

    private Double[] OffsetCv_1 = { 0.745250501873731 };
    private Double[] OffsetCs_1 = { 0.0618626201765685 };
    private Double[] OffsetCsCv_1 = { 0.0830091627191551 };
    private Double[] OffsetR_1 = { 0.252013583005763 };
    private Double[] R_1 = { 0.406942872968657 };
    private Double[] Cv_1 = { 0.792174805037874 };
    private Double[] Cs_1 = { 0.163235113229675 };
    private Double[] CsCv_1 = { 0.206059460855828 };


    //Значения для второго набора данных
    private Double[] range_2 = {	31.00, 23.00, 54.00, 1.00, 98.00, 65.00, 2.00, 53.00, 4.00, 78.00, 32.00,
                                    52.00, 76.00, 99.00, 23.00, 6.00, 76.00, 88.00, 26.22, 92.00, 7.00 };

    private Double[] rangedRange_2 = {	99.00, 98.00, 92.00, 88.00, 78.00, 76.00, 76.00, 65.00, 54.00, 53.00,
                                        52.00, 32.00, 31.00, 26.22, 23.00, 23.00, 7.00, 6.00, 4.00, 2.00, 1.00 };

    private Double[] K_2 = {	2.10804891, 2.08675549, 1.95899495, 1.87382126, 1.66088702, 1.61830018, 1.61830018, 1.38407252,
                                1.14984486, 1.12855144, 1.10725802, 0.68138955, 0.66009612, 0.55831356, 0.48974874,
                                0.48974874, 0.14905396, 0.12776054, 0.08517369, 0.04258685, 0.02129342 };

    private Double[] probabilitys_2  = {	0.045454545454545456, 0.09090909090909091, 0.13636363636363635, 0.18181818181818182, 0.22727272727272727, 0.2727272727272727,
            0.3181818181818182, 0.36363636363636365, 0.4090909090909091, 0.45454545454545453, 0.50000000, 0.5454545454545454,
            0.5909090909090909, 0.6363636363636364, 0.6818181818181818, 0.7272727272727273, 0.7727272727272727, 0.8181818181818182,
            0.8636363636363636,	0.9090909090909091,	0.9545454545454546 };

    private Double[] OffsetCv_2 = { 0.728717126056499 };
    private Double[] OffsetCs_2 = { 0.104813678981121 };
    private Double[] OffsetCsCv_2 = { 0.143833149013977 };
    private Double[] OffsetR_2 = { -0.310729534421149 };
    private Double[] R_2 = { -0.310685595893812 };
    private Double[] Cv_2 = { 0.744193243664237 };
    private Double[] Cs_2 = { 0.200828839173522 };
    private Double[] CsCv_2 = { 0.269861142765401 };

    //Значения для третьего набора данных
    private Double[] range_3 = { 17.52, 176.01, 235.31, 193.53, 239.89, 263.88, 245.55, 101.62, 9.43, 186.25, 204.31, 8.36,
            82.48, 214.82, 263.34, 129.38, 105.39, 58.22, 181.4, 225.61, 204.04, 181.94, 30.73, 132.88, 53.64, 191.11,
            176.28, 47.17, 107.01, 180.32, 231.00, 1.62, 67.12, 67.65, 248.52, 202.43, 245.82, 259.03, 159.3, 91.91, 96.23, 38.01 };

    private Double[] rangedRange_3 = {	263.88, 263.34, 259.03, 248.52, 245.82, 245.55, 239.89, 235.31, 231.00, 225.61, 214.82, 204.31,
            204.04, 202.43, 193.53, 191.11, 186.25, 181.94, 181.4, 180.32, 176.28, 176.01, 159.3, 132.88, 129.38, 107.01, 105.39, 101.62,
            96.23, 91.91, 82.48, 67.65, 67.12, 58.22, 53.64, 47.17, 38.01, 30.73, 17.52, 9.43, 8.36, 1.62 };

    private Double[] K_3 = {	1.80033333, 1.79664916, 1.76724398, 1.69553903, 1.67711816, 1.67527607, 1.63666046, 1.60541320, 1.57600803,
            1.53923451, 1.46561924, 1.39391429, 1.39207220, 1.38108790, 1.32036725, 1.30385669, 1.27069912, 1.24129394, 1.23760977, 1.23024142,
            1.20267834, 1.20083625, 1.08683151, 0.90657986, 0.88270095, 0.73008060, 0.71902808, 0.69330708, 0.65653356, 0.62706017, 0.56272356,
            0.46154521, 0.45792926,0.39720860, 0.36596135, 0.32181948, 0.25932496, 0.20965683, 0.11953100, 0.06433660, 0.05703648, 0.01105252 };

    private Double[] probabilitys_3  = {	0.02325581395348840, 0.04651162790697670, 0.06976744186046510, 0.09302325581395350, 0.11627906976744200,
            0.13953488372093000, 0.16279069767441900, 0.18604651162790700, 0.20930232558139500, 0.23255813953488400,  0.2558139534883721, 0.27906976744186000,
            0.30232558139534900, 0.32558139534883700, 0.34883720930232600, 0.37209302325581395, 0.39534883720930200, 0.41860465116279100, 0.44186046511627900,
            0.46511627906976700, 0.4883720930232558, 0.51162790697674400, 0.53488372093023300, 0.55813953488372100, 0.58139534883720900, 0.60465116279069800,
            0.62790697674418600, 0.65116279069767400, 0.67441860465116300, 0.69767441860465100, 0.72093023255813900, 0.74418604651162800, 0.76744186046511600,
            0.79069767441860500, 0.81395348837209300, 0.83720930232558100, 0.86046511627907000, 0.88372093023255800, 0.90697674418604600, 0.93023255813953500,
            0.95348837209302300, 0.97674418604651200 };





    private Double[] OffsetCv_3 = { 0.564587997382551 };
    private Double[] OffsetCs_3 = { -0.268940379064211 };
    private Double[] OffsetCsCv_3 = { -0.476348027784911 };
    private Double[] OffsetR_3 = { 0.257333353041169 };
    private Double[] R_3 = { 0.326288883639585 };
    private Double[] Cv_3 = { 0.574277204236927 };
    private Double[] Cs_3 = { -0.142074083278185 };
    private Double[] CsCv_3 = { -0.247396348366233 };

    //Значения для четвертого набора данных
    private Double[] range_4 = { 260.11, 254.72, 205.12, 202.7, 190.57, 183.29, 180.05, 128.03, 123.18, 146.88, 139.56, 139.37, 147.18, 134.12, 147.08 };

    private Double[] rangedRange_4 = {	260.11, 254.72, 205.12, 202.70, 190.57, 183.29, 180.05, 147.18, 147.08, 146.88, 139.56, 139.37, 134.12, 128.03, 123.18 };

    private Double[] K_4 = {	1.51111946, 1.479806039, 1.191652853, 1.177593766, 1.107124045, 1.064830594, 1.046007684, 0.855048103, 0.854467149,
            0.853305241, 0.810779408, 0.809675595, 0.77917551, 0.743795411, 0.715619142 };

    private Double[] probabilitys_4  = {	0.0625, 0.125, 0.1875, 0.25, 0.3125, 0.375, 0.4375, 0.5, 0.5625, 0.625, 0.6875, 0.75, 0.8125, 0.875, 0.9375 };

    private Double[] OffsetCv_4 = { 0.254745996552209 };
    private Double[] OffsetCs_4 = { 0.920730403096018 };
    private Double[] OffsetCsCv_4 = { 3.61430764588019 };
    private Double[] OffsetR_4 = { 0.889515217444506 };
    private Double[] R_4 = { 1.60814458492125 };
    private Double[] Cv_4 = { 0.242571614421189 };
    private Double[] Cs_4 = { 1.40036981456847 };
    private Double[] CsCv_4 = { 5.77301601388915 };

    //Значения для пятого набора данных
    private Double[] range_5 = { 32.2, 10.3, -4.2, 2.8, 0.0, 0.0, 8.5  };

    private Double[] rangedRange_5 = {	32.2, 10.3, 8.5, 2.8, 0.0, 0.0, -4.2 };

    private Double[] K_5 = {	4.544354839, 1.453629032, 1.199596774, 0.39516129, 0.0, 0.0, -0.592741935 };

    private Double[] probabilitys_5  = {	0.125, 0.25, 0.375, 0.5, 0.625, 0.75, 0.875 };

    private Double[] OffsetCv_5 = { 1.71807674118638 };
    private Double[] OffsetCs_5 = { 1.76517500334743 };
    private Double[] OffsetCsCv_5 = { 1.02741336346159 };
    private Double[] OffsetR_5 = { 0.42692875671319 };
    private Double[] R_5 = { 1.176749180008055 };
    private Double[] Cv_5 = { 2.48897086958286 };
    private Double[] Cs_5 = { 5.26994657524538 };
    private Double[] CsCv_5 = { 2.11731950729041 };

    //Формируем наборы данных
    public DataSetRange(){
        Map<String, ArrayList<Double>> DataSet1 = new HashMap<String, ArrayList<Double>>();
        Map<String, ArrayList<Double>> DataSet2 = new HashMap<String, ArrayList<Double>>();
        Map<String, ArrayList<Double>> DataSet3 = new HashMap<String, ArrayList<Double>>();
        Map<String, ArrayList<Double>> DataSet4 = new HashMap<String, ArrayList<Double>>();
        Map<String, ArrayList<Double>> DataSet5 = new HashMap<String, ArrayList<Double>>();

        DataSet1.put("range",           toArrayList(range_1));
        DataSet1.put("rangedRange",     toArrayList(rangedRange_1));
        DataSet1.put("K",               toArrayList(K_1));
        DataSet1.put("probabilitys",    toArrayList(probabilitys_1));
        DataSet1.put("OffsetCv",        toArrayList(OffsetCv_1));
        DataSet1.put("OffsetCs",        toArrayList(OffsetCs_1));
        DataSet1.put("OffsetCsCv",      toArrayList(OffsetCsCv_1));
        DataSet1.put("OffsetR",         toArrayList(OffsetR_1));
        DataSet1.put("R",               toArrayList(R_1));
        DataSet1.put("Cv",              toArrayList(Cv_1));
        DataSet1.put("Cs",              toArrayList(Cs_1));
        DataSet1.put("CsCv",            toArrayList(CsCv_1));

        DataSet2.put("range",           toArrayList(range_2));
        DataSet2.put("rangedRange",     toArrayList(rangedRange_2));
        DataSet2.put("K",               toArrayList(K_2));
        DataSet2.put("probabilitys",    toArrayList(probabilitys_2));
        DataSet2.put("OffsetCv",        toArrayList(OffsetCv_2));
        DataSet2.put("OffsetCs",        toArrayList(OffsetCs_2));
        DataSet2.put("OffsetCsCv",      toArrayList(OffsetCsCv_2));
        DataSet2.put("OffsetR",         toArrayList(OffsetR_2));
        DataSet2.put("R",               toArrayList(R_2));
        DataSet2.put("Cv",              toArrayList(Cv_2));
        DataSet2.put("Cs",              toArrayList(Cs_2));
        DataSet2.put("CsCv",            toArrayList(CsCv_2));

        DataSet3.put("range",           toArrayList(range_3));
        DataSet3.put("rangedRange",     toArrayList(rangedRange_3));
        DataSet3.put("K",               toArrayList(K_3));
        DataSet3.put("probabilitys",    toArrayList(probabilitys_3));
        DataSet3.put("OffsetCv",        toArrayList(OffsetCv_3));
        DataSet3.put("OffsetCs",        toArrayList(OffsetCs_3));
        DataSet3.put("OffsetCsCv",      toArrayList(OffsetCsCv_3));
        DataSet3.put("OffsetR",         toArrayList(OffsetR_3));
        DataSet3.put("R",               toArrayList(R_3));
        DataSet3.put("Cv",              toArrayList(Cv_3));
        DataSet3.put("Cs",              toArrayList(Cs_3));
        DataSet3.put("CsCv",            toArrayList(CsCv_3));

        DataSet4.put("range",           toArrayList(range_4));
        DataSet4.put("rangedRange",     toArrayList(rangedRange_4));
        DataSet4.put("K",               toArrayList(K_4));
        DataSet4.put("probabilitys",    toArrayList(probabilitys_4));
        DataSet4.put("OffsetCv",        toArrayList(OffsetCv_4));
        DataSet4.put("OffsetCs",        toArrayList(OffsetCs_4));
        DataSet4.put("OffsetCsCv",      toArrayList(OffsetCsCv_4));
        DataSet4.put("OffsetR",         toArrayList(OffsetR_4));
        DataSet4.put("R",               toArrayList(R_4));
        DataSet4.put("Cv",              toArrayList(Cv_4));
        DataSet4.put("Cs",              toArrayList(Cs_4));
        DataSet4.put("CsCv",            toArrayList(CsCv_4));

        DataSet5.put("range",           toArrayList(range_5));
        DataSet5.put("rangedRange",     toArrayList(rangedRange_5));
        DataSet5.put("K",               toArrayList(K_5));
        DataSet5.put("probabilitys",    toArrayList(probabilitys_5));
        DataSet5.put("OffsetCv",        toArrayList(OffsetCv_5));
        DataSet5.put("OffsetCs",        toArrayList(OffsetCs_5));
        DataSet5.put("OffsetCsCv",      toArrayList(OffsetCsCv_5));
        DataSet5.put("OffsetR",         toArrayList(OffsetR_5));
        DataSet5.put("R",               toArrayList(R_5));
        DataSet5.put("Cv",              toArrayList(Cv_5));
        DataSet5.put("Cs",              toArrayList(Cs_5));
        DataSet5.put("CsCv",            toArrayList(CsCv_5));

        DataSet.add(DataSet1);
        DataSet.add(DataSet2);
        DataSet.add(DataSet3);
        DataSet.add(DataSet4);
        DataSet.add(DataSet5);
    }

    //Превращаем массив double в коллекцию ArrayList
    private ArrayList<Double> toArrayList(Double[] dbl){
        ArrayList<Double> arr = DataConverter.toArrayList(dbl);
        return arr;
    }

    //Возвращаем колелкцию тестовых наборов данных
    public ArrayList<Map<String, ArrayList<Double>>> getDataSet(){
        return DataSet;
    }

}
