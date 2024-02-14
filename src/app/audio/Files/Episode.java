package app.audio.Files;

public final class Episode extends AudioFile {
    private final String description;

    /**
     * Constructor
     * @param name string
     * @param duration integer
     * @param description string
     */
    public Episode(final String name, final Integer duration, final String description) {
        super(name, duration);
        this.description = description;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
