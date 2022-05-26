package com.davidlai.taichungparking.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.davidlai.taichungparking.model.Parks;
import com.davidlai.taichungparking.retrofit.ParkRepository;

import java.util.List;

public class ParkViewModel extends AndroidViewModel {

    private ParkRepository parkRepository;
    private LiveData<List<Parks>> parksLiveData;

    public ParkViewModel(Application application) {
        super(application);
    }

    public void init() {
        parkRepository = new ParkRepository();
        parksLiveData = parkRepository.getParksLiveData();
    }

    public void searchParks() {
        parkRepository.searchParks();
    }

    public LiveData<List<Parks>> getParksLiveData() {
        return parksLiveData;
    }
}
