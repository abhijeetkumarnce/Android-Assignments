package com.ict.myassignment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class LifeCycleApp extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_app);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        Toast.makeText(this, "Bye-Bye", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}