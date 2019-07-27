package com.example.itunesmusic.view.album.list;

import com.example.itunesmusic.model.entity.AlbumPreview;

import java.util.List;

/**
 * Абстрактное представление об операции поиска музыкальных альбомов.
 */
public interface AlbumListView {
    /**
     * Возвращает значение поля ввода имени альбома.
     *
     * @return значение поля ввода имени альбома
     */
    String getAlbumNameEditTextValue();

    /**
     * Отображает список альбомов.
     *
     * @param albums список альбомов
     */
    void showAlbumList(List<AlbumPreview> albums);

    /**
     * Отображает индикатор выполнения.
     */
    void showProgressBar();

    /**
     * Скрывает индикатор выполнения.
     */
    void hideProgressBar();

    /**
     * Запускает операцию отображения подробной информации об альбоме.
     *
     * @param albumIdExtraKey ключ для задания идентификатора альбома
     * @param albumIdExtraValue идентификатор альбома
     */
    void displayAlbumDetail(String albumIdExtraKey, long albumIdExtraValue);

    /**
     * Отображает сообщение о том, что не было найдено ни одного альбома.
     */
    void showEmptyAlbumListMessage();

    /**
     * Очищает поле ввода имени альбома.
     */
    void clearAlbumNameEditText();

    /**
     * Очищает список альбомов.
     */
    void clearAlbumList();
}
