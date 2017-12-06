package ru.hydrologist.exceptions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SavingDataException extends Exception{
    public Logger log = LogManager.getLogger(SavingDataException.class);                  //Объект для логирования

    public SavingDataException(String msg) {
        super(msg);
        log.error(msg);
    }
}
