package es.ulpgc.spotify.model;

public class Track {

    private final String id;
    private final String name;
    private final int duration_ms;
    private final boolean explicit;

    public Track(String id, String name, int duration_ms, boolean explicit) {
        this.id = id;
        this.name = name;
        this.duration_ms = duration_ms;
        this.explicit = explicit;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public boolean isExplicit() {
        return explicit;
    }
}
