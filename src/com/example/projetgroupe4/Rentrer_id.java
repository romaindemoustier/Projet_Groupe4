package com.example.projetgroupe4;

import java.sql.Connection;
import java.util.ArrayList;

import Modele.QuestionsDB;
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

public class Rentrer_id extends ActionBarActivity {

	private Button valider=null;
	private Button deco=null;
	private Connection con=null;
	private EditText ed1;
	private MyAccesDB ma=null;

	int id_ex;

	ArrayList<QuestionsDB> trouve = new ArrayList<QuestionsDB> ();
	QuestionsDB questions = new QuestionsDB();

	public final String ID_QUESTIONS="id_questions";
	//public final String QUESTIONS="questions";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rentrer_id);

		valider=(Button)findViewById(R.id.valid_id_q);
		deco=(Button)findViewById(R.id.deco_id);
		ed1=(EditText)findViewById(R.id.texte1);

		valider.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) 
			{
				ma=new MyAccesDB(Rentrer_id.this);
				ma.execute();     
			}
		});


		deco.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) 
			{
				Intent i = new Intent(Rentrer_id.this,MainActivity.class);      
				startActivity(i);
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rentrer_id, menu);
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


		public MyAccesDB(Rentrer_id pActivity) {

			link(pActivity);
			// TODO Auto-generated constructor stub
		}

		private void link(Rentrer_id pActivity) {
			// TODO Auto-generated method stub


		}

		protected void onPreExecute(){
			super.onPreExecute();
			pgd=new ProgressDialog(Rentrer_id.this);
			pgd.setMessage("chargement en cours");
			pgd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pgd.show();
			//Toast.makeText(Rentrer_id.this,"Veuillez patienter...",Toast.LENGTH_SHORT).show();

		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			if(con==null){
				Connection con = new DBConnection().getConnection();
				if(con==null) {
					resultat="echec de la connexion";
					return false;
				}

				QuestionsDB.setConnection(con);
				UtilisateurDB.setConnection(con);
			}
	

			try  
			{   
				id_ex =Integer.parseInt(ed1.getText().toString());
			
				try 
				{
					trouve= questions.verifverrouillage1(id_ex);
					
				
					questions = trouve.get(0);
					
					if(!questions.getVerrouillage())
					{
						resultat ="La question est verrouillée !";
						return false;
					}
					
					
				}
				catch(Exception e)
				{
					    Log.d("dssd",e.getMessage());
					    resultat ="La question n'existe pas !";
						return false;
				}


			}finally{
				try{
					con.close();
				}catch (Exception e){}
			}
			return true; 
		}

		protected void onPostExecute(Boolean result)
		{
			super.onPostExecute(result);
			pgd.dismiss();
			if (result)
			{

				Toast.makeText(Rentrer_id.this,"ID Valide !",Toast.LENGTH_SHORT).show();
				Intent i = new Intent(Rentrer_id.this,Repondre_question.class); 
				i.putExtra("ID_QUESTIONS",""+id_ex); 
				//i.putExtra(QUESTIONS,questions.getQuestions() ); 
				startActivity(i);
				finish();
			} 
			else 
			{
				ed1.setText("");
				Toast.makeText(Rentrer_id.this,resultat,Toast.LENGTH_SHORT).show();
			}


		}

	}
}	