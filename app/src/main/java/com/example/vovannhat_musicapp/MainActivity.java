package com.example.vovannhat_musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> listSong;
    private MusicAdapter musicAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listSong = new ArrayList<>();
        listSong.add(new Song("Passionfruit", R.raw.passionfruit));
        listSong.add(new Song("Can't take my eyes off of you", R.raw.canttake));
        listSong.add(new Song("Together", R.raw.together));
        listSong.add(new Song("Best Friend", R.raw.bestfriend));
        listSong.add(new Song("Sun Flowers", R.raw.sunflower));
        listSong.add(new Song("Sai Gon dep lam", R.raw.saigondeplam));


        recyclerView = findViewById(R.id.recycleView);
        musicAdapter = new MusicAdapter(this, listSong);
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}