package Model;

import Plugins.jxmap.swingx.JXMapViewer;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * A Link between two Nodes. A Route consists of multiple Links.
 */
public class Link {

    // Variables
    private String name;
    private Node p1;
    private Node p2;
    private ArrayList<Link> links;
    private ArrayList<MediaItem> items;
    private Color color;

    /**
     * Constructor
     *
     * @param name String
     * @param p1 Node
     * @param p2 Node
     */
    public Link(String name, Node p1, Node p2) {
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
        this.links = new ArrayList<Link>();
        this.items = new ArrayList<MediaItem>();
        this.color = Color.RED;
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
    public ArrayList<MediaItem> getItems() {
        return items;
    }

    /**
     * Set the Media Items for this Link
     *
     * @param items ArrayList<MediaItem> The list of Media Items we want to add
     */
    public void setItems(ArrayList<MediaItem> items) {
        this.items = items;
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
     */
    public String printXML() {
        String XMLString = "";
        XMLString += "<link.name>" + this.name + "</link.name>\r\n";
        if (p1 != null) {
            XMLString += "<from>\r\n";
            XMLString += this.p1.printXML();
            XMLString += "</from>\r\n";
        }
        if (p2 != null) {
            XMLString += "<to>\r\n";
            XMLString += this.p2.printXML();
            XMLString += "</to>\r\n";
        }
        XMLString += "<queue>\r\n";
        for (MediaItem item : this.items) {
            XMLString += item.printXML();
        }
        XMLString += "</queue>\r\n";
        if (this.links.size() > 0) {
            XMLString += "<links>\r\n";
            for (Link link : this.links) {
                XMLString += link.printXML();
            }
            XMLString += "</links>\r\n";
        }
        return XMLString;
    }

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
     * TODO 
     * 
     * @param node
     * @param prevLinks
     * @return 
     */
    public ArrayList<Link> getPrevLinksForNode(Node node, ArrayList<Link> prevLinks) {
        if (this.getP2().equals(node)) {
            prevLinks.add(this);
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
     * Get all media items
     */
    public ArrayList<MediaItem> getAllMediaItems(ArrayList<MediaItem> MediaItems) {
        for (MediaItem item : this.items) {
            MediaItems.add(item);
        }
        for (Link link : this.links) {
            MediaItems = link.getAllMediaItems(MediaItems);
        }
        return MediaItems;
    }

    /**
     * Get all media items
     */
    public ArrayList<Line2D.Double> GetLines(JXMapViewer map, Point2D mousePos) {
        ArrayList<Line2D.Double> lines = new ArrayList<Line2D.Double>();
        Point2D pt1;
        Point2D pt2;
        double length = 10;
        if (p2 == null && p1 != null) {
            pt1 = map.getTileFactory().geoToPixel(p1.getGeoposition(), map.getZoom());
            pt2 = map.getTileFactory().geoToPixel(map.convertPointToGeoPosition(mousePos), map.getZoom());
        } else {
            pt1 = map.getTileFactory().geoToPixel(p1.getGeoposition(), map.getZoom());
            pt2 = map.getTileFactory().geoToPixel(p2.getGeoposition(), map.getZoom());
        }
        Line2D.Double line = new Line2D.Double((int) pt1.getX(), (int) pt1.getY(), (int) pt2.getX(), (int) pt2.getY());
        lines.add(line);

        Point2D middle;
        if (pt1.getX() == pt2.getX()) {
            middle = new Point2D.Double(pt1.getX(), (pt1.getY() + pt2.getY()) / 2);
        } else if (pt1.getY() == pt2.getY()) {
            middle = new Point2D.Double((pt1.getX() + pt2.getX()) / 2, pt1.getY());
        } else {
            middle = new Point2D.Double((pt1.getX() + pt2.getX()) / 2, (pt1.getY() + pt2.getY()) / 2);
        }

        double difX = (double) pt1.getX() - (double) pt2.getX();
        double difY = (double) pt1.getY() - (double) pt2.getY();

        Point2D arrowLineY;
        Point2D arrowLineX;

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
        Line2D.Double pijltje1 = new Line2D.Double((int) middle.getX(), (int) middle.getY(), (int) arrowLineY.getX(), (int) arrowLineY.getY());
        lines.add(pijltje1);
        Line2D.Double pijltje2 = new Line2D.Double((int) middle.getX(), (int) middle.getY(), (int) arrowLineX.getX(), (int) arrowLineX.getY());
        lines.add(pijltje2);
        return lines;
    }

    /**
     * Return name of this Link
     */
    @Override
    public String toString() {
        return this.name;
    }
}