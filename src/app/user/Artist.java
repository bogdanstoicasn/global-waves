package app.user;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.AlbumSingleton;
import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.player.PlayerStats;
import fileio.input.CommandInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Artist extends User {

    private final ArrayList<Album> albums;
    private final ArrayList<Event> events;
    private final ArrayList<Merch> merch;

    /**
     * Constructor for Artist
     * @param username
     * @param age
     * @param city
     */
    public Artist(final String username, final int age, final String city) {
        super(username, age, city);
        albums = new ArrayList<>();
        events = new ArrayList<>();
        merch = new ArrayList<>();
    }

    /**
    * @return the albums
     */
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    /**
     * @return the events
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * @return the merch
     */
    public ArrayList<Merch> getMerch() {
        return merch;
    }

    /**
     * @param album
     */
    public void addAlbum(final Album album) {
        albums.add(album);
    }

    /**
     * @param album the album to be removed
     */
    private boolean checkAlbumDelete(final Album album) {

        for (User normalUser : Admin.getUsers()) {
            if (!(Artist.class.isAssignableFrom(normalUser.getClass()))) {

                PlayerStats stats = normalUser.getPlayerStats();
                String type = normalUser.getPlayer().getType();

                if (type != null && (type.equals("song")
                        || type.equals("playlist") || type.equals("album"))) {
                    String songName = stats.getName();
                    for (Song song : Admin.getSongs()) {
                        if (song.getName().equals(songName) && song.matchesAlbum(album.getName())) {
                            return false;
                        }
                    }

                    for (Playlist playlist : Admin.getPlaylists()) {
                        if (playlist.getName().equals(songName)
                                && playlist.matchesAlbum(album.getName())) {
                            return false;
                        }
                    }

                    for (User check : Admin.getUsers()) {
                        if (Artist.class.isAssignableFrom(check.getClass())) {
                            Artist artist = (Artist) check;
                            for (Album checkAlbum : artist.getAlbums()) {
                                if (checkAlbum.getName().equals(album.getName())) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;

    }

    /**
     * @param album the album to be removed
     * @return the result of the command
     */
    public String removeAlbum(final Album album) {

        if (checkAlbumDelete(album)) {
            Admin.deleteAlbum(album);
            albums.remove(album);
            AlbumSingleton.getInstance().removeAlbum(album);
            return "Album deleted.";
        }

        return getUsername() + " can't delete this album.";

    }

    private static final int MAGIC_DAY = 31;
    private static final int MAGIC_MONTH = 12;
    private static final int MAGIC_YEAR = 1900;
    private static final int MAGIC_YEAR2 = 2023;

    /**
     *
     * @param inputDate the date to be validated
     * @return the result of the validation
     */
    public static String validateAndHandleDate(final String inputDate) {
        try {
            // set the date format
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            // set lenient to false for strict validation
            sdf.setLenient(false);

            // parse the date
            Date parsedDate = sdf.parse(inputDate);

            // additional validation for day, month, and year
            int day = Integer.parseInt(new SimpleDateFormat("dd").format(parsedDate));
            int month = Integer.parseInt(new SimpleDateFormat("MM").format(parsedDate));
            int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(parsedDate));

            if (day < 1 || day > MAGIC_DAY || month < 1
                    || month > MAGIC_MONTH || year < MAGIC_YEAR || year > MAGIC_YEAR2) {
                return "error";
            }

            return "Data is valid!";
        } catch (ParseException e) {
            return "error";
        }
    }

    /**
     * @param commandInput the command to be executed
     * @return the result of the command
     */
    public String addEvent(final CommandInput commandInput) {
        String date = validateAndHandleDate(commandInput.getDate());
        if (date.equals("error")) {
            return "Event for " + commandInput.getUsername() + " does not have a valid date.";
        }
        for (Event event : events) {
            if (event == null) {
                continue;
            }
            if (event.getName().equals(commandInput.getName())) {
                return getUsername() + " has already added an event with this name.";
            }
        }
        Event event = new Event(commandInput.getName(),
                commandInput.getDescription(), commandInput.getDate());
        events.add(event);
        return getUsername() + " has added new event successfully.";
    }

    /**
     * @param commandInput the command to be executed
     * @return the result of the command
     */
    public String addMerch(final CommandInput commandInput) {

        if (commandInput.getPrice() < 0) {
            return "Price for merchandise can not be negative.";
        }

        for (Merch merch1 : merch) {
            if (merch1.getName().equals(commandInput.getName())) {
                return getUsername() + " has merchandise with the same name.";
            }
        }

        Merch newMerch = new Merch(commandInput.getName(),
                commandInput.getDescription(), commandInput.getPrice());
        merch.add(newMerch);

        return getUsername() + " has added new merchandise successfully.";

    }

    /**
     * @param commandInput the command to be executed
     * @return the result of the command
     */
    public String removeEvent(final CommandInput commandInput) {
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (event.getName().equals(commandInput.getName())) {
                iterator.remove();
                return getUsername() + " deleted the event successfully.";
            }
        }
        return getUsername() + " doesn't have an event with the given name.";
    }
}
