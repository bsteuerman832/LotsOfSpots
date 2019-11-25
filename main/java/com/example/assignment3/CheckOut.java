package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// Check Out class
public class CheckOut extends AppCompatActivity {
    Spinner spinner;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        spinner = (Spinner) findViewById(R.id.parkedCarSpinner);
        ArrayList<Car> cars = db.getListCar("cars");
        ArrayList<Car> parkedCars = new ArrayList<Car>();
        for (int i = 0; i < cars.size(); i++)
            if (cars.get(i).getCheckInStatus()) {  // creates a new ArrayList with only cars parked in a spot
                parkedCars.add(cars.get(i));
            }
        String[] items = new String[parkedCars.size()];
        for (int i = 0; i < parkedCars.size(); i++) {
            items[i] = parkedCars.get(i).getMake() + " " + parkedCars.get(i).getModel() + ", Plate # " +
                    parkedCars.get(i).getLicensePlate() + ", Parked in " + parkedCars.get(i).getLotParkedIn();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    public void onSubBtn4 (View v)
    {
        if (spinner.getSelectedItem() == null)
            Toast.makeText(CheckOut.this, "You have no cars parked in any parking lots", Toast.LENGTH_LONG).show();
        else {
            String selected = spinner.getSelectedItem().toString();
            selected = selected.substring(selected.indexOf('#')+1).trim();
            selected = selected.substring(0, selected.indexOf(','));
            System.out.println(selected);
            ArrayList<Car> cars = db.getListCar("cars");
            ArrayList<Lot> lots = db.getListLot("lots");
            int i = 0;
            while (!cars.get(i).getLicensePlate().toString().equals(selected))
                i++;

            String lotName = cars.get(i).getLotParkedIn();
            int spotNum = cars.get(i).getSpotNumber();
            cars.get(i).checkOutOfSpot();
            i = 0;
            while (!lots.get(i).getName().toString().equals(lotName))
                i++;
            lots.get(i).checkOutCar(spotNum);
            db.putListLot("lots", lots);
            db.putListCar("cars", cars);
            Toast.makeText(CheckOut.this, "Car with license plate " + selected + " has been checked out of " + lots.get(i).getName(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(CheckOut.this, MainActivity.class));
        }
    }
}
