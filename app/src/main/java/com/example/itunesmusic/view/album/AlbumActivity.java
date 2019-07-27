package com.example.itunesmusic.view.album;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itunesmusic.R;
import com.example.itunesmusic.app.DependencyInjectionFactory;
import com.example.itunesmusic.app.ITunesMusicApplication;
import com.example.itunesmusic.model.entity.AlbumDetail;
import com.example.itunesmusic.model.entity.Song;
import com.example.itunesmusic.presenter.album.AlbumPresenter;

import java.util.List;

/**
 * Операция отображения подробной информации о музыкальном альбоме.
 */
public class AlbumActivity extends AppCompatActivity implements AlbumView {
    private static final String ALBUM_ID_BUNDLE_KEY = "albumId";
    private static final long ALBUM_ID_DEFAULT_BUNDLE_VALUE = 0;
    private AlbumPresenter presenter;
    private long albumId;
    private ProgressBar albumInfoLoadingProgress;
    private ImageView artwork;
    private TextView albumName;
    private TextView albumArtistName;
    private TextView albumCopyright;
    private RecyclerView songListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        initViewElements();

        if(savedInstanceState != null) {
            albumId = savedInstanceState.getLong(ALBUM_ID_BUNDLE_KEY);
        } else {
            albumId = getIntent().getLongExtra(ALBUM_ID_EXTRA_KEY, ALBUM_ID_DEFAULT_BUNDLE_VALUE);
        }

        DependencyInjectionFactory diFactory = ITunesMusicApplication.getInstance().getDiFactory();
        presenter = diFactory.provideAlbumPresenter();
    }

    /**
     * Инициализирует элементы пользовательского интерфейса.
     */
    private void initViewElements() {
        albumInfoLoadingProgress = findViewById(R.id.albumInfoLoadingProgress);
        artwork = findViewById(R.id.albumArtwork);
        albumName = findViewById(R.id.albumName);
        albumArtistName = findViewById(R.id.albumArtistName);
        albumCopyright = findViewById(R.id.albumCopyright);
        songListRecyclerView = findViewById(R.id.songList);
        songListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(ALBUM_ID_BUNDLE_KEY, albumId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewDetached();
    }

    @Override
    public void showProgressBar() {
        albumInfoLoadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        albumInfoLoadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void showAlbumInfo(AlbumDetail album) {
        artwork.setImageBitmap(album.getArtwork());
        albumName.setText(album.getName());
        albumArtistName.setText(album.getArtistName());
        albumCopyright.setText(album.getCopyright());

        List<Song> songs = album.getSongs();

        if(songs != null) {
            SongListAdapter adapter = new SongListAdapter(this, songs);
            songListRecyclerView.setAdapter(adapter);
        } else {
        }
    }

    @Override
    public long getAlbumId() {
        return albumId;
    }

    @Override
    public void showNoAlbumInfoMessage() {
        showPopupMessage(getResources().getText(R.string.no_album_info_message));
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
