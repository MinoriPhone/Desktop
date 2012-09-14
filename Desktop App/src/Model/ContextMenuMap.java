package Model;

import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenuMap extends JPopupMenu {

    JMenuItem deleteItem;
    JMenuItem setStartItem;
    JMenuItem createLinkItem;

    public ContextMenuMap() {
        deleteItem = new JMenuItem("Delete node");
        setStartItem = new JMenuItem("Make start");
        createLinkItem = new JMenuItem("Create Link");
        add(deleteItem);
        add(setStartItem);
        add(createLinkItem);
    }

    public void showContextMenuMap(MouseEvent e) {
        this.show(e.getComponent(), e.getX(), e.getY());
    }

    public JMenuItem getDeleteItem() {
        return deleteItem;
    }

    public void setDeleteItem(JMenuItem deleteItem) {
        this.deleteItem = deleteItem;
    }

    public JMenuItem getSetStartItem() {
        return setStartItem;
    }

    public void setSetStartItem(JMenuItem setStartItem) {
        this.setStartItem = setStartItem;
    }

    public JMenuItem getCreateLinkItem() {
        return createLinkItem;
    }

    public void setCreateLinkItem(JMenuItem createLinkItem) {
        this.createLinkItem = createLinkItem;
    }
}
