package app;

import app.audio.Collections.AlbumSingleton;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Collections.Album;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.Host;
import app.user.User;
import app.user.Artist;
import fileio.input.*;

import java.util.*;

public class Admin {
    private static final AlbumSingleton albumSingleton = AlbumSingleton.getInstance();
    private static List<User> users = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();

    private static int timestamp = 0;

    private static final int MAGIC_NUMBER = 5;

    /**
     * @param userInputList list of users from input
     */
    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }

    /**
     * @return users
     */
    public static List<User> getUsers() {
        return users;
    }

    /**
     * @return albums
     */
    public static ArrayList<Album> getAlbums() {
        return albumSingleton.getAlbums();
    }

    /**
     * set songs
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }

    /**
     * set podcasts
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                        episodeInput.getDuration(), episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * @return songs
     */
    public static List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * @return podcasts
     */
    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * @return timestamp
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * @return timestamp
     */
    public static User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * updated timestamp
     */
    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * @return top 5 songs
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= MAGIC_NUMBER) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * @return top 5 playlists
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= MAGIC_NUMBER) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * @return number of likes for an album
     */
    private static Integer albumLikes(final Album album) {

        Integer likes = 0;

        for (Song song : album.getTracks()) {
            likes += song.getLikes();
        }

        return likes;
    }

    /**
     * @return top 5 albums
     */
    public static ArrayList<String> getTop5Albums() {

        ArrayList<String> topAlbums = new ArrayList<>();
        ArrayList<Integer> topLikes = new ArrayList<>();

        for (User user1: users) {
            if (Artist.class.isAssignableFrom(user1.getClass())) {
                for (Album album: ((Artist) user1).getAlbums()) {
                    topLikes.add(albumLikes(album));
                    topAlbums.add(album.getName());
                }
            }
        }
        // sort by number of likes,and if equal, lexicographically
        // we sort the albums and the likes in the same way
        for (int i = 0; i < topLikes.size() - 1; i++) {
            for (int j = i + 1; j < topLikes.size(); j++) {
                if (topLikes.get(i) < topLikes.get(j)) {
                    Collections.swap(topLikes, i, j);
                    Collections.swap(topAlbums, i, j);
                } else if (topLikes.get(i).equals(topLikes.get(j))) {
                    if (topAlbums.get(i).compareTo(topAlbums.get(j)) > 0) {
                        Collections.swap(topAlbums, i, j);
                    }
                }
            }
        }
        // we return top 5
        if (topAlbums.size() > MAGIC_NUMBER) {
            return new ArrayList<>(topAlbums.subList(0, MAGIC_NUMBER));
        }
        return topAlbums;

    }

    /**
     * @return online users
     */
    public static ArrayList<String> getOnlineUsers() {

        ArrayList<String> userList = new ArrayList<>();

        for (User user : users) {
            if (user.getConnection().equals("online")
                    && !(Artist.class.isAssignableFrom(user.getClass()))
                    && !(Host.class.isAssignableFrom(user.getClass()))) {
                userList.add(user.getUsername());
            }
        }

        return userList;
    }

    /**
     *
     * @param command command input
     * @return message for adding a user
     */
    public static String addUser(final CommandInput command) {

        int ok = 0;

        for (User user : users) {
            if (user.getUsername().equals(command.getUsername())) {
                ok = 1;
                break;
            }
        }
        if (ok == 1) {
            return "The username " + command.getUsername() + " is already taken.";
        }

        if (command.getType().equals("artist")) {
            Artist artist = new Artist(command.getUsername(), command.getAge(), command.getCity());
            users.add(artist);
        } else if (command.getType().equals("host")) {
            User host = new Host(command.getUsername(), command.getAge(), command.getCity());
            users.add(host);
        } else {
            User user = new User(command.getUsername(), command.getAge(), command.getCity());
            users.add(user);
            AlbumSingleton.getInstance().addUser(user);
        }

        return "The username " + command.getUsername() + " has been added successfully.";
    }

    /**
     * @return message for creating an album
     */
    public static String addAlbum(final CommandInput commandInput) {

        ArrayList<Song> songList = new ArrayList<>();
        for (SongInput songInput : commandInput.getSongs()) {
            Song song = new Song(songInput.getName(), songInput.getDuration(),
                    songInput.getAlbum(), songInput.getTags(),
                    songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist());
            songList.add(song);
        }
        for (User user : users) {
            if (user.getUsername().equals(commandInput.getUsername())) {
                if (!(Artist.class.isAssignableFrom(user.getClass()))) {
                    return user.getUsername() + " is not an artist.";
                } else {
                    Album album = new Album(commandInput.getName(), commandInput.getUsername(),
                            commandInput.getReleaseYear(), commandInput.getDescription(), songList);

                    for (Album album1 : ((Artist) user).getAlbums()) {
                        if (album1.getName().equals(commandInput.getName())) {
                            return user.getUsername() + " has another album with the same name.";
                        }
                    }

                    for (int i = 0; i < album.getNumberOfTracks() - 1; i++) {
                        for (int j = i + 1; j < album.getNumberOfTracks(); j++) {
                            if (album.getTrackByIndex(i).getName()
                                    .equals(album.getTrackByIndex(j).getName())) {
                                return user.getUsername()
                                        + " has the same song at least twice in this album.";
                            }
                        }
                    }

                    ((Artist) user).addAlbum(album);
                    Admin.songs.addAll(album.getTracks());
                    albumSingleton.addAlbum(album);
                    return commandInput.getUsername() + " has added new album successfully.";
                }
            }
        }

        return "User not found.";
    }

    /**
     * @return message for creating a podcast
     */
    public static String addPodcast(final CommandInput commandInput) {
        ArrayList<Episode> episodeList = new ArrayList<>();
        for (EpisodeInput episodeInput : commandInput.getEpisodes()) {
            Episode episode = new Episode(episodeInput.getName(),
                    episodeInput.getDuration(), episodeInput.getDescription());
            episodeList.add(episode);
        }
        for (User user : users) {
            if (user.getUsername().equals(commandInput.getUsername())) {
                if (!(Host.class.isAssignableFrom(user.getClass()))) {
                    return user.getUsername() + " is not a host.";
                } else {
                    Podcast podcast = new Podcast(commandInput.getName(),
                            commandInput.getUsername(), episodeList);
                    for (Podcast podcast1 : ((Host) user).getPodcasts()) {
                        if (podcast1.getName().equals(commandInput.getName())) {
                            return user.getUsername() + " has another podcast with the same name.";
                        }
                    }
                    for (int i = 0; i < podcast.getEpisodes().size() - 1; i++) {
                        for (int j = i + 1; j < podcast.getEpisodes().size(); j++) {
                            if (podcast.getEpisodes().get(i).getName()
                                    .equals(podcast.getEpisodes().get(j).getName())) {
                                return user.getUsername()
                                        + " has the same episode in this podcast.";
                            }
                        }
                    }
                    ((Host) user).addPodcast(podcast);
                    // search to see if podcast exists in admin
                    int ok = 0;
                    for (Podcast podcast1 : podcasts) {
                        if (podcast1.getName().equals(podcast.getName())) {
                            ok = 1;
                            break;
                        }
                    }
                    if (ok == 0) {
                        Admin.podcasts.add(podcast);
                    }
                    return commandInput.getUsername() + " has added new podcast successfully.";
                }
            }
        }
        return "User not found.";
    }

    /**
     * @return message for adding all users
     */
    public static ArrayList<String> getAllUsers() {

        ArrayList<String> userList = new ArrayList<>();
        // put normal users first
        for (User user1: users) {
            if (!(Artist.class.isAssignableFrom(user1.getClass()))
                    && !(Host.class.isAssignableFrom(user1.getClass()))) {
                userList.add(user1.getUsername());
            }
        }
        // then artists
        for (User user1: users) {
            if (Artist.class.isAssignableFrom(user1.getClass())) {
                userList.add(user1.getUsername());
            }
        }
        // to be continued
        for (User user: users) {
            if (Host.class.isAssignableFrom(user.getClass())) {
                userList.add(user.getUsername());
            }
        }
        return userList;
    }

    /**
     * we check if we can delete the artist
     * @return bool
     */
    private static boolean checkArtistDelete(final User user) {

        for (User normalUser: users) {
            if (!(Artist.class.isAssignableFrom(normalUser.getClass()))) {
                String type = normalUser.getPlayer().getType();
                if (type != null && (type.equals("playlist")
                        || type.equals("album") || type.equals("song"))) {
                    // check the current track to see if we delete the artist
                    String songName = normalUser.getPlayerStats().getName();
                    for (Song song : songs) {
                        if (song.getName().equals(songName)
                                && song.matchesArtist(user.getUsername())) {
                            return false;
                        }
                    }
                }
            }
            if (normalUser.getPage().equals(user.getUsername())) {
                return false;
            }
        }

        return true;
    }

    /**
     * we check if we can delete the host
     * @return bool
     */
    private static boolean checkHostDelete(final User user) {
        for (User normalUser: users) {
            if (!(Host.class.isAssignableFrom(normalUser.getClass()))) {
                String type = normalUser.getPlayer().getType();
                if (type != null && (type.equals("podcast") || type.equals("episode"))) {
                    // check the current track to see if we delete the host
                    String podcastName = normalUser.getPlayerStats().getName();
                    for (Podcast podcast : podcasts) {
                        if (podcast.getName().equals(podcastName)) {
                            return false;
                        }
                        for (Episode episode : podcast.getEpisodes()) {
                            if (episode.getName().equals(podcastName)) {
                                return false;
                            }
                        }
                    }
                }
            }
            if (normalUser.getPage().equals(user.getUsername())) {
                return false;
            }

        }
        return true;
    }

    /**
     * deleting a liked song
     */
    public static void deleteLikedSong(final User user, final Song song) {
        ArrayList<Song> newLikes = user.getLikedSongs();
        newLikes.remove(song);
        user.setLikedSongs(newLikes);
    }

    /**
     * deleting an album
     */
    public static void deleteAlbum(final Album removeAlbum) {
        List<Song> allSong = new ArrayList<>(songs);

        for (Song song : allSong) {
            if (song.matchesAlbum(removeAlbum.getName())) {
                songs.remove(song);
                for (User user1 : users) {
                    if (user1.getLikedSongs().contains(song)) {
                        deleteLikedSong(user1, song);
                    }
                }

            }
        }

        albumSingleton.removeAlbum(removeAlbum);
    }

    /**
     * delete a playlist by owner
     */
    private static void deletePlaylistByOwner(final String owner) {

        for (User user : users) {
            ArrayList<Playlist> playlists = new ArrayList<>(user.getFollowedPlaylists());

            for (Playlist playlist : user.getFollowedPlaylists()) {
                if (playlist.getOwner().equals(owner)) {
                    playlists.remove(playlist);
                }
            }

            user.setFollowedPlaylists(playlists);
        }

    }

    /**
     * delete a podcast
     */
    public static void deletePodcast(final Podcast podcast) {
        List<Episode> allEpisodes = new ArrayList<>(podcast.getEpisodes());

        for (Episode episode : allEpisodes) {
            int ok = 0;
            for (User user1 : users) {
                if (user1.getPlayer().getType() == null) {
                    continue;
                }
                if (user1.getPlayer().getType().equals("episode")
                        && user1.getPlayerStats().getName().equals(episode.getName())) {
                    podcasts.remove(podcast);
                    ok++;
                    break;
                }
            }
            if (ok == 1) {
                break;
            }
        }


    }

    /**
     * delete user
     * @return message for deleting a user
     */
    public static String deleteUser(final User user) {

        if (Artist.class.isAssignableFrom(user.getClass())) {
            if (!checkArtistDelete(user)) {
                return user.getUsername() + " can't be deleted.";
            } else {
                for (Album album : ((Artist) user).getAlbums()) {
                    deleteAlbum(album);
                }
            }
        } else if (Host.class.isAssignableFrom(user.getClass())) {
            if (!checkHostDelete(user)) {
                return user.getUsername() + " can't be deleted.";
            } else {
                for (Podcast podcast : ((Host) user).getPodcasts()) {
                    deletePodcast(podcast);
                }
            }
        } else {
            // if his playlist is somewhere playing by another user, we don't delete
            ArrayList<Song> likedSongs = user.getLikedSongs();
            ArrayList<Playlist> followedPlaylists = user.getFollowedPlaylists();

            for (Playlist playlist : followedPlaylists) {
                playlist.decreaseFollowers();
            }
            for (Song song : likedSongs) {
                song.dislike();
            }

            deletePlaylistByOwner(user.getUsername());
        }

        users.remove(user);
        AlbumSingleton.getInstance().removeUser(user);
        return user.getUsername() + " was successfully deleted.";
    }

    /**
     * @return message for removing an album
     */
    public static String removeAlbum(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        if (user == null) {
            return "The username " + commandInput.getUsername() + " doesn't exist.";
        }

        Album toBeRemoved = null;
        for (Album album : Admin.getAlbums()) {
            if (commandInput.getName().equals(album.getName())) {
                toBeRemoved = album;
                break;
            }
        }
        if (!(Artist.class.isAssignableFrom(user.getClass()))) {
            return commandInput.getUsername() + " is not an artist.";
        }
        if (toBeRemoved == null) {
            return commandInput.getUsername() + " doesn't have an album with the given name.";
        }
        return ((Artist) user).removeAlbum(toBeRemoved);
    }

    /**
     * @return message for removing a podcast
     */
    public static String removePodcast(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        if (user == null) {
            return "The username " + commandInput.getUsername() + " doesn't exist.";
        }

        Podcast toBeRemoved = null;
        for (Podcast podcast : Admin.getPodcasts()) {
            if (commandInput.getName().equals(podcast.getName())) {
                toBeRemoved = podcast;
                break;
            }
        }
        if (toBeRemoved == null) {
            return commandInput.getUsername() + " doesn't have a podcast with the given name.";
        }
        if (!(Host.class.isAssignableFrom(user.getClass()))) {
            return commandInput.getUsername() + " is not a host.";
        }
        return ((Host) user).removePodcast(toBeRemoved);
    }

    /**
     * show albums by making list of maps
     */
    public static ArrayList<LinkedHashMap<String, Object>> showAlbums(
            final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        assert user != null;
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();
        for (Album album1: ((Artist) user).getAlbums()) {
            ArrayList<String> songs1 = new ArrayList<>();
            for (Song song: album1.getTracks()) {
                songs1.add(song.getName());
            }
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("name", album1.getName());
            map.put("songs", songs1);
            results.add(map);
        }
        return results;
    }

    /**
     * show podcasts by making list of maps
     */
    public static ArrayList<LinkedHashMap<String, Object>> showPodcasts(
            final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        assert user != null;
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>();
        for (Podcast podcast: ((Host) user).getPodcasts()) {
            ArrayList<String> episodes = new ArrayList<>();
            for (Episode episode: podcast.getEpisodes()) {
                episodes.add(episode.getName());
            }
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("name", podcast.getName());
            map.put("episodes", episodes);
            results.add(map);
        }
        return results;
    }

    /**
     * reset our admin
     */
    public static void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        albumSingleton.reset();
        timestamp = 0;
    }

    /**
     * @return hosts
     */
    public static List<User> getHosts() {
        List<User> hosts = new ArrayList<>();
        for (User user: users) {
            if (Host.class.isAssignableFrom(user.getClass())) {
                hosts.add(user);
            }
        }
        return hosts;
    }

    /**
     * @return artists
     */
    public static List<User> getArtists() {
        List<User> artists = new ArrayList<>();
        for (User user: users) {
            if (Artist.class.isAssignableFrom(user.getClass())) {
                artists.add(user);
            }
        }
        return artists;
    }

    /**
     * @return top 5 artists
     */
    public static List<String> getTop5Artists() {
        // calculate the number of likes in each song from every album
        HashMap<String, Integer> artistLikes = new HashMap<String, Integer>();
        ArrayList<Integer> topLikes;
        ArrayList<String> topArtists;
        for (User user1: users) {
            if (Artist.class.isAssignableFrom(user1.getClass())) {
                int likes = 0;
                for (Album album: ((Artist) user1).getAlbums()) {
                    likes += albumLikes(album);
                }
                artistLikes.put(user1.getUsername(), likes);
            }
        }
        topLikes = new ArrayList<Integer>(artistLikes.values());
        topLikes.sort(Collections.reverseOrder());
        topArtists = new ArrayList<String>();
        int count = 0;
        for (Integer index : topLikes) {
            if (count >= MAGIC_NUMBER) {
                break;
            }
            for (String artist: artistLikes.keySet()) {
                if (artistLikes.get(artist).equals(index)) {
                    topArtists.add(artist);
                    count++;
                    break;
                }
            }
        }
        return topArtists;

    }

    /**
     * add a command in singleton
     */
    public static void addCommand(final CommandInput command) {
        albumSingleton.addCommand(command);
    }

    /**
     * remove a command from singleton
     */
    public static void removeCommand(final CommandInput command) {
        albumSingleton.removeCommand(command);
    }
}
