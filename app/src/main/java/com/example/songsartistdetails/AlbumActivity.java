package com.example.songsartistdetails;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songsartistdetails.adapter.AlbumAdapter;
import com.example.songsartistdetails.model.Album;
import com.example.songsartistdetails.model.AlbumsList;
import com.example.songsartistdetails.model.FavoriteAlbum;
import com.example.songsartistdetails.presenter.AlbumPresenter;
import com.example.songsartistdetails.presenter.MainPresenter;
import com.example.songsartistdetails.service.ApiClient;
import com.example.songsartistdetails.util.Constants;
import com.example.songsartistdetails.view.AlbumActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumActivity extends AppCompatActivity implements AlbumActivityView {

    private String TAG = "AlbumActivity";
    @BindView(R.id.artist_name_TV)
    TextView artistNameTV;
    @BindView(R.id.top_album_RV)
    RecyclerView topAlbumRV;

    private AlbumAdapter topAlbumAdapter;
    private AlbumPresenter albumPresenter;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_activity);
        ButterKnife.bind(this);
        initView();
        albumPresenter.performServerRequest(getIntent().getStringExtra(Constants.ARTIST_NAME));
    }

    private void initView() {
        progressdialog = new ProgressDialog(this);
        artistNameTV.setText(getIntent().getStringExtra(Constants.ARTIST_NAME));
        topAlbumRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        albumPresenter = new AlbumPresenter(this, ApiClient.getAPIClientInstance());
    }


    @OnClick(R.id.back_FL)
    public void onBackButtonClick() {
        AlbumActivity.this.finish();
    }

    @Override
    public void showList(List<Album> albumList) {
        topAlbumAdapter = new AlbumAdapter(AlbumActivity.this, albumList);
        topAlbumRV.setAdapter(topAlbumAdapter);
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
