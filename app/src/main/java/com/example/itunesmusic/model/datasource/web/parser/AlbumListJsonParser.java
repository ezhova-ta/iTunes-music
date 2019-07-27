package com.example.itunesmusic.model.datasource.web.parser;

import com.example.itunesmusic.model.entity.AlbumPreview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для разбора объекта JSON. Результат разбора - список музыкальных альбомов.
 */
public class AlbumListJsonParser implements JsonParser<List<AlbumPreview>> {
    /**
     * Разбирает объект JSON.
     *
     * @param jsonString строка, представляющая объект JSON
     * @return список музыкальных альбомов
     * @throws JSONException - если произошла ошибка разбора
     */
    @Override
    public List<AlbumPreview> parse(String jsonString) throws JSONException {
        List<AlbumPreview> albums = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray(JsonTags.RESULTS.tag());

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject innerJsonObject = jsonArray.getJSONObject(i);
            albums.add(new AlbumPreview(
                    innerJsonObject.getLong(JsonTags.COLLECTION_ID.tag()),
                    innerJsonObject.getString(JsonTags.COLLECTION_NAME.tag()),
                    innerJsonObject.getString(JsonTags.ARTIST_NAME.tag())
            ));
        }

        return albums;
    }
}
