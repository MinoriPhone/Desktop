package Model;

import View.Main;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class RoutesTreeRenderer extends DefaultTreeCellRenderer {

    /**
     * Get the image icon for a path in the tree
     *
     * @param path String Path to the branch or leave of the tree
     *
     * @return ImageIcon
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Main.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Sets the value of the current tree cell to value. If selected is true, the cell will be drawn as if selected. If expanded is
     * true the node is currently expanded and if leaf is true the node represets a leaf and if hasFocus is true the node currently has
     * focus. tree is the JTree the receiver is being configured for.
     *
     * @param jtree JTree
     * @param o Object
     * @param bln boolean
     * @param bln1 boolean
     * @param bln2 boolean
     * @param i int
     * @param bln3 boolean
     *
     * @return Component The Component that the renderer uses to draw the value
     */
    @Override
    public Component getTreeCellRendererComponent(JTree jtree, Object o, boolean bln, boolean bln1, boolean bln2, int i, boolean bln3) {
        super.getTreeCellRendererComponent(jtree, o, bln, bln1, bln2, i, bln3);

        // Set ImageIcon
        ImageIcon icon = null;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) o;
        if (node.getUserObject() instanceof Story) {
            icon = createImageIcon("Images/arrow-down.png");
        } else if (node.getUserObject() instanceof Route) {
            icon = createImageIcon("Images/green_start_16.png");
        } else if (node.getUserObject() instanceof Link) {
            icon = createImageIcon("Images/red_link_16.png");
        } else if (node.getUserObject() instanceof Node) {
            icon = createImageIcon("Images/blue_waypoint_16.png");
        }
        setIcon(icon);
        return this;
    }
}
