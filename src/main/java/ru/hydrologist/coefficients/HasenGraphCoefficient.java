package ru.hydrologist.coefficients;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedorovskiy on 13.03.2017.
 */
public class HasenGraphCoefficient {
    private static HasenGraphCoefficient instance;

    Double[] HasenGraphPCoefficientsLong = {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.1, 0.2, 0.3,
            0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0,
            11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 22.0, 24.0, 26.0, 28.0, 30.0, 32.0, 34.0,
            36.0, 38.0, 40.0, 42.0, 44.0, 46.0, 48.0, 50.0, 52.0, 54.0, 56.0, 58.0, 60.0, 62.0, 64.0, 66.0, 68.0,
            70.0, 72.0, 74.0, 76.0, 78.0, 80.0, 81.0, 82.0, 83.0, 84.0, 85.0, 86.0, 87.0, 88.0, 89.0, 90.0, 91.0,
            92.0, 93.0, 94.0, 95.0, 5.5, 96.0, 96.5, 97.0, 97.5, 98.0, 98.5, 99.0, 99.1, 99.2, 99.3, 99.4, 99.5,
            99.6, 99.7, 99.80, 99.9, 99.91, 99.92, 99.93, 99.94, 99.95, 99.96, 99.97, 99.98, 99.99};

    Double[] HasenGraphDistanceCoefficientsLong = {0.0, 7.7, 12.3, 15.7, 18.4, 20.7, 22.6, 24.2, 25.7, 27.0, 36.2,
            41.8, 45.9, 49.2, 51.9, 54.2, 56.3, 58.2, 59.9, 66.6, 71.6, 75.6, 79.0, 82.0, 84.6, 87.1, 89.2, 93.1,
            96.4, 99.5, 102.3, 104.8, 107.2, 109.4, 111.5, 113.5, 115.3, 117.1, 118.9, 120.5, 122.2, 123.7, 126.7,
            129.5, 132.2, 134.8, 137.4, 139.8, 142.2, 144.5, 146.8, 149.0, 151.2, 153.4, 155.6, 157.7, 159.9, 162.1,
            164.2, 166.4, 168.6, 170.8, 173.0, 175.3, 177.6, 180.0, 182.4, 185.0, 187.6, 190.3, 193.1, 196.1, 197.6,
            199.3, 200.9, 202.7, 204.5, 206.3, 208.3, 210.4, 212.6, 215.0, 217.5, 220.3, 223.4, 226.7, 230.6, 232.7,
            235.2, 237.8, 240.8, 244.2, 248.2, 253.2, 259.9, 261.6, 263.5, 265.6, 267.9, 270.6, 273.9, 278.0, 283.6,
            292.8, 294.1, 295.6, 297.2, 299.1, 301.4, 304.1, 307.5, 312.1, 319.8};


    Double[] HasenGraphPCoefficientsShort = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.50, 2.0, 2.50, 3.0,
            3.50, 4.0, 4.50, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0,
            20.0, 22.0, 24.0, 26.0, 28.0, 30.0, 32.0, 34.0, 36.0, 38.0, 40.0, 42.0, 44.0, 46.0, 48.0, 50.0, 52.0,
            54.0, 56.0, 58.0, 60.0, 62.0, 64.0, 66.0, 68.0, 70.0, 72.0, 74.0, 76.0, 78.0, 80.0, 81.0, 82.0, 83.0,
            84.0, 85.0, 86.0, 87.0, 88.0, 89.0, 90.0, 91.0, 92.0, 93.0, 94.0, 95.0, 95.5, 96.0, 96.5, 97.0, 97.5,
            98.0, 98.5, 99.0, 99.1, 99.2, 99.3, 99.4, 99.5, 99.6, 99.7, 99.80, 99.9};

    Double[] HasenGraphDistanceCoefficientsShort = {0.0, 11.06907449, 17.80677201, 22.73972912, 26.71015801,
            29.95869074, 32.72595937, 35.25259594, 37.53860045, 39.58397291, 47.64514673, 53.66094808, 58.47358916,
            62.56433409, 66.1738149, 69.3020316, 72.30993228, 74.83656885, 79.52889391, 83.4993228, 87.22911964,
            90.5979684, 93.60586907, 96.49345372, 99.14040632, 101.6670429, 104.0733634, 106.2390519, 108.4047404,
            110.5704289, 112.4954853, 114.5408578, 116.3455982, 119.955079, 123.3239278, 126.5724605, 129.7006772,
            132.8288939, 135.7164786, 138.6040632, 141.3713318, 144.1386005, 146.785553, 149.4325056, 152.0794582,
            154.7264108, 157.2530474, 159.9, 162.5469526, 165.0735892, 167.7205418, 170.3674944, 173.014447, 175.6613995,
            178.4286682, 181.1959368, 184.0835214, 186.9711061, 190.0993228, 193.2275395, 196.4760722, 199.844921,
            203.4544018, 205.2591422, 207.3045147, 209.2295711, 211.3952596, 213.5609481, 215.7266366, 218.1329571,
            220.6595937, 223.3065463, 226.1941309, 229.2020316, 232.5708804, 236.3006772, 240.2711061, 244.9634312,
            247.4900677, 250.4979684, 253.6261851, 257.2356659, 261.3264108, 266.1390519, 272.1548533, 280.2160271,
            282.2613995, 284.5474041, 287.0740406, 289.8413093, 293.089842, 297.0602709, 301.993228, 308.7309255, 319.8};

    private double totalDistanceLength = 319.8;

    private Double[] printedValues = {0.01, 0.1, 1.0, 5.0, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 95.0, 99.0, 99.9, 99.99};
    private double maxValue = 100.0;

    Map<Double, Double> HasenGraphValuesLong = new HashMap<Double, Double>();
    Map<Double, Double> HasenGraphValuesShort = new HashMap<Double, Double>();

    //Делаем этот класс синглтоном
    public static HasenGraphCoefficient getInstance(){
        if(instance == null){
            instance = new HasenGraphCoefficient();
        }

        return instance;
    }

    //Возвращаем распределение
    public  Map<Double, Double> getLongCoefficients(){
        if(this.HasenGraphValuesLong.size() == 0) {
            initDistribution();
        }
        return HasenGraphValuesLong;
    }

    //Возвращаем распределение
    public  Map<Double, Double> getShortCoefficients(){
        if(this.HasenGraphValuesShort.size() == 0) {
            initDistribution();
        }
        return HasenGraphValuesShort;
    }

    private void initDistribution(){
        if(HasenGraphPCoefficientsLong.length != HasenGraphDistanceCoefficientsLong.length || HasenGraphPCoefficientsShort.length != HasenGraphDistanceCoefficientsShort.length){
            return;
        }
        int longLength = HasenGraphPCoefficientsLong.length;
        int shortLength = HasenGraphPCoefficientsShort.length;

        for(int i=0; i<longLength; i++){
            HasenGraphValuesLong.put(HasenGraphPCoefficientsLong[i], HasenGraphDistanceCoefficientsLong[i]);
        }
        for(int j=0; j<shortLength; j++){
            HasenGraphValuesShort.put(HasenGraphPCoefficientsShort[j], HasenGraphDistanceCoefficientsShort[j]);
        }
    }

    public double getTotalDistanceLength(){
        return totalDistanceLength;
    }

    public Double[] getPrintedValues(){
        return printedValues;
    }

    public double getMaxValue(){
        return maxValue;
    }

}
