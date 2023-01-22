package com.wail.dericteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class conect_Stagiaire extends AppCompatActivity {
    public ArrayList<utilisateur> arrayList=new ArrayList<utilisateur>();
    public basedonne db=new basedonne(conect_Stagiaire.this,"Gestion_auteur",null,1);
    public void getinfoUtilisateur(String r){
        ArrayList<utilisateur> a=new ArrayList<>();
        a=db.getUtilisateurParRole("s");
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conect_stagiaire);
        Bundle extras =getIntent().getExtras();
        String tmp = extras.getString("log");
//        ArrayList<Stagiaire> arrayList;
//        arrayList=db.getPersonneStagaire(tmp);
        TextView logStag,Role;
        EditText name,pre,login,pass,montion,sexe,filier;
        //        edit de Stagaire
        Role=findViewById(R.id.role_Stagaire);
        logStag=findViewById(R.id.textView14);
        name=findViewById(R.id.nm);
        pre=findViewById(R.id.prenomStagFormateur);
        login=findViewById(R.id.loginStagFormateur);
        pass=findViewById(R.id.pasStagFormateur);
//        montion=findViewById(R.id.editTextTextPersonName3);
//        sexe=findViewById(R.id.sexeStagFormateur);
//        filier=findViewById(R.id.filiereStagFormateur);
        Button aj=findViewById(R.id.saveSatgiare);

        logStag.setText(tmp);
        String r="Stagaire";
        Role.setText(r);
        arrayList=db.getUtilisateurParRole("s");
        for (utilisateur s: arrayList
             ) {
            if (s.login.equals(tmp))
            name.setText(s.nom);
            pre.setText(s.prenom);
            login.setText(r);
            pass.setText(s.pass);
        }

//        montion.setText(String.valueOf(s.montionBac));
//            sexe.setText(s.sexe);
//            filier.setText(s.filier);



    }
}