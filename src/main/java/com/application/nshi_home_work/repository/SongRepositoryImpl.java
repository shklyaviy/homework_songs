package com.application.nshi_home_work.repository;

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
public class SongRepositoryImpl implements SongRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Song> getAllSongs() {
        String sql = "SELECT * FROM songs";
        return jdbcTemplate.query(sql, new SongRowMapper());
    }

    @Override
    public Song getSongById(Long id) {
        String sql = "SELECT * FROM songs WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new SongRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Song addSong(Song song) {
        String sql1 = "INSERT INTO artists (name) SELECT ? WHERE NOT EXISTS (SELECT * FROM artists WHERE name = ?)";
        jdbcTemplate.update(sql1, song.getArtistName(), song.getArtistName());
        String sql3 = "SELECT id FROM artists WHERE name = ?";
        int artistId = jdbcTemplate.queryForObject(sql3, Integer.class, song.getArtistName());
        String sql2 = "INSERT INTO songs (artist_id, artist_name, name, auditions) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql2, artistId, song.getArtistName(), song.getName(), song.getAuditions());
        return song;
    }

    @Override
    public Song updateSong(Long id, Song song) {
        String sql1 = "INSERT INTO artists (name) SELECT ? WHERE NOT EXISTS (SELECT * FROM artists WHERE name = ?)";
        jdbcTemplate.update(sql1, song.getArtistName(), song.getArtistName());

        String sql3 = "SELECT id FROM artists WHERE name = ?";
        Long artistId = jdbcTemplate.queryForObject(sql3, Long.class, song.getArtistName());

        String sql = "UPDATE songs SET artist_id=?, artist_name=?, name=?, auditions=? WHERE id=?";
        song.setArtistId(artistId);
        song.setId(id);
        jdbcTemplate.update(sql, artistId, song.getArtistName(), song.getName(), song.getAuditions(), id);
        return song;
    }

    @Override
    public void deleteSong(Long id) {
        String sql = "DELETE FROM songs WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Song> getSortedSongsByAuditions(Integer limit) {
        String sql = "SELECT * FROM songs ORDER BY auditions DESC LIMIT ?";
        return jdbcTemplate.query(sql, new Object[]{limit}, new SongRowMapper());
    }

    @Override
    public void listenSongByIds(List<Long> songIds) {
        for (Long songId : songIds) {
            listenSongById(songId);
        }
    }

    @Override
    public void listenSongById(Long songId) {
        String sql = "UPDATE songs SET auditions=auditions+1 WHERE id=?";
        jdbcTemplate.update(sql, songId);
    }

    @Override
    public List<Song> getArtistSongsById(Long artistId) {
        String sql = "SELECT * FROM songs WHERE artist_id=?";
        return jdbcTemplate.query(sql, new Object[]{artistId}, new SongRowMapper());
    }

    @PostConstruct
    public void createSongsTable() {
        jdbcTemplate.execute("CREATE TABLE songs (\n" +
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    artist_id INT,\n" +
                "    artist_name VARCHAR(255),\n" +
                "    name VARCHAR(255),\n" +
                "    auditions INT\n" +
                ");\n");
    }

    private static class SongRowMapper implements RowMapper<Song> {
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
