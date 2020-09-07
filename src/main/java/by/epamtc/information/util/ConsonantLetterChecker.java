package by.epamtc.information.util;

import by.epamtc.information.configuration.annotation.InjectProperty;

public class ConsonantLetterChecker {
//    @InjectProperty("notConsonantLetters")
    private static String notConsonantLetters = "aeiou";


    public static boolean check(char c) {
        char[] chars = notConsonantLetters.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i]){
                return true;
            }
        }
        return false;
    }
}
