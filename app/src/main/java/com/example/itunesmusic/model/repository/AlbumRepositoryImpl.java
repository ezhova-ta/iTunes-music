package com.example.itunesmusic.model.repository;

import com.example.itunesmusic.model.datasource.web.WebAlbumDataSource;
import com.example.itunesmusic.model.entity.AlbumDetail;

import org.json.JSONException;

import java.io.IOException;

/**
 * Репозиторий для работы с музыкальным альбомом.
 */
public class AlbumRepositoryImpl implements AlbumRepository {
    private WebAlbumDataSource webDataSource;

    public AlbumRepositoryImpl(WebAlbumDataSource webDataSource) {
        this.webDataSource = webDataSource;
    }

    @Override
    public AlbumDetail getAlbumDetail(long albumId) throws IOException, JSONException {
        return webDataSource.getAlbumDetail(albumId);
    }
}
