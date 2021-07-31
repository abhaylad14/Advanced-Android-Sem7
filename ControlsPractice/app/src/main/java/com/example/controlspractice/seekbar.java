package com.example.controlspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class seekbar extends AppCompatActivity {

    SeekBar slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        ControlInitialization();
        events();
    }
    private void ControlInitialization(){
        slider = findViewById(R.id.slider);
    }
    private void events(){
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int status = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                status = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(seekbar.this, "Slider at: " + status, Toast.LENGTH_SHORT).show();
            }
        });
    }
}