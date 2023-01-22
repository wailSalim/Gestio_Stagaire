package com.wail.dericteur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] users = { "Dericteur", "Formateur", "Stagiaire" };
    public  ArrayList<utilisateur> arrayList=new ArrayList<utilisateur>();
    public basedonne db=new basedonne(MainActivity.this,"Gestion_auteur",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        //  text
        TextView log=findViewById(R.id.loginStagFormateur);
        TextView pas=findViewById(R.id.pass);
        Button button=findViewById(R.id.loginn);
        Button listB;
        listB=findViewById(R.id.bas);
        ListView listView=findViewById(R.id.lisv);
//         ArrayList<String> a=new ArrayList<String>();
//        a=db.recherchPerson();

        listB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,db.recherchPerson());
                listView.setAdapter(arrayAdapter);
            }
        });
//        a[0] =db.recherchPerson();
//        ArrayAdapter<String> arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, a[0]);
//        listView.setAdapter(arrayAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (db.mainlogin(log.getText().toString(),pas.getText().toString()).equals("dericteur"))
                    {
                        Toast.makeText(MainActivity.this,"dericteur",Toast.LENGTH_LONG).show();
                        listB.setVisibility(View.GONE);
                        Intent intent=new Intent(MainActivity.this, conecting.class);
                        intent.putExtra("log",log.getText().toString());
                        startActivity(intent);
                    }
                    else if (db.mainlogin(log.getText().toString(),pas.getText().toString()).equals("formateur"))
                    {
                        Toast.makeText(MainActivity.this,"formateur",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(MainActivity.this, conect_Formateur.class);
                        intent.putExtra("log",log.getText().toString());
                        startActivity(intent);
                    }
                    else if (db.mainlogin(log.getText().toString(),pas.getText().toString()).equals("stagaire"))
                    {
                        Intent intent=new Intent(MainActivity.this, conect_Stagiaire.class);
                        intent.putExtra("log",log.getText().toString());
                        startActivity(intent);

                        Toast.makeText(MainActivity.this,"stagaire",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"login Not Found",Toast.LENGTH_LONG).show();
                    }}});

    }
    public String RoleUtilisateur;


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        RoleUtilisateur=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), RoleUtilisateur, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}