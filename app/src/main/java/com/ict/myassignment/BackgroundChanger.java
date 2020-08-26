package com.ict.myassignment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class BackgroundChanger extends AppCompatActivity {

    ConstraintLayout clay;
    TextView tv;
    RadioGroup rg;
    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_changer);
        clay=findViewById(R.id.clay);
        tv=findViewById(R.id.textView);
        b1=findViewById(R.id.b1);
        b1.setOnClickListener(cl);
        b2=findViewById(R.id.b2);
        b2.setOnClickListener(oc);
        rg=findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(ocl);
    }

    RadioGroup.OnCheckedChangeListener ocl=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton r=findViewById(group.getCheckedRadioButtonId());
        }

    };
    View.OnClickListener cl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(rg.getCheckedRadioButtonId()) {
                case R.id.radioButton:
                    clay.setBackgroundColor(Color.YELLOW);
                    break;
                case R.id.radioButton2:
                    clay.setBackgroundColor(Color.GREEN);
                    break;
                case R.id.radioButton3:
                    clay.setBackgroundColor(Color.RED);
                    break;
                case R.id.radioButton4:
                    clay.setBackgroundColor(Color.BLUE);
                    break;
            }
        }
    };
    View.OnClickListener oc= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            rg.clearCheck();
            clay.setBackgroundColor(Color.WHITE);
        }
    };
}