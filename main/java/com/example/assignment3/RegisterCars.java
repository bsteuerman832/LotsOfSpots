package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
                if (registration.equals(null) || license.equals(null) || make.equals(null) || model.equals(null)) {
                    System.out.println("A field is blank");
                    Toast.makeText(RegisterCars.this, "One or more of your text fields is blank.", Toast.LENGTH_LONG).show();
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
