package Modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    
    public static ArrayList<QuestionsDB> verifverrouillage1(int id_quest ) throws Exception {
        
        ArrayList<QuestionsDB> cherche = new ArrayList<QuestionsDB>();
        String query = "SELECT * FROM QUESTIONS WHERE ID_QUESTIONS=?";
        
        PreparedStatement cstmt = null;
     try {
    	 
    	System.out.println("reception de "+id_quest); 
      cstmt = dbConnect.prepareStatement(query);
      cstmt.setInt(1,id_quest);
      ResultSet rs = cstmt.executeQuery();
      boolean trouve = false;
      QuestionsDB quest = new QuestionsDB();
      while (rs.next()) {//PROBLEME
    	trouve=true;  
      quest.id_questions = rs.getInt("ID_QUESTIONS");
       quest.questions = rs.getString("QUESTIONS");
       quest.verrouillage = rs.getBoolean("VERROUILLAGE");
       quest.professeur = rs.getInt("PROFESSEUR");
       cherche.add(quest);
      }
      if(!trouve){throw new Exception("id inconnu");}
               else{return cherche;}

     } catch (Exception e) {
      throw new Exception("Erreur " + e.getMessage());
     } finally {
      try {
       cstmt.close();
      } catch (Exception e) {

      }
     }
    }
       
       /*public ArrayList<QuestionsDB> verifverrouillage(Boolean verif, String id_questions) throws Exception //utilisé lors de la connection à la BD d'un utilisateur
       {
         ArrayList<QuestionsDB> cherche = new ArrayList<QuestionsDB>();
         
         String req = "{?=call READ_QUESTION_VERROUILLAGE(?,?)}";      
         CallableStatement cstmt = null;
         try
         {
            cstmt = dbConnect.prepareCall(req);System.out.println("OK11");
               cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2,id_questions);System.out.println("OK12");
            cstmt.executeQuery();System.out.println("OK1");//LE PROBLEME EST ICI
               ResultSet rs = (ResultSet)cstmt.getObject(1);
            boolean trouve = false;
            System.out.println("OK2");
               while(rs.next())
               {
                  trouve=true;
               /*int id_question = rs.getInt("ID_QUESTIONS");
               String questions = rs.getString("QUESTIONS");
               Boolean verrouillage =rs.getBoolean("VEROUILLAGE");
             int professeur = rs.getInt("PROFESSEUR");
            }
              
               if(!trouve){throw new Exception("id inconnu");}
               else{ System.out.println("OK3");return cherche;}
               
               
               
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
      }*/

}
