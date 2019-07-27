package com.example.itunesmusic.view.album.list;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itunesmusic.R;
import com.example.itunesmusic.model.entity.AlbumPreview;

import java.util.List;

/**
 * Адаптер для списка музыкальных альбомов.
 */
public class AlbumListAdapter extends RecyclerView.Adapter<AlbumHolder> {
    private List<AlbumPreview> albums;
    private Activity activity;
    private OnAlbumListItemClickListener clickListener;


    AlbumListAdapter(Activity activity, List<AlbumPreview> albums) {
        this.activity = activity;
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.album_list_item_view, viewGroup, false);
        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder albumHolder, int position) {
        final AlbumPreview album = albums.get(position);
        albumHolder.fillView(album);

        albumHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAlbumListItemClick(album.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    /**
     * Регистрирует слушателя нажатия на элемент списка музыкальных альбомов.
     *
     * @param clickListener слушатель нажатия на элемент списка музыкальных альбомов
     */
    void subscribeOnAlbumListItemClick(OnAlbumListItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * Вызывается при нажатии на элемент списка музыкальных альбомов.
     *
     * @param albumId идентификатор альбома
     */
    private void onAlbumListItemClick(long albumId) {
        clickListener.onAlbumListItemClick(albumId);
    }
}
