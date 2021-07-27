package com.example.controlspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class searchview extends AppCompatActivity {

    SearchView sv;
    ListView lv;
    String cities[] = {"surat","ahmedabad","bardoli","navsari","Valsad","Vapi"};
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);
        ControlInitialization();
        events();
    }
    private void ControlInitialization(){
        sv = findViewById(R.id.sv);
        lv = findViewById(R.id.lv);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, cities);
        lv.setAdapter(adapter);
    }
    private void events(){
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
