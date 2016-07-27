package com.lataeviaberry.mybeauty.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lataeviaberry.mybeauty.R;
import com.lataeviaberry.mybeauty.adapter.BeautyPagerAdapter;
import com.lataeviaberry.mybeauty.models.Beauty;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeautyDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private BeautyPagerAdapter adapterViewPager;
    ArrayList<Beauty> mBeautys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_detail);
        ButterKnife.bind(this);

        mBeautys = Parcels.unwrap(getIntent().getParcelableExtra("beautys"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new BeautyPagerAdapter(getSupportFragmentManager(), mBeautys);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}