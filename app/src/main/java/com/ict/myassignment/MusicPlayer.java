package com.ict.myassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MusicPlayer extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    ImageView iv;
    SeekBar sb;
    TextView rdur, cdur, name;
    ImageButton play;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        iv=findViewById(R.id.scover);
        sb=findViewById(R.id.seekBar);
        rdur=findViewById(R.id.rdur);
        cdur=findViewById(R.id.cdur);
        name=findViewById(R.id.songname);
        play=findViewById(R.id.play);
        Bundle b=getIntent().getExtras();
        String sname=b.getString("song");
        String data=b.getString("data");
        mp=MediaPlayer.create(this, Uri.parse(data));
        name.setText(sname);
        MediaMetadataRetriever mmr=new MediaMetadataRetriever();
        mmr.setDataSource(data);
        byte[] d=mmr.getEmbeddedPicture();
        if(d!= null)
        {
            Bitmap bmp= BitmapFactory.decodeByteArray(d,0,d.length);
            iv.setImageBitmap(bmp);
        }
        else
        {
            iv.setImageResource(R.drawable.music);
        }

        rdur.setText(convertToDuration(mp.getDuration()));
        sb.setMax(mp.getDuration());
        sb.setOnSeekBarChangeListener(this);
        play.setOnClickListener(this);
        new Thread()
        {
            public void run()
            {
                while(mp.getCurrentPosition()!=mp.getDuration())
                {
                    sb.setProgress(mp.getCurrentPosition());
                }
            }
        }.start();
    }
    String convertToDuration(long dur)
    {
        String d="";
        dur=dur/1000;
        d+=dur/60+":"+dur%60;
        return d;
    }

    @Override
    public void onClick(View view) {
        if(!mp.isPlaying())
        {
            mp.start();
            play.setImageResource(R.drawable.pause);
        }
        else
        {
            mp.pause();
            play.setImageResource(R.drawable.play);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(b)
        {
            mp.seekTo(i);
        }
        rdur.setText(convertToDuration(mp.getDuration()-mp.getCurrentPosition()));
        cdur.setText(convertToDuration(mp.getCurrentPosition()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}