package ru.hydrologist.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDataConverter {
    private double accuracy = 0.000000001;                                        //Точность вычислений
    private final int timeout = 6000;
    public Logger log = LogManager.getLogger(TestDataConverter.class);                  //Объект для логирования

    @Test(timeOut = timeout)
    public void testSmartHydrologicalRound(){
        Double[] values = { 890784.9331,    5038.9123123,   448.2131,   39.23434,   4.95422,    0.32195852, -1.216124,  -9084.21312,    -42.213};
        Double[] target = { 890785.0,       5039.0,         448.0,      39.2,       4.95,       0.322,      -1.22,      -9084.0,        -42.2};

        for(int i = 0; i< values.length; i++){
            Double result = DataConverter.smartHydrologicalRound(values[i]);
            Assert.assertEquals(result, target[i]);
        }

    }

    @Test(timeOut = timeout)
    public void smartHydrologicalRoundToString(){
        Double[] values = { 890784.9331,    5038.9123123,   448.2131,   39.23434,   4.95422,    0.32195852, -1.216124,  -9084.21312,    -42.213};
        String[] target = { "890785",       "5039",         "448",      "39.2",     "4.95",     "0.322",    "-1.22",    "-9084",      "-42.2"};

        for(int i = 0; i< values.length; i++){
            String result = DataConverter.smartHydrologicalRoundToString(values[i]);
            Assert.assertEquals(result, target[i]);
        }
    }

}
