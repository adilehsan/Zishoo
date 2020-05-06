package com.ideologer.zamishoapp.network;

import com.ideologer.zamishoapp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRestClient {
    private static OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(chain -> {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json; charset=UTF-8")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }).build();

    private static synchronized Retrofit getRetrofitClient() {

        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static ApiService getApiService() {
        return getRetrofitClient().create(ApiService.class);
    }
}
