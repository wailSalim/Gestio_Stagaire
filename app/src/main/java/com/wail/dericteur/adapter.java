package com.wail.dericteur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adapter extends BaseAdapter {
        public Context context;
        LayoutInflater layoutInflater;
        public ArrayList<utilisateur> arrayList;
        public adapter(Context con,ArrayList<utilisateur> arrayList1){
            this.arrayList=arrayList1;
            this.context=con;
            layoutInflater=LayoutInflater.from(con);
        }
//        public void suprimItem(int position){
//
//        }
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



    //    @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=layoutInflater.inflate(R.layout.dess,null);
            TextView n,p,s,id;
            ImageButton Mod ,Sup;
            id=(TextView)convertView.findViewById(R.id.ID_DESS);
            n=(TextView)convertView.findViewById(R.id.log);
            p= (TextView)convertView.findViewById(R.id.pass);
            s=(TextView)convertView.findViewById(R.id.role);
            id.setText(String.valueOf(arrayList.get(position).id));
            n.setText(arrayList.get(position).login);
            p.setText(arrayList.get(position).pass);
            s.setText(arrayList.get(position).role);
            Mod=convertView.findViewById(R.id.Mod);
            Sup=convertView.findViewById(R.id.Sup);
           Sup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(position);
                    notifyDataSetChanged();
                }
            });
            Mod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "tu peux modifier apres", Toast.LENGTH_SHORT).show();
            }});
            return convertView;
        }
    }

