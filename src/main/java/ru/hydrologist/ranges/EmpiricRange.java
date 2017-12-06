package ru.hydrologist.ranges;

import ru.hydrologist.coefficients.Coefficient_a;
import ru.hydrologist.coefficients.Coefficient_b;
import ru.hydrologist.utils.DataConverter;
import ru.hydrologist.utils.Interpolator;

import java.util.*;

/**
 *  Эмпирический ряд наблюдений
 */

public class EmpiricRange implements EmpiricRangable{
    private int accuracyRank = 6;           //Число знаков после запятой, до которых будем округлять некоторые значения (например, вероятности)
    private ArrayList<Double> rowRange; 	//сырой ряд наблюдений
    private Map<Double, Double> range; 	    //ряд наблюдений, где ключ - вероятность, значение - собственно само значение (например, расхода воды)
    private ArrayList<Double> rangedRange; 	//отсортированный ряд наблюдений
    private int rangeLength; 				//длина ряда
    private Double rangeAverage; 			//среднее значение ряда
    private ArrayList<Double> Probabilities;//вероятности
    private ArrayList<Double> K; 			//коэффициенты К
    private Double offsetCv;				//смещенный коэффициент вариации
    private Double offsetCs;				//смещенный коэффцицинет ассиметрии
    private Double offsetCsCv;				//смещенное соотношение Cs/Cv
    private Double offsetR; 				//Смещенный коэффициент автокорреляции
    private Double y;						//какой-то коэффцициент
    private Double r; 						//коэффициент автокорреляции
    private Double Cv;						//коэффициент вариации
    private Double Cs;						//коэффцицинет ассиметрии
    private Double CsCv;					//соотношение Cs/Cv

    //Конструктор ряда. Принимает на вход знечения и рассчитывает все его параметры
    public EmpiricRange(ArrayList<Double> rowRange){
        this.rowRange = rowRange;
        this.rangedRange = sortRange(rowRange);
        this.rangeLength = calcRangeLength(rowRange);
        this.rangeAverage = calcRangeAverage(rowRange);
        this.Probabilities = calcProbabilities(rangeLength);
        this.range = setRange(rangedRange, Probabilities);
        this.K = calcK(rangeLength, rangeAverage, rangedRange);
        this.offsetCv = calcOffsetCv(rangeLength, K);
        this.offsetCs = calcOffsetCs(rangeLength, K, offsetCv);
        this.offsetCsCv = calcOffsetCsCv(offsetCs, offsetCv);
        this.offsetR = calcOffsetR(rowRange, rangeLength);
        this.r = calcR(offsetR, rangeLength);
        calcCvAndCs(offsetCsCv, offsetCv, offsetCs, r, rangeLength);
        calcCsCv();

    }

    //Сортировка ряда
    private ArrayList<Double> sortRange(ArrayList<Double> range){
        ArrayList<Double> rangedRange = (ArrayList<Double>) range.clone();

        Collections.sort(rangedRange, Collections.reverseOrder());
        return rangedRange;
    }

    //Заполяем range
    private Map<Double, Double> setRange(ArrayList<Double> rangedRange, ArrayList<Double> Probabilities){
        Map<Double, Double> range = new HashMap<Double, Double>();

        int len = rangedRange.size();
        for(int i=0; i<len; i++){
            range.put(DataConverter.DoubleRound(Probabilities.get(i)*100, accuracyRank), rangedRange.get(i));
        }
        return range;
    }

    //Вычисление длины ряда
    private int calcRangeLength(ArrayList<Double> range){
        return range.size();
    }

    //Вычисление среднего значения ряда
    private double calcRangeAverage(ArrayList<Double> range){
        int n = this.rangeLength;
        double sum = 0;
        double average = 0;
        for(int i=0; i<n; i++){
            sum = sum + range.get(i);
        }
        average = sum/n;

        return average;
    }

    //Вычисление коэффициентов К
    private ArrayList<Double> calcK(int rangeLength, double rangeAverage, ArrayList<Double> rangedRange){
        ArrayList<Double> k = new ArrayList<Double>();
        for(int m=0; m<rangeLength; m++){
            if(rangeAverage == 0){
                k.add(m, 0.0);
            }else{
                k.add(m, rangedRange.get(m)/rangeAverage);
            }
        }

        return k;
    }

    //Вычисление вероятностей ряда
    private ArrayList<Double> calcProbabilities(int rangeLength){
        Probabilities = new ArrayList<Double>();
        for(int m = 0; m < rangeLength; m++){
            Probabilities.add((double) ((m+1.0)/(rangeLength+1.0)));
        }
        return Probabilities;
    }

    //Вычисление смещенной оценки коэффициента вариации
    private Double calcOffsetCv(int rangeLength, ArrayList<Double> k){
        Double sumX = 0.0;
        Double x = 0.0;
        for(int i=0; i<rangeLength; i++){
            x = Math.pow((k.get(i)-1.0), 2);
            sumX += x;
        }
        return Math.pow((sumX/(rangeLength - 1)), 0.5);
    }

    //Вычисление смещенного коэффициента ассиметрии
    private Double calcOffsetCs(int rangeLength, ArrayList<Double> k, Double offsetCv){
        Double sumX = 0.0;
        Double x = 0.0;
        Double offCs = 0.0;
        for(int i=0; i<rangeLength; i++){ //
            x = Math.pow((k.get(i)-1), 3);
            sumX += x;
        }
        if(offsetCv == 0 || rangeLength == 1 || rangeLength == 2){
            offCs = rangeLength*sumX; //
        }else{
            if(rangeLength<=2){
                offCs = rangeLength*sumX/(Math.pow(offsetCv, 3)*(rangeLength-1)*(rangeLength-2));
            }else{
                offCs = rangeLength*sumX/(Math.pow(offsetCv,3)*(rangeLength-1)*(rangeLength-2));
            }
        }
        return offCs;
    }

    //Вычисление смещенного соотношения Cs/Cv
    private Double calcOffsetCsCv(Double offsetCs, Double offsetCv){
        Double offCsCv;
        if(offsetCv == 0){
            offCsCv = 0.0;
        }else{
            offCsCv = offsetCs/offsetCv;
        }
        return offCsCv;
    }

    //Вычисление смещенного коэффициента автокорреляции
    private Double calcOffsetR(ArrayList<Double> range, int rangeLength){
        Double rangeFirstHalfAVG = 0.0;
        Double rangeSecondHalfAVG = 0.0;
        Double sumRangeFirstHalf = 0.0;
        Double sumRangeSecondHalf = 0.0;

        for(int i=1; i<rangeLength; i++){                               //Считаем сумму первой половины ряда
            sumRangeFirstHalf = sumRangeFirstHalf + range.get(i);
        }
        rangeFirstHalfAVG = sumRangeFirstHalf/(rangeLength-1);

        for(int i=0; i<rangeLength-1; i++){                             //Считаем сумму второй половины ряда
            sumRangeSecondHalf = sumRangeSecondHalf + range.get(i);
        }
        rangeSecondHalfAVG = sumRangeSecondHalf/(rangeLength-1);

        Double x = 0.0;
        Double y1 = 0.0;
        Double y2 = 0.0;
        for(int i=1; i<rangeLength; i++){
            x = x + (range.get(i)- rangeFirstHalfAVG)*(range.get(i-1) - rangeSecondHalfAVG);
            y1 = y1 + Math.pow((range.get(i)- rangeFirstHalfAVG), 2);
        }
        for(int i=0; i<rangeLength-1; i++){
            y2 = y2 + Math.pow((range.get(i)- rangeSecondHalfAVG), 2);
        }
        this.y = y1*y2;

        if(this.y == 0){
            return 1.0;
        }else{
            return x/(Math.pow(this.y, 0.5));
        }
    }

    //Вычисление коэффициента автокорреляции
    private Double calcR(Double offsetR, int rangeLength){
        Double r;
        if(y == 0){
            r = 1.0;
        }else{
            r = -0.01 + 0.98*offsetR - 0.06*Math.pow(offsetR, 2) + (1.66 + 6.46*offsetR + 5.69*Math.pow(offsetR, 2))*1/rangeLength;
        }
        return r;
    }

    //Вычисление значений коэффициентов вариации и ассиметрии
    private void calcCvAndCs(Double offsetCsCv, Double offsetCv, Double offsetCs, Double r, int rangeLength){

        double[] a = this.calcA(r, offsetCsCv);
        double[] b = this.calcB(r);

        double n = (double)rangeLength;

        this.Cv = (a[1] + 1/n*a[2]) + (a[3] + 1/n*a[4])*offsetCv + (a[5] + 1/n*a[6])*Math.pow(offsetCv, 2);
        this.Cs = (b[1] + 1/n*b[2]) + (b[3] + 1/n*b[4])*offsetCs + (b[5] + 1/n*b[6])*Math.pow(offsetCs, 2);

    }

    //Вычисляем коэффициент а
    public double[] calcA(Double rCoefficient, double offsetCsCvVal){

        double[][][] aCoeff = Coefficient_a.getaCoefficient();
        double[] a = new double[7];
        double[] a1 = new double[7];
        double[] a2 = new double[7];
        ArrayList<Double> CsCvValues = new ArrayList<Double>(Arrays.asList(Coefficient_a.getCsCvValues()));
        ArrayList<Double> rValues = new ArrayList<Double>(Arrays.asList(Coefficient_a.getrValues()));

        Double[] CsCvBetween = Interpolator.findBetweenWhich(offsetCsCvVal, CsCvValues);
        Double[] rBetween = Interpolator.findBetweenWhich(rCoefficient, rValues);

        int CsCvBetweenIndex0 = Coefficient_a.getCsCvIndex(CsCvBetween[0]);
        int CsCvBetweenIndex1 = Coefficient_a.getCsCvIndex(CsCvBetween[1]);
        int rBewtweenIndex0 = Coefficient_a.getRValuesIndex(rBetween[0]);
        int rBewtweenIndex1 = Coefficient_a.getRValuesIndex(rBetween[1]);

        int len = a.length;

        for(int i=1; i<len; i++){//расчёт коэффициентов а
            a1[i-1] = Interpolator.interpolation(r, rBetween[0], rBetween[1], aCoeff[CsCvBetweenIndex0][rBewtweenIndex0][i-1], aCoeff[CsCvBetweenIndex0][rBewtweenIndex1][i-1]);
            a2[i-1] = Interpolator.interpolation(r, rBetween[0], rBetween[1], aCoeff[CsCvBetweenIndex1][rBewtweenIndex0][i-1], aCoeff[CsCvBetweenIndex1][rBewtweenIndex1][i-1]);
            a[i] = Interpolator.interpolation(offsetCsCvVal, CsCvBetween[0], CsCvBetween[1], a1[i-1], a2[i-1]);
        }

        return a;
    }

    //Вычисляем коэффициент b
    private double[] calcB(Double r){
        double[][] bCoeff = Coefficient_b.getbCoefficient();
        double[] b = new double[7];

        ArrayList<Double> rValues = new ArrayList<Double>(Arrays.asList(Coefficient_a.getrValues()));
        Double[] rBetween = Interpolator.findBetweenWhich(r, rValues);
        int rBewtweenIndex0 = Coefficient_a.getRValuesIndex(rBetween[0]);
        int rBewtweenIndex1 = Coefficient_a.getRValuesIndex(rBetween[1]);

        int len = b.length;

        for(int i=1; i<len; i++) {//расчёт коэффициентов а
            b[i] = Interpolator.interpolation(r, rBetween[0], rBetween[1], bCoeff[rBewtweenIndex0][i - 1], bCoeff[rBewtweenIndex1][i - 1]);
        }

        return b;
    }

    //Вычисляем не соотношение Cs/Cv
    private void calcCsCv(){
        CsCv = Cs/Cv;
    }

    //Возвращаем параметры ряда в виде коллекции
    public Hashtable<String, Double> getRangeParams(){
        Hashtable<String, Double> rangeParams = new Hashtable<String, Double>();
        rangeParams.put("Cs", this.Cs);
        rangeParams.put("Cv", this.Cv);
        rangeParams.put("CsCv", this.CsCv);
        rangeParams.put("r", this.r);
        return rangeParams;
    }

    public ArrayList<Double> getRowRange() {
        return rowRange;
    }

    public ArrayList<Double> getRangedRange() {
        return rangedRange;
    }

    public int getRangeLength() {
        return rangeLength;
    }

    public Double getRangeAverage() {
        return rangeAverage;
    }

    public ArrayList<Double> getProbabilities() {
        return Probabilities;
    }

    public ArrayList<Double> getK() {
        return K;
    }

    public Double getOffsetCv() {
        return offsetCv;
    }

    public Double getOffsetCs() {
        return offsetCs;
    }

    public Double getCv() {
        return Cv;
    }

    public Double getCs() {
        return Cs;
    }

    public Double getOffsetCsCv() {
        return offsetCsCv;
    }

    public Double getCsCv() {
        return CsCv;
    }

    public Double getOffsetR() {
        return offsetR;
    }

    public Double getR() {
        return r;
    }

    public Map<Double, Double> getRange() {
        return range;
    }

    public Double getRangeMaximum(){
        return Collections.max(rangedRange);
    }

    public Double getRangeMinimum(){
        return Collections.min(rangedRange);
    }

}
