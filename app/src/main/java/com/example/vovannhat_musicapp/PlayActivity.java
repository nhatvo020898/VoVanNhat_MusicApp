package com.example.vovannhat_musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.vovannhat_musicapp.MusicServices.MyBinder;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class PlayActivity extends AppCompatActivity {

    private MusicServices musicServices;
    private boolean isBound = false;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final ImageView btPlay = findViewById(R.id.bt_play);
        final ImageView btPause = findViewById(R.id.bt_pause);
        final ImageView btRewind = findViewById(R.id.bt_rewind);
        final ImageView btForward = findViewById(R.id.bt_Forward);
        final ImageView btBack = findViewById(R.id.btBack);



        Intent getAdapterIntent = getIntent();

        int fileMp3 = getAdapterIntent.getIntExtra("fileMp3", 0);
        String name = getAdapterIntent.getStringExtra("name");
        TextView tvSongName = findViewById(R.id.tvName);
        tvSongName.setText(name);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                MyBinder binder = (MyBinder) service;
                musicServices = binder.getServices();
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isBound = false;
            }
        };

        final Intent intent = new Intent(PlayActivity.this, MusicServices.class);
        intent.putExtra("fileMp3", fileMp3);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);






        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicServices.play();
                btPlay.setVisibility(View.GONE);
                btPause.setVisibility(View.VISIBLE);
                isBound = true;
            }
        });

        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound) {
                    musicServices.pause();
                    btPause.setVisibility(View.GONE);
                    btPlay.setVisibility(View.VISIBLE);

                }
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound) {
                    unbindService(connection);
                    isBound = false;
                    finish();
                }
            }
        });

        btForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound) {
                    musicServices.fastForward();

                }
            }
        });
        btRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBound) {
                    musicServices.fastRewind();

                }
            }
        });



    }

}