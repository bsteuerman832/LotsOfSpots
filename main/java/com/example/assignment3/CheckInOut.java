package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CheckInOut extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_out);
        Button subBtn = findViewById(R.id.subButton3);
        Spinner spinner = (Spinner) findViewById(R.id.cars_spinner);
        ArrayList<Car> cars = db.getListCar("cars");
        System.out.println("Cars: " + cars);
        String[] items = new String[cars.size()];
        int i = 0;
        for(i = 0; i < cars.size(); i++)
            items[i] = cars.get(i).getMake() + " " + cars.get(i).getModel() + ", Plate # " + cars.get(i).getLicensePlate();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    public void onSubBtn3 (View v)
    {

    }

}
