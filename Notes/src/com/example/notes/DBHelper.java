package com.example.notes;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sqlNotereminder.db";
    public static final String AUDITS_TABLE_NAME = "audit_table";
    public static final String AUDITS_COLUMN_TYPE = "type";
    public static final String AUDITS_COLUMN_NAME = "Name";
    public static final String AUDITS_COLUMN_PRICE = "price";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE audit_table('type' TEXT,'Name' TEXT,'price' TEXT,'year' Integer, 'month' Integer, 'day' Integer);");
        Log.d("CREATE DATABASE","สำเร็จ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AUDITS_TABLE_NAME + ";");
        onCreate(db);
    }

    public boolean insertAudit(String type, String Name, String price, int year, int month, int day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type",type);
        contentValues.put("Name",Name);
        contentValues.put("price",price);
        contentValues.put("year",year);
        contentValues.put("month",month);
        contentValues.put("day",day);
        db.insert(AUDITS_TABLE_NAME,null,contentValues);
        Log.d("INSERT DATABASE","สำเร็จ");
        return true;
    }

    public Cursor getData(String Name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from" + AUDITS_TABLE_NAME + "where Name="+Name+";",null);
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, AUDITS_TABLE_NAME);
        return numRows;
    }

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

    public Integer deleteAudit (String type,String Name,String price,int year,int month,int day)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(AUDITS_TABLE_NAME,"type = ? AND Name = ? AND price = ? AND year = ? AND month = ? AND day =? ",new String[] {type,Name,price,Integer.toString(year),Integer.toString(month),Integer.toString(day)});
    }

    public ArrayList<String> getAllAuditPrice()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + AUDITS_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(AUDITS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    
    public ArrayList<String> getDayAudit(int year, int month, int day)
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + AUDITS_TABLE_NAME + " where year="+year+" AND month="+month+" AND day="+day, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(AUDITS_COLUMN_TYPE)) + "\t" +res.getString(res.getColumnIndex(AUDITS_COLUMN_NAME)) + "\t" + res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)));
            res.moveToNext();
        }
        return array_list;
    }
    
    public Double getResultDayAudit(int year, int month, int day)
    {
    	Double Positive = calPositive(year,month,day).doubleValue();
    	Double Negative = calNegative(year,month,day).doubleValue();
        return Positive-Negative;
    }
    
    private Integer calPositive(int year, int month, int day)
    {
        Integer result = 0;
        String buffer;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select price from " + AUDITS_TABLE_NAME + " where type='Income' AND year="+year+" AND month="+month+" AND day="+day, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
        	buffer = res.getString(res.getColumnIndex(AUDITS_COLUMN_PRICE)).toString();
            result = result + Integer.parseInt(buffer);
            res.moveToNext();
        }
        
        return result;
    }
    
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

