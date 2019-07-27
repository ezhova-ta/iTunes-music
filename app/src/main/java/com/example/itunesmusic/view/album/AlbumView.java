package com.example.itunesmusic.view.album;

import com.example.itunesmusic.model.entity.AlbumDetail;

/**
 * Абстрактное представление об операции отображения подробной информации
 * о музыкальном альбоме.
 */
public interface AlbumView {
    /**
     * Ключ для задания идентификатора альбома.
     */
    String ALBUM_ID_EXTRA_KEY = "com.example.itunesmusic.view.album.ALBUM_ID";

    /**
     * Отображает индикатор выполнения.
     */
    void showProgressBar();

    /**
     * Скрывает индикатор выполнения.
     */
    void hideProgressBar();

    /**
     * Отображает информацию об альбоме.
     *
     * @param album альбом
     */
    void showAlbumInfo(AlbumDetail album);

    /**
     * Возвращает идентификатор альбома
     *
     * @return идентификатор альбома
     */
    long getAlbumId();

    /**
     * Отображает сообщение о том, что информация об альбоме не найдена.
     */
    void showNoAlbumInfoMessage();
}
