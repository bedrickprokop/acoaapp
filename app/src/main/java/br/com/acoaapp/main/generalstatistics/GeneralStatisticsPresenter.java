package br.com.acoaapp.main.generalstatistics;

import android.support.annotation.NonNull;

import br.com.acoaapp.data.entity.GeneralStatisticsEntity;
import br.com.acoaapp.data.service.GeneralStatisticsServiceApi;

public class GeneralStatisticsPresenter implements GeneralStatisticsContract.UserActionListener {

    private GeneralStatisticsServiceApi mServiceApi;
    private GeneralStatisticsContract.View mView;

    GeneralStatisticsPresenter(@NonNull GeneralStatisticsServiceApi mServiceApi,
                               @NonNull GeneralStatisticsContract.View mView) {
        this.mServiceApi = mServiceApi;
        this.mView = mView;
    }

    @Override
    public void loadStatistics(boolean doRefresh) {
        mView.setProgressIndicator(true);
        mServiceApi.loadStatsConsumption(new GeneralStatisticsServiceApi.GeneralStatisticsCallback<GeneralStatisticsEntity>() {
            @Override
            public void onLoaded(GeneralStatisticsEntity data) {
                mView.setProgressIndicator(false);
                mView.showStatistics(data);
            }
        });
    }
}
