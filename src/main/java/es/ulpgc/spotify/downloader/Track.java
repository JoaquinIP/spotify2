package es.ulpgc.spotify.downloader;

public class Track {

    private String name;
    private int duration_ms;
    private boolean explicit;

    public Track() {
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
