package br.com.acoaapp.impl;

import android.util.Log;

import br.com.acoaapp.data.HttpEndpointGenerator;
import br.com.acoaapp.data.endpoint.StatisticsEndpoint;
import br.com.acoaapp.data.entity.StatisticsEntity;
import br.com.acoaapp.data.service.StatisticsServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsServiceApiImp implements StatisticsServiceApi {

    @Override
    public void loadStatsConsumption(final StatisticsCallback<StatisticsEntity> callback) {
        Call<StatisticsEntity> call = new HttpEndpointGenerator<StatisticsEndpoint>()
                .gen(StatisticsEndpoint.class).loadStatsConsumption();

        //TODO fazer tratamento de erros do servidor - analisar statusCode, body e errorBody
        call.enqueue(new Callback<StatisticsEntity>() {
            @Override
            public void onResponse(Call<StatisticsEntity> call, Response<StatisticsEntity> response) {
                StatisticsEntity body = response.body();
                if (null != body) {
                    callback.onLoaded(body);
                } else {
                    callback.onLoaded(new StatisticsEntity());
                }
            }

            @Override
            public void onFailure(Call<StatisticsEntity> call, Throwable t) {
                Log.e("Errro", "Erro");
                callback.onLoaded(new StatisticsEntity());
            }
        });
    }

    @Override
    public void loadConsumptionPerDay(String date, StatisticsCallback<StatisticsEntity> callback) {

    }

    @Override
    public void loadConsumptionPerMonth(String date, StatisticsCallback<StatisticsEntity> callback) {

    }

    @Override
    public void loadConsumptionPerMonthInYear(Integer year, StatisticsCallback<StatisticsEntity> callback) {

    }

    @Override
    public void loadConsumptionPerYear(Integer year, StatisticsCallback<StatisticsEntity> callback) {

    }
}
