package app.audio;

import java.util.ArrayList;

public abstract class LibraryEntry {
    private final String name;

    /**
     * Constructor for LibraryEntry
     * @param name the name of the entry
     */
    public LibraryEntry(final String name) {
        this.name = name;
    }

    /**
     * Checks if the entry matches the given name
     * @param name1 the name to check
     * @return true if the entry matches the name, false otherwise
     */
    public boolean matchesName(final String name1) {
        return getName().toLowerCase().startsWith(name1.toLowerCase());
    }
    /**
     * Checks if the entry matches the given album
     * @param album the album to check
     * false
     */
    public boolean matchesAlbum(final String album) {
        return false;
    }

    /**
     * Checks if the entry matches the given tags
     * @param tags the tags to check
     * false
     */
    public boolean matchesTags(final ArrayList<String> tags) {
        return false;
    }

    /**
     * Checks if the entry matches the given lyrics
     * @param lyrics the lyrics to check
     * false
     */
    public boolean matchesLyrics(final String lyrics) {
        return false;
    }

    /**
     * Checks if the entry matches the given genre
     * @param genre the genre to check
     * false
     */
    public boolean matchesGenre(final String genre) {
        return false;
    }

    /**
     * Checks if the entry matches the given artist
     * @param artist the artist to check
     * false
     */
    public boolean matchesArtist(final String artist) {
        return false;
    }

    /**
     * Checks if the entry matches the given release year
     * @param releaseYear the release year to check
     * false
     */
    public boolean matchesReleaseYear(final String releaseYear) {
        return false;
    }

    /**
     * Checks if the entry matches the given owner
     * @param user the owner to check
     * false
     */
    public boolean matchesOwner(final String user) {
        return false;
    }

    /**
     * Checks if the entry matches the given user
     * @param user the user to check
     * false
     */
    public boolean isVisibleToUser(final String user) {
        return false;
    }

    /**
     * Checks if the entry matches the given followers
     * @param followers the followers to check
     * false
     */
    public boolean matchesFollowers(final String followers) {
        return false;
    }

    /**
     * Gets the name of the entry
     * @return name of the entry
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param description
     * @return false
     */
    public boolean matchesDescription(final String description) {
        return false;
    }
}
