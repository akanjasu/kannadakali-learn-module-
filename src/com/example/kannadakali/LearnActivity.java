package com.example.kannadakali;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LearnActivity extends Activity {
private Integer key;
private ListView lv;
private DBahelper myDbHelper ;
private SQLiteDatabase db;
private TextView tv;
private SimpleCursorAdapter dataAdapter;
private Cursor cursor;
private Button nxt;


@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learn);
		key=getIntent().getExtras().getInt("key");
		lv = (ListView) findViewById(R.id.wordlist);
		tv = (TextView) findViewById(R.id.wordtxt);
		nxt = (Button) findViewById(R.id.btnnxt);
		
		myDbHelper = new DBahelper(this);
		 try {

		     	myDbHelper.createDataBase();

			} catch (IOException ioe) {

				Toast.makeText(getApplicationContext(),
					    ioe.toString()+"  ol", Toast.LENGTH_SHORT).show();
			}
		 

				myDbHelper.openDataBase();
				db = myDbHelper.getWritableDatabase();
				db.execSQL("UPDATE ClientContextTable SET consulted = 0 WHERE done = 0");
				db.execSQL("UPDATE POSTable SET consulted = 0 WHERE learnt = 0");
				db.close();
				
				if(key==1)
					poscall(1,"PRONOUNS");

				else if(key==2)
					poscall(2,"ADJECTIVES");
				
				else if(key==3)
					poscall(3,"VERBS");


				else if(key==4)
					poscall(4,"ADVERBS");

				else if(key==5)
					poscall(5,"CONJUCTIONS");

				else if(key==6)
					poscall(6,"PREPOSITIONS");

				else if(key==7)		
					contextcall();
					
			
				nxt.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(key==1)
							poscall(1,"PRONOUNS");
						
						else if(key==2)
							poscall(2,"ADJECTIVES");

						else if(key==3)
							poscall(3,"VERBS");

						else if(key==4)
							poscall(4,"ADVERBS");

						else if(key==5)
							poscall(5,"CONJUCTIONS");

						else if(key==6)
							poscall(6,"PREPOSITIONS");

						else if(key==7)
							contextcall();
					}
				});
	}

	public void poscall(int key,String pos)
	{
		db = myDbHelper.getWritableDatabase();
	cursor= db.rawQuery("SELECT engword _id from POSTable where consulted = 0 and pos = \""+key+"\"ORDER BY RANDOM()", null);
	String id;
	 if(cursor.moveToFirst())
	  {
		 id=cursor.getString(cursor.getColumnIndex("_id")).toString();
		 db.execSQL("UPDATE POSTable SET consulted = 1 WHERE engword = \""+id+"\"");
		  tv.setText(id);
		  Cursor cursor3 = db.rawQuery("SELECT kanword _id,engcontext from POSTable where engword = \""+id+"\" and pos = \""+key+"\"",null);
		  String[] columns2 = new String[] {
				   "_id",
				   "engcontext"
				  };
		
		 int[] to2 = new int[] { 
				    R.id.kanword,
				    R.id.context
				   
				    };
				 
				  // create the adapter using the cursor pointing to the desired data 
				  //as well as the layout information
				  dataAdapter = new SimpleCursorAdapter(
				    this, R.layout.wordcontextdisplaylayout, 
				    cursor3, 
				    columns2, 
				    to2,
				    0);
		lv.setAdapter(dataAdapter);
		db.close();
		 
	  }
	 else
	 {
		 tv.setText("All "+pos+" learnt");
		 lv.setAdapter(null);
	 }
	}

	public void contextcall()
	{
		db = myDbHelper.getWritableDatabase();
		cursor= db.rawQuery("SELECT engword _id from ClientContextTable where consulted = 0 ORDER BY RANDOM()", null);
		String id;
		 if(cursor.moveToFirst())
		  {
			 id=cursor.getString(cursor.getColumnIndex("_id")).toString();
			 db.execSQL("UPDATE ClientContextTable SET consulted = 1 WHERE engword = \""+id+"\"");
			  tv.setText(id);
			  Cursor cursor2 = db.rawQuery("SELECT kanword _id,engcontext from ClientContextTable where engword = \""+id+"\"",null);
			  String[] columns = new String[] {
					   "_id",
					   "engcontext"
					  };
			
			 int[] to = new int[] { 
					    R.id.kanword,
					    R.id.context
					   
					    };
					 
					  // create the adapter using the cursor pointing to the desired data 
					  //as well as the layout information
					  dataAdapter = new SimpleCursorAdapter(
					    this, R.layout.wordcontextdisplaylayout, 
					    cursor2, 
					    columns, 
					    to,
					    0);
			lv.setAdapter(dataAdapter);
			
			 db.close();
		  }
		 else
			 tv.setText("All words learnt");
		 lv.setAdapter(null);
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.learn, menu);
		return true;
	}

}
