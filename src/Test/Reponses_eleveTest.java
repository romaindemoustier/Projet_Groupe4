package Test;

import Modele.Reponses_eleveDB;
import MyConnection.DBConnection;

import java.sql.Connection;

public class Reponses_eleveTest {

    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        Connection c = dbc.getConnection();
        if (c == null) {
            System.out.println("connexion impossible !");
            System.exit(0);
        }
        Reponses_eleveDB.setConnection(c);
        Reponses_eleveDB rep_eleve1 = null, rep_eleve2 = null;

        try {
            System.out.println("TEST D'AJOUT REPONSES UTILISATEUR");
            rep_eleve1 = new Reponses_eleveDB(3,1,5);
            rep_eleve1.create();
            int numero = rep_eleve1.getId_eleve()+rep_eleve1.getId_questions();
            rep_eleve2 = new Reponses_eleveDB(numero);
            rep_eleve2.read();
            System.out.println("reponse 2 = " + rep_eleve2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("BAD Exception d'Ajout Reponses utilisateur " + e);

        }
        try {
        	rep_eleve1.delete();
        } catch (Exception e) {

        }

        try {
            System.out.println("TEST D'EFFACEMENT FRUCTUEUX REPONSES");
            rep_eleve1 = new Reponses_eleveDB(3,2,7);
            rep_eleve1.create();
            int numero = rep_eleve1.getId_eleve()+rep_eleve1.getId_questions();
            rep_eleve1.delete();
            rep_eleve2 = new Reponses_eleveDB(numero);
            rep_eleve2.read();
            System.out.println("REPONSE 2 =" + rep_eleve2);
            System.out.println("BAD");

        } catch (Exception e) {
            System.out.println("OK Exception normale d'éffacement" + e);
        }

        try {
        	rep_eleve1.delete();
        } catch (Exception e) {
        }
        
        try {
            System.out.println("TEST D'AJOUT DOUBLON");
            rep_eleve1 = new Reponses_eleveDB(3,2,7);
            rep_eleve1.create();
            int numero = rep_eleve1.getId_reponses();
            rep_eleve2 = new Reponses_eleveDB(numero);
            rep_eleve2.read();
            System.out.println("reponse 2 = " + rep_eleve2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("Cet élève a déjà répondu");

        }
        try {
        	rep_eleve1.delete();
        } catch (Exception e) {

        }
        
        try {
            System.out.println("TEST D'AJOUT QUESTION N'EXISTANT PAS");
            rep_eleve1 = new Reponses_eleveDB(3,10,1);
            rep_eleve1.create();
            int numero = rep_eleve1.getId_reponses();
            rep_eleve2 = new Reponses_eleveDB(numero);
            rep_eleve2.read();
            System.out.println("reponse 2 = " + rep_eleve2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("La question n'existe pas");

        }
        try {
        	rep_eleve1.delete();
        } catch (Exception e) {

        }
        
        try {
            System.out.println("TEST D'AJOUT REPONSE N'EXISTANT PAS");
            rep_eleve1 = new Reponses_eleveDB(3,1,10);
            rep_eleve1.create();
            int numero = rep_eleve1.getId_reponses();
            rep_eleve2 = new Reponses_eleveDB(numero);
            rep_eleve2.read();
            System.out.println("reponse 2 = " + rep_eleve2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("La reponse n'existe pas");

        }
        try {
        	rep_eleve1.delete();
        } catch (Exception e) {

        }
        
        try {
            System.out.println("TEST D'AJOUT ELEVE N'EXISTANT PAS");
            rep_eleve1 = new Reponses_eleveDB(10,1,1);
            rep_eleve1.create();
            int numero = rep_eleve1.getId_reponses();
            rep_eleve2 = new Reponses_eleveDB(numero);
            rep_eleve2.read();
            System.out.println("reponse 2 = " + rep_eleve2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("Cet eleve ne peut pas avoir répondu, il n'existe pas");

        }
        try {
        	rep_eleve1.delete();
        } catch (Exception e) {

        }
        
    }
}
