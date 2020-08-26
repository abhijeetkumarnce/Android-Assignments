package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class GreetFriendApp extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    TextView tv;
    Button greet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet_friend_app);
        name=findViewById(R.id.name);
        greet=findViewById(R.id.greet);
        tv=findViewById(R.id.msg);
        greet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String friendName = name.getText().toString();

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        String greeting = null;
        if(hour>=6 && hour<12){
            greeting = "Good Morning";
        } else if(hour>= 12 && hour < 17){
            greeting = "Good Afternoon";
        } else if(hour >= 17 && hour < 21){
            greeting = "Good Evening";
        } else if(hour >= 21 && hour < 30){
            greeting = "Good Night";
        }

        switch (view.getId()) {
            case R.id.greet:
                tv.setText( greeting + " " + friendName + "!");
                break;
            default:
                break;
        }

    }
}