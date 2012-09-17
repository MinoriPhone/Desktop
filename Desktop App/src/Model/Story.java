package Model;

import View.Routes;
import java.util.ArrayList;

/**
 * A Story that the user is going to watch. This class holds all the different Routes an user can make.
 */
public class Story {

    // Variables
    private String name;
    private ArrayList<Route> routes;
    private Routes routesPanel;

    /**
     * Constructor
     */
    public Story(String name, Routes routesPanel) {
        this.name = name;
        this.routes = new ArrayList<Route>();
        this.routesPanel = routesPanel;
    }

    /**
     * Overload Constructor
     *
     * @param name The we want to set for this Story
     * @param routes A list of all the existing routes
     */
    public Story(String name, ArrayList<Route> routes) {
        this.name = name;
        this.routes = routes;
    }

    /**
     * Get the name of this Story
     *
     * @return String Name of this Story
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this Story
     *
     * @param name String name of the Story
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return all existing Routes
     *
     * @return ArrayList<Route> List of all existing Routes
     */
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    /**
     * Create a new Route and add Route to the Routelist
     *
     * @param startNode The Node where the Route starts
     */
    public void newRoute(Node startNode) {
        Route route = new Route("" + Math.random(), startNode);
        routes.add(route);
        routesPanel.refreshList(routes);
    }

    /**
     * Get a Link with the correct Endnode
     *
     * @param endNode the Node which the Link has as an end
     */
    public Link getLinkForEndNode(Node endNode) {
        Link currentLink;
        for (Route route : routes) {
            currentLink = route.getStartLink().getLinkForEndNode(endNode);
            if (currentLink != null) {
                return currentLink;
            }
        }
        return null;
    }

    /**
     * Print XML
     */
    public String printXML() {
        String XMLString = "";

        XMLString += "<story>";
        XMLString += "<story.name>" + this.name + "</story.name>";
        XMLString += "<routes>";
        for (Route route : this.routes) {
            XMLString += route.printXML();
        }
        XMLString += "</routes>";
        XMLString += "</story>";
        return XMLString;
    }

    /**
     * Get all routes that are part of a Node
     *
     * @param node Node
     */
    public ArrayList<Route> getRoutesFromNode(Node node) {
        ArrayList<Route> r = new ArrayList<Route>();

        // Get all routes that are part of the given Node
        for (Route route : routes) {
            if (route.getStartLink().getRoutesFromNode(node)) {
                r.add(route);
            }
        }
        return r;
    }
}
