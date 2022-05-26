package com.davidlai.taichungparking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parks {
    @SerializedName("ID")
    private int iD;
    @SerializedName("Name")
    private String name;
    @SerializedName("X")
    private double x;
    @SerializedName("Y")
    private double y;
    @SerializedName("ParkingLots")
    private List<ParkingLots> parkingLots;

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public List<ParkingLots> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLots> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public static class ParkingLots {
        @SerializedName("ID")
        private String iD;
        @SerializedName("Position")
        private String position;
        @SerializedName("X")
        private double x;
        @SerializedName("Y")
        private double y;
        @SerializedName("TotalCar")
        private int totalCar;
        @SerializedName("AvailableCar")
        private int availableCar;
        @SerializedName("AvailableCarRGB")
        private String availableCarRGB;
        @SerializedName("Notes")
        private String notes;
        @SerializedName("Type")
        private String type;
        @SerializedName("Updatetime")
        private String updatetime;

        public String getID() {
            return iD;
        }

        public void setID(String iD) {
            this.iD = iD;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public int getTotalCar() {
            return totalCar;
        }

        public void setTotalCar(int totalCar) {
            this.totalCar = totalCar;
        }

        public int getAvailableCar() {
            return availableCar;
        }

        public void setAvailableCar(int availableCar) {
            this.availableCar = availableCar;
        }

        public String getAvailableCarRGB() {
            return availableCarRGB;
        }

        public void setAvailableCarRGB(String availableCarRGB) {
            this.availableCarRGB = availableCarRGB;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
