package com.example.itunesmusic.model.entity;

/**
 * Класс, представляющий песню.
 */
public class Song {
    private long id;
    private String name;
    private long timeMillis;
    private String formattedDuration;

    public Song(long id, String name, long timeMillis) {
        this.id = id;
        this.name = name;
        this.timeMillis = timeMillis;
        if(timeMillis > 0) {
            formattedDuration = formatDuration(timeMillis);
        }
    }

    /**
     * Возвращает идентификатор песни.
     *
     * @return идентификатор песни
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает название песни.
     *
     * @return название песни.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает продолжительность песни.
     *
     * @return продолжительность песни в миллисекундах
     */
    public long getTimeMillis() {
        return timeMillis;
    }

    /**
     * Возвращает продолжительность песни в формате mm:ss.
     *
     * @return строка - продолжительность песни
     */
    public String getFormattedDuration() {
        return formattedDuration;
    }

    /**
     * Представляет продолжительность песни в формате mm:ss.
     *
     * @param timeMillis продолжительность песни в миллисекундах
     * @return продолжительность песни в формате mm:ss
     */
    private String formatDuration(long timeMillis) {
        long minutes = timeMillis / 1000 / 60;
        long seconds = timeMillis / 1000 % 60;

        String minutesString = minutes > 9 ? Long.toString(minutes) : "0" + Long.toString(minutes);
        String secondsString = seconds > 9 ? Long.toString(seconds) : "0" + Long.toString(seconds);

        return minutesString + ":" + secondsString;
    }
}
