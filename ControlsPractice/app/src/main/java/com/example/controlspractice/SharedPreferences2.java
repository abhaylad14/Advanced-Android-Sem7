package com.example.controlspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferences2 extends AppCompatActivity {

    TextView tv;
    Button btnlogout;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences2);
        ControlInitialization();
        events();
    }
    private void ControlInitialization(){
        tv = findViewById(R.id.tvemail);
        btnlogout = findViewById(R.id.btnlogout);
        sp = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        if(sp.contains("email")){
            String email = sp.getString("email","");
            tv.setText(email);
        }
        else{
            Toast.makeText(getApplicationContext(),"no value",Toast.LENGTH_SHORT);
        }

    }
    private void events(){
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email","");
                editor.commit();
                finish();
            }
        });
    }
}