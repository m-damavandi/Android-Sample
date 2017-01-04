package com.damavandi.androidsample;

import android.app.SearchManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.damavandi.androidsample.adapter.MyAdapter;
import com.damavandi.androidsample.network.ServiceGenerator;
import com.damavandi.androidsample.network.modeles.ShowModel;
import com.damavandi.androidsample.network.services.ClientService;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private AccountHeader headerResult = null;
    private Drawer drawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Android Sample");

        final IProfile profile = new ProfileDrawerItem()
                .withName("user")
                .withEmail("user_email@email.com");

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
//                .withHeaderBackground(R.drawable.header_background)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();

        List<IDrawerItem> drawerItems = new ArrayList<>();

        drawerItems.add(new PrimaryDrawerItem().withName("drawer item 1"));
        drawerItems.add(new PrimaryDrawerItem().withName("drawer item 2"));
        drawerItems.add(new PrimaryDrawerItem().withName("drawer item 3"));

        drawer = new DrawerBuilder(this)
                .withActivity(this)
                .withAccountHeader(headerResult)
//                .withRootView(R.id.recycler_view)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(drawerItems.toArray(new IDrawerItem[drawerItems.size()]))
                .withSavedInstance(savedInstanceState)
                .build();

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
                    MyAdapter mAdapter = new MyAdapter(showModels,getApplicationContext());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
}
