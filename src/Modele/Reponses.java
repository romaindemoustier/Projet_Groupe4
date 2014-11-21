package Modele;
public class Reponses {
	// Notez que l'identifiant est un long
	protected int id_reponses;
	protected String reponses;
	protected int id_questions;

	public Reponses(){
		
	}
	
	public Reponses(int id_reponses){
		this.id_reponses = id_reponses;
	}
	
	public Reponses(String reponses, int id_questions) {
		super();
		this.reponses = reponses;
		this.id_questions = id_questions;
	}

	public int getId_reponses() {
		return id_reponses;
	}

	public void setId_reponses(int id_reponses) {
		this.id_reponses = id_reponses;
	}

	public String getReponses() {
		return reponses;
	}

	public void setReponses(String reponses) {
		this.reponses = reponses;
	}
	
	public int getId_questions() {
		return id_questions;
	}

	public void setId_questions(int questions) {
		this.id_questions = id_questions;
	}
}