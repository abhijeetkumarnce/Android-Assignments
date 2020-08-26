package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SongList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    SongCursor sc;
    Cursor c;
    ArrayList<String> songname=new ArrayList<>();
    ArrayList<String> songdata=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        lv=findViewById(R.id.songlist);
        getsong();
        lv.setOnItemClickListener(this);
    }
    void getsong()
    {
        c=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        sc=new SongCursor(this,c,90);
        lv.setAdapter(sc);
        Cursor c1=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        while(c1.moveToNext())
        {
            int i=c1.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
            int j=c1.getColumnIndex(MediaStore.Audio.Media.DATA);
            songname.add(c1.getString(i));
            songdata.add(c1.getString(j));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(this,MusicPlayer.class);
        intent.putExtra("song",songname.get(i));
        intent.putExtra("data",songdata.get(i));
        startActivity(intent);
    }
}