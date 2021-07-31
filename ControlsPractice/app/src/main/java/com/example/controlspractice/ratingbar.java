package com.example.controlspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class ratingbar extends AppCompatActivity {

    RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbar);
        ControlInitialization();
        events();
    }
    private void ControlInitialization(){
        rb = findViewById(R.id.rb);
    }
    private void events(){
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(ratingbar.this, "Rating is: " + rating, Toast.LENGTH_SHORT).show();
            }
        });
    }
}