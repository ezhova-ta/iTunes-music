package com.example.itunesmusic.presenter.album.list;

import android.os.AsyncTask;

import com.example.itunesmusic.model.entity.AlbumPreview;
import com.example.itunesmusic.model.repository.AlbumListRepository;
import com.example.itunesmusic.view.album.AlbumView;
import com.example.itunesmusic.view.album.list.AlbumListView;
import com.example.itunesmusic.view.album.list.OnAlbumListItemClickListener;

import org.json.JSONException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Презентер для операции поиска музыкальных альбомов.
 */
public class AlbumListPresenterImpl implements AlbumListPresenter, OnAlbumListItemClickListener {
    private static AlbumListPresenterImpl instance;
    private AlbumListView view;
    private final AlbumListRepository repository;

    private AlbumListPresenterImpl(AlbumListRepository repository) {
        this.repository = repository;
    }

    public static AlbumListPresenterImpl getInstance(AlbumListRepository repository) {
        if(instance == null) {
            instance = new AlbumListPresenterImpl(repository);
        }
        return instance;
    }

    @Override
    public void onViewAttached(AlbumListView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void onFindAlbumButtonClick() {
        if(view != null) {
            String albumName = view.getAlbumNameEditTextValue();
            if(!albumName.isEmpty()) {
                GettingAlbumListTask task = new GettingAlbumListTask();
                task.execute(albumName);
            }
        }
    }

    @Override
    public void onAlbumListItemClick(long albumId) {
        if(view != null) {
            view.displayAlbumDetail(AlbumView.ALBUM_ID_EXTRA_KEY, albumId);
        }
    }

    /**
     * Задача для поиска и отображения списка альбомов.
     */
    private final class GettingAlbumListTask extends AsyncTask<String, Void, List<AlbumPreview>> {
        private char space = ' ';
        private char plus = '+';

        @Override
        protected void onPreExecute() {
            if(view != null) {
                view.showProgressBar();
            }
        }

        @Override
        protected List<AlbumPreview> doInBackground(String... albumNames) {
            if(albumNames.length > 0) {
                String albumName = albumNames[0].trim().replace(space, plus);
                try {
                    return repository.getAlbumList(albumName);
                } catch (IOException | JSONException e) {
                    e.printStackTrace(System.err);
                    return Collections.emptyList();
                }
            } else {
                return Collections.emptyList();
            }
        }

        @Override
        protected void onPostExecute(List<AlbumPreview> albums) {
            if(view != null) {
                view.hideProgressBar();
                view.clearAlbumNameEditText();
                view.clearAlbumList();

                if(!albums.isEmpty()) {
                    view.showAlbumList(albums);
                } else {
                    view.showEmptyAlbumListMessage();
                }
            }
        }
    }
}
