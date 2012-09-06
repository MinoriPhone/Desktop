package Model;

/**
 * The Route an user is able to make during the Story. A Route holds all the Links between different Nodes (positions).
 */
public class Route {

    // Variables
    private String name;
    private Link startLink;

    /**
     * Constructor
     */
    public Route() {
    }

    /**
     * Overload Constructor
     *
     * @param naam String name of the Route
     * @param beginLink Link start Link van de Route
     */
    public Route(String name, Link startLink) {
        this.name = name;
        this.startLink = startLink;
    }

    /**
     * Get the name of the Route
     * 
     * @return String name of the Route
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the start Link of the Route
     * 
     * @return Link start Link of the Route
     */
    public Link getStartLink() {
        return startLink;
    }

    /**
     * Set the start Link of the Route
     * 
     * @param startLink Link
     */
    public void setStartLink(Link startLink) {
        this.startLink = startLink;
    }
}
