package com.example.assignment3;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckInOut extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;

    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        double latitude, longitude;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_out);

        TextView latitudeTxt = findViewById(R.id.latitude);
        TextView longitudeTxt = findViewById(R.id.longitude);

        Spinner spinner = (Spinner) findViewById(R.id.cars_spinner);
        String[] items = new String[]{"Car 1", "Car 2", "Car 3", "Car 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

    }

}
