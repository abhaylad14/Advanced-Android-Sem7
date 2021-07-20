package com.example.controlspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferences1 extends AppCompatActivity {

    SharedPreferences sp;
    EditText txtemail,txtpass;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences1);
        ControlInitialization();
        events();
    }
    private void ControlInitialization(){
        txtemail = findViewById(R.id.txtemail);
        txtpass = findViewById(R.id.txtpass);
        btnlogin = findViewById(R.id.btnlogin);

        sp = getSharedPreferences("MyPreferences",MODE_PRIVATE);
    }
    private void events(){
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                if(txtemail.getText().toString().equals("admin@gmail.com") && txtpass.getText().toString().equals("1234")) {
                    editor.putString("email",txtemail.getText().toString());
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(),SharedPreferences2.class);
                    txtemail.setText("");
                    txtpass.setText("");
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}