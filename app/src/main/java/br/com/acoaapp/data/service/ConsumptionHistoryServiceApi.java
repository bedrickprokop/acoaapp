package br.com.acoaapp.data.service;

import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;

public interface ConsumptionHistoryServiceApi {

    interface Callback<T> {
        void onLoaded(T data);
    }

    void loadConsumptionHistory(ConsumptionHistoryServiceApi.Callback<ConsumptionHistoryEntity> callback);
}
