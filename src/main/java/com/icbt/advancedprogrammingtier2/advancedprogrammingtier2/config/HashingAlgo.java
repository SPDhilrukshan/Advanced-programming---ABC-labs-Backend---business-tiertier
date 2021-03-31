package com.icbt.advancedprogrammingtier2.advancedprogrammingtier2.config;

import java.math.BigInteger;
import java.security.MessageDigest;

public class HashingAlgo {

    public static String encryptToHash(String text){

        try{
            MessageDigest md = MessageDigest.getInstance(Constants.ENCRYPTION_ALGORITHM);

            byte[] messageDigest = md.digest(text.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
