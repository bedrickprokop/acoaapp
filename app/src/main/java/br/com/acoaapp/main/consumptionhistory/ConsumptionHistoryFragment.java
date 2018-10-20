package br.com.acoaapp.main.consumptionhistory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.acoaapp.Injection;
import br.com.acoaapp.R;
import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;

public class ConsumptionHistoryFragment extends Fragment implements ConsumptionHistoryContract.View {

    private ConsumptionHistoryContract.UserActionListener mActionListener;
    private ConsumptionHistoryEntity mConsumptionHistoryEntity;

    private BarChart mBarChart;
    private HorizontalBarChart mHorizontalBarChart;
    private ProgressBar mProgress;

    public static ConsumptionHistoryFragment newInstance() {
        return new ConsumptionHistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_consumption_history, container, false);
        mActionListener = new ConsumptionHistoryPresenter(Injection.provideConsumptionHistoryServiceApi(), this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupContentView(view);
        loadData();
    }

    private void setupContentView(View view) {
        mBarChart = view.findViewById(R.id.bc_history);
        mHorizontalBarChart = view.findViewById(R.id.hbc_history);
        mProgress = view.findViewById(R.id.pb_consumption_history);
    }

    private void loadData() {
        mActionListener.loadHistory(true);
    }

    @Override
    public void setProgressIndicator(boolean isActive) {

    }

    @Override
    public void showHistory(ConsumptionHistoryEntity consumptionHistoryEntity) {
        this.mConsumptionHistoryEntity = consumptionHistoryEntity;
        mProgress.setVisibility(View.INVISIBLE);
        mBarChart.setVisibility(View.VISIBLE);
        mHorizontalBarChart.setVisibility(View.VISIBLE);

        int[] colors = new int[2];
        colors[0] = ContextCompat.getColor(getContext(), R.color.moonstoneBlue);
        colors[1] = ContextCompat.getColor(getContext(), R.color.flame);

        setupBarChart(consumptionHistoryEntity.getTotalMonthConsumption(), colors);
        setupHorizontalBarChart(consumptionHistoryEntity.getTotalYearsConsumption(), colors);
    }

    private void setupBarChart(Map<String, Double> totalMonthConsumption, int[] baseColors) {
        ArrayList<BarEntry> values = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        int[] colors = new int[12];
        int counter = 0;
        for (Map.Entry<String, Double> entry : totalMonthConsumption.entrySet()) {
            float value = entry.getValue().floatValue();
            values.add(new BarEntry(counter, value));
            labels.add(entry.getKey());
            colors[counter] = (value <= 6000) ? baseColors[0] : baseColors[1];
            counter++;
        }

        BarDataSet barDataSet = new BarDataSet(values, "");
        barDataSet.setDrawValues(true);
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setColors(colors);

        BarData barData = new BarData(barDataSet);
        barData.setValueTextSize(12f);
        barData.setValueTextColor(ContextCompat.getColor(getContext(), R.color.taupe));

        mBarChart.setData(barData);
        mBarChart.animate();
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.isabelline));

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setLabelCount(12);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setLabelRotationAngle(-45);
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.taupe));
        xAxis.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        // Hide grid lines
        YAxis axisLeft = mBarChart.getAxisLeft();
        axisLeft.setEnabled(false);
        YAxis axisRight = mBarChart.getAxisRight();
        axisRight.setEnabled(false);

        // Legend
        LegendEntry lowLegendEntry = new LegendEntry();
        lowLegendEntry.label = "Consumo moderado";
        lowLegendEntry.form = Legend.LegendForm.CIRCLE;
        lowLegendEntry.formColor = baseColors[0];

        LegendEntry highLegendEntry = new LegendEntry();
        highLegendEntry.label = "Consumo elevado";
        highLegendEntry.form = Legend.LegendForm.CIRCLE;
        highLegendEntry.formColor = baseColors[1];

        List<LegendEntry> legendEntryList = new ArrayList<>();
        legendEntryList.add(lowLegendEntry);
        legendEntryList.add(highLegendEntry);

        Legend legend = mBarChart.getLegend();
        legend.setEnabled(true);
        legend.setCustom(legendEntryList);
        legend.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_medium));
        legend.setXEntrySpace(20);
        legend.setFormToTextSpace(5);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        mBarChart.setDrawGridBackground(false);
        mBarChart.setDrawBorders(false);
        mBarChart.setDescription(null);
        mBarChart.animateY(1000);
        mBarChart.setDoubleTapToZoomEnabled(false);
        mBarChart.setPinchZoom(false);
        mBarChart.setDragEnabled(false);
        mBarChart.setHighlightPerTapEnabled(true);
        mBarChart.setLongClickable(false);
        mBarChart.setClickable(true);
        mBarChart.invalidate();
    }

    private void setupHorizontalBarChart(Map<Integer, Double> totalYearsConsumption, int[] baseColors) {
        ArrayList<BarEntry> values = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        int[] colors = new int[4];
        int counter = 0;
        for (Map.Entry<Integer, Double> entry : totalYearsConsumption.entrySet()) {
            float value = entry.getValue().floatValue();
            values.add(new BarEntry(counter, value));
            labels.add(entry.getKey().toString());
            colors[counter] = (value <= 600000) ? baseColors[0] : baseColors[1];
            counter++;
        }

        BarDataSet barDataSet = new BarDataSet(values, "");
        barDataSet.setDrawValues(true);
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setColors(colors);

        BarData barData = new BarData(barDataSet);
        barData.setValueTextSize(12f);
        barData.setValueTextColor(ContextCompat.getColor(getContext(), R.color.taupe));

        mHorizontalBarChart.setData(barData);
        mHorizontalBarChart.animate();
        mHorizontalBarChart.setDrawValueAboveBar(true);
        mHorizontalBarChart.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.isabelline));

        XAxis xAxis = mHorizontalBarChart.getXAxis();
        xAxis.setLabelCount(4);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setLabelRotationAngle(0);
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.taupe));
        xAxis.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        // Hide grid lines
        YAxis axisLeft = mHorizontalBarChart.getAxisLeft();
        axisLeft.setEnabled(false);
        YAxis axisRight = mHorizontalBarChart.getAxisRight();
        axisRight.setEnabled(false);

        // Legend
        LegendEntry lowLegendEntry = new LegendEntry();
        lowLegendEntry.label = "Consumo moderado";
        lowLegendEntry.form = Legend.LegendForm.CIRCLE;
        lowLegendEntry.formColor = baseColors[0];

        LegendEntry highLegendEntry = new LegendEntry();
        highLegendEntry.label = "Consumo elevado";
        highLegendEntry.form = Legend.LegendForm.CIRCLE;
        highLegendEntry.formColor = baseColors[1];

        List<LegendEntry> legendEntryList = new ArrayList<>();
        legendEntryList.add(lowLegendEntry);
        legendEntryList.add(highLegendEntry);

        Legend legend = mHorizontalBarChart.getLegend();
        legend.setEnabled(true);
        legend.setCustom(legendEntryList);
        legend.setTypeface(ResourcesCompat.getFont(getContext(), R.font.montserrat_medium));
        legend.setXEntrySpace(20);
        legend.setFormToTextSpace(5);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        mHorizontalBarChart.setDrawGridBackground(false);
        mHorizontalBarChart.setDrawBorders(false);
        mHorizontalBarChart.setDescription(null);
        mHorizontalBarChart.animateY(1000);
        mHorizontalBarChart.setDoubleTapToZoomEnabled(false);
        mHorizontalBarChart.setPinchZoom(false);
        mHorizontalBarChart.setDragEnabled(false);
        mHorizontalBarChart.setHighlightPerTapEnabled(true);
        mHorizontalBarChart.setLongClickable(false);
        mHorizontalBarChart.setClickable(true);
        mHorizontalBarChart.invalidate();
    }
}
