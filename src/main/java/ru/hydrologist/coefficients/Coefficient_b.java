package ru.hydrologist.coefficients;

/**
 * Created by fedorovskiy on 27.02.2017.
 */
public class Coefficient_b {

    /* Структура коэффициента b:
    r=0:{
        b1:0,
        b2:0.19,
        b3:0.99,
        b4:-0.88,
        b5:0.01,
        b6:1.54
    },
    r=0.3:{...}
    r=0.5:{...}
}
*/
    private static double[][] bCoefficient = {
            {0.03, 2, 0.92, -5.09, 0.03, 8.10},
            {0.03, 1.77, 0.93, -3.45, 0.03, 8.03},
            {0.03, 1.63, 0.92, -0.97, 0.03, 7.94}
    };

    public static double[][] getbCoefficient() {
        return bCoefficient;
    }
}
