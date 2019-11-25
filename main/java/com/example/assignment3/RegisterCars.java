package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// reg cars
public class RegisterCars extends AppCompatActivity {

    EditText regNumText, licenseNumText, makeText, modelText;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cars);
        db = new DB(this);
        Button submitBtn = (Button) findViewById(R.id.subButton);
        regNumText = (EditText)findViewById(R.id.regNumber);
        licenseNumText = (EditText)findViewById(R.id.licenseNum);
        makeText = (EditText)findViewById(R.id.makeCar);
        modelText = (EditText)findViewById(R.id.modelCar);
    }

    public void onSubBtn(View v) {
        String registration = regNumText.getText().toString();
        String license = licenseNumText.getText().toString();
        String make = makeText.getText().toString();
        String model = modelText.getText().toString();
        ArrayList<Car> cars = db.getListCar("cars");
        System.out.println("Cars: " + cars);
        boolean repeatLicense = false;
        for (int i =0; i < cars.size(); i++)
            if (cars.get(i).getLicensePlate().toString().equals(license))
                repeatLicense = true;
        if (repeatLicense) {
            System.out.println("Car already added");
            Toast.makeText(RegisterCars.this, "This license plate number has already been used", Toast.LENGTH_LONG).show();
        } else {
            if (cars.size() >= 10) {
                System.out.println("Car limit reached");
                Toast.makeText(RegisterCars.this, "Car limit of 10 reached", Toast.LENGTH_LONG).show();
            } else
                if (registration.length() < 10 || license.length() < 4 || make.length() < 3 || model.length() < 2) {
                    System.out.println("One or more fields is blank or is insufficient");
                    Toast.makeText(RegisterCars.this, "One or more fields is blank or is insufficient", Toast.LENGTH_LONG).show();
                }
                else
                    if (!license.matches("[a-zA-Z0-9]*") || !registration.matches("[a-zA-Z0-9]*"))
                    {
                        Toast.makeText(RegisterCars.this, "License plate number or Registration number has unwanted characters", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                    Car car = new Car(license, registration, make, model);
                    cars.add(car);
                    db.putListCar("cars", cars);
                    System.out.println("Car added");
                    Toast.makeText(RegisterCars.this, "Car information added", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterCars.this, ManageCars.class));
                    }
        }

    }

    public void onBack (View v)
    {
        startActivity(new Intent(RegisterCars.this, ManageCars.class));
    }
}
