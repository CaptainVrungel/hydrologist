package ru.hydrologist.drawing;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import ru.hydrologist.DataSets.DataSetRange;
import ru.hydrologist.ranges.EmpiricRange;
import ru.hydrologist.utils.DataConverter;

import java.util.ArrayList;
import java.util.Map;

public class TestGraph {
    private double accuracy = 0.000000001;                                        //Точность вычислений
    private final int timeout = 6000;
    public Logger log = LogManager.getLogger(TestGraph.class);                  //Объект для логирования

    @Test(timeOut = timeout)
    public void testCreateRelationGraph(){

        GraphObject data = new GraphObject();
        data.addPoint(new GraphPoint(2.0, 0.0));
        data.addPoint(new GraphPoint(10.0, 27.0));
        data.addPoint(new GraphPoint(40.0, 52.0));
        data.addPoint(new GraphPoint(20.0, 30.0));
        data.addPoint(new GraphPoint(60.0, 80.0));
        data.addPoint(new GraphPoint(90.0, 100.0));
        data.addPoint(new GraphPoint(1100.0, 200.0));
        data.addPoint(new GraphPoint(230.0, 240.0));

        DrawingDataSet drawingDataSet = new DrawingDataSet();
        drawingDataSet.addGraphObject(data);

        Graph relation = new Graph().createRelationGraph(drawingDataSet);
        relation.createGraph();
        relation.saveGraph("relationGraph.png");
    }

    @Test(timeOut = timeout)
    public void testCreateEnsuranceCurve(){

        ArrayList<Map<String, ArrayList<Double>>> DataSet = new ArrayList<Map<String, ArrayList<Double>>>();
        DataSet = new DataSetRange().getDataSet();
        ArrayList<Double> rowRange = DataSet.get(0).get("range");
        EmpiricRange empiricRange = new EmpiricRange(rowRange);
        ArrayList<Double> probabilitys = DataSet.get(0).get("probabilitys");

        GraphObject data = new GraphObject();

        int len = rowRange.size();
        for(int i=0; i<len; i++){
            Double d = DataConverter.DoubleRound(probabilitys.get(i)*100, 6);
            data.addPoint(new GraphPoint(probabilitys.get(i), empiricRange.getRange().get(d)));

        }
        DrawingDataSet drawingDataSet = new DrawingDataSet();
        drawingDataSet.addGraphObject(data);

        try {
            Graph curve = new Graph().createEnsuranceCurve(drawingDataSet);
            curve.createGraph();
            curve.saveGraph("EnsuranceCurve.png");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
