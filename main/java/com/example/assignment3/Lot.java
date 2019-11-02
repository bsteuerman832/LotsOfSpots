package com.example.assignment3;

public class Lot
{
    private String name;
    private String address;
    private Car[] spots;
    private int size;
    private double lat;
    private double lon;

    public Lot(String theName, String theAddress, int lotSize, double latitude, double longitude)
    {
        name = theName;
        address = theAddress;
        size = lotSize;
        lat = latitude;
        lon = longitude;
        spots = new Car[size];
    }

    public int getSize() {
        return size;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    public String getName() {
        return name;
    }

    public Car[] getSpots() {
        return spots;
    }

    public boolean parkCar(int spotNum, Car car)
    {
        if (spotNum >= 0) {
            spots[spotNum] = car;
            return true;
        }

        return false;
    }
}
