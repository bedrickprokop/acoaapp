package br.com.acoaapp.main.commons.components.customlinechart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import br.com.acoaapp.utils.NumberUtils;

public class CustomYAxisRightValueFormatter implements IAxisValueFormatter {

    private CustomLineChart.YAxisValueType mType;

    public CustomYAxisRightValueFormatter(CustomLineChart.YAxisValueType yAxisValueType) {
        this.mType = yAxisValueType;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (mType.equals(CustomLineChart.YAxisValueType.MONEY)) {
            return NumberUtils.postfixFormat(value);
        } else if(mType.equals(CustomLineChart.YAxisValueType.PERCENT)){
            return ((int) value) + "%";
        } else {
            return ((int) value) + "L";
        }
    }
}