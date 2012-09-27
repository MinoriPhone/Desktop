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
    private boolean somethingChanged;
    private long linkCounter;

    /**
     * Constructor
     */
    public Story(String name, Routes routesPanel) {
        this.name = name;
        this.routes = new ArrayList<Route>();
        this.routesPanel = routesPanel;
        this.linkCounter = 0;
        somethingChanged = false;
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
     * @param routeName String Name of the Route
     * @param startLink Link The startlink for this Route
     */
    public void newRoute(String routeName, Link startLink) {
        Route route = new Route(routeName, startLink);
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
     * Get previous links for this given Node
     *
     * @param startNode the Node which the user just clicked for creating a Link
     */
    public ArrayList<Link> getPreviousLinksForStartNode(Node startNode) {
        ArrayList<Link> prevLinks = new ArrayList<Link>();
        for (Route route : routes) {
            prevLinks = route.getStartLink().getPrevLinksForNode(startNode, prevLinks);
        }
        return prevLinks;
    }

    /**
     * Print XML
     */
    public String printXML() {
        String XMLString = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n";

        XMLString += "<story>\r\n";
        XMLString += "<story.name>" + this.name + "</story.name>\r\n";
        XMLString += "<routes>\r\n";
        for (Route route : this.routes) {
            XMLString += route.printXML();
        }
        XMLString += "</routes>\r\n";
        XMLString += "</story>\r\n";
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

    /**
     * Get all route for link
     *
     * @param node Node
     */
    public Route getRouteForLink(Link link) {
        // Get all routes that are part of the given Node
        for (Route route : routes) {
            if (route.getStartLink().getRouteForLink(link)) {
                return route;
            }
        }
        return null;
    }

    /**
     * Get all media items
     */
    public ArrayList<Link> getAllLinks() {
        ArrayList<Link> allLinks = new ArrayList<Link>();
        for (Route route : this.routes) {
            allLinks = route.getStartLink().getAllLinks(allLinks);
        }
        return allLinks;
    }

    /**
     * TODO
     *
     * @return boolean
     */
    public boolean isSomethingChanged() {
        return somethingChanged;
    }

    /**
     * TODO
     *
     * @param somethingChanged
     */
    public void setSomethingChanged(boolean somethingChanged) {
        this.somethingChanged = somethingChanged;
    }

    /**
     * Returns the Link Counter
     * @return the current linkCounter incremented with 1
     */
    public long getLinkCounter() {
        linkCounter++;
        return linkCounter;
    }
}
