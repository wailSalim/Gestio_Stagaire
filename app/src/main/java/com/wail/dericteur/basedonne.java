package com.wail.dericteur;
import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;

        import java.util.ArrayList;

   public class basedonne extends SQLiteOpenHelper {
    public basedonne(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table if not exists utilisateur (ID INTEGER PRIMARY KEY AUTOINCREMENT,login text,password text,nom text,prenom text,role text)");
            db.execSQL("create table if not exists formateur (matricul INTEGER ,diplome text,specialite text,dateDeRecrutement text,ID INTEGER,FOREIGN KEY (ID) REFERENCES utilisateur(ID)) ");
            db.execSQL("create table if not exists stagaire (nom text,prenom text,sexe text,filier text  ,montionBac text,ID INTEGER,FOREIGN KEY (ID) REFERENCES utilisateur(ID),FOREIGN KEY (filier) REFERENCES filier(intitul))");
            db.execSQL("create table if not exists filier (intitul text PRIMARY KEY  ,description text)");
        db.execSQL("insert  into utilisateur(login , password,nom , prenom ,role)  values('wail@','1111','wail','salim','Dericteur')");
        db.execSQL("insert  into utilisateur(login , password,nom , prenom ,role)  values('adam@','2222','adam','fail','formateur')");
//            db.execSQL("insert  into formateur(matricul  ,diplome ,specialite ,dateDeRecrutement,ID )  values(123,'TS','ID','12/12/2009',2)");
//            db.execSQL("insert  into stagaire(nom  ,prenom ,sexe ,filier,montionBac,ID )  values('123','TS','ID','12/12/2009',3)");
//
//            db.execSQL("insert  into utilisateur(login , password,nom , prenom ,role)  values('Ilyias@','0000','Ilyias','Fathi','Dericteur')");




        }
        //fun login
        ArrayList<Stagiaire> arr=new ArrayList<>();
        public ArrayList<Stagiaire> getPersonneStagaire(String l) {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] userinfo = new String[]{l};
            Cursor cr = db.rawQuery("SELECT* FROM stagaire where login=?", userinfo);
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
                int id = cr.getInt(cr.getColumnIndexOrThrow("ID"));
                String name = cr.getString(cr.getColumnIndexOrThrow("nom"));
                String prenom = cr.getString(cr.getColumnIndexOrThrow("prenom"));
                String login = cr.getString(cr.getColumnIndexOrThrow("login"));
                String pass = cr.getString(cr.getColumnIndexOrThrow("password"));
                String role = cr.getString(cr.getColumnIndexOrThrow("role"));
                String sexe=cr.getString(cr.getColumnIndexOrThrow("sexe"));
                String filier=cr.getString(cr.getColumnIndexOrThrow("filier"));
                String montion_bac=cr.getString(cr.getColumnIndexOrThrow("montionBac"));
                Stagiaire ps = new Stagiaire(login,pass,name,prenom,role,sexe,filier,Integer.parseInt(montion_bac));

                ps.id = id;
                arr.add(ps);
                cr.moveToNext();
            }
            cr.close();
            db.close();
            return arr;
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void insertInfoUtilisateur(utilisateur p) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nom", p.nom);
            cv.put("prenom", p.prenom);
            cv.put("login", p.login);
            cv.put("password", p.pass);
            cv.put("role", p.role);
            db.insert("utilisateur", null, cv);
            db.close();
        }
        public void insertInfoStagaire(@NonNull Stagiaire p) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            ContentValues v = new ContentValues();
            v.put("nom", p.nom);
            v.put("prenom", p.prenom);
            v.put("login", p.login);
            v.put("password", p.pass);
            v.put("role", p.role);
            db.insert("utilisateur", null, v);
            cv.put("nom", p.nom);
            cv.put("prenom", p.prenom);
            cv.put("sexe",p.sexe);
            cv.put("filier", p.filier);
            cv.put("montionBac", p.montionBac);
//        cv.put("ID", p.id);
            db.insert("stagaire", null, cv);
            db.close();
        }
        public void insertInfoFormateur(formateur p) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            ContentValues v = new ContentValues();
            v.put("nom", p.nom);
            v.put("prenom", p.prenom);
            v.put("login", p.login);
            v.put("password", p.pass);
            v.put("role", p.role);
            db.insert("utilisateur", null, v);
            cv.put("matricul", p.matricul);
            cv.put("diplome", p.diplome);
            cv.put("specialite",p.specialite);
            cv.put("dateDeRecrutement", p.dateDeRecrutement);
//        cv.put("ID", p.id);
            db.insert("formateur", null, cv);
            db.close();


        }
        // modifier ModifierUtilisateur

        public void ModifierUtilisateur(utilisateur p) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put("nom", p.nom);
            cv.put("prenom", p.prenom);
            cv.put("login", p.login);
            cv.put("pass", p.pass);
            cv.put("role", p.role);
            db.update("utilisateur",cv,"nom=?",new String[]{String.valueOf(p.nom)});
            db.close();
        }

        //modifier Stagaire
        public void ModifierStagaire(Stagiaire p) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nom", p.nom);
            cv.put("prenom", p.prenom);
            cv.put("sexe",p.sexe);
            cv.put("filier", p.filier);
            cv.put("montionBac", p.montionBac);
            cv.put("ID", p.id);
            db.update("stagaire",cv,"ID=?",new String[]{String.valueOf(p.id)});
            db.close();
        }
        //Suprimer Satire
        public void SuprimerStagaire(String nom) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("stagaire","nom=?",new String[]{nom});
        }
        //Suprimer formateur
        public void Suprimerformateur(String nom) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("formateur","nom=?",new String[]{nom});
        }

        //get personne

        public ArrayList<utilisateur> list = new ArrayList<>();

        public ArrayList<utilisateur> getPersonne() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr = db.rawQuery("select * from utilisateur", null);
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
                int id = cr.getInt(cr.getColumnIndexOrThrow("ID"));
                String name = cr.getString(cr.getColumnIndexOrThrow("nom"));
                String prenom = cr.getString(cr.getColumnIndexOrThrow("prenom"));
                String login = cr.getString(cr.getColumnIndexOrThrow("login"));
                String pass = cr.getString(cr.getColumnIndexOrThrow("password"));
                String role = cr.getString(cr.getColumnIndexOrThrow("role"));

                utilisateur ps = new utilisateur(login,pass,name,prenom,role);
                ps.id = id;
                list.add(ps);
                cr.moveToNext();
            }
            cr.close();
            db.close();
            return list;
        }
        //get Stagaire
        public ArrayList<utilisateur> getUtilisateurParRole(String n) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr;
            switch (n) {
                case "s":
                    cr = db.rawQuery("select * from utilisateur where role like 's%' ", null);
                    break;
                case "d":
                    cr = db.rawQuery("select * from utilisateur where role like 'd%' ", null);
                    break;
                case "tous":
                    cr = db.rawQuery("select * from utilisateur  ", null);
                    break;
                default:
                    cr = db.rawQuery("select * from utilisateur where role like 'f%' ", null);
                    break;
            }
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
                int id = cr.getInt(cr.getColumnIndexOrThrow("ID"));
                String name = cr.getString(cr.getColumnIndexOrThrow("nom"));
                String prenom = cr.getString(cr.getColumnIndexOrThrow("prenom"));
                String login = cr.getString(cr.getColumnIndexOrThrow("login"));
                String pass = cr.getString(cr.getColumnIndexOrThrow("password"));
                String role = cr.getString(cr.getColumnIndexOrThrow("role"));
                utilisateur ps = new utilisateur(login,pass,name,prenom,role);
                ps.id = id;
                list.add(ps);
                cr.moveToNext();
            }
            cr.close();
            db.close();
            return list;
        }
        public ArrayList<utilisateur> getProph(String n) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr;
            cr = db.rawQuery("select * from utilisateur where login like ? ", new String[]{n});
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
            int id = cr.getInt(cr.getColumnIndexOrThrow("ID"));
            String name = cr.getString(cr.getColumnIndexOrThrow("nom"));
            String prenom = cr.getString(cr.getColumnIndexOrThrow("prenom"));
            String login = cr.getString(cr.getColumnIndexOrThrow("login"));
            String pass = cr.getString(cr.getColumnIndexOrThrow("password"));
            String role = cr.getString(cr.getColumnIndexOrThrow("role"));
            utilisateur ps = new utilisateur(login,pass,name,prenom,role);
            ps.id = id;
                list.add(ps);
                cr.moveToNext();
            }
            cr.close();
            db.close();
            return list;
        }
        public String nom;
        String id, name, prenom, login, pass;
        ArrayList<String> l = new ArrayList<>();

        public ArrayList<String> recherchPerson() {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cr=db.rawQuery("select * from utilisateur  ",null);
            cr.moveToFirst();
            while (!cr.isAfterLast()) {
                id = cr.getString(cr.getColumnIndexOrThrow("ID"));
                name = cr.getString(cr.getColumnIndexOrThrow("nom"));
                prenom = cr.getString(cr.getColumnIndexOrThrow("prenom"));
                login = cr.getString(cr.getColumnIndexOrThrow("login"));
                pass = cr.getString(cr.getColumnIndexOrThrow("password"));
                String role = cr.getString(cr.getColumnIndexOrThrow("role"));
                l.add(id + "-" + name + "-" + prenom + "-" + login + "-" + pass+ "-" + role);

                cr.moveToNext();
            }
            cr.close();
            db.close();
            return l;
        }


        public String mainlogin(String login, String password)
        {
            SQLiteDatabase db=this.getReadableDatabase();
            String[] userinfo = new String[]{login,password};
            Cursor c=db.rawQuery("select * from utilisateur where login = ? and password = ? ",userinfo);
            int count = c.getCount();
            c.moveToFirst();
            if (c!=null && count>0)
            {
                String role= c.getString(5);
                c.close();
                return role.toLowerCase();
            }
            else{
                return "no";}}
    }



