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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//  ######  ##   ##  ###   ##   #####  ######  ######   ####   ###   ##
//  ##      ##   ##  ####  ##  ##   ##   ##      ##    ##  ##  ####  ##
//  ####    ##   ##  ## ## ##  ##        ##      ##    ##  ##  ## ## ##
//  ##      ##   ##  ##  ####  ##   ##   ##      ##    ##  ##  ##  ####
//  ##       #####   ##   ###   #####    ##      ##     ####   ##   ###

public class View_audit extends Activity {
	// This class is use for show ALL audit in one day and Calculate Total Audit's price
	private ArrayList<String> DayAudit;
	private DBHelper mydb;
	private int year;
	private int month;
	private int day;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_audit); // Setup page
		Bundle bundle = getIntent().getExtras();      // receive 3 values
   	  	year = bundle.getInt("Year");
   	  	month = bundle.getInt("Month");
   	  	day = bundle.getInt("Day");
		mydb = new DBHelper(this);                    // Set Database
		DayAudit = mydb.getDayAudit(year,month,day);  // Get All Audit
		DayAudit.add("Total");                        // add Total Button
		DayAudit.add("Back");                         // add Back button
		
		
		final ListView listview = (ListView) findViewById(R.id.listview);
		// use list Day Audit to Create List of LAYOUT
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, DayAudit);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // Can hear "CLICK!"

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) { 
	          // CLICK keep position
	    	  final String item = (String) parent.getItemAtPosition(position);
	    	  if(item.equalsIgnoreCase("Back")){
	    		  finish(); // CLICK Back then go back
	    	  }else if(item.equalsIgnoreCase("Total")){
	    		  showCheckDialog();  // CLICK Total to calculate total price of the day
	    	  }else{ // CLICK other look position
	    	  view.animate().setDuration(2000).alpha(0)
	            .withEndAction(new Runnable() {
	              @Override
	              public void run() {
	                DayAudit.remove(item); // remove from screen
	                String [] str = item.split("\t");
	                mydb.deleteAudit(str[0], str[1], str[2], year, month, day); // DELETE from database
	                
	                adapter.notifyDataSetChanged(); // Notify
	                view.setAlpha(1);
	              }
	            });
	    	  }
	      }

	    });
	}

	private void showCheckDialog() { // Show total price CLASS
        AlertDialog.Builder showAuditAlert = new AlertDialog.Builder(this); // Create Alert dialog
        showAuditAlert.setTitle("Total prize"); // Set title
        showAuditAlert.setMessage("= "+mydb.getResultDayAudit(year,month,day).intValue()+""); // Set message

        showAuditAlert.setPositiveButton("Done",
                new DialogInterface.OnClickListener()
                { // Can hear "CLICK"
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                    	dialog.dismiss(); // CLICK then disappear
                    }
                }
        );
        showAuditAlert.show(); // Show dialog that created upper this line
    }
	// Explained in some pevious class
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
