package com.wail.dericteur;

public class utilisateur {

        public  int id;
//        public static int cnt=2;
        public String login;
        public String pass;
        public String nom;public String prenom;public String role;
        public utilisateur(String login,String pass,String nom,String prenom,String role){
            this.login = login;
            this.pass = pass;
            this.nom = nom;
            this.prenom = prenom;
            this.role = role;
//            cnt++;
//            this.id=cnt;
        }

    }

