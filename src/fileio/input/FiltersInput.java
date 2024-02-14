package fileio.input;

import java.util.ArrayList;

public class FiltersInput {
    private String name;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private String releaseYear; // pentru search song/episode -> releaseYear
    private String artist;
    private String owner; // pentru search playlist si podcast
    private String followers; // pentru search playlist -> followers

    private String description; // pentru search

    public FiltersInput() {
    }

    /**
      *get name
     **/
    public String getName() {
        return name;
    }

    /**
    * set name
     **/
    public void setName(final String name) {
        this.name = name;
    }

    /**
    * get album
     **/
    public String getAlbum() {
        return album;
    }

    /**
    * set album
     **/
    public void setAlbum(final String album) {
        this.album = album;
    }

    /**
    * get tags
     **/
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
    * set tags
     **/
    public void setTags(final ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
    * get lyrics
     **/
    public String getLyrics() {
        return lyrics;
    }

    /**
    * set lyrics
     **/
    public void setLyrics(final String lyrics) {
        this.lyrics = lyrics;
    }

    /**
    * get genre
     **/
    public String getGenre() {
        return genre;
    }

    /**
    * set genre
     **/
    public void setGenre(final String genre) {
        this.genre = genre;
    }

    /**
    * get releaseYear
     **/
    public String getReleaseYear() {
        return releaseYear;
    }

    /**
    * set releaseYear
     **/
    public void setReleaseYear(final String releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
    * get artist
     **/
    public String getArtist() {
        return artist;
    }

    /**
    * set artist
     **/
    public void setArtist(final String artist) {
        this.artist = artist;
    }

    /**
    * get owner
     **/
    public String getOwner() {
        return owner;
    }

    /**
    * set owner
     **/
    public void setOwner(final String owner) {
        this.owner = owner;
    }

    /**
    * get followers
     **/
    public String getFollowers() {
        return followers;
    }

    /**
    * set followers
     **/
    public void setFollowers(final String followers) {
        this.followers = followers;
    }

    /**
     * get description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
    * toString
     **/
    @Override
    public String toString() {
        return "FilterInput{"
                +
                ", name='" + name + '\''
                +
                ", album='" + album + '\''
                +
                ", tags=" + tags
                +
                ", lyrics='" + lyrics + '\''
                +
                ", genre='" + genre + '\''
                +
                ", releaseYear='" + releaseYear + '\''
                +
                ", artist='" + artist + '\''
                +
                ", owner='" + owner + '\''
                +
                ", followers='" + followers + '\''
                +
                '}';
    }
}
