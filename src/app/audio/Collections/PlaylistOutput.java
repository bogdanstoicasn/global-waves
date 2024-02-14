package app.audio.Collections;

import app.utils.Enums;

import java.util.ArrayList;


public class PlaylistOutput {
    private final String name;
    private final ArrayList<String> songs;
    private final String visibility;
    private final int followers;
//    private final int timestamp;

    /**
     * Constructor
     * @param playlist
     */
    public PlaylistOutput(final Playlist playlist) {
        this.name = playlist.getName();
        this.songs = new ArrayList<>();
        for (int i = 0; i < playlist.getSongs().size(); i++) {
            songs.add(playlist.getSongs().get(i).getName());
        }
        this.visibility = playlist.getVisibility()
                == Enums.Visibility.PRIVATE ? "private" : "public";
        this.followers = playlist.getFollowers();
//        this.timestamp = playlist.getTimestamp();
    }

    /**
     * Getters
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getters
     * @return songs
     */
    public ArrayList<String> getSongs() {
        return songs;
    }

    /**
     * Getters
     * @return visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Getters
     * @return followers
     */
    public int getFollowers() {
        return followers;
    }


}
