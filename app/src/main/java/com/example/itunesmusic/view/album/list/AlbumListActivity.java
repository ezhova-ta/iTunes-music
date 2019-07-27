package com.example.itunesmusic.view.album.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.itunesmusic.R;
import com.example.itunesmusic.app.DependencyInjectionFactory;
import com.example.itunesmusic.app.ITunesMusicApplication;
import com.example.itunesmusic.model.entity.AlbumPreview;
import com.example.itunesmusic.presenter.album.list.AlbumListPresenter;
import com.example.itunesmusic.view.album.AlbumActivity;

import java.util.List;

/**
 * Операция поиска музыкальных альбомов.
 */
public class AlbumListActivity extends AppCompatActivity implements AlbumListView {
    private AlbumListPresenter presenter;
    private OnAlbumListItemClickListener clickListener;
    private EditText albumNameEditText;
    private ProgressBar albumListLoadingProgress;
    private RecyclerView albumListRecyclerView;
    private AlbumListAdapter adapter;
    private List<AlbumPreview> albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        initViewElements();

        DependencyInjectionFactory diFactory = ITunesMusicApplication.getInstance().getDiFactory();
        presenter = diFactory.provideAlbumListPresenter();
        clickListener = diFactory.provideOnAlbumListItemClickListener();
    }

    /**
     * Инициализирует элементы пользовательского интерфейса.
     */
    private void initViewElements() {
        albumNameEditText = findViewById(R.id.albumNameEditText);
        albumListLoadingProgress = findViewById(R.id.albumListLoadingProgress);
        albumListRecyclerView = findViewById(R.id.albumList);
        albumListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewDetached();
    }

    /**
     * Вызывается при нажатии пользователем на кнопку поиска альбомов.
     *
     * @param view компонент, на который было произведено нажатие
     */
    public void onFindAlbumButtonClick(View view) {
        presenter.onFindAlbumButtonClick();
    }

    @Override
    public String getAlbumNameEditTextValue() {
        return albumNameEditText.getText().toString();
    }

    @Override
    public void showAlbumList(List<AlbumPreview> albums) {
        this.albums = albums;
        adapter = new AlbumListAdapter(this, albums);
        adapter.subscribeOnAlbumListItemClick(clickListener);
        albumListRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgressBar() {
        albumListLoadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        albumListLoadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void displayAlbumDetail(String albumIdExtraKey, long albumIdExtraValue) {
        Intent intent = new Intent(this, AlbumActivity.class);
        intent.putExtra(albumIdExtraKey, albumIdExtraValue);
        startActivity(intent);
    }

    @Override
    public void showEmptyAlbumListMessage() {
        showPopupMessage(getResources().getText(R.string.empty_album_list_message));
    }

    @Override
    public void clearAlbumNameEditText() {
        albumNameEditText.getText().clear();
    }

    @Override
    public void clearAlbumList() {
        if(adapter != null && albums != null) {
            albums.clear();
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Отображает короткое всплывающее сообщение в центре экрана.
     *
     * @param text текст сообщения
     */
    private void showPopupMessage(final CharSequence text) {
        final Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
