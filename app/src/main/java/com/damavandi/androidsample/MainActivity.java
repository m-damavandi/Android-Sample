package com.damavandi.androidsample;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.damavandi.androidsample.adapter.MyAdapter;
import com.damavandi.androidsample.interfaces.OnItemClickListener;
import com.damavandi.androidsample.network.ServiceGenerator;
import com.damavandi.androidsample.network.modeles.ShowModel;
import com.damavandi.androidsample.network.services.ClientService;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
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

    public static String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private CircularProgressView progress;
    private Button retryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadMovieData();
    }

    private void initView() {
        // init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Android Sample");

        // init drawer
        final IProfile profile = new ProfileDrawerItem()
                .withName("user")
                .withEmail("user_email@email.com");

        AccountHeader headerDrawer = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .addProfiles(
                        profile
                )
                .build();

        List<IDrawerItem> drawerItems = new ArrayList<>();

        drawerItems.add(new PrimaryDrawerItem().withName("drawer item 1"));
        drawerItems.add(new PrimaryDrawerItem().withName("drawer item 2"));
        drawerItems.add(new PrimaryDrawerItem().withName("drawer item 3"));

        new DrawerBuilder(this)
                .withActivity(this)
                .withAccountHeader(headerDrawer)
                .withToolbar(toolbar)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(drawerItems.toArray(new IDrawerItem[drawerItems.size()]))
                .build();

        // init recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        recyclerView, new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //recycler item clicked
                        Intent intent = new Intent(getApplicationContext(),ShowDetailActivity.class);
                        ShowModel showModel = mAdapter.getModel(position);
                        intent.putExtra("show_object",showModel);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        //recycler item long clicked
                        Toast.makeText(getApplicationContext(), "long clicked", Toast.LENGTH_SHORT).show();
                    }
                }));

        // init progress
        progress = (CircularProgressView) findViewById(R.id.progress_view);

        // init retry button
        retryBtn = (Button) findViewById(R.id.retry_button);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryBtn.setVisibility(View.GONE);
                loadMovieData();
            }
        });
    }

    // get movie data from baseApiUrl/show,
    // on success response adapt recyclerView
    // TODO : on error and on failure show (error/retry) to client
    private void loadMovieData() {
        progress.setVisibility(View.VISIBLE);
        ClientService client = ServiceGenerator.createService(ClientService.class);
        Call<List<ShowModel>> call = client.getShows();

        call.enqueue(new Callback<List<ShowModel>>() {
            @Override
            public void onResponse(Call<List<ShowModel>> call, Response<List<ShowModel>> response) {
                progress.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: success");
                    List<ShowModel> showModels = response.body();
                    mAdapter = new MyAdapter(showModels, getApplicationContext());
                    recyclerView.setAdapter(mAdapter);
                } else {
                    retryBtn.setVisibility(View.VISIBLE);
                    Log.e(TAG, "onResponse: " + response.message());
                    Toast.makeText(getApplicationContext(), R.string.error_occurred, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ShowModel>> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.e(TAG, "onFailure:" + t.getMessage());
                Toast.makeText(getApplicationContext(), R.string.connection_problem, Toast.LENGTH_SHORT).show();
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
