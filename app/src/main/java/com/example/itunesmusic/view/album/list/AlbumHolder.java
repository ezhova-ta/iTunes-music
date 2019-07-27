package com.example.itunesmusic.view.album.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.itunesmusic.R;
import com.example.itunesmusic.model.entity.AlbumPreview;

/**
 * Представление элемента списка музыкальных альбомов.
 */
class AlbumHolder extends RecyclerView.ViewHolder {
    private TextView albumName;
    private TextView artistName;

    AlbumHolder(@NonNull View albumView) {
        super(albumView);
        albumName = albumView.findViewById(R.id.albumListItemName);
        artistName = albumView.findViewById(R.id.albumListItemArtistName);
    }

    /**
     * Отображает информацию об альбоме в списке.
     *
     * @param album альбом
     */
    void fillView(AlbumPreview album) {
        albumName.setText(album.getName());
        artistName.setText(album.getArtistName());
    }
}
