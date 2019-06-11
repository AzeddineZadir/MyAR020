package com.example.myar02.vue;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myar02.AR.MainActivity;
import com.example.myar02.R;

public class Commode1Activity extends AppCompatActivity {
    // les composant graphique
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commode1);
        ImageView iv_Produits = (ImageView)findViewById(R.id.iv_produis);
        TextView tv_nomProduits =(TextView)findViewById(R.id.tv_nomProduis);
        TextView tv_description =(TextView)findViewById(R.id.tv_description);
        TextView tv_meseurs =(TextView)findViewById(R.id.tv_mesures);
        TextView tv_prix =(TextView)findViewById(R.id.tv_prix);

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

        // le formatage de la vue
        // 1 recuperation de lindex du produits selectionner
        int index =getIntent().getExtras().getInt("index");
        Toast.makeText(getApplicationContext(),"item +................."+ index,Toast.LENGTH_LONG).show();
        if (index==0){
            iv_Produits.setImageResource(R.drawable.commode1);
            tv_nomProduits.setText("Commode Alpha");
            tv_description.setText("commmode Alpha en coeur de Hêtre facade orphée");
            tv_meseurs.setText("L:1300 x P:520 x H:870");
            tv_prix.setText("prix : 9000 DA");
            }else if (index==1){
            iv_Produits.setImageResource(R.drawable.commode2);
            tv_nomProduits.setText("Commode Davos");
            tv_description.setText("Commode Davos en orme ");
            tv_meseurs.setText("L:1100 x P:560 x H:770");
            tv_prix.setText("prix : 8000 DA");
            }else if (index==2){
            iv_Produits.setImageResource(R.drawable.commode3);
            tv_nomProduits.setText("Commode Epona");
            tv_description.setText("Commode Epona en orme");
            tv_meseurs.setText("L:1100 x P:560 x H:770");
            tv_prix.setText("prix : 5900 DA");
            }else if (index==3){
            iv_Produits.setImageResource(R.drawable.commode6);
            tv_nomProduits.setText("Commode Nova");
            tv_description.setText("Commode Nova en chêne");
            tv_meseurs.setText("L:1100 x P:560 x H:770");
            tv_prix.setText("prix : 10900 DA");
            }else if (index==4){
            iv_Produits.setImageResource(R.drawable.commode2);
            tv_nomProduits.setText("Commode Jupiter");
            tv_description.setText("Commode Jupiter en Noyer");
            tv_meseurs.setText("L:1300 x P:520 x H:850");
            tv_prix.setText("prix : 11000 DA");
           }
        // modification de la photo



    }
}
