package com.davidlai.taichungparking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.davidlai.taichungparking.adapter.ParkAdapter;
import com.davidlai.taichungparking.viewmodel.ParkViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;


public class MainActivity extends AppCompatActivity {

    MaterialButton main_search;
    RecyclerView main_recyclerView;
    ParkAdapter parkAdapter;
    ParkViewModel viewModel;
    CircularProgressIndicator main_progress;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActivityCompat.requestPermissions(this,
//                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                100);

        main_search = findViewById(R.id.main_searchBT);
        main_recyclerView = findViewById(R.id.main_recyclerView);
        main_progress = findViewById(R.id.main_progress);
        main_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        parkAdapter = new ParkAdapter();
        main_recyclerView.setAdapter(parkAdapter);

        viewModel = new ViewModelProvider(this).get(ParkViewModel.class);
        viewModel.init();
        viewModel.getParksLiveData().observe(this, parks -> {
            if (parks != null) {
                main_progress.setVisibility(View.GONE);
                parkAdapter.setResult(parks, (parkingLot, park) -> {
                    Uri gmmIntentUri = Uri.parse("geo:" + parkingLot.getY() + "," + parkingLot.getX() + "?q=" + parkingLot.getPosition());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

//                    double distance;
//                    Location locationA = new Location("");
//                    locationA.setLatitude(parkingLot.getX());
//                    locationA.setLongitude(parkingLot.getY());
//                    Location locationB = new Location("");
//                    locationB.setLatitude(latitude);
//                    locationB.setLongitude(longitude);
//                    distance = locationA.distanceTo(locationB)/1000;
//                    Toast.makeText(getApplicationContext(), ""+distance, Toast.LENGTH_LONG).show();

                });
            }
        });
        viewModel.searchParks();

        main_search.setOnClickListener(view -> {
            main_progress.setVisibility(View.VISIBLE);
            viewModel.searchParks();
        });

    }

//    public void getLocal() {
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        String localProvider = "";
//        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Location location = manager.getLastKnownLocation(localProvider);
//        if (location != null) {
//            showLocation(location);
//        } else {
//            Log.d("Main", "getLocation: ");
//            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, mListener);
//            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mListener);
//        }
//
//    }
//
//    private void showLocation(@NonNull Location location){
//        String address = "緯度："+location.getLatitude()+"經度："+location.getLongitude();
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//        Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show();
////        btShow.setOnClickListener(view -> {
////            String url = "https://www.google.com/maps/@"+location.getLatitude()+","+location.getLongitude()+",15z";
////            Intent i = new Intent(Intent.ACTION_VIEW);
////            i.setData(Uri.parse(url));
////            startActivity(i);
////        });
//
//    }
//
//    LocationListener mListener = this::showLocation;
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        getLocal();
//    }
}