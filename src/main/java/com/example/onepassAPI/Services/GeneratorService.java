package com.example.onepassAPI.Services;

import java.util.Arrays;
import java.util.Random;

public class GeneratorService {
    public static String initToken() {
        final int TOKEN_LENGTH = 32;

        char[] token = new char[TOKEN_LENGTH];
        for (int i = 0; i < TOKEN_LENGTH; i++) token[i] = randomChar();
        return Arrays.toString(token).replaceAll("\\[|\\]|,|\\s", "");
    }

    public static String initUID() {
        final int UID_LENGTH = 8;

        char[] uid = new char[UID_LENGTH];
        for (int i = 0; i < UID_LENGTH; i++) uid[i] = randomChar();
        return Arrays.toString(uid).replaceAll("\\[|\\]|,|\\s", "");
    }

    private static char randomChar() {
        char[] char_array = new String("abcdefghijklmnopqrstuvwxyz0123456789").toCharArray();
        Random random = new Random();
        int key = random.nextInt(char_array.length);

        return (char) char_array[key];
    }
}
