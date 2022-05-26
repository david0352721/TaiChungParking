package com.davidlai.taichungparking.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.davidlai.taichungparking.model.Parks;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParkRepository {

    private static final String Park_Url = "https://datacenter.taichung.gov.tw/";

    private final ParkService parkService;
    private final MutableLiveData<List<Parks>> parksLiveData;

    public ParkRepository() {
        parksLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        parkService = new Retrofit.Builder()
                .baseUrl(Park_Url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ParkService.class);

    }

    public void searchParks() {
        parkService.searchParks().enqueue(new Callback<List<Parks>>() {
            @Override
            public void onResponse(@NonNull Call<List<Parks>> call, @NonNull Response<List<Parks>> response) {
                Log.d("Repository", response.message());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        parksLiveData.setValue(response.body());
                    }
                } else {
                    Log.d("Repository", response.message());
                }
             }

            @Override
            public void onFailure(@NonNull Call<List<Parks>> call, @NonNull Throwable t) {
                Log.d("Repository", t.getMessage());
            }
        });
    }

    public LiveData<List<Parks>> getParksLiveData() {
        return parksLiveData;
    }
}
