package Model;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * A Link between two Nodes. A Route consists of multiple Links.
 */
public class Link extends DefaultMutableTreeNode {

    // Variables
    private String name;
    private Node p1;
    private Node p2;
    // private Video video

    /**
     *
     * @param name
     * @param o
     * @param p1
     * @param p2
     */
    public Link(String name, DefaultMutableTreeNode startLink, Node p1, Node p2 /*,video*/) {
        super(startLink, true);
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     *
     * @param name
     * @param o
     * @param p1
     * @param p2
     */
    public Link(String name, Node p1, Node p2) {
        this.name = name;
        this.p1 = p1;
        this.p2 = p2;
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
     *
     * @return
     */
    @Override
    public String toString() {
        return name;
    }
}