package com.example.itunesmusic.model.entity;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Класс, представляющий детальную информацию о музыкальном альбоме.
 */
public class AlbumDetail {
    private long id;
    private String name;
    private String artistName;
    private Bitmap artwork;
    private String copyright;
    private List<Song> songs;

    public AlbumDetail(long id, String name, String artistName, Bitmap artwork, String copyright, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.artwork = artwork;
        this.copyright = copyright;
        this.songs = songs;
    }

    public AlbumDetail(long id, String name, String artistName, Bitmap artwork, String copyright) {
        this(id, name, artistName, artwork, copyright, null);
    }

    /**
     * Возвращает идентификатор альбома.
     *
     * @return идентификатор альбома
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает название альбома.
     *
     * @return название альбома
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает имя исполнителя.
     *
     * @return имя исполнителя
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Возвращает обложку альбома.
     *
     * @return обложка альбома
     */
    public Bitmap getArtwork() {
        return artwork;
    }

    /**
     * Возвращает авторские права.
     *
     * @return строка - авторские права
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Возвращает список песен альбома.
     *
     * @return список песен
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Устанавливает список песен альбома.
     *
     * @param songs список песен
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
