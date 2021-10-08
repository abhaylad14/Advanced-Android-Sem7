package com.example.databaseio_php;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity {

    EditText txtenro, txtname, txtsem;
    Button btninsert;
    String AddURL = "http://192.168.56.1/PHPCrudApi1/addData.php";
    String enro,name,sem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        ControlInitialization();
        events();
    }

    private void events() {
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enro = txtenro.getText().toString();
                name = txtname.getText().toString();
                sem = txtsem.getText().toString();
                AddRequest();
            }
        });
    }

    private void ControlInitialization() {
        txtenro = findViewById(R.id.txtenro);
        txtname = findViewById(R.id.txtname);
        txtsem = findViewById(R.id.txtsem);
        btninsert = findViewById(R.id.btninsert);
    }
    private void  AddRequest()
    {
        txtenro.setText("");
        txtname.setText("");
        txtsem.setText("");
        StringRequest addRequest = new StringRequest(Request.Method.POST, AddURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                Log.d("warning",error.toString());
                error.printStackTrace();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("enro",enro);
                params.put("name",name);
                params.put("sem",sem);
                return params;
            }
        };

        RequestQueue addqueue = Volley.newRequestQueue(getApplicationContext());
        addqueue.add(addRequest);
    }
}