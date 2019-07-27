package com.example.itunesmusic.model.repository;

import com.example.itunesmusic.model.entity.AlbumPreview;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Абстрактное представление о репозитории для работы со списком музыкальных альбомов.
 */
public interface AlbumListRepository {
    /**
     * Возвращает список альбомов.
     *
     * @param albumName имя альбома для поиска
     * @return список найденных альбомов
     * @throws IOException - если произошла ошибка ввода/вывода
     * @throws JSONException - если произошла ошибка разбора
     */
    List<AlbumPreview> getAlbumList(String albumName)  throws IOException, JSONException;
}
