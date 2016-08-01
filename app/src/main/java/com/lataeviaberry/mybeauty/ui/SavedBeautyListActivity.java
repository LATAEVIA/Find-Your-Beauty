package com.lataeviaberry.mybeauty.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lataeviaberry.mybeauty.Constants;
import com.lataeviaberry.mybeauty.R;
import com.lataeviaberry.mybeauty.adapter.FirebaseBeautyViewHolder;
import com.lataeviaberry.mybeauty.models.Beauty;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedBeautyListActivity extends AppCompatActivity {
    private DatabaseReference mBeautyReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_beautys);
        ButterKnife.bind(this);

        mBeautyReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BEAUTYS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Beauty, FirebaseBeautyViewHolder>
                (Beauty.class, R.layout.beauty_list_item, FirebaseBeautyViewHolder.class,
                        mBeautyReference) {

            @Override
            protected void populateViewHolder(FirebaseBeautyViewHolder viewHolder,
                                              Beauty model, int position) {
                viewHolder.bindBeauty(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}