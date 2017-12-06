package ru.hydrologist.drawing;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestGraphObject {
    private Logger log = LogManager.getLogger(TestGraphObject.class);
    private final int timeout = 6000;
    private Double accuracy = 0.0000001;
    GraphObject dataSet = new GraphObject();

    TestGraphObject(){
        dataSet.addPoint(new GraphPoint(13.3, 25.4));
        dataSet.addPoint(new GraphPoint(1.3, 5.4));
        dataSet.addPoint(new GraphPoint(3.2, 2.1));
        dataSet.addPoint(new GraphPoint(15.7, 12.9));
        dataSet.addPoint(new GraphPoint(123.1, 214.2));
        dataSet.addPoint(new GraphPoint(0.0, 0.0));
        dataSet.addPoint(new GraphPoint(-123.0, -13.3));
    }

    private void checkAssert(Double ultimateTruth, Double result){
        if(ultimateTruth - result < - accuracy || ultimateTruth - result > accuracy){
            log.error("Target: " + ultimateTruth + "Result: " + result);
            Assert.fail();
        }
    }

    @Test(timeOut = timeout)
    public void receiveYPointsAverage(){
        Double ultimateTruth = 35.2428571428571;
        Double result = dataSet.getYPointsAverage();
        checkAssert(ultimateTruth, result);
    }

    @Test(timeOut = timeout)
    public void receiveXPointsAverage(){
        Double ultimateTruth = 4.8;
        Double result = dataSet.getXPointsAverage();
        checkAssert(ultimateTruth, result);
    }

    @Test(timeOut = timeout)
    public void receiveYPointsMaximum(){
        Double ultimateTruth = 214.2;
        Double result = dataSet.getYPointsMaximum();
        checkAssert(ultimateTruth, result);
}

    @Test(timeOut = timeout)
    public void receiveXPointsMaximum(){
        Double ultimateTruth = 123.1;
        Double result = dataSet.getXPointsMaximum();
        checkAssert(ultimateTruth, result);
    }

    @Test(timeOut = timeout)
    public void receiveYPointsMinimum(){
        Double ultimateTruth = -13.3;
        Double result = dataSet.getYPointsMinimum();
        checkAssert(ultimateTruth, result);
    }

}



