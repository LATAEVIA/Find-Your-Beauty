package com.lataeviaberry.mybeauty.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.lataeviaberry.mybeauty.models.Beauty;

import java.util.ArrayList;

public class BeautyListAdapter extends RecyclerView.Adapter<BeautyListAdapter.BeautyViewHolder> {
    private ArrayList<Beauty> mBeautys = new ArrayList<>();
    private Context mContext;

    public BeautyListAdapter(Context context, ArrayList<Beauty> beautys) {
        mContext = context;
        mBeautys = beautys;
    }
}
