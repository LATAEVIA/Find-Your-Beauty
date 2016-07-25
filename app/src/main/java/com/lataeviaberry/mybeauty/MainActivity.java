package com.lataeviaberry.mybeauty;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mFindBeautyButton;
    private EditText mZipCodeEditText;
    private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mZipCodeEditText = (EditText) findViewById(R.id.zipCodeEditText);
        mFindBeautyButton = (Button) findViewById(R.id.findBeautyButton);
        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface madisonSquare = Typeface.createFromAsset(getAssets(), "fonts/madison-square/madison-square.ttf");
        mAppNameTextView.setTypeface(madisonSquare);
        mFindBeautyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String zipCode = mZipCodeEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, BeautysActivity.class);
                intent.putExtra("zipCode", zipCode);
                startActivity(intent);
            }
        });
    }
}
