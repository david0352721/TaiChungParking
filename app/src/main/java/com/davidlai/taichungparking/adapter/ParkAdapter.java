package com.davidlai.taichungparking.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.davidlai.taichungparking.R;
import com.davidlai.taichungparking.model.Parks;

import java.util.ArrayList;
import java.util.List;

public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ViewHolder> implements ParkinglotAdapter.OnChildClick {

    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<Parks> parks = new ArrayList<>();
    private OnItemClick onItemClick;

    @SuppressLint("NotifyDataSetChanged")
    public void setResult(List<Parks> parks, OnItemClick onItemClick) {
        this.parks = parks;
        this.onItemClick = onItemClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.park_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.park_name.setText(parks.get(position).getName());
        holder.park_recycleView.setAdapter(new ParkinglotAdapter(parks.get(position).getParkingLots(), position, this));
        holder.park_recycleView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return parks.size();
    }

    @Override
    public void onChildClick(Parks.ParkingLots parkingLot, int parentPosition) {
        onItemClick.onItemClick(parkingLot, parks.get(parentPosition));
    }

    public interface OnItemClick {
        void onItemClick(Parks.ParkingLots parkingLot, Parks park);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView park_name;
        RecyclerView park_recycleView;

        public ViewHolder(View itemView) {
            super(itemView);

            park_name = itemView.findViewById(R.id.park_name);
            park_recycleView = itemView.findViewById(R.id.park_recyclerView);

            Drawable down = ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.ic_arrow_drop_down_24, null);
            Drawable right = ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.ic_arrow_right_24, null);
            park_name.setCompoundDrawablesWithIntrinsicBounds(null,null, right, null);
            park_name.setOnClickListener(view -> {
                if (park_recycleView.getVisibility() == View.VISIBLE) {
                    park_name.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
                    park_recycleView.setVisibility(View.GONE);
                } else {
                    park_name.setCompoundDrawablesWithIntrinsicBounds(null, null, down, null);
                    park_recycleView.setVisibility(View.VISIBLE);
                }
            });

        }

    }

}
