package Modele;
public class Utilisateur {
	
	
	protected int id_user;
	protected String login;
	protected String password;
	protected boolean estprof;

	public Utilisateur(){
		
	}
	
	public Utilisateur(int id_user){
		this.id_user = id_user;
	}
	

	public Utilisateur(int id_user, String login, String password, boolean estprof) {
		super();
		this.id_user=id_user;
		this.login = login;
		this.password = password;
		this.estprof = estprof;
	}
	
	public Utilisateur(String login, String password, boolean estprof) {
		super();
		this.login = login;
		this.password = password;
		this.estprof = estprof;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId(int id_user) {
		this.id_user = id_user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEstprof() {
		return estprof;
	}

	public void setEstprof(boolean estprof) {
		this.estprof = estprof;
	}
}