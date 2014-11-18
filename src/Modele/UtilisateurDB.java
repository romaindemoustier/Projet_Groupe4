package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UtilisateurDB extends Utilisateur implements CRUD {

    protected static Connection dbConnect = null;

    public UtilisateurDB() {

    }

    public UtilisateurDB(int id_user) {
        super(id_user);
    }

    public UtilisateurDB(int id_user, String login, String password, boolean estprof) {
        super(id_user, login, password,estprof);
    }

    public static void setConnection(Connection C) {
        dbConnect = C;
    }

    @Override
    public void create() throws Exception {
        CallableStatement c;
        try {
            String req = "call create_utilisateur(?,?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_user);
            c.setString(2, login);
            c.setString(3, password);
            c.setBoolean(4, estprof);
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
            String req = "{?=call read_utilisateur(?)}";
            c = dbConnect.prepareCall(req);
            c.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            c.setInt(2, id_user);
            c.executeQuery();
            ResultSet rs = (ResultSet) c.getObject(1);
            if (rs.next()) {
                this.id_user = rs.getInt("ID_USER");
                this.login = rs.getString("LOGIN");
                this.password = rs.getString("PASSWORD");
                this.estprof = rs.getBoolean("ESTPROF");
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
            String req = "call utilisateur_maj(?,?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_user);
            c.setString(2, login);
            c.setString(3, password);
            c.setBoolean(4, estprof);
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
            String req = "call utilisateur_supp(?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_user);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

}
