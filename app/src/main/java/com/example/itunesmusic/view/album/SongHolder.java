package com.example.itunesmusic.view.album;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.itunesmusic.R;
import com.example.itunesmusic.model.entity.Song;

/**
 * Представление элемента списка песен музыкального альбома.
 */
class SongHolder extends RecyclerView.ViewHolder {
    private TextView songName;
    private TextView songDuration;

    SongHolder(@NonNull View songView) {
        super(songView);
        songName = songView.findViewById(R.id.songListItemName);
        songDuration = songView.findViewById(R.id.songListItemDuration);
    }

    /**
     * Отображает информацию о песне в списке.
     *
     * @param song песня
     */
    void fillView(Song song) {
        songName.setText(song.getName());
        songDuration.setText(song.getFormattedDuration());
    }
}
