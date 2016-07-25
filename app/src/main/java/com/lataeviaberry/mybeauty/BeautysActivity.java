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

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeautysActivity extends AppCompatActivity {
    @Bind(R.id.listView)TextView mZipCodeTextView;
    @Bind(R.id.zipCodeTextView) ListView mListView;
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
    }
}
