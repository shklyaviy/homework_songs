package com.application.nshi_home_work.service;

import com.application.nshi_home_work.model.Song;
import com.application.nshi_home_work.repository.SongRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {
    @Autowired
    private final SongRepositoryImpl songRepositoryImpl;

    public List<Song> getAllSongs() {
        return songRepositoryImpl.getAllSongs();
    }

    public Song getSongById(Long id) {
        return songRepositoryImpl.getSongById(id);
    }

    public Song addSong (Song song) {
        return songRepositoryImpl.addSong(song);
    }

    public Song updateSong(Long id, Song song) {
        return songRepositoryImpl.updateSong(id, song);
    }

    public void deleteSong(Long id) {
        songRepositoryImpl.deleteSong(id);
    }

    public List<Song> getSortedSongsByAuditions(Integer limit) {
        return songRepositoryImpl.getSortedSongsByAuditions(limit);
    }

    public void listenSongByIds(List<Long> songIds) {
        songRepositoryImpl.listenSongByIds(songIds);
    }

    public void listenSongById(Long songId) {
        songRepositoryImpl.listenSongById(songId);
    }

    public List<Song> getArtistSongsById(Long artistId) {
        return songRepositoryImpl.getArtistSongsById(artistId);
    }
}
