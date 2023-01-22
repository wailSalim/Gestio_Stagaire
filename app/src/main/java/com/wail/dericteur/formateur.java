package com.wail.dericteur;

public class formateur extends utilisateur {
    public Integer matricul;
    public String diplome;
    public String specialite;
    public String dateDeRecrutement;

    public formateur(String login, String pass, String nom, String prenom, String role, Integer matricul,
                     String diplome,
                     String specialite,
                     String dateDeRecrutement) {
        super(login, pass, nom, prenom, role);
        this.matricul = matricul;
        this.dateDeRecrutement = dateDeRecrutement;
        this.diplome = diplome;
        this.specialite = specialite;
    }

    public formateur(String login, String pass, String nom, String prenom, String role) {
        super(login, pass, nom, prenom, role);
    }

}
