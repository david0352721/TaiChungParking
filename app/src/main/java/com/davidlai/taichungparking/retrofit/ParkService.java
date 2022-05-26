package com.davidlai.taichungparking.retrofit;

import com.davidlai.taichungparking.model.Parks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ParkService {

    @GET("/swagger/OpenData/56a846ca-bc23-4754-b14a-0170f0541e09")
    Call<List<Parks>> searchParks();

}
