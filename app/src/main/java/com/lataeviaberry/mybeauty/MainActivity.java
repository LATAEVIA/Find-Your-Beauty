package com.lataeviaberry.mybeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mFindBeautyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFindBeautyButton = (Button) findViewById(R.id.findBeautyButton);
        mFindBeautyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MainActivity.this, BeautysActivity.class);
                startActivity(intent);
            }
        });
    }
}
