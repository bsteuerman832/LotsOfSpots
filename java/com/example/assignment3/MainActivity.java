package com.example.assignment3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button regCarsBtn = findViewById(R.id.regCarsBtn);
        Button checkBtn = findViewById(R.id.checkBtn);
        Button aboutBtn = findViewById(R.id.aboutBtn);
        Button contactBtn = findViewById(R.id.contactBtn);


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


    public void onRegisterBtn(View v) {
        startActivity(new Intent(MainActivity.this, RegisterCars.class));
    }

    public void onCheckBtn(View v) {
        startActivity(new Intent(MainActivity.this, CheckInOut.class));
    }

    public void onAboutBtn(View v)
    {
        startActivity(new Intent(MainActivity.this, About.class));
    }

}