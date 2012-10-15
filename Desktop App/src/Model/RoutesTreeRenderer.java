/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Main;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Jeffrey
 */
public class RoutesTreeRenderer extends DefaultTreeCellRenderer {

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Main.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public Component getTreeCellRendererComponent(JTree jtree, Object o, boolean bln, boolean bln1, boolean bln2, int i, boolean bln3) {
        super.getTreeCellRendererComponent(jtree, o, bln, bln1, bln2, i, bln3);

        ImageIcon icon = null;
        // Icons setten
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
