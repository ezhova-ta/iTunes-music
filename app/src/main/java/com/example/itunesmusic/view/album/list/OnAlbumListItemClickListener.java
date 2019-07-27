package com.example.itunesmusic.view.album.list;

/**
 * Абстрактное представление о слушателе нажатия на элемент списка
 * музыкальных альбомов.
 */
public interface OnAlbumListItemClickListener {
    /**
     * Вызывается при нажатии на элемент списка музыкальных альбомов.
     *
     * @param albumId идентификатор альбома
     */
    void onAlbumListItemClick(long albumId);
}
