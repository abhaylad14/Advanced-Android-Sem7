package com.example.databaseio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btninsert, btnupdate;
    ListView lv;
    EditText txtname,txtcontact;
    SQLiteDatabase db;
    DatabaseHelper dh;
    ArrayAdapter<String> adapter;
    int UpdateId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlInitialization();
        events();
        displaydata();
        registerForContextMenu(lv);
    }

    private void ControlInitialization(){
        txtname = findViewById(R.id.txtname);
        txtcontact = findViewById(R.id.txtcontact);
        btninsert = findViewById(R.id.btninsert);
        btnupdate = findViewById(R.id.btnupdate);
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
                    txtname.setText("");
                    txtcontact.setText("");
                    displaydata();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedata();
            }
        });
    }
    private void displaydata(){
        ArrayList<String> contactlist = dh.getdata();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactlist);
        lv.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.showmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.optupdate:
                callupdate(adapter.getItem(info.position));
                return true;
            case R.id.optdelete:
                delete(adapter.getItem(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    private void callupdate(String data){
        String arr[] = data.split(",");
        txtname.setText(arr[1]);
        txtcontact.setText(arr[2]);
        UpdateId = Integer.parseInt(arr[0]);
    }
    private void updatedata(){
        String name = txtname.getText().toString();
        String contactno = txtcontact.getText().toString();

        int status = dh.updatedata(new Contacts(UpdateId,name,contactno));
        if(status >= 1){
            Toast.makeText(this, "Record updated successfully", Toast.LENGTH_SHORT).show();
            displaydata();
            txtname.setText("");
            txtcontact.setText("");
        }
        else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
    private void delete(String data){
        String id[] = data.split("");
        int status = dh.deleteDataById(Integer.parseInt(id[0]));
        if(status >= 1){
            Toast.makeText(this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
            displaydata();
        }
        else{
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }
}