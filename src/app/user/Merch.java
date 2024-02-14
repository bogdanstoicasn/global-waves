package app.user;

public class Merch {

    private String name;
    private String description;
    private Integer price;

    /**
     * Constructor
     * @param name
     * @param description
     * @param price
     */
    public Merch(final String name, final String description, final Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Getter name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Getter price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Setter price
     */
    public void setPrice(final Integer price) {
        this.price = price;
    }

    /**
     * Override toString
     */
    @Override
    public String toString() {
        return name + " - " + price + ":\n\t" + description;
    }
}
