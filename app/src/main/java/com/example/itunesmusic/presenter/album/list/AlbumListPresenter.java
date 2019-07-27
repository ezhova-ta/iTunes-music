package com.example.itunesmusic.presenter.album.list;

import com.example.itunesmusic.view.album.list.AlbumListView;

/**
 * Абстрактное представление о презентере для операции поиска музыкальных альбомов.
 */
public interface AlbumListPresenter {
    /**
     * Вызывается, когда операция связывается с презентером.
     *
     * @param view операция
     */
    void onViewAttached(AlbumListView view);

    /**
     * Вызывается, когда операция отвязывается от презентера.
     */
    void onViewDetached();

    /**
     * Вызывается при нажатии пользователем на кнопку поиска альбомов.
     */
    void onFindAlbumButtonClick();
}
