package com.example.video;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView video;
    private ImageView play;
    private ImageView stop;
    private ImageView skip;
    private ImageView skipback;
    private ImageView toStart;
    private boolean loop = false;
    private int stopPosition;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video = findViewById(R.id.videoView);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        skip = findViewById(R.id.skip);
        skipback = findViewById(R.id.skipback);
        toStart = findViewById(R.id.tostart);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.devfest19;
        Uri uri = Uri.parse(videoPath);
        video.setVideoURI(uri);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (video.isPlaying()) {
                    video.pause();
                    stopPosition = video.getCurrentPosition();
                    play.setImageResource(R.drawable.play);

                } else {
                    video.seekTo(stopPosition);
                    video.start();
                    play.setImageResource(R.drawable.pause);
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                if (video.isPlaying()){
                    video.pause();
                    play.setImageResource(R.drawable.play);
                }
                stopPosition = video.getDuration();
                video.seekTo(stopPosition);
                stopPosition =0;
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                stopPosition = video.getCurrentPosition();
                if(stopPosition +30000 <= video.getDuration()){
                    System.out.println("add 30 second");
                    stopPosition +=30000;

                }
                System.out.println(stopPosition);
                video.seekTo(stopPosition);
                video.start();
            }
        });
        skipback.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                stopPosition = video.getCurrentPosition();
                if(stopPosition -30000 >= 0){
                    stopPosition -=30000;

                }
                video.seekTo(stopPosition);
                video.start();
            }
        });
        toStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
                    video.pause();
                    stopPosition=0;
                    video.seekTo(stopPosition);
                    play.setImageResource(R.drawable.play);
            }
        });
    }
}