package Model;

import java.util.ArrayList;

/**
 * A Link between two Nodes. A Route consists of multiple Links.
 */
public class Link {

    // Variables
    private Node p1;
    private Node p2;
    private ArrayList<Link> nextLinks;
    // private Video video

    /**
     * Constructor
     */
    public Link() {
    }

    /**
     * Overload Constructor
     */
    public Link(Node p1, Node p2, ArrayList<Link> nextLinks /*,video*/) {
        this.p1 = p1;
        this.p2 = p2;
        this.nextLinks = nextLinks;
        //this.video = video;
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
}