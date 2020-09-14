package com.example.songsartistdetails;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.songsartistdetails.adapter.TracksAdapter;
import com.example.songsartistdetails.model.FavoriteAlbum;
import com.example.songsartistdetails.model.Track;
import com.example.songsartistdetails.presenter.AlbumDetailPresenter;
import com.example.songsartistdetails.util.Constants;
import com.example.songsartistdetails.view.AlbumDetailActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.songsartistdetails.service.ApiClient.getAPIClientInstance;


public class AlbumDetailsActivity extends AppCompatActivity implements AlbumDetailActivityView {

    private String TAG = "AlbumDetailsActivity";
    @BindView(R.id.no_tracks_LL)
    LinearLayout noTracksLL;
    @BindView(R.id.body_LL)
    LinearLayout bodyLL;
    @BindView(R.id.album_name_TV)
    TextView albumTV;
    @BindView(R.id.artist_name_TV)
    TextView artistTV;
    @BindView(R.id.cover_IV)
    ImageView coverIV;
    @BindView(R.id.tracks_RV)
    RecyclerView tracksRV;
    private List<Track> mTracks;
    private TracksAdapter tracksAdapter;
    private AlbumDetailPresenter albumDetailPresenter;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);
        ButterKnife.bind(this);
        initView();
        String album = getIntent().getStringExtra(Constants.ALBUM_NAME);
        String artist = getIntent().getStringExtra(Constants.ARTIST_NAME);
        albumDetailPresenter.fetchAlbumDetails(artist, album);
    }

    private void initView() {
        progressdialog = new ProgressDialog(this);
        albumDetailPresenter = new AlbumDetailPresenter(this,getAPIClientInstance());
        tracksRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        tracksRV.setHasFixedSize(true);
    }


    @Override
    public void showAlbum(FavoriteAlbum favoriteAlbum) {
        albumTV.setText(favoriteAlbum.getName());
        artistTV.setText(favoriteAlbum.getArtist());
        mTracks = favoriteAlbum.getTracks().getTrack();
        if (mTracks.size() == 0) {
            bodyLL.setVisibility(View.GONE);
            noTracksLL.setVisibility(View.VISIBLE);
        }
        Glide.with(this)
                .load(favoriteAlbum.getImage().get(2).getText())
                .apply(RequestOptions.circleCropTransform())
                .into(coverIV);
        tracksAdapter = new TracksAdapter(AlbumDetailsActivity.this, mTracks);
        tracksRV.setAdapter(tracksAdapter);
    }

    @Override
    public void showProgress() {
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
    }

    @Override
    public void hideProgress() {
        progressdialog.dismiss();
    }
}
