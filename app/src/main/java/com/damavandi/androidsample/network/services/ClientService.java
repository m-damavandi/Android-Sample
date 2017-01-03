package com.damavandi.androidsample.network.services;

import com.damavandi.androidsample.network.modeles.ShowModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by arch on 1/3/17.
 */

public interface ClientService {

    @GET("shows")
    Call<List<ShowModel>> getShows();
}
