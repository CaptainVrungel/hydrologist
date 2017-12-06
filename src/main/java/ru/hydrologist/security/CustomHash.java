package ru.hydrologist.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Хэш счиается тут, плюс он захардкожен в настройках JASS
public class CustomHash {

    public static String getHash(String request){

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(request.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
