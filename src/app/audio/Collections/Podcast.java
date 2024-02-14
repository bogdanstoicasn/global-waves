package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import java.util.List;

public final class Podcast extends AudioCollection {
    private final List<Episode> episodes;

    /**
     * Constructor for Podcast
     * @param name name of the podcast
     * @param owner owner of the podcast
     */
    public Podcast(final String name, final String owner, final List<Episode> episodes) {
        super(name, owner);
        this.episodes = episodes;
    }

    /**
     * getter
     * @return episodes
     */
    public List<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * getter
     * @return number of episodes
     */
    @Override
    public int getNumberOfTracks() {
        return episodes.size();
    }

    /**
     * getter
     * @param index index of the episode
     * @return episode
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return episodes.get(index);
    }

    /**
     * getter
     * @param name name of the episode
     * @return episode
     */
    public boolean matchesPodcast(final String name) {
        return this.getName().equalsIgnoreCase(name);
    }
}
