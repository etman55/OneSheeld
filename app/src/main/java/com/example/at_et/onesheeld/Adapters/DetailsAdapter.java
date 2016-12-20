package com.example.at_et.onesheeld.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.at_et.onesheeld.R;
import com.example.at_et.onesheeld.Utility.DetailsCallBack;
import com.example.at_et.onesheeld.Utility.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by at-et on 10/27/16.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    private String[] selectedSensors = new String[9];
    private static DetailsCallBack listener;

    public DetailsAdapter(String[] selectedSensors) {
        this.selectedSensors = selectedSensors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    public void setOnDetailsClickListener(DetailsCallBack listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView sensors = (TextView) holder.itemView.findViewById(R.id.selected_sensor);
        sensors.setText(selectedSensors[position]);

    }

    @Override
    public int getItemCount() {
        return selectedSensors.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.details_item, parent, false));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, getLayoutPosition());
                }
            });
        }
    }
}