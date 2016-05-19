package com.example.notes;

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

public class Add_audit extends Activity {
    private ToggleButton type;
    private EditText Name;
    private EditText price;
    private String strtype;
    private String strName;
    private String strprice;
    private Button submit;
    private Button back;

    private DBHelper mydb;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_audit);
        
        mydb = new DBHelper(this);
        
        initWidget();
        submit = (Button)findViewById(R.id.submit);
        back = (Button)findViewById(R.id.back);
        submit.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		strtype = type.getText().toString();
                strName = Name.getText().toString();
                strprice = price.getText().toString();
                if(strName.compareTo("")==0 || strprice.compareTo("")==0)
                	showErrorDialog();
                else
                	showCheckDialog();
        	}
        });
        back.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		finish();
        	}
        });
    }

    private void initWidget() {
        type = (ToggleButton)findViewById(R.id.type);
        Name = (EditText)findViewById(R.id.Name);
        price = (EditText)findViewById(R.id.price);
    } 
    
    private void resetWidget() {
        Name.setText("");
        price.setText("");
    } 

    private void showCheckDialog() {
        AlertDialog.Builder addAuditAlert = new AlertDialog.Builder(this);
        addAuditAlert.setTitle("เพิ่มรายการประเภท " + strtype + " เข้าสู่ฐานข้อมูล");
        addAuditAlert.setMessage("รายการ\t:\t" + strName + "\n" + "ราคา\t:\t" + strprice);

        addAuditAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        addData();
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
 
    
    private void showErrorDialog() {
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
    	Bundle bundle = getIntent().getExtras();
   	  	int year = bundle.getInt("Year");
   	  	int month = bundle.getInt("Month");
   	  	int day = bundle.getInt("Day");
        if(mydb.insertAudit(strtype,strName,strprice,year,month,day)){
        	
        	Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();	
        }	
        else{
            Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
        }
        finish();
        resetWidget();
    }
}

