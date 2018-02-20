package com.example.stampcollectorapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by singhmanral on 12/27/17.
 */

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.StampHolder> {

    ArrayList<StampData> mStampDataAd;
    LayoutInflater mLayoutInflater;

    public StampAdapter(Context context,ArrayList<StampData> data){
        mLayoutInflater = LayoutInflater.from(context);
        mStampDataAd = data;
    }

    @Override
    public StampAdapter.StampHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.
                inflate(R.layout.recycle_row_layout,parent,false);
        return new StampHolder(view);
    }

    @Override
    public void onBindViewHolder(StampAdapter.StampHolder holder, int position) {
        StampData currentStampData = mStampDataAd.get(position);

        holder.mStampTitleHolder.setText(
                currentStampData.getmStampTitle());
        holder.mStampIconHolder.setImageResource(
                currentStampData.getmStampIcon());
        holder.mStampCounterHolder.setText(
                String.valueOf(currentStampData.getmStampCounter()));
    }

    @Override
    public int getItemCount() {
        return mStampDataAd.size();
    }

    public class StampHolder extends RecyclerView.ViewHolder {
        TextView mStampTitleHolder;
        TextView mStampCounterHolder;
        ImageView mStampIconHolder;

        Button mAddButton;
        Button mSubButton;

        private int mcounter;

        private StampData mEditStampData;

        public StampHolder(View itemView) {
            super(itemView);
            mStampTitleHolder = itemView.
                    findViewById(R.id.stamp_title);
            mStampIconHolder = itemView.
                    findViewById(R.id.stamp_pic);

            mStampCounterHolder = itemView.
                    findViewById(R.id.stamp_count);

            mAddButton = itemView.
                    findViewById(R.id.add_button);

            mSubButton = itemView.
                    findViewById(R.id.sub_button);

            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Get the current StampData object by using the
                    // getAdapterPosition() method
                    mEditStampData = mStampDataAd.
                            get(getAdapterPosition());

                    //Access the current counter value from StampData object
                    mcounter = mEditStampData.getmStampCounter();

                    //Increment the counter value by one every time
                    mcounter++;

                    //Update the new counter value in StampData object
                    mEditStampData.setmStampCounter(mcounter);

                    //Update the current object in the ArrayList object too
                    mStampDataAd.set(getAdapterPosition(),mEditStampData);

                    //Notify the adapter about the changes
                    // made at the current position
                    notifyItemChanged(getAdapterPosition());

                }
            });


            mSubButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Get the current StampData object by using the
                    // getAdapterPosition() method
                    mEditStampData = mStampDataAd.
                            get(getAdapterPosition());

                    //Access the current counter value from StampData object
                    mcounter = mEditStampData.getmStampCounter();

                    //Check if counter value is greater than 0,
                    // then only decrement
                    if (mcounter > 0){
                        //Decrement counter value by one each time
                        mcounter--;

                        //Update the new counter value in StampData object
                        mEditStampData.setmStampCounter(mcounter);

                        //Update the current object in the ArrayList object too
                        mStampDataAd.set(getAdapterPosition(),mEditStampData);

                        //Notify the adapter about the changes
                        // made at the current position
                        notifyItemChanged(getAdapterPosition());
                    }

                }
            });
        }
    }
}
