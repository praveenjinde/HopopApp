package com.hopop.hopopv10.communicators;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hopop.hopopv10.communicators.services.RegisterClass;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunicatorClass {

    private static RegisterClass registerClass;

    public static RegisterClass getRegisterClass() {
        if (registerClass == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor()).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.31.4.252:80/hari/hopop/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            registerClass = retrofit.create(RegisterClass.class);
        }
        return registerClass;

    }
}