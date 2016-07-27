package com.lataeviaberry.mybeauty.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lataeviaberry.mybeauty.R;
import com.lataeviaberry.mybeauty.adapter.BeautyListAdapter;
import com.lataeviaberry.mybeauty.models.Beauty;
import com.lataeviaberry.mybeauty.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BeautyListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentAddress;
    public static final String TAG = BeautyListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private BeautyListAdapter mAdapter;

    public ArrayList<Beauty> mBeautys =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautys);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
        getBeautys(zipCode);

    }

    private void getBeautys(String zipCode) {
        final YelpService yelpService = new YelpService();
        yelpService.findBeautys(zipCode, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mBeautys = yelpService.processResults(response);

                BeautyListActivity.this.runOnUiThread(new Runnable() {

                   @Override
                   public void run() {
                       mAdapter = new BeautyListAdapter(getApplicationContext(), mBeautys);
                       mRecyclerView.setAdapter(mAdapter);
                       RecyclerView.LayoutManager layoutManager =
                               new LinearLayoutManager(BeautyListActivity.this);
                       mRecyclerView.setLayoutManager(layoutManager);
                       mRecyclerView.setHasFixedSize(true);
                   }
                });
            }
        });
    }
}