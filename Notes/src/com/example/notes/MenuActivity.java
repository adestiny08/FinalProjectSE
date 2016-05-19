package com.example.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		final ListView listview = (ListView) findViewById(R.id.listview);
	    String[] values = new String[] { "Add Audit","Delete Audit","View Audit","Back"};

	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	    }
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	    	  Bundle bundle = getIntent().getExtras();
	    	  int year = bundle.getInt("Year");
	    	  int month = bundle.getInt("Month");
	    	  int day = bundle.getInt("Day");
	    	  final String item = (String) parent.getItemAtPosition(position);
	    	  Toast.makeText(getApplicationContext(), item + " : " + day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
	    	  if(item.equalsIgnoreCase("Back")){
	    		  finish();
	    	  }else if(item.equalsIgnoreCase("Add Audit")){
	    		  Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
					Intent i = new Intent(getApplicationContext(), Add_audit.class);
					i.putExtra("Year", year);
					i.putExtra("Month", month);
					i.putExtra("Day", day);
					startActivity(i);
	    	  }else if(item.equalsIgnoreCase("Delete Audit")){
	    		  	Intent i = new Intent(getApplicationContext(), Delete_audit.class);
					i.putExtra("Year", year);
					i.putExtra("Month", month);
					i.putExtra("Day", day);
					startActivity(i);
	    	  }else if(item.equalsIgnoreCase("View Audit")){
	    		  Intent i = new Intent(getApplicationContext(), View_audit.class);
					i.putExtra("Year", year);
					i.putExtra("Month", month);
					i.putExtra("Day", day);
					startActivity(i);
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

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
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
	
	
}


