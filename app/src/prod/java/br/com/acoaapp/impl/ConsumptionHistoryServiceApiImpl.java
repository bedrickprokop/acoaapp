package br.com.acoaapp.impl;

import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;
import br.com.acoaapp.data.service.ConsumptionHistoryServiceApi;

public class ConsumptionHistoryServiceApiImpl implements ConsumptionHistoryServiceApi {

    @Override
    public void loadConsumptionHistory(ConsumptionHistoryCallback<ConsumptionHistoryEntity> consumptionHistoryCallback) {
        consumptionHistoryCallback.onLoaded(new ConsumptionHistoryEntity());
    }
}
