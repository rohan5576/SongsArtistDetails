package com.example.songsartistdetails.presenter;

import com.example.songsartistdetails.model.AlbumMatch;
import com.example.songsartistdetails.model.AlbumsList;
import com.example.songsartistdetails.service.ApiClient;
import com.example.songsartistdetails.view.AlbumActivityView;
import com.example.songsartistdetails.view.AlbumDetailActivityView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AlbumPresenterTest {

    private AlbumPresenter mAlbumDetailPresenter;
    private AlbumActivityView mAlbumDetailActivityView;
    private ApiClient mApiClient;

    @Before
    public void setUp() {
        mAlbumDetailActivityView = mock(AlbumActivityView.class);
        mApiClient = mock(ApiClient.class);
        mAlbumDetailPresenter = new AlbumPresenter(mAlbumDetailActivityView, mApiClient);
    }

    /**
     * On Error of API test.
     */
    @Test
    public void testOnError() {
        mAlbumDetailPresenter.onError();
        verify(mAlbumDetailActivityView, times(1)).hideProgress();
    }
}
