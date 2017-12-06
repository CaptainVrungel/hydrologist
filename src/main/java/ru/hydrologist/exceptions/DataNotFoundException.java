package ru.hydrologist.exceptions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DataNotFoundException extends Exception{
    public Logger log = LogManager.getLogger(DataNotFoundException.class);                  //Объект для логирования

    public DataNotFoundException(String msg) {
        super(msg);
        log.error(msg);
    }
}
