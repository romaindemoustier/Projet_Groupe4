package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UtilisateurDB extends Utilisateur implements CRUD {

    protected static Connection dbConnect = null;

    public UtilisateurDB() {

    }

    public UtilisateurDB(int id_user) {
        super(id_user);
    }

    public UtilisateurDB(String login, String password, boolean estprof) {
        super(login, password,estprof);
    }

    public static void setConnection(Connection C) {
        dbConnect = C;
    }

    @Override
    public void  create() throws Exception {
        CallableStatement c;
        try {
            String req = "call create_utilisateur(?,?,?,?)";
            c = dbConnect.prepareCall(req);
            /*c.setInt(1, id_user);*/
            c.registerOutParameter(1, java.sql.Types.INTEGER);
            c.setString(2, login);
            c.setString(3, password);
            c.setBoolean(4, estprof);
            c.executeUpdate();
            this.id_user=c.getInt(1);
            c.close();
        } catch (Exception e) {
            throw new Exception("Erreur " + e.getMessage());
        }
    }

    @Override
	public void read() throws Exception {
		String query = "SELECT * FROM UTILISATEURS WHERE ID_USER=?";
		PreparedStatement cstmt = null;
		try {
			cstmt = dbConnect.prepareStatement(query);
			cstmt.setInt(1, this.id_user);
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				this.login = rs.getString("login");
				this.password = rs.getString("password");
				this.estprof = rs.getBoolean("estprof");
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
    
    
    public ArrayList<UtilisateurDB> rechlog(String login) throws Exception 
	   {
		    ArrayList<UtilisateurDB> rech = new ArrayList<UtilisateurDB>();
		    
		    String req = "{?=call read_utilisateur_login(?)}";
	            
		    CallableStatement cstmt = null;
		    try
		    {
		       cstmt = dbConnect.prepareCall(req);
	           cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
		       cstmt.setString(2,login);
		       cstmt.executeQuery();
	           ResultSet rs = (ResultSet)cstmt.getObject(1);
		       boolean trouve = false;
		    
	           while(rs.next())
	           {
	              trouve=true;
		          /*int id_user = rs.getInt("ID_USER");*/
		       	  String log = rs.getString("LOGIN");
		       	  String password=rs.getString("PASSWORD");
		     	  Boolean estprof = rs.getBoolean("ESTPROF");
		     
		     	  rech.add(new UtilisateurDB(log,password,estprof));
		       }
		    
	           if(!trouve)
	           {
	        	  throw new Exception("nom inconnu");
	           }
	           else 
	           {
	           	  return rech;
	           }
		    }
		    catch(Exception e)
		    {
	            throw new Exception("Erreur de lecture "+e.getMessage());
	        }
	        finally
	        {
	           try
	           {
	              cstmt.close();
	           }
	           catch (Exception e){}
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
