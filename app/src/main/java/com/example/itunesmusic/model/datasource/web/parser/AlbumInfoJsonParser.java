package com.example.itunesmusic.model.datasource.web.parser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.itunesmusic.model.entity.AlbumDetail;
import com.example.itunesmusic.model.entity.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для разбора объекта JSON. Результат разбора - подробная информация о музыкальном альбоме.
 */
public class AlbumInfoJsonParser implements JsonParser<AlbumDetail> {
    /**
     * Разбирает объект JSON.
     *
     * @param jsonString строка, представляющая объект JSON
     * @return подробная информация о музыкальном альбоме
     * @throws JSONException - если произошла ошибка разбора
     * @throws IOException - если произошла ошибка ввода/вывода
     */
    @Override
    public AlbumDetail parse(String jsonString) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray(JsonTags.RESULTS.tag());
        AlbumDetail album = null;
        List<Song> songs = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject innerJsonObject = jsonArray.getJSONObject(i);
            if(JsonTags.TRACK.tag().equals(
                    innerJsonObject.getString(JsonTags.WRAPPER_TYPE.tag())
            )) {
                songs.add(new Song(
                        innerJsonObject.getLong(JsonTags.TRACK_ID.tag()),
                        innerJsonObject.getString(JsonTags.TRACK_NAME.tag()),
                        innerJsonObject.getLong(JsonTags.TRACK_TIME_MILLIS.tag())
                ));
            } else if(JsonTags.COLLECTION.tag().equals(
                    innerJsonObject.getString(JsonTags.WRAPPER_TYPE.tag())
            )) {
                URL artworkUrl = new URL(innerJsonObject.getString(JsonTags.ARTWORK_URL_100.tag()));
                Bitmap artworkBitmap = BitmapFactory.decodeStream(artworkUrl.openStream());

                album = new AlbumDetail(
                        innerJsonObject.getLong(JsonTags.COLLECTION_ID.tag()),
                        innerJsonObject.getString(JsonTags.COLLECTION_NAME.tag()),
                        innerJsonObject.getString(JsonTags.ARTIST_NAME.tag()),
                        artworkBitmap,
                        innerJsonObject.getString(JsonTags.COPYRIGHT.tag())
                );
            }
        }

        if(album != null) {
            album.setSongs(songs);
        }

        return album;
    }
}
