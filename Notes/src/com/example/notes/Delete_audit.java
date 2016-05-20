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
import android.util.Log;
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

public class Delete_audit extends Activity {
	// this class will DELETE a selected AUDIT from database 
	private ArrayList<String> DayAudit; // Prepare for show (show for user select)
	private DBHelper mydb; // Call database
	private int year;      // receive year month day from pevious class that call this class
	private int month;
	private int day;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_audit);
		Bundle bundle = getIntent().getExtras(); // Setup bundle
   	  	year = bundle.getInt("Year");            // Get 3 values
   	  	month = bundle.getInt("Month");
   	  	day = bundle.getInt("Day");
   	  	
		mydb = new DBHelper(this);               // Call database
		
		DayAudit = mydb.getDayAudit(year,month,day);    // Query the AUDIT in selected day from database
		DayAudit.add("Back");                           // ADD BACK button to the last
		final ListView listview = (ListView) findViewById(R.id.listview);    // Set listview
		
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, DayAudit);  // throw Query's result + Back button to StableArrayAdapter
	    listview.setAdapter(adapter);                        // make a listview that contain Query's result + Back button

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              // GIVE listview can hear "CLICK!" 
	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	          // Click at some position => get the ITEM at the position
	    	  final String item = (String) parent.getItemAtPosition(position);
	    	  if(item.equalsIgnoreCase("Back")){ // If Click the Back button GO pevious page
	    		  finish();                  // GO pevious page
	    	  }else{                             // If not
	    	  view.animate().setDuration(2000).alpha(0)  
	            .withEndAction(new Runnable() { // Cool disappear animation
	              @Override
	              public void run() {
	                DayAudit.remove(item); // Remove from list in the screen
	                String [] str = item.split("\t");  // ITEM = "Outcome BUYpapaya 215" --> split it and throw values to query
	                mydb.deleteAudit(str[0], str[1], str[2], year, month, day); // Query then DELETE it
	                
	                adapter.notifyDataSetChanged();   // Notify
	                view.setAlpha(1);
	              }
	            });
	    	  }
	      }

	    });
	}

	 private class StableArrayAdapter extends ArrayAdapter<String> {
                    // Check Click Position at the LAYOUT list
		    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>(); // SET HashMap

		    public StableArrayAdapter(Context context, int textViewResourceId,
		        List<String> objects) {
		      super(context, textViewResourceId, objects);
		      for (int i = 0; i < objects.size(); ++i) {
		        mIdMap.put(objects.get(i), i);  // Run OBJ size
		      }
		    }

		    @Override
		    public long getItemId(int position) { // Get position
		      String item = getItem(position);    // Change position to string
		      return mIdMap.get(item);            // return String
		    }

		    @Override
		    public boolean hasStableIds() {
		      return true;
		    }

	 }
	
}
