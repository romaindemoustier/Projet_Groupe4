package com.example.projetgroupe4;

import java.sql.Connection;
import java.util.ArrayList;

import Modele.QuestionsDB;
import Modele.ReponsesDB;
import Modele.Reponses_eleveDB;
import Modele.UtilisateurDB;
import MyConnection.DBConnection;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private Button inscription = null;
	private Button connection = null;

	private MyAccesDB mc = null;
	private Connection con = null;

	private EditText login;
	private EditText mdp;

	public static final String IDUTILISATEUR = "Utilisateur";

	String log, mot_p;

	ArrayList<UtilisateurDB> trouve = new ArrayList<UtilisateurDB>();
	UtilisateurDB util = new UtilisateurDB();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		login = (EditText) findViewById(R.id.login);
		mdp = (EditText) findViewById(R.id.password);
		connection = (Button) findViewById(R.id.B_inscr);
		inscription = (Button) findViewById(R.id.button1);

		inscription.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, Inscription.class);
				startActivity(i);
				finish();
			}
		});

		connection.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				mc = new MyAccesDB(MainActivity.this);
				mc.execute();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			con.close();
			con = null;
			Log.d("connexion", "deconnexion OK");
		} catch (Exception e) {

		}
		Log.d("connexion", "deconnexion OK");
	}

	class MyAccesDB extends AsyncTask<String, Integer, Boolean> {

		private String resultat;
		private ProgressDialog pgd = null;

		public MyAccesDB(MainActivity pActivity) {
			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(MainActivity pActivity) {
			// TODO Auto-generated method stub

		}

		protected void onPreExecute() {
			super.onPreExecute();
			pgd = new ProgressDialog(MainActivity.this);
			pgd.setMessage("veuillez attendre");
			pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pgd.show();
			// Toast.makeText(MainActivity.this,"Veuillez Attendre...",Toast.LENGTH_SHORT).show();

		}

		@Override
		protected Boolean doInBackground(String... arg0) {
			if (con == null) {
				Connection con = new DBConnection().getConnection();
				if (con == null) {
					resultat = "Echec de la connexion !";
					
					return false;
				}

				UtilisateurDB.setConnection(con);
			
			}
			try {
				log = login.getText().toString();
				mot_p = mdp.getText().toString();
				try {
					trouve = util.rechlog(log);
					util = trouve.get(0);

					if (!util.getPassword().equals(mot_p)) {
						resultat = "Mot de passe invalide !";
						return false;
					}
				} catch (Exception e) {
					resultat = "Veuillez remplir tous les champs";
					return false;
				}
			} catch (Exception e) {
				resultat = "Login invalide !";
				return false;
			} finally {
				try {
					con.close();
				} catch (Exception e) {
				}
			}
			return true;

		}

		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			pgd.dismiss();
			if (result) {
				
				Toast.makeText(MainActivity.this,"Connexion à votre compte...", Toast.LENGTH_SHORT).show();
				
				if(!util.getEstprof()){
					Intent i = new Intent(MainActivity.this, Rentrer_id.class);
					i.putExtra(IDUTILISATEUR, "util");
					startActivity(i);
					finish();
				}
				
				else{
				Intent i = new Intent(MainActivity.this, Menu_prof.class);
				i.putExtra(IDUTILISATEUR, "util");
				startActivity(i);
				finish();
				}
			} else {
				mdp.setText("");
				Toast.makeText(MainActivity.this, resultat, Toast.LENGTH_SHORT)
						.show();
			}

		}

	}

}
