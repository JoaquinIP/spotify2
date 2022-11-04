package es.ulpgc.spotify.downloader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;

public class Main {

    public static final Map<String, String> artists = Map.of(
            "Bad Bunny", "4q3ewBCX7sLwd24euuV69X",
            "Quevedo", "52iwsT98xCoGgiGntTiR7K");

    public static void main(String[] args) throws Exception {
        SpotifyAccessor accessor = new SpotifyAccessor();
        /*for (String artistId : artists.values()) {
            String response = accessor.get("/artists/" + artistId, Map.of());
            JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
            JsonArray items = jsonObject.get("items").getAsJsonArray();
            for (JsonElement item : items) {
                System.out.println(item.getAsJsonObject().get("name").getAsString());
            }
        }*/
        DataBase base = new DataBase();
        base.setConnect("C:\\Users\\joaqu\\IdeaProjects\\spotify2\\src\\main\\java\\es\\ulpgc\\spotify\\downloader\\spoti.db");

    }

    //TODO
    //List<Artist> getArtists(...) filtering by some criteria
    //List<Album> getAlbums(artist)
    //List<Track> getTracks(artist),

}
