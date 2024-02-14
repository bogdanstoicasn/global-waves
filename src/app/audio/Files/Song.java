package app.audio.Files;


import java.util.ArrayList;
import java.util.List;

public final class Song extends AudioFile {
    private final String album;
    private final ArrayList<String> tags;
    private final String lyrics;
    private final String genre;
    private final Integer releaseYear;
    private final String artist;
    private Integer likes;

    /**
     * Constructor with parameters
     * @param name
     * @param duration
     * @param album
     * @param tags
     * @param lyrics
     * @param genre
     * @param releaseYear
     * @param artist
     */
    public Song(final String name, final Integer duration, final String album,
                final ArrayList<String> tags, final String lyrics,
                final String genre, final Integer releaseYear, final String artist) {
        super(name, duration);
        this.album = album;
        this.tags = tags;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.likes = 0;
    }
    // getters and setters(don't have setters because the fields are final)

    /**
     * @return album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @return tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * @return lyrics
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @return releaseYear
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * @return artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @return likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * @param album1
     * @return true if the album matches
     */
    @Override
    public boolean matchesAlbum(final String album1) {
        return this.getAlbum().equalsIgnoreCase(album1);
    }

    /**
     * @param tags1
     * @return true if the tags match
     */
    @Override
    public boolean matchesTags(final ArrayList<String> tags1) {
        List<String> songTags = new ArrayList<>();
        for (String tag : this.getTags()) {
            songTags.add(tag.toLowerCase());
        }

        for (String tag : tags1) {
            if (!songTags.contains(tag.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param lyrics1
     * @return true if the lyrics match
     */
    @Override
    public boolean matchesLyrics(final String lyrics1) {
        return this.getLyrics().toLowerCase().contains(lyrics1.toLowerCase());
    }

    /**
     * @param genre1
     * @return true if the genre matches
     */
    @Override
    public boolean matchesGenre(final String genre1) {
        return this.getGenre().equalsIgnoreCase(genre1);
    }

    /**
     * @param artist1
     * @return true if the artist matches
     */
    @Override
    public boolean matchesArtist(final String artist1) {
        return this.getArtist().equalsIgnoreCase(artist1);
    }

    /**
     * @param releaseYear1
     * @return true if the release year matches
     */
    @Override
    public boolean matchesReleaseYear(final String releaseYear1) {
        return filterByYear(this.getReleaseYear(), releaseYear1);
    }

    /**
     * filters by year
     */
    private static boolean filterByYear(final int year1, final String query1) {
        if (query1.startsWith("<")) {
            return year1 < Integer.parseInt(query1.substring(1));
        } else if (query1.startsWith(">")) {
            return year1 > Integer.parseInt(query1.substring(1));
        } else {
            return year1 == Integer.parseInt(query1);
        }
    }

    /**
     * increments the likes
     */
    public void like() {
        likes++;
    }

    /**
     * decrements the likes
     */
    public void dislike() {
        likes--;
    }
}
