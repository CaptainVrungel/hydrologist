package ru.hydrologist.validators;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestLoginValidator {
    private final int timeout = 6000;
    public Logger log = LogManager.getLogger(TestLoginValidator.class);                  //Объект для логирования

    @Test
    public void testCheckEmailByRegExp(){
        LoginValidator validator = new LoginValidator();

        ArrayList<String> incorrectEmails = new ArrayList<String>();
        incorrectEmails.add("asd");
        incorrectEmails.add("asdljadlnasd");
        incorrectEmails.add("asdj@ldksj");
        incorrectEmails.add("asdjldksj@.");
        incorrectEmails.add("asdjldksj.ru");
        incorrectEmails.add("@asdjldksj.ru");
        incorrectEmails.add("яплакаль@почта.ru");

        ArrayList<String> correctEmails = new ArrayList<String>();
        correctEmails.add("asdf@yandex.ru");
        correctEmails.add("fsdahasd@gmail.com");

        for(String email : incorrectEmails){
            if(validator.checkEmailByRegExp(email)){
                Assert.fail("Email " + email + " should be incorrect, but it is not");
            }
        }

        for(String email : correctEmails){
            if(!validator.checkEmailByRegExp(email)){
                Assert.fail("Email " + email + "should be correct, but it is not");
            }
        }
    }

}
