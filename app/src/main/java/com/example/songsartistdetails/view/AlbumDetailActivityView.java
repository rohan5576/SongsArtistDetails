package com.example.songsartistdetails.view;

import com.example.songsartistdetails.model.Artist;
import com.example.songsartistdetails.model.FavoriteAlbum;

import java.util.List;

public interface AlbumDetailActivityView extends BaseView {
    void showAlbum(FavoriteAlbum favoriteAlbum);
}