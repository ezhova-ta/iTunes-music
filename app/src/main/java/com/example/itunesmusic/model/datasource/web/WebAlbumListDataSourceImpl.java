package com.example.itunesmusic.model.datasource.web;

import com.example.itunesmusic.model.datasource.web.parser.AlbumListJsonParser;
import com.example.itunesmusic.model.datasource.web.parser.JsonParser;
import com.example.itunesmusic.model.entity.AlbumPreview;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Класс для получения списка музыкальных альбомов из сети.
 */
public class WebAlbumListDataSourceImpl implements WebAlbumListDataSource {
    private static WebAlbumListDataSourceImpl instance;

    private WebAlbumListDataSourceImpl() {}

    public static WebAlbumListDataSourceImpl getInstance() {
        if(instance == null) {
            instance = new WebAlbumListDataSourceImpl();
        }
        return instance;
    }

    @Override
    public List<AlbumPreview> getAlbumList(String albumName) throws IOException, JSONException {
        String charset = "UTF-8";
        StringBuilder responseStringBuilder = new StringBuilder();
        List<AlbumPreview> albums;
        String url = constructUrlToSearchAlbum(albumName);
        JsonParser<List<AlbumPreview>> parser = new AlbumListJsonParser();

        URL iTunesStore = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) iTunesStore.openConnection();
        InputStream response = connection.getInputStream();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response, charset));

        while ((line = bufferedReader.readLine()) != null) {
            responseStringBuilder.append(line);
        }

        response.close();
        bufferedReader.close();
        connection.disconnect();

        albums = parser.parse(responseStringBuilder.toString());

        Collections.sort(albums);
        return albums;
    }

    /**
     * Возвращает URL для поиска альбомов.
     *
     * @param albumName имя альбома для поиска
     * @return URL для поиска альбомов
     */
    private String constructUrlToSearchAlbum(String albumName) {
        return
                "https://itunes.apple.com/search?term=" +
                albumName +
                "&media=music&entity=album&attribute=albumTerm";
    }
}
