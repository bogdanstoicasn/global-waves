package app.audio.Collections;

import app.user.User;
import fileio.input.CommandInput;

import java.util.ArrayList;

public final class AlbumSingleton {

    private static ArrayList<Album> albums;
    private static ArrayList<CommandInput> commands;

    private static ArrayList<User> users;
    private static final AlbumSingleton albumSingleton = new AlbumSingleton();

    /**
     * constructor
     */
    private AlbumSingleton() {
        albums = new ArrayList<>();
        commands = new ArrayList<>();
        users = new ArrayList<>();
    }

    /**
     *
     * @param album add album to the list
     */
    public void addAlbum(Album album) {
        albums.add(album);
    }

    /**
     *
     * @param command add command to the list
     */
    public void addCommand(final CommandInput command) {
        commands.add(command);
    }

    /**
     *
     * @return the list of commands
     */
    public ArrayList<CommandInput> getCommands() {
        return commands;
    }

    /**
     * reset the list of albums and commands
     */
    public void reset() {
        albums = new ArrayList<>();
        commands = new ArrayList<>();
        users = new ArrayList<>();
    }

    /**
     *
     * @param album remove album from the list
     */
    public void removeAlbum(final Album album) {
        albums.remove(album);
    }

    /**
     *
     * @param command remove command from the list
     */
    public void removeCommand(final CommandInput command) {
        commands.remove(command);
    }

    /**
     *
     * @return the list of albums
     */
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    /**
     *
     * @return the instance of the singleton
     */
    public static AlbumSingleton getInstance() {
        return albumSingleton;
    }

    /**
     * @param user add user to the list
     */
    public void addUser(final User user) {
        users.add(user);
    }

    /**
     * @return the list of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @param user remove user from the list
     */
    public void removeUser(final User user) {
        users.remove(user);
    }

}