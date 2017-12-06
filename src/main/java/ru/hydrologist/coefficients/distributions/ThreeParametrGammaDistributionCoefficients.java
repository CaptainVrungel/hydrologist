package ru.hydrologist.coefficients.distributions;

import java.util.HashMap;
import java.util.Map;

//Сделать его синглтоном

/**
 * Created by fedorovskiy on 03.03.2017.
 */
public class ThreeParametrGammaDistributionCoefficients {
    private static ThreeParametrGammaDistributionCoefficients instance;

    private Double[] PValues = { 0.001, 0.01, 0.03, 0.05, 0.1, 0.3, 0.5, 1.0, 3.0, 5.0, 10.0, 20.0, 25.0, 30.0, 40.0, 50.0, 60.0, 70.0, 75.0, 80.0, 90.0, 95.0, 97.0, 99.0 };
    private Double[] CvValues = { 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0 };
    private Double[] CsCvValues= {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};

    Map<Double, Map<Double, Map<Double, Double>>> Distribution = new HashMap<Double, Map<Double, Map<Double, Double>>>();


    Double[][] D10 = {
            {1.46,  1.38,  1.35,  1.34,  1.32,  1.28,  1.27,  1.24,  1.19,  1.17,  1.13,  1.08,  1.07,  1.05,  1.02,   0.998,  0.973,  0.946,  0.932,  0.915,  0.873,   0.838,   0.816,    0.775},
            {1.94,  1.81,  1.74,  1.71,  1.67,  1.59,  1.55,  1.49,  1.39,  1.34,  1.26,  1.17,  1.13,  1.1,   1.04,   0.993,  0.943,  0.890,  0.861,  0.829,  0.748,   0.683,   0.642,    0.586},
            {2.46,  2.26,  2.15,  2.10,  2.03,  1.91,  1.84,  1.76,  1.60,  1.52,  1.40,  1.25,  1.20,  1.15,  1.06,   0.985,  0.909,  0.830,  0.787,  0.740,  0.623,   0.533,   0.487,    0.383},
            {2.97,  2.70,  2.56,  2.49,  2.40,  2.23,  2.15,  2.03,  1.82,  1.70,  1.54,  1.34,  1.26,  1.20,  1.08,   0.972,  0.870,  0.764,  0.708,  0.648,  0.500,   0.392,   0.329,    0.229},
            {3.47,  3.15,  2.97,  2.89,  2.77,  2.56,  2.46,  2.30,  2.04,  1.90,  1.68,  1.42,  1.33,  1.24,  1.09,   0.954,  0.824,  0.692,  0.622,  0.549,  0.378,   0.263,   0.202,    0.115},
            {3.94,  3.57,  3.37,  3.27,  3.13,  2.89,  2.77,  2.59,  2.27,  2.10,  1.83,  1.51,  1.39,  1.29,  1.10,   0.928,  0.768,  0.609,  0.528,  0.445,  0.264,   0.157,   0.107,    0.047},
            {4.36,  3.59,  3.74,  3.64,  3.48,  3.21,  3.08,  2.88,  2.50,  2.30,  1.99,  1.60,  1.46,  1.33,  1.10,   0.891,  0.698,  0.515,  0.426,  0.338,  0.165,   0.081,   0.048,    0.015},
            {4.73,  4.31,  4.09,  3.98,  3.82,  3.53,  3.38,  3.16,  2.75,  2.53,  2.16,  1.70,  1.52,  1.37,  1.08,   0.836,  0.613,  0.413,  0.321,  0.237,  0.092,   0.036,   0.018,    0.004},
            {5.06,  4.64,  4.41,  4.29,  4.13,  3.84,  3.69,  3.46,  3.01,  2.76,  2.35,  1.80,  1.59,  1.39,  1.05,   0.760,  0.512,  0.309,  0.224,  0.151,  0.045,   0.013,   0.005,    0.001},
            {5.35,  4.92,  4.69,  4.58,  4.42,  4.14,  3.99,  3.75,  3.29,  3.02,  2.55,  1.90,  1.64,  1.40,  0.995,  0.665,  0.406,  0.215,  0.144,  0.088,  0.01919, 0.004,   0.001,    0.0001},
            {5.88,  5.16,  4.94,  4.83,  4.69,  4.44,  4.29,  4.06,  3.59,  3.31,  2.78,  2.00,  1.68,  1.39,  0.916,  0.559,  0.306,  0.141,  0.086,  0.047,  0.007,   0.001,   0.0003,   0.00002},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005}
    };

        Double[][] D15 = {
            {1.47,  1.40,  1.37,  1.35,  1.33,  1.29,  1.27,  1.24,  1.19,  1.17,  1.13,  1.08,  1.07,  1.05,  1.020,  0.988,  0.972,  0.946,  0.931,  0.915,  0.874,  0.8400,   0.819,    0.78},
            {2.01,  1.86,  1.79,  1.75,  1.70,  1.61,  1.57,  1.51,  1.40,  1.35,  1.26,  1.16,  1.13,  1.10,  1.040,  0.990,  0.940,  0.888,  0.860,  0.829,  0.751,  0.6890,   0.651,    0.581},
            {2.63,  2.38,  2.26,  2.20,  2.11,  1.97,  1.90,  1.79,  1.62,  1.53,  1.40,  1.25,  1.19,  1.14,  1.060,  0.977,  0.903,  0.826,  0.785,  0.741,  0.632,  0.5480,   0.498,    0.410},
            {3.30,  2.94,  2.76,  2.68,  2.54,  2.34,  2.24,  2.09,  1.85,  1.72,  1.54,  1.32,  1.25,  1.18,  1.060,  0.958,  0.860,  0.750,  0.708,  0.652,  0.518,  0.419,    0.363,    0.268},
            {4.03,  3.55,  3.30,  3.18,  3.02,  2.74,  2.61,  2.42,  2.09,  1.92,  1.68,  1.40,  1.30,  1.21,  1.060,  0.934,  0.812,  0.690,  0.630,  0.562,  0.409,  0.305,    0.247,    0.160},
            {4.81,  4.19,  3.88,  3.73,  3.52,  3.17,  3.00,  2.76,  2.34,  2.13,  1.82,  1.47,  1.35,  1.24,  1.060,  0.902,  0.757,  0.616,  0.545,  0.472,  0.310,  0.207,    0.155,    0.084},
            {5.64,  4.88,  4.50,  4.31,  4.06,  3.62,  3.41,  3.11,  2.60,  2.34,  1.97,  1.54,  1.39,  1.27,  1.050,  0.862,  0.695,  0.538,  0.460,  0.384,  0.222,  0.130,    0.068,    0.038},
            {6.50,  5.61,  5.14,  4.93,  4.62,  4.10,  3.85,  3.49,  2.88,  2.57,  2.11,  1.61,  1.43,  1.28,  1.030,  0.814,  0.627,  0.457,  0.377,  0.299,  0.148,  0.074,    0.045,    0.015},
            {7.41,  6.38,  5.82,  5.58,  5.22,  4.61,  4.31,  3.89,  3.16,  2.80,  2.26,  1.67,  1.46,  1.28,  0.994,  0.754,  0.553,  0.376,  0.297,  0.233,  0.092,  0.038,    0.020,    0.005},
            {8.39,  7.19,  6.56,  6.26,  5.84,  5.14,  4.80,  4.30,  3.46,  3.03,  2.41,  1.72,  1.48,  1.28,  0.952,  0.690,  0.4775, 0.298,  0.223,  0.156,  0.053,  0.018,    0.008,    0.001},
            {9.41,  8.03,  7.33,  6.59,  5.60,  5.72,  5.32,  4.74,  3.78,  3.28,  2.56,  1.76,  1.49,  1.26,  0.901,  0.618,  0.398,  0.223,  0.161,  0.105,  0.028,  0.008,    0.003,    0.0004},
            {10.4,  8.92,  8.13,  7.67,  7.18,  6.32,  5.87,  5.21,  4.12,  3.55,  2.71,  1.80,  1.49,  1.24,  0.840,  0.541,  0.324,  0.168,  0.111,  0.067,  0.014,  0.003,    0.001,    0.0001},
            {11.5,  9.83,  8.96,  8.43,  7.88,  6.59,  6.44,  5.70,  4.48,  3.83,  2.86,  1.82,  1.48,  1.20,  0.766,  0.463,  0.253,  0.118,  0.072,  0.039,  0.006,  0.001,    0.0003,   0.00004},
            {12.7,  10.8,  9.80,  9.22,  8.61,  7.60,  7.04,  6.24,  4.86,  4.12,  3.00,  1.83,  1.46,  1.16,  0.692,  0.388,  0.193,  0.079,  0.045,  0.022,  0.003,  0.0003,   0.00008,  0.000008},
            {13.9,  11.8,  10.7,  10.1,  9.38,  8.25,  7.66,  6.78,  5.27,  4.44,  3.13,  1.83,  1.43,  1.10,  0.622,  0.320,  0.142,  0.051,  0.027,  0.012,  0.001,  0.0002,   0.00003,  0.000002},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005},
            {5.76,  5.34,  5.16,  5.06,  4.92,  4.74,  4.59,  4.36,  3.92,  3.63,  3.03,  2.10,  1.69,  1.34,  0.808,  0.446,  0.216,  0.085,  0.046,  0.023,  0.002,   0.0003,  0.00006,  0.000005}
        };

    Double[][] D20 = {
            {1.49,  1.42,  1.38,  1.36,  1.34,  1.30,  1.28,  1.25,  1.20,  1.17,  1.13,  1.03,  1.06,  1.05,  1.02,  0.997,  0.972,  0.945,  0.931,  0.915,  0.847,  0.842,  0.821,  0.782},
            {2.09,  1.92,  1.83,  1.79,  1.73,  1.64,  1.59,  1.52,  1.41,  1.35,  1.35,  1.16,  1.13,  1.09,  1.04,  0.936,  0.933,  0.886,  0.858,  0.830,  0.754,  0.696,  0.660,  0.594},
            {2.82,  2.52,  2.36,  2.29,  2.19,  2.02,  1.94,  1.82,  1.64,  1.54,  1.40,  1.34,  1.18,  1.13,  1.05,  0.970,  0.893,  0.823,  0.734,  0.745,  0.640,  0.565,  0.517,  0.436},
            {3.68,  3.20,  2.96,  2.85,  2.70,  2.45,  2.32,  2.16,  1.87,  1.74,  1.54,  1.31,  1.33,  1.16,  1.05,  0.943,  0.852,  0.760,  0.708,  0.656,  0.532,  0.448,  0.392,  0.304},
            {4.67,  3.98,  3.64,  3.46,  3.27,  2.91,  2.74,  2.51,  2.13,  1.94,  1.67,  1.38,  1.28,  1.19,  1.04,  0.913,  0.803,  0.691,  0.634,  0.574,  0.436,  0.342,  0.288,  0.206},
            {5.78,  4.85,  4.39,  4.18,  3.87,  3.42,  3.20,  2.89,  2.39,  2.15,  1.80,  1.44,  1.31,  1.21,  1.03,  0.866,  0.743,  0.622,  0.556,  0.496,  0.352,  0.256,  0.202,  0.130},
            {7.03,  5.81,  5.22,  4.95,  4.56,  3.96,  3.63,  3.29,  2.66,  2.36,  1.94,  1.50,  1.34,  1.22,  1.01,  0.846,  0.692,  0.552,  0.489,  0.419,  0.272,  0.181,  0.139,  0.076},
            {8.40,  6.85,  6.11,  5.77,  5.30,  4.55,  4.19,  3.71,  2.94,  2.57,  2.06,  1.54,  1.37,  1.32,  0.984, 0.800,  0.632,  0.488,  0.416,  0.352,  0.208,  0.154,  0.082,  0.040},
            {9.89,  7.98,  7.08,  6.66,  6.08,  5.16,  4.74,  4.15,  3.21,  2.78,  2.19,  1.58,  1.38,  1.22,  0.955, 0.743,  0.563,  0.424,  0.352,  0.280,  0.154,  0.082,  0.046,  0.019},
            {11.5,  9.31,  8.11,  7.60,  6.91,  5.81,  5.30,  4.60,  3.51,  3.00,  2.30,  1.61,  1.39,  1.20,  0.916, 0.693,  0.511,  0.357,  0.288,  0.223,  0.105,  0.051,  0.030,  0.010},
            {13.2,  10.5,  9.20,  8.61,  7.75,  6.47,  5.90,  5.05,  3.80,  3.22,  2.40,  1.62,  1.39,  1.18,  0.87,  0.640,  0.450,  0.300,  0.241,  0.175,  0.074,  0.030,  0.016,  0.005},
            {15.1,  11.8,  10.3,  9.65,  8.65,  7.10,  6.50,  5.53,  4.12,  3.40,  2.50,  1.63,  1.35,  1.14,  0.83,  0.580,  0.390,  0.250,  0.193,  0.130,  0.049,  0.016,  0.008,  0.002},
            {17.2,  13.2,  11.6,  10.8,  9.60,  7.98,  7.13,  6.02,  4.42,  3.60,  2.57,  1.62,  1.33,  1.11,  0.77,  0.520,  0.334,  0.203,  0.146,  0.094,  0.030,  0.009,  0.004,  0.001},
            {19.3,  14.7,  12.9,  11.9,  10.6,  8.70,  7.80,  6.55,  4.71,  3.80,  2.64,  1.61,  1.31,  1.08,  0.725, 0.460,  0.283,  0.155,  0.106,  0.065,  0.016,  0.004,  0.002,  0.0002},
            {21.6,  16.4,  14.3,  13.1,  11.6,  9.50,  8.42,  7.08,  4.98,  3.96,  2.70,  1.59,  1.28,  1.04,  0.67,  0.405,  0.234,  0.120,  0.077,  0.046,  0.009,  0.002,  0.001,  0.00008},
            {21.6,  16.4,  14.3,  13.1,  11.6,  9.50,  8.42,  7.08,  4.98,  3.96,  2.70,  1.59,  1.28,  1.04,  0.67,  0.405,  0.234,  0.120,  0.077,  0.046,  0.009,  0.002,  0.001,  0.00008},
            {21.6,  16.4,  14.3,  13.1,  11.6,  9.50,  8.42,  7.08,  4.98,  3.96,  2.70,  1.59,  1.28,  1.04,  0.67,  0.405,  0.234,  0.120,  0.077,  0.046,  0.009,  0.002,  0.001,  0.00008},
            {21.6,  16.4,  14.3,  13.1,  11.6,  9.50,  8.42,  7.08,  4.98,  3.96,  2.70,  1.59,  1.28,  1.04,  0.67,  0.405,  0.234,  0.120,  0.077,  0.046,  0.009,  0.002,  0.001,  0.00008},
            {21.6,  16.4,  14.3,  13.1,  11.6,  9.50,  8.42,  7.08,  4.98,  3.96,  2.70,  1.59,  1.28,  1.04,  0.67,  0.405,  0.234,  0.120,  0.077,  0.046,  0.009,  0.002,  0.001,  0.00008},
            {21.6,  16.4,  14.3,  13.1,  11.6,  9.50,  8.42,  7.08,  4.98,  3.96,  2.70,  1.59,  1.28,  1.04,  0.67,  0.405,  0.234,  0.120,  0.077,  0.046,  0.009,  0.002,  0.001,  0.00008}
        };

    Double[][] D25 = {
            {1.52,  1.44,  1.40,  1.38,  1.35,  1.30,  1.28,  1.25,  1.20,  1.17,  1.13,  1.08,  1.07,  1.05,   1.02,   0.998,  0.972,  0.945,  0.931,  0.915,  0.875,  0.843,  0.823,  0.784},
            {2.18,  1.98,  1.88,  1.83,  1.77,  1.66,  1.61,  1.54,  1.42,  1.35,  1.23,  1.16,  1.12,  1.09,   1.04,   0.984,  0.935,  0.885,  0.858,  0.830,  0.757,  0.702,  0.667,  0.606},
            {3.05,  2.67,  2.48,  2.39,  2.27,  2.08,  1.99,  1.86,  1.65,  1.55,  1.40,  1.23,  1.18,  1.13,   1.04,   0.964,  0.893,  0.822,  0.785,  0.745,  0.648,  0.576,  0.533,  0.459},
            {4.13,  3.49,  3.18,  3.04,  2.85,  2.55,  2.41,  2.21,  1.90,  1.74,  1.53,  1.30,  1.22,  1.15,   1.04,   0.938,  0.847,  0.758,  0.712,  0.663,  0.549,  0.467,  0.420,  0.341},
            {5.41,  4.45,  4.00,  3.79,  3.51,  3.07,  2.87,  2.59,  2.15,  1.95,  1.66,  1.36,  1.60,  1.17,   1.03,   0.906,  0.797,  0.693,  0.640,  0.585,  0.459,  0.373,  0.325,  0.248},
            {6.90,  5.54,  4.91,  4.62,  4.24,  3.64,  3.36,  3.00,  2.42,  2.15,  1.78,  1.41,  1.28,  1.18,   1.01,   0.870,  0.754,  0.629,  0.571,  0.512,  0.381,  0.293,  0.247,  0.175},
            {8.61,  6.76,  5.93,  5.54,  5.04,  4.26,  3.90,  3.42,  2.69,  2.35,  1.90,  1.45,  1.31,  1.18,   0.989,  0.830,  0.692,  0.567,  0.505,  0.444,  0.310,  0.227,  0.184,  0.120},
            {10.5,  8.10,  7.02,  6.53,  5.90,  4.91,  4.46,  3.87,  2.96,  2.55,  2.01,  1.49,  1.32,  1.18,   0.962,  0.787,  0.639,  0.506,  0.443,  0.381,  0.250,  0.172,  0.134,  0.080},
            {12.60,  9.55,  8.20,  7.59,  6.80,  5.58,  5.03,  4.32,  3.23,  2.75,  2.12,  1.52,  1.33,  1.17,   0.930,  0.742,  0.586,  0.449,  0.385,  0.324,  0.198,  0.128,  0.095,  0.052},
            {14.8,  11.1,  9.45,  8.72,  7.75,  6.23,  5.63,  4.78,  3.50,  2.94,  2.22,  1.54,  1.33,  1.15,   0.859,  0.695,  0.533,  0.395,  0.332,  0.272,  0.155,  0.093,  0.065,  0.032},
            {17.2,  12.8,  10.8,  9.92,  8.76,  7.02,  6.25,  5.26,  3.77,  3.13,  2.31,  1.55,  1.32,  1.14,   0.857,  0.648,  0.482,  0.344,  0.283,  0.226,  0.118,  0.066,  0.044,  0.019},
            {19.9,  14.6,  12.2,  11.2,  9.81,  7.78,  6.89,  5.73,  4.04,  3.31,  2.39,  1.56,  1.31,  1.11,   0.816,  0.600,  0.432,  0.297,  0.238,  0.185,  0.089,  0.046,  0.028,  0.011},
            {22.6,  16.4,  13.7,  12.5,  10.9,  8.56,  7.54,  6.22,  4.30,  3.48,  2.46,  1.56,  1.29,  1.08,   0.773,  0.552,  0.385,  0.254,  0.199,  0.149,  0.066,  0.030,  0.018,  0.006},
            {25.6,  18.4,  15.2,  13.8,  12.0,  9.36,  8.20,  6.71,  4.56,  3.65,  2.53,  1.55,  1.27,  1.05,   0.729,  0.505,  0.340,  0.215,  0.164,  0.119,  0.047,  0.020,  0.011,  0.003},
            {28.7,  20.4,  16.8,  15.2,  13.2,  10.2,  8.88,  7.20,  4.81,  3.81,  2.59,  1.54,  1.24,  1.01,   0.684,  0.459,  0.298,  0.180,  0.133,  0.094,  0.033,  0.012,  0.006,  0.001},
            {32.1,  22.5,  18.5,  16.7,  14.4,  11.1,  9.56,  7.70,  5.06,  3.96,  2.64,  1.52,  1.21,  0.972,  0.638,  0.415,  0.259,  0.149,  0.107,  0.072,  0.023,  0.008,  0.003,  0.001},
            {35.8,  24.7,  20.2,  18.2,  15.7,  12.0,  10.3,  8.20,  5.30,  4.11,  2.69,  1.50,  1.17,  0.931,  0.592,  0.373,  0.224,  0.122,  0.085,  0.055,  0.015,  0.004,  0.002,  0.0003},
            {39.7,  27.0,  22.1,  19.8,  17.0,  13.0,  11.0,  8.71,  5.54,  4.26,  2.73,  1.47,  1.14,  0.888,  0.545,  0.332,  0.191,  0.099,  0.066,  0.041,  0.010,  0.002,  0.001,  0.0001},
            {43.9,  29.3,  24.0,  21.5,  18.4,  14.1,  11.8,  9.22,  5.78,  4.39,  2.76,  1.44,  1.10,  0.843,  0.497,  0.295,  0.162,  0.079,  0.051,  0.036,  0.006,  0.001,  0.0004,  0.00004},
            {48.4,  31.9,  26.0,  23.2,  19.8,  15.2,  12.6,  9.74,  6.01,  4.52,  2.79,  1.41,  1.05,  0.797,  0.447,  0.259,  0.136,  0.062,  0.039,  0.022,  0.004,  0.001,  0.0002,  0.00002}
    };
    Double[][] D30 = {
            {1.54,  1.46,  1.41,  1.39,  1.36,  1.31,  1.28,  1.25,  1.20,  1.17,  1.13,  1.08,  1.07,  1.05,   1.02,   0.997,  0.972,  0.945,  0.931,  0.915,  0.876,  0.844,  0.825,  0.876},
            {2.29,  2.05,  1.93,  1.88,  1.81,  1.69,  1.63,  1.55,  1.42,  1.36,  1.26,  1.16,  1.12,  1.09,   1.03,   0.981,  0.933,  0.884,  0.858,  0.830,  0.761,  0.807,  0.675,  0.618},
            {3.32,  2.83,  2.59,  2.49,  2.35,  2.12,  2.03,  1.90,  1.66,  1.55,  1.40,  1.23,  1.17,  1.12,   1.03,   0.959,  0.890,  0.822,  0.786,  0.748,  0.656,  0.588,  0.548,  0.484},
            {4.63,  3.80,  3.42,  3.24,  3.01,  2.65,  2.48,  3.26,  1.91,  1.75,  1.52,  1.29,  1.21,  1.14,   1.03,   0.980,  0.843,  0.758,  0.715,  0.669,  0.563,  0.487,  0.443,  0.369},
            {6.24,  4.94,  4.35,  4.09,  3.74,  3.21,  2.97,  2.66,  2.17,  1.95,  1.65,  1.34,  1.24,  1.15,   1.01,   0.989,  0.794,  0.696,  0.647,  0.596,  0.479,  0.400,  0.355,  0.283},
            {8.14,  6.26,  5.39,  5.04,  4.56,  3.82,  3.50,  3.07,  2.43,  2.14,  1.76,  1.38,  1.26,  1.16,   0.995,  0.862,  0.745,  0.636,  0.583,  0.528,  0.406,  0.326,  0.282,  0.213},
            {10.3,  7.70,  6.58,  6.08,  5.44,  4.48,  4.06,  3.50,  2.69,  2.34,  1.87,  1.42,  1.28,  1.16,   0.972,  0.823,  0.695,  0.578,  0.522,  0.465,  0.341,  0.263,  0.221,  0.158},
            {12.7,  9.30,  7.85,  7.21,  6.38,  5.17,  4.64,  3.96,  2.95,  2.52,  1.97,  1.45,  1.28,  1.15,   0.946,  0.783,  0.646,  0.523,  0.465,  0.407,  0.284,  0.210,  0.171,  0.116},
            {15.4,  11.0,  9.19,  8.40,  7.37,  5.88,  5.24,  4.41,  3.21,  2.70,  2.06,  1.47,  1.29,  1.14,   0.915,  0.741,  0.597,  0.471,  0.412,  0.354,  0.235,  0.166,  0.131,  0.083},
            {18.2,  12.8,  10.6,  9.65,  8.41,  6.61,  5.84,  4.87,  3.47,  2.88,  2.15,  1.49,  1.29,  1.13,   0.883,  0.699,  0.549,  0.422,  0.363,  0.306,  0.193,  0.129,  0.099,  0.058},
            {21.3,  14.8,  12.1,  11.0,  9.49,  7.37,  6.47,  5.33,  3.73,  3.05,  2.23,  1.50,  1.28,  1.11,   0.848,  0.656,  0.503,  0.375,  0.318,  0.263,  0.158,  0.100,  0.073,  0.04},
            {24.5,  16.8,  13.7,  12.4,  10.6,  8.15,  7.40,  5.79,  3.98,  3.22,  2.30,  1.50,  1.27,  1.08,   0.812,  0.614,  0.459,  0.330,  0.277,  0.224,  0.126,  0.076,  0.054,  0.027},
            {27.9,  19.0,  15.3,  13.8,  11.8,  8.94,  7.75,  6.26,  4.20,  3.37,  2.36,  1.50,  1.25,  1.06,   0.775,  0.572,  0.417,  0.293,  0.239,  0.190,  0.100,  0.057,  0.038,  0.017},
            {31.5,  21.2,  17.0,  15.2,  13.0,  9.75,  8.41,  6.74,  4.44,  3.52,  2.42,  1.49,  1.23,  1.03,   0.736,  0.531,  0.377,  0.257,  0.206,  0.160,  0.078,  0.042,  0.027,  0.011},
            {35.3,  23.5,  18.8,  16.8,  14.2,  10.6,  9.07,  7.21,  4.67,  3.66,  2.47,  1.48,  1.20,  0.997,  0.697,  0.491,  0.339,  0.224,  0.176,  0.133,  0.061,  0.030,  0.018,  0.007},
            {35.3,  23.5,  18.8,  16.8,  14.2,  10.6,  9.07,  7.21,  4.67,  3.66,  2.47,  1.48,  1.20,  0.997,  0.697,  0.491,  0.339,  0.224,  0.176,  0.133,  0.061,  0.030,  0.018,  0.007},
            {39.3,  25.9,  20.6,  18.3,  15.4,  11.4,  9.74,  7.68,  4.89,  3.80,  2.51,  1.46,  1.18,  0.964,  0.659,  0.452,  0.304,  0.194,  0.149,  0.110,  0.047,  0.022,  0.012,  0.004},
            {43.4,  28.4,  22.4,  19.9,  16.7,  12.3,  10.4,  8.14,  5.10,  3.92,  2.55,  1.45,  1.15,  0.929,  0.620,  0.415,  0.271,  0.166,  0.125,  0.090,  0.035,  0.015,  0.008,  0.0002},
            {47.8,  31.0,  24.3,  21.5,  18.0,  13.1,  11.1,  8.61,  5.31,  4.04,  2.58,  1.42,  1.12,  0.892,  0.581,  0.379,  0.240,  0.142,  0.105,  0.073,  0.026,  0.010,  0.005,  0.001},
            {52.5,  33.7,  26.3,  23.3,  19.4,  14.0,  11.8,  9.07,  5.51,  4.15,  2.60,  1.40,  1.08,  0.855,  0.511,  0.345,  0.212,  0.121,  0.087,  0.059,  0.017,  0.007,  0.003,  0.001},
            {57.4,  36.5,  28.4,  25.1,  20.8,  14.8,  12.4,  9.53,  5.70,  4.26,  2.62,  1.37,  1.05,  0.818,  0.507,  0.313,  0.186,  0.102,  0.071,  0.047,  0.014,  0.004,  0.002,  0.0004}
        };

    Double[][] D35 = {
            {1.56,  1.48,  1.43,  1.40,  1.37,  1.31,  1.29,  1.25,  1.20,  1.17,  1.13,  1.08,  1.07,  1.05,   1.02,   0.997,  0.972,  0.945,  0.931,  0.915,  0.877,  0.84,  0.827,  0.788},
            {2.39,  2.12,  1.98,  1.93,  1.84,  1.71,  1.65,  1.57,  1.43,  1.36,  1.26,  1.16,  1.12,  1.08,   1.03,   0.978,  0.931,  0.883,  0.858,  0.831,  0.764,  0.713,  0.683,  0.629},
            {3.59,  2.99,  2.71,  2.58,  2.43,  2.16,  2.07,  1.93,  1.68,  1.56,  1.39,  1.22,  1.16,  1.11,   1.03,   0.954,  0.887,  0.821,  0.787,  0.751,  0.664,  0.6,  0.563,  0.499},
            {5.23,  4.12,  3.63,  3.41,  3.14,  2.75,  2.55,  2.31,  1.93,  1.75,  1.52,  1.28,  1.20,  1.13,   1.02,   0.925,  0.841,  0.760,  0.719,  0.676,  0.576,  0.504,  0.463,  0.396},
            {7.26,  5.46,  4.64,  4.33,  3.93,  3.36,  3.06,  2.71,  2.18,  1.94,  1.63,  1.32,  1.22,  1.14,   1.00,   0.892,  0.793,  0.700,  0.654,  0.606,  0.490,  0.422,  0.380,  0.312},
            {9.65,  6.94,  5.85,  5.38,  4.79,  4.00,  3.62,  3.13,  2.43,  2.13,  1.74,  1.36,  1.24,  1.14,   0.984,  0.856,  0.745,  0.636,  0.593,  0.541,  0.427,  0.351,  0.309,  0.244},
            {12.3,  8.60,  7.17,  6.54,  5.75,  4.60,  4.18,  3.56,  2.68,  2.31,  1.84,  1.39,  1.24,  1.14,   0.960,  0.819,  0.698,  0.588,  0.536,  0.482,  0.366,  0.29,  0.249,  0.186},
            {15.2,  10.4,  8.56,  7.77,  6.77,  5.36,  4.76,  4.00,  2.94,  2.49,  1.93,  1.42,  1.26,  1.13,   0.935,  0.781,  0.652,  0.537,  0.482,  0.427,  0.311,  0.239,  0.201,  0.145},
            {18.2,  12.3,  10.0,  9.04,  7.82,  6.08,  5.35,  4.45,  3.19,  2.66,  2.02,  1.44,  1.26,  1.12,   0.907,  0.742,  0.606,  0.488,  0.432,  0.377,  0.263,  0.195,  0.160,  0.110},
            {21.6,  14.4,  11.6,  10.4,  8.90,  6.83,  5.97,  4.90,  3.43,  2.83,  2.10,  1.45,  1.26,  1.11,   0.877,  0.703,  0.662,  0.442,  0.386,  0.332,  0.221,  0.158,  0.126,  0.082},
            {25.1,  16.5,  13.2,  11.8,  10.0,  7.59,  6.59,  5.36,  3.67,  2.98,  2.17,  1.46,  1.25,  1.09,   0.845,  0.664,  0.520,  0.398,  0.343,  0.290,  0.185,  0.127,  0.098,  0.061},
            {28.9,  18.8,  14.8,  13.2,  11.2,  8.37,  7.22,  5.80,  3.90,  3.14,  2.23,  1.46,  1.24,  1.07,   0.812,  0.635,  0.479,  0.358,  0.304,  0.253,  0.145,  0.101,  0.07,  0.044},
            {32.8,  21.1,  16.6,  14.7,  12.4,  9.17,  7.84,  6.26,  4.12,  3.28,  2.29,  1.46,  1.22,  1.04,   0.777,  0.587,  0.410,  0.321,  0.268,  0.219,  0.127,  0.08,  0.058,  0.032},
            {36.9,  23.5,  18.4,  16.3,  13.6,  9.97,  8.50,  6.71,  4.34,  3.42,  2.34,  1.45,  1.21,  1.02,   0.743,  0.549,  0.403,  0.286,  0.236,  0.189,  0.104,  0.062,  0.044,  0.022},
            {41.2,  26.0,  22.2,  17.8,  14.9,  10.8,  9.14,  7.16,  4.55,  3.55,  2.38,  1.44,  1.18,  0.989,  0.708,  0.513,  0.368,  0.254,  0.206,  0.163,  0.085,  0.048,  0.033,  0.013},
            {45.7,  28.6,  22.1,  19.4,  16.1,  11.6,  9.79,  7.61,  4.75,  3.67,  2.42,  1.43,  1.16,  0.960,  0.673,  0.477,  0.335,  0.225,  0.180,  0.139,  0.069,  0.037,  0.024,  0.011},
            {50.3,  31.3,  24.1,  21.1,  17.4,  12.4,  10.4,  8.05,  4.95,  3.78,  2.46,  1.41,  1.14,  0.929,  0.638,  0.443,  0.303,  0.199,  0.156,  0.118,  0.055,  0.028,  0.018,  0.007},
            {55.1,  34.1,  26.1,  22.8,  18.8,  13.3,  11.1,  8.49,  5.14,  3.89,  2.48,  1.39,  1.11,  0.897,  0.604,  0.410,  0.274,  0.175,  0.135,  0.100,  0.044,  0.021,  0.013,  0.005},
            {60.1,  36.8,  28.1,  24.5,  20.1,  14.1,  11.8,  9.92,  5.32,  3.99,  2.51,  1.37,  1.08,  0.864,  0.570,  0.379,  0.247,  0.153,  0.116,  0.084,  0.035,  0.016,  0.009,  0.003},
            {65.4,  39.9,  30.2,  26.2,  21.4,  15.0,  12.4,  9.36,  5.50,  4.08,  2.52,  1.35,  1.05,  0.831,  0.537,  0.350,  0.222,  0.134,  0.099,  0.070,  0.027,  0.011,  0.006,  0.002}
        };

    Double[][] D40 = {
            {1.59,   1.50,  1.44,  1.41,  1.38,  1.32,  1.29,  1.25,  1.20,  1.17,  1.13,  1.08,  1.07,  1.05,   1.02,   0.997,  0.972,  0.945,  0.931,  0.915,  0.877,  0.846,  0.829,  0.790},
            {2.49,   2.18,  2.04,  1.97,  1.88,  1.74,  1.67,  1.58,  1.44,  1.36,  1.26,  1.15,  1.12,  1.08,   1.02,   0.976,  0.929,  0.883,  0.858,  0.832,  0.769,  0.719,  0.690,  0.638},
            {3.90,   3.17,  2.86,  2.72,  2.53,  2.24,  2.12,  1.94,  1.68,  1.56,  1.39,  1.22,  1.16,  1.11,   1.02,   0.950,  0.885,  0.821,  0.788,  0.754,  0.671,  0.611,  0.576,  0.516},
            {5.80,   4.43,  3.86,  3.61,  3.29,  2.82,  2.61,  2.34,  1.93,  1.75,  1.51,  1.27,  1.19,  1.12,   1.01,   0.920,  0.839,  0.761,  0.722,  0.681,  0.586,  0.519,  0.481,  0.417},
            {8.15,   5.91,  5.02,  4.63,  4.15,  3.44,  3.13,  2.75,  2.18,  1.94,  1.62,  1.31,  1.21,  1.13,   1.00,   0.888,  0.793,  0.704,  0.660,  0.614,  0.511,  0.440,  0.400,  0.336},
            {10.90,  7.58,  6.30,  5.76,  5.07,  4.09,  3.68,  3.17,  2.43,  2.12,  1.72,  1.34,  1.23,  1.13,   0.976,  0.853,  0.747,  0.649,  0.601,  0.553,  0.444,  0.372,  0.332,  0.269},
            {13.90,  9.41,  7.67,  6.96,  6.05,  4.79,  4.26,  3.59,  2.68,  2.29,  1.81,  1.37,  1.24,  1.13,   0.954,  0.818,  0.702,  0.597,  0.546,  0.496,  0.384,  0.312,  0.274,  0.274},
            {17.20,  11.4,  9.14,  8.22,  7.08,  5.50,  4.85,  4.03,  2.92,  2.46,  1.90,  1.40,  1.24,  1.12,   0.929,  0.781,  0.658,  0.548,  0.495,  0.443,  0.331,  0.261,  0.224,  0.168},
            {20.80,  13.4,  10.7,  9.56,  8.15,  6.22,  5.43,  4.47,  3.16,  2.62,  1.98,  1.41,  1.24,  1.11,   0.902,  0.744,  0.614,  0.501,  0.448,  0.359,  0.284,  0.217,  0.182,  0.132},
            {24.60,  15.5,  12.3,  11.0,  9.26,  6.96,  6.03,  4.91,  3.39,  2.78,  2.05,  1.42,  1.34,  1.10,   0.873,  0.707,  0.572,  0.457,  0.403,  0.351,  0.243,  0.180,  0.147,  0.102},
            {28.60,  17.9,  14.0,  12.4,  10.4,  7.73,  6.65,  5.34,  3.62,  2.93,  2.12,  1.43,  1.23,  1.08,   0.843,  0.670,  0.532,  0.415,  0.332,  0.311,  0.207,  0.148,  0.119,  0.078},
            {32.80,  20.3,  15.8,  13.9,  11.6,  8.53,  7.29,  5.79,  3.83,  3.07,  2.18,  1.44,  1.22,  1.06,   0.812,  0.634,  0.494,  0.377,  0.325,  0.274,  0.176,  0.121,  0.095,  0.060},
            {37.20,  22.8,  17.6,  15.4,  12.8,  9.31,  7.91,  6.22,  4.04,  3.21,  2.24,  1.43,  1.21,  1.04,   0.781,  0.598,  0.457,  0.341,  0.290,  0.242,  0.148,  0.098,  0.075,  0.045},
            {41.80,  25.4,  19.4,  18.6,  15.3,  10.9,  9.16,  7.09,  4.45,  3.46,  2.32,  1.42,  1.17,  0.99,   0.716,  0.529,  0.388,  0.277,  0.230,  0.165,  0.104,  0.064,  0.045,  0.025},
            {46.60,  28.0,  21.3,  18.6,  15.3,  10.9,  9.16,  7.09,  4.45,  3.46,  2.32,  1.42,  1.17,  0.985,  0.716,  0.529,  0.388,  0.277,  0.230,  0.165,  0.104,  0.064,  0.046,  0.025},
            {61.50,  30.8,  23.3,  20.3,  16.6,  11.7,  9.79,  7.52,  4.64,  3.57,  2.36,  1.41,  1.15,  0.958,  0.684,  0.495,  0.356,  0.248,  0.203,  0.162,  0.087,  0.051,  0.036,  0.018},
            {66.60,  33.6,  25.3,  21.9,  17.9,  12.5,  10.4,  7.95,  4.83,  3.68,  2.39,  1.39,  1.13,  0.929,  0.652,  0.464,  0.327,  0.223,  0.179,  0.140,  0.072,  0.041,  0.028,  0.013},
            {61.80,  36.5,  27.3,  23.7,  19.2,  13.3,  11.0,  8.37,  5.01,  3.78,  2.42,  1.38,  1.10,  0.900,  0.620,  0.433,  0.299,  0.199,  0.158,  0.122,  0.060,  0.032,  0.021,  0.009},
            {67.20,  39.4,  29.4,  25.4,  20.6,  14.2,  11.7,  8.78,  5.18,  3.87,  2.44,  1.36,  1.08,  0.871,  0.588,  0.403,  0.273,  0.177,  0.014,  0.105,  0.049,  0.025,  0.016,  0.006},
            {72.80,  42.4,  31.5,  27.2,  21.9,  15.0,  12.3,  9.19,  5.34,  3.96,  2.45,  1.33,  1.05,  0.841,  0.588,  0.375,  0.249,  0.157,  0.121,  0.090,  0.040,  0.019,  0.012,  0.004}
        };

    private ThreeParametrGammaDistributionCoefficients(){

    }

    //Делаем этот класс синглтоном
    public static ThreeParametrGammaDistributionCoefficients getInstance(){
        if(instance == null){
            instance = new ThreeParametrGammaDistributionCoefficients();
        }

        return instance;
    }

    private void initDistribution(){
        Map<Double, Double[][]> TemporaryDistribution = new HashMap<Double, Double[][]>();

        TemporaryDistribution.put(1.0, D10);
        TemporaryDistribution.put(1.5, D15);
        TemporaryDistribution.put(2.0, D20);
        TemporaryDistribution.put(2.5, D25);
        TemporaryDistribution.put(3.0, D30);
        TemporaryDistribution.put(3.5, D35);
        TemporaryDistribution.put(4.0, D40);

        Map<Double, Double> line = new HashMap<Double, Double>();
        Map<Double, Map<Double, Double>> mp = new HashMap<Double, Map<Double, Double>>();

        for(int i=0; i<CsCvValues.length; i++){
            for(int j=0; j<CvValues.length; j++){
                for(int f=0; f<PValues.length; f++){
                    line.put(PValues[f], TemporaryDistribution.get(CsCvValues[i])[j][f]);
                }
                mp.put(CvValues[j], line);
                line = new HashMap<Double, Double>();
            }
            this.Distribution.put(CsCvValues[i], mp);
            mp = new HashMap<Double, Map<Double, Double>>();
        }
    }

    //Возвращаем распределение
    public  Map<Double, Map<Double, Map<Double, Double>>> getDistribution(){
        if(this.Distribution.size() == 0){
            initDistribution();
        }
        //System.out.println("Coeff: " + Distribution);
        return Distribution;
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


}
