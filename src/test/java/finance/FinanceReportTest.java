package finance;

import org.example.finance.FinanceReport;
import org.example.finance.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinanceReportTest {

    private FinanceReport report;

    @BeforeEach
    void setUp() {
        Payment[] payments = {
                new Payment("Ашаев Игорь Викторович", 10, 5, 2026, 15050),
                new Payment("Березин Андрей Андреевич", 15, 6, 2026, 4500)
        };
        report = new FinanceReport(payments, "Березин А.А.", 20, 6, 2026);
    }

    @Test
    void toStringOfReport() {
        String expected = "[Автор: Березин А.А., дата: 20.06.2026, Платежи: [\n"
                + "Плательщик: Ашаев Игорь Викторович, дата: 10.05.2026 сумма: 150 руб. 50 коп.\n"
                + "Плательщик: Березин Андрей Андреевич, дата: 15.06.2026 сумма: 45 руб. 00 коп.\n"
                + "]]";
        assertEquals(expected, report.toString());
    }

    @Test
    void toStringOfEmptyReport() {
        FinanceReport empty = new FinanceReport(new Payment[0], "Березин А.А.", 1, 1, 2026);
        assertEquals("[Автор: Березин А.А., дата: 01.01.2026, Платежи: [\n]]", empty.toString());
    }

    @Test
    void copyHasSameData() {
        FinanceReport copy = new FinanceReport(report);
        assertEquals(report.toString(), copy.toString());
    }

    @Test
    void copyDoesNotChangeWhenPaymentChanges() {
        FinanceReport copy = new FinanceReport(report);

        report.getPayment(0).setAmount(999999);

        assertEquals(15050, copy.getPayment(0).getAmount());
    }

    @Test
    void copyDoesNotChangeWhenPaymentReplaced() {
        FinanceReport copy = new FinanceReport(report);

        report.setPayment(1, new Payment("Ашаев Игорь Викторович", 1, 7, 2026, 100));

        assertEquals("Березин Андрей Андреевич", copy.getPayment(1).getFullName());
    }
}
