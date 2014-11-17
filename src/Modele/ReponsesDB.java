package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReponsesDB extends Reponses implements CRUD {

    protected static Connection dbConnect = null;

    public ReponsesDB() {

    }

    public ReponsesDB(int id_reponses) {
        super(id_reponses);
    }

    public ReponsesDB(int id_reponses, String reponses) {
        super(id_reponses,reponses);
    }

    public static void setConnection(Connection C) {
        dbConnect = C;
    }

    @Override
    public void create() throws Exception {
        CallableStatement c;
        try {
            String req = "call chambrecreation(?,?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_reponses);
            c.setString(2, reponses);
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
            String req = "{?=call chambrelecture(?)}";
            c = dbConnect.prepareCall(req);
            c.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            c.setInt(2, id_reponses);
            c.executeQuery();
            ResultSet rs = (ResultSet) c.getObject(1);
            if (rs.next()) {
                this.id_reponses = rs.getInt("ID_USER");
                this.reponses = rs.getString("LOGIN");
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
            String req = "call chambremaj(?,?,?,?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_reponses);
            c.setString(2, reponses);
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
            String req = "call chambresupprime(?)";
            c = dbConnect.prepareCall(req);
            c.setInt(1, id_reponses);
            c.executeUpdate();
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

}
