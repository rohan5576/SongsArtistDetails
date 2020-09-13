package com.example.songsartistdetails.view;

import com.example.songsartistdetails.model.Album;
import com.example.songsartistdetails.model.Artist;

import java.util.List;

public interface  AlbumActivityView extends BaseView {
    void showList(List<Album> albumList);
}