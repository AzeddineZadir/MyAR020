package com.example.myar02.vue;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.myar02.AR.MainActivity;
import com.example.myar02.R;

public class CommodeActivity extends AppCompatActivity {
    GridLayout liste_comodes ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commode);
        BottomNavigationView navigationView =findViewById(R.id.bottom_nav);
        liste_comodes=(GridLayout)findViewById(R.id.liste_commodes);
        //set event
        setSingleEvent(liste_comodes);
        // navigation bottom view
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

    private void setSingleEvent(GridLayout liste_comodes) {
        for (int i = 0 ;i< liste_comodes.getChildCount();i++){
            CardView cardView =(CardView)liste_comodes.getChildAt(i);
            final int index =i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"item +"+ index,Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(getApplicationContext(),Commode1Activity.class);
                    intent.putExtra("index",index);
                    startActivity(intent);
                    finish();


                }
            });
        }

    }


//    public void onClickCommode1(View view) {
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+view.getId());
//        view.getId();
//        Intent intent =new Intent(getApplicationContext(),Commode1Activity.class);
//        startActivity(intent);
//    }
}
