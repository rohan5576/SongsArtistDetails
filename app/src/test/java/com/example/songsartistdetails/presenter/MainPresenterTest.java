package com.example.songsartistdetails.presenter;

import com.example.songsartistdetails.service.ApiClient;
import com.example.songsartistdetails.view.MainActivityView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    private MainPresenter mMainPresenter;
    private MainActivityView mMainActivityView;
    private ApiClient mApiClient;

    @Before
    public void setUp() {
        mMainActivityView = mock(MainActivityView.class);
        mApiClient = mock(ApiClient.class);
        mMainPresenter = new MainPresenter(mMainActivityView, mApiClient);
    }

    /**
     *  On Success of API test.
     */
    @Test
    public void testOnSuccess() {
        mMainPresenter.onSuccess("Songs");
        verify(mMainActivityView,times(1)).hideProgress();
    }

    /**
     *  On Error of API test.
     */
    @Test
    public void testOnError() {
        mMainPresenter.onError();
        verify(mMainActivityView,times(1)).hideProgress();
    }
}
