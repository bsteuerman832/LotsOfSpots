package com.example.assignment3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.*;

// main activity
public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    Scanner scan = new Scanner(System.in);
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button manageCarsBtn = findViewById(R.id.manageCarsBtn);
        Button checkBtn = findViewById(R.id.checkBtn);
        Button aboutBtn = findViewById(R.id.aboutBtn);
        Button contactBtn = findViewById(R.id.contactBtn);
        TextView lotCreator = findViewById(R.id.Copyright);


        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:7326373621"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Access denied");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                }
                else
                     startActivity(callIntent);
            }
        });

    }


    public void onManageBtn(View v) {
        startActivity(new Intent(MainActivity.this, ManageCars.class));
    }

    public void onCheckBtn(View v) {
        startActivity(new Intent(MainActivity.this, CheckIn.class));
    }

    public void onCheckOutBtn(View v)
    {
        startActivity(new Intent(MainActivity.this, CheckOut.class));
    }

    public void onAboutBtn(View v)
    {
        startActivity(new Intent(MainActivity.this, About.class));
    }

    public void lotCreator(View v)
    {
        db = new DB(this);
        ArrayList<Lot> lots = new ArrayList<Lot>();
        lots.add(new Lot("Lot 1", "123 Main Street", 100, 1));
        lots.add(new Lot("Lot 2", "17 Wood Street", 50, 4));
        lots.add(new Lot("Lot 3", "172 Main Street", 75, 0.5));
        lots.add(new Lot("Lot 4", "84 Maple Street", 75, 0.75));
        System.out.println("Lots created");
        db.putListLot("lots", lots);

        // Because the lots are reset, all cars are checked out of all spots in all lots
        ArrayList<Car> cars = db.getListCar("cars");
        for (int i = 0; i < cars.size(); i++)
            cars.get(i).checkOutOfSpot();

        db.putListCar("cars", cars);
    }
}
