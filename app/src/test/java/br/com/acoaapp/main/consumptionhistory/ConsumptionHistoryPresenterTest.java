package br.com.acoaapp.main.consumptionhistory;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;
import br.com.acoaapp.data.service.ConsumptionHistoryServiceApi;

public class ConsumptionHistoryPresenterTest {

    private static ConsumptionHistoryEntity CONSUMPTION_HISTORY;

    static {
        CONSUMPTION_HISTORY = new ConsumptionHistoryEntity();
        CONSUMPTION_HISTORY.setTotalYearsConsumption(new HashMap<Integer, Double>());
        CONSUMPTION_HISTORY.setTotalMonthConsumption(new HashMap<String, Double>());
    }

    @Mock
    private ConsumptionHistoryServiceApi consumptionHistoryServiceApi;

    @Captor
    ArgumentCaptor<ConsumptionHistoryServiceApi.ConsumptionHistoryCallback> consumptionHistoryServiceCallbackArgumentCaptor;

    @Mock
    private ConsumptionHistoryContract.View consumptionHistoryView;
    private ConsumptionHistoryContract.UserActionListener consumptionHistoryPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        consumptionHistoryPresenter = new ConsumptionHistoryPresenter(consumptionHistoryServiceApi,
                consumptionHistoryView);
    }

    @Test
    public void testeLoadHistory() {
        consumptionHistoryPresenter.loadHistory(true);

        Mockito.verify(consumptionHistoryView).setProgressIndicator(true);
        Mockito.verify(consumptionHistoryServiceApi).loadConsumptionHistory(
                consumptionHistoryServiceCallbackArgumentCaptor.capture());
        consumptionHistoryServiceCallbackArgumentCaptor.getValue().onLoaded(CONSUMPTION_HISTORY);
        Mockito.verify(consumptionHistoryView).setProgressIndicator(false);
        Mockito.verify(consumptionHistoryView).showHistory(CONSUMPTION_HISTORY);
    }
}
