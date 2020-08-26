package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BasicCalculator extends AppCompatActivity implements View.OnClickListener {
    Button a,s,m,d;
    EditText n1,n2;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);
        a=findViewById(R.id.add);
        s=findViewById(R.id.sub);
        m=findViewById(R.id.mul);
        d=findViewById(R.id.div);
        n1=findViewById(R.id.n1);
        n2=findViewById(R.id.n2);
        tv=findViewById(R.id.ans);
        a.setOnClickListener(this);
        s.setOnClickListener(this);
        m.setOnClickListener(this);
        d.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        double num1=Double.parseDouble(n1.getText().toString());
        double num2=Double.parseDouble(n2.getText().toString());
        switch(view.getId())
        {
            case R.id.add:
                tv.setText("Answer is "+(num1+num2));
                break;
            case R.id.sub:
                tv.setText("Answer is "+(num1-num2));
                break;
            case R.id.mul:
                tv.setText("Answer is "+(num1*num2));
                break;
            case R.id.div:
                tv.setText("Answer is "+(num1/num2));
                break;
        }
    }
}