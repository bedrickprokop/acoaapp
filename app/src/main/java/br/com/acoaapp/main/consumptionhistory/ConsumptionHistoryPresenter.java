package br.com.acoaapp.main.consumptionhistory;

import android.support.annotation.NonNull;

import java.util.Calendar;

import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;
import br.com.acoaapp.data.entity.GeneralStatisticsEntity;
import br.com.acoaapp.data.service.ConsumptionHistoryServiceApi;
import br.com.acoaapp.data.service.GeneralStatisticsServiceApi;

public class ConsumptionHistoryPresenter implements ConsumptionHistoryContract.UserActionListener {

    private ConsumptionHistoryServiceApi mServiceApi;
    private ConsumptionHistoryContract.View mView;

    ConsumptionHistoryPresenter(@NonNull ConsumptionHistoryServiceApi serviceApi,
                                @NonNull ConsumptionHistoryContract.View view) {
        this.mServiceApi = serviceApi;
        this.mView = view;
    }

    @Override
    public void loadHistory(boolean doRefresh) {
        mView.setProgressIndicator(true);
        mServiceApi.loadConsumptionHistory(new ConsumptionHistoryServiceApi.Callback<ConsumptionHistoryEntity>() {
            @Override
            public void onLoaded(ConsumptionHistoryEntity data) {
                mView.setProgressIndicator(false);
                mView.showHistory(data);
            }
        });
    }
}
