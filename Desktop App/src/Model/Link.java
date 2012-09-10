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
     * LATER WEGHALEN!!
     */
    public void print(int i) {
        String spaties = "";
        for (int a = 0; a < i; a++) {
            spaties += "   ";
        }
        i++;
        System.out.println(spaties + "--->" + this.toString());
        for (Link link : this.links) {
            link.print(i);
        }
    }
}