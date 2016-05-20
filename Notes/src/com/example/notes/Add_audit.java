/*
WOWW             WOW             WOWW             WOWWOWWOWWOWWOWWOWWOWWOW			WOWW             WOW             WOWW      !!!!!      !!!!!
WOWW            WOWW            WOWW             WOWWOWWOWWOWWOWWOWWOWWOW			WOWW            WOWW            WOWW      !!!!!      !!!!!
WOWW           WOWWO           WOWW             WOWWOW            WOWWOW			WOWW           WOWWO           WOWW      !!!!!      !!!!!
WOWW          WOWWOW          WOWW             WOWWOW            WOWWOW				WOWW          WOWWOW          WOWW      !!!!!      !!!!!
WOWW         WOWWWOW         WOWW             WOWWOW            WOWWOW				WOWW         WOWWWOW         WOWW      !!!!!      !!!!!
WOWW        WOWWOWWO        WOWW             WOWWOW            WOWWOW				WOWW        WOWWOWWO        WOWW      !!!!!      !!!!!
WOWW       WOWW WWOW       WOWW             WOWWOW            WOWWOW				WOWW       WOWW WWOW       WOWW      !!!!!      !!!!!
WOWW      WOWW  WWOW      WOWW             WOWWOW            WOWWOW					WOWW      WOWW  WWOW      WOWW      !!!!!      !!!!!
WOWW     WOWW   WWOW     WOWW             WOWWOW            WOWWOW					WOWW     WOWW   WWOW     WOWW      !!!!!      !!!!!
WOWW    WOWW    WWOW    WOWW             WOWWOW            WOWWOW					WOWW    WOWW    WWOW    WOWW      !!!!!      !!!!!
WOWW   WOWW     WWOW   WOWW		        WOWWOW            WOWWOW					WOWW   WOWW     WWOW   WOWW      !!!!!      !!!!!
WOWW  WOWW      WWOW  WOWW			   WOWWOW            WOWWOW						WOWW  WOWW      WWOW  WOWW      !!!!!      !!!!!
WOWW WOWW       WWOW WOWW             WOWWOW            WOWWOW						WOWW WOWW       WWOW WOWW      !!!!!      !!!!!
WOWWOWWO        WWOWWOWW             WOWWOW            WOWWOW						WOWWOWWO        WWOWWOWW      !!!!!      !!!!!
WOWWOWW         WWOWWOW             WOWWOW            WOWWOW						WOWWOWW         WWOWWOW
WOWWOW          WWOWWO             WOWWOW            WOWWOW							WOWWOW          WWOWWO
WOWWO           OWWOW             WOWWOWWOWWOWWOWWOWWOWWOW							WOWWO           OWWOW      !!!!!      !!!!!
WOWW            OWWO             WOWWOWWOWWOWWOWWOWWOWWOW							WOWW            OWWO      !!!!!      !!!!!

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

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import android.content.Intent;

//  ######  ##   ##  ###   ##   #####  ######  ######   ####   ###   ##
//  ##      ##   ##  ####  ##  ##   ##   ##      ##    ##  ##  ####  ##
//  ####    ##   ##  ## ## ##  ##        ##      ##    ##  ##  ## ## ##
//  ##      ##   ##  ##  ####  ##   ##   ##      ##    ##  ##  ##  ####
//  ##       #####   ##   ###   #####    ##      ##     ####   ##   ###

public class Add_audit extends Activity {
    //ADD audit is a class for ADD INCOME/OUTCOME to the database
    //here is private variable for syc to LAYOUT
    private ToggleButton type;
    private EditText Name;
    private EditText price;
    //here is a string that keep the value of USER-INPUT
    private String strtype;
    private String strName;
    private String strprice;
    //2 buttons
    private Button submit;
    private Button back;
    //Call Database
    private DBHelper mydb;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_audit);
        // Call database as "mydb"
        mydb = new DBHelper(this);
        
        initWidget();   // Set values of syc-variable
        submit = (Button)findViewById(R.id.submit);        // syc to SUBMIT button
        back = (Button)findViewById(R.id.back);            // syc to BACK button
        submit.setOnClickListener(new OnClickListener() {  // use OnClickListener for hear "CLICK!" at this button
        	public void onClick(View v) {                  // if SUBMIT button was clicked, this function will run
        		strtype = type.getText().toString();       // get 3 values and set it to string for future use
                strName = Name.getText().toString();
                strprice = price.getText().toString();
                if(strName.compareTo("")==0 || strprice.compareTo("")==0)  // Check for User not input some value
                	showErrorDialog();                     // show "try again"
                else
                	showCheckDialog();                     // IF required variables are fullfilled,Show Check-box for comfirm 
        	}
        });
        back.setOnClickListener(new OnClickListener() {    // use OnClickListener for hear "CLICK!" at this button
        	public void onClick(View v) {                  // if BACK button was clicked, this function will run
        		finish();                                  // BACK to pevious page
        	}
        });
    }

    private void initWidget() {
        // get the input values
        type = (ToggleButton)findViewById(R.id.type);
        Name = (EditText)findViewById(R.id.Name);
        price = (EditText)findViewById(R.id.price);
    } 
    
    private void resetWidget() {
        // clear syc variables for avoid some bugs
        Name.setText("");
        price.setText("");
    } 

    private void showCheckDialog() { // this dialog will show,when required variables were fullfilled
        AlertDialog.Builder addAuditAlert = new AlertDialog.Builder(this);                   // Create dialog
        addAuditAlert.setTitle("เพิ่มรายการประเภท " + strtype + " เข้าสู่ฐานข้อมูล");                // Set title of Dialog
        addAuditAlert.setMessage("รายการ\t:\t" + strName + "\n" + "ราคา\t:\t" + strprice);    // Show Input Information for user-comfirm
        // When Click "OK" button (OK is a positive), addData(); will run
        addAuditAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        addData(); // Add input values to the Database (SQLite-AndroidSupport)
                    }
                }
        );
        // When Click "Cancel" button (Cancel is a negative), dialog will disappear
        addAuditAlert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        dialog.dismiss(); // dialog dis appear
                    }
                }
        );

        addAuditAlert.show(); // Show dialog (UPPER CODE is for Create not for show but this code is for SHOW this Dialog)
    }
 
    
    private void showErrorDialog() {  // Similar to showCheckDialog() but this dialog is for ALERT user to fullfill all required values
        AlertDialog.Builder addAuditAlert = new AlertDialog.Builder(this);
        addAuditAlert.setTitle("Error : กรุณากรอกข้อมูลให้ครบ");
        addAuditAlert.setMessage("กรอกข้อมูลใหม่อีกครั้ง");

        addAuditAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                    	dialog.dismiss();
                    }
                }
        );

        addAuditAlert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        dialog.dismiss();
                    }
                }
        );

        addAuditAlert.show();
    }
    
    private void addData()
    {
    	Bundle bundle = getIntent().getExtras();   // Setup bundle
   	  	int year = bundle.getInt("Year");
   	  	int month = bundle.getInt("Month");
   	  	int day = bundle.getInt("Day");
   	  	// Insert to database
        if(mydb.insertAudit(strtype,strName,strprice,year,month,day)){
        // Show a mini-black-box at the bottom of screen,If insertion is successful
        	Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
        }	
        else{
        // Show a mini-black-box at the bottom of screen,If insertion is failure
            Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
        }
        resetWidget(); // Reset syc values
        finish();      // Back;
    }
}

