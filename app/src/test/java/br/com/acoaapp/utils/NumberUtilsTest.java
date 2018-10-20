package br.com.acoaapp.utils;

import org.junit.Assert;
import org.junit.Test;

public class NumberUtilsTest {

    @Test
    public void testRandomIntValue() {
        int random = NumberUtils.randomIntValue(10, 0);
        Assert.assertTrue(random >= 0 && random <= 10);
    }

    @Test(expected = RuntimeException.class)
    public void testRandomIntValuesReverseOrder() {
        NumberUtils.randomIntValue(0, 10);
    }

    @Test
    public void testRandomLongValue() {
        long random = NumberUtils.randomLongValue(10, 0);
        Assert.assertTrue(random >= 0 && random <= 10);
    }

    @Test
    public void testFormatToPercentageWithDecimals() {
        //Float
        String formatted3 = NumberUtils.formatToPercentage(110.6666f, true);
        Assert.assertEquals("110,67%", formatted3);

        String formatted4 = NumberUtils.formatToPercentage(0.0f, true);
        Assert.assertEquals("0,00%", formatted4);

        //Double
        String formatted1 = NumberUtils.formatToPercentage(150.3456d, true);
        Assert.assertEquals("150,35%", formatted1);

        String formatted2 = NumberUtils.formatToPercentage(0.0d, true);
        Assert.assertEquals("0,00%", formatted2);
    }

    @Test
    public void testFormatToPercentageWithoutDecimals() {
        //Float
        String formatted3 = NumberUtils.formatToPercentage(110.6666f, false);
        Assert.assertEquals("111%", formatted3);

        String formatted4 = NumberUtils.formatToPercentage(0.0f, false);
        Assert.assertEquals("0%", formatted4);

        //Double
        String formatted1 = NumberUtils.formatToPercentage(150.3456, false);
        Assert.assertEquals("150%", formatted1);

        String formatted2 = NumberUtils.formatToPercentage(0.0, false);
        Assert.assertEquals("0%", formatted2);
    }

    @Test
    public void testFormatToPercentageWithNullValues() {
        String formatted1 = NumberUtils.formatToPercentage((Double) null, null);
        Assert.assertEquals("-", formatted1);

        String formatted2 = NumberUtils.formatToPercentage((Float) null, null);
        Assert.assertEquals("-", formatted2);
    }

    @Test
    public void testPostfixFormat() {
        String postfixFormat1 = NumberUtils.postfixFormat(12f);
        Assert.assertEquals("R$ 12", postfixFormat1);

        String postfixFormat2 = NumberUtils.postfixFormat(122341f);
        Assert.assertEquals("R$ 122 mil", postfixFormat2);

        String postfixFormat3 = NumberUtils.postfixFormat(9122341f);
        Assert.assertEquals("R$ 9 bi", postfixFormat3);

        String postfixFormat4 = NumberUtils.postfixFormat(1999122341f);
        Assert.assertEquals("R$ 1 tri", postfixFormat4);

        String postfixFormat5 = NumberUtils.postfixFormat(-81999122341f);
        Assert.assertEquals("R$ -81 tri", postfixFormat5);

        String postfixFormat6 = NumberUtils.postfixFormat(null);
        Assert.assertEquals("", postfixFormat6);
    }
}
