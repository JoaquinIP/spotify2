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

public class Deserializer {
    public static Map<String, String> artists = Map.of(
            "Bad Bunny", "4q3ewBCX7sLwd24euuV69X",
            "Imagine Dragons", "53XhwfbYqKCa1cC15pYq2q");

    public void Store() throws Exception {
        SpotifyAccessor accessor = new SpotifyAccessor();
        List<Artist> artists1 = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();
        for (String artistId : artists.values()) {
            String responseArtist = accessor.get("/artists/" + artistId, Map.of());
            JsonObject artistObject = new Gson().fromJson(responseArtist, JsonObject.class);
            String artistName = artistObject.getAsJsonObject().get("name").getAsString();
            int artistPopularity = artistObject.getAsJsonObject().get("popularity").getAsInt();
            artists1.add(new Artist(artistId, artistName, artistPopularity));

            String responseAlbum = accessor.get("/artists/" + artistId + "/albums", Map.of());
            JsonObject albumObject = new Gson().fromJson(responseAlbum, JsonObject.class);
            JsonArray albumItems = albumObject.get("items").getAsJsonArray();
            for (JsonElement albumItem : albumItems) {
                String albumId = albumItem.getAsJsonObject().get("id").getAsString();
                String albumName = albumItem.getAsJsonObject().get("name").getAsString();
                String releaseDate = albumItem.getAsJsonObject().get("release_date").getAsString();
                int totalTracks = albumItem.getAsJsonObject().get("total_tracks").getAsInt();
                albums.add(new Album(albumId, albumName, releaseDate, totalTracks));

                String responseTracks = accessor.get("/albums/" + albumId + "/tracks", Map.of());
                JsonObject trackObject = new Gson().fromJson(responseTracks, JsonObject.class);
                JsonArray trackItems = trackObject.get("items").getAsJsonArray();
                for (JsonElement trackItem : trackItems) {
                    String trackId = trackItem.getAsJsonObject().get("id").getAsString();
                    String trackName = trackItem.getAsJsonObject().get("name").getAsString();
                    int durationMs = trackItem.getAsJsonObject().get("duration_ms").getAsInt();
                    boolean explicit = trackItem.getAsJsonObject().get("explicit").getAsBoolean();
                    tracks.add(new Track(trackId, trackName, durationMs, explicit));
                }
            }
        }
        connector(artists1, albums, tracks);
    }

    private void connector(List<Artist> artists1, List<Album> albums, List<Track> tracks) throws SQLException {
        DataBase base = new DataBase();
        base.setConnect("C:\\Users\\joaqu\\IdeaProjects\\spotify2\\src\\main\\java\\es\\ulpgc\\spotify\\controller\\spoti.db", artists1, albums, tracks);
    }
}
