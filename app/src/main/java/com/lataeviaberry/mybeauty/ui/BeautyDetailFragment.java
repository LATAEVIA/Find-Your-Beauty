package com.lataeviaberry.mybeauty.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lataeviaberry.mybeauty.Constants;
import com.lataeviaberry.mybeauty.R;
import com.lataeviaberry.mybeauty.models.Beauty;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeautyDetailFragment extends Fragment implements View.OnClickListener{
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

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSaveBeautyButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mBeauty.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mBeauty.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mBeauty.getLatitude()
                            + "," + mBeauty.getLongitude()
                            + "?q=(" + mBeauty.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSaveBeautyButton) {
            DatabaseReference beautyRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_BEAUTYS);
            beautyRef.push().setValue(mBeauty);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
