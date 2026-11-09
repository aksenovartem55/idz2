package org.example.finance;

import java.util.ArrayList;
import java.util.List;

public class FinanceReportProcessor {

    private static final String[] MONTH_NAMES = {
            "Январь", "Февраль", "Март", "Апрель",
            "Май", "Июнь", "Июль", "Август",
            "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    };

    public static FinanceReport getPaymentsByPrefix(FinanceReport report, char prefix) {
        char target = Character.toLowerCase(prefix);
        int count = 0;

        for (int i = 0; i < report.getPaymentsCount(); i++) {
            Payment p = report.getPayment(i);
            if (p != null && !p.getFullName().trim().isEmpty()) {
                char firstChar = Character.toLowerCase(p.getFullName().trim().charAt(0));
                if (firstChar == target) {
                    count++;
                }
            }
        }

        Payment[] filtered = new Payment[count];
        int index = 0;
        for (int i = 0; i < report.getPaymentsCount(); i++) {
            Payment p = report.getPayment(i);
            if (p != null && !p.getFullName().trim().isEmpty()) {
                char firstChar = Character.toLowerCase(p.getFullName().trim().charAt(0));
                if (firstChar == target) {
                    filtered[index] = new Payment(p);
                    index++;
                }
            }
        }

        return new FinanceReport(filtered, report.getAuthor(), report.getDay(), report.getMonth(), report.getYear());
    }

    public static FinanceReport getPaymentsSmallerThan(FinanceReport report, int maxAmount) {
        int count = 0;

        for (int i = 0; i < report.getPaymentsCount(); i++) {
            Payment p = report.getPayment(i);
            if (p != null && p.getAmount() < maxAmount) {
                count++;
            }
        }

        Payment[] filtered = new Payment[count];
        int index = 0;
        for (int i = 0; i < report.getPaymentsCount(); i++) {
            Payment p = report.getPayment(i);
            if (p != null && p.getAmount() < maxAmount) {
                filtered[index] = new Payment(p);
                index++;
            }
        }

        return new FinanceReport(filtered, report.getAuthor(), report.getDay(), report.getMonth(), report.getYear());
    }

    public static int getTotalPaymentOnDate(FinanceReport report, String dateStr) {
        if (report == null || dateStr == null || dateStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Некорректные входные данные");
        }
        String[] parts = dateStr.trim().split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат даты. Ожидается dd.mm.yy");
        }

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        int total = 0;
        for (int i = 0; i < report.getPaymentsCount(); i++) {
            Payment p = report.getPayment(i);
            if (p != null) {
                boolean yearMatches = (p.getYear() == year) || (year < 100 && p.getYear() % 100 == year);
                if (p.getDay() == day && p.getMonth() == month && yearMatches) {
                    total += p.getAmount();
                }
            }
        }
        return total;
    }

    public static List<String> getMonthsWithoutPayments(FinanceReport report, int year) {
        boolean[] hasPayments = new boolean[12];

        if (report != null) {
            for (int i = 0; i < report.getPaymentsCount(); i++) {
                Payment p = report.getPayment(i);
                if (p != null && p.getYear() == year) {
                    int m = p.getMonth();
                    if (m >= 1 && m <= 12) {
                        hasPayments[m - 1] = true;
                    }
                }
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            if (!hasPayments[i]) {
                result.add(MONTH_NAMES[i]);
            }
        }
        return result;
    }
}