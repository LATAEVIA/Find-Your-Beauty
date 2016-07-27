package com.lataeviaberry.mybeauty.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lataeviaberry.mybeauty.Constants;
import com.lataeviaberry.mybeauty.R;
import com.lataeviaberry.mybeauty.models.Beauty;
import com.lataeviaberry.mybeauty.ui.BeautyDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseBeautyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseBeautyViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindBeauty(Beauty beauty) {
        ImageView beautyImageView = (ImageView) mView.findViewById(R.id.beautyImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.beautyNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.with(mContext)
                .load(beauty.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(beautyImageView);

        nameTextView.setText(beauty.getName());
        categoryTextView.setText(beauty.getCategories().get(0));
        ratingTextView.setText("Rating: " + beauty.getRating() + "/5");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Beauty> beautys = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESTAURANTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    beautys.add(snapshot.getValue(Beauty.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, BeautyDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("beautys", Parcels.wrap(beautys));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}