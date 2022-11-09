package es.ulpgc.spotify.model;

public class Album {

    private final String id;
    private final String name;
    private final String release_date;
    private final int total_tracks;

    public Album(String id, String name, String release_date, int total_tracks) {
        this.id = id;
        this.name = name;
        this.release_date = release_date;
        this.total_tracks = total_tracks;
    }

    public String getName() {
        return name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getTotal_tracks() {
        return total_tracks;
    }

    public String getId() {
        return id;
    }
}
