package br.com.acoaapp.data;

import br.com.acoaapp.ApiConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpEndpointGenerator<T> {

    public T gen(Class<T> clazz) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(provideOkHttpClient())
                .build();

        return retrofit.create(clazz);
    }
}
