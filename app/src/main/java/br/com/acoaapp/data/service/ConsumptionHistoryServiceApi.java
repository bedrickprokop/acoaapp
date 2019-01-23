package br.com.acoaapp.data.service;

import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;

public interface ConsumptionHistoryServiceApi {

    interface ConsumptionHistoryCallback<T> {
        void onLoaded(T data);
    }

    void loadConsumptionHistory(ConsumptionHistoryCallback<ConsumptionHistoryEntity> consumptionHistoryCallback);
}
