package br.com.acoaapp.main.commons.components.customlinechart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


public class CustomXAxisValueFormatter implements IAxisValueFormatter {

    private String[] mDates;

    public CustomXAxisValueFormatter(String[] dates) {
        this.mDates = dates;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int index = (int) value;
        if(index < mDates.length) {
            return getDate(index).substring(0, 5);
        }
        return "";
    }

    private String getDate(int index) {
        return mDates[index];
    }
}