package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CheckInOut extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    DB db;
    EditText  spotNumText;
    Spinner spinner1, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_out);

        // sets up variables
        Button subBtn = findViewById(R.id.subButton3);
        spotNumText = (EditText)findViewById(R.id.spotNumText);
        spinner1 = (Spinner) findViewById(R.id.cars_spinner);
        spinner2 = (Spinner) findViewById(R.id.lotSpinner);

        // retrieves list of user cars to display
        ArrayList<Car> cars = db.getListCar("cars");
        System.out.println("Cars: " + cars);
        String[] items = new String[cars.size()];
        // displays in spinner object
        int i = 0;
        for(i = 0; i < cars.size(); i++)
            items[i] = cars.get(i).getMake() + " " + cars.get(i).getModel() + ", Plate # " + cars.get(i).getLicensePlate();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner1.setAdapter(adapter1);

        ArrayList<Lot> lots = db.getListLot("lots");
        System.out.println("Lots: " + lots);
        String[] items2 = new String[lots.size()];
        // displays in spinner object
        i = 0;
        for(i = 0; i < lots.size(); i++)
            if (!lots.get(i).isFull())
                items2[i] = lots.get(i).getName() + ", " + lots.get(i).getAddress() + ", Distance: " + lots.get(i).getDist() + " miles";
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        spinner2.setAdapter(adapter2);
    }

    // User hits submit and atempts to park in a spot
    public void onSubBtn3 (View v)
    {
       int spotNum = Integer.parseInt(spotNumText.getText().toString());
        String selected = spinner1.getSelectedItem().toString();
        selected = selected.substring(selected.indexOf('#') + 1).trim();
        ArrayList<Car> cars = db.getListCar("cars");
        int i = 0;
        while (!cars.get(i).getLicensePlate().toString().equals(selected))
            i++;
        Car selectedCar = cars.get(i);

    }

    public void onBack5 (View v)
    {
        startActivity(new Intent(CheckInOut.this, MainActivity.class));
    }
}

