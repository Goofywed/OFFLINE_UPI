package com.aine.offline_upi_finalproject; // Replace with your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.aine.offline_upi_finalproject.Set_PinActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSetPin = findViewById(R.id.btnSetPin);
        Button btnEnterPin = findViewById(R.id.btnEnterPin);
        Button btnSecurity = findViewById(R.id.btnSecurity);
        Button btnSecurityforget = findViewById(R.id.btnSecurityforget);



        btnSetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Set Pin Activity or perform desired action
                startActivity(new Intent(MainActivity.this, Set_PinActivity.class));
            }
        });

        btnEnterPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Enter Pin Activity or perform desired action
                startActivity(new Intent(MainActivity.this, EnterPinActivity.class));
            }
        });

        btnSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Enter Pin Activity or perform desired action
                startActivity(new Intent(MainActivity.this, SecurityQuestionsActivity.class));
            }
        });

        btnSecurityforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Enter Pin Activity or perform desired action
                startActivity(new Intent(MainActivity.this, useofsecurityquestion.class));
            }
        });
    }
}
