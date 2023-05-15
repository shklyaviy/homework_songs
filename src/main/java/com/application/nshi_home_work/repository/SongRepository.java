package com.application.nshi_home_work.repository;

import com.application.nshi_home_work.model.Song;

import java.util.List;

public interface SongRepository {
    List<Song> getAllSongs();
    Song getSongById(Long id);
    Song addSong(Song song);
    Song updateSong(Long id, Song song);
    void deleteSong(Long id);
    List<Song> getSortedSongsByAuditions(Integer limit);
    void listenSongByIds(List<Long> songIds);
    void listenSongById(Long songId);
    List<Song> getArtistSongsById(Long artistId);
}
