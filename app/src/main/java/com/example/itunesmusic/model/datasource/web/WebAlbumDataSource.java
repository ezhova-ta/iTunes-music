package com.example.itunesmusic.model.datasource.web;

import com.example.itunesmusic.model.entity.AlbumDetail;

import org.json.JSONException;

import java.io.IOException;

/**
 * Абстрактное представление о классе для получения подробной информации о
 * музыкальном альбоме из сети.
 */
public interface WebAlbumDataSource{
    /**
     * Возвращает детальную информацию об альбоме.
     *
     * @param albumId albumId идентификатор альбома
     * @return информация об альбоме
     * @throws IOException - если произошла ошибка ввода/вывода
     * @throws JSONException - если произошла ошибка разбора
     */
    AlbumDetail getAlbumDetail(long albumId) throws IOException, JSONException;
}
