package com.example.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Delete_audit extends Activity {
	private ArrayList<String> DayAudit;
	private DBHelper mydb;
	private int year;
	private int month;
	private int day;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_audit);
		Bundle bundle = getIntent().getExtras();
   	  	year = bundle.getInt("Year");
   	  	month = bundle.getInt("Month");
   	  	day = bundle.getInt("Day");
		mydb = new DBHelper(this);
		DayAudit = mydb.getDayAudit(year,month,day);
		DayAudit.add("Back");
		final ListView listview = (ListView) findViewById(R.id.listview);
		
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, DayAudit);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	  
	    	  final String item = (String) parent.getItemAtPosition(position);
	    	  if(item.equalsIgnoreCase("Back")){
	    		  finish();
	    	  }else{
	    	  view.animate().setDuration(2000).alpha(0)
	            .withEndAction(new Runnable() {
	              @Override
	              public void run() {
	                DayAudit.remove(item);
	                String [] str = item.split("\t");
	                mydb.deleteAudit(str[0], str[1], str[2], year, month, day);
	                
	                adapter.notifyDataSetChanged();
	                view.setAlpha(1);
	              }
	            });
	    	  }
	      }

	    });
	}

	 private class StableArrayAdapter extends ArrayAdapter<String> {

		    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		    public StableArrayAdapter(Context context, int textViewResourceId,
		        List<String> objects) {
		      super(context, textViewResourceId, objects);
		      for (int i = 0; i < objects.size(); ++i) {
		        mIdMap.put(objects.get(i), i);
		      }
		    }

		    @Override
		    public long getItemId(int position) {
		      String item = getItem(position);
		      return mIdMap.get(item);
		    }

		    @Override
		    public boolean hasStableIds() {
		      return true;
		    }

	 }
	
}
