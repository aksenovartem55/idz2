package finance;

import org.example.finance.FinanceReport;
import org.example.finance.FinanceReportProcessor;
import org.example.finance.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinanceReportProcessorTest {

    private FinanceReport report;

    @BeforeEach
    void setUp() {
        Payment[] payments = {
                new Payment("Ашаев Игорь Викторович", 10, 5, 2026, 15050),
                new Payment("Березин Андрей Андреевич", 10, 5, 2026, 320000),
                new Payment("Ашаев Игорь Викторович", 15, 6, 2026, 4500)
        };
        report = new FinanceReport(payments, "Березин А.А.", 20, 6, 2026);
    }

    @Test
    void paymentsBySurnameLetter() {
        FinanceReport result = FinanceReportProcessor.getPaymentsByPrefix(report, 'А');

        assertEquals(2, result.getPaymentsCount());
        assertEquals(15050, result.getPayment(0).getAmount());
        assertEquals(4500, result.getPayment(1).getAmount());
    }

    @Test
    void firstNameLetterIsIgnored() {
        assertEquals(1, FinanceReportProcessor.getPaymentsByPrefix(report, 'Б').getPaymentsCount());
    }

    @Test
    void letterCaseDoesNotMatter() {
        assertEquals(2, FinanceReportProcessor.getPaymentsByPrefix(report, 'а').getPaymentsCount());
    }

    @Test
    void noPaymentsForUnknownLetter() {
        assertEquals(0, FinanceReportProcessor.getPaymentsByPrefix(report, 'Я').getPaymentsCount());
    }

    @Test
    void emptyNameIsSkipped() {
        Payment[] payments = {
                new Payment("   ", 1, 1, 2026, 100),
                new Payment("Ашаев Игорь Викторович", 1, 1, 2026, 100)
        };
        FinanceReport withEmptyName = new FinanceReport(payments, "Березин А.А.", 1, 1, 2026);

        assertEquals(1, FinanceReportProcessor.getPaymentsByPrefix(withEmptyName, 'А').getPaymentsCount());
    }

    @Test
    void resultContainsCopies() {
        FinanceReport result = FinanceReportProcessor.getPaymentsByPrefix(report, 'А');

        result.getPayment(0).setAmount(1);

        assertEquals(15050, report.getPayment(0).getAmount());
    }

    @Test
    void paymentsSmallerThan200Rubles() {
        FinanceReport result = FinanceReportProcessor.getPaymentsSmallerThan(report, 20000);

        assertEquals(2, result.getPaymentsCount());
        assertEquals(15050, result.getPayment(0).getAmount());
        assertEquals(4500, result.getPayment(1).getAmount());
    }

    @Test
    void paymentEqualToLimitIsNotIncluded() {
        FinanceReport result = FinanceReportProcessor.getPaymentsSmallerThan(report, 15050);

        assertEquals(1, result.getPaymentsCount());
        assertEquals(4500, result.getPayment(0).getAmount());
    }

    @Test
    void noPaymentsSmallerThanLimit() {
        assertEquals(0, FinanceReportProcessor.getPaymentsSmallerThan(report, 100).getPaymentsCount());
    }

    @Test
    void totalOnDateSumsPayments() {
        assertEquals(335050, FinanceReportProcessor.getTotalPaymentOnDate(report, "10.05.26"));
    }

    @Test
    void totalOnDateWithoutPayments() {
        assertEquals(0, FinanceReportProcessor.getTotalPaymentOnDate(report, "01.01.26"));
    }

    @Test
    void wrongDateFormatThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getTotalPaymentOnDate(report, "10.05"));
    }

    @Test
    void monthsWithoutPaymentsIn2026() {
        List<String> expected = List.of("Январь", "Февраль", "Март", "Апрель",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь");
        assertEquals(expected, FinanceReportProcessor.getMonthsWithoutPayments(report, 2026));
    }

    @Test
    void yearWithoutPaymentsHasAllMonths() {
        assertEquals(12, FinanceReportProcessor.getMonthsWithoutPayments(report, 2025).size());
    }
}
