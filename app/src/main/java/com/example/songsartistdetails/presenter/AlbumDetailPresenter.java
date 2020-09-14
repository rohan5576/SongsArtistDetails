package com.example.songsartistdetails.presenter;

import com.example.songsartistdetails.model.AlbumMatch;
import com.example.songsartistdetails.model.Artist;
import com.example.songsartistdetails.model.ArtistsList;
import com.example.songsartistdetails.model.FavoriteAlbum;
import com.example.songsartistdetails.service.ApiClient;
import com.example.songsartistdetails.view.AlbumDetailActivityView;

import java.util.List;

public class AlbumDetailPresenter {
    private ApiClient apiClient;
    private AlbumDetailActivityView albumDetailActivityView;
    private FavoriteAlbum mAlbum;

    public AlbumDetailPresenter(AlbumDetailActivityView albumDetailActivityView ,ApiClient apiClient) {
        this.albumDetailActivityView = albumDetailActivityView;
        if(this.apiClient == null) this.apiClient = apiClient;
    }

    public void fetchAlbumDetails(String artist, String album) {
        albumDetailActivityView.showProgress();
        ApiClient apiClient = new ApiClient();
        apiClient.getAlbumDetails(artist, album,  new ApiClient.ServerResponseListener() {
                    @Override
                    public void onSuccessResponseReceived(Object response) {
                        onSuccess((AlbumMatch) response);
                    }


            @Override
            public void onErrorResponseReceived() {
                onError();
            }

        });
    }

    /**
     *  On Success API.
     * @param response AlbumMatch response.
     */
    protected void onSuccess(AlbumMatch response) {
        mAlbum = response.getAlbum();
        albumDetailActivityView.showAlbum(mAlbum);
        albumDetailActivityView.hideProgress();
    }

    /**
     * Error Code Data.
     */
    protected void onError() {
        albumDetailActivityView.hideProgress();
    }
}
