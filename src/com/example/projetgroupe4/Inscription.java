package com.example.projetgroupe4;

import java.sql.Connection;

import Modele.UtilisateurDB;
import MyConnection.DBConnection;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends ActionBarActivity {
	
	private Button Insc=null;
	private Button annul=null;

	
	private EditText login;
	private EditText mdp;
	private EditText verif;
	
	private MyAccesDB ma=null;
	
	private String resultat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inscription);
		
		verif=(EditText)findViewById(R.id.editText5);
		mdp=(EditText)findViewById(R.id.editText2);
		login=(EditText)findViewById(R.id.editText1);
		Insc=(Button)findViewById(R.id.button1);
		annul=(Button)findViewById(R.id.button2);
		
		Insc.setOnClickListener(
				 new View.OnClickListener() {
												
						public void onClick(View v) 
						{
							
							boolean bon = verifSaisie();
							if(bon)
							{
							ma=new MyAccesDB(Inscription.this);
							ma.execute();
							}
							else
							{
								Toast.makeText(Inscription.this,resultat,Toast.LENGTH_SHORT).show();
							}
						}
					}	 
	       );
		
		
	    annul.setOnClickListener(
				new OnClickListener()
				{
					public void onClick(View v)
					{
						Intent i = new Intent(Inscription.this,MainActivity.class);						
						startActivity(i);
						finish();
					}
				 }
				);
	   
	}
	    
	    
	    private boolean verifSaisie()
		{

	    	String log = login.getText().toString();	
	    	String pass = mdp.getText().toString();	
	    	String verification = verif.getText().toString();
	    		
	    	  
	    	resultat=null;
	    	
	    	if(log.equals("") || pass.equals("") )
	    	{
	    		resultat="Veuillez remplir tous les champs !";
	    		return false;
	    	}
	    	
	    	if (!verification.equals(pass))
	    	{
	    		resultat="Mot de passe incorrect !";
	    		return false;
	    	}
		   
	      return true;
		}
	
	    class MyAccesDB extends AsyncTask<String,Integer,Boolean> {
		
		    
				 
			public MyAccesDB(Inscription pActivity) 
			{
				link(pActivity);
				// TODO Auto-generated constructor stub
			}

			private void link(Inscription pActivity) 
			{
				// TODO Auto-generated method stub

			}
			
			protected void onPreExecute()
			{
				 super.onPreExecute();
				 Toast.makeText(Inscription.this,"Veuillez patienter...",Toast.LENGTH_SHORT).show();
				 
							
			}

			@Override
			protected Boolean doInBackground(String... arg0) {
				Connection con = new DBConnection().getConnection();
				if(con==null) 
				{
				    resultat="Echec de la connexion !";
			      	return false;
				}
			
				UtilisateurDB.setConnection(con);
			    
			    try  
			    {    
			    	String log = login.getText().toString();	
			    	String mot = mdp.getText().toString();	
			    	
	                UtilisateurDB part = new UtilisateurDB(log,mot,false);
	                part.create();
			
			    	
	            } 
			    catch  (Exception e)
			    {
					resultat ="Vous êtes déjà inscrit !";
				    return false;
			    }
				finally
				{
					try
					{
					 con.close();
				    }
				    catch (Exception e){}
				   
				}
				return true;
			}
			
			
			protected void onPostExecute(Boolean result)
			{
				 super.onPostExecute(result);
				 if (result)
				 {
					 Toast.makeText(Inscription.this,"Compte ajouté",Toast.LENGTH_SHORT).show();
					 Intent i = new Intent(Inscription.this,MainActivity.class);						
					 startActivity(i);
					 finish();
					 
			     } 
			     else 
			     {
			    	 Toast.makeText(Inscription.this,resultat,Toast.LENGTH_SHORT).show();
			     }
				
				 
			}

		}
			

	}