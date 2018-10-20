package br.com.acoaapp.main.commons.components.customlinechart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.acoaapp.R;
import br.com.acoaapp.constants.AppConstants;
import br.com.acoaapp.utils.DateTimeUtils;
import br.com.acoaapp.utils.StringUtils;

import static android.content.Context.WINDOW_SERVICE;

public class CustomLineChart extends LinearLayout {

    private Context mContext;
    private LineChart mLineChart;

    private TextView tvHeaderTitle, tvHeaderBodyLegendLeft, tvHeaderBodyLegendCenter, tvHeaderBodyLegendRight;
    private View vUnderlinedTitle;

    private LinearLayout mLinearLayoutFinancialLineChart, mLinearLayoutPagination;
    private ConstraintLayout clHeaderBody;

    private List<ChartPaginationItem> mChartPaginationItemList;
    private HorizontalScrollView mHorizontalScrollView;

    private String[] mDates;
    private List<List<Entry>> mYEntriesList;
    private boolean mDataValid = true;

    private List<Integer> monthsSizeList = new ArrayList<>();
    private Integer visibleRange = 0;

    private Header mHeader;
    private HeaderBody mHeaderBody;
    private List<String> mChartLegends;
    private CustomLineChart.YAxisValueType mYAxisValueType;
    private boolean mHeaderEnabled, mHeaderBodyEnabled, mChartLegendsEnabled, mYEntriesListEnabled,
            mLinesModesEnabled, mColorsEnabled;

    private LineDataSet.Mode[] mLinesModes;
    private static final LineDataSet.Mode DEFAULT_LINES_MODES[] = new LineDataSet.Mode[]{
            LineDataSet.Mode.CUBIC_BEZIER, LineDataSet.Mode.CUBIC_BEZIER, LineDataSet.Mode.CUBIC_BEZIER};

    private int[] mColors;
    private static final int[] DEFAULT_COLORS = new int[]{Color.DKGRAY, Color.GRAY, Color.LTGRAY};

    public CustomLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.custom_line_chart, this);

        this.mContext = context;
        this.mLinearLayoutFinancialLineChart = findViewById(R.id.flc);
        this.tvHeaderTitle = findViewById(R.id.tv_header_title);
        this.vUnderlinedTitle = findViewById(R.id.v_underlined_title);

        this.mLineChart = findViewById(R.id.lc_chart);
        this.mLinearLayoutPagination = findViewById(R.id.ll_pagination);
        this.mHorizontalScrollView = findViewById(R.id.hsv_pagination);

        // Defaults
        this.mLinesModes = DEFAULT_LINES_MODES;
        this.mYAxisValueType = YAxisValueType.PERCENT;
        this.mColors = DEFAULT_COLORS;
    }

    public CustomLineChart withHeader(Header header) {
        this.mHeader = header;
        this.mHeaderEnabled = true;
        return this;
    }

    public CustomLineChart withHeaderBody(HeaderBody headerBody) {
        this.mHeaderBody = headerBody;
        this.mHeaderBodyEnabled = true;
        return this;
    }

    public CustomLineChart withChartLegends(List<String> chartLegends) {
        this.mChartLegends = chartLegends;
        this.mChartLegendsEnabled = true;
        return this;
    }

    public CustomLineChart withYEntriesList(List<List<Entry>> yEntriesList) {
        this.mYEntriesList = yEntriesList;
        this.mYEntriesListEnabled = true;
        return this;
    }

    public CustomLineChart withYEntriesType(CustomLineChart.YAxisValueType yAxisValueType) {
        this.mYAxisValueType = yAxisValueType;
        return this;
    }

    public CustomLineChart withYEntriesLineMode(LineDataSet.Mode[] linesModes) {
        this.mLinesModes = linesModes;
        this.mLinesModesEnabled = true;
        return this;
    }

    public CustomLineChart withYAxisGranularity(Float yAxisGranularity) {
        YAxis axisLeft = mLineChart.getAxisLeft();
        axisLeft.setGranularityEnabled(true);
        axisLeft.setGranularity(yAxisGranularity);
        return this;
    }

    public CustomLineChart withLineColors(int[] colors) {
        this.mColors = colors;
        this.mColorsEnabled = true;
        return this;
    }

    public void build() {
        if (mYEntriesListEnabled) {
            validateYEntriesList();

            if (mLinesModesEnabled) {
                validateLinesMode();
            }
            if (mColorsEnabled) {
                validateColors();
            }
            if (mHeaderEnabled) {
                validateHeader();
            }
            if (mHeaderBodyEnabled) {
                validateHeaderBody();
            }
            mountChart();
        }
    }

    private void validateYEntriesList() {
        if (null != mYEntriesList && mYEntriesList.size() > 0) {
            int entriesSize = mYEntriesList.size();
            if (mChartLegendsEnabled) {
                validateChartLegends(entriesSize);
            } else {
                mChartLegends = new ArrayList<>();
                for (int i = 0; i < mYEntriesList.size(); i++) {
                    mChartLegends.add("No legend");
                }
            }

            int[] sizes = new int[entriesSize];
            for (int i = 0; i < entriesSize; i++) {
                if (null != mYEntriesList.get(i)) {
                    int size = mYEntriesList.get(i).size();
                    if (size == 0) {
                        mDataValid = false;
                        return;
                    }
                    sizes[i] = size;
                } else {
                    mDataValid = false;
                    return;
                }
            }

            for (int i = 0; i < sizes.length; i++) {
                for (int j = 0; j < sizes.length; j++) {
                    if (j == i) {
                        continue;
                    }
                    if (sizes[j] != sizes[i]) {
                        mDataValid = false;
                        return;
                    }
                }
            }
        } else {
            mDataValid = false;
        }
    }

    private void validateChartLegends(int chartLegendsSize) {
        validateStrList(mChartLegends, chartLegendsSize);
    }

    private void validateStrList(List<String> list, int expectedSize) {
        if (null != list) {
            int size = list.size();
            if (size < expectedSize) {
                for (int i = 0; i < expectedSize - size; i++) {
                    list.add("");
                }
            } else if (size > expectedSize) {
                list = list.subList(0, expectedSize);
            }
        } else {
            list = new ArrayList<>();
            for (int i = 0; i < expectedSize; i++) {
                list.add("");
            }
        }
    }

    private void validateColors() {
        if (null != this.mColors) {
            if (this.mColors.length != this.mYEntriesList.size()) {
                this.mColors = DEFAULT_COLORS;
            }
        } else {
            this.mColors = DEFAULT_COLORS;
        }
    }

    private void validateLinesMode() {
        if (null != this.mLinesModes) {
            if (this.mLinesModes.length != this.mYEntriesList.size()) {
                this.mLinesModes = DEFAULT_LINES_MODES;
            }
        } else {
            this.mLinesModes = DEFAULT_LINES_MODES;
        }
    }

    private void validateHeader() {
        if (null != mHeader) {
            mHeader.title = (null != mHeader.title) ? mHeader.title : "";
        }
    }

    private void validateHeaderBody() {
        if (null != mHeaderBody) {
            validateStrList(mHeaderBody.legendValues, HeaderBody.DEFAULT_LINE_ARRAY_SIZE);
            if (null != mHeaderBody.linesValues) {
                for (List<String> list : mHeaderBody.linesValues) {
                    validateStrList(list, HeaderBody.DEFAULT_LINE_ARRAY_SIZE);
                }
            }
        } else {
            mDataValid = false;
        }
    }

    private void mountChart() {
        if (mDataValid) {
            this.mLinearLayoutFinancialLineChart.setVisibility(VISIBLE);
            this.setupDatesArray();

            if (mHeaderEnabled) {
                this.tvHeaderTitle.setText(mHeader.title);
                this.tvHeaderTitle.setVisibility(VISIBLE);
                int visibility = mHeader.isUnderlinedTitleEnabled ? VISIBLE : GONE;
                this.vUnderlinedTitle.setVisibility(visibility);
            }

            if (mHeaderBodyEnabled) {
                this.clHeaderBody.setVisibility(VISIBLE);
                this.tvHeaderBodyLegendLeft.setText(mHeaderBody.legendValues.get(0));
                this.tvHeaderBodyLegendCenter.setText(mHeaderBody.legendValues.get(1));
                this.tvHeaderBodyLegendRight.setText(mHeaderBody.legendValues.get(2));
            }
            buildChart();
        }
    }

    private void setupDatesArray() {
        int size = mYEntriesList.get(0).size();
        mDates = new String[size];
        for (int i = 0; i < size; i++) {
            Date date = (Date) mYEntriesList.get(0).get(i).getData();
            String strDate = DateTimeUtils.format(date, "dd/MM/yyyy");
            mDates[i] = strDate;
        }
    }

    /**
     * Método que desenha o gráfico com os dados recebidos
     */
    private void buildChart() {
        buildPagination();

        List<ILineDataSet> linesDataSet = new ArrayList<>();
        for (int i = 0; i < mYEntriesList.size(); i++) {

            //Cria a linha com o array dos dados e array dos títulos
            LineDataSet lineDataSet = new LineDataSet(mYEntriesList.get(i),
                    (String) mChartLegends.toArray()[i]);

            //Habilita as linhas guias de destaque quando se clica no gráfico
            lineDataSet.setDrawHighlightIndicators(true);
            lineDataSet.setHighlightEnabled(true);
            lineDataSet.setHighLightColor(getResources().getColor(R.color.colorGrayDisabled));
            lineDataSet.setHighlightLineWidth(2);
            lineDataSet.setDrawHorizontalHighlightIndicator(false);

            //Define a espessura da linha
            lineDataSet.setLineWidth(3);

            //Define a cor das linhas
            int color = (i == 0) ? mColors[0] : mColors[1];
            lineDataSet.setColor(color);

            //Habilita para desenhar os círculos/pontos em cada cruzamento dos eixos x e y
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);

            //Define o modo que as linhas serão exibidas - curvas
            LineDataSet.Mode mode = (i == 0) ? mLinesModes[0] : mLinesModes[1];
            lineDataSet.setMode(mode);
            lineDataSet.setCubicIntensity(0.4f);
            linesDataSet.add(lineDataSet);
        }

        //Desenha a legenda no gráfico
        Legend legend = mLineChart.getLegend();
        List<LegendEntry> legendEntryList = new ArrayList<>();
        for (int i = 0; i < mChartLegends.size(); i++) {
            LegendEntry legendEntry = new LegendEntry();
            legendEntry.label = mChartLegends.get(i);
            legendEntry.form = Legend.LegendForm.CIRCLE;
            legendEntry.formColor = mColors[i];
            legendEntry.formSize = 8;
            legendEntryList.add(legendEntry);
        }
        legend.setCustom(legendEntryList);
        legend.setTypeface(ResourcesCompat.getFont(mContext, R.font.montserrat));
        //legend.setLabelTextSize(); recebe um float em dp e não em sp
        legend.setXEntrySpace(40);
        legend.setFormToTextSpace(10);
        legend.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);

        //Propriedades eixo x
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setEnabled(true);

        //trocar para 'true' ou 'false' para visualização dos labels
        xAxis.setDrawLabels(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawLimitLinesBehindData(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setLabelRotationAngle(-50);
        //xAxis.setValueFormatter(new CustomXAxisValueFormatter(mDates));
        //xAxis.setCenterAxisLabels(true);
        //xAxis.setSpaceMax(0.05f);
        //xAxis.setSpaceMin(0.05f);
        //xAxis.setDrawLimitLinesBehindData();
        //xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelCount(7, true);
        xAxis.removeAllLimitLines();

        //Propriedades eixo y da esquerda
        YAxis axisLeft = mLineChart.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawLabels(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setDrawGridLines(true);
        axisLeft.enableGridDashedLine(20f, 10f, 0);
        axisLeft.setDrawLimitLinesBehindData(false);
        axisLeft.setDrawZeroLine(false);
        axisLeft.setDrawTopYLabelEntry(true);

        //Propriedades eixo y da direita
        YAxis axisRight = mLineChart.getAxisRight();
        axisRight.setEnabled(true);
        axisRight.setDrawLabels(true);
        axisRight.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        //axisRight.enableGridDashedLine(20f, 10f, 0);
        axisRight.setDrawLimitLinesBehindData(false);
        axisRight.setDrawZeroLine(false);
        axisRight.setDrawTopYLabelEntry(true);
        axisRight.setTextSize(13);
        axisRight.setTextColor(getResources().getColor(R.color.taupe));
        axisRight.setValueFormatter(new CustomYAxisRightValueFormatter(this.mYAxisValueType));
        Typeface typeface = ResourcesCompat.getFont(mContext, R.font.montserrat_medium);
        axisRight.setTypeface(typeface);
        //Renderer personalizado para desenhar os círculos somente no meio do gráfico
        //mLineChart.setRenderer(new FinancialLineChartRenderer(mLineChart, mLineChart.getAnimator(),
        //        mLineChart.getViewPortHandler()));

        LineData lineData = new LineData(linesDataSet);
        CustomMarkerView customMarkerView = new CustomMarkerView(mContext, this.mYAxisValueType,
                this.mColors, mLineChart);

        mLineChart.setData(lineData);
        mLineChart.setBackgroundColor(getResources().getColor(R.color.isabelline));
        mLineChart.setDrawGridBackground(false);
        mLineChart.setDrawBorders(false);
        mLineChart.setMarker(customMarkerView);
        mLineChart.setExtraOffsets(0, 25, 0, 0);
        mLineChart.setDescription(null);
        mLineChart.animateY(1000);
        //mLineChart.setVisibleXRange(visibleRange, visibleRange);
        mLineChart.setDoubleTapToZoomEnabled(false);
        mLineChart.setPinchZoom(false);
        mLineChart.setDragEnabled(false);
        mLineChart.setHighlightPerTapEnabled(true);
        mLineChart.setLongClickable(false);
        mLineChart.setClickable(true);
        mLineChart.invalidate();
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                int size = mLineChart.getData().getDataSets().size();
                Highlight highlightArray[] = new Highlight[size];

                for (int j = 0; j < size; j++) {
                    IDataSet iDataSet = mLineChart.getData().getDataSets().get(j);

                    for (int i = 0; i < ((LineDataSet) iDataSet).getValues().size(); i++) {
                        if (((LineDataSet) iDataSet).getValues().get(i).getX() == entry.getX()) {
                            highlightArray[j] = new Highlight(entry.getX(), entry.getY(), j);
                        }
                    }
                }
                mLineChart.highlightValues(highlightArray);
            }

            @Override
            public void onNothingSelected() {
            }
        });
    }

    private void buildPagination() {
        setChartPaginationValues();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int horizontalScreenWidth = displayMetrics.widthPixels;

        for (int i = 0; i < mChartPaginationItemList.size(); i++) {
            TextView textView = new TextView(mContext);
            textView.setWidth(horizontalScreenWidth / (monthsSizeList.size() >= 3 ? 3 : monthsSizeList.size()));
            textView.setText(mChartPaginationItemList.get(i).monthName + " " + mChartPaginationItemList.get(i).year);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            textView.setTextColor(getResources().getColor(R.color.taupe));
            textView.setTypeface(ResourcesCompat.getFont(mContext, R.font.montserrat_light));
            textView.setPadding(15, 15, 15, 15);
            textView.setGravity(Gravity.CENTER);
            if (i > 0 && i < mChartPaginationItemList.size() - 1) {
                final int indexPosition = i;
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        changePaginationStyle(indexPosition);

                        TextView tv = (TextView) mLinearLayoutPagination.getChildAt(indexPosition - 1);
                        mHorizontalScrollView.scrollTo(tv.getLeft(), tv.getTop());
                        moveChartToPosition(indexPosition);
                    }
                });
            } else {
                textView.setOnClickListener(null);
            }
            this.mLinearLayoutPagination.addView(textView);
        }

        if (monthsSizeList.size() >= 3) {
            //exibe na metade do array retornado e não pela data atual
            mHorizontalScrollView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int position = mChartPaginationItemList.size() / 2;
                    mLinearLayoutPagination = (LinearLayout) mHorizontalScrollView.getChildAt(0);
                    changePaginationStyle(position);

                    TextView tv = (TextView) mLinearLayoutPagination.getChildAt(position - 1);
                    mHorizontalScrollView.scrollTo(tv.getLeft(), tv.getTop());
                    moveChartToPosition(position);
                }
            }, 100);
        } else {
            mHorizontalScrollView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLinearLayoutPagination = (LinearLayout) mHorizontalScrollView.getChildAt(0);

                    TextView tv = (TextView) mLinearLayoutPagination.getChildAt(0);
                    mHorizontalScrollView.scrollTo(tv.getLeft(), tv.getTop());
                }
            }, 100);
        }
    }

    /**
     * Gera uma lista de objetos de paginação, cada qual conterá o nome do mês, o último dia deste
     * mês e o índice do array deste dia para fazer a navegação
     */
    private void setChartPaginationValues() {
        this.mChartPaginationItemList = new ArrayList<>();
        Date initialDate = null, finalDate = null;

        try {
            finalDate = DateTimeUtils.toDate(mDates[mDates.length - 1], "dd/MM/yyyy");
            initialDate = DateTimeUtils.toDate(mDates[0], "dd/MM/yyyy");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calInitialDate = Calendar.getInstance();
        calInitialDate.setTime(initialDate);
        Calendar calFinalDate = Calendar.getInstance();
        calFinalDate.setTime(finalDate);

        int idxLastDayOfMonth = 0;
        while (calInitialDate.compareTo(calFinalDate) <= 0) {
            String monthName = StringUtils.capEachWord(calInitialDate.getDisplayName(Calendar.MONTH, Calendar.LONG,
                    AppConstants.PtBR)).trim();

            for (int i = idxLastDayOfMonth; i < mDates.length - 1; i++) {
                String strMonth = mDates[i + 1].split("/")[1];
                Integer month = Integer.parseInt(strMonth) - 1;
                idxLastDayOfMonth = i;
                if (!month.equals(calInitialDate.get(Calendar.MONTH))) {
                    break;
                }
            }
            ChartPaginationItem chartPaginationItem = new ChartPaginationItem();
            chartPaginationItem.idxLastDayOfMonth = idxLastDayOfMonth;
            chartPaginationItem.monthName = monthName;
            chartPaginationItem.year = String.valueOf(calInitialDate.get(Calendar.YEAR)).substring(2, 4);
            this.mChartPaginationItemList.add(chartPaginationItem);

            monthsSizeList.add(getTotalWorkingDaysOfMonth(calInitialDate));
            calInitialDate.add(Calendar.MONTH, 1);
        }
        visibleRange = getVisibleRange(monthsSizeList);
    }

    private Integer getVisibleRange(List<Integer> monthsSizeList) {
        visibleRange = 0;
        List<Integer> months;
        int size = monthsSizeList.size();
        if (size >= 3) {
            months = monthsSizeList.subList((monthsSizeList.size() / 2) - 1, (monthsSizeList.size() / 2) + 2);
        } else {
            months = monthsSizeList.subList(0, monthsSizeList.size());
        }
        for (int i = 0; i < months.size(); i++) {
            visibleRange += months.get(i);
        }
        return visibleRange;
    }

    private Integer getTotalWorkingDaysOfMonth(Calendar calendar) {
        Calendar cal = Calendar.getInstance();
        cal.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);

        int totalDays = 0;
        while (cal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
            if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                totalDays++;
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return totalDays;
    }

    private void changePaginationStyle(int indexPosition) {
        for (int i = 0; i < mLinearLayoutPagination.getChildCount(); i++) {
            TextView child = (TextView) mLinearLayoutPagination.getChildAt(i);
            if (indexPosition == i) {
                child.setTypeface(ResourcesCompat.getFont(mContext, R.font.montserrat_medium),
                        Typeface.BOLD);
            } else {
                child.setTypeface(ResourcesCompat.getFont(mContext, R.font.montserrat_light),
                        Typeface.NORMAL);
            }
        }
    }

    private void moveChartToPosition(int indexPosition) {
        ChartPaginationItem current = mChartPaginationItemList.get(indexPosition);

        List<Integer> sublist;
        if (monthsSizeList.size() >= 3) {
            sublist = monthsSizeList.subList(indexPosition - 1, indexPosition + 2);
        } else {
            sublist = monthsSizeList.subList(0, monthsSizeList.size());
        }
        Integer visibleRange = getVisibleRange(sublist);
        int idxMiddleSelectedMonth = (current.idxLastDayOfMonth + 1) - (monthsSizeList.get(indexPosition) / 2);

        mLineChart.setVisibleXRange(visibleRange, visibleRange);
        mLineChart.moveViewToAnimated(idxMiddleSelectedMonth - (visibleRange / 2), mLineChart.getY(), YAxis.AxisDependency.LEFT, 350);
        mLineChart.highlightValue(idxMiddleSelectedMonth, 0);
        mLineChart.highlightValue(idxMiddleSelectedMonth, 1);
        mLineChart.invalidate();
    }

    public static class Header {
        private String title;
        private boolean isUnderlinedTitleEnabled = true;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUnderlinedTitleEnabled(boolean underlinedTitleEnabled) {
            isUnderlinedTitleEnabled = underlinedTitleEnabled;
        }
    }

    public static class HeaderBody {
        private static final int DEFAULT_LINE_ARRAY_SIZE = 3;
        private List<String> legendValues;
        private List<List<String>> linesValues;

        public List<String> getLegendValues() {
            return legendValues;
        }

        public void setLegendValues(List<String> legendValues) {
            this.legendValues = legendValues;
        }

        public List<List<String>> getLinesValues() {
            return linesValues;
        }

        public void setLinesValues(List<List<String>> linesValues) {
            this.linesValues = linesValues;
        }
    }

    private class ChartPaginationItem {
        int idxLastDayOfMonth;
        String monthName;
        String year;
    }

    public enum YAxisValueType {
        MONEY, PERCENT, LITERS
    }
}
