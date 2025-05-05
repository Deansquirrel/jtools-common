package com.github.deansquirrel.tools.common;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathToolTest {

    @Test
    public void testRandInt() {
        int min = 1;
        int max = 10;
        int result = MathTool.RandInt(min, max);
        assertTrue(result >= min && result < max, "Random integer should be within the specified range");
    }

    @Test
    void testRandInt2() {
        int min = 10;
        int max = 11;
        int result = MathTool.RandInt(min, max);
        assertTrue(result >= min && result < max, "Random integer should be within the specified range");
    }

    @Test
    void testRandIntWithSingleParameter() {
        int max = 10;
        int result = MathTool.RandInt(max);
        assertTrue(result >= 0 && result < max, "Random integer should be within the range [0, max)");
    }

    @Test
    void testAddDataByte() {
        Byte a = 10;
        Byte b = 20;
        Byte result = MathTool.addData(a, b);
        assertEquals((byte)30, result, "Byte addition should return the correct sum");
    }

    @Test
    void testAddDataInteger() {
        Integer a = 100;
        Integer b = 200;
        Integer result = MathTool.addData(a, b);
        assertEquals(300, result, "Integer addition should return the correct sum");
    }

    @Test
    void testAddDataBigInteger() {
        BigInteger a = new BigInteger("1000000000000000000");
        BigInteger b = new BigInteger("2000000000000000000");
        BigInteger result = MathTool.addData(a, b);
        assertEquals(new BigInteger("3000000000000000000"), result, "BigInteger addition should return the correct sum");
    }

    @Test
    void testAddDataBigDecimal() {
        BigDecimal a = new BigDecimal("100.50");
        BigDecimal b = new BigDecimal("200.75");
        BigDecimal result = MathTool.addData(a, b);
        assertEquals(new BigDecimal("301.25"), result, "BigDecimal addition should return the correct sum");
    }

    @Test
    void testSubtractBigDecimal() {
        BigDecimal a = new BigDecimal("500.00");
        BigDecimal b = new BigDecimal("200.50");
        BigDecimal result = MathTool.subtractBigDecimal(a, b);
        assertEquals(new BigDecimal("299.50"), result, "BigDecimal subtraction should return the correct result");
    }

    @Test
    void testMultiplyBigDecimal() {
        BigDecimal a = new BigDecimal("10.00");
        BigDecimal b = new BigDecimal("20.00");
        BigDecimal result = MathTool.multiplyBigDecimal(a, b);
        assertEquals(new BigDecimal("200.0000"), result, "BigDecimal multiplication should return the correct result");
    }

    @Test
    void testMultiplyBigDecimal2() {
        BigDecimal a = new BigDecimal("10.0");
        BigDecimal b = new BigDecimal("20.00");
        BigDecimal result = MathTool.multiplyBigDecimal(a, b);
        assertEquals(new BigDecimal("200.000"), result, "BigDecimal multiplication should return the correct result");
    }

    @Test
    void testMultiplyBigDecimal3() {
        BigDecimal a = new BigDecimal("10.00");
        BigDecimal b = new BigDecimal("20.0");
        BigDecimal result = MathTool.multiplyBigDecimal(a, b);
        assertEquals(new BigDecimal("200.000"), result, "BigDecimal multiplication should return the correct result");
    }

    @Test
    void testDivideBigDecimal() {
        BigDecimal a = new BigDecimal("100.00");
        BigDecimal b = new BigDecimal("20.00");
        BigDecimal result = MathTool.divideBigDecimal(a, b);
        assertEquals(new BigDecimal("5.00"), result, "BigDecimal division should return the correct result");
    }

    @Test
    void testTransDecimal() {
        BigDecimal num = new BigDecimal("100.00");
        int ret = 2;
        BigDecimal result = MathTool.transDecimal(num, ret);
        assertEquals(new BigDecimal("10000.00"), result, "TransDecimal should scale the number correctly");
    }

    @Test
    void testTransDecimal2() {
        BigDecimal num = new BigDecimal("100.00");
        int ret = -2;
        BigDecimal result = MathTool.transDecimal(num, ret);
        assertEquals(new BigDecimal("1.0000"), result, "TransDecimal should scale the number correctly");
    }

    @Test
    void testTransMoneyWan() {
        BigDecimal num = new BigDecimal("1000000.00");
        BigDecimal result = MathTool.transMoneyWan(num);
        assertEquals(new BigDecimal("100.000000"), result, "TransMoneyWan should scale the number correctly");
    }

    @Test
    void testGetPercentString() {
        BigDecimal t = new BigDecimal("200.00");
        BigDecimal c = new BigDecimal("50.00");
        String result = MathTool.getPercentString(t, c);
        assertEquals("25.00%", result, "Percentage string should be formatted correctly");
    }

    @Test
    void testGetPercentStringWithNumbers() {
        Number t = 200;
        Number c = 50;
        String result = MathTool.getPercentString(t, c);
        assertEquals("25.00%", result, "Percentage string should be formatted correctly");
    }

}