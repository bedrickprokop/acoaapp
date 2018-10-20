package br.com.acoaapp.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import br.com.acoaapp.constants.AppConstants;
import br.com.acoaapp.constants.StringConstants;

public class MoneyUtils {

    private MoneyUtils() {
    }

    public static String removeFormatMoney(String value) {
        return (value != null && !value.equals(StringConstants.EMPTY)) ?
                value.replace(StringConstants.UNITY_COIN_BRL, StringConstants.EMPTY)
                        .replace(StringConstants.DOT, StringConstants.EMPTY)
                        .replace(StringConstants.COMMA, StringConstants.DOT)
                        .replace(StringConstants.SPACE, StringConstants.EMPTY)
                        .replace(StringConstants.SPACE_AND_PIE, StringConstants.EMPTY)
                : StringConstants.EMPTY;
    }

    public static Double parse(final String amount) {
        return parse(amount, AppConstants.PtBR);
    }

    public static Double parse(final String amount, final Locale locale) {
        try {
            final NumberFormat format = NumberFormat.getNumberInstance(locale);
            if (format instanceof DecimalFormat) {
                ((DecimalFormat) format).setParseBigDecimal(true);
            }
            return format.parse(amount.replaceAll("[^\\d.,]", "")).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            return Double.valueOf(amount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(double value) {
        final Locale loc = AppConstants.PtBR;
        final NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
        return nf.format(value);
    }

    public static String currencySymbol() {
        final Locale loc = AppConstants.PtBR;
        final String symbol = NumberFormat.getCurrencyInstance(loc).getCurrency().getSymbol();
        return String.format("[%s. ]", symbol);
    }
}
