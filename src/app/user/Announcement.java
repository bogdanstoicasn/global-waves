package app.user;

public class Announcement {
    private String name;
    private String description;

    /**
     * Constructor
     * @param name
     * @param description
     */
    public Announcement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param name set name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param description set description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

}
