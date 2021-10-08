package com.example.databaseio_php;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnadd,btnview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlInitialization();
        events();
    }

    private void events() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), InsertData.class);
                startActivity(i);
            }
        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewData.class);
                startActivity(i);
            }
        });

    }

    private void ControlInitialization() {
        btnadd = findViewById(R.id.btnadd);
        btnview = findViewById(R.id.btnview);
    }

}
