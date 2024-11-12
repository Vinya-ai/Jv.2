package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculateTest {

    @Test
    public void testSimpleExpression() {
        Calculate calc = new Calculate("3+2");
        assertEquals("5.0", calc.compute(), "Должно быть 5.0");
    }

    @Test
    public void testComplexExpression() {
        Calculate calc = new Calculate("10 + 2 * 6");
        assertEquals("22.0", calc.compute(), "Должно быть 22.0");
    }

    @Test
    public void testExpressionWithParentheses() {
        Calculate calc = new Calculate("(10 + 2) * 6");
        assertEquals("72.0", calc.compute(), "Должно быть 72.0");
    }

    @Test
    public void testDivisionByZero() {
        Calculate calc = new Calculate("10 / 0");
        assertEquals("Ошибка: Деление на ноль", calc.compute(), "Должна быть ошибка деления на ноль");
    }

    @Test
    public void testExpressionWithSpaces() {
        Calculate calc = new Calculate("  3 +  5  ");
        assertEquals("8.0", calc.compute(), "Должно быть 8.0");
    }

    @Test
    public void testIncorrectExpression() {
        Calculate calc = new Calculate("3+*5");
        assertEquals("Ошибка: Некорректное выражение.", calc.compute(), "Должна быть ошибка из-за некорректного выражения");
    }

    @Test
    public void testEmptyExpression() {
        Calculate calc = new Calculate("");
        assertEquals("Ошибка: Некорректное выражение.", calc.compute(), "Пустое выражение должно возвращать ошибку");
    }

    @Test
    public void testExpressionWithNegativeNumbers() {
        Calculate calc = new Calculate("3 - 5");
        assertEquals("-2.0", calc.compute(), "Должно быть -2.0");
    }

    @Test
    public void testExpressionWithMultipleOperators() {
        Calculate calc = new Calculate("2+3*4-5");
        assertEquals("9.0", calc.compute(), "Должно быть 9.0");
    }

    @Test
    public void testComplexExpressionWithAllOperators() {
        Calculate calc = new Calculate("3 + (6 * 2 - (4 / 2))");
        assertEquals("13.0", calc.compute(), "Должно быть 13.0");
    }
}
