package com.example.databaseio_php;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewData extends AppCompatActivity {
    EditText txtenro, txtname, txtsem;
    Button btnupdate;
    ListView lvstudents;
    ArrayAdapter<String> adapter;
    String ViewURL = "http://192.168.56.1/PHPCrudApi1/viewData.php";
    String UpdateURL = "http://192.168.56.1/PHPCrudApi1/updateData.php";
    String DeleteURL = "http://192.168.56.1/PHPCrudApi1/deleteData.php";
    int ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        ControlInitialization();
        UpdateList();
        events();
        registerForContextMenu(lvstudents);
    }

    private void UpdateList() {
        StringRequest request = new StringRequest(Request.Method.GET, ViewURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            ArrayList<String> arrayList = new ArrayList<String>();
                            JSONArray dataArray = object.getJSONArray("students");
                            for(int i=0; i<dataArray.length(); i++){
                                JSONObject dataObject = dataArray.getJSONObject(i);
                                arrayList.add(dataObject.get("id") + "," + dataObject.get("enro") + "," + dataObject.get("name") + "," + dataObject.get("sem") );
                            }
                            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                            lvstudents.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void events() {
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtenro.equals("") || txtname.equals("") || txtsem.equals("")){
                    Toast.makeText(getApplicationContext(), "please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    UpdateRequest();
                }
            }
        });
    }
    public void UpdateRequest(){
        String enro = txtenro.getText().toString();
        String name = txtname.getText().toString();
        String sem = txtsem.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, UpdateURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        UpdateList();
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String,String>();
                params.put("id",String.valueOf(ID));
                params.put("enro", enro);
                params.put("name", name);
                params.put("sem", sem);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void DeleteRequest(){
        StringRequest request = new StringRequest(Request.Method.POST, DeleteURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        UpdateList();
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String,String>();
                params.put("id",String.valueOf(ID));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void ControlInitialization() {
        txtenro = findViewById(R.id.txtenro);
        txtname = findViewById(R.id.txtname);
        txtsem = findViewById(R.id.txtsem);
        btnupdate = findViewById(R.id.btnupdate);
        lvstudents = findViewById(R.id.lvstudents);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Option");
        menu.add("Edit");
        menu.add("Delete");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String data = adapter.getItem(info.position);
        String dataarr[] = data.split(",");
        ID = Integer.parseInt(dataarr[0]);
        if(item.toString().equals("Edit")){
//            Toast.makeText(getApplicationContext(), adapter.getItem(info.position), Toast.LENGTH_SHORT).show();
            txtenro.setText(dataarr[1]);
            txtname.setText(dataarr[2]);
            txtsem.setText(dataarr[3]);
        }
        else if(item.toString().equals("Delete")){
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure want to delete this record?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DeleteRequest();
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();
        }
        return super.onContextItemSelected(item);
    }
}