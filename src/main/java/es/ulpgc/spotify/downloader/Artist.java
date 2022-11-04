package es.ulpgc.spotify.downloader;

public class Artist {

    private String name;
    private String id;
    private int popularity;

    public Artist() {
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
