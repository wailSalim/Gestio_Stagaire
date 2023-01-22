package com.wail.dericteur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class prophil extends AppCompatActivity {
    String[] users = { "Dericteur", "Formateur", "Stagiaire" };
    public ArrayList<utilisateur> arrayList=new ArrayList<utilisateur>();

    public basedonne db=new basedonne(prophil.this,"Gestion_auteur",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prophil);
        Bundle extras =getIntent().getExtras();
        String tmp = extras.getString("log");
        String rol = extras.getString("role");
//        String r;
//        if (rol.equals("Dericteur")) {
//            r="D";
//        }else if(rol.equals("Dericteur")){r="f";}
//        else{r="s";}
        TextView role,nom,pre,log,pass;
        role=findViewById(R.id.textView18);
        role.setText(rol);
        arrayList=db.getProph(tmp);
        log=findViewById(R.id.textView20);
        log.setText(tmp.toUpperCase(Locale.ROOT));
        for (utilisateur u:arrayList
             ) {
            nom=findViewById(R.id.textViewN);
            pre=findViewById(R.id.textViewLast);
            pass=findViewById(R.id.textViewP);
            nom.setText(u.nom);
            pre.setText(u.prenom);
            pass.setText(u.pass);
        }


        }
    }
