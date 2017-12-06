package ru.hydrologist.calculations;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import ru.hydrologist.DataSets.DataSetRange;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.ranges.EmpiricRange;

import java.util.ArrayList;
import java.util.Map;

public class TestSpringFloodMaximumWaterFlowCalculationSP331012003 {

    private double accuracy = 0.000000001;                                        //Точность вычислений
    private final int timeout = 6000;
    public Logger log = LogManager.getLogger(TestSpringFloodMaximumWaterFlowCalculationSP331012003.class);                  //Объект для логирования

    @Test(timeOut = timeout)
    public void testSpringFloodMaximumWaterFlowCalculationSP331012003() throws InterpolatorException {
        ArrayList<Map<String, ArrayList<Double>>> DataSet = new DataSetRange().getDataSet();
        ArrayList<Double> rowRange = DataSet.get(0).get("range");
        System.out.println(rowRange);
        EmpiricRange empiricRange = new EmpiricRange(rowRange);

        SpringFloodMaximumWaterFlowCalculationSP331012003 calculation = new SpringFloodMaximumWaterFlowCalculationSP331012003(empiricRange);
    }
}
