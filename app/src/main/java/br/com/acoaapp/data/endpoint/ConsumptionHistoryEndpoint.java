package br.com.acoaapp.data.endpoint;

import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ConsumptionHistoryEndpoint {

    @GET("history")
    Call<ConsumptionHistoryEntity> loadConsumptionHistory();
}
