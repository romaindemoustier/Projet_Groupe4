package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Reponses_eleveDB extends Reponses_eleve implements CRUD {

    protected static Connection dbConnect = null;

    public Reponses_eleveDB() {

    }

    public Reponses_eleveDB(int rep_eleve_id) {
        super(rep_eleve_id);
    }

    public Reponses_eleveDB(int id_eleve, int id_questions, int id_reponses) {
        super(id_eleve, id_questions, id_reponses);
    }

    public static void setConnection(Connection C) {
        dbConnect = C;
    }

    @Override
    public void create() throws Exception {
        CallableStatement c;
        try {
            String req = "call create_reponse_eleve(?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_eleve);
            c.setInt(2, id_questions);
            c.setInt(3, id_reponses);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

    @Override
	public void read() throws Exception {
		String query = "SELECT * FROM REPONSES_ELEVE WHERE ID_ELEVE=? AND ID_QUESTIONS=?";
		PreparedStatement cstmt = null;
		try {
			cstmt = dbConnect.prepareStatement(query);
			cstmt.setInt(1, this.id_eleve);
			cstmt.setInt(2, this.id_questions);
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				this.id_eleve = rs.getInt("id_eleve");
				this.id_questions = rs.getInt("id_questions");
				this.id_reponses = rs.getInt("id_reponses");
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
    public void delete() throws Exception {
        CallableStatement c;
        try {
            String req = "call reponse_eleve_supp(?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_eleve);
            c.setInt(2, id_questions);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }
    
    @Override
    public void update() throws Exception {
        CallableStatement c;
        try {
            String req = "call reponse_eleve_maj(?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_eleve);
            c.setInt(2, id_questions);
            c.setInt(2, id_reponses);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

}
