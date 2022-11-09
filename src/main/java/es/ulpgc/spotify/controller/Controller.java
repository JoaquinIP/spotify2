package es.ulpgc.spotify.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import es.ulpgc.spotify.model.Album;
import es.ulpgc.spotify.model.Artist;
import es.ulpgc.spotify.model.Track;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    public static Map<String, String> artists = Map.of(
            "Bad Bunny", "4q3ewBCX7sLwd24euuV69X",
            "Quevedo", "52iwsT98xCoGgiGntTiR7K",
            "Mora", "0Q8NcsJwoCbZOHHW63su5S",
            "Myke Towers", "7iK8PXO48WeuP03g8YR51W",
            "Duki", "1bAftSH8umNcGZ0uyV7LMg");
    List<Artist> artists1 = new ArrayList<>();
    List<Album> albums = new ArrayList<>();
    List<Track> tracks = new ArrayList<>();

    public void store() throws Exception {
        SpotifyAccessor accessor = new SpotifyAccessor();
        for (String artistId : artists.values()) {
            JsonObject artistObject = artistAccessor(accessor, artistId);
            getArtists(artists1, artistId, artistObject);
            JsonArray albumItems = albumAccessor(accessor, artistId);
            for (JsonElement albumItem : albumItems) {
                String albumId = albumItem.getAsJsonObject().get("id").getAsString();
                getAlbums(albums, albumItem, albumId);
                JsonArray trackItems = trackAccessor(accessor, albumId);
                for (JsonElement trackItem : trackItems) {
                    getTracks(tracks, trackItem);
                }
            }
        }
        connector(artists1, albums, tracks);
    }

    private static void getArtists(List<Artist> artists1, String artistId, JsonObject artistObject) {
        String artistName = artistObject.getAsJsonObject().get("name").getAsString();
        int artistPopularity = artistObject.getAsJsonObject().get("popularity").getAsInt();
        int followers = artistObject.getAsJsonObject().get("followers").getAsJsonObject().get("total").getAsInt();
        artists1.add(new Artist(artistId, artistName, artistPopularity, followers));
    }

    private static void getAlbums(List<Album> albums, JsonElement albumItem, String albumId) {
        String albumName = albumItem.getAsJsonObject().get("name").getAsString();
        String releaseDate = albumItem.getAsJsonObject().get("release_date").getAsString();
        int totalTracks = albumItem.getAsJsonObject().get("total_tracks").getAsInt();
        albums.add(new Album(albumId, albumName, releaseDate, totalTracks));
    }

    private static void getTracks(List<Track> tracks, JsonElement trackItem) {
        String trackId = trackItem.getAsJsonObject().get("id").getAsString();
        String trackName = trackItem.getAsJsonObject().get("name").getAsString();
        int durationMs = trackItem.getAsJsonObject().get("duration_ms").getAsInt();
        boolean explicit = trackItem.getAsJsonObject().get("explicit").getAsBoolean();
        tracks.add(new Track(trackId, trackName, durationMs, explicit));
    }

    private static JsonObject artistAccessor(SpotifyAccessor accessor, String artistId) throws Exception {
        String responseArtist = accessor.get("/artists/" + artistId, Map.of());
        JsonObject artistObject = new Gson().fromJson(responseArtist, JsonObject.class);
        return artistObject;
    }

    private static JsonArray albumAccessor(SpotifyAccessor accessor, String artistId) throws Exception {
        String responseAlbum = accessor.get("/artists/" + artistId + "/albums", Map.of());
        JsonObject albumObject = new Gson().fromJson(responseAlbum, JsonObject.class);
        JsonArray albumItems = albumObject.get("items").getAsJsonArray();
        return albumItems;
    }

    private static JsonArray trackAccessor(SpotifyAccessor accessor, String albumId) throws Exception {
        String responseTracks = accessor.get("/albums/" + albumId + "/tracks", Map.of());
        JsonObject trackObject = new Gson().fromJson(responseTracks, JsonObject.class);
        JsonArray trackItems = trackObject.get("items").getAsJsonArray();
        return trackItems;
    }


    private void connector(List<Artist> artists1, List<Album> albums, List<Track> tracks) throws SQLException {
        DataBase base = new DataBase();
        base.setConnect("C:\\Users\\joaqu\\IdeaProjects\\spotify2\\src\\main\\java\\es\\ulpgc\\spotify\\database\\spoti.db", artists1, albums, tracks);
    }
}
