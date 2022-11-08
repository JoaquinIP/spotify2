package es.ulpgc.spotify.controller;

import es.ulpgc.spotify.model.Album;
import es.ulpgc.spotify.model.Artist;
import es.ulpgc.spotify.model.Track;

import java.sql.*;
import java.util.List;



public class DataBase {

    public DataBase() {
    }
    public void setConnect(String url, List<Artist> artists1, List<Album> albums, List<Track> tracks) throws SQLException {
        String dbPath = "jdbc:sqlite:" + url;
        try(Connection connection = connect(dbPath)) {
            Statement statement = connection.createStatement();
            createTable(statement);
            insertArtist(statement, artists1, connection);
            insertAlbum(statement, albums, connection);
            insertTrack(statement, tracks, connection);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS artists (" +
                "id TEXT NOT NULL PRIMARY KEY,\n" +
                "name TEXT NOT NULL,\n" +
                "popularity INTEGER\n" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS albums (" +
                "album_id TEXT NOT NULL PRIMARY KEY,\n" +
                "name TEXT NOT NULL,\n" +
                "release_date TEXT NOT NULL,\n" +
                "total_tracks INTEGER\n" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS tracks (" +
                "track_id TEXT NOT NULL PRIMARY KEY,\n" +
                "name TEXT NOT NULL,\n" +
                "duration_ms INTEGER,\n" +
                "duration_minutes REAL,\n" +
                "explicit BOOLEAN\n" +
                ");");
    }

    private static void insertArtist(Statement statement, List<Artist> artists1, Connection connection) throws SQLException {
        String sql = "INSERT INTO artists (id,name,popularity) VALUES (?,?,?)";
        for (Artist artist : artists1) {
            try (
                    PreparedStatement pstm = connection.prepareStatement(sql)){
                pstm.setString(1, artist.getId());
                pstm.setString(2, artist.getName());
                pstm.setInt(3, artist.getPopularity());
                pstm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void insertAlbum(Statement statement, List<Album> albums, Connection connection) throws SQLException {
        String sql = "INSERT INTO albums (album_id,name,release_date,total_tracks) VALUES (?,?,?,?)";
        for (Album album : albums) {
            try (
                    PreparedStatement pstm = connection.prepareStatement(sql)){
                pstm.setString(1, album.getId());
                pstm.setString(2, album.getName());
                pstm.setString(3, album.getRelease_date());
                pstm.setInt(4, album.getTotal_tracks());
                pstm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void insertTrack(Statement statement, List<Track> tracks, Connection connection) throws SQLException {
        String sql = "INSERT INTO tracks (track_id,name,duration_ms,duration_minutes,explicit) VALUES (?,?,?,?,?)";
        for (Track track : tracks) {
            try (
                    PreparedStatement pstm = connection.prepareStatement(sql)){
                pstm.setString(1, track.getId());
                pstm.setString(2, track.getName());
                pstm.setInt(3, track.getDuration_ms());
                pstm.setFloat(4, (float)track.getDuration_ms() / 60000);
                pstm.setBoolean(5, track.isExplicit());
                pstm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static Connection connect(String dbPath) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            System.out.println("Connection to SQLite has been established.");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


}
