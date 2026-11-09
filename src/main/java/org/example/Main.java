package org.example;

import org.example.finance.FinanceReport;
import org.example.finance.FinanceReportProcessor;
import org.example.finance.Payment;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Список заданий:");
        System.out.println("1 - Копии строки N раз");
        System.out.println("2 - Количество вхождений подстроки");
        System.out.println("3 - Замена цифр 1, 2, 3 словами");
        System.out.println("4 - Удаление каждого второго символа в StringBuilder");
        System.out.println("5 - Разворот слов с сохранением пробелов (*)");
        System.out.println("6 - Замена 0xNNNNNNNN на десятичное число (*)");
        System.out.println("7 - Класс Payment");
        System.out.println("8-9 - Класс FinanceReport и форматированный вывод");
        System.out.println("10 - Конструктор копирования FinanceReport");
        System.out.println("11 - Фильтрация отчетов (FinanceReportProcessor)");
        System.out.println("12 - Сумма на дату и месяцы без платежей (*)");
        System.out.println("13 - Налоговая декларация (*)");

        System.out.print("\nВведите номер задания: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Payment[] samplePayments = {
                new Payment("Ашаев Игорь Викторович", 10, 5, 2026, 15050),
                new Payment("Березин Андрей Андреевич", 10, 5, 2026, 320000),
                new Payment("Ашаев Игорь Викторович", 15, 6, 2026, 4500)
        };
        FinanceReport sampleReport = new FinanceReport(samplePayments, "Березин А.А.", 20, 6, 2026);

        switch (choice) {
            case 1:
                System.out.print("Введите строку: ");
                String str = scanner.nextLine();
                System.out.print("Введите число копий N: ");
                int n = scanner.nextInt();
                System.out.println("Результат: \"" + StringProcessor.repeat(str, n) + "\"");
                break;

            case 2:
                System.out.print("Введите первую строку: ");
                String first = scanner.nextLine();
                System.out.print("Введите вторую строку: ");
                String second = scanner.nextLine();
                System.out.println("Количество вхождений: " + StringProcessor.countOccurrences(first, second));
                break;

            case 3:
                System.out.print("Введите строку с цифрами 1, 2, 3: ");
                String raw = scanner.nextLine();
                System.out.println("Результат: " + StringProcessor.replaceDigits(raw));
                break;

            case 4:
                System.out.print("Введите строку: ");
                String input = scanner.nextLine();
                StringBuilder sb = new StringBuilder(input);
                StringProcessor.removeEverySecondChar(sb);
                System.out.println("После удаления каждого второго символа: " + sb);
                break;

            case 5:
                String wordsStr = "  aaa   bbb cc  dd ";
                System.out.println("Исходная: \"" + wordsStr + "\"");
                System.out.println("Результат: \"" + StringProcessor.reverseWords(wordsStr) + "\"");
                break;

            case 6:
                String hexStr = "Васе 0x00000010 лет, а другу 0x00000014 лет";
                System.out.println("Исходная: " + hexStr);
                System.out.println("Результат: " + StringProcessor.replaceHexWithDecimal(hexStr));
                break;

            case 7:
                Payment p1 = new Payment("Ашаев Игорь Викторович", 10, 5, 2026, 15050);
                Payment p2 = new Payment("Ашаев Игорь Викторович", 10, 5, 2026, 15050);
                System.out.println("Платеж 1: " + p1);
                System.out.println("Платеж 2: " + p2);
                System.out.println("Равны ли платежи? " + p1.equals(p2));
                System.out.println("Хэш-коды совпадают? " + (p1.hashCode() == p2.hashCode()));
                break;

            case 8:
            case 9:
                System.out.println("Количество платежей: " + sampleReport.getPaymentsCount());
                System.out.println("Первый платеж: " + sampleReport.getPayment(0));
                System.out.println("\nПолный отчет:\n" + sampleReport);
                break;

            case 10:
                FinanceReport copyReport = new FinanceReport(sampleReport);
                sampleReport.getPayment(0).setAmount(999999);
                System.out.println("Исходный отчет (изменен 1-й платеж):\n" + sampleReport);
                System.out.println("\nКопия отчета (не должна измениться):\n" + copyReport);
                break;

            case 11:
                System.out.println("Платежи на букву 'А':\n" +
                        FinanceReportProcessor.getPaymentsByPrefix(sampleReport, 'А'));
                System.out.println("\nПлатежи меньше 20000 коп. (200 руб.):\n" +
                        FinanceReportProcessor.getPaymentsSmallerThan(sampleReport, 20000));
                break;

            case 12:
                int sumOnDate = FinanceReportProcessor.getTotalPaymentOnDate(sampleReport, "10.05.26");
                System.out.println("Суммарный платеж на 10.05.26 (в коп.): " + sumOnDate);
                System.out.println("Месяцы без платежей в 2026 году: " +
                        FinanceReportProcessor.getMonthsWithoutPayments(sampleReport, 2026));
                break;

            case 13:
                double[] inc1 = {1200, 2300, 24000, 25000, 0, 0, 0, 0, 0, 0, 0, 0};
                double[] inc2 = {0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                IncomeStatement st1 = new IncomeStatement(2020, "Ашаев И.В.", "Казино «Поле чудес»", inc1);
                IncomeStatement st2 = new IncomeStatement(2020, "Ашаев И.В.", "ООО «Рога и копыта»", inc2);
                TaxDeclaration decl = new TaxDeclaration(2020, "Ашаев И.В.", st1, st2);

                System.out.println("Доходы по месяцам: " + Arrays.toString(decl.getMonthlyIncomes()));
                System.out.println("Суммарный доход с начала года: " + Arrays.toString(decl.getCumulativeIncomes()));
                System.out.println("Начисленный налог: " + Arrays.toString(decl.getCumulativeTaxes()));
                System.out.println("Итоговый налог за год: " + decl.getTotalTax());
                break;

            default:
                System.out.println("Неверный номер задания.");
                break;
        }
    }
}