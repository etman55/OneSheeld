package com.example.at_et.onesheeld.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.at_et.onesheeld.R;
import com.example.at_et.onesheeld.Utility.OnItemClickListener;
import com.example.at_et.onesheeld.Utility.SensorsModel;

import java.util.ArrayList;


/**
 * Created by at-et on 10/23/16.
 */

/**
 * Adapter to display recycler view.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private Context context;

    // Define listener member variable
    private static OnItemClickListener listener;
    private ArrayList<SensorsModel> modelArrayList = new ArrayList<SensorsModel>();

    public ContentAdapter(Context context, ArrayList<SensorsModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.sensorName.setText(modelArrayList.get(position).getSensorName());
        holder.chk.setOnCheckedChangeListener(null);
        holder.chk.setChecked(modelArrayList.get(position).isSelected());
        holder.chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                modelArrayList.get(holder.getLayoutPosition()).setSelected(isChecked);
            }
        });
    }


    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView sensorName = (TextView) itemView.findViewById(R.id.sensor_name);
        private CheckBox chk = (CheckBox) itemView.findViewById(R.id.chk_item);

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item, parent, false));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, getLayoutPosition());
                }
            });

        }
    }
}






