package View;

import Model.Link;
import Model.Route;
import Model.Story;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 */
public class Routes extends JPanel {

    // Variables
    private JTree tree;
    private DefaultTreeModel treeModel;
    private Story story;
    private DefaultMutableTreeNode storyNode;

    /**
     * Creates new form Routes
     */
    public Routes() {
        initComponents();

    }

    public void refreshList(ArrayList<Route> routes) {
        storyNode.removeAllChildren();
        // Loop trough routes        
        for (Route route : routes) {
            DefaultMutableTreeNode tempRoute = new DefaultMutableTreeNode(route.getName());
            storyNode.add(tempRoute);

            DefaultMutableTreeNode tempLink = new DefaultMutableTreeNode(route.getStartLink().getName());
            tempRoute.add(tempLink);
            appendChilds(route.getStartLink(), tempLink);

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

    void addStory(Story story) {
        this.story = story;
        storyNode = new DefaultMutableTreeNode(story.getName());
        treeModel = new DefaultTreeModel(storyNode);
        refreshList(story.getRoutes());

        treeModel = new DefaultTreeModel(storyNode);

        tree = new JTree(treeModel);
        add(tree);
    }
        

    void appendChilds(Link parentLink, DefaultMutableTreeNode parentTreeNode) {

        for (Link link : parentLink.getLinks()) {
            DefaultMutableTreeNode tempLink = new DefaultMutableTreeNode(link.getName());
            parentTreeNode.add(tempLink);
            appendChilds(link, tempLink);
        }

    }
}