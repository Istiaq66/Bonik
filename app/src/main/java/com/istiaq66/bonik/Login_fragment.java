package com.istiaq66.bonik;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;


public class Login_fragment extends Fragment {

    EditText ed1,ed2;
    Button lgbt;
    float i=0;

    FirebaseAuth auth;
    ProgressDialog progressDialog;


    String URL_LOGIN = "http://138.197.129.176/auth/login";

    private  static  String TAG = Login_fragment.class.getSimpleName();

    String username ;
    String password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View root = (ViewGroup) inflater.inflate(R.layout.fragment_login_fragment,container,false);

        ed1 = root.findViewById(R.id.username);
        ed2 = root.findViewById(R.id.pass);
        lgbt = root.findViewById(R.id.login_bt);

        ed1.setTranslationX(800);
        ed2.setTranslationX(800);
        lgbt.setTranslationX(800);

        ed1.setAlpha(i);
        ed2.setAlpha(i);
        lgbt.setAlpha(i);

        ed1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        ed2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        lgbt.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Login");
        progressDialog.setTitle("Login to your account");



        auth = FirebaseAuth.getInstance();


        lgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login();

                //--email login using firebase--//

               /* auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        progressDialog.dismiss();



                        if(task.isSuccessful()){


                            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                        }
                        else
                        {

                            Toast.makeText(getActivity().getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });*/
            }
        });

       /* if(auth.getCurrentUser()!=null){
            Intent intent = new Intent(getActivity().getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }*/



        return root;
    }

    public void Login() {


        if (ed1.getText().toString().isEmpty()) {

            ed1.setError("Enter a username");
            return;
        }

        if (ed2.getText().toString().isEmpty()) {

            ed2.setError("Enter a password");
            return;
        }
        else {


            String username = ed1.getText().toString().trim();
            String password = ed2.getText().toString().trim();

            progressDialog.show();


            StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    progressDialog.dismiss();
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();


                }

            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", username);
                    params.put("password", password);

                    return params;

                }
            };

            AppSingleton1.getInstance(getContext()).addToRequestQueue(request, TAG);

        }
    }


}