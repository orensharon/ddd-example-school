package com.sharono.example.ddd.school.util.mock;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static Retrofit getAdapter() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl("http://www.mock.com/")
                .client(createOkHttpMockClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient createOkHttpMockClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new MockInterceptor())
                .build();
    }

    private static OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            final Request original = chain.request();
            final HttpUrl originalHttpUrl = original.url();
            final HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("username", "demo")
                    .build();
            // Request customization: add request headers
            final Request.Builder requestBuilder = original.newBuilder()
                    .url(url);
            final Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        return httpClient.build();
    }
}