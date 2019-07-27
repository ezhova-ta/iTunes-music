package com.example.itunesmusic.model.repository;

import com.example.itunesmusic.model.entity.AlbumDetail;

import org.json.JSONException;

import java.io.IOException;

/**
 * Абстрактное представление о репозитории для работы с музыкальным альбомом.
 */
public interface AlbumRepository {
    /**
     * Возвращает детальную информацию об альбоме.
     *
     * @param albumId идентификатор альбома
     * @return информация об альбоме
     * @throws IOException - если произошла ошибка ввода/вывода
     * @throws JSONException - если произошла ошибка разбора
     */
    AlbumDetail getAlbumDetail(long albumId)  throws IOException, JSONException;
}
