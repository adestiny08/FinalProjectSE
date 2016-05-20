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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.EditText;
import android.widget.Toast;

//  ######  ##   ##  ###   ##   #####  ######  ######   ####   ###   ##
//  ##      ##   ##  ####  ##  ##   ##   ##      ##    ##  ##  ####  ##
//  ####    ##   ##  ## ## ##  ##        ##      ##    ##  ##  ## ## ##
//  ##      ##   ##  ##  ####  ##   ##   ##      ##    ##  ##  ##  ####
//  ##       #####   ##   ###   #####    ##      ##     ####   ##   ###

// Some function I will not description,because i was comment it at pevious class
public class MainActivity extends Activity {
	CalendarView calendar;
	private DBHelper mydb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//initializes the calendarview
		mydb = new DBHelper(this);
		initializeCalendar();    // Setup Calendar
	}
	
	public void initializeCalendar() {
		calendar = (CalendarView) findViewById(R.id.calendar);  // Look id 'Calendar'

		// sets whether to show the week number.
		calendar.setShowWeekNumber(false);

		// sets the first day of week according to Calendar.
		// here we set Monday as the first day of the Calendar
		calendar.setFirstDayOfWeek(2);

		//The background color for the selected week.
		calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
		
		//sets the color for the dates of an unfocused month. 
		calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
	
		//sets the color for the separator line between weeks.
		calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
		
		//sets the color for the vertical bar shown at the beginning and at the end of the selected date.
		calendar.setSelectedDateVerticalBar(R.color.darkgreen);
		
		//sets the listener to be notified upon selected date change.
		calendar.setOnDateChangeListener(new OnDateChangeListener() {
                       //show the selected date as a toast
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
				Toast.makeText(getApplicationContext(), day + "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
				Intent i = new Intent(getApplicationContext(), MenuActivity.class);
				i.putExtra("Year", year);
				i.putExtra("Month", month+1); // month start at 0 but we want it to start at 1
				i.putExtra("Day", day);
				startActivity(i);
			}
		});
	}
	// *top-right dot on the screen is SETTING menu* not comment about code * just explain
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu); 
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.total_month:     // Menu=> total Month
	        	showMonthDialog();
	            return true;
	        case R.id.total_year:      // Menu=> total year
	        	showYearDialog();
	            return true;
	        case R.id.total_all:       // Menu=> total all
	        	showAllDialog();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	// showMonthDialog();  ,  showYearDialog();  ,  showAllDialog();  => 3 class is similar to eachother
	private void showMonthDialog() {
		
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog, null);
		
        AlertDialog.Builder showmonth = new AlertDialog.Builder(this);
        final EditText inputmonth = (EditText) promptsView.findViewById(R.id.inputmonth); // input month in dialog
        final EditText inputyear = (EditText) promptsView.findViewById(R.id.inputyear);   // input year in dialog
        showmonth.setTitle("ระบุเดือนและปีที่ต้องการแสดง(ตัวเลข)");
        showmonth.setView(promptsView);

        showmonth.setPositiveButton("OK",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                    	int month = Integer.parseInt(inputmonth.getText().toString());
                    	int year = Integer.parseInt(inputyear.getText().toString());
                    	int value = mydb.getResultMonthAudit(year, month).intValue();
                    	//Log.d("Month", "Result "+Integer.toString(value));
                    	dialog.dismiss(); // dialog disappear
                    	showAudit(value); // Popup to show values
                    }       
                }
        );

        showmonth.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        dialog.dismiss();
                    }
                }
        );

        showmonth.show();
    }
	
	private void showYearDialog() {
        AlertDialog.Builder showyear = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        showyear.setTitle("ระบุปีที่ต้องการแสดง(ตัวเลข)");
        showyear.setView(input);
        

        showyear.setPositiveButton("OK",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                    	int year = Integer.parseInt(input.getText().toString());
                    	int value = mydb.getResultYearAudit(year).intValue();
                    	
                    	Log.d("Year", "Result "+Integer.toString(value));
                    	dialog.dismiss();
                    	showAudit(value);
                    	
                    }
                }
        );

        showyear.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        dialog.dismiss();
                    }
                }
        );

        showyear.show();
    }
	
	private void showAllDialog() {
        AlertDialog.Builder showall = new AlertDialog.Builder(this);
        int value = mydb.getResultAudit().intValue();
    	Log.d("All", "Result "+Integer.toString(value));
        showall.setTitle("All Audit");
        showall.setMessage("Prize : " + value);
        
        showall.setPositiveButton("OK",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                    	dialog.dismiss();             	
                    }
                }
        );
        showall.show();

    }
	
	private void showAudit(int value) {
        AlertDialog.Builder showall = new AlertDialog.Builder(this);
        showall.setTitle("Total Audit");
        showall.setMessage("Prize : " + value);

        showall.setPositiveButton("OK",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                    	dialog.dismiss();
                    }
                }
        );
        
        showall.show();
    }
}
