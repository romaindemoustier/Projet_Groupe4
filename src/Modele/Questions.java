package Modele;
public class Questions {
	// Notez que l'identifiant est un long
	protected int id_questions;
	protected String questions;
	protected boolean verrouillage;
	protected int professeur;

	public Questions() {
		
	}
	
	public Questions(int id_questions) {
		this.id_questions = id_questions;
	}
	
	public Questions(int id_questions, String questions, boolean verrouillage, int professeur) {
		super();
		this.id_questions = id_questions;
		this.questions = questions;
		this.verrouillage = verrouillage;
		this.professeur = professeur;
	}

	public int getId_questions() {
		return id_questions;
	}

	public void setId_questions(int id_questions) {
		this.id_questions = id_questions;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public boolean getVerrouillage() {
		return verrouillage;
	}

	public void setVerrouillage(boolean verrouillage) {
		this.verrouillage = verrouillage;
	}
	
	public int getProfesseur() {
		return professeur;
	}

	public void setProfesseur(int professeur) {
		this.professeur = professeur;
	}
}