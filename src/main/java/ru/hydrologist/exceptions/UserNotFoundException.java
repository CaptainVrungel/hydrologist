package ru.hydrologist.exceptions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserNotFoundException extends Exception{
    public Logger log = LogManager.getLogger(UserNotFoundException.class);                  //Объект для логирования

    public UserNotFoundException(String msg) {
        super(msg);
        log.error(msg);
    }
}
