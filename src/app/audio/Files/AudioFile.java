package app.audio.Files;

import app.audio.LibraryEntry;

public abstract class AudioFile extends LibraryEntry {
    private final Integer duration;

    /**
     * Constructor
     * @param name string
     * @param duration integer
     */
    public AudioFile(final String name, final Integer duration) {
        super(name);
        this.duration = duration;
    }

    /**
     * @return duration
     */
    public  Integer getDuration() {
        return duration;
    }
}
