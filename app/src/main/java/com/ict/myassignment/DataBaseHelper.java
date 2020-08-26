package com.ict.myassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="employeeDB";
    public static final int version=1;
    Context c;
    public static String query="create table if not exists  "+
            Employee.Data.TABLE_NAME+" ( " + Employee.Data.ID+
            " integer primary key autoincrement, " + Employee.Data.E_ID+
            " varchar , " + Employee.Data.E_NAME+
            " varchar , " + Employee.Data.E_DEPT+
            " varchar )";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, version);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sd) {
        sd.execSQL(query);
        Toast.makeText(c, "DataBase Created...!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addRecord(String empid, String name, String dept, SQLiteDatabase sd)
    {
        ContentValues cv=new ContentValues();
        cv.put(Employee.Data.E_ID,empid);
        cv.put(Employee.Data.E_NAME,name);
        cv.put(Employee.Data.E_DEPT,dept);
        sd.insert(Employee.Data.TABLE_NAME, null,cv);
        Toast.makeText(c, "Data Added", Toast.LENGTH_SHORT).show();
    }
    public Cursor getRecord(SQLiteDatabase sd)
    {
        Cursor c;
        String col[] = {Employee.Data.ID,Employee.Data.E_ID,Employee.Data.E_NAME,Employee.Data.E_DEPT};
        c = sd.query(Employee.Data.TABLE_NAME,col,null, null, null, null, Employee.Data.E_ID);
        return c;
    }
    public void delete(SQLiteDatabase sd,String id)
    {
        sd.execSQL("delete from "+Employee.Data.TABLE_NAME+" where "+Employee.Data.ID+" = '"+id+"'");
    }
}
