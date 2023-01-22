package com.wail.dericteur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class conect_Formateur extends AppCompatActivity {

    ListView listView;
    LinearLayout linearLayout;
    BottomNavigationView bottomNavigationView;
    public ArrayList<utilisateur> arrayList=new ArrayList<utilisateur>();
    public basedonne db=new basedonne(conect_Formateur.this,"Gestion_auteur",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        arrayList=db.getPersonne();
//        EditText Role,name,pre,login,pass,montion,sexe,filier;
//        //        edit de Stagaire
//        Role=findViewById(R.id.roleStagaire);
//        name=findViewById(R.id.nameStagFormateur);
//        pre=findViewById(R.id.prenomStagFormateur);
//        login=findViewById(R.id.loginStagFormateur);
//        pass=findViewById(R.id.pasStagFormateur);
//        montion=findViewById(R.id.editTextTextPersonName3);
//        sexe=findViewById(R.id.sexeStagFormateur);
//        filier=findViewById(R.id.filiereStagFormateur);
//        Button aj=findViewById(R.id.saveSatgiare);

        setContentView(R.layout.activity_conect_formateur);
        TextView N;
        N=findViewById(com.wail.dericteur.R.id.textView14);
        Bundle extras = getIntent().getExtras();
        String tmp = extras.getString("log");
        N.setText(tmp);
//        aj.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String role=Role.getText().toString();
//                Stagiaire stagiaire=new Stagiaire(login.getText().toString(),pass.getText().toString(),name.getText().toString(),
//                        pre.getText().toString(),role,sexe.getText().toString(),filier.getText().toString(),Integer.parseInt(montion.getText().toString()));
//                db.insertInfoStagaire(stagiaire);
//            }
//        });
        bottomNavigationView=findViewById(com.wail.dericteur.R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                listView=findViewById(com.wail.dericteur.R.id.lik);
                switch (item.getItemId()) {
                    case R.id.iconhome:
                        arrayList.clear();
                        arrayList=db.getUtilisateurParRole("tous");
                        adapter a=new adapter(conect_Formateur.this,arrayList);
                        listView.setAdapter(a);
                        return true;
                    case R.id.iconplus:
                        Intent intent=new Intent(conect_Formateur.this,inscription.class);
                        startActivity(intent);
                        Toast.makeText(conect_Formateur.this, "plus", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.iconproph:
                        Intent intent1=new Intent(conect_Formateur.this,prophil.class);
                        intent1.putExtra("log",tmp);
                        intent1.putExtra("role","Dericteur");
                        startActivity(intent1);
                        return true;
                    case R.id.iconset:
                        Toast.makeText(conect_Formateur.this, "setting", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.holder:
                        Intent i=new Intent(conect_Formateur.this,inscription.class);
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
        inflater.inflate(R.menu.menu_formateur,menu);
        return true;
    }
    public void getinfoUtilisateur(String r){
        listView=findViewById(R.id.lik);
        ArrayList<utilisateur> a=new ArrayList<>();
        if(arrayList.size()==0){a=db.getUtilisateurParRole(r);
            adapter a1=new adapter(conect_Formateur.this,a);
            listView.setAdapter(a1);
        }else{
            a.clear();
            adapter a1=new adapter(conect_Formateur.this,a);
            listView.setAdapter(a1);
        }}
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Sta:
                linearLayout=findViewById(R.id.listVFormateur);
                linearLayout.setVisibility(View.VISIBLE);
                getinfoUtilisateur("s");
                return true;
            case R.id.sea:
                Toast.makeText(this, "actualise avec succes", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.set:
                Toast.makeText(this, "setting ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.ajouter:
//                LinearLayout linearLayout=findViewById(R.id.gone_gon);
//                linearLayout.setVisibility(View.VISIBLE);
                Intent intent=new Intent(conect_Formateur.this,inscription.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}