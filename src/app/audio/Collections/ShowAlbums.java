package app.audio.Collections;

import java.util.ArrayList;

public class ShowAlbums {
    private String name;
    private ArrayList<String> songs;

    /**
     * @param name  name of the album
     * @param songs list of songs
     */
    public ShowAlbums(final String name, final ArrayList<String> songs) {
        this.name = name;
        this.songs = songs;
    }

    /**
     * getter for name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for songs
     */
    public ArrayList<String> getSongs() {
        return songs;
    }

    /**
     * setter for name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * setter for songs
     */
    public void setSongs(final ArrayList<String> songs) {
        this.songs = songs;
    }
}
