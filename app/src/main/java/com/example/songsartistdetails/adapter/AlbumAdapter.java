package com.example.songsartistdetails.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.songsartistdetails.AlbumDetailsActivity;
import com.example.songsartistdetails.R;
import com.example.songsartistdetails.model.Album;
import com.example.songsartistdetails.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = "TopAlbumAdapter";
    private List<Album> topAlbumList;
    private Context mContext;

    public AlbumAdapter(Context context, List<Album> topAlbumList) {
        this.mContext = context;
        this.topAlbumList = topAlbumList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.artist_single_item,
                viewGroup, false);
        return new AlbumAdapter.TopAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final Album album = topAlbumList.get(position);
        final AlbumAdapter.TopAlbumViewHolder topAlbumViewHolder = (AlbumAdapter.TopAlbumViewHolder) viewHolder;
        topAlbumViewHolder.topAlbumNameTV.setText(album.getName());
        if (!album.getImage().get(2).getText().isEmpty()) {
            Glide.with(mContext)
                    .load(album.getImage().get(2).getText())
                    .into(topAlbumViewHolder.topAlbumIV);
        }
        topAlbumViewHolder.root.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AlbumDetailsActivity.class);
            intent.putExtra(Constants.ALBUM_NAME, album.getName());
            intent.putExtra(Constants.ARTIST_NAME, album.getArtist().getName());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return topAlbumList == null ? 0 : topAlbumList.size();
    }

    public class TopAlbumViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        RelativeLayout root;
        @BindView(R.id.artist_name_TV)
        TextView topAlbumNameTV;
        @BindView(R.id.artist_IV)
        ImageView topAlbumIV;

        TopAlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
