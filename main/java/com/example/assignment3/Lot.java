package com.example.assignment3;

public class Lot
{
    private String name;
    private String address;
    private Car[] spots;
    private int size;
    private double dist;
    private int carsParked;

    public Lot(String theName, String theAddress, int lotSize, double dist_from_user)
    {
        name = theName;
        address = theAddress;
        size = lotSize;
        dist = dist_from_user;
        spots = new Car[size];
        carsParked = 0;
    }

    public int getSize() {
        return size;
    }

    public Car getCarAtSpot(int spot)
    {
        if (spot < 0 || spot >= size)
            return null;
        else
            return spots[spot];
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public double getDist() {
        return dist;
    }

    public Car[] getSpots() {
        return spots;
    }

    public boolean parkCar(int spotNum, Car car)
    {
        if (spotNum >= 0) {
            spots[spotNum] = car;
            carsParked = carsParked + 1;
            return true;
        }
        return false;
    }

    public boolean checkOutCar(int spotNum)
    {
        spots[spotNum] = null;
        carsParked = carsParked - 1;
        return true;
    }

    public boolean isFull()
    {
        if (size - carsParked == 0)
            return true;
        else
            return false;
    }
}
