package com.example.itunesmusic.app;

import com.example.itunesmusic.model.datasource.web.WebAlbumDataSource;
import com.example.itunesmusic.model.datasource.web.WebAlbumDataSourceImpl;
import com.example.itunesmusic.model.datasource.web.WebAlbumListDataSource;
import com.example.itunesmusic.model.datasource.web.WebAlbumListDataSourceImpl;
import com.example.itunesmusic.model.repository.AlbumListRepository;
import com.example.itunesmusic.model.repository.AlbumListRepositoryImpl;
import com.example.itunesmusic.model.repository.AlbumRepository;
import com.example.itunesmusic.model.repository.AlbumRepositoryImpl;
import com.example.itunesmusic.presenter.album.AlbumPresenter;
import com.example.itunesmusic.presenter.album.AlbumPresenterImpl;
import com.example.itunesmusic.presenter.album.list.AlbumListPresenter;
import com.example.itunesmusic.presenter.album.list.AlbumListPresenterImpl;
import com.example.itunesmusic.view.album.list.OnAlbumListItemClickListener;

/**
 * Класс, осуществляющий разрешение зависимостей.
 */
public class DependencyInjectionFactory {
    /**
     * Возвращает экземпляр класса для получения списка музыкальных альбомов из сети.
     *
     * @return экземпляр класса для получения списка музыкальных альбомов из сети
     */
    private WebAlbumListDataSource provideWebAlbumListDataSource() {
        return WebAlbumListDataSourceImpl.getInstance();
    }

    /**
     * Возвращает экземпляр класса для получения подробной информации
     * о музыкальном альбоме из сети.
     *
     * @return экземпляр класса для получения подробной информации
     * о музыкальном альбоме из сети
     */
    private WebAlbumDataSource provideWebAlbumDataSource() {
        return WebAlbumDataSourceImpl.getInstance();
    }

    /**
     * Возвращает экземпляр класса для работы со списком музыкальных альбомов.
     *
     * @return репозиторий для работы со списком музыкальных альбомов
     */
    private AlbumListRepository provideAlbumListRepository() {
        return new AlbumListRepositoryImpl(provideWebAlbumListDataSource());
    }

    /**
     * Возвращает экземпляр класса для работы с музыкальным альбомом.
     *
     * @return репозиторий для работы с музыкальным альбомом
     */
    private AlbumRepository provideAlbumRepository() {
        return new AlbumRepositoryImpl(provideWebAlbumDataSource());
    }

    /**
     * Возвращает экземпляр презентера для опреации поиска музыкальных альбомов.
     *
     * @return презентер для опреации поиска музыкальных альбомов
     */
    public AlbumListPresenter provideAlbumListPresenter() {
        return AlbumListPresenterImpl.getInstance(provideAlbumListRepository());
    }

    /**
     * Возвращает экземпляр презентера для опреации отображения подробной
     * информации о музыкальном альбоме.
     *
     * @return презентер для опреации отображения подробной информации
     * о музыкальном альбоме
     */
    public AlbumPresenter provideAlbumPresenter() {
        return AlbumPresenterImpl.getInstance(provideAlbumRepository());
    }

    /**
     * Возвращает экземпляр презентера для опреации поиска музыкальных альбомов.
     *
     * @return презентер для опреации поиска музыкальных альбомов
     */
    public OnAlbumListItemClickListener provideOnAlbumListItemClickListener() {
        return AlbumListPresenterImpl.getInstance(provideAlbumListRepository());
    }
}
