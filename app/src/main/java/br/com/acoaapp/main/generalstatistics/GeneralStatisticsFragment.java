package br.com.acoaapp.main.generalstatistics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import br.com.acoaapp.Injection;
import br.com.acoaapp.R;
import br.com.acoaapp.data.entity.DateFlowEntity;
import br.com.acoaapp.data.entity.GeneralStatisticsEntity;
import br.com.acoaapp.main.commons.components.customlinechart.CustomLineChart;

public class GeneralStatisticsFragment extends Fragment implements GeneralStatisticsContract.View {

    private GeneralStatisticsContract.UserActionListener mActionListener;
    private GeneralStatisticsEntity mGeneralStatisticsEntity;

    private CustomLineChart mChart;
    private ProgressBar mProgress;
    private CardView mCardView;

    public static GeneralStatisticsFragment newInstance() {
        return new GeneralStatisticsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_general_statistics, container, false);
        mActionListener = new GeneralStatisticsPresenter(Injection.provideStatisticsServiceApi(), this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupContentView(view);
        loadData();
    }

    private void setupContentView(View view) {
        mChart = view.findViewById(R.id.lc_dashboard);
        mProgress = view.findViewById(R.id.pb_general_statistics);
        mCardView = view.findViewById(R.id.cv_statistics);
    }

    private void loadData() {
        mActionListener.loadStatistics(true);
    }

    @Override
    public void setProgressIndicator(boolean isActive) {

    }

    @Override
    public void showStatistics(GeneralStatisticsEntity generalStatisticsEntity) {
        this.mGeneralStatisticsEntity = generalStatisticsEntity;
        mProgress.setVisibility(View.INVISIBLE);
        mChart.setVisibility(View.VISIBLE);
        mCardView.setVisibility(View.VISIBLE);

        List<DateFlowEntity> dailyConsumptionList = this.mGeneralStatisticsEntity.getDailyConsumptionList();
        if (dailyConsumptionList.size() > 0) {

            CustomLineChart.Header header = new CustomLineChart.Header();
            header.setTitle(getString(R.string.frag_general_statistics_line_chart_title));
            header.setUnderlinedTitleEnabled(false);

            List<String> chartLegends = new ArrayList<>();
            chartLegends.add(getString(R.string.frag_general_statistics_line_chart_legend_1));
            chartLegends.add(getString(R.string.frag_general_statistics_line_chart_legend_2));

            List<List<Entry>> yEntriesList = new ArrayList<>();
            List<Entry> dailyConsumption = new ArrayList<>();
            List<Entry> onuConsumption = new ArrayList<>();

            for (int i = 0; i < dailyConsumptionList.size(); i++) {
                Entry entryDailyConsumption = new Entry();
                entryDailyConsumption.setX(i);
                entryDailyConsumption.setData(dailyConsumptionList.get(i).getDate());
                entryDailyConsumption.setY(dailyConsumptionList.get(i).getFlowRate().floatValue());
                dailyConsumption.add(entryDailyConsumption);

                Entry entryOnuConsumption = new Entry();
                entryOnuConsumption.setX(i);
                entryOnuConsumption.setData(dailyConsumptionList.get(i).getDate());
                entryOnuConsumption.setY(110f * this.mGeneralStatisticsEntity.getTotalAccountUsers());
                onuConsumption.add(entryOnuConsumption);
            }
            yEntriesList.add(dailyConsumption);
            yEntriesList.add(onuConsumption);

            mChart.withHeader(header)
                    .withChartLegends(chartLegends)
                    .withYEntriesList(yEntriesList)
                    .withYEntriesType(CustomLineChart.YAxisValueType.LITERS)
                    .withYEntriesLineMode(new LineDataSet.Mode[]{LineDataSet.Mode.CUBIC_BEZIER,
                            LineDataSet.Mode.CUBIC_BEZIER})
                    .withLineColors(new int[]{ContextCompat.getColor(getContext(), R.color.flame),
                            ContextCompat.getColor(getContext(), R.color.moonstoneBlue)})
                    .withYAxisGranularity(110f).build();
        }
    }
}
