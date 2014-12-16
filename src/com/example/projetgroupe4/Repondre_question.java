package com.example.projetgroupe4;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import Modele.QuestionsDB;
import Modele.ReponsesDB;
import Modele.Reponses_eleve;
import Modele.Reponses_eleveDB;
import MyConnection.DBConnection;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Repondre_question extends ActionBarActivity {


	private Connection con=null;
	private ArrayList<ReponsesDB> rep_liste;
	private ListView list=null;



	ReponsesDB reponses= new ReponsesDB();
	QuestionsDB question = new QuestionsDB();
	Reponses_eleve rep_e = new Reponses_eleve();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repondre_question);


		MyAccesDB ma=new MyAccesDB(Repondre_question.this);
		ma.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.repondre_question, menu);
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



	class MyAccesDB extends AsyncTask<String,Integer,Boolean> {
		private String resultat;
		private ProgressDialog pgd=null;


		public MyAccesDB(Repondre_question pActivity) {

			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(Repondre_question pActivity) {
			// TODO Auto-generated method stub


		}

		protected void onPreExecute(){
			super.onPreExecute();
			pgd=new ProgressDialog(Repondre_question.this);
			pgd.setMessage("chargement en cours");
			pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pgd.show();

		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			if(con==null){
				con = new DBConnection().getConnection();
				if(con==null) {
					resultat="echec de la connexion";
					return false;
				}

				QuestionsDB.setConnection(con);
				ReponsesDB.setConnection(con);
				Reponses_eleveDB.setConnection(con);
			}

			try{

				rep_liste= ReponsesDB.getreponse(1);
				for(int i=0; i<rep_liste.size();i++)
				{
					Log.d("ELEMENT","Elt"+i+"est: "+rep_liste.get(i));
				}

			}
			catch(Exception e)
			{
				resultat="erreur" +e.getMessage();
				return false;
			}

             return true;

		}

		protected void onPostExecute(Boolean result)
		{
			super.onPostExecute(result);
			if (result)
			{
				ArrayList<String> liste_simple=new ArrayList<String>();
				list = (ListView) findViewById(R.id.listView1);
				Iterator it=rep_liste.iterator();
				int i=0;
				while(it.hasNext()){
					it.next();
					if(rep_liste.get(i).getId_questions()==1){
						liste_simple.add(rep_liste.get(i).getReponses());
						i++;}
					else{
						it.remove();
						i++;
					}


					ArrayAdapter<String> adapter = new ArrayAdapter<String>(Repondre_question.this,android.R.layout.simple_list_item_1, liste_simple);
					list.setAdapter(adapter);


				

			}
		}
		}
	}
}

	
