package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBase extends AppCompatActivity {
    Button add, create, view;
    SQLiteDatabase sd;
    DataBaseHelper mdh;
    ListView lv;
    ArrayList<String> ar;
    View.OnClickListener ocl=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog d=new Dialog(DataBase.this);
            d.setContentView(R.layout.insertrecords);
            d.show();
            final EditText id=d.findViewById(R.id.empid);
            final EditText name=d.findViewById(R.id.name);
            final EditText dept=d.findViewById(R.id.dept);
            Button reg=d.findViewById(R.id.save);
            reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String eid=id.getText().toString();
                    String ename=name.getText().toString();
                    String edept=dept.getText().toString();
                    mdh.addRecord(eid,ename,edept,sd);
                    Toast.makeText(DataBase.this, "Data Inserted...!!!", Toast.LENGTH_SHORT).show();
                    d.dismiss();
                    getData();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        add=findViewById(R.id.insertrecords);
        create=findViewById(R.id.create);
        add.setOnClickListener(ocl);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DataBase.this, "TABLE CREATED...!!!", Toast.LENGTH_SHORT).show();
            }
        });
        mdh=new DataBaseHelper(this);
        sd=mdh.getWritableDatabase();
        lv=findViewById(R.id.employeelist);
        getData();
        registerForContextMenu(lv);
    }
    public void getData()
    {
        ar=new ArrayList<>();
        Cursor c=mdh.getRecord(sd);
        EmployeeCursorAdapter mca=new EmployeeCursorAdapter(this,c, 0);
        lv.setAdapter(mca);
        Cursor c1=mdh.getRecord(sd);
        while(c1.moveToNext())
        {
            ar.add(c1.getString(c1.getColumnIndex(Employee.Data.ID)));
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select your work");
        menu.addSubMenu(0,v.getId(),0,"Delete");
        menu.addSubMenu(0,v.getId(),0,"Update");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().toString().equals("Delete"))
        {
            AdapterView.AdapterContextMenuInfo adc=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            mdh.delete(sd,ar.get(adc.position));
            getData();
        }
        return false;
    }
}