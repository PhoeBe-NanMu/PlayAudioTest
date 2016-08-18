package com.example.leiyang.playaudiotest;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by LeiYang on 2016/8/18 0018.
 */

public class VideoTest extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;
    private Button start_video;
    private Button pause_video;
    private Button replay_video;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videotest);
        start_video = (Button) findViewById(R.id.start_video);
        pause_video = (Button) findViewById(R.id.pause_video);
        replay_video = (Button) findViewById(R.id.replay_video);
        videoView = (VideoView) findViewById(R.id.videoView);
        start_video.setOnClickListener(this);
        pause_video.setOnClickListener(this);
        replay_video.setOnClickListener(this);
        initVideoPlayer();
    }

    private void initVideoPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(),"test_video.mp4");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.start_video:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.pause_video:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.replay_video:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
            default:break;

        }

    }
}
