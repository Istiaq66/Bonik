package com.istiaq66.bonik;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import java.util.List;


public class Inventory_frag extends Fragment {



   // final private String JSONURL2 = "http://10.0.2.2/Bonik_API/getinventory.php";
    final private String JSONURL2 = "https://istiaq67.000webhostapp.com/Bonik/getinventory.php";


    //getting the recyclerview from xml
    RecyclerView recycleView;


    //the Model list where we will store all the Model objects after parsing json
    List<inventModel> list;
    inventory_adapter adapter;

    private  static  String TAG = Home_frag.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root = inflater.inflate(R.layout.fragment_inventory, container, false);

       recycleView = root.findViewById(R.id.review);

        list = new ArrayList<>();
        LoadListData();

        return  root;
    }

    private void LoadListData(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSONURL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    //-----------way--2   //way 2 --array wise
                    //converting the string to json array object
                    JSONArray array  = new JSONArray(response);
                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject modelObject = array.getJSONObject(i);

                        //adding the product to Model list
                        inventModel proo = new inventModel(modelObject.getString("productname"), modelObject.getString("description"), modelObject.getInt("quantity"), modelObject.getString("unit"),modelObject.getInt("per_unit_price"));

                        //adding the proo2 to list
                         list.add(proo);
                    }

                    //creating custom adapter object
                    adapter = new inventory_adapter(list,getContext());



                    recycleView.setHasFixedSize(true);
                    recycleView.setLayoutManager(new LinearLayoutManager(getContext()));

                    //adding the adapter to RecycleView
                    recycleView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




       /* //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);*/

        AppSingleton1.getInstance(getContext()).addToRequestQueue(stringRequest,TAG);

    }

}