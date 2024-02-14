package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.LibraryEntry;
import lombok.Getter;

@Getter
public abstract class AudioCollection extends LibraryEntry {
    private final String owner;

    /**
     * @param name  the name of the collection
     * @param owner the owner of the collection
     */
    public AudioCollection(final String name, final String owner) {
        super(name);
        this.owner = owner;
    }

    /**
     *
     * @return number of tracks in the collection
     */
    public abstract int getNumberOfTracks();

    /**
     *
     * @param index the index of the track
     * @return the track at the given index
     */

    public abstract AudioFile getTrackByIndex(int index);

    /**
     *
     * compares the owner of the collection with the given user
     */
    @Override
    public boolean matchesOwner(final String user) {
        return this.getOwner().equals(user);
    }

    /**
     *
     * @return the owner of the collection
     */
    public String getOwner() {
        return owner;
    }
}
