package com.istiaq66.bonik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth auth;
    private  long backPressedTime;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating Firebase Instance//
        auth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);
        floatingActionButton = findViewById(R.id.floatingAB);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent = new Intent(MainActivity.this,Home.class);
                 startActivity(intent);
            }
        });


        //-- middle home icon clicked after opening app---//
        View home = bottomNavigationView.findViewById(R.id.home);
         home.performClick();


        //----we can also use this code but the icon will not be clicked only the fragment will show here---- //
       // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_frag()).commit();


    }


    private  BottomNavigationView.OnNavigationItemSelectedListener navListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selected_fragment = null;

            switch (item.getItemId()){

                case R.id.home:
                    selected_fragment = new Home_frag();
                    break;

                case R.id.inventory:
                    selected_fragment = new Inventory_frag();
                    break;

                case R.id.deliveryhub:
                    selected_fragment = new Devivaryhub_frag();
                    break;

                case R.id.vendorHub:
                    selected_fragment = new VendorHub_frag();
                    break;

                case R.id.profile:
                    selected_fragment = new Profile_frag();
                    break;




            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected_fragment).commit();
            return true;
        }
    };











    //------------Creating menu-----------------//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return  super.onCreateOptionsMenu(menu);

    }


    //-------------Switching between Menu Items-----------------------//
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, Login_Signup.class).



                        /*If the Activity being started is already running in the current task
                         then instead of launching the new instance of that Activity,
                         all the other activities on top of it is destroyed (with call to onDestroy method)
                         and this intent is delivered to the resumed instance of the Activity*/
                        setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                startActivity(intent);
                break;
        }

        return true;
    }



    //----Exit app----//
    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000>System.currentTimeMillis())
        {
            super.onBackPressed();
            this.finishAffinity();
            return;
        }
        else
        {
            Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }
}