package com.lataeviaberry.mybeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private String[] beautys = new String[] {"Nail Shop", "Hair Salon", "Organic Spa", "Massage Therapist", "Waxing Salon", "Nail Shop", "Hair Salon", "Organic Spa", "Massage Therapist", "Waxing Salon", "Nail Shop", "Hair Salon", "Organic Spa", "Massage Therapist", "Waxing Salon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautys);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, beautys);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String beauty = ((TextView)view).getText().toString();
                Toast.makeText(BeautysActivity.this, beauty, Toast.LENGTH_LONG).show();
            }
        });

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
                       
                   }

                });
            }
        });
    }
}
