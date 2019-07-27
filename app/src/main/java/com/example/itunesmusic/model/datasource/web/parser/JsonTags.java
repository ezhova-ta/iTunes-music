package com.example.itunesmusic.model.datasource.web.parser;

/**
 * Ключи объекта JSON.
 */
enum JsonTags {
    RESULTS("results"),
    COLLECTION("collection"),
    TRACK("track"),
    COLLECTION_ID("collectionId"),
    COLLECTION_NAME("collectionName"),
    ARTIST_NAME("artistName"),
    WRAPPER_TYPE("wrapperType"),
    TRACK_ID("trackId"),
    TRACK_NAME("trackName"),
    TRACK_TIME_MILLIS("trackTimeMillis"),
    ARTWORK_URL_100("artworkUrl100"),
    COPYRIGHT("copyright");

    private String tag;

    JsonTags(String tag) {
        this.tag = tag;
    }

    /**
     * Возвращает ключ объекта JSON.
     *
     * @return ключ объекта JSON
     */
    String tag() {
        return tag;
    }
}
