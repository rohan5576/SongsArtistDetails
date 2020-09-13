package com.example.songsartistdetails.presenter;

import com.example.songsartistdetails.model.Artist;
import com.example.songsartistdetails.model.ArtistsList;
import com.example.songsartistdetails.service.ApiClient;
import com.example.songsartistdetails.view.MainActivityView;

import java.util.List;

public class MainPresenter {
    private ApiClient apiClient;
    private MainActivityView mainActivityView;

    public MainPresenter(MainActivityView mainActivityView,  ApiClient apiClient) {
        this.mainActivityView = mainActivityView;
        if(this.apiClient == null) this.apiClient = apiClient;
    }

    public void performSearch(String searchTxt) {
        mainActivityView.showProgress();
        apiClient = new ApiClient();
        apiClient.getArtist(searchTxt);
        apiClient.setServerResponseListener(response -> {
            if (response instanceof ArtistsList) {
                List<Artist> artistsList = ((ArtistsList) response).getResults().getArtistmatchs().getArtist();
                mainActivityView.showList(artistsList);
                mainActivityView.hideProgress();
            }
        });
    }
}
