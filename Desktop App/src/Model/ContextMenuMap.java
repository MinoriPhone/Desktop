package Model;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenuMap extends JPopupMenu {

    // Variables
    private JMenuItem deleteItem;
    private JMenuItem setStartItem;
    private JMenuItem createLinkItem;
    private JMenuItem changeLinkItem;
    private JMenuItem changeRadiusItem;
    private ArrayList<JMenuItem> menuItems;

    /**
     * Constructor
     */
    public ContextMenuMap() {
        this.deleteItem = new JMenuItem("Delete node");
        this.setStartItem = new JMenuItem("Make start");
        this.createLinkItem = new JMenuItem("Create link");
        this.changeLinkItem = new JMenuItem("Change start");
        this.changeRadiusItem = new JMenuItem("Change radius");
        add(this.deleteItem);
        add(this.setStartItem);
        add(this.createLinkItem);
        add(this.changeLinkItem);
        add(this.changeRadiusItem);
    }

    /**
     * Overload Constructor
     *
     * @param links ArrayList<Link>
     */
    public ContextMenuMap(ArrayList<Link> links) {
        this.menuItems = new ArrayList<JMenuItem>();
        for (Link link : links) {
            JMenuItem menuItem = new JMenuItem(link.getName());
            this.menuItems.add(menuItem);
            add(menuItem);
        }
    }

    /**
     * Show menu click on Node
     *
     * @param e MouseEvent
     */
    public void showContextMenuMap(MouseEvent e) {
        this.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * Get the menu item that has the function to delete a Node
     *
     * @return JMenuItem
     */
    public JMenuItem getDeleteItem() {
        return this.deleteItem;
    }

    /**
     * Set a menu item for deleting a Node
     *
     * @param deleteItem JMenuItem
     */
    public void setDeleteItem(JMenuItem deleteItem) {
        this.deleteItem = deleteItem;
    }

    /**
     * Get the menu item that has the function to create a startNode
     *
     * @return JMenuItem
     */
    public JMenuItem getSetStartItem() {
        return this.setStartItem;
    }

    /**
     * Set a menu item for setting a startNode
     *
     * @param setStartItem JMenuItem
     */
    public void setSetStartItem(JMenuItem setStartItem) {
        this.setStartItem = setStartItem;
    }

    /**
     * Get the menu item that has the function to create a Link
     *
     * @return JMenuItem
     */
    public JMenuItem getCreateLinkItem() {
        return this.createLinkItem;
    }

    /**
     * Set a menu item for creating a Link
     *
     * @param createLinkItem JMenuItem
     */
    public void setCreateLinkItem(JMenuItem createLinkItem) {
        this.createLinkItem = createLinkItem;
    }

    /**
     * Get the menu item that has the function to change a Link
     *
     * @return JMenuItem
     */
    public JMenuItem getChangeLinkItem() {
        return this.changeLinkItem;
    }

    /**
     * Set a menu item for changing a Link
     *
     * @param changeLinkItem JMenuItem
     */
    public void setChangeLinkItem(JMenuItem changeLinkItem) {
        this.changeLinkItem = changeLinkItem;
    }

    /**
     * Get the menu item that has the function to change the radius of a Node
     *
     * @return JMenuItem
     */
    public JMenuItem getChangeRadiusItem() {
        return this.changeRadiusItem;
    }

    /**
     * Set a menu item for changing the radius of a Node
     *
     * @param changeRadiusItem JMenuItem
     */
    public void setChangeRadiusItem(JMenuItem changeRadiusItem) {
        this.changeRadiusItem = changeRadiusItem;
    }

    /**
     * Get all menu items
     *
     * @return ArrayList<JMenuItem>
     */
    public ArrayList<JMenuItem> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Set all menu items
     *
     * @param menuItems ArrayList<JMenuItem>
     */
    public void setMenuItems(ArrayList<JMenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
