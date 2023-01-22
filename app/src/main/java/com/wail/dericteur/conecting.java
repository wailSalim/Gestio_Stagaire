package com.wail.dericteur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class conecting extends AppCompatActivity {
    ListView listView;
    public  String tmp;
    public void getinfoUtilisateur(String r){
        if(arrayList.size()==0){arrayList=db.getUtilisateurParRole(r);
            adapter a=new adapter(conecting.this,arrayList);
            listView.setAdapter(a);
        }else{
            arrayList.clear();
            arrayList=db.getUtilisateurParRole(r);
            adapter a=new adapter(conecting.this,arrayList);
            listView.setAdapter(a);

        }}
    BottomNavigationView bottomNavigationView;
    String[] users = { "Dericteur", "Formateur", "Stagiaire" };
    public ArrayList<utilisateur> arrayList=new ArrayList<utilisateur>();
    public basedonne db=new basedonne(conecting.this,"Gestion_auteur",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conecting);
        TextView N,R;
        R=findViewById(com.wail.dericteur.R.id.R_D);
        N=findViewById(com.wail.dericteur.R.id.n_dericteur);
        Bundle extras = getIntent().getExtras();
        tmp= extras.getString("log");
        N.setText(tmp);
        R.setText("Dericteur");
        bottomNavigationView=findViewById(com.wail.dericteur.R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                listView=findViewById(com.wail.dericteur.R.id.ListAffichParDericteur);
                switch (item.getItemId()) {
                    case com.wail.dericteur.R.id.iconhome:
                        arrayList.clear();
                        arrayList=db.getUtilisateurParRole("tous");
                        adapter a=new adapter(conecting.this,arrayList);
                        listView.setAdapter(a);
                        return true;
                    case com.wail.dericteur.R.id.iconplus:
                        Intent intent=new Intent(conecting.this,inscription.class);
                        startActivity(intent);
                        Toast.makeText(conecting.this, "plus", Toast.LENGTH_SHORT).show();
                        return true;
                    case com.wail.dericteur.R.id.iconproph:
                        Intent intent1=new Intent(conecting.this,prophil.class);
                        intent1.putExtra("log",tmp);
                        intent1.putExtra("role","Dericteur");
                        startActivity(intent1);
                        return true;
                    case com.wail.dericteur.R.id.iconset:
                        Toast.makeText(conecting.this, "setting", Toast.LENGTH_SHORT).show();
                        return true;
                    case com.wail.dericteur.R.id.holder:
                        Intent i=new Intent(conecting.this,inscription.class);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_test,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        listView=findViewById(R.id.ListAffichParDericteur);
        switch (item.getItemId()){
            case R.id.stagiare:
                getinfoUtilisateur("s");
                return true;
            case R.id.formateur:
                getinfoUtilisateur("f");
                return true;
            case R.id.sea:
                Toast.makeText(this, "refresh  succeed ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.set:
                Toast.makeText(this, "setting ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.tous:
                getinfoUtilisateur("tous");
                return true;
            case R.id.ajout_utilisateur:
                Intent intent=new Intent(conecting.this,inscription.class);
                startActivity(intent);
                return true;
                default:
                return super.onOptionsItemSelected(item);
        }
    }



}
