package es.ulpgc.spotify.model;

public class Track {

    private String id;
    private String name;
    private int duration_ms;
    private boolean explicit;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }
}
