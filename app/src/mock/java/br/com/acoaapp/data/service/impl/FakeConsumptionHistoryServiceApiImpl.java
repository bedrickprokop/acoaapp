package br.com.acoaapp.data.service.impl;

import android.os.Handler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;
import br.com.acoaapp.data.service.ConsumptionHistoryServiceApi;

public class FakeConsumptionHistoryServiceApiImpl implements ConsumptionHistoryServiceApi {

    @Override
    public void loadConsumptionHistory(final Callback<ConsumptionHistoryEntity> callback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Map<String, Double> totalMonthConsumption = new LinkedHashMap<>();
                totalMonthConsumption.put("Jan", 2000.0);
                totalMonthConsumption.put("Fev", 4945.5);
                totalMonthConsumption.put("Mar", 9023.4);
                totalMonthConsumption.put("Abr", 3434.5);
                totalMonthConsumption.put("Mai", 3330.1);
                totalMonthConsumption.put("Jun", 9000.9);
                totalMonthConsumption.put("Jul", 4573.7);
                totalMonthConsumption.put("Ago", 2344.4);
                totalMonthConsumption.put("Set", 7472.3);
                totalMonthConsumption.put("Out", 6000.1);
                totalMonthConsumption.put("Nov", 2009.8);
                totalMonthConsumption.put("Dez", 5000.2);

                Map<Integer, Double> totalYearsConsumption = new LinkedHashMap<>();
                totalYearsConsumption.put(2015, 400000.0);
                totalYearsConsumption.put(2016, 800000.0);
                totalYearsConsumption.put(2017, 759333.0);
                totalYearsConsumption.put(2018, 599999.9);

                ConsumptionHistoryEntity consumptionHistoryEntity = new ConsumptionHistoryEntity();
                consumptionHistoryEntity.setTotalMonthConsumption(totalMonthConsumption);
                consumptionHistoryEntity.setTotalYearsConsumption(totalYearsConsumption);
                callback.onLoaded(consumptionHistoryEntity);
            }
        }, 2000);
    }
}
