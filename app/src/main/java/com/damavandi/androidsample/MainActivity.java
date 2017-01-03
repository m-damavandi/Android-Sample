package com.damavandi.androidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.damavandi.androidsample.network.ServiceGenerator;
import com.damavandi.androidsample.network.modeles.ShowModel;
import com.damavandi.androidsample.network.services.ClientService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleRequest();

    }

    private void sampleRequest() {
        ClientService client = ServiceGenerator.createService(ClientService.class);
        Call<List<ShowModel>> call = client.getShows();

        call.enqueue(new Callback<List<ShowModel>>() {
            @Override
            public void onResponse(Call<List<ShowModel>> call, Response<List<ShowModel>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: success");
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ShowModel>> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }
}
