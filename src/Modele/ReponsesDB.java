package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReponsesDB extends Reponses implements CRUD {

    protected static Connection dbConnect = null;

    public ReponsesDB() {

    }

    public ReponsesDB(int id_reponses) {
        super(id_reponses);
    }

    public ReponsesDB(String reponses, int id_questions) {
        super(reponses, id_questions);
    }

    public static void setConnection(Connection C) {
        dbConnect = C;
    }

    @Override
    public void create() throws Exception {
        CallableStatement c;
        try {
            String req = "call create_reponse(?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_reponses);
            c.setString(2, reponses);
            c.setInt(3, id_questions);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

    @Override
    public void read() throws Exception {
		String query = "SELECT * FROM REPONSES WHERE ID_REPONSES=?";
		PreparedStatement cstmt = null;
		try {
			cstmt = dbConnect.prepareStatement(query);
			cstmt.setInt(1, this.id_reponses);
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				this.reponses = rs.getString("reponses");
				this.id_questions = rs.getInt("id_questions");
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
            String req = "call reponse_maj(?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_reponses);
            c.setString(2, reponses);
            c.setInt(3, id_questions);
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
            String req = "call reponse_supp(?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_reponses);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

}
