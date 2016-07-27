package com.lataeviaberry.mybeauty.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lataeviaberry.mybeauty.R;
import com.lataeviaberry.mybeauty.models.Beauty;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeautyListAdapter extends RecyclerView.Adapter<BeautyListAdapter.BeautyViewHolder> implements View.OnClickListener {
    private ArrayList<Beauty> mBeautys = new ArrayList<>();
    private Context mContext;

    public BeautyListAdapter(Context context, ArrayList<Beauty> beautys) {
        mContext = context;
        mBeautys = beautys;
    }
    @Override
    public BeautyListAdapter.BeautyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beauty_list_item, parent, false);
        BeautyViewHolder viewHolder = new BeautyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BeautyListAdapter.BeautyViewHolder holder, int position) {
        holder.bindBeauty(mBeautys.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeautys.size();
    }

    public class BeautyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.beautyImageView) ImageView mBeautyImageView;
        @Bind(R.id.beautyNameTextView) TextView mNameTextView;
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;

        public BeautyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BeautyDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("beautys", Parcels.wrap(mBeautys));
            mContext.startActivity(intent);
        }

        public void bindBeauty(Beauty beauty) {
            Picasso.with(mContext).load(beauty.getImageUrl()).into(mBeautyImageView);
            mNameTextView.setText(beauty.getName());
            mCategoryTextView.setText(beauty.getCategories().get(0));
            mRatingTextView.setText("Rating: " + beauty.getRating() + "/5");
        }
    }
}