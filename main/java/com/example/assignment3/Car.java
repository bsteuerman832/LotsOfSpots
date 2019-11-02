package com.example.assignment3;

public class Car
{
    private String licensePlate;
    private String regNumber;
    private String make;
    private String model;
    private boolean checkedInToSpot;


    public Car(String license, String reg, String theMake, String theModel)
    {
        licensePlate = license;
        regNumber = reg;
        make = theMake;
        model = theModel;
        checkedInToSpot = false;
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
    }

    public boolean getCheckInStatus()
    {
        return checkedInToSpot;
    }

}