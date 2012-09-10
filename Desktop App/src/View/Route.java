package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

/**
 *
 */
public class Route extends JPanel {

    /**
     * Creates new form Route
     */
    public Route() {
        initComponents();


        // Create a list containing CheckListItem's
        JList list = new JList(new CheckListItem[]{
                    new CheckListItem("apple"),
                    new CheckListItem("orange"),
                    new CheckListItem("mango"),
                    new CheckListItem("paw paw"),
                    new CheckListItem("banana")});

        // Use a CheckListRenderer (see below)
        // to renderer list cells

        list.setCellRenderer(new CheckListRenderer());
        list.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        // Add a mouse listener to handle changing selection

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();

                // Get index of item clicked

                int index = list.locationToIndex(event.getPoint());
                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);

                // Toggle selected state

                item.setSelected(!item.isSelected());

                // Repaint cell

                list.repaint(list.getCellBounds(index, index));
            }
        });

        this.add(new JScrollPane(list), BorderLayout.CENTER);
    }

    /* DO NOT TOUCH */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

/**
 * Represents items in the JList that can be selected
 */
class CheckListItem {

    // Variables
    private String label;
    private boolean isSelected = false;

    /**
     * Constructor
     *
     * @param label String name of the CheckListItem (the checkbox)
     */
    public CheckListItem(String label) {
        this.label = label;
    }

    /**
     * Return the state of the CheckListItem (the checkbox) Checked or not checked
     *
     * @return boolean
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Set state of CheckListItem (the checkbox)
     *
     * @param isSelected boolean True of false
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * Get the name (label) of the CheckListItem (the checkbox)
     *
     * @return label of the CheckListItem (the checkbox)
     */
    @Override
    public String toString() {
        return label;
    }
}

/**
 * Handles rendering cells in the jList using a CheckBox
 */
class CheckListRenderer extends JCheckBox implements ListCellRenderer {

    /**
     * Set the list cell renderer component with our own created JList
     *
     * @param list JList
     * @param value Object
     * @param index int
     * @param isSelected boolean
     * @param hasFocus boolean
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        setEnabled(list.isEnabled());
        setSelected(((CheckListItem) value).isSelected());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(value.toString());
        return this;
    }
}