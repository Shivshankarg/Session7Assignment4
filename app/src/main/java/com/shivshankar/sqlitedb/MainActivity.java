package com.shivshankar.sqlitedb;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText nm,emailid,id_no;
    TextView show_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm=(EditText)findViewById(R.id.name);
        emailid=(EditText)findViewById(R.id.email);
        id_no=(EditText)findViewById(R.id.colum_id);
        show_data=(TextView)findViewById(R.id.display_data);

        dbHelper=new DBHelper(this);
    }

    public void onSave(View view) {
        if (nm.getText().toString().isEmpty()||emailid.getText().toString().isEmpty()){

            Toast.makeText(this,"Data not inserted",Toast.LENGTH_SHORT).show();
        /*if (isInserted)
        {
            Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show();
        }*/}
        else
        {
            boolean isInserted=dbHelper.insert(nm.getText().toString(),emailid.getText().toString());
            Toast.makeText(this,"Data  inserted",Toast.LENGTH_SHORT).show();
        }
        nm.setText("");
        emailid.setText("");
        id_no.setText("");
    }

    public void onDisplay(View view) {
        Cursor cursor=dbHelper.getAllData();
        if (cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"Data not found",Toast.LENGTH_SHORT).show();
        }
        StringBuffer buffer=new StringBuffer();
        while (cursor.moveToNext())
        {
            buffer.append("\nID: "+cursor.getString(0)+ ",First Name: " +cursor.getString(1)+ " ,Last Name: "+cursor.getString(2));
        }
        show_data.setText(buffer.toString());
    }

    public void onUpdate(View view) {
        boolean isinsereted=dbHelper.update(id_no.getText().toString(),nm.getText().toString(),emailid.getText().toString());
        if (isinsereted)
        {
            Toast.makeText(this,"Data Updated",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Data not updated",Toast.LENGTH_SHORT).show();
        }
        nm.setText("");
        emailid.setText("");
        id_no.setText("");
    }

    public void onDelete(View view) {
        int row=dbHelper.deleteData(id_no.getText().toString());
        if (row==0)
        {
            Toast.makeText(this,"Data not available",Toast.LENGTH_SHORT).show();
        }
        else
        {

        }
        nm.setText("");
        emailid.setText("");
        id_no.setText("");
    }
}
