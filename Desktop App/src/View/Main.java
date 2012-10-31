package View;

import Model.Application;
import Model.ExtensionFileFilter;
import Model.Image;
import Model.Link;
import Model.MediaItem;
import Model.Node;
import Model.Story;
import Model.Text;
import Model.Video;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;

/**
 * The Main screen of the application
 */
public class Main extends JFrame implements PropertyChangeListener {

    // Variables
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private Map map;
    private Story story;
    private Routes panelRoutes;
    private ProgressMonitor progressMonitor;
    private Task task;
    private boolean close = false;
    private String currentMediaItemName;
    private final String defaultStoryName;
    private HashMap<MediaItem, Link> exportMediaItems;

    private enum TaskOptions {

        IMPORT, EXPORT
    };

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        // Get the size of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        // Determine the new location of the window
        int x = (dim.width - (this.getSize().width)) / 2;
        int y = (dim.height - (this.getSize().height)) / 2;

        // Center the window
        this.setLocation(x, y);

        // Set the exportMediaItem for the export
        exportMediaItems = new HashMap<MediaItem, Link>();

        // Change title
        defaultStoryName = "New Story";
        this.setTitle(defaultStoryName + " - iStory designer " + Application.getVersion());

        // Add Routes
        panelRoutes = new Routes();
        pMenu.add(panelRoutes, BorderLayout.CENTER);

        // Create a story
        story = new Story(defaultStoryName, panelRoutes, this);

        // Add Map
        map = new Map(story, this);
        panelRoutes.addStory(story);
        pMain.add(this.map, BorderLayout.CENTER);

        // Add accelerators
        miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        miClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));

        miImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        miExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        // Lister to prevent the application from closing when the user did something change
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                checkChangeBeforeClose();
            }
        });

        // Revalidate JPanels
        this.pack();
    }

    private void checkChangeBeforeClose() {
        if (story.isSomethingChanged()) {

            // Show save confirm window
            int n = JOptionPane.showConfirmDialog(null,
                    "You made one or several changes to the current story.\n"
                    + "Do you want to save this before closing the program?\n",
                    "Save?",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                close = true;
                saveStory();
            } else if (n == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    private void triggerProgressTask(TaskOptions taskOption) {
        if (taskOption == TaskOptions.EXPORT) {
            progressMonitor = new ProgressMonitor(Main.this, "Exporting iStory..", "", 0, 100);
            progressMonitor.setProgress(0);
            task = new Task();
            task.setTaskOption(TaskOptions.EXPORT);
            task.addPropertyChangeListener(this);
            task.execute();
        } else if (taskOption == TaskOptions.IMPORT) {
            progressMonitor = new ProgressMonitor(Main.this, "Importing iStory..", "", 0, 100);
            progressMonitor.setProgress(0);
            task = new Task();
            task.setTaskOption(TaskOptions.IMPORT);
            task.addPropertyChangeListener(this);
            task.execute();
        }
    }

    /**
     * Returns the default story name
     *
     * @return the default story name
     */
    public String getDefaultStoryName() {
        return defaultStoryName;
    }

    public Routes getPanelRoutes() {
        return panelRoutes;
    }

    /* DO NOT TOUCH */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMain = new javax.swing.JPanel();
        pMenu = new javax.swing.JPanel();
        pMenuButtons = new javax.swing.JPanel();
        bNode = new javax.swing.JButton();
        bLink = new javax.swing.JButton();
        bStart = new javax.swing.JButton();
        mbMenubar = new javax.swing.JMenuBar();
        mFile = new javax.swing.JMenu();
        miNew = new javax.swing.JMenuItem();
        miOpen = new javax.swing.JMenuItem();
        miSave = new javax.swing.JMenuItem();
        sepFile = new javax.swing.JPopupMenu.Separator();
        miImport = new javax.swing.JMenuItem();
        miExport = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miClose = new javax.swing.JMenuItem();
        mSettings = new javax.swing.JMenu();
        miProject = new javax.swing.JMenuItem();
        mHelp = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setName("fMain"); // NOI18N

        pMain.setLayout(new java.awt.BorderLayout());

        pMenu.setPreferredSize(new java.awt.Dimension(200, 500));
        pMenu.setLayout(new java.awt.BorderLayout());

        bNode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/blue_waypoint.png"))); // NOI18N
        bNode.setText("Node");
        bNode.setToolTipText("");
        bNode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bNode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNodeActionPerformed(evt);
            }
        });

        bLink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/red_link.png"))); // NOI18N
        bLink.setText("Link");
        bLink.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLinkActionPerformed(evt);
            }
        });

        bStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/green_start.png"))); // NOI18N
        bStart.setText("Start");
        bStart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pMenuButtonsLayout = new org.jdesktop.layout.GroupLayout(pMenuButtons);
        pMenuButtons.setLayout(pMenuButtonsLayout);
        pMenuButtonsLayout.setHorizontalGroup(
            pMenuButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMenuButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .add(pMenuButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, bLink, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, bNode, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .add(bStart, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pMenuButtonsLayout.setVerticalGroup(
            pMenuButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMenuButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .add(bNode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(bStart)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(bLink, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pMenu.add(pMenuButtons, java.awt.BorderLayout.PAGE_START);

        pMain.add(pMenu, java.awt.BorderLayout.LINE_START);

        mFile.setText("File");

        miNew.setText("New");
        miNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewActionPerformed(evt);
            }
        });
        mFile.add(miNew);

        miOpen.setText("Open");
        miOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miOpenActionPerformed(evt);
            }
        });
        mFile.add(miOpen);

        miSave.setText("Save");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveActionPerformed(evt);
            }
        });
        mFile.add(miSave);
        mFile.add(sepFile);

        miImport.setText("Import");
        miImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImportActionPerformed(evt);
            }
        });
        mFile.add(miImport);

        miExport.setText("Export");
        miExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExportActionPerformed(evt);
            }
        });
        mFile.add(miExport);
        mFile.add(jSeparator1);

        miClose.setText("Close");
        miClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCloseActionPerformed(evt);
            }
        });
        mFile.add(miClose);

        mbMenubar.add(mFile);

        mSettings.setText("Settings");

        miProject.setText("Project");
        miProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProjectActionPerformed(evt);
            }
        });
        mSettings.add(miProject);

        mbMenubar.add(mSettings);

        mHelp.setText("Help");
        mHelp.setToolTipText("");

        miAbout.setText("About");
        miAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAboutActionPerformed(evt);
            }
        });
        mHelp.add(miAbout);

        mbMenubar.add(mHelp);

        setJMenuBar(mbMenubar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAboutActionPerformed
        // Show about window
        About about = new About();
        about.setVisible(true);
    }//GEN-LAST:event_miAboutActionPerformed
    private void bNodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNodeActionPerformed
        // Add Node to the map
        this.map.setNodeOnMouse();
        this.map.setButtonNodeClicked(true);
        this.map.setButtonLinkClicked(false);
        this.map.setButtonStartClicked(false);
    }//GEN-LAST:event_bNodeActionPerformed

    private void bLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLinkActionPerformed
        // Add link to a node
        this.map.setButtonNodeClicked(false);
        this.map.setButtonLinkClicked(true);
        this.map.setButtonStartClicked(false);
    }//GEN-LAST:event_bLinkActionPerformed

    private void bStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartActionPerformed
        // Add startlink to node
        this.map.setButtonNodeClicked(false);
        this.map.setButtonLinkClicked(false);
        this.map.setButtonStartClicked(true);
    }//GEN-LAST:event_bStartActionPerformed

    private void miSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveActionPerformed
        saveStory();
    }//GEN-LAST:event_miSaveActionPerformed

    private void miProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miProjectActionPerformed
        ProjectSettings projectSettings = new ProjectSettings(this, map);
        projectSettings.setVisible(true);
    }//GEN-LAST:event_miProjectActionPerformed

    private void miCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCloseActionPerformed
        checkChangeBeforeClose();
    }//GEN-LAST:event_miCloseActionPerformed

    private void miExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExportActionPerformed
        triggerProgressTask(TaskOptions.EXPORT);
    }//GEN-LAST:event_miExportActionPerformed

    private void miOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miOpenActionPerformed
        if (story.isSomethingChanged()) {
            // Show save confirm window
            int n = JOptionPane.showConfirmDialog(null,
                    "You made one or several changes to the current story.\n"
                    + "Do you want to save this before opening one?\n",
                    "Save?",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                // saveStory returns false if the user cancels
                if (!saveStory()) {
                    return;
                }
            } else if (n == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        //browse
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Select an iStory project file file to open");
        j.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".proj"}, // Extensions we accept
                "Project files (*.proj)"));

        int dialog = j.showOpenDialog(this);

        // Catch actions of the File Chooser Dialog Window
        if (dialog == JFileChooser.APPROVE_OPTION) {

            // Create an XML-file
            File projectFile = j.getSelectedFile();

            try {
                // Open the file that is the first 
                // command line parameter
                FileInputStream fstream = new FileInputStream(projectFile);
                // Get the object of DataInputStream
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                //Read File Line By Line
                ArrayList<Link> tempLink = new ArrayList<Link>();
                double tempLong = 0.0;
                double tempLat = 0.0;
                MediaItem tempMediaItem = null;
                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console
                    if (strLine.equals("<story>")) {
                        story = new Story("", panelRoutes, this);
                        map.Clear(story);
                    } else if (strLine.contains("<story.name>") && strLine.contains("</story.name>")) {
                        story.setName(strLine.substring("<story.name>".length(), strLine.length() - "</story.name>".length()));
                    } else if (strLine.contains("<route>")) {
                        story.newEmptyRoute();
                    } else if (strLine.contains("<route.name>") && strLine.contains("</route.name>")) {
                        story.getRoutes().get(story.getRoutes().size() - 1).setName(strLine.substring("<route.name>".length(), strLine.length() - "</route.name>".length()));
                    } else if (strLine.contains("<route.link>")) {
                        tempLink.add(new Link());
                        story.getRoutes().get(story.getRoutes().size() - 1).setStartLink(tempLink.get(tempLink.size() - 1));
                    } else if (strLine.contains("</route.link>")) {
                        story.getRoutes().get(story.getRoutes().size() - 1).getStartLink().getP2().setStart();
                    } else if (strLine.contains("<link.name>") && strLine.contains("</link.name>")) {
                        tempLink.get(tempLink.size() - 1).setName(strLine.substring("<link.name>".length(), strLine.length() - "</link.name>".length()));
                    } else if (strLine.contains("<link.id>") && strLine.contains("</link.id>")) {
                        tempLink.get(tempLink.size() - 1).setId(Long.parseLong(strLine.substring("<link.id>".length(), strLine.length() - "</link.id>".length())));
                        story.setLinkCounter(tempLink.get(tempLink.size() - 1).getId());
                    } else if (strLine.contains("<longitude>") && strLine.contains("</longitude>")) {
                        tempLong = Double.parseDouble(strLine.substring("<longitude>".length(), strLine.length() - "</longitude>".length()));
                    } else if (strLine.contains("<latitude>") && strLine.contains("</latitude>")) {
                        tempLat = Double.parseDouble(strLine.substring("<latitude>".length(), strLine.length() - "</latitude>".length()));
                    } else if (strLine.contains("</from>")) {
                        tempLink.get(tempLink.size() - 1).setP1(tempLink.get(tempLink.size() - 2).getP2());
                    } else if (strLine.contains("</to>")) {
                        Node nod = new Node(tempLat, tempLong);
                        tempLink.get(tempLink.size() - 1).setP2(nod);
                        map.addNode(nod);
                    } else if (strLine.contains("<video>")) {
                        tempMediaItem = new Video();
                    } else if (strLine.contains("<image>")) {
                        tempMediaItem = new Image();
                    } else if (strLine.contains("<text>")) {
                        tempMediaItem = new Text();
                    } else if (strLine.contains("<filename>") && strLine.contains("</filename>")) {
                        final File file = new File(strLine.substring("<filename>".length(), strLine.length() - "</filename>".length()));
                        //Check if file exists
                        if (file.exists()) {
                            tempMediaItem.setAbsolutePath(file.getPath().substring(0, file.getPath().length() - file.getName().length()));
                            tempMediaItem.setFileName(file.getName());
                        } else {

                            // Browse for any corrupt file
                            JFileChooser corruptFileChooser = new JFileChooser();
                            corruptFileChooser.setDialogTitle("Find the corrupt file");
                            corruptFileChooser.setFileFilter(new FileFilter() {
                                @Override
                                public boolean accept(File corruptFile) {
                                    if (corruptFile.isDirectory()) {
                                        return true;
                                    } else if (file.getName().equals(corruptFile.getName())) {
                                        return true;
                                    }
                                    return false;
                                }

                                @Override
                                public String getDescription() {
                                    return file.getName();
                                }
                            });

                            int corruptDialog = corruptFileChooser.showOpenDialog(this);

                            // Catch actions of the File Chooser Dialog Window
                            if (corruptDialog == JFileChooser.APPROVE_OPTION) {
                                File newFile = corruptFileChooser.getSelectedFile();

                                tempMediaItem.setAbsolutePath(newFile.getPath().substring(0, newFile.getPath().length() - newFile.getName().length()));
                                tempMediaItem.setFileName(newFile.getName());
                            } else if (corruptDialog == JFileChooser.CANCEL_OPTION) {
                                tempMediaItem.setAbsolutePath(file.getPath().substring(0, file.getPath().length() - file.getName().length()));
                                tempMediaItem.setFileName(file.getName());
                                tempMediaItem.setCorrupt(true);
                            }
                        }
                    } else if (strLine.contains("<duration>") && strLine.contains("</duration>")) {
                        tempMediaItem.setShowDurationInSeconds(Integer.parseInt(strLine.substring("<duration>".length(), strLine.length() - "</duration>".length())));
                    } else if (strLine.contains("</video>") || strLine.contains("</image>") || strLine.contains("</text>")) {
                        tempLink.get(tempLink.size() - 1).getMediaItems().add(tempMediaItem);
                    } else if (strLine.contains("<link>")) {
                        Link link = new Link();
                        tempLink.add(link);
                        map.addLink(link);
                    } else if (strLine.contains("</link>")) {
                        tempLink.get(tempLink.size() - 2).addLink(tempLink.get(tempLink.size() - 1));
                        tempLink.remove(tempLink.size() - 1);
                    } else if (strLine.contains("</node>")) {
                        Node nod = new Node(tempLat, tempLong);
                        map.addNode(nod);
                    }
                }
                panelRoutes.refreshList(story.getRoutes());
                story.setSomethingChanged(false);
                //Close the input stream
                in.close();
            } catch (Exception e) {//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        } else if (dialog == JFileChooser.CANCEL_OPTION) {
            // User canceled uploaden file
            LOGGER.log(Level.INFO, "File Chooser is canceled by user");
        } else {
            // Error or dialog is dismissed
            LOGGER.log(Level.WARNING, "File Chooser returns error");
        }
    }//GEN-LAST:event_miOpenActionPerformed

    private void miNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewActionPerformed
        if (story.isSomethingChanged()) {
            // Show save confirm window
            int n = JOptionPane.showConfirmDialog(null,
                    "You made one or several changes to the current story.\n"
                    + "Do you want to save this before starting a new one?\n",
                    "Save?",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                // saveStory returns false if the user cancels
                if (!saveStory()) {
                    return;
                }
            } else if (n == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        String storyName = JOptionPane.showInputDialog("Name of the new story", defaultStoryName);
        if (storyName != null) {
            if (!storyName.equals("")) {
                story = new Story(storyName, panelRoutes, this);
                map.Clear(story);
                panelRoutes.addStory(story);
                panelRoutes.refreshList(story.getRoutes());

                this.setTitle(storyName + " - iStory designer " + Application.getVersion());
                return;
            }
        }
        miNewActionPerformed(evt);
    }//GEN-LAST:event_miNewActionPerformed

    private void miImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miImportActionPerformed
        // Check for changes, if so ask for confirm
        if (story.isSomethingChanged()) {
            // Show save confirm window
            int n = JOptionPane.showConfirmDialog(null,
                    "You made one or several changes to the current story.\n"
                    + "Do you want to save this before importing a new one?\n",
                    "Save?",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                // saveStory returns false if the user cancels
                if (!saveStory()) {
                    return;
                }
            } else if (n == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        triggerProgressTask(TaskOptions.IMPORT);
    }//GEN-LAST:event_miImportActionPerformed

    /**
     * Run Main window
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        // Set Nimbus look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    /**
     * TODO
     *
     * @return boolean
     */
    private boolean saveStory() {

        if (!verifyValidExport()) {
            return false;
        }

        boolean XMLProject = true; // make a proj file

        JFileChooser j = new JFileChooser();
        //j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        j.setSelectedFile(new File(map.getStory().getName()));
        j.setDialogTitle("Select a location to save the project");
        int dialog = j.showSaveDialog(this);

        // Catch actions of the File Chooser Dialog Window
        if (dialog == JFileChooser.APPROVE_OPTION) {

            try {

                String XMLcontent = this.story.printXML(XMLProject);
                // Add no linked nodes at the end
                XMLcontent += this.map.printNodeXML();

                String filePathName = j.getSelectedFile().toString();

                // Add ".iStory.proj  at the end of the string (only if it not exists)
                if (!filePathName.endsWith(".iStory.proj")) {
                    filePathName += ".iStory.proj";
                }

                ///////////
                // XML
                ///////////

                // Create an XML-file
                File projectFile = new File(filePathName);

                // Check if the iStory file already exists
                boolean exists = projectFile.createNewFile();
                if (!exists) {
                    // Confirm the save
                    int option = JOptionPane.showConfirmDialog(null,
                            "There is already a file with the same name in the selected folder. \n "
                            + "Do you want to overwrite this file?",
                            "File already exists",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        projectFile.delete();
                    } else if (option == JOptionPane.NO_OPTION) {
                        saveStory();
                        return false;
                    } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                        return false;
                    }
                }
                FileWriter fstream = new FileWriter(filePathName);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(XMLcontent);
                out.close();
                JOptionPane.showMessageDialog(
                        Main.this,
                        "The project has been saved",
                        "Saved succes..",
                        JOptionPane.INFORMATION_MESSAGE);
                // Set the changed to false to be able to close the program
                story.setSomethingChanged(false);
                return true;
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }

    /**
     * Check if there are any corrupt mediaitems
     *
     * @return true if all the corrupt files were fixed / false if not
     */
    private Boolean verifyValidExport() {


        for (Link link : story.getAllLinks()) {

            // Check if there are any links with no media item
            if (link.getMediaItems().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                            "There are Links with no Media items. Add some media items or delete these links.",
                            "No media items",
                            JOptionPane.ERROR_MESSAGE);
                return false;
            }

            for (final MediaItem mediaItem : link.getMediaItems()) {

                // Check for corrupt items
                if (mediaItem.isCorrupt()) {
                    // Browse for any corrupt file
                    JFileChooser corruptFileChooser = new JFileChooser();
                    corruptFileChooser.setDialogTitle("Find the corrupt file");
                    corruptFileChooser.setFileFilter(new FileFilter() {
                        @Override
                        public boolean accept(File corruptFile) {
                            if (corruptFile.isDirectory()) {
                                return true;
                            } else if (mediaItem.getFileName().equals(corruptFile.getName())) {
                                return true;
                            }
                            return false;
                        }

                        @Override
                        public String getDescription() {
                            return mediaItem.getFileName();
                        }
                    });

                    int corruptDialog = corruptFileChooser.showOpenDialog(this);

                    // Catch actions of the File Chooser Dialog Window
                    if (corruptDialog == JFileChooser.APPROVE_OPTION) {
                        File corruptFile = corruptFileChooser.getSelectedFile();
                        mediaItem.setAbsolutePath(corruptFile.getPath().substring(0, corruptFile.getPath().length() - corruptFile.getName().length()));
                        mediaItem.setCorrupt(false);
                    } else if (corruptDialog == JFileChooser.CANCEL_OPTION) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * TODO
     *
     * @return boolean
     */
    private boolean exportStory() {

        if (!verifyValidExport()) {
            return false;
        }

        float progress = 0f;
        boolean XMLProject = false; // make a proj file

        JFileChooser j = new JFileChooser();
        j.setAcceptAllFileFilterUsed(false);
        j.setDialogTitle("Select the export location");
        j.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".iStory"}, // Extensions we accept
                "Project files (*.iStory)"));
        //j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        j.setSelectedFile(new File(map.getStory().getName()));
        int dialog = j.showSaveDialog(this);

        // Catch actions of the File Chooser Dialog Window
        if (dialog == JFileChooser.APPROVE_OPTION) {

            try {
                // Max length of the buffer
                int maxBufferSize = 1024; // bytes

                String fileName = j.getSelectedFile().toString();

                // Add ".iStory  at the end of the string (only if it doesn't exist)
                if (!fileName.endsWith(".iStory")) {
                    fileName += ".iStory";
                }

                // Check if the iStory file already exists
                File iStoryFile = new File(fileName);
                ZipOutputStream zipOut = null;
                if (!iStoryFile.isFile()) {
                    // Zipje
                    zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
                } else {
                    // Confirm the save
                    int option = JOptionPane.showConfirmDialog(null,
                            "There is already a file with the same name in the selected folder. \n "
                            + "Do you want to overwrite this file?",
                            "File already exists",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        File deletedFile = new File(fileName);
                        deletedFile.delete();
                        zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
                    } else if (option == JOptionPane.NO_OPTION) {
                        exportStory();
                        return false;
                    } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                        return false;
                    }
                }


                if (map.getStory().getImage() != null) {
                    ///////////
                    // Story image
                    ///////////

                    File storyImage = map.getStory().getImage();
                    boolean exists = storyImage.isFile();
                    if (exists) {

                        // Get the data from the file
                        byte[] data = new byte[maxBufferSize];
                        // Create inputBuffer for the data
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(storyImage));
                        // Internal count for the databuffer
                        int count = 0;
                        // Add new file to the zip file
                        zipOut.putNextEntry(new ZipEntry(storyImage.getName()));
                        // Fill the new file with data
                        while ((count = in.read(data, 0, maxBufferSize)) != -1) {
                            zipOut.write(data, 0, count);
                            if (task.isCancelled() || progressMonitor.isCanceled()) {
                                // Close the buffers
                                in.close();
                                zipOut.flush();
                                zipOut.close();
                                // Delete the corupt file
                                if (!storyImage.delete()) {
                                    JOptionPane.showMessageDialog(
                                            Main.this,
                                            "The corrupt iStory file could not be deleted. You have to delete it manualy at: '" + fileName + "'",
                                            "Could not delete iStory file",
                                            JOptionPane.WARNING_MESSAGE);
                                }
                                return false;
                            }
                        }
                        in.close();
                    }
                }
                task.setProgression(Math.min(10, 100));

                ///////////
                // MEDIAFILES
                ///////////
                progress = 10f;
                float storyFilesSize = 0f;

                boolean exportShortCut = false;
                // Get total of filesize
                for (Link link : story.getAllLinks()) {

                    for (final MediaItem mediaItem : link.getMediaItems()) {
                        File tempFile = new File(mediaItem.getAbsolutePath() + mediaItem.getFileName());

                        // Check an add shortcuts for performance
                        if (exportMediaItems.isEmpty()) {
                            exportMediaItems.put(mediaItem, link);
                        } else {
                            for (MediaItem currentMediaItem : exportMediaItems.keySet()) {

                                String mediaPathFile = mediaItem.getAbsolutePath() + mediaItem.getFileName();
                                String currentMediaPathFile = currentMediaItem.getAbsolutePath() + currentMediaItem.getFileName();

                                if (mediaPathFile.equals(currentMediaPathFile)) {
                                    mediaItem.setShortcut(exportMediaItems.get(currentMediaItem));
                                    exportShortCut = true;
                                    break;
                                }
                            }
                            if (!exportShortCut) {
                                // Filesize (only if it's not a shortcut)
                                storyFilesSize += tempFile.length() / maxBufferSize;
                                exportMediaItems.put(mediaItem, link);
                            }
                            exportShortCut = false;
                        }
                    }
                }
///////////
// XML
///////////
// Create an XML-file
                String filenameWithPath = fileName.replace(".iStory", "") + ".xml";
                File XMLfile = new File(filenameWithPath);
                String filename = j.getName(XMLfile);
                String XMLcontent = this.story.printXML(XMLProject);

                boolean exists = XMLfile.createNewFile();
                if (exists) {
                    FileWriter fstream = new FileWriter(filenameWithPath);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(XMLcontent);
                    out.close();
                    // Get the data from the file
                    byte[] data = new byte[maxBufferSize];
                    // Create inputBuffer for the data
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(filenameWithPath));
                    // Internal count for the databuffer
                    int count = 0;
                    // Add new file to the zip file
                    zipOut.putNextEntry(new ZipEntry(filename));
                    // Fill the new file with data
                    while ((count = in.read(data, 0, maxBufferSize)) != -1) {
                        zipOut.write(data, 0, count);
                    }
                    in.close();
                    XMLfile.delete();
                }
                task.setProgression(Math.min(20, 100));

                float percent = (80f / storyFilesSize);

                // Loop over all links to get the  mediafiles
                for (Link link : story.getAllLinks()) {

                    zipOut.putNextEntry(new ZipEntry(link.getId() + "/"));

                    for (MediaItem mediaItem : link.getMediaItems()) {
                        // Get the file from the location
                        File file = new File(mediaItem.getAbsolutePath() + mediaItem.getFileName());
                        exists = file.isFile();

                        if (exists) {
                            // check if it's not a shortcut
                            if (mediaItem.getShortcut() == null) {

                                currentMediaItemName = file.getName();

                                // Get the data from the file
                                byte[] data = new byte[maxBufferSize];
                                // Create inputBuffer for the data
                                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                                // Internal count for the databuffer
                                int count = 0;
                                // Add new file to the zip file                            
                                zipOut.putNextEntry(new ZipEntry(link.getId() + "/" + mediaItem.getFileName()));

                                // Fill the new file with data
                                while ((count = in.read(data, 0, maxBufferSize)) != -1) {
                                    zipOut.write(data, 0, count);
                                    progress += percent;
                                    if (!task.isCancelled() || !progressMonitor.isCanceled()) {
                                        task.setProgression((int) Math.floor(Math.min(progress, 99)));
                                    } else {
                                        // Close the buffers
                                        in.close();
                                        zipOut.flush();
                                        zipOut.close();
                                        // Delete the corupt file
                                        File deletedFile = new File(fileName);
                                        if (!deletedFile.delete()) {
                                            JOptionPane.showMessageDialog(
                                                    Main.this,
                                                    "The corrupt iStory file could not be deleted. You have to delete it manualy at: '" + fileName + "'",
                                                    "Could not delete iStory file",
                                                    JOptionPane.WARNING_MESSAGE);
                                        }
                                        exportMediaItems.clear();
                                        return false;
                                    }
                                }
                                in.close();
                            }
                        }
                    }
                }
                // Save and close the buffers
                zipOut.flush();
                zipOut.close();
                System.out.println("Your file is zipped");

                task.setProgression(Math.min(99, 100));
                task.setProgression(100);

                // Set the changed to false to be able to close the program
                story.setSomethingChanged(false);
                return true;


            } catch (IOException ex) {
                Logger.getLogger(Main.class
                        .getName()).log(Level.SEVERE, null, ex);

                return false;
            }
        } else if (dialog == JFileChooser.CANCEL_OPTION) {
            return true;
        }
        return false;
    }

    public float copyInputStream(InputStream in, OutputStream out, float progress, float percent, int maxBufferSize)
            throws IOException {
        byte[] buffer = new byte[maxBufferSize];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
            progress += percent;
            task.setProgression((int) Math.floor(Math.min(progress, 99)));
        }

        in.close();
        out.close();
        return progress;
    }

    public boolean importStory() throws IOException {
        float progress = 10f;

        JFileChooser j = new JFileChooser();
        j.setAcceptAllFileFilterUsed(false);
        j.setDialogTitle("Select an iStory file to import");
        j.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".iStory"}, // Extensions we accept
                "Project files (*.iStory)"));
        //j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int dialog = j.showOpenDialog(this);

        // Catch actions of the File Chooser Dialog Window
        if (dialog == JFileChooser.APPROVE_OPTION) {

            try {

                JFileChooser importLocationChooser = new JFileChooser();
                importLocationChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                importLocationChooser.setDialogTitle("Select a location to store the files");
                int importDialog = importLocationChooser.showOpenDialog(this);
                String pad;
                String selectedZipFileName = j.getSelectedFile().getName().replace(".iStory", "");

                File folder = new File(importLocationChooser.getSelectedFile().getAbsolutePath() + System.getProperty("file.separator") + selectedZipFileName);

                // Catch actions of the File Chooser Dialog Window
                if (importDialog == JFileChooser.APPROVE_OPTION) {

                    if (folder.isDirectory()) {
                        // Overwrite check
                        int option = JOptionPane.showConfirmDialog(null,
                                "There is already a story folder with the same name in the selected folder. \n "
                                + "Do you want to overwrite this folder?",
                                "File already exists",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            folder.delete();
                        } else if (option == JOptionPane.NO_OPTION) {
                            importStory();
                            return false;
                        } else if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                            return false;
                        }
                    }
                } else if (dialog == JFileChooser.CANCEL_OPTION) {
                    return true;
                }
                folder.mkdir();
                pad = importLocationChooser.getSelectedFile().getAbsolutePath() + System.getProperty("file.separator") + selectedZipFileName + System.getProperty("file.separator");
                // Max length of the buffer
                int maxBufferSize = 1024; // bytes
                File selectedFile = j.getSelectedFile();
                ZipFile importZipFile = new ZipFile(selectedFile);

                // entries = importZipFile.entries();
                task.setProgression((int) Math.floor(Math.min(progress, 99)));
                float percent = (90f / (selectedFile.length() / maxBufferSize));

                ZipInputStream zipinputstream = new ZipInputStream(
                        new FileInputStream(selectedFile));
                ZipEntry entry = zipinputstream.getNextEntry();
                while (entry != null) {
                    if (entry.isDirectory()) {
                        // Assume directories are stored parents first then children.
                        System.err.println("Extracting directory: " + pad + entry.getName());
                        // This is not robust, just for demonstration purposes.
                        (new File(pad + entry.getName())).mkdir();
                        entry = zipinputstream.getNextEntry();
                        continue;
                    }
                    currentMediaItemName = entry.getName();
                    System.err.println("Extracting file: " + pad + entry.getName());
                    progress = copyInputStream(importZipFile.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(pad + entry.getName())), progress, percent, maxBufferSize);
                    entry = zipinputstream.getNextEntry();
                }

                importZipFile.close();


                ////////////////////////////

                task.setProgression(Math.min(100, 100));


                try {
                    // Open the file that is the first 
                    // command line parameter
                    FileInputStream fstream = new FileInputStream(pad + selectedZipFileName + ".xml");
                    // Get the object of DataInputStream
                    DataInputStream in = new DataInputStream(fstream);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    //Read File Line By Line
                    ArrayList<Link> tempLink = new ArrayList<Link>();
                    double tempLong = 0.0;
                    double tempLat = 0.0;
                    MediaItem tempMediaItem = null;
                    while ((strLine = br.readLine()) != null) {
                        // Print the content on the console
                        if (strLine.equals("<story>")) {
                            story = new Story("", panelRoutes, this);
                            map.Clear(story);
                        } else if (strLine.contains("<story.name>") && strLine.contains("</story.name>")) {
                            story.setName(strLine.substring("<story.name>".length(), strLine.length() - "</story.name>".length()));
                        } else if (strLine.contains("<route>")) {
                            story.newEmptyRoute();
                        } else if (strLine.contains("<route.name>") && strLine.contains("</route.name>")) {
                            story.getRoutes().get(story.getRoutes().size() - 1).setName(strLine.substring("<route.name>".length(), strLine.length() - "</route.name>".length()));
                            //panelRoutes.refreshList(story.getRoutes());
                        } else if (strLine.contains("<route.link>")) {
                            tempLink.add(new Link());
                            story.getRoutes().get(story.getRoutes().size() - 1).setStartLink(tempLink.get(tempLink.size() - 1));
                        } else if (strLine.contains("</route.link>")) {
                            story.getRoutes().get(story.getRoutes().size() - 1).getStartLink().getP2().setStart();
                        } else if (strLine.contains("<link.name>") && strLine.contains("</link.name>")) {
                            tempLink.get(tempLink.size() - 1).setName(strLine.substring("<link.name>".length(), strLine.length() - "</link.name>".length()));
                        } else if (strLine.contains("<link.id>") && strLine.contains("</link.id>")) {
                            tempLink.get(tempLink.size() - 1).setId(Long.parseLong(strLine.substring("<link.id>".length(), strLine.length() - "</link.id>".length())));
                            story.setLinkCounter(tempLink.get(tempLink.size() - 1).getId());
                        } else if (strLine.contains("<longitude>") && strLine.contains("</longitude>")) {
                            tempLong = Double.parseDouble(strLine.substring("<longitude>".length(), strLine.length() - "</longitude>".length()));
                        } else if (strLine.contains("<latitude>") && strLine.contains("</latitude>")) {
                            tempLat = Double.parseDouble(strLine.substring("<latitude>".length(), strLine.length() - "</latitude>".length()));
                        } else if (strLine.contains("</from>")) {
                            tempLink.get(tempLink.size() - 1).setP1(tempLink.get(tempLink.size() - 2).getP2());
                        } else if (strLine.contains("</to>")) {
                            Node nod = new Node(tempLat, tempLong);
                            tempLink.get(tempLink.size() - 1).setP2(nod);
                            map.addNode(nod);
                        } else if (strLine.contains("<video>")) {
                            tempMediaItem = new Video();
                        } else if (strLine.contains("<image>")) {
                            tempMediaItem = new Image();
                        } else if (strLine.contains("<text>")) {
                            tempMediaItem = new Text();
                        } else if (strLine.contains("<filename>") && strLine.contains("</filename>")) {
                            File file = new File(pad + tempLink.get(tempLink.size() - 1).getId() + System.getProperty("file.separator") + strLine.substring("<filename>".length(), strLine.length() - "</filename>".length()));
                            //Check if file exists
                            if (file.exists()) {
                                tempMediaItem.setAbsolutePath(file.getPath().substring(0, file.getPath().length() - file.getName().length()));
                                tempMediaItem.setFileName(file.getName());
                            }
                        } else if (strLine.contains("<duration>") && strLine.contains("</duration>")) {
                            tempMediaItem.setShowDurationInSeconds(Integer.parseInt(strLine.substring("<duration>".length(), strLine.length() - "</duration>".length())));
                        } else if (strLine.contains("</video>") || strLine.contains("</image>") || strLine.contains("</text>")) {
                            tempLink.get(tempLink.size() - 1).getMediaItems().add(tempMediaItem);
                        } else if (strLine.contains("<link>")) {
                            Link link = new Link();
                            tempLink.add(link);
                            map.addLink(link);
                        } else if (strLine.contains("</link>")) {
                            tempLink.get(tempLink.size() - 2).addLink(tempLink.get(tempLink.size() - 1));
                            tempLink.remove(tempLink.size() - 1);
                        } else if (strLine.contains("</node>")) {
                            Node nod = new Node(tempLat, tempLong);
                            map.addNode(nod);
                        }
                    }
                    panelRoutes.refreshList(story.getRoutes());
                    story.setSomethingChanged(false);
                    //Close the input stream
                    in.close();
                } catch (Exception e) {//Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }



                return true;


            } catch (IOException ex) {
                Logger.getLogger(Main.class
                        .getName()).log(Level.SEVERE, null, ex);

                return false;
            }
        } else if (dialog == JFileChooser.CANCEL_OPTION) {
            return true;
        }
        return false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLink;
    private javax.swing.JButton bNode;
    private javax.swing.JButton bStart;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenu mHelp;
    private javax.swing.JMenu mSettings;
    private javax.swing.JMenuBar mbMenubar;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miClose;
    private javax.swing.JMenuItem miExport;
    private javax.swing.JMenuItem miImport;
    private javax.swing.JMenuItem miNew;
    private javax.swing.JMenuItem miOpen;
    private javax.swing.JMenuItem miProject;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JPanel pMain;
    private javax.swing.JPanel pMenu;
    private javax.swing.JPanel pMenuButtons;
    private javax.swing.JPopupMenu.Separator sepFile;
    // End of variables declaration//GEN-END:variables

    /**
     * Progressbar for exporting a story
     */
    class Task extends SwingWorker<Void, Void> {

        private TaskOptions taskOption;

        /**
         * Set progress
         *
         * Own implementation of setProgress() because we need to set the progress outside SwingWorker. The function setProgress() is
         * final protected.
         *
         * @param progress int current progress
         */
        public void setProgression(int progress) {
            this.setProgress(progress);
        }

        /**
         * Export story
         *
         * @return null
         * @throws InterruptedException
         */
        @Override
        public Void doInBackground() throws IOException {
            if (taskOption == TaskOptions.EXPORT) {
                if (!exportStory()) {

                    //TODO Delete corrupted file

                    // Show popup to user that exporting failed
                    JOptionPane.showMessageDialog(
                            Main.this,
                            "Exporting failed!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (taskOption == TaskOptions.IMPORT) {
                if (!importStory()) {

                    //TODO Delete corrupted file

                    // Show popup to user that exporting failed
                    JOptionPane.showMessageDialog(
                            Main.this,
                            "Importing failed!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            return null;
        }

        /**
         * This function is called when the exporting progress is done
         */
        @Override
        public void done() {
            if (taskOption == TaskOptions.EXPORT) {
                // Exporting is canceled by user
                if (progressMonitor.isCanceled()) {
                    JOptionPane.showMessageDialog(
                            Main.this,
                            "Exporting canceled by user!",
                            "Canceled",
                            JOptionPane.WARNING_MESSAGE);
                } // Export succeeded
                else if (task.isDone() && task.getProgress() == 100) {
                    JOptionPane.showMessageDialog(
                            Main.this,
                            "Exporting succeeded!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    if (close) {
                        System.exit(0);
                    }
                }
            } else if (taskOption == TaskOptions.IMPORT) {
                // Exporting is canceled by user
                if (progressMonitor.isCanceled()) {
                    JOptionPane.showMessageDialog(
                            Main.this,
                            "Importing canceled by user!",
                            "Canceled",
                            JOptionPane.WARNING_MESSAGE);
                } // Export succeeded
                else if (task.isDone() && task.getProgress() == 100) {
                    JOptionPane.showMessageDialog(
                            Main.this,
                            "Importing succeeded!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    if (close) {
                        System.exit(0);
                    }
                }
            }
        }

        private void setTaskOption(TaskOptions taskOption) {
            this.taskOption = taskOption;
        }
    }

    /**
     * Invoked when task's progress property changes.
     *
     * @param pce PropertyChangeEvent
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if ("progress".equals(pce.getPropertyName())) {
            int progress = (Integer) pce.getNewValue();
            // Show progress to user
            progressMonitor.setProgress(progress);
            progressMonitor.setNote(String.format("<html><b>Completed %d%%.</b> <br /><br /> %s<br /></html>", progress, currentMediaItemName));

            // If user cancel export
            if (progressMonitor.isCanceled()) {
                task.cancel(true);
                progressMonitor.setProgress(0);
                // Task.done() is called
            }
        }
    }
}
