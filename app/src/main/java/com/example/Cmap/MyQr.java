package com.example.Cmap;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class MyQr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr);

//        VideoView mVideoView = (VideoView) findViewById(R.id.videoView);
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/QR.mp4");
//        mVideoView.setVideoURI(uri);
//
//        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                // 준비 완료되면 비디오 재생
//                mp.start();
//            }
//        });
    }


}