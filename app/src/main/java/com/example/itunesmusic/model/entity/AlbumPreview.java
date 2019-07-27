package com.example.itunesmusic.model.entity;

/**
 * Класс, представляющий основную информацию о музыкальном альбоме.
 */
public class AlbumPreview implements Comparable<AlbumPreview> {
    private long id;
    private String name;
    private String artistName;

    public AlbumPreview(long id, String name, String artistName) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
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

    @Override
    public int compareTo(AlbumPreview album) {
        if(!name.equals(album.name)) {
            return name.compareToIgnoreCase(album.name);
        } else {
            return artistName.compareToIgnoreCase(album.artistName);
        }
    }
}
