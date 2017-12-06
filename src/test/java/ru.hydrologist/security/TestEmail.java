package ru.hydrologist.security;

import org.testng.annotations.Test;

public class TestEmail {
    @Test
    public void testSendEmail(){
        String text = "This is test email. Please, do not answer on it.";
        String subject = "test";
        String address = "fedorovskiy1@yandex.ru";

        Email.sendEmail(text, subject, address);
    }
}
