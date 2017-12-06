package ru.hydrologist.calculations;

import ru.hydrologist.drawing.*;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.ranges.AnalystRangable;
import ru.hydrologist.ranges.AnalystRange;
import ru.hydrologist.ranges.EmpiricRangable;
import ru.hydrologist.ranges.ProbabilityDistribution;

import java.awt.*;
import java.util.Map;
import java.util.UUID;

//Синхронизировать откладываемые значения по и риски на шкалах (сейчас на X мин значение 0,01, а откладывается 0,001)
//В классе построения кривой было магической число 100, на которое умножалось значение по X, надо найти откуда берётся необходимость этого умножения и перенести его в изначальный класс
//Рисовать эпирические и аналитические точки разными цветами и в зависимости от параметров на одном графике
public class SpringFloodMaximumWaterFlowCalculationSP331012003 extends Calculation{

    private EmpiricRangable empiricRange;
    private AnalystRangable analystRange;
    Graph ensuranceCurve;

    SpringFloodMaximumWaterFlowCalculationSP331012003(EmpiricRangable empiricRange) throws InterpolatorException {
        this.CALCULATIONMETHODNAME = "Расчёт максимальных расходов воды весеннего половодья по методике СП 33-101-2003";

        this.empiricRange = empiricRange;
        this.analystRange = new AnalystRange(empiricRange, "ru.ThreeParametrGammaDistribution");

    }

    SpringFloodMaximumWaterFlowCalculationSP331012003(EmpiricRangable empiricRange, ProbabilityDistribution distribution) throws InterpolatorException {
        this.CALCULATIONMETHODNAME = "Расчёт максимальных расходов воды весеннего половодья по методике СП 33-101-2003";

        this.empiricRange = empiricRange;
        this.analystRange = new AnalystRange(empiricRange, distribution);

    }

    private DrawableDataSet getEnsuranceCurveDrawableData(Color empiricCurveColor, Color analystCurveColor, boolean drawInscription, boolean drawConnectionLine, boolean fillPoints, boolean drawAnalystCurve) throws InterpolatorException {
        Map<Double, Double> empiricData = empiricRange.getRange();

        //Это надо переделывать. Цвет точек, рисовать или нет аналитическую кривую - должно задаваться клиентом, а не быть захардкожено тут
        DrawableData empiricCurveDrawingData = new GraphObject(empiricCurveColor, drawInscription, drawConnectionLine, fillPoints);
        setDrawingData(empiricData, empiricCurveDrawingData);
        DrawableDataSet dataSet = new DrawingDataSet();
        dataSet.addGraphObject(empiricCurveDrawingData);

        if(drawAnalystCurve == true){
            Map<Double, Double> analystData = analystRange.getAnalystRange();
            DrawableData analystCurveDrawingData = new GraphObject(analystCurveColor, drawInscription, drawConnectionLine, fillPoints);
            setDrawingData(analystData, analystCurveDrawingData);
            dataSet.addGraphObject(analystCurveDrawingData);
        }

        return dataSet;
    }

    private DrawableDataSet getEnsuranceCurveDrawableData() throws InterpolatorException {
        return getEnsuranceCurveDrawableData(Color.black, Color.red, true, true, true, true);
    }

    private void setDrawingData(Map<Double, Double> distribution, DrawableData drawingData){
        for(Map.Entry<Double, Double> entry : distribution.entrySet()){
            GraphPoint point = new GraphPoint(entry.getKey(), entry.getValue());
            drawingData.addPoint(point);
        }
    }

    public EmpiricRangable getEmpiricRange() {
        return empiricRange;
    }

    public AnalystRangable getAnalystRange() {
        return analystRange;
    }

    public Graph getEnsuranceCurve() throws InterpolatorException {
        this.ensuranceCurve = new Graph().createEnsuranceCurve(getEnsuranceCurveDrawableData());
        ensuranceCurve.createGraph();

        return ensuranceCurve;
    }

    public String getEnsuranceCurveFileName(Color empiricCurveColor, Color analystCurveColor, boolean drawInscription, boolean drawConnectionLine, boolean fillPoints, boolean drawAnalystCurve) throws InterpolatorException {
        this.ensuranceCurve = new Graph().createEnsuranceCurve(getEnsuranceCurveDrawableData(empiricCurveColor, analystCurveColor, drawInscription, drawConnectionLine, fillPoints, drawAnalystCurve));
        ensuranceCurve.createGraph();

        String randomFilename = UUID.randomUUID().toString() + ".png";
        ensuranceCurve.saveGraph(randomFilename );

        return randomFilename;
    }

    public String getEnsuranceCurveFileName() throws InterpolatorException {
        this.ensuranceCurve = new Graph().createEnsuranceCurve(getEnsuranceCurveDrawableData());
        ensuranceCurve.createGraph();

        String randomFilename = UUID.randomUUID().toString() + ".png";
        ensuranceCurve.saveGraph(randomFilename );

        return randomFilename;
    }

}
