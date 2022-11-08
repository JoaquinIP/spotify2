package es.ulpgc.spotify.model;

public class Album {

    private String id;
    private String name;
    private String release_date;
    private int total_tracks;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks;
    }

    public void setId(String id) {
        this.id = id;
    }
}
