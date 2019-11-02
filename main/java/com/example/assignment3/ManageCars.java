package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageCars extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_cars);
        Button regBtn = findViewById(R.id.regCarsBtn);
        Button deleteCarBtn = findViewById(R.id.deleteCarBtn);
    }

    public void onRegBtn (View v)
    {
        startActivity(new Intent(ManageCars.this, RegisterCars.class));
    }

    public void onDeleteBtn (View v)
    {
        startActivity(new Intent(ManageCars.this, DeleteCars.class));
    }

    public void onBack (View v)
    {
        startActivity(new Intent(ManageCars.this, MainActivity.class));
    }
}
