/*
WOWW             WOW             WOWW             WOWWOWWOWWOWWOWWOWWOWWOW      WOWW             WOW             WOWW      !!!!!      !!!!!
WOWW            WOWW            WOWW             WOWWOWWOWWOWWOWWOWWOWWOW       WOWW            WOWW            WOWW      !!!!!      !!!!!
WOWW           WOWWO           WOWW             WOWWOW            WOWWOW        WOWW           WOWWO           WOWW      !!!!!      !!!!!
WOWW          WOWWOW          WOWW             WOWWOW            WOWWOW         WOWW          WOWWOW          WOWW      !!!!!      !!!!!
WOWW         WOWWWOW         WOWW             WOWWOW            WOWWOW          WOWW         WOWWWOW         WOWW      !!!!!      !!!!!
WOWW        WOWWOWWO        WOWW             WOWWOW            WOWWOW           WOWW        WOWWOWWO        WOWW      !!!!!      !!!!!
WOWW       WOWW WWOW       WOWW             WOWWOW            WOWWOW            WOWW       WOWW WWOW       WOWW      !!!!!      !!!!!
WOWW      WOWW  WWOW      WOWW             WOWWOW            WOWWOW             WOWW      WOWW  WWOW      WOWW      !!!!!      !!!!!
WOWW     WOWW   WWOW     WOWW             WOWWOW            WOWWOW              WOWW     WOWW   WWOW     WOWW      !!!!!      !!!!!
WOWW    WOWW    WWOW    WOWW             WOWWOW            WOWWOW               WOWW    WOWW    WWOW    WOWW      !!!!!      !!!!!
WOWW   WOWW     WWOW   WOWW             WOWWOW            WOWWOW                WOWW   WOWW     WWOW   WOWW      !!!!!      !!!!!
WOWW  WOWW      WWOW  WOWW             WOWWOW            WOWWOW                 WOWW  WOWW      WWOW  WOWW      !!!!!      !!!!!
WOWW WOWW       WWOW WOWW             WOWWOW            WOWWOW                  WOWW WOWW       WWOW WOWW      !!!!!      !!!!!
WOWWOWWO        WWOWWOWW             WOWWOW            WOWWOW                   WOWWOWWO        WWOWWOWW      !!!!!      !!!!!
WOWWOWW         WWOWWOW             WOWWOW            WOWWOW                    WOWWOWW         WWOWWOW
WOWWOW          WWOWWO             WOWWOW            WOWWOW                     WOWWOW          WWOWWO
WOWWO           OWWOW             WOWWOWWOWWOWWOWWOWWOWWOW                      WOWWO           OWWOW      !!!!!      !!!!!
WOWW            OWWO             WOWWOWWOWWOWWOWWOWWOWWOW                       WOWW            OWWO      !!!!!      !!!!!
*****************************************************
* WOW WOW WOW group : NOTES & REMINDERS             *
*                     member                        *
* Miss  Nattida     Boonpae            56070501097  *
* MR.   Thanawat    Wongpuak           56070501098  *
* Miss  Panussaya   Sathitchaiwattana  56070501099  *
* MR.   Pacharapon  Leewanitchakul     56070501100  *
*****************************************************
*/
package com.example.notes;
// ######  ###     ###  #####    ####   #####  ######
//   ##    ####   ####  ##  ##  ##  ##  ##  ##   ##
//   ##    ## ## ## ##  #####   ##  ##  #####    ##
//   ##    ##  ###  ##  ##      ##  ##  ##  ##   ##
// ######  ##   #   ##  ##       ####   ##   ##  ##
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

//  ######  ##   ##  ###   ##   #####  ######  ######   ####   ###   ##
//  ##      ##   ##  ####  ##  ##   ##   ##      ##    ##  ##  ####  ##
//  ####    ##   ##  ## ## ##  ##        ##      ##    ##  ##  ## ## ##
//  ##      ##   ##  ##  ####  ##   ##   ##      ##    ##  ##  ##  ####
//  ##       #####   ##   ###   #####    ##      ##     ####   ##   ###

public class MenuActivity extends Activity {
	// this class is for Menu at top-right screen

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		final ListView listview = (ListView) findViewById(R.id.listview); // Setup id list view
	    String[] values = new String[] { "Add Audit","Delete Audit","View Audit","Back"};

	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]); // add 4 String in String[] to list
	    }
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	// can hear "CLICK!"

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {                  // Keep Click position
	    	  Bundle bundle = getIntent().getExtras();  // receive 3 values from pevious page
	    	  int year = bundle.getInt("Year");         // "--------------------------------"
	    	  int month = bundle.getInt("Month");
	    	  int day = bundle.getInt("Day");
	    	  final String item = (String) parent.getItemAtPosition(position);   // Keep position
	    	  Toast.makeText(getApplicationContext(), item + " : " + day + "/" + month + "/" + year, Toast.LENGTH_LONG).show(); //Black-box popup
	    	  if(item.equalsIgnoreCase("Back")){
	    		  finish(); // CLICK Back button then go pevious page
	    	  }else if(item.equalsIgnoreCase("Add Audit")){
	    		  Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show(); //Black-box popup
					Intent i = new Intent(getApplicationContext(), Add_audit.class); // Prepare go to Add_audit class & page
					i.putExtra("Year", year); // passing 3 values
					i.putExtra("Month", month);
					i.putExtra("Day", day);
					startActivity(i); // GO!
	    	  }else if(item.equalsIgnoreCase("Delete Audit")){
	    		  	Intent i = new Intent(getApplicationContext(), Delete_audit.class); // Prepare go to Delete_audit class & page
					i.putExtra("Year", year); // passing 3 values
					i.putExtra("Month", month);
					i.putExtra("Day", day);
					startActivity(i); // GO!
	    	  }else if(item.equalsIgnoreCase("View Audit")){
	    		  Intent i = new Intent(getApplicationContext(), View_audit.class); // Prepare go to View_audit class & page
					i.putExtra("Year", year); // passing 3 values
					i.putExtra("Month", month);
					i.putExtra("Day", day);
					startActivity(i); // GO!
	    	  }
	      }

	    });
	}
	// explain in some pevious class
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


