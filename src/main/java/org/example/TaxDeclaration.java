package org.example;

import java.util.Arrays;

public final class TaxDeclaration {
    private final int year;
    private final String fullName;
    private final double[] monthlyIncomes;
    private final double[] cumulativeIncomes;
    private final double[] cumulativeTaxes;

    public TaxDeclaration(int year, String fullName, IncomeStatement... statements) {
        this.year = year;
        this.fullName = fullName;
        this.monthlyIncomes = new double[12];
        this.cumulativeIncomes = new double[12];
        this.cumulativeTaxes = new double[12];

        if (statements != null) {
            for (IncomeStatement st : statements) {
                if (st != null && st.getYear() == year) {
                    double[] incomes = st.getMonthlyIncomes();
                    for (int i = 0; i < 12; i++) {
                        this.monthlyIncomes[i] += incomes[i];
                    }
                }
            }
        }

        double runningTotal = 0.0;
        for (int i = 0; i < 12; i++) {
            runningTotal += this.monthlyIncomes[i];
            this.cumulativeIncomes[i] = runningTotal;
            this.cumulativeTaxes[i] = calculateTaxForCumulativeIncome(runningTotal);
        }
    }

    private double calculateTaxForCumulativeIncome(double income) {
        if (income <= 24000.0) {
            return 0.0;
        } else if (income <= 240000.0) {
            return (income - 24000.0) * 0.13;
        } else {
            return (240000.0 - 24000.0) * 0.13 + (income - 240000.0) * 0.20;
        }
    }

    public int getYear() {
        return year;
    }

    public String getFullName() {
        return fullName;
    }

    public double[] getMonthlyIncomes() {
        return Arrays.copyOf(monthlyIncomes, 12);
    }

    public double[] getCumulativeIncomes() {
        return Arrays.copyOf(cumulativeIncomes, 12);
    }

    public double[] getCumulativeTaxes() {
        return Arrays.copyOf(cumulativeTaxes, 12);
    }

    public double getTotalTax() {
        return cumulativeTaxes[11];
    }
}