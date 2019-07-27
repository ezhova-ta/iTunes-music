package com.example.itunesmusic.model.datasource.web;

import com.example.itunesmusic.model.entity.AlbumPreview;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Абстрактное представление о классе для получения списка музыкальных
 * альбомов из сети.
 */
public interface WebAlbumListDataSource {
    /**
     * Возвращает список альбомов.
     *
     * @param albumName имя альбома для поиска
     * @return список найденных альбомов
     * @throws IOException - если произошла ошибка ввода/вывода
     * @throws JSONException - если произошла ошибка разбора
     */
    List<AlbumPreview> getAlbumList(String albumName) throws IOException, JSONException;
}
