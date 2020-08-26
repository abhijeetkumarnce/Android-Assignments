package com.ict.myassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Camera extends AppCompatActivity implements View.OnClickListener {
    ImageView iv;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        iv=findViewById(R.id.iv);
        click=findViewById(R.id.click);
        click.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
        startActivityForResult(intent,1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b=data.getExtras();
        if(requestCode==1234 && resultCode==RESULT_OK)
        {
            Bitmap bmp=(Bitmap)b.get("data");
            iv.setImageBitmap(bmp);
        }
    }
}