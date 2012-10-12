package View;

import Model.FileChooser;
import Model.Link;
import Model.MediaItem;
import Model.MediaItemTableModel;
import Model.Route;
import Model.Text;
import Model.Video;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Popup Window for adding media to a link
 */
public final class AddMedia extends JDialog {

    // Variables
    private static final Logger LOGGER = Logger.getLogger(AddMedia.class.getName());
    private JScrollPane spAddedMedia;
    private JTable tAddedMedia;
    private MediaItemTableModel tableModel;
    private ArrayList<MediaItem> addedItems;
    private DefaultListModel listModel;
    private ArrayList<Link> prevLinks;
    private boolean closedBySave;
    private Link link;
    private int callFrom;
    private View.Map map;
    private boolean changeLink;

    /**
     * Creates form addMedia
     *
     * @param parent JFrame The Main window of this application is the parent of this window
     * @param map Instance of Class View.Map
     * @param link Link The Link we are creating
     */
    public AddMedia(Main parent, final View.Map map, Link link) {
        super(parent, true);

        initComponents();

        // Set variables
        this.map = map;
        this.link = link;
        this.closedBySave = false;
        this.callFrom = (link.getP2() == null) ? 1 : 0;
        this.tfRouteName.setEditable(false);
        this.tfRouteName.setText(map.getStory().getRouteForLink(link).getName());
        this.tfLinkName.setText(link.getName());
        this.changeLink = true;
        this.addedItems = new ArrayList<MediaItem>();
        this.spAddedMedia = new JScrollPane();
        this.tAddedMedia = new JTable();
        this.tableModel = new MediaItemTableModel();

        // Get the size of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        // Determine the new location of the window
        int x = (dim.width - (this.getSize().width)) / 2;
        int y = (dim.height - (this.getSize().height)) / 2;

        // Center the window
        this.setLocation(x, y);

        // User is creating a startnode and not a Link
        if (this.callFrom == 1) {

            // Set title and route
            this.lTitle.setText("Startnode Properties");

            // Enable routename and disable prev links
            this.tfRouteName.setEditable(true);
            this.lLinks.setEnabled(false);

        } // User is NOT creating a startnode, but a new link
        else {
            this.prevLinks = new ArrayList<Link>();
            this.prevLinks.add(map.getStory().getParentFromLink(this.link));

            // Check if this link has twins and add their parent to the list
            Link twin = this.link.getTwin();
            while (twin != null) {
                this.prevLinks.add(map.getStory().getParentFromLink(twin));
                twin = this.link.getTwin();
            }

            // Check if there is a link that has this link as a twin
            twin = map.getStory().getTwins(this.link);
            while (twin != null) {
                this.prevLinks.add(map.getStory().getParentFromLink(twin));
                twin = map.getStory().getTwins(twin);
            }
            this.lLinks.setListData(this.prevLinks.toArray());
            this.lLinks.setEnabled(false);
        }

        // Generate JTable so we can add MediaItems to it
        generateTable();

        // MediaItems
        for (MediaItem mItem : link.getMediaItems()) {
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
        }

        // Add window listener to this window
        this.addWindowListener(new WindowAdapter() {
            /**
             * Add window closing event
             *
             * @param evt WindowEvent The window closing event
             */
            @Override
            public void windowClosing(WindowEvent evt) {

                // When user closed window (so without saving!)
                if (!closedBySave) {

                    // Notify user that nothing is going to be saved and
                    // that the created link (drawing) will be deleted.
                    Object[] options = {"Close", "Cancel"};
                    int n = JOptionPane.showOptionDialog(null,
                            "By pressing 'Close' the Link will be deleted and" + "\n"
                            + "nothing will be saved. Otherwise press 'Cancel'.", // message
                            "Deleted created Link", // title
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, // do not use a custom Icon
                            options, // the titles of buttons
                            options[0]); // default button title

                    // user pressed cancel
                    if (n == 1) {
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    } // user pressed close
                    else {
                        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    }
                }
            }
        });

        // Add mouse listener to table with added mediaitems
        addMouseListenerToAddedMediaItemsTable();

        // Add changelistener to combobox with previous links
        this.lLinks.addListSelectionListener(new ListSelectionListener() {
            /**
             * Set Route name on change
             *
             * @param e ActionEvent
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object[] objArray = lLinks.getSelectedValues();
                String tfRouteNameText = "";
                for (Object obj : objArray) {
                    Link link = (Link) obj;
                    Route route = map.getStory().getRouteForLink(link);
                    if (route != null) {
                        if (!tfRouteNameText.equals("")) {
                            tfRouteNameText += ", " + route.getName();
                        } else {
                            tfRouteNameText += route.getName();
                        }
                    }
                }
                tfRouteName.setText(tfRouteNameText);
            }
        });
    }

    /**
     * Overload Constructor
     *
     * Creates form addMedia
     *
     * @param parent JFrame The Main window of this application is the parent of this window
     * @param prevLinks ArrayList<Link> List with all the previous Link we can connect this Link to
     * @param link Link The Link we are creating
     * @param callFrom int if int = 1, we are creating a startnode (Link)
     * @param routeName String Name of the Route we are adding Links to
     */
    public AddMedia(Main parent, final View.Map map, ArrayList<Link> prevLinks, Link link, int callFrom) {
        super(parent, true);

        initComponents();

        // Set variables
        this.prevLinks = prevLinks;
        this.map = map;
        this.closedBySave = false;
        this.link = link;
        this.callFrom = callFrom;
        this.tfRouteName.setEditable(false);
        this.changeLink = false;
        this.addedItems = new ArrayList<MediaItem>();
        this.spAddedMedia = new JScrollPane();
        this.tAddedMedia = new JTable();
        this.tableModel = new MediaItemTableModel();

        // Get the size of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        // Determine the new location of the window
        int x = (dim.width - (this.getSize().width)) / 2;
        int y = (dim.height - (this.getSize().height)) / 2;

        // Center the window
        this.setLocation(x, y);

        // User is creating a startnode and not a Link
        if (this.callFrom == 1) {

            // Set title and route
            this.lTitle.setText("Startnode Properties");

            // Enable routename and disable prev links
            this.tfRouteName.setEditable(true);
            this.lLinks.setEnabled(false);

        } // User is NOT creating a startnode, but a new link
        else {
            // Remove JTextField 'tfRouteName'
            BorderLayout layout = (BorderLayout) pRouteName.getLayout();
            pRouteName.remove(layout.getLayoutComponent(BorderLayout.CENTER));
            pRouteName.revalidate();

            // Set previous links in list
            this.lLinks.setListData(this.prevLinks.toArray());
        }

        // Generate JTable so we can add MediaItems to it
        generateTable();

        // Add window listener to this window
        this.addWindowListener(new WindowAdapter() {
            /**
             * Add window closing event
             *
             * @param evt WindowEvent The window closing event
             */
            @Override
            public void windowClosing(WindowEvent evt) {

                // When user closed window (so without saving!)
                if (!closedBySave) {

                    // Notify user that nothing is going to be saved and
                    // that the created link (drawing) will be deleted.
                    Object[] options = {"Close", "Cancel"};
                    int n = JOptionPane.showOptionDialog(null,
                            "By pressing 'Close' the Link will be deleted and" + "\n"
                            + "nothing will be saved. Otherwise press 'Cancel'.", // message
                            "Deleted created Link", // title
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, // do not use a custom Icon
                            options, // the titles of buttons
                            options[0]); // default button title

                    // user pressed cancel
                    if (n == 1) {
                        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    } // user pressed close
                    else {
                        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    }
                }
            }
        });

        // Add mouse listener to table with added mediaitems
        addMouseListenerToAddedMediaItemsTable();

        // Add changelistener to combobox with previous links
        this.lLinks.addListSelectionListener(new ListSelectionListener() {
            /**
             * Set Route name on change
             *
             * @param e ActionEvent
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object[] objArray = lLinks.getSelectedValues();
                String tfRouteNameText = "";
                for (Object obj : objArray) {
                    Link link = (Link) obj;
                    Route route = map.getStory().getRouteForLink(link);
                    if (route != null) {
                        if (!tfRouteNameText.equals("")) {
                            tfRouteNameText += ", " + route.getName();
                        } else {
                            tfRouteNameText += route.getName();
                        }
                    }
                }

                // Set route name as JLabel with a JLabel
                pRouteName.add(new JLabel(tfRouteNameText), BorderLayout.CENTER);
                pRouteName.revalidate();
            }
        });
    }

    /**
     * Get the MediaItem object of the row we clicked
     */
    private void addMouseListenerToAddedMediaItemsTable() {
        this.tAddedMedia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    /**
     * Adds a MediaItem to the addedItems list and the name of the MediaItem to the drag and drop list.
     *
     * @param mediaItem MediaItem The MediaItem (instance of Video, Text or Image) we want to add
     */
    public void addItem(MediaItem mediaItem) {
        this.addedItems.add(mediaItem);

        // Add to table
        addMediaItemToTable(mediaItem);
    }

    /**
     * Removes a MediaItem from the addedItems list and removes the name from the drag and drop list
     *
     * @param mediaItem MediaItem The MediaItem (instance of Video, Text or Image) we want to remove
     */
    public void removeItem(MediaItem mediaItem) {
        this.addedItems.remove(mediaItem);

        // Remove from table
        removeMediaItemFromTable(mediaItem);
    }

    /**
     * Get all added media items. If we closed the window, then the items will be in the right order!
     *
     * @return ArrayList<MediaItem>
     */
    public ArrayList<MediaItem> getAddedItems() {
        return addedItems;
    }

    /**
     * Get added media item by file name.
     *
     * @return MediaItem
     */
    public MediaItem getAddedItemByFilename(String fileName) {
        for (MediaItem mediaItem : addedItems) {
            if (mediaItem.getFileName().equals(fileName)) {
                return mediaItem;
            }
        }
        return null;
    }

    /**
     * Get close status of this window
     *
     * @return boolean
     */
    public boolean isClosedBySave() {
        return closedBySave;
    }

    /**
     * Get close status of this window
     *
     * @return boolean
     */
    public void enterDuration(MediaItem mItem) {
        String n = JOptionPane.showInputDialog("Enter the duration of the file in seconds", mItem.getShowDurationInSeconds());
        if (n != null) {
            if (!n.equals("")) {
                try {
                    mItem.setShowDurationInSeconds(Integer.parseInt(n));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "The value entered is not a number");
                    enterDuration(mItem);
                }
            } else {
                enterDuration(mItem);
            }
        }
    }

    /**
     * Generate the custom JTable that will hold the added MediaItems
     */
    private void generateTable() {

        // Create and set model for JTable
        this.tAddedMedia.setModel(this.tableModel);
        this.tAddedMedia.getTableHeader().setReorderingAllowed(false);
        this.tAddedMedia.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.tAddedMedia.setColumnSelectionAllowed(false);
        this.tAddedMedia.setCellSelectionEnabled(false);
        this.tAddedMedia.setRowSelectionAllowed(true);
        this.tAddedMedia.revalidate();

        // Enable Drag and Drop
        this.tAddedMedia.setDragEnabled(true);
        this.tAddedMedia.setTransferHandler(new TableTransferHandler());

        // Add JTable to JScrollpane
        this.spAddedMedia.add(this.tAddedMedia);
        this.spAddedMedia.setPreferredSize(new Dimension(400, 100));

        // Add scrollpane to AddedMedia JPanel
        this.pAddedMedia.add(this.spAddedMedia, BorderLayout.CENTER);
    }

    /**
     * Add new MediaItem to table
     */
    private void addMediaItemToTable(MediaItem item) {
        this.tableModel.addRow(item);
    }

    /**
     * Remove MediaItem from table
     */
    private void removeMediaItemFromTable(MediaItem item) {
        this.tableModel.removeRow(item);
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
        pAddedMedia = new javax.swing.JPanel();
        lLinkName = new javax.swing.JLabel();
        tfLinkName = new javax.swing.JTextField();
        bSave = new javax.swing.JButton();
        lRoute = new javax.swing.JLabel();
        lSpaceBottom = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lLinks = new javax.swing.JList();
        lSpaceMediaUp = new javax.swing.JLabel();
        lSpaceMediaDown = new javax.swing.JLabel();
        pRouteName = new javax.swing.JPanel();
        lRouteName = new javax.swing.JLabel();
        tfRouteName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitle.setText("Link properties");

        bBrowse.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        bBrowse.setText("Browse");
        bBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBrowseActionPerformed(evt);
            }
        });

        pAddedMedia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "  Added media  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 15))); // NOI18N
        pAddedMedia.setLayout(new java.awt.BorderLayout());

        lLinkName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lLinkName.setText("Link name:");

        tfLinkName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        lRoute.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lRoute.setText("Previous link:");

        jScrollPane1.setViewportView(lLinks);

        pRouteName.setMaximumSize(new java.awt.Dimension(0, 40));
        pRouteName.setMinimumSize(new java.awt.Dimension(0, 40));
        pRouteName.setPreferredSize(new java.awt.Dimension(0, 40));
        pRouteName.setLayout(new java.awt.BorderLayout());

        lRouteName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lRouteName.setText("Route name:  ");
        pRouteName.add(lRouteName, java.awt.BorderLayout.LINE_START);

        tfRouteName.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tfRouteName.setToolTipText("");
        tfRouteName.setMaximumSize(new java.awt.Dimension(0, 40));
        tfRouteName.setMinimumSize(new java.awt.Dimension(0, 40));
        tfRouteName.setPreferredSize(new java.awt.Dimension(0, 40));
        pRouteName.add(tfRouteName, java.awt.BorderLayout.CENTER);

        org.jdesktop.layout.GroupLayout pMainLayout = new org.jdesktop.layout.GroupLayout(pMain);
        pMain.setLayout(pMainLayout);
        pMainLayout.setHorizontalGroup(
            pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMainLayout.createSequentialGroup()
                .addContainerGap()
                .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(pMainLayout.createSequentialGroup()
                        .add(lTitle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(6, 6, 6))
                    .add(pMainLayout.createSequentialGroup()
                        .add(lSpaceLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lSpaceTop, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(pMainLayout.createSequentialGroup()
                                .add(6, 6, 6)
                                .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(pRouteName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(pMainLayout.createSequentialGroup()
                                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(lRoute)
                                            .add(lLinkName)
                                            .add(lSpaceMediaUp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(lSpaceMediaDown, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(pMainLayout.createSequentialGroup()
                                                .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(lSpaceBottom, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .add(pMainLayout.createSequentialGroup()
                                                        .add(bBrowse)
                                                        .add(0, 0, Short.MAX_VALUE)))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(bSave))
                                            .add(tfLinkName)
                                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                                            .add(pAddedMedia, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(lSpaceRight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pMainLayout.setVerticalGroup(
            pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMainLayout.createSequentialGroup()
                .addContainerGap()
                .add(lTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(pMainLayout.createSequentialGroup()
                        .add(lSpaceLeft, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(26, 26, 26))
                    .add(pMainLayout.createSequentialGroup()
                        .add(lSpaceTop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pRouteName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(lLinkName)
                            .add(tfLinkName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(pMainLayout.createSequentialGroup()
                                .add(lRoute)
                                .add(0, 129, Short.MAX_VALUE))
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                        .add(18, 18, 18)
                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(pMainLayout.createSequentialGroup()
                                .add(lSpaceMediaUp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 197, Short.MAX_VALUE)
                                .add(lSpaceMediaDown, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(pAddedMedia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED, 42, Short.MAX_VALUE)
                        .add(pMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(pMainLayout.createSequentialGroup()
                                .add(bBrowse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(22, 22, 22)
                                .add(lSpaceBottom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, pMainLayout.createSequentialGroup()
                                .add(bSave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(17, 17, 17))))
                    .add(pMainLayout.createSequentialGroup()
                        .add(lSpaceRight, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        getContentPane().add(pMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBrowseActionPerformed
        // Create and show File Chooser Window
        FileChooser f = new FileChooser();
        JFileChooser j = f.getChooser();
        if (this.getAddedItems().size() > 0) {
            File file = new File(this.getAddedItems().get(this.getAddedItems().size() - 1).getAbsolutePath());
            j.setCurrentDirectory(file);
        }
        int dialog = j.showOpenDialog(this);

        // Catch actions of the File Chooser Dialog Window
        if (dialog == JFileChooser.APPROVE_OPTION) {

            // Get selected MediaItem by path of the File that the user selected
            MediaItem mItem = f.getMediaItemFromFile(j.getSelectedFile().getAbsolutePath().toString());

            // Add selected file to list
            if (mItem != null) {
                if (mItem instanceof Video) {
                    this.addItem((Video) mItem);
                } else if (mItem instanceof Model.Image) {
                    enterDuration(mItem);
                    this.addItem((Model.Image) mItem);
                } else if (mItem instanceof Text) {
                    enterDuration(mItem);
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

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        if (!changeLink) {
            // Get selected previous Link
            Object[] objArray = lLinks.getSelectedValues();
            // User must give this Link a name
            if (!tfLinkName.getText().trim().isEmpty()) {

                // User is creating startnode OR user must select previous link
                if (this.callFrom == 1 || objArray.length > 0) {

                    this.closedBySave = true;

                    // Get variables we want to save
                    String routeName = tfRouteName.getText().trim();
                    String linkName = tfLinkName.getText().trim();
                    ArrayList<MediaItem> mediaItems = new ArrayList<MediaItem>();
                    Object[] elements = listModel.toArray();

                    // Get all added Media Items in the right order
                    for (Object obj : elements) {
                        for (MediaItem item : addedItems) {
                            if (item.getFileName().equals(obj.toString())) {
                                mediaItems.add(item);
                                break;
                            }
                        }
                    }

                    // Set this link variables
                    this.link.setName(linkName);
                    this.link.setItems(mediaItems);

                    // User is creating a start node
                    if (this.callFrom == 1) {

                        // Create new Route
                        map.getStory().newRoute(routeName, this.link);

                    } else {
                        // Add this link to the selected previous link (so this is the next link for that previous link)
                        for (Object object : objArray) {
                            Link prevLink = (Link) object;
                            // If user selected more than one previous link, we have to make 2 new links instead of using the same one
                            if (objArray.length > 1) {
                                // Make a new link so the link won't be saved twice.
                                Link newLink = new Link(this.link, map.getStory().getLinkCounter());
                                prevLink.addLink(newLink);
                            } else {
                                prevLink.addLink(this.link);
                            }
                        }
                        // Refresh routes list (treeview)
                        map.getStory().repaint();
                    }

                    // Set changeboolean to true
                    map.getStory().setSomethingChanged(true);

                    // Close this window and bring main window to the front
                    super.toFront();
                    this.dispose();

                } // User did NOT select previous Link
                else {
                    JOptionPane.showMessageDialog(null,
                            "You must select a previous Link!", // message
                            "Info", // title
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } // User did NOT give this Link a name
            else {
                JOptionPane.showMessageDialog(null,
                        "You must name this Link!", // message
                        "Info", // title
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // User must give this Link a name
            if (!tfLinkName.getText().trim().isEmpty()) {

                this.closedBySave = true;

                // Get variables we want to save
                String linkName = tfLinkName.getText().trim();
                ArrayList<MediaItem> mediaItems = new ArrayList<MediaItem>();
                Object[] elements = listModel.toArray();

                // Get all added Media Items in the right order
                for (Object obj : elements) {
                    for (MediaItem item : addedItems) {
                        if (item.getFileName().equals(obj.toString())) {
                            mediaItems.add(item);
                            break;
                        }
                    }
                }

                // Set this link variables
                this.link.setName(linkName);
                this.link.setItems(mediaItems);

                // Set changeboolean to true
                map.getStory().setSomethingChanged(true);

                // Close this window and bring main window to the front
                super.toFront();
                this.dispose();
            } // User did NOT give this Link a name
            else {
                JOptionPane.showMessageDialog(null,
                        "You must name this Link!", // message
                        "Info", // title
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_bSaveActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBrowse;
    private javax.swing.JButton bSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lLinkName;
    private javax.swing.JList lLinks;
    private javax.swing.JLabel lRoute;
    private javax.swing.JLabel lRouteName;
    private javax.swing.JLabel lSpaceBottom;
    private javax.swing.JLabel lSpaceLeft;
    private javax.swing.JLabel lSpaceMediaDown;
    private javax.swing.JLabel lSpaceMediaUp;
    private javax.swing.JLabel lSpaceRight;
    private javax.swing.JLabel lSpaceTop;
    private javax.swing.JLabel lTitle;
    private javax.swing.JPanel pAddedMedia;
    private javax.swing.JPanel pMain;
    private javax.swing.JPanel pRouteName;
    private javax.swing.JTextField tfLinkName;
    private javax.swing.JTextField tfRouteName;
    // End of variables declaration//GEN-END:variables
}

/*
 * StringTransferHandler.java is used by the 1.4 ExtendedDnDDemo.java example.
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
                String str = (String) t
                        .getTransferData(DataFlavor.stringFlavor);
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

/*
 * TableTransferHandler.java is used by the 1.4 ExtendedDnDDemo.java example.
 */
class TableTransferHandler extends StringTransferHandler {

    private int[] rows = null;
    private int addIndex = -1; //Location where items were added
    private int addCount = 0; //Number of items added.

    @Override
    protected String exportString(JComponent c) {
        JTable table = (JTable) c;
        rows = table.getSelectedRows();
        int colCount = table.getColumnCount();

        StringBuilder buff = new StringBuilder();

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < colCount; j++) {
                Object val = table.getValueAt(rows[i], j);
                buff.append(val == null ? "" : val.toString());
                if (j != colCount - 1) {
                    buff.append(",");
                }
            }
            if (i != rows.length - 1) {
                buff.append("\n");
            }
        }

        return buff.toString();
    }

    @Override
    protected void importString(JComponent c, String str) {
        JTable target = (JTable) c;
        DefaultTableModel model = (DefaultTableModel) target.getModel();
        int index = target.getSelectedRow();

        //Prevent the user from dropping data back on itself.
        //For example, if the user is moving rows #4,#5,#6 and #7 and
        //attempts to insert the rows after row #5, this would
        //be problematic when removing the original rows.
        //So this is not allowed.
        if (rows != null && index >= rows[0] - 1
                && index <= rows[rows.length - 1]) {
            rows = null;
            return;
        }

        int max = model.getRowCount();
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
        int colCount = target.getColumnCount();
        for (int i = 0; i < values.length && i < colCount; i++) {
            model.insertRow(index++, values[i].split(","));
        }
    }

    @Override
    protected void cleanup(JComponent c, boolean remove) {
        JTable source = (JTable) c;
        if (remove && rows != null) {
            DefaultTableModel model = (DefaultTableModel) source.getModel();

            //If we are moving items around in the same table, we
            //need to adjust the rows accordingly, since those
            //after the insertion point have moved.
            if (addCount > 0) {
                for (int i = 0; i < rows.length; i++) {
                    if (rows[i] > addIndex) {
                        rows[i] += addCount;
                    }
                }
            }
            for (int i = rows.length - 1; i >= 0; i--) {
                model.removeRow(rows[i]);
            }
        }
        rows = null;
        addCount = 0;
        addIndex = -1;
    }
}
