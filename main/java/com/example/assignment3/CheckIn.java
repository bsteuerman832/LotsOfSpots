package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// CheckIn
public class CheckIn extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;
    DB db;
    EditText  spotNumText;
    Spinner spinner1, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DB(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

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
        {
            items[i] = cars.get(i).getMake() + " " + cars.get(i).getModel() + ", Plate # " + cars.get(i).getLicensePlate();
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner1.setAdapter(adapter1);

        ArrayList<Lot> lots = db.getListLot("lots");
        System.out.println("Lots: " + lots);
        ArrayList<String> items2 = new ArrayList<String>();
        // displays in spinner object
        i = 0;
        for(i = 0; i < lots.size(); i++)
            if (!lots.get(i).isFull() && lots.get(i).getDist() < 3.0)
                items2.add(lots.get(i).getName() + ", " + lots.get(i).getAddress() + ", Distance: " + lots.get(i).getDist() + " miles");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        spinner2.setAdapter(adapter2);
    }

    // User hits submit and attempts to park in a spot
    public void onSubBtn3 (View v)
    {
        int spotNum = -1;
        int i, j;
        if (spotNumText.getText().length() < 1)
            Toast.makeText(CheckIn.this, "You have not entered a spot number", Toast.LENGTH_LONG).show();
        else
            spotNum = Integer.parseInt(spotNumText.getText().toString());
        if (spinner1.getSelectedItem() == null)
            Toast.makeText(CheckIn.this, "You have no Cars registered", Toast.LENGTH_LONG).show();
        else {
            String selected1 = spinner1.getSelectedItem().toString();
            selected1 = selected1.substring(selected1.indexOf('#') + 1).trim();
            ArrayList<Car> cars = db.getListCar("cars");
            i = 0;
            while (!cars.get(i).getLicensePlate().toString().equals(selected1))
                i++;

            String selected2 = spinner2.getSelectedItem().toString();
            j = 0;
            selected2 = selected2.substring(0, selected2.indexOf(',')).trim();
            ArrayList<Lot> lots = db.getListLot("lots");
            while (!lots.get(j).getName().toString().equals(selected2))
                j++;

            if (spotNum > lots.get(j).getSize() || spotNum < 0)
                Toast.makeText(CheckIn.this, "This spot number is out of range, try a different number", Toast.LENGTH_LONG).show();
            else
                if (lots.get(j).getCarAtSpot(spotNum) != null)
                    Toast.makeText(CheckIn.this, "There is a car already parked in this spot", Toast.LENGTH_LONG).show();
                else
                    if (cars.get(i).getCheckInStatus()) {
                        Toast.makeText(CheckIn.this, "This car is already parked in some parking spot", Toast.LENGTH_LONG).show();
                    }
                    else
                        {
                        System.out.println(cars.get(i).getCheckInStatus());
                        cars.get(i).checkInToSpot();
                        lots.get(j).parkCar(spotNum, cars.get(i));
                        cars.get(i).setSpotNumber(spotNum);
                        cars.get(i).setLotParkedIn(lots.get(j).getName());
                        System.out.println("Car parked");
                        db.putListLot("lots", lots);
                        db.putListCar("cars", cars);
                        Toast.makeText(CheckIn.this, "Car with license plate " + cars.get(i).getLicensePlate() + " is parked in " + lots.get(j).getName(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(CheckIn.this, MainActivity.class));
                        }

        }
    }

    public void onBack5 (View v)
    {
        startActivity(new Intent(CheckIn.this, MainActivity.class));
    }
}

