package com.example.vovannhat_musicapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicServices extends Service {

    private MyPlayer myPlayer;
    private IBinder binder;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ServiceDemo", "onCreate()");
        binder = new MyBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        int fileMp3 = intent.getIntExtra("fileMp3", 0);
        System.out.println(fileMp3);
        myPlayer = new MyPlayer(this, fileMp3);
        Log.d("ServiceDemo", "onBind()");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("ServiceDemo", "onUnbind()");
        myPlayer.stop();
        return super.onUnbind(intent);
    }

    public void play() {
        myPlayer.play();
    }
    public void stop() {
        myPlayer.stop();
    }
    public void pause() {
        myPlayer.pause();
    }
    public void getDuration() {
        myPlayer.getDuration();
    }

    public void fastForward() {
        int currPosition = myPlayer.getCurrPosition();
        currPosition += 5000;
        myPlayer.fastToPos(currPosition);
    }
    public void fastRewind() {
        int currPosition = myPlayer.getCurrPosition();
        if(currPosition > 5000) {
            currPosition -= 5000;
            myPlayer.fastToPos(currPosition);
        }
    }
    public int currentPosition() {
       return myPlayer.getCurrPosition();
    }

    public class MyBinder extends Binder {
        public MusicServices getServices() {
            return MusicServices.this;
        }
    }
}

class MyPlayer {
    private MediaPlayer mediaPlayer;

    public MyPlayer(Context context, int fileMp3) {
        mediaPlayer = MediaPlayer.create(context, fileMp3);
        mediaPlayer.setLooping(true);
    }
    public void getDuration() {
        mediaPlayer.getDuration();
    }

    public void play() {
        if(mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
    public void stop() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void fastToPos(int pos) {
        mediaPlayer.seekTo(pos);
    }

    public int getCurrPosition() {
        return mediaPlayer.getCurrentPosition();
    }
}
