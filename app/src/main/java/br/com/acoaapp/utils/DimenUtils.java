package br.com.acoaapp.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class DimenUtils {

    private DimenUtils() {
    }

    public static int getPixelFromDp(Resources resources, int dpValue) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                resources.getDisplayMetrics());
    }
}
