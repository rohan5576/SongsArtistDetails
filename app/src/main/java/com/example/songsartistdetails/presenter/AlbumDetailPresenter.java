package com.example.songsartistdetails.presenter;

import com.example.songsartistdetails.model.AlbumMatch;
import com.example.songsartistdetails.model.FavoriteAlbum;
import com.example.songsartistdetails.service.ApiClient;
import com.example.songsartistdetails.view.AlbumDetailActivityView;

public class AlbumDetailPresenter {
    private ApiClient apiClient;
    private AlbumDetailActivityView albumDetailActivityView;
    private FavoriteAlbum mAlbum;

    public AlbumDetailPresenter(AlbumDetailActivityView albumDetailActivityView) {
        this.albumDetailActivityView = albumDetailActivityView;
        if (this.apiClient == null) this.apiClient = new ApiClient();
    }

    public void fetchAlbumDetails(String artist, String album) {
        albumDetailActivityView.showProgress();
        ApiClient apiClient = new ApiClient();
        apiClient.getAlbumDetails(artist, album);
        apiClient.setServerResponseListener(response -> {
            mAlbum = ((AlbumMatch) response).getAlbum();
            albumDetailActivityView.showAlbum(mAlbum);
            albumDetailActivityView.hideProgress();
        });
    }
}
