package Model;

import View.Main;
import View.Routes;
import java.io.File;
import java.util.ArrayList;

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
     */
    public void newEmptyRoute() {
        Route route = new Route();
        routes.add(route);
    }

    /**
     * Get a Link with the correct Endnode
     *
     * @param endNode Node The Node which the Link has as an end
     *
     * @return Link
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
     * @param endNode Node The Node which the Link has as a start
     *
     * @return Link
     */
    public Link getStartLinkForNode(Node endNode) {
        for (Route route : routes) {
            if (route.getStartLink().getP2().equals(endNode)) {
                return route.getStartLink();
            }
        }
        return null;
    }

    /**
     * Get previous links for this given Node
     *
     * @param startNode Node The Node which the user just clicked for creating a Link
     *
     * @return ArrayList<Link>
     */
    public ArrayList<Link> getPreviousLinksForStartNode(Node startNode) {
        ArrayList<Link> prevLinks = new ArrayList<Link>();
        for (Route route : routes) {
            prevLinks = route.getStartLink().getPrevLinksForNode(startNode, prevLinks);
        }
        return prevLinks;
    }

    /**
     * Get XML
     *
     * @param XMLProject boolean Used to define difference between shortcut or not
     *
     * @return String
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
     * Set the boolean for already printed or not
     *
     * @param printed boolean
     */
    public void setPrinted(boolean printed) {
        for (Route route : this.routes) {
            route.getStartLink().setAllPrinted(printed);
        }
    }

    /**
     * Get all routes that are part of a Node
     *
     * @param node Node
     *
     * @return ArrayList<Route>
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
     * @param link Link
     *
     * @return ArrayList<Route>
     */
    public ArrayList<Route> getRoutesForLink(Link link) {
        // Get all routes that are part of the given Node
        ArrayList<Route> linkRoutes = new ArrayList<Route>();
        for (Route route : routes) {
            if (route.getStartLink().getRouteForLink(link)) {
                linkRoutes.add(route);
            }
        }
        return linkRoutes;
    }

    /**
     * Get all parents for link
     *
     * @param link Link
     */
    public ArrayList<Link> getParentsFromLink(Link link) {
        ArrayList<Link> parents = new ArrayList<Link>();
        // Get all routes that are part of the given Node
        for (Route route : routes) {
            parents = route.getStartLink().getParentsFromLink(parents, link, null);
        }
        return parents;
    }

    /**
     * Get all Links for Story
     *
     * @return ArrayList<Link>
     */
    public ArrayList<Link> getAllLinks() {
        ArrayList<Link> allLinks = new ArrayList<Link>();
        for (Route route : this.routes) {
            allLinks = route.getStartLink().getAllLinks(allLinks);
        }
        return allLinks;
    }

    /**
     * Indicates whether something for this Story has been changed by the user. If true, a astrix will be shown in the titlebar before
     * the title name
     *
     * @return boolean
     */
    public boolean isSomethingChanged() {
        return somethingChanged;
    }

    /**
     * Set true if the user changed the Story or false if not
     *
     * @param somethingChanged boolean
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
     * @return long The current linkCounter incremented with 1
     */
    public long getLinkCounter() {
        linkCounter++;
        return linkCounter;
    }

    /**
     * Set the linkcounter manually
     *
     * @param linkCounter long
     */
    public void setLinkCounter(long linkCounter) {
        this.linkCounter = linkCounter;
    }

    /**
     * Repaints the routesPanel
     */
    public void repaint() {
        routesPanel.refreshList(routes);
    }

    /**
     * Get the name of the Story
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
