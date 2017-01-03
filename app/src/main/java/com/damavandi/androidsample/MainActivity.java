package com.damavandi.androidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.damavandi.androidsample.adapter.MyAdapter;
import com.damavandi.androidsample.network.ServiceGenerator;
import com.damavandi.androidsample.network.modeles.ShowModel;
import com.damavandi.androidsample.network.services.ClientService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public String TAG = "MainActivity";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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
                    List<ShowModel> showModels = response.body();
                    MyAdapter mAdapter = new MyAdapter(showModels);
                    recyclerView.setAdapter(mAdapter);
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
