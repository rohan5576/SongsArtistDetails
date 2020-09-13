package com.example.songsartistdetails.presenter;

import com.example.songsartistdetails.adapter.AlbumAdapter;
import com.example.songsartistdetails.model.Album;
import com.example.songsartistdetails.model.AlbumsList;
import com.example.songsartistdetails.model.Artist;
import com.example.songsartistdetails.model.ArtistsList;
import com.example.songsartistdetails.service.ApiClient;
import com.example.songsartistdetails.util.Constants;
import com.example.songsartistdetails.view.AlbumActivityView;
import com.example.songsartistdetails.view.MainActivityView;

import java.util.List;

public class AlbumPresenter {
    private ApiClient apiClient;
    private AlbumActivityView albumActivityView;
    private List<Album> albumList;

    public AlbumPresenter(AlbumActivityView albumActivityView) {
        this.albumActivityView = albumActivityView;
        if(this.apiClient == null) this.apiClient = new ApiClient();
    }

    public void performServerRequest(String artistName) {
        albumActivityView.showProgress();
        ApiClient apiClient = new ApiClient();
        apiClient.getArtistTopAlbums(artistName);
        apiClient.setServerResponseListener((Object response) -> {
            if (response instanceof AlbumsList) {
                albumList = ((AlbumsList) response).getTopalbums().getAlbum();
                albumActivityView.showList(albumList);
                albumActivityView.hideProgress();
            }
        });
    }
}
