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
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;

//  ######  ##   ##  ###   ##   #####  ######  ######   ####   ###   ##
//  ##      ##   ##  ####  ##  ##   ##   ##      ##    ##  ##  ####  ##
//  ####    ##   ##  ## ## ##  ##        ##      ##    ##  ##  ## ## ##
//  ##      ##   ##  ##  ####  ##   ##   ##      ##    ##  ##  ##  ####
//  ##       #####   ##   ###   #####    ##      ##     ####   ##   ###

public class DBHelper extends SQLiteOpenHelper {
    // This class is a class for help us connecting to the database
    // Now we setup the NAME of everythings
    public static final String DATABASE_NAME = "sqlNotereminder.db";
    public static final String AUDITS_TABLE_NAME = "audit_table";
    public static final String AUDITS_COLUMN_TYPE = "type";
    public static final String AUDITS_COLUMN_NAME = "Name";
    public static final String AUDITS_COLUMN_PRICE = "price";

    // Setup
    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Call AndroidSupport for help to create table ******* We not use NAME,that we setup at the top of this code,because it cause too much confuse
        db.execSQL("CREATE TABLE audit_table('type' TEXT,'Name' TEXT,'price' TEXT,'year' Integer, 'month' Integer, 'day' Integer);");
        //Log.d("CREATE DATABASE","สำเร็จ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AUDITS_TABLE_NAME + ";");
        // Drop on UPGRADE and create again (for re-structure of database)
        onCreate(db);
    }

    public boolean insertAudit(String type, String Name, String price, int year, int month, int day){
        SQLiteDatabase db = this.getWritableDatabase();       // Get Writable
        ContentValues contentValues = new ContentValues();    // Setup
        contentValues.put("type",type);                       // Keep values
        contentValues.put("Name",Name);
        contentValues.put("price",price);
        contentValues.put("year",year);
        contentValues.put("month",month);
        contentValues.put("day",day);
        db.insert(AUDITS_TABLE_NAME,null,contentValues);      // Let insert values
        //Log.d("INSERT DATABASE","สำเร็จ");
        return true;
    }
    // Get Cursor from Name of data (We don't use it now)
    public Cursor getData(String Name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from" + AUDITS_TABLE_NAME + "where Name="+Name+";",null);
        return res;
    }
    // Get the number of rows (We don't use it now)
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, AUDITS_TABLE_NAME);
        return numRows;
    }
    // Replace updated Audit by Name searching (We don't use it now)
    public boolean updateAudit (String type, String Name, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type",type);
        contentValues.put("Name",Name);
        contentValues.put("price",price);
        db.update(AUDITS_TABLE_NAME, contentValues, "Name = ? ", new String[] {Name} );
        return true;
    }
    // Get spesific values of Audit and DELETE that line
    public Integer deleteAudit (String type,String Name,String price,int year,int month,int day)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(AUDITS_TABLE_NAME,"type = ? AND Name = ? AND price = ? AND year = ? AND month = ? AND day =? ",new String[] {type,Name,price,Integer.toString(year),Integer.toString(month),Integer.toString(day)});
    }
    // View Audit in a specific day
    public ArrayList<String> getDayAudit(int year, int month, int day)
    {
        ArrayList<String> array_list = new ArrayList<String>(); // Setup ArrayList

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase(); // Get Readable
        // Query
        Cursor res =  db.rawQuery( "select * from " + AUDITS_TABLE_NAME + " where year="+year+" AND month="+month+" AND day="+day, null );
        res.moveToFirst(); //Let's start

        while(res.isAfterLast() == false){ // .hasNext();
            // Add one row to one list
            array_list.add(res.getString(res.getColumnIndex(AUDITS_COLUMN_TYPE)) + "\t" +res.getString(res.getColumnIndex(AUDITS_COLUMN_NAME)) + "\t" + res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)));
            res.moveToNext(); //.Next();
        }
        return array_list; //return query result
    }
    // Calculate SUM of Audit in the specific day
    public Double getResultDayAudit(int year, int month, int day)
    {
    	Double Positive = calPositive(year,month,day).doubleValue(); // Call Income Calculation function
    	Double Negative = calNegative(year,month,day).doubleValue(); // Call Outcome Calculation function
        return Positive-Negative; //return result
    }
    // Income Calculation function
    private Integer calPositive(int year, int month, int day)
    {
        Integer result = 0;    //setup values
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Income' AND year="+year+" AND month="+month+" AND day="+day, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString(); // Get values in String
            result = result + Integer.parseInt(buffer); // parse to int,then Add to buffer
            res.moveToNext(); //.Next();
        }
        
        return result;
    }
    // Outcome Calculation function (Same as Income Calculation)
    private Integer calNegative(int year, int month, int day)
    {
        Integer result = 0;
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Outcome' AND year="+year+" AND month="+month+" AND day="+day, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString();
            result = result + Integer.parseInt(buffer);
            res.moveToNext();
        }
        return result;
    }
    // Calculate SUM of Audit in the specific month (COPY-PASTE but EDIT to query with day+month)
    public Double getResultMonthAudit(int year, int month)
    {
    	Double Positive = calPositiveM(year,month).doubleValue();
    	Double Negative = calNegativeM(year,month).doubleValue();
        return Positive-Negative;
    }
    
    private Integer calPositiveM(int year, int month)
    {
        Integer result = 0;
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Income' AND year="+year+" AND month="+month, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString();
            result = result + Integer.parseInt(buffer);
            res.moveToNext();
        }
        
        return result;
    }
    
    private Integer calNegativeM(int year, int month)
    {
        Integer result = 0;
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Outcome' AND year="+year+" AND month="+month, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString();
            result = result + Integer.parseInt(buffer);
            res.moveToNext();
        }
        return result;
    }
    // Calculate SUM of Audit in the specific year (COPY-PASTE but EDIT to query with day+month+year)
    public Double getResultYearAudit(int year)
    {
    	Double Positive = calPositiveY(year).doubleValue();
    	Double Negative = calNegativeY(year).doubleValue();
        return Positive-Negative;
    }
    
    private Integer calPositiveY(int year)
    {
        Integer result = 0;
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Income' AND year="+year, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString();
            result = result + Integer.parseInt(buffer);
            res.moveToNext();
        }
        
        return result;
    }
    
    private Integer calNegativeY(int year)
    {
        Integer result = 0;
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Outcome' AND year="+year, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString();
            result = result + Integer.parseInt(buffer);
            res.moveToNext();
        }
        return result;
    }
    // Calculate SUM of Audit from forever (COPY-PASTE but EDIT to query only by TYPE for calculate)
    public Double getResultAudit()
    {
    	Double Positive = calPositiveAll().doubleValue();
    	Double Negative = calNegativeAll().doubleValue();
        return Positive-Negative;
    }
    
    private Integer calPositiveAll()
    {
        Integer result = 0;
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Income'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString();
            result = result + Integer.parseInt(buffer);
            res.moveToNext();
        }
        
        return result;
    }
    
    private Integer calNegativeAll()
    {
        Integer result = 0;
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Outcome'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString();
            result = result + Integer.parseInt(buffer);
            res.moveToNext();
        }
        return result;
    }
}

