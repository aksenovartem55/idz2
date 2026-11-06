package org.example.finance;

public class FinanceReport {
    private Payment[] payments;
    private String author;
    private int day;
    private int month;
    private int year;

    public FinanceReport(Payment[] payments, String author, int day, int month, int year) {
        this.payments = payments;
        this.author = author;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public FinanceReport(FinanceReport other) {
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("[Автор: %s, дата: %02d.%02d.%d, Платежи: [\n",
                author, day, month, year));
        for (int i = 0; i < payments.length; i++) {
            Payment p = payments[i];
            if (p != null) {
                int rubles = p.getAmount() / 100;
                int kopecks = p.getAmount() % 100;
                builder.append(String.format("Плательщик: %s, дата: %02d.%02d.%d сумма: %d руб. %02d коп.\n",
                        p.getFullName(), p.getDay(), p.getMonth(), p.getYear(), rubles, kopecks));
            }
        }
        builder.append("]]");
        return builder.toString();
    }
}