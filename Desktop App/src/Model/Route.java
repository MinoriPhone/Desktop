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
     *
     * @param name String Name of the Route
     * @param startLink Link Startlink of the Route
     */
    public Route(String name, Node startNode) {
        this.name = name;
        this.startLink = new Link("Start", null, startNode);
        startNode.setStart();
    }

    /**
     * Get the name of the Route
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the Route
     *
     * @param name String Name of the route
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the startlink of the Route
     *
     * @return Link
     */
    public Link getStartLink() {
        return startLink;
    }

    /**
     * Set the startlink of the Route
     *
     * @param startLink Link The startlink of the Route
     */
    public void setStartLink(Link startLink) {
        this.startLink = startLink;
    }
}
