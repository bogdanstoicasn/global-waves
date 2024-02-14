package app.player;


public class PodcastBookmark {
    private final String name;
    private final int id;
    private final int timestamp;

    /**
     * @param name       the name of the podcast
     * @param id         the id of the podcast
     * @param timestamp  the timestamp of the podcast
     */
    public PodcastBookmark(final String name, final int id, final int timestamp) {
        this.name = name;
        this.id = id;
        this.timestamp = timestamp;
    }

    /**
     * @return the string representation of the podcast bookmark
     */
    @Override
    public String toString() {
        return "PodcastBookmark{"
                +
                "name='" + name + '\''
                +
                ", id=" + id
                +
                ", timestamp=" + timestamp
                +
                '}';
    }

    /**
     * @return the name of the podcast
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id of the podcast
     */
    public int getId() {
        return id;
    }

    /**
     * @return the timestamp of the podcast
     */
    public int getTimestamp() {
        return timestamp;
    }
}
