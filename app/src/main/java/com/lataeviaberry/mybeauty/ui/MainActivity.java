package com.lataeviaberry.mybeauty.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lataeviaberry.mybeauty.Constants;
import com.lataeviaberry.mybeauty.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

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

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mFindBeautyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindBeautyButton) {
            String zipCode = mZipCodeEditText.getText().toString();
            if(!(zipCode).equals("")) {
                addToSharedPreferences(zipCode);
            }
            Intent intent = new Intent(MainActivity.this, BeautyListActivity.class);
            intent.putExtra("zipCode", zipCode);
            startActivity(intent);
        }
    }
    private void addToSharedPreferences(String zipCode) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, zipCode).apply();
    }
}