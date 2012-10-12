package Model;

import View.Main;
import View.Routes;
import java.io.File;
import java.util.ArrayList;

/**
 * A Story that the user is going to watch. This class holds all the different Routes an user can make.
 */
public class Story {

    // Variables
    private String name;
    private File image;
    private ArrayList<Route> routes;
    private Routes routesPanel;
    private boolean somethingChanged;
    private long linkCounter;
    private Main main;

    /**
     * Constructor
     */
    public Story(String name, Routes routesPanel, Main main) {
        this.name = name;
        this.routes = new ArrayList<Route>();
        this.routesPanel = routesPanel;
        this.linkCounter = 0;
        this.main = main;
        somethingChanged = false;
    }

    /**
     * Overload Constructor
     *
     * @param name The we want to set for this Story
     * @param routes A list of all the existing routes
     */
    public Story(String name, ArrayList<Route> routes, Main main) {
        this.name = name;
        this.routes = routes;
        this.main = main;
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
     * Get the image from the story
     *
     * @return the image file
     */
    public File getImage() {
        return image;
    }

    /**
     * Set the story image file
     *
     * @param image The story image file
     */
    public void setImage(File image) {
        this.image = image;
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
     * Create a new empty Route and add Route to the Routelist
     *
     */
    public void newEmptyRoute() {
        Route route = new Route();
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
     * Get the startLink for this node
     *
     * @param endNode the Node which the Link has as a start
     */
    public Link getStartLinkForNode(Node endNode) {
        for (Route route : routes) {
            if(route.getStartLink().getP2().equals(endNode))
            {
                return route.getStartLink();
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
    public String printXML(boolean XMLProject) {
        String XMLString = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n";

        XMLString += "<story>\r\n";
        XMLString += "<story.name>" + this.name + "</story.name>\r\n";
        if (this.image != null) {
            if (XMLProject) {
                XMLString += "<story.image>" + this.image.getAbsolutePath() + "</story.image>\r\n";
            } else {
                XMLString += "<story.image>" + this.image.getName() + "</story.image>\r\n";
            }
        }
        XMLString += "<routes>\r\n";
        for (Route route : this.routes) {
            XMLString += route.printXML(XMLProject);
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
     * Get all route for link
     *
     * @param node Node
     */
    public Link getParentFromLink(Link link) {
        // Get all routes that are part of the given Node
        for (Route route : routes) {
            Link parentLink = route.getStartLink().getParentFromLink(link, null);
            if (parentLink != null) {
                return parentLink;
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
     * Get all media items
     */
    public Link getTwins(Link link) {
        // Get all routes that are part of the given Node
        for (Route route : routes) {
            Link twin = route.getStartLink().getTwins(link);
            if (twin != null) {
                return twin;
            }
        }
        return null;
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

        // Add a astrix at the end of the title (to indicates that the story is not saved)
        if (somethingChanged) {
            if (!main.getTitle().startsWith("*")) {
                main.setTitle("*" + main.getTitle());
            }
        } else {
            if (main.getTitle().startsWith("*")) {
                main.setTitle(main.getTitle().replaceFirst("\\*", ""));
            }
        }
    }

    /**
     * Returns the Link Counter
     *
     * @return the current linkCounter incremented with 1
     */
    public long getLinkCounter() {
        linkCounter++;
        return linkCounter;
    }
    
    /**
     * Set the linkcounter manually
     *
     */
    public void setLinkCounter(long linkCounter) {
        this.linkCounter = linkCounter;
    }
    
    public void repaint(){
        routesPanel.refreshList(routes);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
