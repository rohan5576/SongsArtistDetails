package com.example.songsartistdetails.presenter;

import com.example.songsartistdetails.model.AlbumMatch;
import com.example.songsartistdetails.service.ApiClient;
import com.example.songsartistdetails.view.AlbumDetailActivityView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AlbumDetailPresenterTest {

    private AlbumDetailPresenter mAlbumDetailPresenter;
    private AlbumDetailActivityView mAlbumDetailActivityView;
    private ApiClient mApiClient;

    @Before
    public void setUp() {
        mAlbumDetailActivityView = mock(AlbumDetailActivityView.class);
        mApiClient = mock(ApiClient.class);
        mAlbumDetailPresenter = new AlbumDetailPresenter(mAlbumDetailActivityView, mApiClient);
    }

    /**
     *  On Success of API test.
     */
    @Test
    public void testOnSuccess() {
        mAlbumDetailPresenter.onSuccess(new AlbumMatch());
        verify(mAlbumDetailActivityView,times(1)).hideProgress();
    }

    /**
     *  On Error of API test.
     */
    @Test
    public void testOnError() {
        mAlbumDetailPresenter.onError();
        verify(mAlbumDetailActivityView,times(1)).hideProgress();
    }
}
