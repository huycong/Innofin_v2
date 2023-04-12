package com.innofin.api.helper;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

public class RandomData {
    static final String TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String DIGIT = "0123456789";
    static final String SPECIAL = "~=+%^*/()[]{}/!@#$?|";
    static final String TEXT_DIGIT = TEXT + DIGIT;
    static final String ALL = TEXT + DIGIT + SPECIAL;
    static SecureRandom random = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(TEXT.charAt(random.nextInt(TEXT.length())));
        }
        return sb.toString();
    }

    public static String randomNumber(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(DIGIT.charAt(random.nextInt(DIGIT.length())));
        }
        return sb.toString();
    }
    public static String randomStringAndNumber(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(TEXT_DIGIT.charAt(random.nextInt(TEXT_DIGIT.length())));
        }
        return sb.toString();
    }

    public static String randomStringWithSpecialText(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ALL.charAt(random.nextInt(ALL.length())));
        }
        return sb.toString();
    }

    public static String randomStringHexToken(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[byteLength];
        secureRandom.nextBytes(token);
        return new BigInteger(1, token).toString(16); // Hexadecimal encoding
    }

    public static String randomStringUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static int randomNumberIntFromTo(int from, int to) {
        int random_int = (int) Math.floor(Math.random() * (to - from + 1) + from);
        return random_int;
    }
}


