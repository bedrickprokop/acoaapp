package br.com.acoaapp.utils;

import java.util.List;

public class StringUtils {

    private StringUtils() {
    }

    public static String capEachWord(String str) {
        if (null != str) {
            String[] strArray = str.split(" ");
            StringBuilder builder = new StringBuilder();
            for (String s : strArray) {
                String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
                builder.append(cap).append(" ");
            }
            return builder.toString().trim();
        }
        return "";
    }

    public static String getInitials(String name) {
        if (null != name) {
            return name.substring(0, 1).toUpperCase();
        }
        return "";
    }

    public static boolean containsIgnoreCase(List<String> stringList, String text) {
        if (null != text && stringList.size() > 0) {
            for (String str : stringList) {
                if (text.toLowerCase().contains(str.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }
}
