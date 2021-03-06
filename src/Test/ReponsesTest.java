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
            rep1 = new ReponsesDB("Rep1",40);
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
            rep1 = new ReponsesDB("Rep2",40);
            rep1.create();
            int numero = rep1.getId_reponses();
            rep1.delete();
            rep2 = new ReponsesDB(numero);
            rep2.read();
            System.out.println("REPONSE 2 =" + rep2);
            System.out.println("BAD");

        } catch (Exception e) {
            System.out.println("OK Exception normale d'�ffacement" + e);
        }

        try {
        	rep1.delete();
        } catch (Exception e) {
        }

        try {
            System.out.println("TEST MISE A JOUR REPONSES");
            rep1 = new ReponsesDB("Rep3",40);
            rep1.create();
            int numero = rep1.getId_reponses();
            rep1.setReponses("Rep4");
            rep1.update();
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
            System.out.println("TEST D'AJOUT DOUBLON");
            rep1 = new ReponsesDB("Rep1",40);
            rep1.create();
            int numero = rep1.getId_reponses();
            rep2 = new ReponsesDB(numero);
            rep2.read();
            System.out.println("reponse 2 = " + rep2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("La reponse existe d�j�");

        }
        try {
        	rep1.delete();
        } catch (Exception e) {

        }
        
        try {
            System.out.println("TEST D'AJOUT QUESTION N'EXISTANT PAS");
            rep1 = new ReponsesDB("Rep5",5);
            rep1.create();
            int numero = rep1.getId_reponses();
            rep2 = new ReponsesDB(numero);
            rep2.read();
            System.out.println("reponse 2 = " + rep2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("La question n'existe pas");

        }
        try {
        	rep1.delete();
        } catch (Exception e) {

        }

      /*  try {
            System.out.println("TEST D'EFFACEMENT INFRUCTUEUX. LA REPONSES ETANT IMPLIQUE DANS UNE QUESTION");
            rep1 = new ReponsesDB(2);
            rep1.delete();
            System.out.println("BAD");
        } catch (Exception e) {
            System.out.println("OK exception normale d'effacement. La reponse etant implique dans une question.");
        }

        try {
            c.close();
        } catch (Exception e) {
        }*/
        
    }
}
