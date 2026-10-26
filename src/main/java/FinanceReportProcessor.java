package org.example;

public class FinanceReportProcessor {

    public static FinanceReport getPaymentsByPrefix(FinanceReport report, char prefix) {
        char target = Character.toLowerCase(prefix);
        int count = 0;

        for (int i = 0; i < report.getPaymentsCount(); i++) {
            Payment p = report.getPayment(i);
            if (p != null && !p.getFullName().isEmpty()) {
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
            if (p != null && !p.getFullName().isEmpty()) {
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
}