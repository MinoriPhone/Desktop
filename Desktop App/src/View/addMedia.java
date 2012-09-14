package View;

import Model.FileChooser;
import Model.MediaItem;
import Model.Text;
import Model.Video;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

/**
 * Popup Window for adding media to a link
 */
public class addMedia extends JDialog {

    // Variables
    private static final Logger LOGGER = Logger.getLogger(addMedia.class.getName());
    private ArrayList<MediaItem> addedItems;
    private DefaultListModel listModel;
    private JList list;
    private JScrollPane scrollPane;

    /**
     * Creates new form addMedia
     */
    public addMedia(JFrame parent) {
        // Make sure we can NOT touch main until this window is diposed
        super(parent, true);

        initComponents();

        // List model
        this.listModel = new DefaultListModel();

        // Lists
        this.addedItems = new ArrayList<MediaItem>();
        this.list = new JList(this.listModel);
        this.list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // ScrollPane
        this.scrollPane = new JScrollPane(this.list);
        this.scrollPane.setPreferredSize(new Dimension(400, 100));
        this.pAddedMedia.add(this.scrollPane, BorderLayout.CENTER);

        // Enable drag and drop
        this.list.setDragEnabled(true);
        this.list.setTransferHandler(new ListTransferHandler());
    }

    /**
     * Adds a MediaItem to the addedItems list and the name of the MediaItem to the drag and drop list.
     *
     * @param mediaItem MediaItem The MediaItem (instance of Video, Text or Image) we want to add
     */
    public void addItem(MediaItem mediaItem) {
        this.addedItems.add(mediaItem);
        this.listModel.addElement(mediaItem.getFileName());
    }

    /**
     * Removes a MediaItem from the addedItems list and removes the name from the drag and drop list
     *
     * @param mediaItem MediaItem The MediaItem (instance of Video, Text or Image) we want to remove
     */
    public void removeItem(MediaItem mediaItem) {
        this.addedItems.remove(mediaItem);
        this.listModel.removeElement(mediaItem.getFileName());
    }

    /* DO NOT TOUCH */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMain = new javax.swing.JPanel();
        lTitle = new javax.swing.JLabel();
        lSpaceLeft = new javax.swing.JLabel();
        lSpaceRight = new javax.swing.JLabel();
        lSpaceTop = new javax.swing.JLabel();
        bBrowse = new javax.swing.JButton();
        lSpaceBetween = new javax.swing.JLabel();
        pAddedMedia = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitle.setText("Add media to Link");

        bBrowse.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        bBrowse.setText("Browse");
        bBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBrowseActionPerformed(evt);
            }
        });

        pAddedMedia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "  Added media  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N
        pAddedMedia.setOpaque(false);
        pAddedMedia.setLayout(new java.awt.BorderLayout());

        org.jdesktop.layout.GroupLayout pMainLayout = new org.jdesktop.layout.GroupLayout(pMain);
        pMain.setLayout(pMainLayout);
        pMainLayout.setHorizontalGroup(
            pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMainLayout.createSequentialGroup()
                .addContainerGap()
                .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(lTitle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(pMainLayout.createSequentialGroup()
                        .add(lSpaceLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(pAddedMedia, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(lSpaceTop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 435, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(bBrowse))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(lSpaceRight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lSpaceBetween, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pMainLayout.setVerticalGroup(
            pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMainLayout.createSequentialGroup()
                .addContainerGap()
                .add(lTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(pMainLayout.createSequentialGroup()
                        .add(lSpaceTop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(pMainLayout.createSequentialGroup()
                                .add(58, 58, 58)
                                .add(lSpaceBetween, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(pMainLayout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(pAddedMedia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 193, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(bBrowse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(lSpaceRight, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .add(lSpaceLeft, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBrowseActionPerformed
        // Create and show File Chooser Window
        FileChooser f = new FileChooser();
        JFileChooser j = f.getChooser();
        int dialog = j.showOpenDialog(this);

        // Catch actions of the File Chooser Dialog Window
        if (dialog == JFileChooser.APPROVE_OPTION) {

            // Get selected MediaItem by path of the File that the user selected
            MediaItem mItem = f.getMediaItemFromFile(j.getSelectedFile().getPath().toString());

            // Add selected file to list
            if (mItem != null) {
                if (mItem instanceof Video) {
                    this.addItem((Video) mItem);
                } else if (mItem instanceof Model.Image) {
                    this.addItem((Model.Image) mItem);
                } else if (mItem instanceof Text) {
                    this.addItem((Text) mItem);
                }
            }
        } else if (dialog == JFileChooser.CANCEL_OPTION) {
            // User canceled uploaden file
            LOGGER.log(Level.INFO, "File Chooser is canceled by user");
        } else {
            // Error or dialog is dismissed
            LOGGER.log(Level.WARNING, "File Chooser returns error");
        }
    }//GEN-LAST:event_bBrowseActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBrowse;
    private javax.swing.JLabel lSpaceBetween;
    private javax.swing.JLabel lSpaceLeft;
    private javax.swing.JLabel lSpaceRight;
    private javax.swing.JLabel lSpaceTop;
    private javax.swing.JLabel lTitle;
    private javax.swing.JPanel pAddedMedia;
    private javax.swing.JPanel pMain;
    // End of variables declaration//GEN-END:variables
}

/**
 * Custom StringTransferHandler for dragging and dropping items in a list
 */
class ListTransferHandler extends StringTransferHandler {

    // Variables
    private int[] indices = null;
    private int addIndex = -1; // Location where items were added
    private int addCount = 0; // Number of items added

    /**
     * Bunde up the selected items in the list as a single string for export
     *
     * @param c JComponent The parent of the list items
     * @return String with selected items
     */
    @Override
    protected String exportString(JComponent c) {
        JList list = (JList) c;
        indices = list.getSelectedIndices();
        Object[] values = list.getSelectedValues();

        StringBuilder buff = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            Object val = values[i];
            buff.append(val == null ? "" : val.toString());
            if (i != values.length - 1) {
                buff.append("\n");
            }
        }

        return buff.toString();
    }

    /**
     * Take the incoming string and wherever there is a newline, break it into a separate item in the list.
     *
     * @param c JComponent
     * @param str String
     */
    @Override
    protected void importString(JComponent c, String str) {
        JList target = (JList) c;
        DefaultListModel listModel = (DefaultListModel) target.getModel();
        int index = target.getSelectedIndex();

        /**
         * Prevent the user from dropping data back on itself. For example, if the user is moving items #4, #5, #6 and #7 and attempts
         * to insert the items after item #5, this would be problematic when removing the original items. So this is not allowed.
         */
        if (indices != null && index >= indices[0] - 1
                && index <= indices[indices.length - 1]) {
            indices = null;
            return;
        }

        int max = listModel.getSize();
        if (index < 0) {
            index = max;
        } else {
            index++;
            if (index > max) {
                index = max;
            }
        }

        addIndex = index;
        String[] values = str.split("\n");
        addCount = values.length;

        for (int i = 0; i < values.length; i++) {
            listModel.add(index++, values[i]);
        }
    }

    /**
     * If the remove argument is true, the drop has been successful and it's time to remove the selected items from the list. If the
     * remove argument is false, it was a Copy operation and the original list is left intact.
     *
     * @param c JComponent
     * @param remove boolean
     */
    @Override
    protected void cleanup(JComponent c, boolean remove) {
        if (remove && indices != null) {
            JList source = (JList) c;
            DefaultListModel model = (DefaultListModel) source.getModel();

            /**
             * If we are moving items around in the same list, we need to adjust the indices accordingly, since those after the
             * insertion point have moved.
             */
            if (addCount > 0) {
                for (int i = 0; i < indices.length; i++) {
                    if (indices[i] > addIndex) {
                        indices[i] += addCount;
                    }
                }
            }

            for (int i = indices.length - 1; i >= 0; i--) {
                model.remove(indices[i]);
            }

        }

        indices = null;
        addCount = 0;
        addIndex = -1;
    }
}

/*
 * Custom TransferHandler for handling strings while dragging and dropping
 */
abstract class StringTransferHandler extends TransferHandler {

    protected abstract String exportString(JComponent c);

    protected abstract void importString(JComponent c, String str);

    protected abstract void cleanup(JComponent c, boolean remove);

    @Override
    protected Transferable createTransferable(JComponent c) {
        return new StringSelection(exportString(c));
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public boolean importData(JComponent c, Transferable t) {
        if (canImport(c, t.getTransferDataFlavors())) {
            try {
                String str = (String) t.getTransferData(DataFlavor.stringFlavor);
                importString(c, str);
                return true;
            } catch (UnsupportedFlavorException ufe) {
            } catch (IOException ioe) {
            }
        }
        return false;
    }

    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {
        cleanup(c, action == MOVE);
    }

    @Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
            if (DataFlavor.stringFlavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }
}