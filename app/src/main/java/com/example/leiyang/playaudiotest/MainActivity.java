package com.example.leiyang.playaudiotest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

/**
 * 1、注意播放音频权限：
 *      <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 *      <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
 * 2、由于Activity并无变化，注意打开音量（调大音量），避免无效果
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_start;
    private Button btn_pause;
    private Button btn_stop;
    private Button btn_playVideo;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.start);
        btn_pause = (Button) findViewById(R.id.pause);
        btn_stop = (Button) findViewById(R.id.stop);
        btn_playVideo = (Button) findViewById(R.id.playVideo);
        btn_start.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_playVideo.setOnClickListener(this);
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(),"test_music.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            Log.i("info",file.getPath().toString());
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.start:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.playVideo:
                Intent intent = new Intent(this,VideoTest.class);
                startActivity(intent);
            default:break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
