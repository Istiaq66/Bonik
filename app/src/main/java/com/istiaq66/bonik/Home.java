package com.istiaq66.bonik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {

    EditText productname,description,quantity,per_unit_price;
    String[] units ={"unit","Kg","Litre","gauze","Ton","inch","meter"};
    ArrayList<String> arrayList;
    String spin;
    Spinner spinner;
    ImageView insertData;




 //   private String insertDataURL = "http://10.0.2.2/Bonik_API/Inventory.php";
 final private String insertDataURL = "https://istiaq67.000webhostapp.com/Bonik/Inventory.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinner = findViewById(R.id.unitspinner);
        productname = findViewById(R.id.productname);
        description = findViewById(R.id.description);
        quantity = findViewById(R.id.quantity);
        per_unit_price = findViewById(R.id.per_unit_price);
        insertData =findViewById(R.id.inventor_btn);


        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //confirmadddata();
                if(productname.getText().toString().isEmpty()){

                    productname.setError("Enter a product Name");
                    return;
                }

                if(description.getText().toString().isEmpty()){

                    description.setError("Enter description");
                    return;
                }

                if(quantity.getText().toString().isEmpty()){

                    quantity.setError("Enter quantity");
                    return;
                }

                if(per_unit_price.getText().toString().isEmpty()){

                    per_unit_price.setError("Enter per unit price");
                    return;
                }
                else{
                insertdatanewway2();
                
            }
            }
        });


        arrayList = new ArrayList<>();
        ArrayList<String> a10 = new ArrayList<String>();
        for (String i:units)
        {

            a10.add(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,a10);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spin = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void insertdatanewway2() {



        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressBar.setVisibility(View.GONE);


              productname.setText("");
              description.setText("");
              quantity.setText("");
              per_unit_price.setText("");


                Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parameters = new HashMap<String, String>();


                parameters.put("productname", productname.getText().toString());
                parameters.put("description", description.getText().toString());
                parameters.put("quantity", quantity.getText().toString());
                parameters.put("unit", spin);
                parameters.put("per_unit_price", per_unit_price.getText().toString());

                return parameters;
            }
        };

       // AppSingleton1.getInstance(this).addToRequestQueue(stringRequest,TAG);
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);


    }

}