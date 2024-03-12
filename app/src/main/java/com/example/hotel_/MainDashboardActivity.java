package com.example.hotel_;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class MainDashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindashboard);

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(), "Welcome"+" "+username, Toast.LENGTH_SHORT).show();


        CardView buybreakfast = findViewById(R.id.cardBuyBreakfast);
        buybreakfast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboardActivity.this, BuyBreakfastActivity.class));
            }
        });

        CardView buylunch = findViewById(R.id.cardlunch);
        buylunch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboardActivity.this, BuyLunchActivity.class));
            }
        });

        CardView buysalads = findViewById(R.id.cardsalads);
        buysalads.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboardActivity.this, BuysaladsActivity.class));
            }
        });
        CardView buydrinks = findViewById(R.id.carddrinks);
        buydrinks.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboardActivity.this, BuydrinksActivity.class));
            }
        });

        CardView buyfruits = findViewById(R.id.cardFDFruits);
        buyfruits.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboardActivity.this, BuyLunchActivity.class));
            }
        });


        CardView orderDetails = findViewById(R.id.cardOrderDetails);
        orderDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboardActivity.this, OrderDetailsActivity.class));
            }
        });

        CardView viewLocations = findViewById(R.id.cardFDLocations);
        viewLocations.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboardActivity.this, GoogleMapsActivity.class));
            }
        });

        CardView customercare = findViewById(R.id.cardFDNotify);
        customercare.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainDashboardActivity.this, NotificationActivity.class));
            }
        });

    }
}