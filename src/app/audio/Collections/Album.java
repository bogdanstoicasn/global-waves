package app.audio.Collections;

import java.util.ArrayList;
import app.audio.Files.Song;

public class Album extends Playlist {

    private String description;

    private Integer releaseYear;

    /**
     * @param name
     * @param owner
     * @param releaseYear
     * @param description
     * @param songs
     */
    public Album(final String name, final String owner,
                 final Integer releaseYear, final String description,
                 final ArrayList<Song> songs) {
        super(name, owner);

        this.description = description;

        for (Song song1: songs) {
            addSong(song1);
        }

        this.releaseYear = releaseYear;


    }
    /**
     * getter for description
     */
    public String getDescription() {
        return description;
    }
    /**
     * setter for description
     */
    public void setDescription(final String description) {
        this.description = description;
    }
    /**
     * getter for releaseYear
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }
    /**
     * setter for releaseYear
     */
    public void setReleaseYear(final Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * @return String of name
     */
    public String toString() {
        return getName();
    }
}
