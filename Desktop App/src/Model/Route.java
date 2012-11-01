package Model;

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
     * @param name String Name of the Route
     * @param startLink Link Startlink of the Route
     */
    public Route(String name, Link startLink) {
        this.name = name;
        this.startLink = startLink;
        startLink.getP2().setStart();
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

    /**
     * Get XML
     *
     * @param XMLProject boolean Used to define difference between shortcut or not
     *
     * @return String
     */
    public String printXML(boolean XMLProject) {
        String XMLString = "";
        XMLString += "<route>\r\n";
        XMLString += "<route.name>" + this.name + "</route.name>\r\n";
        XMLString += "<route.link>\r\n";
        XMLString += startLink.printXML(XMLProject);
        XMLString += "</route.link>\r\n";
        XMLString += "</route>\r\n";
        return XMLString;
    }

    /**
     * Get the Name of the Route
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
