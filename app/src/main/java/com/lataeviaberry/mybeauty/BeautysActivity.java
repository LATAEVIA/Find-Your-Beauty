package com.lataeviaberry.mybeauty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BeautysActivity extends AppCompatActivity {
    private TextView mZipCodeTextView;
    private ListView mListView;
    private String[] beautys = new String[] {"Nail Shop", "Hair Salon", "Organic Spa", "Massage Therapist", "Waxing Salon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautys);

        mListView = (ListView) findViewById(R.id.listView);
        mZipCodeTextView = (TextView) findViewById(R.id.zipCodeTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, beautys);
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
        mZipCodeTextView.setText("Beauty near: " + zipCode);
    }
}
