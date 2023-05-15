package com.application.nshi_home_work.controller;

import com.application.nshi_home_work.dto.SongDTO;
import com.application.nshi_home_work.model.Song;
import com.application.nshi_home_work.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SongController {

    @Autowired
    private final SongService songService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/songs/{id}")
    public Song getSongById(@PathVariable Long id) {
        return songService.getSongById(id);
    }

    @GetMapping("/songs/listen/{limit}")
    public List<Song> getSortedSongsByAuditions(@PathVariable Integer limit) {
        return songService.getSortedSongsByAuditions(limit);
    }

    @GetMapping("/artists/{artistId}/songs")
    public List<Song> getArtistSongsById(@PathVariable Long artistId) {
        return songService.getArtistSongsById(artistId);
    }

    @PostMapping("/songs")
    public ResponseEntity<Song> addSong(@RequestBody Song song) {
        Song createdSong = songService.addSong(song);
//        if(song.getArtistName() )
        return new ResponseEntity<>(createdSong, HttpStatus.CREATED);
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Song updatedSong = songService.updateSong(id, song);
        return ResponseEntity.ok(updatedSong);
    }

    @PostMapping("songs/listen")
    public void listenSongByIds(@RequestBody List<Long> songIds) {
        songService.listenSongByIds(songIds);
    }

    @PostMapping("/songs/{id}/listen")
    public void listenSongById(@PathVariable Long id) {
        songService.listenSongById(id);

    }


    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
