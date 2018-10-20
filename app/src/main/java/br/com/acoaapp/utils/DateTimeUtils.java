package br.com.acoaapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    public static Date toDate(final String value, final String format) throws ParseException {
        if (value != null && format != null) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            return dateFormat.parse(value);
        }
        return null;
    }

    public static String format(final Date date, final String format) {
        if (date != null) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            return dateFormat.format(date);
        }
        return null;
    }

    public static boolean isValidDate(final String date, final String format) {
        if (date == null || date.length() != format.length()) {
            return false;
        }

        final DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isEqualOrGreaterThanCurrentDate(final Date date) {
        return date.compareTo(getCurrentDate()) >= 0;
    }

    public static boolean isGreaterThanCurrentDate(final Date date) {
        return date.compareTo(getCurrentDate()) > 0;
    }

    public static Date getCurrentDate() {
        try {
            final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(format.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentDate(String pattern) {
        // "yyyy-MM-dd'T'HH:mm:ss"
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(new Date());
    }

    public static Calendar convertStringToCalendar(String date) {
        Calendar calendar = Calendar.getInstance();
        String[] dateExtract = date.split("/");
        int ppYear, ppMonth, ppDay;
        ppDay = Integer.parseInt(dateExtract[0]);
        ppMonth = Integer.parseInt(dateExtract[1]) - 1;
        ppYear = Integer.parseInt(dateExtract[2]);
        calendar.set(ppYear, ppMonth, ppDay);
        return calendar;
    }

    public static Date subtractDays(Date date, int days) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, days * (-1));
            return calendar.getTime();
        }
        return null;
    }

    public static Date addDays(Date date, int days) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return calendar.getTime();
        }
        return null;
    }

    public static Date addMonths(Date date, int months) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, months);
            return calendar.getTime();
        }
        return null;
    }

    public static Date addYears(Date date, int years) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, years);
            return calendar.getTime();
        }
        return null;
    }
}
