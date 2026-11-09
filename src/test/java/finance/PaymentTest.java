package finance;

import org.example.finance.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void toStringShowsRublesAndKopecks() {
        Payment payment = new Payment("Ашаев Игорь Викторович", 10, 5, 2026, 15050);
        assertEquals("Плательщик: Ашаев Игорь Викторович, дата: 10.05.2026 сумма: 150 руб. 50 коп.",
                payment.toString());
    }

    @Test
    void toStringAddsLeadingZeros() {
        Payment payment = new Payment("Березин Андрей Андреевич", 5, 3, 2026, 5);
        assertEquals("Плательщик: Березин Андрей Андреевич, дата: 05.03.2026 сумма: 0 руб. 05 коп.",
                payment.toString());
    }

    @Test
    void copyDoesNotChangeWhenOriginalChanges() {
        Payment original = new Payment("Ашаев Игорь Викторович", 10, 5, 2026, 15050);
        Payment copy = new Payment(original);

        original.setAmount(100);

        assertEquals(15050, copy.getAmount());
    }
}
