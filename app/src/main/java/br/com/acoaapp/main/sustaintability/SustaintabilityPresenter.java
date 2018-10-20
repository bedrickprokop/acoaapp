package br.com.acoaapp.main.sustaintability;

import android.support.annotation.NonNull;

import br.com.acoaapp.data.service.GeneralStatisticsServiceApi;

public class SustaintabilityPresenter implements SustaintabilityContract.UserActionListener {

    private GeneralStatisticsServiceApi mServiceApi;
    private SustaintabilityContract.View mView;

    SustaintabilityPresenter(@NonNull GeneralStatisticsServiceApi serviceApi,
                             @NonNull SustaintabilityContract.View view) {
        this.mServiceApi = serviceApi;
        this.mView = view;
    }

    @Override
    public void loadHistory(boolean doRefresh) {
        //TODO implement
        mView.showHistory(null);
    }
}
