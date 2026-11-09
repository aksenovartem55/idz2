import org.example.IncomeStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncomeStatementTest {

    @Test
    void elevenMonthsThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new IncomeStatement(2020, "Ашаев И.В.", "ООО «Рога и копыта»", new double[11]));
    }

    @Test
    void thirteenMonthsThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new IncomeStatement(2020, "Ашаев И.В.", "ООО «Рога и копыта»", new double[13]));
    }

    @Test
    void nullIncomesThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new IncomeStatement(2020, "Ашаев И.В.", "ООО «Рога и копыта»", null));
    }

    @Test
    void changingSourceArrayDoesNotChangeStatement() {
        double[] incomes = new double[12];
        incomes[0] = 1200;
        IncomeStatement statement = new IncomeStatement(2020, "Ашаев И.В.", "ООО «Рога и копыта»", incomes);

        incomes[0] = 5000;

        assertEquals(1200, statement.getMonthlyIncomes()[0], 0.001);
    }
}
