package Modele;
public class Reponses {
	// Notez que l'identifiant est un long
	protected int id_reponses;
	protected String reponses;

	public Reponses(){
		
	}
	
	public Reponses(int id_reponses){
		this.id_reponses = id_reponses;
	}
	
	public Reponses(int id_reponses, String reponses) {
		super();
		this.id_reponses = id_reponses;
		this.reponses = reponses;
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
}