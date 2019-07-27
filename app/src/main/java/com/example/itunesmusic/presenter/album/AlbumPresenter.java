package com.example.itunesmusic.presenter.album;

import com.example.itunesmusic.view.album.AlbumView;

/**
 * Абстрактное представление о презентере для операции отображения
 * подробной информации о музыкальном альбоме.
 */
public interface AlbumPresenter {
    /**
     * Вызывается, когда операция связывается с презентером.
     *
     * @param view операция
     */
    void onViewAttached(AlbumView view);

    /**
     * Вызывается, когда операция отвязывается от презентера.
     */
    void onViewDetached();

    /**
     * Вызывается перед тем, как операция начнет взаимодействие с пользователем.
     */
    void onResume();
}
