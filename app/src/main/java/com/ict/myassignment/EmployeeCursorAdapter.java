package com.ict.myassignment;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

class EmployeeCursorAdapter extends CursorAdapter {
    public EmployeeCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.employeelistlayout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView id=view.findViewById(R.id.empid);
        TextView name=view.findViewById(R.id.name);
        TextView dept=view.findViewById(R.id.dept);
        String eid=cursor.getString(cursor.getColumnIndexOrThrow(Employee.Data.E_ID));
        String ename=cursor.getString(cursor.getColumnIndexOrThrow(Employee.Data.E_NAME));
        String edept=cursor.getString(cursor.getColumnIndexOrThrow(Employee.Data.E_DEPT));
        id.setText(eid);
        name.setText(ename);
        dept.setText(edept);
    }
}
