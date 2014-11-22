package Test;

import Modele.UtilisateurDB;
import MyConnection.DBConnection;
import java.sql.Connection;

public class UtilisateurTest {

    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        Connection c = dbc.getConnection();
        if (c == null) {
            System.out.println("connexion impossible !");
            System.exit(0);
        }
        UtilisateurDB.setConnection(c);
        UtilisateurDB util1 = null, util2 = null;

        try {
            System.out.println("TEST D'AJOUT UTILISATEUR");
            util1 = new UtilisateurDB("Gerard", "Romain",false);
            util1.create();
            int numero = util1.getId_user();
            util2 = new UtilisateurDB(numero);
            util2.read();
            System.out.println("utilisateur 2 = " + util2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("BAD Exception d'Ajout Utilisateur " + e);

        }
        try {
        	util1.delete();
        } catch (Exception e) {

        }
        
        try {
            System.out.println("TEST MISE A JOUR UTILISATEUR");
            util1 = new UtilisateurDB("Gerard1", "Romain",false);
            util1.create();
            int numero = util1.getId_user();
            util1.setLogin("Roger");
            util1.setPassword("Romain");
            util1.update();
            util2 = new UtilisateurDB(numero);
            util2.read();
            System.out.println("utilisateur 2=" + util2);
            util1.delete();
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("BAD Exception MISE A JOUR UTILISATEUR " + e);
        }

        try {
        	util1.delete();
        } catch (Exception e) {
        }

        try {
            System.out.println("TEST D'EFFACEMENT FRUCTUEUX UTILISATEUR");
            util1 = new UtilisateurDB("Gerard2", "Romain", false);
            util1.create();
            int numero = util1.getId_user();
            util1.delete();
            util2 = new UtilisateurDB(numero);
            util2.read();
            System.out.println("UTILISATEUR 2 =" + util2);
            System.out.println("BAD");

        } catch (Exception e) {
            System.out.println("OK Exception normale d'éffacement" + e);
        }

        try {
        	util1.delete();
        } catch (Exception e) {
        }

        try {
            System.out.println("TEST D'AJOUT doublon");
            util1 = new UtilisateurDB("Gerard", "Romain",false);
            util1.create();
            int numero = util1.getId_user();
            util2 = new UtilisateurDB(numero);
            util2.read();
            System.out.println("utilisateur 2 = " + util2);
            System.out.println("NON CA NE DOIT PAS ETRE AJOUTER");
        } catch (Exception e) {
            System.out.println("Test d'ajout de doublon raté, opération réussi");

        }
        try {
        	util1.delete();
        } catch (Exception e) {

        }
        
    }
}
