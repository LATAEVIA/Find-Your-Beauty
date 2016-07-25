package com.lataeviaberry.mybeauty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BeautysActivity extends AppCompatActivity {
    private TextView mZipCodeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautys);
        mZipCodeTextView = (TextView) findViewById(R.id.zipCodeTextView);
        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
        mZipCodeTextView.setText("Beauty near: " + zipCode);
    }
}
