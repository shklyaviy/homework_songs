package com.application.nshi_home_work.repository;

import com.application.nshi_home_work.model.Artist;
import com.application.nshi_home_work.model.Song;

import java.util.List;

public interface ArtistRepository {
    List<Artist> getAllArtists();
    Artist getArtistById(Long id);
    List<Song> getSongsByArtistId(Long id);
    Artist addArtist(Artist artist);
    Artist updateArtist(Artist artist);
    Artist deleteArtist(Long id);
}
