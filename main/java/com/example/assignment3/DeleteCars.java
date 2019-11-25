package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// delete cars
public class DeleteCars extends AppCompatActivity {

    Spinner spinner;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_cars);
        db = new DB(this);
        Button subBtn = findViewById(R.id.subButton2);
        spinner = (Spinner) findViewById(R.id.deleteSpinner);
        ArrayList<Car> cars = db.getListCar("cars");
        System.out.println("Cars: " + cars);
        String[] items = new String[cars.size()];
        int i = 0;
        for(i = 0; i < cars.size(); i++)
            items[i] = cars.get(i).getMake() + " " + cars.get(i).getModel() + ", Plate # " + cars.get(i).getLicensePlate();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    public void onSubBtn2 (View v)
    {
        String selected = spinner.getSelectedItem().toString();
        selected = selected.substring(selected.indexOf('#') + 1).trim();
        ArrayList<Car> cars = db.getListCar("cars");
        System.out.println("Cars: " + cars);
        int i = 0;
        while (!cars.get(i).getLicensePlate().toString().equals(selected))
            i++;

        if (cars.get(i).getCheckInStatus()) // car is checked into a spot already
            Toast.makeText(DeleteCars.this, "Car is checked in to spot. Cannot delete", Toast.LENGTH_LONG).show();
        else {
            cars.remove(i);
            db.putListCar("cars", cars);
            Toast.makeText(DeleteCars.this, "Car With plate number " + selected + " deleted", Toast.LENGTH_LONG).show();
            startActivity(new Intent(DeleteCars.this, ManageCars.class));
          }
    }

    public void onBack (View v)
    {
        startActivity(new Intent(DeleteCars.this, ManageCars.class));
    }
}
