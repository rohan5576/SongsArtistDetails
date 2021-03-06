package com.example.songsartistdetails.service;


import com.example.songsartistdetails.model.AlbumMatch;
import com.example.songsartistdetails.model.AlbumsList;
import com.example.songsartistdetails.model.ArtistsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("?method=artist.gettopalbums&format=json")
    Call<AlbumsList> getTopAlbums(@Query("artist") String artist, @Query("api_key") String api_key);

    @GET("?method=artist.search&format=json")
    Call<ArtistsList> getArtist(@Query("artist") String artist,
                                @Query("limit") int limit, @Query("api_key") String apiKey);

    @GET("?method=album.getinfo&format=json")
    Call<AlbumMatch> getAlbumDetails(@Query("artist") String artist,
                                     @Query("album") String album, @Query("api_key") String apiKey);

}
