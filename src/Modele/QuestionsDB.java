package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionsDB extends Questions implements CRUD {

    protected static Connection dbConnect = null;

    public QuestionsDB() {

    }

    public QuestionsDB(int id_questions) {
        super(id_questions);
    }

    public QuestionsDB(int id_questions, String questions, boolean verrouillage, boolean professeur) {
        super(id_questions,questions,verrouillage,professeur);
    }

    public static void setConnection(Connection C) {
        dbConnect = C;
    }

    @Override
    public void create() throws Exception {
        CallableStatement c;
        try {
            String req = "call questionscreation(?,?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_questions);
            c.setString(2, questions);
            c.setBoolean(3, verrouillage);
            c.setBoolean(4, professeur);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

    @Override
    public void read() throws Exception {
        CallableStatement c;
        try {
            String req = "{?=call questionslecture(?)}";
            c = dbConnect.prepareCall(req);
            c.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            c.setInt(2, id_questions);
            c.executeQuery();
            ResultSet rs = (ResultSet) c.getObject(1);
            if (rs.next()) {
                this.id_questions = rs.getInt("ID_QUESTIONS");
                this.questions = rs.getString("QUESTIONS");
                this.verrouillage = rs.getBoolean("VERROUILLAGE");
                this.professeur = rs.getBoolean("PROFESSEUR");
            } else {
                throw new Exception("Numero de chambre inconnu");
            }
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

    @Override
    public void update() throws Exception {
        CallableStatement c;
        try {
            String req = "call questionsmaj(?,?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_questions);
            c.setString(2, questions);
            c.setBoolean(3, verrouillage);
            c.setBoolean(4, professeur);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

    @Override
    public void delete() throws Exception {
        CallableStatement c;
        try {
            String req = "call questionssupprime(?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_questions);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

}
