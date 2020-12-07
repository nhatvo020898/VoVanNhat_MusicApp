package com.example.vovannhat_musicapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private ArrayList<Song> listSong;
    private LayoutInflater layoutInflater;

    public MusicAdapter(Context context, ArrayList<Song> listSong) {
        this.layoutInflater = LayoutInflater.from(context);
        this.listSong = listSong;
    }

    @NonNull
    @Override
    public MusicAdapter.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.songs_recycleview, parent, false);
        return new MusicViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.MusicViewHolder holder, int position) {
        final String name = listSong.get(position).getName();
        final int fileMp3 = listSong.get(position).getFileMp3();

        holder.tvSongName.setText(name);
        holder.tvSongName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlayActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("fileMp3", fileMp3);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listSong.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSongName;
        public MusicAdapter musicAdapter;

        public MusicViewHolder(@NonNull View itemView, MusicAdapter musicAdapter) {
            super(itemView);
            tvSongName = itemView.findViewById(R.id.tvSongName);
            this.musicAdapter = musicAdapter;
        }
    }
}
