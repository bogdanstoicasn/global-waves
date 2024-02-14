package app.searchBar;

import fileio.input.FiltersInput;

import java.util.ArrayList;

public class Filters {
    private String name;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private String releaseYear;
    private String artist;
    private String owner;
    private String followers;

    private String description;

    /**
     * Constructor
     * @param filters
     */
    public Filters(final FiltersInput filters) {
        this.name = filters.getName();
        this.album = filters.getAlbum();
        this.tags = filters.getTags();
        this.lyrics = filters.getLyrics();
        this.genre = filters.getGenre();
        this.releaseYear = filters.getReleaseYear();
        this.artist = filters.getArtist();
        this.owner = filters.getOwner();
        this.followers = filters.getFollowers();
        this.description = filters.getDescription();

    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return album
     */
    public String getAlbum() {
        return album;
    }

    /**
     *
     * @param album
     */
    public void setAlbum(final String album) {
        this.album = album;
    }

    /**
     *
     * @return tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     *
     * @param tags
     */
    public void setTags(final ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     *
     * @return lyrics
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     *
     * @param lyrics
     */
    public void setLyrics(final String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     *
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     */
    public void setGenre(final String genre) {
        this.genre = genre;
    }

    /**
     *
     * @return releaseYear
     */
    public String getReleaseYear() {
        return releaseYear;
    }

    /**
     *
     * @param releaseYear
     */
    public void setReleaseYear(final String releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     *
     * @return artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     *
     * @param artist
     */
    public void setArtist(final String artist) {
        this.artist = artist;
    }

    /**
     *
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     */
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
     *
     * @return followers
     */
    public String getFollowers() {
        return followers;
    }

    /**
     *
     * @param followers
     */
    public void setFollowers(final String followers) {
        this.followers = followers;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}
