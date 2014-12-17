package com.example.projetgroupe4;

import java.sql.Connection;
import java.util.ArrayList;

import Modele.QuestionsDB;
import Modele.Reponses_eleveDB;
import Modele.UtilisateurDB;
import MyConnection.DBConnection;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Stat extends ActionBarActivity {

	private Connection con = null;
	private MyAccesDB ma = null;
	private Button retour = null;

	ArrayList<Reponses_eleveDB> trouve = new ArrayList<Reponses_eleveDB>();
	Reponses_eleveDB rep_eleve = new Reponses_eleveDB();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stat);

		retour = (Button) findViewById(R.id.retour);

		//ma = new MyAccesDB(Stat.this);
		ma.execute();

		retour.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(Stat.this, Rentrer_id.class);
				startActivity(i);
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.stat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class MyAccesDB extends AsyncTask<String, Integer, Boolean> {
		private String resultat;
		private ProgressDialog pgd = null;

		public MyAccesDB(Rentrer_id pActivity) {

			link(pActivity);
		}

		private void link(Rentrer_id pActivity) {

		}

		protected void onPreExecute() {
			super.onPreExecute();
			pgd = new ProgressDialog(Stat.this);
			pgd.setMessage("Lalala");
			pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pgd.show();
			// Toast.makeText(Rentrer_id.this,"Veuillez patienter...",Toast.LENGTH_SHORT).show();

		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			if (con == null) {// premier invocation
				Connection con = new DBConnection().getConnection();
				if (con == null) {
					resultat = "echec de la connexion";
					return false;
				}

				QuestionsDB.setConnection(con);
				UtilisateurDB.setConnection(con);
			}

			try {
				/*
				 * id_ex = ed1.getText().toString(); try { trouve=
				 * questions.verifverrouillage(true,id_ex);
				 * System.out.println("Ca marche"); questions = trouve.get(0);
				 * if(!questions.getVerrouillage()) { resultat
				 * ="La question est verrouillé !"; return false; } }
				 * catch(Exception e) { resultat ="Vous n'avez pas entrer d'id";
				 * return false; }
				 * 
				 * }catch (Exception e) { resultat = "Id invalide !"; return
				 * false;
				 */
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

		}

	}
}
