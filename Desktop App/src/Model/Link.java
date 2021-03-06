package Model;

import Plugins.jxmap.swingx.JXMapViewer;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Link {

    // Variables
    private String name;
    private Node p1;
    private Node p2;
    private ArrayList<Link> links;
    private ArrayList<MediaItem> mediaItems;
    private Color color;
    private long id;
    private boolean printed = false;

    /**
     * Constructor
     */
    public Link() {
        this.links = new ArrayList<Link>();
        this.mediaItems = new ArrayList<MediaItem>();
        this.color = Color.RED;
    }

    /**
     * Overload constructor (1)
     *
     * @param name String Name of the Link
     * @param p1 Node First Node
     * @param p2 Node Second Node
     */
    public Link(String name, Node p1, Node p2, long id) {
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
        this.links = new ArrayList<Link>();
        this.mediaItems = new ArrayList<MediaItem>();
        this.color = Color.RED;
        this.id = id;
    }

    /**
     * Overload constructor (2)
     *
     * @param link Link
     * @param id long
     */
    public Link(Link link, long id) {
        this.name = link.getName();
        this.p1 = link.getP1();
        this.p2 = link.getP2();
        this.links = new ArrayList<Link>();
        this.mediaItems = link.getMediaItems();
        this.color = Color.RED;
        this.id = id;
    }

    /**
     * Get the first Node (position) of the Link
     *
     * @return Node first position
     */
    public Node getP1() {
        return p1;
    }

    /**
     * Set the first Node (position) of the Link
     *
     * @param p1 Node first position
     */
    public void setP1(Node p1) {
        this.p1 = p1;
    }

    /**
     * Get the second Node (position) of the Link
     *
     * @return Node second position
     */
    public Node getP2() {
        return p2;
    }

    /**
     * Set the second Node (position) of the Link
     *
     * @param p2 Node second position
     */
    public void setP2(Node p2) {
        this.p2 = p2;
    }

    /**
     * Add a link to the consisting links
     *
     * @param link The new link
     */
    public void addLink(Link link) {
        this.links.add(link);
    }

    /**
     * Get the name of the Link
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Set the name of the Link
     *
     * @param name String name of the Link
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the next Link(s) of this Link
     *
     * @return ArrayList<Link>
     */
    public ArrayList<Link> getLinks() {
        return links;
    }

    /**
     * Set the next Link(s) of the Link
     *
     * @param links ArrayList<Link> The list with all next Links
     */
    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    /**
     * Get Media Items for this Link
     *
     * @return ArrayList<MediaItem>
     */
    public ArrayList<MediaItem> getMediaItems() {
        return mediaItems;
    }

    /**
     * Set the Media Items for this Link
     *
     * @param items ArrayList<MediaItem> The list of Media Items we want to add
     */
    public void setItems(ArrayList<MediaItem> items) {
        this.mediaItems = items;
    }

    /**
     * Get color of the Link
     *
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set color of the Link
     *
     * @param color Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Print XML
     *
     * @param XMLProject boolean
     *
     * @return String
     */
    public String printXML(boolean XMLProject) {
        String XMLString = "";
        if (!printed) {
            XMLString += "<link.name>" + this.name + "</link.name>\r\n";
            XMLString += "<link.id>" + this.id + "</link.id>\r\n";
            if (p1 != null) {
                XMLString += "<from>\r\n";
                XMLString += this.p1.printXML(XMLProject);
                XMLString += "</from>\r\n";
            }
            if (p2 != null) {
                XMLString += "<to>\r\n";
                XMLString += this.p2.printXML(XMLProject);
                XMLString += "</to>\r\n";
            }
            XMLString += "<queue>\r\n";
            for (MediaItem item : this.mediaItems) {
                XMLString += item.printXML(XMLProject);
            }
            XMLString += "</queue>\r\n";
            if (this.links.size() > 0) {
                XMLString += "<links>\r\n";
                for (Link link : this.links) {
                    XMLString += "<link>\r\n";
                    XMLString += link.printXML(XMLProject);
                    XMLString += "</link>\r\n";
                }
                XMLString += "</links>\r\n";
            }
        } else {
            XMLString += "<link.shortcut>" + this.id + "</link.shortcut>\r\n";
        }
        this.printed = true;
        return XMLString;
    }

    /**
     * Set all printed boolean
     *
     * @param printed boolean
     */
    public void setAllPrinted(boolean printed) {
        this.printed = printed;
        for (Link link : this.links) {
            link.setAllPrinted(printed);
        }
    }

    /**
     * Set printed boolean
     *
     * @param printed boolean
     */
    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    /**
     * Get printed boolean
     *
     * @return boolean
     */
    public boolean getPrinted() {
        return this.printed;
    }

    /**
     * Get Link for given endNode
     *
     * @param endNode Node
     *
     * @return Link
     */
    public Link getLinkForEndNode(Node endNode) {
        if (this.getP2().equals(endNode)) {
            return this;
        } else {
            for (Link currentLink : links) {
                Link link = currentLink.getLinkForEndNode(endNode);
                if (link != null) {
                    return link;
                }
            }
            return null;
        }
    }

    /**
     * Get all previous Links for given endNode
     *
     * @param node Node
     * @param prevLinks ArrayList<Link>
     *
     * @return ArrayList<Link>
     */
    public ArrayList<Link> getPrevLinksForNode(Node node, ArrayList<Link> prevLinks) {
        boolean found = false;
        if (this.getP2() == node) {
            for (Link link : prevLinks) {
                if (link == this) {
                    found = true;
                }
            }
            if (!found) {
                prevLinks.add(this);
            }
        }
        for (Link currentLink : links) {
            prevLinks = currentLink.getPrevLinksForNode(node, prevLinks);
        }
        return prevLinks;
    }

    /**
     * Get routes that are part of this given Node
     *
     * @param node Node
     *
     * @return boolean
     */
    public boolean getRoutesFromNode(Node node) {
        if (this.getP2().equals(node)) {
            return true;
        } else {
            for (Link currentLink : links) {
                return currentLink.getRoutesFromNode(node);
            }
            return false;
        }
    }

    /**
     * Get all Links for a Story
     *
     * @param allLinks ArrayList<Link>
     *
     * @return ArrayList<Link>
     */
    public ArrayList<Link> getAllLinks(ArrayList<Link> allLinks) {
        allLinks.add(this);
        for (Link l : this.links) {
            l.getAllLinks(allLinks);
        }
        return allLinks;
    }

    /**
     * Get line-drawing (red line) with arrow
     *
     * @param map JXMapViewer
     * @param mousePos Point2D
     *
     * @return ArrayList<Line2D.Double>
     */
    public ArrayList<Line2D.Double> getLines(JXMapViewer map, Point2D mousePos) {
        ArrayList<Line2D.Double> lines = new ArrayList<Line2D.Double>();
        if (p1 != null) {

            // Initialize variables
            Point2D pt1 = null;
            Point2D pt2 = null;
            Point2D middle = null;
            double length = 10;

            // Set pt1 and pt2
            if (p2 == null && p1 != null) {
                pt1 = map.getTileFactory().geoToPixel(p1.getGeoposition(), map.getZoom());
                pt2 = map.getTileFactory().geoToPixel(map.convertPointToGeoPosition(mousePos), map.getZoom());
            } else {
                pt1 = map.getTileFactory().geoToPixel(p1.getGeoposition(), map.getZoom());
                pt2 = map.getTileFactory().geoToPixel(p2.getGeoposition(), map.getZoom());
            }

            // Get red line
            Line2D.Double line = new Line2D.Double((int) pt1.getX(), (int) pt1.getY(), (int) pt2.getX(), (int) pt2.getY());
            lines.add(line);

            // Set middle point
            if (pt1.getX() == pt2.getX()) {
                middle = new Point2D.Double(pt1.getX(), (pt1.getY() + pt2.getY()) / 2);
            } else if (pt1.getY() == pt2.getY()) {
                middle = new Point2D.Double((pt1.getX() + pt2.getX()) / 2, pt1.getY());
            } else {
                middle = new Point2D.Double((pt1.getX() + pt2.getX()) / 2, (pt1.getY() + pt2.getY()) / 2);
            }

            // Get length of Line
            double difX = (double) pt1.getX() - (double) pt2.getX();
            double difY = (double) pt1.getY() - (double) pt2.getY();

            // Initialize variables
            Point2D arrowLineX = null;
            Point2D arrowLineY = null;

            // Calculate angle for drawing red arrow
            if (difY == 0) {
                double deltaX = Math.sqrt((length * length) / 2);
                if (difX < 0) {
                    deltaX = deltaX * -1;
                }
                double deltaY = deltaX;
                arrowLineX = new Point2D.Double(middle.getX() + deltaX, middle.getY() + deltaY);
                arrowLineY = new Point2D.Double(middle.getX() + deltaY, middle.getY() - deltaX);
            } else {
                double angle = Math.atan(difX / difY);
                double arrowAngle = angle - 95;
                double deltaX = length * Math.sin(arrowAngle);
                double deltaY = length * Math.cos(arrowAngle);

                if (difY > 0) {
                    arrowLineX = new Point2D.Double(middle.getX() + deltaX, middle.getY() + deltaY);
                    arrowLineY = new Point2D.Double(middle.getX() + deltaY, middle.getY() - deltaX);
                } else {
                    arrowLineX = new Point2D.Double(middle.getX() - deltaY, middle.getY() + deltaX);
                    arrowLineY = new Point2D.Double(middle.getX() - deltaX, middle.getY() - deltaY);
                }
            }

            // Get red arrow
            Line2D.Double pijltje1 = new Line2D.Double((int) middle.getX(), (int) middle.getY(), (int) arrowLineY.getX(), (int) arrowLineY.getY());
            Line2D.Double pijltje2 = new Line2D.Double((int) middle.getX(), (int) middle.getY(), (int) arrowLineX.getX(), (int) arrowLineX.getY());
            lines.add(pijltje1);
            lines.add(pijltje2);
        }

        // Return red line + arrow
        return lines;
    }

    /**
     * Return true if the given Link is part of a Route
     *
     * @param link Link
     *
     * @return boolean
     */
    public boolean getRouteForLink(Link link) {
        if (this.equals(link)) {
            return true;
        } else {
            for (Link currentLink : links) {
                if (currentLink.getRouteForLink(link)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Get the identifier from this link
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Get the identifier from this link
     *
     * @return the id
     */
    public ArrayList<Link> getParentsFromLink(ArrayList<Link> parents, Link link, Link parent) {
        if (this == link) {
            boolean found = false;
            for (Link link1 : parents) {
                if (parent == link1) {
                    found = true;
                }
            }
            if (!found) {
                parents.add(parent);
            }
        }
        for (Link currentLink : links) {
            currentLink.getParentsFromLink(parents, link, this);
        }
        return parents;
    }

    /**
     * Return the name of this Link
     */
    @Override
    public String toString() {
        return this.name;
    }
}
