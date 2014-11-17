package Test;

import Modele.QuestionsDB;
import MyConnection.DBConnection;
import java.sql.Connection;

public class QuestionsTest {

    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
        Connection c = dbc.getConnection();
        if (c == null) {
            System.out.println("connexion impossible !");
            System.exit(0);
        }
        QuestionsDB.setConnection(c);
        QuestionsDB ques1 = null, ques2 = null;

        try {
            System.out.println("TEST D'AJOUT QUESTIONS");
            ques1 = new QuestionsDB(1, "Romain",false,false);
            ques1.create();
            int numero = ques1.getId_questions();
            ques2 = new QuestionsDB(numero);
            ques2.read();
            System.out.println("question 2 = " + ques2);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("BAD Exception d'Ajout Questions " + e);

        }
        try {
        	ques1.delete();
        } catch (Exception e) {

        }

        try {
            System.out.println("TEST D'EFFACEMENT FRUCTUEUX QUESTIONS");
            ques1 = new QuestionsDB(1, "Romain",false,false);
            ques1.create();
            int numero = ques1.getId_questions();
            ques1.delete();
            ques2 = new QuestionsDB(numero);
            ques2.read();
            System.out.println("QUESTIONS 2 =" + ques2);
            System.out.println("BAD");

        } catch (Exception e) {
            System.out.println("OK Exception normale d'éffacement" + e);
        }

        try {
        	ques1.delete();
        } catch (Exception e) {
        }

        try {
            System.out.println("TEST MISE A JOUR QUESTIONS");
            ques1 = new QuestionsDB(1, "Romain",false,false);
            ques1.create();
            int numero = ques1.getId_questions();
            ques1.setQuestions("Roger");
            ques1.setVerrouillage(false);
            ques1.setProfesseur(false);
            ques1.update();
            ques2 = new QuestionsDB(numero);
            ques2.read();
            System.out.println("questions 2=" + ques2);
            ques1.delete();
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("BAD Exception MISE A JOUR QUESTIONS " + e);
        }

        try {
        	ques1.delete();
        } catch (Exception e) {
        }
    }
}
