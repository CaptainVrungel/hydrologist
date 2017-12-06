package ru.hydrologist.coefficients.distributions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedorovskiy on 08.03.2017.
 */
public class KrickyMenkelDistributionCoefficients {
    private static KrickyMenkelDistributionCoefficients instance;
    private Double[] PValues = {0.1, 0.33, 0.5,	1.0, 2.0, 5.0, 10.0, 20.0, 25.0, 30.0, 40.0, 50.0, 60.0, 70.0, 75.0, 80.0, 90.0, 95.0, 97.0, 99.0, 99.5, 99.7, 99.9};
    private Double[] CvValues = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.5};
    private Double[] CsCvValues = {1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 6.0};

    Map<Double, Map<Double, Map<Double, Double>>> Distribution = new HashMap<Double, Map<Double, Map<Double, Double>>>();

    //Делаем этот класс синглтоном
    public static KrickyMenkelDistributionCoefficients getInstance(){
        if(instance == null){
            instance = new KrickyMenkelDistributionCoefficients();
        }

        return instance;
    }

    //Возвращаем распределение
    public  Map<Double, Map<Double, Map<Double, Double>>> getDistribution(){
        if(this.Distribution.size() == 0){
            initDistribution();
        }
        return Distribution;
    }

    private void initDistribution(){
        int len_P = PValues.length;
        int len_Cv = CvValues.length;
        int len_CsCv = CsCvValues.length;

        Map<Double, Double> map = new HashMap<Double, Double>();
        Map<Double, Map<Double, Double>> mapMap = new HashMap<Double, Map<Double, Double>>();

        for(int i=0; i<len_CsCv; i++){
            for(int j=0; j<len_Cv; j++){
                for(int k=0; k<len_P; k++){
                    map.put(PValues[k], rowData[i][j][k]);
                }
                mapMap.put(CvValues[j], map);
                map = new HashMap<Double, Double>();
            }
            Distribution.put(CsCvValues[i], mapMap);
            mapMap = new HashMap<Double, Map<Double, Double>>();
        }
    }

    public Double[] getPValues() {
        return PValues;
    }

    public Double[] getCvValues() {
        return CvValues;
    }

    public Double[] getCsCvValues() {
        return CsCvValues;
    }

    Double[][][] rowData = {
            {
                    { 1.32, 1.28, 1.27, 1.24, 1.21, 1.17, 1.13, 1.08, 1.06, 1.05, 1.02, 1.0, 0.97, 0.95, 0.93, 0.91, 0.88, 0.84, 0.82, 0.78, 0.76, 0.74, 0.7 },
                    { 1.67, 1.58, 1.55, 1.49, 1.43, 1.34, 1.26, 1.17, 1.13, 1.1, 1.04, 0.99, 0.94, 0.89, 0.86, 0.83, 0.75, 0.68, 0.64, 0.57, 0.53, 0.5, 0.45 },
                    { 2.03, 1.88, 1.84, 1.75, 1.64, 1.52, 1.39, 1.25, 1.19, 1.15, 1.06, 0.99, 0.9, 0.83, 0.78, 0.74, 0.63, 0.53, 0.48, 0.38, 0.34, 0.31, 0.25 },
                    { 2.39, 2.21, 2.15, 2.03, 1.9, 1.7, 1.53, 1.34, 1.26, 1.2, 1.08, 0.97, 0.87, 0.77, 0.71, 0.65, 0.5, 0.38, 0.33, 0.23, 0.18, 0.15, 0.11 },
                    { 2.77, 2.53, 2.45, 2.31, 2.14, 1.9, 1.68, 1.42, 1.33, 1.24, 1.09, 0.96, 0.83, 0.7, 0.62, 0.55, 0.38, 0.26, 0.21, 0.12, 0.09, 0.07, 0.04 },
                    { 3.14, 2.86, 2.76, 2.59, 2.38, 2.1, 1.83, 1.51, 1.41, 1.29, 1.1, 0.93, 0.79, 0.62, 0.53, 0.45, 0.26, 0.15, 0.11, 0.05, 0.03, 0.02, 0.01 },
                    { 2.48, 3.18, 3.06, 2.87, 2.63, 2.31, 1.99, 1.59, 1.47, 1.34, 1.1, 0.89, 0.71, 0.51, 0.42, 0.35, 0.17, 0.08, 0.05, 0.01, 0.0, 0.0, 0.0 },
                    { 3.82, 3.48, 3.37, 3.15, 2.89, 2.52, 2.16, 1.69, 1.52, 1.38, 1.1, 0.83, 0.61, 0.41, 0.31, 0.24, 0.09, 0.04, 0.02, 0.0, 0.0, 0.0, 0.0 },
                    { 4.13, 3.8, 3.68, 3.45, 3.18, 2.76, 2.35, 1.78, 1.58, 1.4, 1.05, 0.76, 0.51, 0.3, 0.21, 0.15, 0.04, 0.01, 0.0, 0.0, 0.0, 0.0, 0.0 },
                    { 4.44, 4.12, 4.0, 3.78, 3.49, 3.04, 2.57, 1.88, 1.62, 1.39, 0.99, 0.67, 0.4, 0.21, 0.14, 0.09, 0.02, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                    { 4.44, 4.12, 4.0, 3.78, 3.49, 3.04, 2.57, 1.88, 1.62, 1.39, 0.99, 0.67, 0.4, 0.21, 0.14, 0.09, 0.02, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                    { 4.44, 4.12, 4.0, 3.78, 3.49, 3.04, 2.57, 1.88, 1.62, 1.39, 0.99, 0.67, 0.4, 0.21, 0.14, 0.09, 0.02, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                    { 4.44, 4.12, 4.0, 3.78, 3.49, 3.04, 2.57, 1.88, 1.62, 1.39, 0.99, 0.67, 0.4, 0.21, 0.14, 0.09, 0.02, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
            },
            {
                    { 1.33, 1.28, 1.27, 1.24, 1.21, 1.17, 1.13, 1.1, 1.07, 1.05, 1.03, 1.0, 0.97, 0.95, 0.93, 0.91, 0.87, 0.84, 0.82, 0.78, 0.76, 0.74, 0.72 },
                    { 1.7, 1.6, 1.57, 1.51, 1.44, 1.34, 1.26, 1.17, 1.13, 1.1, 1.02, 0.99, 0.94, 0.89, 0.86, 0.83, 0.75, 0.69, 0.65, 0.58, 0.55, 0.52, 0.47 },
                    { 2.11, 1.94, 1.9, 1.79, 1.68, 1.53, 1.4, 1.25, 1.19, 1.14, 1.07, 0.98, 0.9, 0.83, 0.78, 0.74, 0.63, 0.55, 0.5, 0.41, 0.36, 0.33, 0.28 },
                    { 2.54, 2.31, 2.24, 2.09, 1.94, 1.72, 1.54, 1.32, 1.25, 1.18, 1.06, 0.96, 0.86, 0.76, 0.71, 0.65, 0.52, 0.42, 0.36, 0.27, 0.22, 0.2, 0.15 },
                    { 3.02, 2.7, 2.6, 2.41, 2.21, 1.92, 1.69, 1.41, 1.3, 1.2, 1.06, 0.93, 0.81, 0.69, 0.63, 0.57, 0.41, 0.31, 0.25, 0.16, 0.12, 0.11, 0.07 },
                    { 3.53, 3.12, 3.0, 2.76, 2.49, 2.13, 1.82, 1.48, 1.35, 1.24, 1.06, 0.9, 0.76, 0.62, 0.55, 0.47, 0.31, 0.21, 0.15, 0.08, 0.06, 0.04, 0.02 },
                    { 4.05, 3.55, 3.42, 3.11, 2.79, 2.35, 1.96, 1.55, 1.4, 1.26, 1.05, 0.86, 0.7, 0.55, 0.46, 0.39, 0.22, 0.14, 0.09, 0.04, 0.02, 0.02, 0.0 },
                    { 4.6, 4.02, 3.85, 3.49, 3.1, 2.56, 2.11, 1.61, 1.43, 1.28, 1.03, 0.81, 0.63, 0.46, 0.38, 0.31, 0.15, 0.08, 0.04, 0.02, 0.01, 0.0, 0.0 },
                    { 5.21, 4.52, 4.32, 3.9, 3.42, 2.8, 2.27, 1.67, 1.46, 1.3, 1.0, 0.76, 0.56, 0.38, 0.3, 0.23, 0.09, 0.04, 0.02, 0.01, 0.0, 0.0, 0.0 },
                    { 5.82, 5.04, 4.79, 4.31, 3.76, 3.05, 2.42, 1.72, 1.49, 1.29, 0.95, 0.7, 0.48, 0.3, 0.22, 0.16, 0.05, 0.02, 0.01, 0.0, 0.0, 0.0, 0.0 },
                    { 6.5, 5.56, 5.3, 4.73, 4.14, 3.28, 2.56, 1.75, 1.48, 1.26, 0.9, 0.62, 0.4, 0.23, 0.16, 0.11, 0.03, 0.01, 0.0, 0.0, 0.0, 0.0, 0.0 },
                    { 7.18, 6.1, 5.87, 5.21, 4.48, 3.54, 2.7, 1.77, 1.47, 1.25, 0.84, 0.54, 0.34, 0.17, 0.11, 0.07, 0.01, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                    { 7.18, 6.1, 5.87, 5.21, 4.48, 3.54, 2.7, 1.77, 1.47, 1.25, 0.84, 0.54, 0.34, 0.17, 0.11, 0.07, 0.01, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}
            },
            {
                    { 1.34, 1.29, 1.28, 1.25, 1.22, 1.17, 1.13, 1.08, 1.07, 1.05, 1.02, 1.0, 0.97, 0.95, 0.93, 0.92, 0.87, 0.84, 0.82, 0.78, 0.76, 0.75, 0.72 },
                    { 1.73, 1.62, 1.59, 1.52, 1.45, 1.35, 1.26, 1.16, 1.13, 1.09, 1.04, 0.99, 0.94, 0.89, 0.86, 0.83, 0.75, 0.7, 0.66, 0.59, 0.56, 0.54, 0.49 },
                    { 2.19, 2.0, 1.94, 1.83, 1.72, 1.54, 1.4, 1.24, 1.18, 1.13, 1.05, 0.97, 0.9, 0.82, 0.78, 0.75, 0.64, 0.56, 0.52, 0.44, 0.4, 0.37, 0.32 },
                    { 2.7, 2.42, 2.33, 2.16, 1.99, 1.74, 1.53, 1.31, 1.23, 1.17, 1.05, 0.95, 0.85, 0.76, 0.71, 0.66, 0.53, 0.45, 0.39, 0.31, 0.27, 0.24, 0.19 },
                    { 3.27, 2.87, 2.74, 2.51, 2.27, 1.94, 1.67, 1.38, 1.28, 1.19, 1.04, 0.92, 0.8, 0.69, 0.63, 0.57, 0.44, 0.34, 0.29, 0.21, 0.17, 0.15, 0.11 },
                    { 3.89, 3.36, 3.2, 2.89, 2.58, 2.15, 1.81, 1.44, 1.31, 1.21, 1.03, 0.88, 0.75, 0.62, 0.56, 0.49, 0.35, 0.25, 0.2, 0.13, 0.1, 0.08, 0.05 },
                    { 4.57, 3.88, 3.68, 3.29, 2.89, 2.36, 1.94, 1.49, 1.34, 1.22, 1.01, 0.84, 0.69, 0.55, 0.49, 0.42, 0.27, 0.18, 0.14, 0.08, 0.05, 0.04, 0.02 },
                    { 5.3, 4.47, 4.19, 3.71, 3.24, 2.57, 2.06, 1.54, 1.37, 1.22, 0.99, 0.8, 0.63, 0.49, 0.42, 0.35, 0.21, 0.13, 0.09, 0.04, 0.03, 0.02, 0.01 },
                    { 6.08, 5.06, 4.73, 4.15, 3.53, 2.78, 2.19, 1.58, 1.38, 1.22, 0.96, 0.75, 0.57, 0.42, 0.35, 0.28, 0.15, 0.08, 0.05, 0.02, 0.01, 0.01, 0.0 },
                    { 6.91, 5.68, 5.3, 4.61, 3.85, 3.0, 2.3, 1.61, 1.39, 1.2, 0.92, 0.69, 0.51, 0.36, 0.29, 0.22, 0.11, 0.05, 0.33, 0.01, 0.01, 0.0, 0.0 },
                    { 7.76, 6.32, 5.88, 5.06, 4.22, 3.21, 2.41, 1.62, 1.37, 1.18, 0.87, 0.64, 0.45, 0.31, 0.24, 0.17, 0.07, 0.03, 0.02, 0.0, 0.0, 0.0, 0.0 },
                    { 8.65, 6.9, 6.5, 5.3, 4.47, 3.45, 2.5, 1.62, 1.34, 1.13, 0.81, 0.58, 0.4, 0.26, 0.19, 0.13, 0.05, 0.02, 0.01, 0.0, 0.0, 0.0, 0.0},
                    { 8.65, 6.9, 6.5, 5.3, 4.47, 3.45, 2.5, 1.62, 1.34, 1.13, 0.81, 0.58, 0.4, 0.26, 0.19, 0.13, 0.05, 0.02, 0.01, 0.0, 0.0, 0.0, 0.0}
            },
            {
                    { 1.35, 1.3, 1.29, 1.25, 1.22, 1.17, 1.14, 1.09, 1.07, 1.05, 1.02, 0.99, 0.97, 0.94, 0.93, 0.91, 0.87, 0.84, 0.83, 0.79, 0.77, 0.76, 0.73 },
                    { 1.8, 1.67, 1.63, 1.55, 1.47, 1.36, 1.26, 1.16, 1.12, 1.09, 1.03, 0.98, 0.93, 0.88, 0.86, 0.83, 0.76, 0.71, 0.68, 0.62, 0.59, 0.57, 0.53 },
                    { 2.36, 2.08, 2.02, 1.88, 1.75, 1.54, 1.39, 1.23, 1.17, 1.12, 1.03, 0.96, 0.89, 0.82, 0.79, 0.75, 0.66, 0.59, 0.55, 0.48, 0.45, 0.43, 0.38 },
                    { 3.0, 2.6, 2.48, 2.25, 2.03, 1.75, 1.52, 1.29, 1.21, 1.14, 1.03, 0.93, 0.84, 0.76, 0.72, 0.67, 0.57, 0.49, 0.45, 0.37, 0.34, 0.31, 0.27 },
                    { 3.75, 3.18, 3.0, 2.66, 2.33, 1.94, 1.63, 1.33, 1.23, 1.15, 1.01, 0.9, 0.79, 0.7, 0.65, 0.6, 0.48, 0.41, 0.36, 0.29, 0.25, 0.23, 0.19 },
                    { 4.58, 3.74, 3.5, 3.07, 2.55, 2.14, 1.76, 1.38, 1.26, 1.15, 1.0, 0.86, 0.74, 0.64, 0.58, 0.53, 0.41, 0.33, 0.28, 0.21, 0.18, 0.16, 0.13 },
                    { 5.43, 4.16, 4.06, 3.49, 2.96, 2.35, 1.87, 1.42, 1.27, 1.16, 0.97, 0.82, 0.69, 0.58, 0.52, 0.47, 0.34, 0.26, 0.22, 0.16, 0.12, 0.12, 0.09 },
                    { 6.31, 4.96, 4.64, 3.92, 3.3, 2.51, 1.97, 1.45, 1.29, 1.15, 0.95, 0.78, 0.65, 0.53, 0.47, 0.41, 0.29, 0.21, 0.17, 0.12, 0.09, 0.08, 0.06 },
                    { 7.33, 5.68, 5.21, 4.4, 3.64, 2.7, 2.09, 1.47, 1.28, 1.14, 0.91, 0.74, 0.6, 0.47, 0.41, 0.36, 0.24, 0.17, 0.13, 0.08, 0.06, 0.05, 0.03 },
                    { 8.43, 6.44, 5.85, 4.88, 4.0, 2.89, 2.15, 1.49, 1.28, 1.13, 0.88, 0.7, 0.55, 0.42, 0.36, 0.31, 0.19, 0.13, 0.1, 0.06, 0.04, 0.03, 0.02 },
                    { 9.49, 7.16, 6.5, 5.37, 4.3, 3.05, 2.24, 1.49, 1.27, 1.11, 0.85, 0.66, 0.5, 0.37, 0.31, 0.26, 0.16, 0.1, 0.07, 0.04, 0.03, 0.02, 0.01 },
                    { 10.68, 8.05, 7.1, 5.79, 4.65, 3.23, 2.31, 1.5, 1.27, 1.08, 0.81, 0.61, 0.46, 0.33, 0.27, 0.22, 0.12, 0.07, 0.05, 0.03, 0.02, 0.01, 0.01},
                    { 10.68, 8.05, 7.1, 5.79, 4.65, 3.23, 2.31, 1.5, 1.27, 1.08, 0.81, 0.61, 0.46, 0.33, 0.27, 0.22, 0.12, 0.07, 0.05, 0.03, 0.02, 0.01, 0.01}
            },
            {
                    { 1.38, 1.32, 1.3, 1.25, 1.21, 1.17, 1.11, 1.08, 1.06, 1.05, 1.02, 0.99, 0.97, 0.94, 0.93, 0.91, 0.88, 0.85, 0.83, 0.8, 0.78, 0.77, 0.75 },
                    { 1.87, 1.71, 1.67, 1.58, 1.48, 1.36, 1.26, 1.15, 1.11, 1.08, 1.03, 0.98, 0.93, 0.88, 0.86, 0.83, 0.77, 0.72, 0.69, 0.64, 0.61, 0.6, 0.56 },
                    { 2.53, 2.2, 2.1, 1.94, 1.77, 1.55, 1.38, 1.21, 1.15, 1.1, 1.02, 0.95, 0.89, 0.82, 0.79, 0.75, 0.67, 0.61, 0.58, 0.52, 0.49, 0.47, 0.43 },
                    { 3.29, 2.74, 2.6, 2.34, 2.07, 1.75, 1.51, 1.26, 1.19, 1.12, 1.01, 0.92, 0.84, 0.76, 0.72, 0.68, 0.59, 0.52, 0.48, 0.42, 0.39, 0.37, 0.33 },
                    { 4.2, 3.34, 3.13, 2.77, 2.38, 1.93, 1.61, 1.31, 1.21, 1.13, 0.99, 0.89, 0.79, 0.71, 0.66, 0.61, 0.51, 0.44, 0.4, 0.34, 0.3, 0.29, 0.25 },
                    { 5.07, 4.0, 3.69, 3.17, 2.72, 2.11, 1.72, 1.34, 1.23, 1.13, 0.97, 0.85, 0.75, 0.65, 0.6, 0.55, 0.44, 0.37, 0.33, 0.27, 0.24, 0.22, 0.19 },
                    { 6.05, 4.6, 4.25, 3.59, 3.0, 2.28, 1.82, 1.37, 1.23, 1.12, 0.95, 0.82, 0.7, 0.6, 0.55, 0.5, 0.38, 0.32, 0.27, 0.22, 0.19, 0.17, 0.14 },
                    { 7.08, 5.28, 4.81, 4.01, 3.28, 2.45, 1.9, 1.4, 1.24, 1.12, 0.93, 0.78, 0.66, 0.55, 0.5, 0.45, 0.33, 0.26, 0.23, 0.17, 0.15, 0.13, 0.1 },
                    { 8.12, 5.96, 5.43, 4.43, 3.55, 2.6, 2.0, 1.41, 1.25, 1.1, 0.9, 0.75, 0.62, 0.5, 0.45, 0.4, 0.29, 0.22, 0.18, 0.14, 0.11, 0.1, 0.08 },
                    { 9.25, 6.67, 6.02, 4.9, 3.9, 2.77, 2.05, 1.42, 1.24, 1.09, 0.87, 0.71, 0.57, 0.46, 0.4, 0.36, 0.25, 0.18, 0.15, 0.11, 0.08, 0.07, 0.05 },
                    { 10.42, 7.4, 6.65, 5.35, 4.25, 2.92, 2.12, 1.43, 1.24, 1.07, 0.85, 0.67, 0.53, 0.42, 0.37, 0.31, 0.21, 6.15, 0.12, 0.08, 0.06, 0.05, 0.04 },
                    { 11.65, 8.2, 7.31, 5.82, 4.58, 3.07, 2.18, 1.43, 1.22, 1.06, 0.81, 0.63, 0.49, 0.38, 0.32, 0.27, 0.18, 0.12, 0.1, 0.06, 0.05, 0.04, 0.03 },
                    {15.2, 10.5, 9.17, 7.09, 5.5, 3.5, 2.35, 1.43, 1.2, 0.98, 0.72, 0.51, 0.36, 0.26, 0.2, 0.16, 0.09, 0.04, 0.03, 0.02, 0.02, 0.01, 0.0}
            },
            {
                { 1.4, 1.33, 1.31, 1.27, 1.23, 1.17, 1.13, 1.08, 1.06, 1.05, 1.02, 0.99, 0.97, 0.94, 0.93, 0.91, 0.88, 0.84, 0.82, 0.78, 0.76, 0.75, 0.73 },
                { 1.95, 1.76, 1.7, 1.61, 1.5, 1.36, 1.26, 1.15, 1.11, 1.08, 1.02, 0.97, 0.93, 0.88, 0.86, 0.83, 0.77, 0.73, 0.7, 0.66, 0.63, 0.62, 0.59 },
                { 2.66, 2.27, 2.16, 1.98, 1.78, 1.55, 1.37, 1.21, 1.15, 1.09, 1.01, 0.94, 0.88, 0.82, 0.79, 0.75, 0.68, 0.63, 0.6, 0.55, 0.52, 0.51, 0.47 },
                { 3.51, 2.88, 2.69, 2.38, 2.1, 1.74, 1.49, 1.25, 1.17, 1.1, 1.0, 0.92, 0.84, 0.77, 0.73, 0.69, 0.61, 0.55, 0.51, 0.45, 0.42, 0.41, 0.37 },
                { 4.44, 3.44, 3.21, 2.79, 2.4, 1.9, 1.6, 1.3, 1.2, 1.1, 0.98, 0.88, 0.79, 0.71, 0.67, 0.63, 0.53, 0.47, 0.43, 0.37, 0.34, 0.32, 0.29 },
                { 5.4, 4.2, 3.77, 3.21, 2.72, 2.08, 1.7, 1.32, 1.2, 1.11, 0.97, 0.85, 0.75, 0.66, 0.62, 0.57, 0.47, 0.4, 0.36, 0.31, 0.28, 0.26, 0.23 },
                { 6.43, 4.76, 4.34, 3.65, 3.0, 2.22, 1.79, 1.34, 1.2, 1.1, 0.94, 0.82, 0.71, 0.61, 0.56, 0.52, 0.41, 0.34, 0.31, 0.25, 0.23, 0.21, 0.18 },
                { 7.54, 5.5, 4.93, 4.06, 3.35, 2.41, 1.86, 1.36, 1.22, 1.1, 0.92, 0.78, 0.67, 0.56, 0.51, 0.47, 0.36, 0.29, 0.26, 0.2, 0.18, 0.16, 0.14 },
                { 8.64, 6.2, 5.52, 4.5, 3.65, 2.54, 1.94, 1.36, 1.22, 1.09, 0.9, 0.75, 0.63, 0.52, 0.47, 0.42, 0.32, 0.25, 0.22, 0.16, 0.14, 0.12, 0.1 },
                { 9.83, 6.9, 6.17, 4.94, 3.92, 2.71, 2.0, 1.39, 1.22, 1.08, 0.87, 0.71, 0.58, 0.48, 0.42, 0.37, 0.27, 0.21, 0.18, 0.13, 0.11, 0.1, 0.08 },
                { 10.96, 7.7, 6.85, 5.33, 4.2, 2.85, 2.05, 1.4, 1.2, 1.06, 0.84, 0.68, 0.55, 0.44, 0.39, 0.34, 0.24, 0.18, 0.15, 0.1, 0.09, 0.08, 0.06 },
                { 12.14, 8.2, 7.35, 5.75, 4.5, 2.98, 2.11, 1.41, 1.2, 1.04, 0.81, 0.65, 0.51, 0.41, 0.36, 0.31, 0.21, 0.15, 0.12, 0.08, 0.07, 0.06, 0.04},
                { 12.14, 8.2, 7.35, 5.75, 4.5, 2.98, 2.11, 1.41, 1.2, 1.04, 0.81, 0.65, 0.51, 0.41, 0.36, 0.31, 0.21, 0.15, 0.12, 0.08, 0.07, 0.06, 0.04}
            },
            {
                { 1.41, 1.34, 1.32, 1.29, 1.24, 1.18, 1.14, 1.08, 1.07, 1.04, 1.02, 0.99, 0.96, 0.94, 0.93, 0.91, 0.88, 0.85, 0.83, 0.8, 0.78, 0.76, 0.75 },
                { 2.02, 1.8, 1.74, 1.63, 1.51, 1.37, 1.26, 1.14, 1.1, 1.07, 1.02, 0.97, 0.92, 0.88, 0.8, 0.84, 0.78, 0.74, 0.72, 0.67, 0.65, 0.64, 0.61 },
                { 2.8, 2.34, 2.22, 2.01, 1.8, 1.55, 1.37, 1.19, 1.13, 1.08, 1.01, 0.94, 0.88, 0.83, 0.8, 0.77, 0.7, 0.65, 0.62, 0.57, 0.55, 0.53, 0.5 },
                { 3.68, 2.92, 2.73, 2.4, 2.1, 1.73, 1.47, 1.23, 1.16, 1.1, 0.99, 0.91, 0.84, 0.77, 0.74, 0.7, 0.62, 0.56, 0.53, 0.48, 0.45, 0.43, 0.4 },
                { 4.58, 3.55, 3.26, 2.81, 2.38, 1.89, 1.56, 1.27, 1.18, 1.1, 0.98, 0.88, 0.8, 0.72, 0.68, 0.64, 0.55, 0.49, 0.46, 0.4, 0.37, 0.36, 0.33 },
                { 5.54, 4.2, 3.82, 3.22, 2.68, 2.05, 1.66, 1.3, 1.19, 1.1, 0.96, 0.85, 0.76, 0.67, 0.63, 0.58, 0.49, 0.43, 0.39, 0.33, 0.31, 0.29, 0.26 },
                { 6.57, 4.84, 4.38, 3.63, 2.98, 2.2, 1.73, 1.32, 1.2, 1.1, 0.94, 0.82, 0.72, 0.63, 0.58, 0.53, 0.43, 0.37, 0.33, 0.28, 0.25, 0.24, 0.21 },
                { 7.63, 5.48, 4.93, 4.03, 3.29, 2.36, 1.82, 1.34, 1.21, 1.09, 0.92, 0.79, 0.68, 0.58, 0.53, 0.48, 0.38, 0.32, 0.28, 0.23, 0.2, 0.19, 0.16 },
                { 8.79, 6.16, 5.51, 4.44, 3.57, 2.51, 1.9, 1.36, 1.2, 1.08, 0.89, 0.75, 0.64, 0.54, 0.49, 0.44, 0.33, 0.27, 0.24, 0.19, 0.17, 0.15, 0.12 },
                { 10.0, 6.88, 6.11, 4.86, 3.84, 2.66, 1.96, 1.37, 1.2, 1.07, 0.87, 0.72, 0.6, 0.49, 0.44, 0.39, 0.29, 0.23, 0.2, 0.15, 0.13, 0.12, 0.09 },
                { 11.18, 7.6, 6.71, 5.27, 4.14, 2.8, 2.03, 1.37, 1.2, 1.05, 0.85, 0.68, 0.56, 0.45, 0.4, 0.35, 0.26, 0.2, 0.17, 0.12, 0.1, 0.09, 0.08 },
                { 12.39, 8.3, 7.31, 5.69, 4.4, 2.9, 2.08, 1.38, 1.19, 1.04, 0.82, 0.66, 0.53, 0.42, 0.37, 0.32, 0.22, 0.17, 0.14, 0.1, 0.08, 0.07, 0.06 },
                {15.55, 10.2, 9.08, 6.95, 5.25, 3.25, 2.22, 1.4, 1.17, 1.0, 0.74, 0.57, 0.43, 0.32, 0.27, 0.24, 0.14, 0.1, 0.07, 0.04, 0.03, 0.02, 0.01}
            }
    };


}
