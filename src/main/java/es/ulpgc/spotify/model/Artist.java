package es.ulpgc.spotify.model;

public class Artist {

    private final String id;
    private final String name;
    private final int popularity;
    private final int followers;

    public Artist(String id, String name, int popularity, int followers) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.followers = followers;
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

    public int getFollowers() {
        return followers;
    }
}