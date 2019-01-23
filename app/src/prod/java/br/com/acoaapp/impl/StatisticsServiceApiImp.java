package br.com.acoaapp.impl;

import android.util.Log;

import br.com.acoaapp.data.HttpEndpointGenerator;
import br.com.acoaapp.data.endpoint.GeneralStatisticsEndpoint;
import br.com.acoaapp.data.entity.GeneralStatisticsEntity;
import br.com.acoaapp.data.service.GeneralStatisticsServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsServiceApiImp implements GeneralStatisticsServiceApi {

    @Override
    public void loadStatsConsumption(final GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback) {
        Call<GeneralStatisticsEntity> call = new HttpEndpointGenerator<GeneralStatisticsEndpoint>()
                .gen(GeneralStatisticsEndpoint.class).loadStatsConsumption();

        //TODO fazer tratamento de erros do servidor - analisar statusCode, body e errorBody
        call.enqueue(new Callback<GeneralStatisticsEntity>() {
            @Override
            public void onResponse(Call<GeneralStatisticsEntity> call, Response<GeneralStatisticsEntity> response) {
                GeneralStatisticsEntity body = response.body();
                if (null != body) {
                    generalStatisticsCallback.onLoaded(body);
                } else {
                    generalStatisticsCallback.onLoaded(new GeneralStatisticsEntity());
                }
            }

            @Override
            public void onFailure(Call<GeneralStatisticsEntity> call, Throwable t) {
                Log.e("Errro", "Erro");
                generalStatisticsCallback.onLoaded(new GeneralStatisticsEntity());
            }
        });
    }

    @Override
    public void loadConsumptionPerDay(String date, GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback) {

    }

    @Override
    public void loadConsumptionPerMonth(String date, GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback) {

    }

    @Override
    public void loadConsumptionPerMonthInYear(Integer year, GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback) {

    }

    @Override
    public void loadConsumptionPerYear(Integer year, GeneralStatisticsCallback<GeneralStatisticsEntity> generalStatisticsCallback) {

    }
}
