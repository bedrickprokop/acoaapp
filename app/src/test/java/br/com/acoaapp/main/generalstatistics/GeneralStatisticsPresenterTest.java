package br.com.acoaapp.main.generalstatistics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import br.com.acoaapp.data.entity.DateFlowEntity;
import br.com.acoaapp.data.entity.GeneralStatisticsEntity;
import br.com.acoaapp.data.service.GeneralStatisticsServiceApi;
import br.com.acoaapp.main.generalstatistics.GeneralStatisticsContract;
import br.com.acoaapp.main.generalstatistics.GeneralStatisticsPresenter;

import static org.mockito.Mockito.verify;

public class GeneralStatisticsPresenterTest {

    private static GeneralStatisticsEntity GENERAL_STATISTICS;

    static {
        GENERAL_STATISTICS = new GeneralStatisticsEntity();
        GENERAL_STATISTICS.setTotalConsumptionPerYear(23577.04);
        GENERAL_STATISTICS.setTotalConsumptionPerMonth(4234.34);
        GENERAL_STATISTICS.setTotalConsumptionPerDay(4234.34);
        GENERAL_STATISTICS.setTotalAccountUsers(20);
        GENERAL_STATISTICS.setMonthlyConsumptionList(new ArrayList<DateFlowEntity>());
        GENERAL_STATISTICS.setDailyConsumptionList(new ArrayList<DateFlowEntity>());
        GENERAL_STATISTICS.setAverageConsumptionPerMonth(4234.34);
        GENERAL_STATISTICS.setAverageConsumptionPerHour(4234.34);
        GENERAL_STATISTICS.setAverageConsumptionPerDay(4234.34);
    }

    @Mock
    private GeneralStatisticsServiceApi generalStatisticsServiceApi;

    @Captor
    ArgumentCaptor<GeneralStatisticsServiceApi.Callback> generalStatisticsServiceCallbackArgumentCaptor;

    @Mock
    private GeneralStatisticsContract.View generalStatisticsView;
    private GeneralStatisticsContract.UserActionListener generalStatisticsPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        generalStatisticsPresenter = new GeneralStatisticsPresenter(generalStatisticsServiceApi,
                generalStatisticsView);
    }

    @Test
    public void testLoadStatistics() {
        generalStatisticsPresenter.loadStatistics(true);

        Mockito.verify(generalStatisticsView).setProgressIndicator(true);
        Mockito.verify(generalStatisticsServiceApi).loadStatsConsumption(
                generalStatisticsServiceCallbackArgumentCaptor.capture());
        generalStatisticsServiceCallbackArgumentCaptor.getValue().onLoaded(GENERAL_STATISTICS);
        Mockito.verify(generalStatisticsView).setProgressIndicator(false);
        Mockito.verify(generalStatisticsView).showStatistics(GENERAL_STATISTICS);
    }
}
