package app.user;

public class Event {

    private String name;
    private String description;
    private String date;

    /**
     * @param name
     * @param description
     * @param date
     */
    public Event(final String name, final String description, final String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /**
     * @return name of event
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return description of event
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return date of event
     */
    public String getDate() {
        return this.date;
    }

    /**
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param date
     */
    public void setDate(final String date) {
        this.date = this.date;
    }

    /**
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return String of name, date and description for printCurrentPage

     */
    public String toString() {
        return this.name + " - " + this.date + ":\n\t" + this.description;
    }
}
