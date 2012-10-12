package View;

import Model.Link;
import Model.Node;
import Model.Route;
import Model.Story;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

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
            DefaultMutableTreeNode tempRoute = new DefaultMutableTreeNode(route);
            storyNode.add(tempRoute);

            DefaultMutableTreeNode tempLink = new DefaultMutableTreeNode(route.getStartLink());
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
        storyNode = new DefaultMutableTreeNode(story);
        treeModel = new DefaultTreeModel(storyNode);
        refreshList(story.getRoutes());

        treeModel = new DefaultTreeModel(storyNode);

        tree = new JTree(treeModel);
        add(tree);
    }

    public void expandToNode(DefaultMutableTreeNode dmt, Object obj) {
        Enumeration a = dmt.children();

        while (a.hasMoreElements()) {
            DefaultMutableTreeNode tempTreeNode = (DefaultMutableTreeNode) a.nextElement();
            if (tempTreeNode.getUserObject() == obj) {
                tree.expandPath(new TreePath(tempTreeNode.getPath()).getParentPath());
                
                break;
            }else{
                expandToNode(tempTreeNode, obj);
            }
        }
    }

    void appendChilds(Link parentLink, DefaultMutableTreeNode parentTreeNode) {

        for (Link link : parentLink.getLinks()) {
            DefaultMutableTreeNode tempLink = new DefaultMutableTreeNode(link);
            parentTreeNode.add(tempLink);
            appendChilds(link, tempLink);
        }
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public DefaultMutableTreeNode getStoryNode() {
        return storyNode;
    }

    public JTree getTree() {
        return tree;
    }
}