package com.example.itunesmusic.presenter.album;

import android.os.AsyncTask;

import com.example.itunesmusic.model.entity.AlbumDetail;
import com.example.itunesmusic.model.repository.AlbumRepository;
import com.example.itunesmusic.view.album.AlbumView;

import org.json.JSONException;

import java.io.IOException;

/**
 * Презентер для операции отображения подробной информации о музыкальном альбоме.
 */
public class AlbumPresenterImpl implements AlbumPresenter {
    private static AlbumPresenterImpl instance;
    private AlbumView view;
    private final AlbumRepository repository;

    private AlbumPresenterImpl(AlbumRepository repository) {
        this.repository = repository;
    }

    public static AlbumPresenterImpl getInstance(AlbumRepository repository) {
        if(instance == null) {
            instance = new AlbumPresenterImpl(repository);
        }
        return instance;
    }

    @Override
    public void onViewAttached(AlbumView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void onResume() {
        if(view != null) {
            GettingAlbumInfoTask task = new GettingAlbumInfoTask();
            task.execute(view.getAlbumId());
        }
    }

    /**
     * Задача для поиска и отображения информации об альбоме.
     */
    private final class GettingAlbumInfoTask extends AsyncTask<Long, Void, AlbumDetail> {
        @Override
        protected void onPreExecute() {
            if(view != null) {
                view.showProgressBar();
            }
        }

        @Override
        protected AlbumDetail doInBackground(Long... longs) {
            if(longs.length > 0) {
                try {
                    return repository.getAlbumDetail(longs[0]);
                } catch (IOException | JSONException e) {
                    e.printStackTrace(System.err);
                    return null;
                }
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(AlbumDetail album) {
            if(view != null) {
                view.hideProgressBar();

                if(album != null) {
                    view.showAlbumInfo(album);
                } else {
                    view.showNoAlbumInfoMessage();
                }
            }
        }
    }
}
