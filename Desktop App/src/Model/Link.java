package Model;

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
}
