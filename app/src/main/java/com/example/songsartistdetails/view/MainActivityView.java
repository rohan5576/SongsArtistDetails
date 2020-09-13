package com.example.songsartistdetails.view;

import com.example.songsartistdetails.model.Artist;

import java.util.List;

public interface MainActivityView extends BaseView {
    void showList(List<Artist> articles);

}