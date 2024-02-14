package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.utils.Enums;

import java.util.ArrayList;

public class Playlist extends AudioCollection {
    private final ArrayList<Song> songs;
    private Enums.Visibility visibility;
    private Integer followers;
    private int timestamp;

    /**
     * Constructor for Playlist
     * @param name name of the playlist
     * @param owner owner of the playlist
     */
    public Playlist(final String name, final String owner) {
        this(name, owner, 0);
    }
    /**
     * Constructor for Playlist
     * @param name name of the playlist
     * @param owner owner of the playlist
     * @param timestamp timestamp of the playlist
     */
    public Playlist(final String name, final String owner, final int timestamp) {
        super(name, owner);
        this.songs = new ArrayList<>();
        this.visibility = Enums.Visibility.PUBLIC;
        this.followers = 0;
        this.timestamp = timestamp;
    }
    // getters and setters

    /**
     * Getter for songs
     * @return songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
    /**
     * Setter for visibility
     * @param visibility visibility of the playlist
     */
    public void setVisibility(final Enums.Visibility visibility) {
        this.visibility = visibility;
    }
    /**
     * Setter for followers
     * @param followers followers of the playlist
     */
    public void setFollowers(final Integer followers) {
        this.followers = followers;
    }
    /**
     * Setter for timestamp
     * @param timestamp timestamp of the playlist
     */
    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }
    /**
     * Getter for visibility
     * @return visibility
     */
    public Enums.Visibility getVisibility() {
        return visibility;
    }
    /**
     * Getter for followers
     * @return followers
     */
    public Integer getFollowers() {
        return followers;
    }
    /**
     * Getter for timestamp
     * @return timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    // methods

    /**
     * Checks if the playlist contains a song
     * @param song song to be checked
     * @return true if the playlist contains the song, false otherwise
     */
    public boolean containsSong(final Song song) {
        return songs.contains(song);
    }
    /**
     * Adds a song to the playlist
     * @param song song to be added
     */
    public void addSong(final Song song) {
        songs.add(song);
    }
    /**
     * Removes a song from the playlist
     * @param song song to be removed
     */
    public void removeSong(final Song song) {
        songs.remove(song);
    }
    /**
     * Removes a song from the playlist
     * @param index index of the song to be removed
     */
    public void removeSong(final int index) {
        songs.remove(index);
    }
    /**
     * Switches the visibility of the playlist
     */
    public void switchVisibility() {
        if (visibility == Enums.Visibility.PUBLIC) {
            visibility = Enums.Visibility.PRIVATE;
        } else {
            visibility = Enums.Visibility.PUBLIC;
        }
    }
    /**
     * Increases the number of followers of the playlist
     */
    public void increaseFollowers() {
        followers++;
    }
    /**
     * Decreases the number of followers of the playlist
     */
    public void decreaseFollowers() {
        followers--;
    }
    /**
     * @return number of songs in the playlist
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }
    /**
     * @param index index of the song
     * @return song at the given index
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }
    /**
     * @return songs in the playlist
     */
    public ArrayList<Song> getTracks() {
        return songs;
    }
    /**
     * @return string representation of the playlist
     */
    @Override
    public boolean isVisibleToUser(final String user) {
        return this.getVisibility() == Enums.Visibility.PUBLIC
                ||
                (this.getVisibility() == Enums.Visibility.PRIVATE && this.getOwner().equals(user));
    }

    /**
     *
     * @param followers string
     * @return filters the playlist by followers
     */
    @Override
    public boolean matchesFollowers(final String followers) {
        return filterByFollowersCount(this.getFollowers(), followers);
    }

    /**
     *
     * @param count int
     * @param query string
     * @return filters by count
     */
    private static boolean filterByFollowersCount(final int count, final String query) {
        if (query.startsWith("<")) {
            return count < Integer.parseInt(query.substring(1));
        } else if (query.startsWith(">")) {
            return count > Integer.parseInt(query.substring(1));
        } else {
            return count == Integer.parseInt(query);
        }
    }
}
