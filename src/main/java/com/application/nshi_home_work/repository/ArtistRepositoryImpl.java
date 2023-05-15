package com.application.nshi_home_work.repository;

import com.application.nshi_home_work.model.Artist;
import com.application.nshi_home_work.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Artist> getAllArtists() {
        String sql = "SELECT * FROM artists";
        return jdbcTemplate.query(sql, new ArtistRowMapper());
    }

    @Override
    public Artist getArtistById(Long id) {
        String sql = "SELECT * FROM artists WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ArtistRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Song> getSongsByArtistId(Long id) {
        String sql = "SELECT * FROM songs WHERE artist_id=?";
        return jdbcTemplate.query(sql, new Object[]{id}, new SongRowMapper());
    }

    @Override
    public Artist addArtist(Artist artist) {
        String sql = "INSERT INTO artists (name) VALUES (?)";
        jdbcTemplate.update(sql, artist.getName());
        return artist;
    }

    @Override
    public Artist updateArtist(Artist artist) {
        String sql = "UPDATE artists SET name=? WHERE id=?";
        jdbcTemplate.update(sql, artist.getName(), artist.getId());
        return artist;
    }

    @Override
    public Artist deleteArtist(Long id) {
        String sql = "DELETE FROM artists WHERE id=?";
        jdbcTemplate.update(sql, id);
        return null;
    }

    private static class ArtistRowMapper implements RowMapper<Artist> {
        @Override
        public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Artist artist = new Artist();
            artist.setId(rs.getLong("id"));
            artist.setName(rs.getString("name"));
            return artist;
        }
    }

    @PostConstruct
    public void createArtistTable() {
        jdbcTemplate.execute("CREATE TABLE artists (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name VARCHAR(255)\n" +
                ");");
    }

    static class SongRowMapper implements RowMapper<Song> {
        @Override
        public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
            Song song = new Song();
            song.setId(rs.getLong("id"));
            song.setArtistId(rs.getLong("artist_id"));
            song.setArtistName(rs.getString("artist_name"));
            song.setName(rs.getString("name"));
            song.setAuditions(rs.getInt("auditions"));
            return song;
        }
    }
}

