package com.example.itunesmusic.model.repository;

import com.example.itunesmusic.model.datasource.web.WebAlbumListDataSource;
import com.example.itunesmusic.model.entity.AlbumPreview;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Репозиторий для работы со списком музыкальных альбомов.
 */
public class AlbumListRepositoryImpl implements AlbumListRepository {
    private WebAlbumListDataSource webDataSource;

    public AlbumListRepositoryImpl(WebAlbumListDataSource webDataSource) {
        this.webDataSource = webDataSource;
    }

    @Override
    public List<AlbumPreview> getAlbumList(String albumName) throws IOException, JSONException {
        return webDataSource.getAlbumList(albumName);
    }
}
