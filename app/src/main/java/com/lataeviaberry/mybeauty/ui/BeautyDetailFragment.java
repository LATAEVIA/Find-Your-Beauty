package com.lataeviaberry.mybeauty.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lataeviaberry.mybeauty.R;
import com.lataeviaberry.mybeauty.models.Beauty;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeautyDetailFragment extends Fragment {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.beautyImageView) ImageView mImageLabel;
    @Bind(R.id.beautyNameTextView) TextView mNameLabel;
    @Bind(R.id.serviceTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveBeautyButton) TextView mSaveBeautyButton;

    private Beauty mBeauty;

    public static BeautyDetailFragment newInstance(Beauty beauty) {
        BeautyDetailFragment beautyDetailFragment = new BeautyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("beauty", Parcels.wrap(beauty));
        beautyDetailFragment.setArguments(args);
        return beautyDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeauty = Parcels.unwrap(getArguments().getParcelable("beauty"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mBeauty.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mBeauty.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mBeauty.getCategories()));
        mRatingLabel.setText(Double.toString(mBeauty.getRating()) + "/5");
        mPhoneLabel.setText(mBeauty.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mBeauty.getAddress()));

        return view;
    }
}