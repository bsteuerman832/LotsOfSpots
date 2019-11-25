package com.example.assignment3;

// car class
public class Car
{
    private String licensePlate;
    private String regNumber;
    private String make;
    private String model;
    private boolean checkedInToSpot;
    private String lotParkedIn;
    private int spotNumber;


    public Car(String license, String reg, String theMake, String theModel)
    {
        licensePlate = license;
        regNumber = reg;
        make = theMake;
        model = theModel;
        checkedInToSpot = false;
        spotNumber = -1;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public String getRegNumber()
    {
        return regNumber;
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public void checkInToSpot()
    {
        checkedInToSpot = true;
    }
    public void checkOutOfSpot()
    {
        checkedInToSpot = false;
        lotParkedIn = null;
        spotNumber = -1;
    }

    public boolean getCheckInStatus()
    {
        return checkedInToSpot;
    }

    public void setLotParkedIn(String lotName)
    {
        lotParkedIn = lotName;
    }

    public String getLotParkedIn() {
        return lotParkedIn;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int num) {
        spotNumber = num;
    }
}
