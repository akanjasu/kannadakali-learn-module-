package com.example.kannadakali;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TutorOptions extends Activity {

	private Button btnadv;
	private Button btnpro;
	private Button btnadj;
	private Button btnver;
	private Button btncon;
	private Button btnpre;
	private Button btncmn;
	private Button btntest;
	private Bundle bundle;
	private SimpleCursorAdapter dataAdapter;
	private SQLiteDatabase db;
	private DBahelper myDbHelper ;
	
	private String key;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutor_options);
		key="key";
		btnadv = (Button) findViewById(R.id.btnadv);//4
		btnpro = (Button) findViewById(R.id.btnpro);//1
		btnadj = (Button) findViewById(R.id.btnadj);//2
		btnver = (Button) findViewById(R.id.btnverb);//3
		btncon = (Button) findViewById(R.id.btnconj);//5
		btnpre = (Button) findViewById(R.id.btnprep);//6
		btncmn = (Button) findViewById(R.id.btncmn);//7
		btntest = (Button) findViewById(R.id.btntest);
		bundle = new Bundle();
		
		myDbHelper = new DBahelper(this);
		 try {

		     	myDbHelper.createDataBase();

			} catch (IOException ioe) {

				Toast.makeText(getApplicationContext(),
					    ioe.toString()+"  ol", Toast.LENGTH_SHORT).show();
			}
		 

				myDbHelper.openDataBase();
				db = myDbHelper.getWritableDatabase();
			
				
		btnpro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				// TODO Auto-generated method stub
				Intent i= new Intent(TutorOptions.this,LearnActivity.class);
				bundle.putInt(key,1);
				i.putExtras(bundle);
				startActivity(i);
				
			}
		});
		
		btnadj.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(TutorOptions.this,LearnActivity.class);
				bundle.putInt(key,2);
				i.putExtras(bundle);
				startActivity(i);
			}
		});
		
		btnver.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(TutorOptions.this,LearnActivity.class);
				bundle.putInt(key,3);
				i.putExtras(bundle);
				startActivity(i);
			}
		});

		btnadv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 	TODO Auto-generated method stub
				Intent i= new Intent(TutorOptions.this,LearnActivity.class);
				bundle.putInt(key,4);
				i.putExtras(bundle);
				startActivity(i);
			}
		});
		
		btncon.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(TutorOptions.this,LearnActivity.class);
				bundle.putInt(key,5);
				i.putExtras(bundle);
				startActivity(i);
			}
		});

		btnpre.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(TutorOptions.this,LearnActivity.class);
				bundle.putInt(key,6);
				i.putExtras(bundle);
				startActivity(i);
			}
		});

		btncmn.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(TutorOptions.this,LearnActivity.class);
				bundle.putInt(key,7);
				i.putExtras(bundle);
				startActivity(i);
			}
		});

		btntest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cursor c= db.rawQuery("SELECT consulted _id FROM POSTable where _id=1 and learnt = 0",null);
				if(c.moveToFirst())
				{
					Intent i= new Intent(TutorOptions.this,KanWordsTestActivity.class);
					
					startActivity(i);
					
				}
				else
				{
					Toast.makeText(getApplicationContext(),
						    "PLEASE LEARN THE NEW WORDS", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tutor_options, menu);
		return true;
	}

}
