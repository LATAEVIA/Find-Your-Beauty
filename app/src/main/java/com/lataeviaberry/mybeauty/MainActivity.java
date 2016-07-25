package com.lataeviaberry.mybeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button mFindBeautyButton;
    private EditText mZipCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mZipCodeEditText = (EditText) findViewById(R.id.zipCodeEditText);
        mFindBeautyButton = (Button) findViewById(R.id.findBeautyButton);
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
