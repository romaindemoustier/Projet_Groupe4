package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuestionsDB extends Questions implements CRUD {

    protected static Connection dbConnect = null;

    public QuestionsDB() {

    }

    public QuestionsDB(int id_questions) {
        super(id_questions);
    }

    public QuestionsDB(String questions, boolean verrouillage, int professeur) {
        super(questions,verrouillage,professeur);
    }

    public static void setConnection(Connection C) {
        dbConnect = C;
    }

    @Override
    public void create() throws Exception {
        CallableStatement c;
        try {
            String req = "call create_question(?,?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_questions);
            c.setString(2, questions);
            c.setBoolean(3, verrouillage);
            c.setInt(4, professeur);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

    @Override
	public void read() throws Exception {
		String query = "SELECT * FROM QUESTIONS WHERE ID_QUESTIONS=?";
		PreparedStatement cstmt = null;
		try {
			cstmt = dbConnect.prepareStatement(query);
			cstmt.setInt(1, this.id_questions);
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				this.questions = rs.getString("questions");
				this.verrouillage = rs.getBoolean("verrouillage");
				this.professeur = rs.getInt("professeur");
			}

		} catch (Exception e) {
			throw new Exception("Erreur " + e.getMessage());
		} finally {
			try {
				cstmt.close();
			} catch (Exception e) {

			}
		}
	}

    @Override
    public void update() throws Exception {
        CallableStatement c;
        try {
            String req = "call question_maj(?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_questions);
            c.setString(2, questions);
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
            String req = "call question_supp(?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_questions);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

}
