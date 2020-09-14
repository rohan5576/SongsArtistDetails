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
        apiClient.getArtist(searchTxt, new ApiClient.ServerResponseListener() {
                    @Override
                    public void onSuccessResponseReceived(Object response) {
                        onSuccess(response);
                    }

                    @Override
                    public void onErrorResponseReceived() {
                        onError();
                    }
                }
        );
    }

    /**
     * OnSuccess Response
     *
     * @param response Response object
     */
    protected void onSuccess(Object response) {
        if (response instanceof ArtistsList) {
            List<Artist> artistsList = ((ArtistsList) response).getResults().getArtistmatchs().getArtist();
            mainActivityView.showList(artistsList);
        }
        mainActivityView.hideProgress();
    }

    /**
     * Error Code Data.
     */
    protected void onError() {
        mainActivityView.hideProgress();
    }
}
