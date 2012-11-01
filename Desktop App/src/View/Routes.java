package View;

import Model.Link;
import Model.Route;
import Model.RoutesTreeRenderer;
import Model.Story;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class Routes extends JPanel {

    // Variables
    private JTree tree;
    private DefaultTreeModel treeModel;
    private Story story;
    private DefaultMutableTreeNode storyNode;

    /**
     * Creates new routes panel
     */
    public Routes() {
        initComponents();

    }

    /**
     * Appand a new child to the tree
     *
     * @param parentLink Link the parent link
     * @param parentTreeNode DefaultMutableTreeNode the parent tree node
     */
    private void appendChild(Link parentLink, DefaultMutableTreeNode parentTreeNode) {

        for (Link link : parentLink.getLinks()) {
            DefaultMutableTreeNode tempLink = new DefaultMutableTreeNode(link);
            parentTreeNode.add(tempLink);
            appendChild(link, tempLink);
        }
    }

    /**
     * Refresh and rebuild the tree
     *
     * @param routes ArrayList<Route> A list with all the routes
     */
    public void refreshList(ArrayList<Route> routes) {
        // Clear the current active tree
        storyNode.removeAllChildren();

        // Loop trough routes
        for (Route route : routes) {
            DefaultMutableTreeNode tempRoute = new DefaultMutableTreeNode(route);
            storyNode.add(tempRoute);

            // Recursively loop over all the children and add them
            DefaultMutableTreeNode tempLink = new DefaultMutableTreeNode(route.getStartLink());
            tempRoute.add(tempLink);
            appendChild(route.getStartLink(), tempLink);
        }

        treeModel.reload();
    }
    /* DO NOT TOUCH */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Create a story tree in the routes panel
     *
     * @param story Story the story
     */
    public void createStoryTree(Story story) {
        this.story = story;
        storyNode = new DefaultMutableTreeNode(story);
        treeModel = new DefaultTreeModel(storyNode);
        refreshList(story.getRoutes());

        treeModel = new DefaultTreeModel(storyNode);

        tree = new JTree(treeModel);
        tree.setCellRenderer(new RoutesTreeRenderer());
        add(tree);
    }

    /**
     * Expand a tree path to an item
     *
     * @param dmt DefaultMutableTreeNode Start tree node
     * @param obj Object the object which has to expand
     */
    public void expandToNode(DefaultMutableTreeNode dmt, Object obj) {
        Enumeration a = dmt.children();

        while (a.hasMoreElements()) {
            DefaultMutableTreeNode tempTreeNode = (DefaultMutableTreeNode) a.nextElement();
            if (tempTreeNode.getUserObject() == obj) {
                tree.expandPath(new TreePath(tempTreeNode.getPath()).getParentPath());

                break;
            } else {
                expandToNode(tempTreeNode, obj);
            }
        }
    }

    /**
     * Get the tree model
     *
     * @return DefaultTreeModel the current tree model
     */
    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    /**
     * Get the start node from the story
     *
     * @return DefaultMutableTreeNode the start node from the story
     */
    public DefaultMutableTreeNode getStoryNode() {
        return storyNode;
    }
}