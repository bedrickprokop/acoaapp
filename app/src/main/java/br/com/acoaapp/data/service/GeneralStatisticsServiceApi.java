package br.com.acoaapp.data.service;

import br.com.acoaapp.data.entity.GeneralStatisticsEntity;

public interface GeneralStatisticsServiceApi {

    interface Callback<T> {
        void onLoaded(T data);
    }

    void loadStatsConsumption(Callback<GeneralStatisticsEntity> callback);

    void loadConsumptionPerDay(String date, Callback<GeneralStatisticsEntity> callback);

    void loadConsumptionPerMonth(String date, Callback<GeneralStatisticsEntity> callback);

    void loadConsumptionPerMonthInYear(Integer year, Callback<GeneralStatisticsEntity> callback);

    void loadConsumptionPerYear(Integer year, Callback<GeneralStatisticsEntity> callback);
}
