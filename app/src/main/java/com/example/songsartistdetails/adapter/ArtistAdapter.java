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
import com.example.songsartistdetails.AlbumActivity;
import com.example.songsartistdetails.R;
import com.example.songsartistdetails.model.Artist;
import com.example.songsartistdetails.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String TAG;
    private List<Artist> artistList;
    private Context mContext;

    public ArtistAdapter(Context context, List<Artist> artistList) {
        this.mContext = context;
        this.artistList = artistList;
        TAG = "ArtistAdapter";
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.artist_single_item,
                viewGroup, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final Artist artist = artistList.get(position);
        final ArtistAdapter.ArtistViewHolder artistViewHolder = (ArtistAdapter.ArtistViewHolder) viewHolder;
        artistViewHolder.artistNameTV.setText(artist.getName());
        if (!artist.getImage().get(2).getText().isEmpty()) {
            Glide.with(artistViewHolder.artistNameTV.getContext())
                    .load(artist.getImage().get(2).getText())
                    .into(artistViewHolder.artistIV);
        }
        artistViewHolder.root.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AlbumActivity.class);
            intent.putExtra(Constants.ARTIST_NAME, artist.getName());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return artistList == null ? 0 : artistList.size();
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root) RelativeLayout root;
        @BindView(R.id.artist_IV) ImageView artistIV;
        @BindView(R.id.artist_name_TV)  TextView artistNameTV;

        ArtistViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
