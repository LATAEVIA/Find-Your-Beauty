package com.lataeviaberry.mybeauty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeautysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautys);
        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
    }
}
