package Modele;

public class Reponses_eleve {
	protected int id_eleve;
	protected int id_questions;
	protected int id_reponses;
	protected int rep_eleve_id;
	
public Reponses_eleve() {
		
	}
	
	public Reponses_eleve(int rep_eleve_id) {
		this.rep_eleve_id = rep_eleve_id;
	}
	
	public Reponses_eleve(int id_eleve, int id_questions, int id_reponses) {
		super();
		this.id_eleve = id_eleve;
		this.id_questions = id_questions;
		this.id_reponses = id_reponses;
	}

	public int getId_eleve() {
		return id_eleve;
	}

	public void setId_eleve(int id_eleve) {
		this.id_eleve = id_eleve;
	}

	public int getId_questions() {
		return id_questions;
	}

	public void setId_questions(int id_questions) {
		this.id_questions = id_questions;
	}

	public int getId_reponses() {
		return id_reponses;
	}

	public void setId_reponses(int id_reponses) {
		this.id_reponses = id_reponses;
	}

	/*public int getRep_eleve_id() {
		return rep_eleve_id;
	}

	public void setRep_eleve_id(int rep_eleve_id) {
		this.rep_eleve_id = rep_eleve_id;
	}*/
	
	public int getRep_eleve_id() {
		return id_eleve+id_questions;
	}

	public void setRep_eleve_id(int id_eleve, int id_questions) {
		this.id_eleve = id_eleve;
		this.id_questions = id_questions;
	}

}
