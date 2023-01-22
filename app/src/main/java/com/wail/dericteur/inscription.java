package com.wail.dericteur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.role.RoleManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class inscription extends AppCompatActivity   {
    String[] users = { "Dericteur", "Formateur", "Stagiaire" };
    public ArrayList<utilisateur> arrayList=new ArrayList<utilisateur>();
public  String fil;
    public basedonne db=new basedonne(inscription.this,"Gestion_auteur",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        EditText Role,name,pre,login,pass,montion,sexe,filier,specialite,matricul,diplome,DAteRecrutement;
        Button save;
//        edit de formateur
        matricul=findViewById(R.id.matricul);
        diplome=findViewById(R.id.Diplome);
        specialite=findViewById(R.id.sepicilite);
        DAteRecrutement=findViewById(R.id.DAteRecrutement);
        //        edit de Stagaire
        Role=findViewById(R.id.Rolle);
        name=findViewById(R.id.nameSF);
        pre=findViewById(R.id.prenomSF);
        login=findViewById(R.id.loginSF);
        pass=findViewById(R.id.pasSF);
        montion=findViewById(R.id.bacSF);
         sexe=findViewById(R.id.sexeSF);

filier=findViewById(R.id.editTextTextPersonName);
        save=findViewById(R.id.saveS);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {String role=Role.getText().toString();
                switch (role) {
                    case "stagaire":
                        Stagiaire stagiaire = new Stagiaire(login.getText().toString(), pass.getText().toString(), name.getText().toString(),
                                pre.getText().toString(), role, sexe.getText().toString(), filier.getText().toString(), Integer.parseInt(montion.getText().toString()));
                        db.insertInfoStagaire(stagiaire);
                        Toast.makeText(inscription.this, "insert valid Satgaire ", Toast.LENGTH_LONG).show();
                        break;
                    case "formateur":
                        formateur formateur = new formateur(login.getText().toString(), pass.getText().toString(), name.getText().toString(),
                                pre.getText().toString(), role, Integer.parseInt(matricul.getText().toString()), diplome.getText().toString(), specialite.getText().toString(), DAteRecrutement.getText().toString());
                        db.insertInfoFormateur(formateur);
                        Toast.makeText(inscription.this, "insert valid formateur", Toast.LENGTH_LONG).show();
                        break;
                    case "dericteur":
                        utilisateur utilisateur = new utilisateur(login.getText().toString(), pass.getText().toString(), name.getText().toString(),
                                pre.getText().toString(), role);
                        db.insertInfoUtilisateur(utilisateur);
                        Toast.makeText(inscription.this, "insert valid utilisateur ", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(inscription.this, "role -> Doit respecter cette form" +
                                "EX :stagaire,formateur,dericteur ", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.inscriremenu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        LinearLayout linearLayoutSta =findViewById(R.id.goneStagaire);
        LinearLayout formateur =findViewById(R.id.goneFormateur);
        switch (item.getItemId()){
            case R.id.Sta:
                linearLayoutSta.setVisibility(View.VISIBLE);
                formateur.setVisibility(View.GONE);
                return true;
            case R.id.forma:
                linearLayoutSta.setVisibility(View.GONE);
                formateur.setVisibility(View.VISIBLE);
                return true;
            case R.id.setting:
                Toast.makeText(this, "setting ", Toast.LENGTH_SHORT).show();
                return true;

            default:
                formateur.setVisibility(View.GONE);
                linearLayoutSta.setVisibility(View.GONE);
                return super.onOptionsItemSelected(item);
        }
    }


}