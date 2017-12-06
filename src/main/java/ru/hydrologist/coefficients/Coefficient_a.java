package ru.hydrologist.coefficients;

import java.util.Arrays;

/**
 * Created by fedorovskiy on 27.02.2017.
 */
public class Coefficient_a {

    /*структура aCoefficient
    Cs/Cv=2=>[
            r=0=>[
                "a1"=>0,
                "a2"=>0.19,
                "a3"=>0.99,
                "a4"=>-0.88,
                "a5"=>0.01,
                "a6"=>1.54
            ],
            r=0.3=>[...]
            r=0.5=>[...]
        ]
    Cs/Cv=3=>[...],
    Cs/Cv=4=>[...]
    */
    private static double[][][] aCoefficient = {
            {
                    {0.0, 0.19, 0.99, -0.88, 0.01, 1.54},
                    {0.0, 0.22,	0.99, -0.41, 0.01, 1.51},
                    {0.0, 0.18, 0.98, 0.41, 0.02, 1.47}
            },  {
                    {0.0, 0.69, 0.98, -4.34, 0.01, 6.78},
                    {0.0, 1.15, 1.02, -7.53, -0.04, 12.38},
                    {0.0, 1.75, 1.0, -11.79, -0.05, 21.13}
            },  {
                    {0.0, 1.36, 1.02, -9.68, -0.05, 15.55},
                    {0.02, 2.61, 1.13, -19.85, -0.22, 34.18},
                    {-0.02, 3.47, 1.18, -29.71, -0.41, 58.08}
                }
            };

    private static Double[] CsCvValues = {2.0, 3.0, 4.0}; 	//Промежуточные значения CsCv, для которых зафиксировано значение коэффициента
    private static Double[] rValues = {0.0, 0.3, 0.5};	//Промежуточные значения r, для которых зафикисрованы значения коэффициента



    public static double[][][] getaCoefficient() {
        return aCoefficient;
    }

    public static Double[] getCsCvValues() {
        return CsCvValues;
    }

    public static Double[] getrValues() {
        return rValues;
    }

    public static int getCsCvIndex(Double value){
        int elemIndex = Arrays.binarySearch(CsCvValues, value);
        return elemIndex;
    }

    public static int getRValuesIndex(Double value){
        int elemIndex = Arrays.binarySearch(rValues, value);
        return elemIndex;
    }

}
