package es.ulpgc.spotify.downloader;

public class Artist {

    private String id;
    private String name;
    private int popularity;

    public Artist(String name, String id, int popularity) {
        this.name = name;
        this.id = id;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
