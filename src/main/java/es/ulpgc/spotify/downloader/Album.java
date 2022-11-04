package es.ulpgc.spotify.downloader;

public class Album {

    private String name;
    private String release_date;
    private int total_tracks;

    public Album() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks;
    }
}
