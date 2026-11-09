package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor {

    public static String repeat(String text, int count) {
        if (text == null) {
            throw new IllegalArgumentException("Строка не может быть null");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Количество копий не может быть отрицательным");
        }
        if (count == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(text);
        }
        return builder.toString();
    }

    public static int countOccurrences(String text, String target) {
        if (target == null || target.isEmpty()) {
            throw new IllegalArgumentException("Искомая строка не может быть пустой или null");
        }
        if (text == null || text.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        return count;
    }

    public static String replaceDigits(String text) {
        if (text == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            if (current == '1') {
                builder.append("один");
            } else if (current == '2') {
                builder.append("два");
            } else if (current == '3') {
                builder.append("три");
            } else {
                builder.append(current);
            }
        }
        return builder.toString();
    }

    public static void removeEverySecondChar(StringBuilder builder) {
        if (builder == null) {
            return;
        }
        for (int i = 1; i < builder.length(); i++) {
            builder.deleteCharAt(i);
        }
    }

    public static String reverseWords(String text) {
        if (text == null) {
            return null;
        }
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch != ' ') {
                currentWord.append(ch);
            } else {
                if (currentWord.length() > 0) {
                    words.add(currentWord.toString());
                    currentWord.setLength(0);
                }
            }
        }
        if (currentWord.length() > 0) {
            words.add(currentWord.toString());
        }

        StringBuilder result = new StringBuilder();
        int wordIndex = words.size() - 1;
        boolean inWord = false;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                result.append(' ');
                inWord = false;
            } else {
                if (!inWord) {
                    result.append(words.get(wordIndex--));
                    inWord = true;
                }
            }
        }
        return result.toString();
    }

    public static String replaceHexWithDecimal(String text) {
        if (text == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("0[xX]([0-9a-fA-F]{8})");
        Matcher matcher = pattern.matcher(text);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String hexDigits = matcher.group(1);
            long decimalValue = Long.parseUnsignedLong(hexDigits, 16);
            matcher.appendReplacement(result, String.valueOf(decimalValue));
        }
        matcher.appendTail(result);
        return result.toString();
    }
}