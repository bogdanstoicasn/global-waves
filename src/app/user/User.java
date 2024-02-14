package app.user;

import app.Admin;
import app.audio.Collections.*;
import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.player.Player;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.searchBar.SearchBar;
import app.utils.Enums;

import java.util.ArrayList;
import java.util.List;

public class User extends LibraryEntry {

    private String username;

    private int age;

    private String city;
    private String page;
    private String connectionStatus;

    private ArrayList<Playlist> playlists;

    private ArrayList<Song> likedSongs;

    private ArrayList<Playlist> followedPlaylists;
    private final Player player;
    private final SearchBar searchBar;
    private boolean lastSearched;

    /**
     * Constructor for User
     * @param username
     * @param age
     * @param city
     */
    public User(final String username, final int age, final String city) {
        super(username);
        this.username = username;
        this.age = age;
        this.city = city;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        lastSearched = false;
        connectionStatus = "online";
        page = "Home";
    }

    /**
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     *
     * @param page set
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     *
     * @return the search bar
     */
    public String getName() {
        return this.username;
    }

    /**
     *
     * @return the search bar
     */
    public int getAge() {
        return this.age;
    }

    /**
     *
     * @return the search bar
     */
    public String getCity() {
        return this.city;
    }

    /**
     *
     * @set the name
     */
    public void setName(final String name) {
        this.username = name;
    }

    /**
     *
     * set age
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     *
     * set city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     *
     * @return connection status
     */
    public String getConnection() {
        return connectionStatus;
    }

    /**
     *
     * @param connection set
     */
    public void setConnection(final String connection) {
        this.connectionStatus = connection;
    }

    /**
     *
     * @return the search bar
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return liked songs
     */
    public ArrayList<Song> getLikedSongs() {
        return likedSongs;
    }

    /**
     *
     * @return the playlists
     */
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     *
     * set the playlists
     */
    public void setPlaylists(final ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     *
     * @return the followed playlists
     */
    public ArrayList<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }

    /**
     *
     * @param followedPlaylists set
     */
    public void setFollowedPlaylists(final ArrayList<Playlist> followedPlaylists) {
        this.followedPlaylists = followedPlaylists;
    }

    /**
     *
     * set the liked songs
     */
    public void setLikedSongs(final ArrayList<Song> likedSongs) {
        this.likedSongs = likedSongs;
    }

    /**
     *
     * search for anything
     */
    public ArrayList<String> search(final Filters filters, final String type) {
        searchBar.clearSelection();
        player.stop();

        lastSearched = true;
        ArrayList<String> results = new ArrayList<>();
        List<LibraryEntry> libraryEntries = searchBar.search(filters, type);

        for (LibraryEntry libraryEntry : libraryEntries) {
            results.add(libraryEntry.getName());
        }
        return results;
    }

    /**
     *
     * @param itemNumber the item number
     * @return the selected item
     */
    public String select(final int itemNumber) {
        if (!lastSearched) {
            return "Please conduct a search before making a selection.";
        }

        lastSearched = false;

        LibraryEntry selected = searchBar.select(itemNumber);

        if (selected == null) {
            return "The selected ID is too high.";
        }

        if (searchBar.getLastSearchType().equals("artist")) {
            page = ((User) selected).getUsername();
            return "Successfully selected " + page + "'s page.";
        }
        if (searchBar.getLastSearchType().equals("host")) {
            page = ((Host) selected).getUsername();
            return "Successfully selected " + page + "'s page.";
        }
        return "Successfully selected %s.".formatted(selected.getName());
    }

    /**
     * load the selected item
     */
    public String load() {
        if (searchBar.getLastSelected() == null) {
            return "Please select a source before attempting to load.";
        }

        if (!searchBar.getLastSearchType().equals("song")
                && ((AudioCollection) searchBar.getLastSelected()).getNumberOfTracks() == 0) {
            return "You can't load an empty audio collection!";
        }

        player.setSource(searchBar.getLastSelected(), searchBar.getLastSearchType());
        searchBar.clearSelection();

        player.pause();

        return "Playback loaded successfully.";
    }

    /**
     * play/pause the current item
     */
    public String playPause() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to pause or resume playback.";
        }

        player.pause();

        if (player.getPaused()) {
            return "Playback paused successfully.";
        } else {
            return "Playback resumed successfully.";
        }
    }

    /**
     * repeat the current item
     */
    public String repeat() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before setting the repeat status.";
        }

        Enums.RepeatMode repeatMode = player.repeat();
        String repeatStatus = "";

        switch (repeatMode) {
            case NO_REPEAT:
                repeatStatus = "no repeat";
                break;
            case REPEAT_ONCE:
                repeatStatus = "repeat once";
                break;
            case REPEAT_ALL:
                repeatStatus = "repeat all";
                break;
            case REPEAT_INFINITE:
                repeatStatus = "repeat infinite";
                break;
            case REPEAT_CURRENT_SONG:
                repeatStatus = "repeat current song";
                break;
        }

        return "Repeat mode changed to %s.".formatted(repeatStatus);
    }

    /**
     * shuffle the current item
     */
    public String shuffle(final Integer seed) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before using the shuffle function.";
        }

        if (!player.getType().equals("playlist") && !player.getType().equals("album")) {
            return "The loaded source is not a playlist or an album.";
        }

        player.shuffle(seed);

        if (player.getShuffle()) {
            return "Shuffle function activated successfully.";
        }
        return "Shuffle function deactivated successfully.";
    }

    /**
     * forward the current item
     */
    public String forward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to forward.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipNext();

        return "Skipped forward successfully.";
    }

    /**
     * backward the current item
     */
    public String backward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please select a source before rewinding.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipPrev();

        return "Rewound successfully.";
    }

    /**
     * like loaded item
     */
    public String like() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before liking or unliking.";
        }

        if (!player.getType().equals("song")
                && !player.getType().equals("playlist") && !player.getType().equals("album")) {
            return "Loaded source is not a song.";
        }

        if (connectionStatus.equals("offline")) {
            return username + " is offline.";
        }

        Song song = (Song) player.getCurrentAudioFile();

        if (likedSongs.contains(song)) {
            likedSongs.remove(song);
            song.dislike();

            return "Unlike registered successfully.";
        }

        likedSongs.add(song);
        song.like();
        return "Like registered successfully.";
    }

    /**
     * next item
     */
    public String next() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        player.next();

        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        return "Skipped to next track successfully. The current track is %s."
                .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     * previous item
     */
    public String prev() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before returning to the previous track.";
        }

        player.prev();

        return "Returned to previous track successfully. The current track is %s."
                .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     * stop the current item
     */
    public String createPlaylist(final String name, final int timestamp) {
        if (playlists.stream().anyMatch(playlist -> playlist.getName().equals(name))) {
            return "A playlist with the same name already exists.";
        }

        playlists.add(new Playlist(name, username, timestamp));

        return "Playlist created successfully.";
    }

    /**
     * remove/add in playlist
     */
    public String addRemoveInPlaylist(final int id) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before adding to or removing from the playlist.";
        }

        if (player.getType().equals("podcast")) {
            return "The loaded source is not a song.";
        }

        if (id > playlists.size()) {
            return "The specified playlist does not exist.";
        }

        Playlist playlist = playlists.get(id - 1);

        if (playlist.containsSong((Song) player.getCurrentAudioFile())) {
            playlist.removeSong((Song) player.getCurrentAudioFile());
            return "Successfully removed from playlist.";
        }

        playlist.addSong((Song) player.getCurrentAudioFile());
        return "Successfully added to playlist.";
    }

    /**
     * switch visibility
     */
    public String switchPlaylistVisibility(final Integer playlistId) {
        if (playlistId > playlists.size()) {
            return "The specified playlist ID is too high.";
        }

        Playlist playlist = playlists.get(playlistId - 1);
        playlist.switchVisibility();

        if (playlist.getVisibility() == Enums.Visibility.PUBLIC) {
            return "Visibility status updated successfully to public.";
        }

        return "Visibility status updated successfully to private.";
    }

    /**
     * show playlists
     */
    public ArrayList<PlaylistOutput> showPlaylists() {
        ArrayList<PlaylistOutput> playlistOutputs = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistOutputs.add(new PlaylistOutput(playlist));
        }

        return playlistOutputs;
    }

    /**
     * follow/unfollow
     */
    public String follow() {
        LibraryEntry selection = searchBar.getLastSelected();
        String type = searchBar.getLastSearchType();

        if (selection == null) {
            return "Please select a source before following or unfollowing.";
        }

        if (!type.equals("playlist")) {
            return "The selected source is not a playlist.";
        }

        Playlist playlist = (Playlist) selection;

        if (playlist.getOwner().equals(username)) {
            return "You cannot follow or unfollow your own playlist.";
        }

        if (followedPlaylists.contains(playlist)) {
            followedPlaylists.remove(playlist);
            playlist.decreaseFollowers();

            return "Playlist unfollowed successfully.";
        }

        followedPlaylists.add(playlist);
        playlist.increaseFollowers();


        return "Playlist followed successfully.";
    }

    /**
     * get the player stats
     * @return the player stats
     */
    public PlayerStats getPlayerStats() {
        return player.getStats();
    }

    /**
     * @return the preferred songs
     */
    public ArrayList<String> showPreferredSongs() {
        ArrayList<String> results = new ArrayList<>();
        for (AudioFile audioFile : likedSongs) {
            results.add(audioFile.getName());
        }

        return results;
    }

    /**
     * @return the preferred genres
     */
    public String getPreferredGenre() {
        String[] genres = {"pop", "rock", "rap"};
        int[] counts = new int[genres.length];
        int mostLikedIndex = -1;
        int mostLikedCount = 0;

        for (Song song : likedSongs) {
            for (int i = 0; i < genres.length; i++) {
                if (song.getGenre().equals(genres[i])) {
                    counts[i]++;
                    if (counts[i] > mostLikedCount) {
                        mostLikedCount = counts[i];
                        mostLikedIndex = i;
                    }
                    break;
                }
            }
        }

        String preferredGenre = mostLikedIndex != -1 ? genres[mostLikedIndex] : "unknown";
        return "This user's preferred genre is %s.".formatted(preferredGenre);
    }

    /**
     * simulate time
     * @param time the time
     */
    public void simulateTime(final int time) {
        if (connectionStatus.equals("online")) {
            player.simulatePlayer(time);
        }
    }

    /**
     * switch connection status
     * @return message
     */
    public  String switchConnectionStatus() {

        if (connectionStatus.equals("online")) {
            setConnection("offline");
        } else {
            setConnection("online");
        }

        return username + " has changed status successfully.";
    }
    private static final int MAGIC_NUMBER = 5;
    /**
     * print the current page
     * @return the page
     */
    public String printCurrentPage() {

        if (connectionStatus.equals("offline")) {
            return username + " is offline.";
        }

        if (page.equals("Home")) {
            StringBuilder result = new StringBuilder("Liked songs:\n\t[");
            int ok = 0;
            // sort the songs in a different array by number of likes from best to worst
            ArrayList<Song> sortedSongs = new ArrayList<>(likedSongs);
            sortedSongs.sort((o1, o2) -> {
                if (o1.getLikes() > o2.getLikes()) {
                    return -1;
                } else if (o1.getLikes() < o2.getLikes()) {
                    return 1;
                } else {
                    return 0;
                }
            });
            for (int i = 0; i < Math.min(MAGIC_NUMBER, sortedSongs.size()); i++) {
                Song song = sortedSongs.get(i);
                int n = Math.min(MAGIC_NUMBER, sortedSongs.size());
                if (i == n - 1) { // Check if it's the last song
                    result.append(song.getName()).append("]");
                } else {
                    result.append(song.getName()).append(", ");
                }

                ok++;
            }

            if (ok == 0) {
                result.append("]");
            }
            ok = 0;
            result.append("\n\n").append("Followed playlists:\n\t[");
            for (Playlist playlist : followedPlaylists) {
                if (followedPlaylists.size() - 1 == followedPlaylists.indexOf(playlist)) {
                    result.append(playlist.getName()).append("]");
                } else {
                    result.append(playlist.getName()).append(", ");
                }
                ok++;
            }
            if (ok == 0) {
                result.append("]");
            }
            return result.toString();
        } else if (page.equals("LikedContent")) {
            StringBuilder result = new StringBuilder("Liked songs:\n\t[");
            int ok = 0;
            for (Song song : likedSongs) {
                if (likedSongs.size() - 1 == likedSongs.indexOf(song)) {
                    result.append(song.getName()).append(" - ")
                            .append(song.getArtist()).append("]");
                } else {
                    result.append(song.getName()).append(" - ")
                            .append(song.getArtist()).append(", ");
                }
                ok++;
            }
            if (ok == 0) {
                result.append("]");
            }
            ok = 0;
            result.append("\n\n").append("Followed playlists:\n\t[");
            for (Playlist playlist : followedPlaylists) {
                if (followedPlaylists.size() - 1 == followedPlaylists.indexOf(playlist)) {
                    result.append(playlist.getName()).append(" - ")
                            .append(playlist.getOwner()).append("]");
                } else {
                    result.append(playlist.getName()).append(" - ")
                            .append(playlist.getOwner()).append(", ");
                }
                ok++;
            }
            if (ok == 0) {
                result.append("]");
            }
            return result.toString();
        } else {

            User user = Admin.getUser(page);

            if (Artist.class.isAssignableFrom(user.getClass())) {
                StringBuilder result = new StringBuilder("Albums:\n\t[");
                int ok = 0;
                for (Album album : ((Artist) user).getAlbums()) {
                    if (((Artist) user).getAlbums().size() - 1
                            == ((Artist) user).getAlbums().indexOf(album)) {
                        result.append(album.getName()).append("]");
                    } else {
                        result.append(album.getName()).append(", ");
                    }
                    ok++;
                }
                if (ok == 0) {
                    result.append("]");
                }
                ok = 0;
                result.append("\n\n").append("Merch:\n\t[");
                for (Merch merch : ((Artist) user).getMerch()) {
                    if (((Artist) user).getMerch().size() - 1
                            == ((Artist) user).getMerch().indexOf(merch)) {
                        result.append(merch.getName()).append(" - ")
                                .append(merch.getPrice()).append(":\n\t")
                                .append(merch.getDescription()).append("]");
                    } else {
                        result.append(merch.getName())
                                .append(" - ").append(merch.getPrice()).append(":\n\t")
                                .append(merch.getDescription()).append(", ");
                    }
                    ok++;
                }
                if (ok == 0) {
                    result.append("]");
                }
                ok = 0;
                result.append("\n\n").append("Events:\n\t[");
                for (Event event : ((Artist) user).getEvents()) {
                    if (((Artist) user).getEvents().size() - 1
                            == ((Artist) user).getEvents().indexOf(event)) {
                        result.append(event.getName())
                                .append(" - ").append(event.getDate()).append(":\n\t")
                                .append(event.getDescription()).append("]");
                    } else {
                        result.append(event.getName()).append(" - ")
                                .append(event.getDate()).append(":\n\t")
                                .append(event.getDescription()).append(", ");
                    }
                    ok++;
                }
                if (ok == 0) {
                    result.append("]");
                }
                return result.toString();
            }

            if (Host.class.isAssignableFrom(user.getClass())) {
                StringBuilder result = new StringBuilder("Podcasts:\n\t[");
                int ok = 0;
                for (Podcast podcast : ((Host) user).getPodcasts()) {
                    if (((Host) user).getPodcasts().size() - 1
                            == ((Host) user).getPodcasts().indexOf(podcast)) {
                        result.append(podcast.getName()).append(":\n\t[");
                        for (Episode episode : podcast.getEpisodes()) {
                            if (podcast.getEpisodes().size() - 1
                                    == podcast.getEpisodes().indexOf(episode)) {
                                result.append(episode.getName())
                                        .append(" - ").append(episode.getDescription())
                                        .append("]\n");
                            } else {
                                result.append(episode.getName())
                                        .append(" - ").append(episode.getDescription())
                                        .append(", ");
                            }
                        }
                        result.append("]");
                    } else {
                        result.append(podcast.getName()).append(":\n\t[");
                        for (Episode episode : podcast.getEpisodes()) {
                            if (podcast.getEpisodes().size() - 1
                                    == podcast.getEpisodes().indexOf(episode)) {
                                result.append(episode.getName())
                                        .append(" - ").append(episode.getDescription())
                                        .append("]\n, ");
                            } else {
                                result.append(episode.getName()).append(" - ")
                                        .append(episode.getDescription()).append(", ");
                            }
                        }
                    }
                    ok++;
                }
                if (ok == 0) {
                    result.append("]");
                }
                ok = 0;
                result.append("\n\n").append("Announcements:\n\t[");
                for (Announcement announcement : ((Host) user).getAnnouncements()) {
                    if (((Host) user).getAnnouncements().size() - 1
                            == ((Host) user).getAnnouncements().indexOf(announcement)) {
                        result.append(announcement.getName()).append(":\n\t")
                                .append(announcement.getDescription())
                                .append("\n]");
                    } else {
                        result.append(announcement.getName()).append(":\n\t")
                                .append(announcement.getDescription()).append(", ");
                    }
                    ok++;
                }
                if (ok == 0) {
                    result.append("]");
                }

                return result.toString();
            }

        }
        return "";
    }

    /**
     * change page
     * @param page1 the page
     * @return message
     */
    public String changePage(final String page1) {
        this.page = page1;
        return getName() + " accessed " + page1 + " successfully.";
    }
}
