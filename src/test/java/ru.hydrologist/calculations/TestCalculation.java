package ru.hydrologist.calculations;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.hydrologist.DataSets.DataSetRange;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.ranges.EmpiricRange;

import java.util.ArrayList;
import java.util.Map;

public class TestCalculation {

    private double accuracy = 0.000000001;                                        //Точность вычислений
    private final int timeout = 6000;
    public Logger log = LogManager.getLogger(TestCalculation.class);                  //Объект для логирования
    EmpiricRange empiricRange;

    @BeforeClass
    public void initiateCalculations(){
        ArrayList<Map<String, ArrayList<Double>>> DataSet = new DataSetRange().getDataSet();
        ArrayList<Double> rowRange = DataSet.get(0).get("range");
        System.out.println(rowRange);
        this.empiricRange = new EmpiricRange(rowRange);
    }


    @Test(timeOut = timeout)
    public void testCreateSpringFloodMaximumWaterFlowCalculationSP331012003() throws InterpolatorException {
        SpringFloodMaximumWaterFlowCalculationSP331012003 calculation = new Calculation().createSpringFloodMaximumWaterFlowCalculationSP331012003(empiricRange);
    }

    @Test(timeOut = timeout)
    public void testGetCalculationMethodName() throws InterpolatorException {
        Calculation calculation = new Calculation().createSpringFloodMaximumWaterFlowCalculationSP331012003(empiricRange);
        Assert.assertEquals(calculation.getCalculationMethodName(), "Расчёт максимальных расходов воды весеннего половодья по методике СП 33-101-2003");
    }
}
