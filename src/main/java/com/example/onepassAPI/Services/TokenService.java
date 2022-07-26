package com.example.onepassAPI.Services;

import com.example.onepassAPI.Models.Token;
import com.example.onepassAPI.Repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Random;

public class TokenService {

    public static String generate(int token_length) {
        char[] token = new char[token_length];
        for (int i = 0; i < token_length; i++) token[i] = randomChar();

        return Arrays.toString(token).replaceAll("\\[|\\]|,|\\s", "");
    }

    private static char randomChar() {
        char[] char_array = new String("abcdefghijklmnopqrstuvwxyz0123456789").toCharArray();
        Random random = new Random();
        int key = random.nextInt(36);

        return (char) char_array[key];
    }
}
