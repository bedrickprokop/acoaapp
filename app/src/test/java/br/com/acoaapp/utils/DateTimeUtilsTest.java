package br.com.acoaapp.utils;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtilsTest {

    @Test
    public void testToDate() {
        try {
            Date date = DateTimeUtils.toDate("01/02/2018", "dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            Assert.assertEquals(1, day);
            Assert.assertEquals(2, month);
            Assert.assertEquals(2018, year);

        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testToDateWithNullValues() {
        try {
            Date date = DateTimeUtils.toDate(null, null);
            Assert.assertNull(date);
        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testIsEqualOrGreaterThanCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 2);

        boolean equalOrGreaterThanCurrentDate = DateTimeUtils
                .isEqualOrGreaterThanCurrentDate(calendar.getTime());

        Assert.assertEquals(false, equalOrGreaterThanCurrentDate);
    }

    @Test
    public void testIsGreaterThanCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        boolean greaterThanCurrentDate = DateTimeUtils.isGreaterThanCurrentDate(calendar.getTime());
        Assert.assertEquals(true, greaterThanCurrentDate);
    }

    @Test
    public void testGetCurrentDate() {
        Date currentDate = DateTimeUtils.getCurrentDate();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(currentDate);
        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH) + 1;
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);

        Calendar calendar2 = Calendar.getInstance();
        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH) + 1;
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);

        Assert.assertEquals(year2, year1);
        Assert.assertEquals(month2, month1);
        Assert.assertEquals(day2, day1);
    }

    @Test
    public void testConvertStringToCalendar() {
        Calendar calendar = DateTimeUtils.convertStringToCalendar("21/12/2018");
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Assert.assertEquals(2018, year);
        Assert.assertEquals(12, month);
        Assert.assertEquals(21, day);
    }
}
