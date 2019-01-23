package br.com.acoaapp.data.service;

import br.com.acoaapp.data.entity.GeneralStatisticsEntity;

public interface GeneralStatisticsServiceApi {

    interface GeneralStatisticsCallback<T> {
        void onLoaded(T data);
    }

    void loadStatsConsumption(GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback);

    void loadConsumptionPerDay(String date, GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback);

    void loadConsumptionPerMonth(String date, GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback);

    void loadConsumptionPerMonthInYear(Integer year, GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback);

    void loadConsumptionPerYear(Integer year, GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback);
}
