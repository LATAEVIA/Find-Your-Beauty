package com.lataeviaberry.mybeauty.ui;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lataeviaberry.mybeauty.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findBeautyButton) Button mFindBeautyButton;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface madisonSquare = Typeface.createFromAsset(getAssets(), "fonts/madison-square.ttf");
        mAppNameTextView.setTypeface(madisonSquare);
        mFindBeautyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindBeautyButton) {
            String zipCode = mZipCodeEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, BeautyListActivity.class);
            intent.putExtra("zipCode", zipCode);
            startActivity(intent);
        }
    }
}