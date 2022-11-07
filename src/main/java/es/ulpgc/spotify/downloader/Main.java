package es.ulpgc.spotify.downloader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static final Map<String, String> artists = Map.of(
            "Bad Bunny", "4q3ewBCX7sLwd24euuV69X",
            "Quevedo", "52iwsT98xCoGgiGntTiR7K");

    public static void main(String[] args) throws Exception {
        SpotifyAccessor accessor = new SpotifyAccessor();
        List<Artist> artists1 = new ArrayList<>();
        List<Album> albums = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();

        for (String artistId : artists.values()) {
            String response1 = accessor.get("/artists/" + artistId, Map.of());
            JsonObject jsonObject1 = new Gson().fromJson(response1, JsonObject.class);
            String artist_id = jsonObject1.getAsJsonObject().get("id").getAsString();
            String artist_name = jsonObject1.getAsJsonObject().get("name").getAsString();
            int popularity = jsonObject1.getAsJsonObject().get("popularity").getAsInt();
            artists1.add(new Artist(artist_id, artist_name, popularity));


            String response2 = accessor.get("/artists/" + artistId + "/albums", Map.of());
            JsonObject jsonObject2 = new Gson().fromJson(response2, JsonObject.class);
            JsonArray items2 = jsonObject2.get("items").getAsJsonArray();
            for (JsonElement item : items2) {
                String album_id = item.getAsJsonObject().get("id").getAsString();
                String album_name = item.getAsJsonObject().get("name").getAsString();
                String release_date = item.getAsJsonObject().get("release_date").getAsString();
                int total_tracks = item.getAsJsonObject().get("total_tracks").getAsInt();
                albums.add(new Album(album_id, album_name, release_date, total_tracks));
            }
        }

        DataBase base = new DataBase();
        base.setConnect("C:\\Users\\joaqu\\IdeaProjects\\spotify2\\src\\main\\java\\es\\ulpgc\\spotify\\downloader\\spoti.db");

    }

    //TODO
    //List<Artist> getArtists(...) filtering by some criteria
    //List<Album> getAlbums(artist)
    //List<Track> getTracks(artist),

}
