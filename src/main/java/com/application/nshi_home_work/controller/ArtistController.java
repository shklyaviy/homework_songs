package com.application.nshi_home_work.controller;

import com.application.nshi_home_work.model.Artist;
import com.application.nshi_home_work.model.Song;
import com.application.nshi_home_work.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ArtistController {

    @Autowired
    private final ArtistService artistService;


    @GetMapping("/artists")
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/artists/{id}")
    public Artist getArtistById(@PathVariable Long id) {
        return artistService.getArtistById(id);
    }

//    @GetMapping("/artists/{id}/songs")
//    public List<Song> getSongsByArtistId(@PathVariable Long id) {
//        return artistService.getSongsByArtistId(id);
//    }

//    @PostMapping("/artists")
//    public Artist addArtist(@RequestBody Artist artist) {
////        Artist artist1 = new Artist();
////        BeanUtils.copyProperties(artist, artist1);
//        return artistService.addArtist(artist);
//    }
//
//    @PutMapping("/artists/{id}")
//    public Artist updateArtist(@RequestBody Artist artist, @PathVariable Long id) {
//        artist.setId(id);
//        return artistService.updateArtist(artist);
//    }
//
//    @DeleteMapping("/artists/{id}")
//    public void deleteArtist(@PathVariable Long id) {
//        artistService.deleteArtist(id);
//    }
}
