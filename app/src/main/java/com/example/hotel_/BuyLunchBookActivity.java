package com.example.hotel_;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BuyLunchBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edpincode, edcontact;
    Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_lunch_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edpincode = findViewById(R.id.editTextBMBPinCode);
        edcontact = findViewById(R.id.editTextBMBPhoneNumber);
        btnOrder = findViewById(R.id.buttonBMBOrder);

        Intent intent=getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date  = intent.getStringExtra("date");
        //String time  = intent.getStringExtra("time");

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"hotel_",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(), "You have successfully ordered", Toast.LENGTH_SHORT).show();
                startActivity( new Intent(BuyLunchBookActivity.this, MainDashboardActivity.class));
            }
        });
    }
}