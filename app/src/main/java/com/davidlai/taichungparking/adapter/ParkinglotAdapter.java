package com.davidlai.taichungparking.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davidlai.taichungparking.R;
import com.davidlai.taichungparking.model.Parks;

import java.util.List;

public class ParkinglotAdapter extends RecyclerView.Adapter<ParkinglotAdapter.ViewHolder> {

    private List<Parks.ParkingLots> parkingLots;
    private OnChildClick childClick;
    private int parentPosition;

    public ParkinglotAdapter(List<Parks.ParkingLots> parkingLots, int parentPosition, OnChildClick childClick) {
        this.parkingLots = parkingLots;
        this.parentPosition = parentPosition;
        this.childClick = childClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parkinglot_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Parks.ParkingLots parkingLot = parkingLots.get(position);

        holder.parkinglot_position.setText(parkingLot.getPosition());
        holder.parkinglot_totalCar.setText(String.valueOf(parkingLot.getTotalCar()));
        holder.parkinglot_availableCar.setText(String.valueOf(parkingLot.getAvailableCar()));
        holder.parkinglot_Notes.setText(parkingLot.getNotes());
        holder.parkinglot_Updatetime.setText(parkingLot.getUpdatetime());
        holder.parkinglot_XY.setOnClickListener(view ->
                childClick.onChildClick(parkingLot, parentPosition));
    }

    @Override
    public int getItemCount() {
        return parkingLots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView parkinglot_position;
        TextView parkinglot_totalCar;
        TextView parkinglot_availableCar;
        TextView parkinglot_Notes;
        TextView parkinglot_Updatetime;
        Button parkinglot_XY;

        public ViewHolder(View itemView) {
            super(itemView);

            parkinglot_position = itemView.findViewById(R.id.parkinglot_position);
            parkinglot_totalCar = itemView.findViewById(R.id.parkinglot_totalCar);
            parkinglot_availableCar = itemView.findViewById(R.id.parkinglot_availableCar);
            parkinglot_Notes = itemView.findViewById(R.id.parkinglot_Notes);
            parkinglot_Updatetime = itemView.findViewById(R.id.parkinglot_Updatetime);
            parkinglot_XY = itemView.findViewById(R.id.parkinglot_XY);
        }
    }

    interface OnChildClick {
        void onChildClick(Parks.ParkingLots parkingLot, int parentPosition);
    }

}
