package br.com.acoaapp.utils;

import java.util.Random;

public class NumberUtils {

    private NumberUtils() {
    }

    public static int randomIntValue(final int max, final int min) {
        return new Random().nextInt(max - min) + min;
    }

    public static long randomLongValue(final long max, final long min) {
        return min + (long) (Math.random() * (max - min));
    }

    public static String formatToPercentage(Double value, Boolean withDecimals) {
        if (null != value && null != withDecimals) {
            String pattern = "%.0f";
            if (withDecimals) {
                pattern = "%.2f";
            }
            return String.format(pattern, value).replace(".", ",").concat("%");
        }
        return "-";
    }

    public static String formatToPercentage(Float value, Boolean withDecimals) {
        if (value != null) {
            String pattern = "%.0f";
            if (withDecimals) {
                pattern = "%.2f";
            }
            return String.format(pattern, value).replace(".", ",").concat("%");
        }
        return "-";
    }

    public static String postfixFormat(Float value) {
        if (value != null) {
            long longValue = value.longValue();
            boolean isNegativeValue = (longValue < 0);
            String strValue = String.valueOf(Math.abs(longValue));
            String postFix = "";
            int subtractIndex = 0;
            switch (strValue.length()) {
                case 1:
                case 2:
                case 3:
                    postFix = "";
                    break;
                case 4:
                case 5:
                case 6:
                    postFix = " mil";
                    subtractIndex = 3;
                    break;
                case 7:
                case 8:
                case 9:
                    postFix = " bi";
                    subtractIndex = 6;
                    break;
                case 10:
                case 11:
                case 12:
                    postFix = " tri";
                    subtractIndex = 9;
                    break;
            }
            strValue = strValue.substring(0, strValue.length() - subtractIndex);
            if (isNegativeValue) {
                strValue = "-".concat(strValue);
            }
            return "R$ " + strValue + postFix;
        }
        return "";
    }
}
