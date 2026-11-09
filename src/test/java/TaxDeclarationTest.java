import org.example.IncomeStatement;
import org.example.TaxDeclaration;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TaxDeclarationTest {

    private IncomeStatement statement(int year, double... incomes) {
        return new IncomeStatement(year, "Ашаев И.В.", "ООО «Рога и копыта»", Arrays.copyOf(incomes, 12));
    }

    @Test
    void exampleFromTaskWithTwoStatements() {
        IncomeStatement first = statement(2020, 1200, 2300, 24000, 25000);
        IncomeStatement second = statement(2020, 0, 50, 0, 0);
        TaxDeclaration declaration = new TaxDeclaration(2020, "Ашаев И.В.", first, second);

        double[] incomes = declaration.getCumulativeIncomes();
        double[] taxes = declaration.getCumulativeTaxes();

        assertEquals(1200, incomes[0], 0.01);
        assertEquals(3550, incomes[1], 0.01);
        assertEquals(27550, incomes[2], 0.01);
        assertEquals(52550, incomes[3], 0.01);

        assertEquals(0, taxes[0], 0.01);
        assertEquals(0, taxes[1], 0.01);
        assertEquals(461.50, taxes[2], 0.01);
        assertEquals(3711.50, taxes[3], 0.01);
    }

    @Test
    void taxForAllThreeBrackets() {
        IncomeStatement statement = statement(2020,
                6000, 6000, 6000, 8000, 60000, 60000, 60000, 60000, 100000, 100000, 100000, 100000);
        TaxDeclaration declaration = new TaxDeclaration(2020, "Ашаев И.В.", statement);

        double[] expected = {0, 0, 0, 260, 8060, 15860, 23660, 33280, 53280, 73280, 93280, 113280};
        assertArrayEquals(expected, declaration.getCumulativeTaxes(), 0.01);
        assertEquals(113280, declaration.getTotalTax(), 0.01);
    }

    @Test
    void noTaxUpTo24000() {
        TaxDeclaration declaration = new TaxDeclaration(2020, "Ашаев И.В.", statement(2020, 24000));
        assertEquals(0, declaration.getTotalTax(), 0.01);
    }

    @Test
    void taxAt240000() {
        TaxDeclaration declaration = new TaxDeclaration(2020, "Ашаев И.В.", statement(2020, 240000));
        assertEquals(28080, declaration.getTotalTax(), 0.01);
    }

    @Test
    void declarationWithoutStatements() {
        TaxDeclaration declaration = new TaxDeclaration(2020, "Ашаев И.В.");
        assertEquals(0, declaration.getTotalTax(), 0.01);
    }

    @Test
    void statementOfOtherYearIsIgnored() {
        TaxDeclaration declaration = new TaxDeclaration(2020, "Ашаев И.В.",
                statement(2019, 500000), statement(2020, 10000));
        assertEquals(10000, declaration.getCumulativeIncomes()[11], 0.01);
        assertEquals(0, declaration.getTotalTax(), 0.01);
    }
}
