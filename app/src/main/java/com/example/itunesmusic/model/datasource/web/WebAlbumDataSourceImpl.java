package com.example.itunesmusic.model.datasource.web;

import com.example.itunesmusic.model.datasource.web.parser.AlbumInfoJsonParser;
import com.example.itunesmusic.model.datasource.web.parser.JsonParser;
import com.example.itunesmusic.model.entity.AlbumDetail;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Класс для получения подробной информации о музыкальном альбоме из сети.
 */
public class WebAlbumDataSourceImpl implements WebAlbumDataSource {
    private static WebAlbumDataSourceImpl instance;

    private WebAlbumDataSourceImpl() {}

    public static WebAlbumDataSourceImpl getInstance() {
        if(instance == null) {
            instance = new WebAlbumDataSourceImpl();
        }
        return instance;
    }

    @Override
    public AlbumDetail getAlbumDetail(long albumId) throws IOException, JSONException {
        String charset = "UTF-8";
        StringBuilder responseStringBuilder = new StringBuilder();
        String url = constructUrlToSearchAlbumInfo(albumId);
        JsonParser<AlbumDetail> parser = new AlbumInfoJsonParser();

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

        return parser.parse(responseStringBuilder.toString());
    }

    /**
     * Возвращает URL для поиска подробной информации об альбоме.
     *
     * @param albumId имя альбома
     * @return URL для поиска подробной информации об альбоме
     */
    private String constructUrlToSearchAlbumInfo(long albumId) {
        return "https://itunes.apple.com/lookup?id=" + albumId + "&entity=song";
    }
}
