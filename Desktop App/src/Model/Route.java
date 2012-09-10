package Model;

import javax.swing.tree.TreeNode;

/**
 * The Route an user is able to make during the Story. A Route holds all the Links between different Nodes (positions).
 */
public class Route {

    // Variables
    private String name;
    private Link startLink;

    /**
     *
     * @param name
     * @param link
     */
    public Route(String name, Link startLink) {
        this.name = name;
        this.startLink = startLink;
    }

    /**
     *
     * @return
     */
    public void getPath() {
        TreeNode tim = this.startLink.getChildAt(0);
       
        System.out.println(tim);
    }
    
    /**
     *
     * @return
     */
    public void print() {
        System.out.println(this.name);
       
        this.startLink.print(0);
    }
}
