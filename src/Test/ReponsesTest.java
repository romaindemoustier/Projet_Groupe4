package Test;

import Modele.ReponsesDB;
import MyConnection.DBConnection;
import java.sql.Connection;

public class ReponsesTest {

    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        Connection c = dbc.getConnection();
        if (c == null) {
            System.out.println("connexion impossible !");
            System.exit(0);
        }
        ReponsesDB.setConnection(c);
        ReponsesDB rep1 = null, rep2 = null;

        try {
            System.out.println("TEST D'AJOUT REPONSES");
            rep1 = new ReponsesDB("Gerard",40);
            rep1.create();
            int numero = rep1.getId_reponses();
            rep2 = new ReponsesDB(numero);
            rep2.read();
            System.out.println("reponse 2 = " + rep2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("BAD Exception d'Ajout Reponses " + e);

        }
        try {
        	rep1.delete();
        } catch (Exception e) {

        }

        try {
            System.out.println("TEST D'EFFACEMENT FRUCTUEUX REPONSES");
            rep1 = new ReponsesDB("Gerard",40);
            rep1.create();
            int numero = rep1.getId_reponses();
            rep1.delete();
            rep2 = new ReponsesDB(numero);
            rep2.read();
            System.out.println("REPONSE 2 =" + rep2);
            System.out.println("BAD");

        } catch (Exception e) {
            System.out.println("OK Exception normale d'éffacement" + e);
        }

        try {
        	rep1.delete();
        } catch (Exception e) {
        }

        try {
            System.out.println("TEST MISE A JOUR REPONSES");
            rep1 = new ReponsesDB("Gerard",40);
            rep1.create();
            int numero = rep1.getId_reponses();
            rep1.setReponses("Roger");
            rep1.update();System.out.println("NON NON");
            rep2 = new ReponsesDB(numero);
            rep2.read();
            System.out.println("reponse 2=" + rep2);
            rep1.delete();
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("BAD Exception MISE A JOUR REPONSES " + e);
        }

        try {
        	rep1.delete();
        } catch (Exception e) {
        }

        try {
            System.out.println("TEST D'EFFACEMENT INFRUCTUEUX. LA REPONSES ETANT IMPLIQUE DANS UNE QUESTION");
            rep1 = new ReponsesDB(2);
            rep1.delete();
            System.out.println("BAD");
        } catch (Exception e) {
            System.out.println("OK exception normale d'effacement. La reponse etant implique dans une question." + e);
        }

        try {
            c.close();
        } catch (Exception e) {
        }
        
        /*
        try {
            System.out.println("TEST D'AJOUT doublon");
            rep1 = new ReponsesDB(9,"Gerard",3);
            System.out.println("Je code avec le cul");
            rep1.create();
            int numero = rep1.getId_reponses();
            rep2 = new ReponsesDB(numero);
            rep2.read();
            System.out.println("reponse 2 = " + rep2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("BAD Exception d'Ajout Reponses " + e);

        }
        try {
        	rep1.delete();
        } catch (Exception e) {

        }*/

        
    }
}
