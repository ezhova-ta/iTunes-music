package com.example.itunesmusic.view.album;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itunesmusic.R;
import com.example.itunesmusic.model.entity.Song;

import java.util.List;

/**
 * Адаптер для списка песен музыкального альбома.
 */
public class SongListAdapter extends RecyclerView.Adapter<SongHolder> {
    private List<Song> songs;
    private Activity activity;

    SongListAdapter(Activity activity, List<Song> songs) {
        this.activity = activity;
        this.songs = songs;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.song_list_item_view, viewGroup, false);
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder songHolder, int position) {
        Song song = songs.get(position);
        songHolder.fillView(song);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
