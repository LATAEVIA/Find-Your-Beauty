package com.lataeviaberry.mybeauty.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lataeviaberry.mybeauty.R;
import com.lataeviaberry.mybeauty.models.Beauty;
import com.lataeviaberry.mybeauty.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BeautysActivity extends AppCompatActivity {
    public static final String TAG = BeautysActivity.class.getSimpleName();
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.zipCodeTextView) TextView mZipCodeTextView;
    public ArrayList<Beauty> mBeautys =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautys);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
        mZipCodeTextView.setText("Beauty near: " + zipCode);
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

                BeautysActivity.this.runOnUiThread(new Runnable() {

                   @Override
                   public void run() {
                       String[] beautyNames = new String[mBeautys.size()];
                       for (int i = 0; i < beautyNames.length; i++) {
                           beautyNames[i] = mBeautys.get(i).getName();
                       }
                       ArrayAdapter adapter = new ArrayAdapter(BeautysActivity.this,
                               android.R.layout.simple_list_item_1, beautyNames);
                       mListView.setAdapter(adapter);

                       for (Beauty restaurant : mBeautys) {
                           Log.d(TAG, "Name: " + restaurant.getName());
                           Log.d(TAG, "Phone: " + restaurant.getPhone());
                           Log.d(TAG, "Website: " + restaurant.getWebsite());
                           Log.d(TAG, "Image url: " + restaurant.getImageUrl());
                           Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
                           Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getAddress()));
                           Log.d(TAG, "Categories: " + restaurant.getCategories().toString());

                       }
                   }
                });
            }
        });
    }
}
