package com.mario;

import java.util.ArrayList;
import java.util.List;

// https://www.codewars.com/kata/58f5c63f1e26ecda7e000029/java
public class MexicanWave {

    public static String[] wave(String str) {
        List<String> words = new ArrayList<>();

        for(int index = 0; index < str.length(); index++) {
            char currentChar = str.charAt(index);
            if(!Character.isWhitespace(currentChar)) {
                StringBuilder sb = new StringBuilder(str);
                sb.setCharAt(index, Character.toUpperCase(currentChar));
                words.add(sb.toString());
            }
        }

        return words.toArray(String[]::new);
    }

}
