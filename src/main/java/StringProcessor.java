package org.example;

public class StringProcessor {

    public static String repeat(String text, int count) {
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
}