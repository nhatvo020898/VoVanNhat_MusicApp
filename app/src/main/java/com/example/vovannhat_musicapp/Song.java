package com.example.vovannhat_musicapp;

public class Song {
    private String name;

    private int fileMp3;

    public Song(String name, int fileMp3) {
        this.name = name;
        this.fileMp3 = fileMp3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getFileMp3() {
        return fileMp3;
    }

    public void setFileMp3(int fileMp3) {
        this.fileMp3 = fileMp3;
    }
}
