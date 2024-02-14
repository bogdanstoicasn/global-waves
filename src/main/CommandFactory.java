package main;

import app.CommandRunner;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;

public interface CommandFactory {
    /**
     * @param command
     * @execute the command
     */
    ObjectNode executeCommand(CommandInput command);
}

class SearchCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.search(command);
    }
}

class SelectCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.select(command);
    }
}

class LoadCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.load(command);
    }
}

class PlayPauseCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.playPause(command);
    }
}

class RepeatCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.repeat(command);
    }
}

class ShuffleCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.shuffle(command);
    }
}

class ForwardCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.forward(command);
    }
}

class BackwardCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.backward(command);
    }
}

class LikeCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.like(command);
    }
}

class NextCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.next(command);
    }
}

class PrevCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.prev(command);
    }
}

class CreatePlaylistCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.createPlaylist(command);
    }
}

class AddRemoveInPlaylistCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.addRemoveInPlaylist(command);
    }
}

class SwitchVisibilityCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.switchVisibility(command);
    }
}

class ShowPlaylistsCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.showPlaylists(command);
    }
}

class FollowCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.follow(command);
    }
}

class StatusCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.status(command);
    }
}

class ShowLikedSongsCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.showLikedSongs(command);
    }
}

class GetPreferredGenreCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.getPreferredGenre(command);
    }
}

class GetTop5SongsCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.getTop5Songs(command);
    }
}

class GetTop5PlaylistsCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.getTop5Playlists(command);
    }
}

class GetTop5AlbumsCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.getTop5Albums(command);
    }
}

class GetTop5ArtistsCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.getTop5Artists(command);
    }
}

class SwitchConnectionStatusCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.switchConnectionStatus(command);
    }
}

class GetOnlineUsersCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.getOnlineUsers(command);
    }
}

class GetAllUsersCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.getAllUsers(command);
    }
}

class AddUserCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.addUser(command);
    }
}

class DeleteUserCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.deleteUser(command);
    }
}

class AddAlbumCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.addAlbum(command);
    }
}

class RemoveAlbumCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.removeAlbum(command);
    }
}

class ShowAlbumsCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.showAlbums(command);
    }
}

class PrintCurrentPageCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.printCurrentPage(command);
    }
}

class ChangePageCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.changePage(command);
    }
}

class AddEventCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.addEvent(command);
    }
}

class RemoveEventCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.removeEvent(command);
    }
}

class AddMerchCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.addMerch(command);
    }
}

class AddAnnouncementCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.addAnnouncement(command);
    }
}

class RemoveAnnouncementCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.removeAnnouncement(command);
    }
}

class AddPodcastCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.addPodcast(command);
    }
}

class ShowPodcastsCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.showPodcasts(command);
    }
}

class RemovePodcastCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return CommandRunner.removePodcast(command);
    }
}

class DefaultCommandFactory implements CommandFactory {
    @Override
    public ObjectNode executeCommand(final CommandInput command) {
        return null;
    }
}

class CommandFactoryProducer {
    public static CommandFactory getFactory(final String commandName) {
        switch (commandName) {
            case "search" -> {
                return new SearchCommandFactory();
            }
            case "select" -> {
                return new SelectCommandFactory();
            }
            case "load" -> {
                return new LoadCommandFactory();
            }
            case "playPause" -> {
                return new PlayPauseCommandFactory();
            }
            case "repeat" -> {
                return new RepeatCommandFactory();
            }
            case "shuffle" -> {
                return new ShuffleCommandFactory();
            }
            case "forward" -> {
                return new ForwardCommandFactory();
            }
            case "backward" -> {
                return new BackwardCommandFactory();
            }
            case "like" -> {
                return new LikeCommandFactory();
            }
            case "next" -> {
                return new NextCommandFactory();
            }
            case "prev" -> {
                return new PrevCommandFactory();
            }
            case "createPlaylist" -> {
                return new CreatePlaylistCommandFactory();
            }
            case "addRemoveInPlaylist" -> {
                return new AddRemoveInPlaylistCommandFactory();
            }
            case "switchVisibility" -> {
                return new SwitchVisibilityCommandFactory();
            }
            case "showPlaylists" -> {
                return new ShowPlaylistsCommandFactory();
            }
            case "follow" -> {
                return new FollowCommandFactory();
            }
            case "status" -> {
                return new StatusCommandFactory();
            }
            case "showPreferredSongs" -> {
                return new ShowLikedSongsCommandFactory();
            }
            case "getPreferredGenre" -> {
                return new GetPreferredGenreCommandFactory();
            }
            case "getTop5Songs" -> {
                return new GetTop5SongsCommandFactory();
            }
            case "getTop5Playlists" -> {
                return new GetTop5PlaylistsCommandFactory();
            }
            case "getTop5Albums" -> {
                return new GetTop5AlbumsCommandFactory();
            }
            case "getTop5Artists" -> {
                return new GetTop5ArtistsCommandFactory();
            }
            case "switchConnectionStatus" -> {
                return new SwitchConnectionStatusCommandFactory();
            }
            case "getOnlineUsers" -> {
                return new GetOnlineUsersCommandFactory();
            }
            case "getAllUsers" -> {
                return new GetAllUsersCommandFactory();
            }
            case "addUser" -> {
                return new AddUserCommandFactory();
            }
            case "deleteUser" -> {
                return new DeleteUserCommandFactory();
            }
            case "addAlbum" -> {
                return new AddAlbumCommandFactory();
            }
            case "removeAlbum" -> {
                return new RemoveAlbumCommandFactory();
            }
            case "showAlbums" -> {
                return new ShowAlbumsCommandFactory();
            }
            case "printCurrentPage" -> {
                return new PrintCurrentPageCommandFactory();
            }
            case "changePage" -> {
                return new ChangePageCommandFactory();
            }
            case "addEvent" -> {
                return new AddEventCommandFactory();
            }
            case "removeEvent" -> {
                return new RemoveEventCommandFactory();
            }
            case "addMerch" -> {
                return new AddMerchCommandFactory();
            }
            case "addAnnouncement" -> {
                return new AddAnnouncementCommandFactory();
            }
            case "removeAnnouncement" -> {
                return new RemoveAnnouncementCommandFactory();
            }
            case "addPodcast" -> {
                return new AddPodcastCommandFactory();
            }
            case "showPodcasts" -> {
                return new ShowPodcastsCommandFactory();
            }
            case "removePodcast" -> {
                return new RemovePodcastCommandFactory();
            }
            default -> {
                return new DefaultCommandFactory();
            }
        }
    }
}
// Create similar classes for other commands
