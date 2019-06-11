package com.example.myar02.vue;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myar02.AR.MainActivity;
import com.example.myar02.R;

public class HomeActivity extends AppCompatActivity {
    Button b_AR ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // b_AR =(Button)findViewById(R.id.b_ar);

        BottomNavigationView navigationView =findViewById(R.id.bottom_nav);
        navigationView.setSelectedItemId(R.id.bm_home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id= menuItem.getItemId();
                // map activity pour aller vers le module de geolocalisation
                if ((id==R.id.bm_map)){
                    Intent mapIntent = new Intent(getApplicationContext(),MapActivity.class);
                    startActivity(mapIntent);
                }
                //main activity pouyr aller vers le catalogue de produits
                if (id==R.id.bm_home){
                    Intent homeIntent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(homeIntent);
                }
                if (id==R.id.bm_ar){
                    Intent ARintent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(ARintent);
                }
                return false;
            }
        });


    }

    public void goToAr(View view) {
        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void onClickCommodes(View view) {
        Intent intent =new Intent(getApplicationContext(), CommodeActivity.class);
        startActivity(intent);
    }
    public void onClickLit(View view) {
        Intent intent =new Intent(getApplicationContext(),LitsActivity.class);
        startActivity(intent);
    }
    public void onClickTable(View view) {
        Intent intent =new Intent(getApplicationContext(),TablesActivity.class);
        startActivity(intent);
    }



}
