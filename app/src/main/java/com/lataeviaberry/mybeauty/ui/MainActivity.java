package com.lataeviaberry.mybeauty.ui;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lataeviaberry.mybeauty.Constants;
import com.lataeviaberry.mybeauty.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;
    @Bind(R.id.findBeautyButton) Button mFindBeautyButton;
    @Bind(R.id.zipCodeEditText) EditText mZipCodeEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedBeautysButton) Button mSavedBeautysButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);
        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface madisonSquare = Typeface.createFromAsset(getAssets(), "fonts/madison-square.ttf");
        mAppNameTextView.setTypeface(madisonSquare);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindBeautyButton.setOnClickListener(this);
        mSavedBeautysButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mFindBeautyButton) {
            String zipCode = mZipCodeEditText.getText().toString();

            saveLocationToFirebase(zipCode);

//            if(!(zipCode).equals("")) {
//                addToSharedPreferences(zipCode);
//            }
            Intent intent = new Intent(MainActivity.this, BeautyListActivity.class);
            intent.putExtra("zipCode", zipCode);
            startActivity(intent);
        }

        if (v == mSavedBeautysButton) {
            Intent intent = new Intent(MainActivity.this, SavedBeautyListActivity.class);
            startActivity(intent);
        }
    }
    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }
//    private void addToSharedPreferences(String zipCode) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, zipCode).apply();
//    }
}