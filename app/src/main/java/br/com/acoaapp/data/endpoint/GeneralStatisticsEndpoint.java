package br.com.acoaapp.data.endpoint;

import br.com.acoaapp.data.entity.GeneralStatisticsEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GeneralStatisticsEndpoint {

    @GET("statistics")
    Call<GeneralStatisticsEntity> loadStatsConsumption();

    @GET("statistics/day/{date}")
    Call<GeneralStatisticsEntity> loadConsumptionPerDay(@Path("date") String date);

    @GET("statistics/month/{date}")
    Call<GeneralStatisticsEntity> loadConsumptionPerMonth(@Path("date") String date);

    @GET("statistics/month/year/{year}")
    Call<GeneralStatisticsEntity> loadConsumptionPerMonthInYear(@Path("year") Integer year);

    @GET("statistics/year/{year}")
    Call<GeneralStatisticsEntity> loadConsumptionPerYear(@Path("year") Integer year);
}
