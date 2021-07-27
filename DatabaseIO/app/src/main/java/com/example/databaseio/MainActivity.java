package com.example.databaseio;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btninsert;
    ListView lv;
    EditText txtname,txtcontact;
    SQLiteDatabase db;
    DatabaseHelper dh;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlInitialization();
        events();
        displaydata();
    }

    private void ControlInitialization(){
        txtname = findViewById(R.id.txtname);
        txtcontact = findViewById(R.id.txtcontact);
        btninsert = findViewById(R.id.btninsert);
        lv = findViewById(R.id.lv);
        dh = new DatabaseHelper(this);
    }
    private void events(){
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtname.getText().toString();
                String contactno = txtcontact.getText().toString();

                int status = dh.adddata(new Contacts(name,contactno));
                if(status >= 1){
                    Toast.makeText(MainActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    displaydata();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void displaydata(){
        ArrayList<String> contactlist = dh.getdata();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactlist);
        lv.setAdapter(adapter);
    }

}