import org.example.StringProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringProcessorTest {

    @Test
    void repeatThreeTimes() {
        assertEquals("хахаха", StringProcessor.repeat("ха", 3));
    }

    @Test
    void repeatZeroTimes() {
        assertEquals("", StringProcessor.repeat("ха", 0));
    }

    @Test
    void repeatNegativeCountThrows() {
        assertThrows(IllegalArgumentException.class, () -> StringProcessor.repeat("ха", -1));
    }

    @Test
    void repeatNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> StringProcessor.repeat(null, 2));
    }

    @Test
    void countInWord() {
        assertEquals(2, StringProcessor.countOccurrences("банан", "ан"));
    }

    @Test
    void countNothingFound() {
        assertEquals(0, StringProcessor.countOccurrences("Кошка сидит на окне", "собака"));
    }

    @Test
    void countIgnoresOverlaps() {
        assertEquals(1, StringProcessor.countOccurrences("ананас", "ана"));
    }

    @Test
    void countEmptyTargetThrows() {
        assertThrows(IllegalArgumentException.class, () -> StringProcessor.countOccurrences("ананас", ""));
    }

    @Test
    void countNullTargetThrows() {
        assertThrows(IllegalArgumentException.class, () -> StringProcessor.countOccurrences("ананас", null));
    }

    @Test
    void digitsInPhoneNumber() {
        assertEquals("Номер телефона 8-одиндватри-456",
                StringProcessor.replaceDigits("Номер телефона 8-123-456"));
    }

    @Test
    void singleDigitInSentence() {
        assertEquals("Позвонить три раза", StringProcessor.replaceDigits("Позвонить 3 раза"));
    }

    @Test
    void otherDigitsStay() {
        assertEquals("Квартира 45 на 6 этаже", StringProcessor.replaceDigits("Квартира 45 на 6 этаже"));
    }

    @Test
    void removeSecondCharsInWord() {
        StringBuilder sb = new StringBuilder("привет");
        StringProcessor.removeEverySecondChar(sb);
        assertEquals("пие", sb.toString());
    }

    @Test
    void removeSecondCharsInDigits() {
        StringBuilder sb = new StringBuilder("0123456789");
        StringProcessor.removeEverySecondChar(sb);
        assertEquals("02468", sb.toString());
    }

    @Test
    void removeFromEmptyBuilder() {
        StringBuilder sb = new StringBuilder();
        StringProcessor.removeEverySecondChar(sb);
        assertEquals("", sb.toString());
    }

    @Test
    void reverseWordsInSentence() {
        assertEquals("вечером сегодня кино в идём Мы",
                StringProcessor.reverseWords("Мы идём в кино сегодня вечером"));
    }

    @Test
    void reverseWordsKeepsSpaces() {
        assertEquals("  мир   привет ", StringProcessor.reverseWords("  привет   мир "));
    }

    @Test
    void reverseOnlySpaces() {
        assertEquals("   ", StringProcessor.reverseWords("   "));
    }

    @Test
    void hexExampleFromTask() {
        assertEquals("Васе 16 лет", StringProcessor.replaceHexWithDecimal("Васе 0x00000010 лет"));
    }

    @Test
    void hexSingleNumber() {
        assertEquals("Размер файла 1024 байт",
                StringProcessor.replaceHexWithDecimal("Размер файла 0x00000400 байт"));
    }

    @Test
    void hexTwoNumbers() {
        assertEquals("Порты 80 и 443",
                StringProcessor.replaceHexWithDecimal("Порты 0x00000050 и 0x000001BB"));
    }

    @Test
    void hexMaxValue() {
        assertEquals("4294967295", StringProcessor.replaceHexWithDecimal("0xFFFFFFFF"));
    }

    @Test
    void noHexNumbers() {
        assertEquals("Все платежи обработаны", StringProcessor.replaceHexWithDecimal("Все платежи обработаны"));
    }

    @Test
    void shortHexNotChanged() {
        assertEquals("адрес 0x12", StringProcessor.replaceHexWithDecimal("адрес 0x12"));
    }
}
