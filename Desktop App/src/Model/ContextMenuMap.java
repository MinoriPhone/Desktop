package Model;

import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenuMap extends JPopupMenu {

    // Variables
    JMenuItem deleteItem;
    JMenuItem setStartItem;
    JMenuItem createLinkItem;
    JMenuItem changeLinkItem;
    JMenuItem changeRadiusItem;

    /**
     * Constructor
     */
    public ContextMenuMap() {
        deleteItem = new JMenuItem("Delete node");
        setStartItem = new JMenuItem("Make start");
        createLinkItem = new JMenuItem("Create link");
        changeLinkItem = new JMenuItem("Change start");
        changeRadiusItem = new JMenuItem("Change radius");
        add(deleteItem);
        add(setStartItem);
        add(createLinkItem);
        add(changeLinkItem);
        add(changeRadiusItem);
    }

    /**
     * TODO
     * 
     * @param e 
     */
    public void showContextMenuMap(MouseEvent e) {
        this.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * TODO
     * 
     * @return 
     */
    public JMenuItem getDeleteItem() {
        return deleteItem;
    }

    /**
     * TODO 
     * 
     * @param deleteItem 
     */
    public void setDeleteItem(JMenuItem deleteItem) {
        this.deleteItem = deleteItem;
    }

    /**
     * TODO 
     * 
     * @return 
     */
    public JMenuItem getSetStartItem() {
        return setStartItem;
    }

    /**
     * TODO 
     * 
     * @param setStartItem 
     */
    public void setSetStartItem(JMenuItem setStartItem) {
        this.setStartItem = setStartItem;
    }

    /**
     * TODO 
     * 
     * @return 
     */
    public JMenuItem getCreateLinkItem() {
        return createLinkItem;
    }

    /**
     * TODO 
     * 
     * @param createLinkItem 
     */
    public void setCreateLinkItem(JMenuItem createLinkItem) {
        this.createLinkItem = createLinkItem;
    }

    public JMenuItem getChangeLinkItem() {
        return changeLinkItem;
    }

    public void setChangeLinkItem(JMenuItem changeLinkItem) {
        this.changeLinkItem = changeLinkItem;
    }

    public JMenuItem getChangeRadiusItem() {
        return changeRadiusItem;
    }

    public void setChangeRadiusItem(JMenuItem changeRadiusItem) {
        this.changeRadiusItem = changeRadiusItem;
    }
    
}
