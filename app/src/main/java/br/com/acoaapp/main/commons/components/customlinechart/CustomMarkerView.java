package br.com.acoaapp.main.commons.components.customlinechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import br.com.acoaapp.R;
import br.com.acoaapp.utils.NumberUtils;

public class CustomMarkerView extends MarkerView {

    private int[] colorsArray;
    private CustomLineChart.YAxisValueType mType;

    private ImageView ivPoint;
    private TextView tvLeftValue, tvRightValue;
    private LineChart lineChart;

    //TODO another solution - do not delete
    //private TextView tvValue;
    //private LinearLayout llFinancialMarkerView;

    public CustomMarkerView(Context context, CustomLineChart.YAxisValueType yAxisValueType,
                            int[] colorsArray, LineChart lineChart) {
        super(context, R.layout.custom_marker_view);
        this.lineChart = lineChart;
        this.mType = yAxisValueType;
        this.colorsArray = colorsArray;

        tvLeftValue = findViewById(R.id.tv_left_value);
        ivPoint = findViewById(R.id.iv_point);
        tvRightValue = findViewById(R.id.tv_right_value);


        //TODO another solution - do not delete
        //llFinancialMarkerView = findViewById(R.id.ll_financial_marker_view);
        /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.width = 15;
        params.height = 15;
        ivPoint = new ImageView(context);
        ivPoint.setBackground(context.getDrawable(R.drawable.custom_marker_view_shape));
        ivPoint.setLayoutParams(params);
        tvValue = new TextView(context);*/
    }

    @Override
    public void refreshContent(Entry entry, Highlight highlight) {
        int dataSetIndex = highlight.getDataSetIndex();
        Drawable background = ivPoint.getBackground();
        background.setColorFilter(colorsArray[dataSetIndex], PorterDuff.Mode.ADD);

        //TODO another solution - do not delete
        //tvValue.setText(formatValue(entry.getY()));
        //tvValue.setTextColor(colorsArray[dataSetIndex]);
        //llFinancialMarkerView.removeAllViews();
        //llFinancialMarkerView.refreshDrawableState();

        int lineChartWidth = lineChart != null ? lineChart.getWidth() : 0;
        if (highlight.getDrawX() > lineChartWidth / 2) {
            tvRightValue.setVisibility(GONE);
            tvLeftValue.setVisibility(VISIBLE);
            tvLeftValue.setText(formatValue(entry.getY()));
            tvLeftValue.setTextColor(colorsArray[dataSetIndex]);

            //TODO another solution - do not delete
            //llFinancialMarkerView.addView(tvValue);
            //llFinancialMarkerView.addView(ivPoint);
        } else {
            tvLeftValue.setVisibility(GONE);
            tvRightValue.setVisibility(VISIBLE);
            tvRightValue.setText(formatValue(entry.getY()));
            tvRightValue.setTextColor(colorsArray[dataSetIndex]);

            //TODO another solution - do not delete
            //llFinancialMarkerView.addView(ivPoint);
            //llFinancialMarkerView.addView(tvValue);
        }
        super.refreshContent(entry, highlight);
    }

    public String formatValue(float value) {
        if (mType.equals(CustomLineChart.YAxisValueType.MONEY)) {
            return value > 0 ? "+ " + "R$" + value : "- " + "R$" + value;
        } else if (mType.equals(CustomLineChart.YAxisValueType.PERCENT)) {
            return NumberUtils.formatToPercentage(value, true);
        } else {
            return value + "L";
        }
    }

    @Override
    public MPPointF getOffset() {
        int height = getHeight();
        int width = getWidth();

        //int widthPercent = (height * 50) / 100;
        //int heightPercent = (height * 50) / 100;
        return new MPPointF(-(width/* - widthPercent*/), -(height/* + heightPercent*/));
    }

    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        int lineChartWidth = lineChart != null ? lineChart.getWidth() : 0;
        float viewWidth = getWidth();
        float viewHeight = getHeight();
        float widthSubtract = 24;

        //alinha o tooltip para a esquerda
        if (posX > lineChartWidth / 2) {
            posX -= (viewWidth - widthSubtract);
        } else {
            posX += -widthSubtract;
        }

        canvas.translate(posX, posY - (viewHeight / 2));
        draw(canvas);
        canvas.translate(-posX, -(posY - (viewHeight / 2)));
    }
}