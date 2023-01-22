package com.wail.dericteur;

public class Stagiaire extends utilisateur {
    public String sexe;
    public String filier;
    public Integer montionBac;

    public Stagiaire(String login, String pass, String nom, String prenom, String role,
                     String sexe,
                     String filier,
                     Integer montionBac) {
        super(login, pass, nom, prenom, role);
        this.sexe = sexe;
        this.filier = filier;
        this.montionBac = montionBac;
    }

    public Stagiaire(String login, String pass, String nom, String prenom, String role) {
        super(login, pass, nom, prenom, role);
    }
}
