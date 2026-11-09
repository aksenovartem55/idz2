package org.example.finance;

import java.util.Objects;

public class FinanceReport {
    private final Payment[] payments;
    private final String author;
    private final int day;
    private final int month;
    private final int year;

    public FinanceReport(Payment[] payments, String author, int day, int month, int year) {
        Objects.requireNonNull(payments, "Массив платежей не может быть null");
        this.payments = payments.clone();
        this.author = author;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public FinanceReport(FinanceReport other) {
        Objects.requireNonNull(other, "Копируемый отчет не может быть null");
        this.author = other.author;
        this.day = other.day;
        this.month = other.month;
        this.year = other.year;
        this.payments = new Payment[other.payments.length];
        for (int i = 0; i < other.payments.length; i++) {
            if (other.payments[i] != null) {
                this.payments[i] = new Payment(other.payments[i]);
            }
        }
    }

    public int getPaymentsCount() {
        return payments.length;
    }

    public Payment getPayment(int index) {
        return payments[index];
    }

    public void setPayment(int index, Payment payment) {
        payments[index] = payment;
    }

    public String getAuthor() {
        return author;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("[Автор: %s, дата: %02d.%02d.%d, Платежи: [\n",
                author, day, month, year));
        for (Payment p : payments) {
            if (p != null) {
                builder.append(p).append('\n');
            }
        }
        builder.append("]]");
        return builder.toString();
    }
}
