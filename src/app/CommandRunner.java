package app;

import app.audio.Collections.PlaylistOutput;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Host;
import app.user.User;
import app.user.Artist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CommandRunner {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param commandInput command
     * @return buildObjectNode
     */
    public static ObjectNode buildObjectNode(final CommandInput commandInput,
                                             final String message) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode
     */
    public static ObjectNode buildObjectNodeInverse(final CommandInput commandInput,
                                                    final String message) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for search
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        Filters filters = new Filters(commandInput.getFilters());
        ArrayList<String> results = new ArrayList<>();
        String message;
        String type = commandInput.getType();

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        if (user.getConnection().equals("offline")) {
            message = user.getUsername() + " is offline.";
        } else {
            results = user.search(filters, type);
            message = "Search returned " + results.size() + " results";
        }

        ObjectNode objectNode = buildObjectNode(commandInput, message);
        objectNode.put("results", objectMapper.valueToTree(results));
        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for select
     */
    public static ObjectNode select(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.select(commandInput.getItemNumber());

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for load
     */
    public static ObjectNode load(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.load();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for play/stop
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.playPause();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for repeat
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.repeat();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for shuffle
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNodeInverse(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        Integer seed = commandInput.getSeed();
        String message = user.shuffle(seed);

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for forward
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.forward();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for backward
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.backward();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for like
     */
    public static ObjectNode like(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.like();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for next
     */
    public static ObjectNode next(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.next();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for prev
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.prev();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for create playlist
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }
        String message = user.createPlaylist(commandInput.getPlaylistName(),
                commandInput.getTimestamp());

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for add/remove song
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for switch visibility
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for show playlist
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for follow
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String message = user.follow();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for status
     */
    public static ObjectNode status(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        PlayerStats stats = user.getPlayerStats();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for show liked songs
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for show preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for get top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for get top 5 albums
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        List<String> albums = Admin.getTop5Albums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for get top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for switch connection status
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message;

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }
        if (Artist.class.isAssignableFrom(user.getClass())
                || Host.class.isAssignableFrom(user.getClass())) {
            return buildObjectNode(commandInput, user.getUsername()
                    + " is not a normal user.");
        }
        message = user.switchConnectionStatus();

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for get online users
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        List<String> onlineUsers = Admin.getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for get all users
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        List<String> users = Admin.getAllUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(users));

        return objectNode;
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for add an user
     */
    public static ObjectNode addUser(final CommandInput commandInput) {

        String message = Admin.addUser(commandInput);
        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for add an album
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {

        String message = Admin.addAlbum(commandInput);

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for show albums
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>(
                Admin.showAlbums(commandInput));

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(results));

        return objectNode;

    }

    /**
     * @param commandInput command
     * @return buildObjectNode for print current page
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {

        User user = Admin.getUser(commandInput.getUsername());
        String message;

        if (user == null) {
            return buildObjectNodeInverse(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        message = user.printCurrentPage();
        return buildObjectNodeInverse(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for change page
     */
    public static ObjectNode changePage(final CommandInput commandInput) {

        User user = Admin.getUser(commandInput.getUsername());
        String message;

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }

        message = user.changePage(commandInput.getNextPage());

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for add event
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {

        User user = Admin.getUser(commandInput.getUsername());
        String message;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (!(Artist.class.isAssignableFrom(user.getClass()))) {
            message = user.getUsername() + " is not an artist.";
        } else {
            message = ((Artist) user).addEvent(commandInput);
        }

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for add merch
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {

        User user = Admin.getUser(commandInput.getUsername());
        String message;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (!(Artist.class.isAssignableFrom(user.getClass()))) {
            message = user.getUsername() + " is not an artist.";
        } else {
            message = ((Artist) user).addMerch(commandInput);
        }

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for delete user
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message;

        if (user == null) {
            return buildObjectNode(commandInput, "The username "
                    + commandInput.getUsername() + " doesn't exist.");
        }
        message = Admin.deleteUser(user);

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for remove album
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {

        String message = Admin.removeAlbum(commandInput);

        return buildObjectNode(commandInput, message);

    }

    /**
     * @param command command
     * @return buildObjectNode add announcement
     */
    public static  ObjectNode addAnnouncement(final CommandInput command) {
        User user = Admin.getUser(command.getUsername());
        if (user == null) {
            return buildObjectNode(command, "The username "
                    + command.getUsername() + " doesn't exist.");
        } else if (!(Host.class.isAssignableFrom(user.getClass()))) {
            return buildObjectNode(command, user.getUsername() + " is not a host.");
        } else {
            return buildObjectNode(command, ((Host) user).addAnnouncement(command));
        }
    }

    /**
     * @param command command
     * @return buildObjectNode remove announcement
     */
    public static ObjectNode removeAnnouncement(final CommandInput command) {
        User user = Admin.getUser(command.getUsername());
        if (user == null) {
            return buildObjectNode(command, "The username "
                    + command.getUsername() + " doesn't exist.");
        } else if (!(Host.class.isAssignableFrom(user.getClass()))) {
            return buildObjectNode(command, user.getUsername() + " is not a host.");
        } else {
            return buildObjectNode(command, ((Host) user).removeAnnouncement(command.getName()));
        }
    }

    /**
     * @param command command
     * @return buildObjectNode for add podcast
     */
    public static ObjectNode addPodcast(final CommandInput command) {
        User user = Admin.getUser(command.getUsername());
        if (user == null) {
            return buildObjectNode(command, "The username "
                    + command.getUsername() + " doesn't exist.");
        }
        String message = Admin.addPodcast(command);
        return buildObjectNode(command, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for show podcasts
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        ArrayList<LinkedHashMap<String, Object>> results = new ArrayList<>(
                Admin.showPodcasts(commandInput));

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(results));

        return objectNode;
    }

    /**
     * @param command command
     * @return buildObjectNode for remove podcast
     */
    public static ObjectNode removePodcast(final CommandInput command) {
        String message = Admin.removePodcast(command);
        return buildObjectNode(command, message);
    }

    /**
     * @param commandInput command
     * @return buildObjectNode for remove event
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message;

        if (user == null) {
            message = "The username " + commandInput.getUsername() + " doesn't exist.";
        } else if (!(Artist.class.isAssignableFrom(user.getClass()))) {
            message = user.getUsername() + " is not an artist.";
        } else {
            message = ((Artist) user).removeEvent(commandInput);
        }

        return buildObjectNode(commandInput, message);
    }

    /**
     * @param command command
     * @return buildObjectNode for get top 5 artists
     */
    public static ObjectNode getTop5Artists(final CommandInput command) {
        List<String> artists = Admin.getTop5Artists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(artists));
        return objectNode;
    }
}
