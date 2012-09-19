package View;

import Model.MediaItem;
import Model.Story;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * The Main screen of the application
 */
public class Main extends JFrame {

    // Variables
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private Map map;
    private Story story;
    private Routes panelRoutes;

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

        // Add Routes
        panelRoutes = new Routes();
        pMenu.add(panelRoutes, BorderLayout.CENTER);

        // Create a story
        story = new Story("New Story", panelRoutes);

        // Add Map
        map = new Map(story, this);
        pMain.add(this.map, BorderLayout.CENTER);

        // Lister to prevent the application from closing when the user did something change
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                if (story.isSomethingChanged()) {

                    // Show save confirm window
                    int n = JOptionPane.showConfirmDialog(null,
                            "You made one or several changes to the current story.\n"
                            + "Do you want to save this before closing the program?\n",
                            "Save?",
                            JOptionPane.YES_NO_OPTION);

                    if (n == 1) {
                        System.exit(0);
                    } else {
                        if (saveStory()) {
                            System.exit(0);
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        });

        // Revalidate JPanels
        this.pack();
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
        miClose = new javax.swing.JMenuItem();
        mEdit = new javax.swing.JMenu();
        mCopy = new javax.swing.JMenuItem();
        mPaste = new javax.swing.JMenuItem();
        mHelp = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iStory designer");
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setName("fMain"); // NOI18N

        pMain.setLayout(new java.awt.BorderLayout());

        pMenu.setPreferredSize(new java.awt.Dimension(200, 500));
        pMenu.setLayout(new java.awt.BorderLayout());

        bNode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/waypoint_white.png"))); // NOI18N
        bNode.setText("Node");
        bNode.setToolTipText("");
        bNode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bNode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNodeActionPerformed(evt);
            }
        });

        bLink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/link.png"))); // NOI18N
        bLink.setText("Link");
        bLink.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLinkActionPerformed(evt);
            }
        });

        bStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/start.png"))); // NOI18N
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
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, bNode, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .add(bStart, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pMenuButtonsLayout.setVerticalGroup(
            pMenuButtonsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pMenuButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .add(bNode, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(bLink, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(bStart)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pMenu.add(pMenuButtons, java.awt.BorderLayout.PAGE_START);

        pMain.add(pMenu, java.awt.BorderLayout.LINE_START);

        mFile.setText("File");

        miNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        miNew.setText("New");
        mFile.add(miNew);

        miOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        miOpen.setText("Open");
        mFile.add(miOpen);

        miSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miSave.setText("Save");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveActionPerformed(evt);
            }
        });
        mFile.add(miSave);
        mFile.add(sepFile);

        miClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        miClose.setText("Close");
        mFile.add(miClose);

        mbMenubar.add(mFile);

        mEdit.setText("Edit");

        mCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mCopy.setText("Copy");
        mEdit.add(mCopy);

        mPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mPaste.setText("Paste");
        mEdit.add(mPaste);

        mbMenubar.add(mEdit);

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
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int dialog = j.showSaveDialog(this);

        // Catch actions of the File Chooser Dialog Window
        if (dialog == JFileChooser.APPROVE_OPTION) {

            // Max length of the buffer
            int maxFileSize = 100000000;
            String storyName = "story1";
            String XMLcontent = this.story.printXML();

            // List of names (paths) to the mediafile locations
            //List<String> fileNames = new ArrayList<String>();
            //fileNames.add("C:\\klein.png");
            //fileNames.add("C:\\Users\\Public\\Videos\\Sample Videos\\Natuur.wmv");

            try {
                // Zipje
                ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(j.getSelectedFile() + System.getProperty("file.separator") + storyName + ".iStory")));

                ///////////
                // XML
                ///////////

                // Create an XML-file
                String file_name = storyName + ".xml";

                File XMLfile = new File(file_name);
                boolean exist = XMLfile.createNewFile();
                if (!exist) {
                    System.out.println("File already exists.");
                } else {
                    FileWriter fstream = new FileWriter(file_name);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(XMLcontent);
                    out.close();
                    // Get the data from the file
                    byte[] data = new byte[maxFileSize];
                    // Create inputBuffer for the data
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file_name));
                    // Internal count for the databuffer
                    int count = 0;
                    // Add new file to the zip file
                    zipOut.putNextEntry(new ZipEntry(file_name));
                    // Fill the new file with data
                    while ((count = in.read(data, 0, maxFileSize)) != -1) {
                        zipOut.write(data, 0, count);
                    }
                    in.close();
                    XMLfile.delete();
                    System.out.println("Temp XML-file created successfully.");
                }


                ///////////
                // MEDIAFILES
                ///////////

                // Loop over mediafiles
                for (MediaItem mediaItem : story.getAllMediaItems()) {

                    // Get the file from the location
                    File file = new File(mediaItem.getAbsolutePath() + "" + mediaItem.getFileName());
                    exist = file.isFile();
                    if (exist) {
                        System.out.println("File exists.");

                        // Get the data from the file
                        byte[] data = new byte[maxFileSize];
                        // Create inputBuffer for the data
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                        // Internal count for the databuffer
                        int count = 0;
                        // Add new file to the zip file
                        zipOut.putNextEntry(new ZipEntry(mediaItem.getFileName()));
                        // Fill the new file with data
                        while ((count = in.read(data, 0, maxFileSize)) != -1) {
                            zipOut.write(data, 0, count);
                        }
                        in.close();

                    } else {
                        System.out.println("File does not exist!");
                    }
                }

                // Save and close the buffers
                zipOut.flush();
                zipOut.close();
                System.out.println("Your file is zipped");

                // Confirm the save
                JOptionPane.showMessageDialog(null,
                        "The story has been saved.");

                // Set the changed to false to be able to close the program
                story.setSomethingChanged(false);
                return true;
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        return false;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLink;
    private javax.swing.JButton bNode;
    private javax.swing.JButton bStart;
    private javax.swing.JMenuItem mCopy;
    private javax.swing.JMenu mEdit;
    private javax.swing.JMenu mFile;
    private javax.swing.JMenu mHelp;
    private javax.swing.JMenuItem mPaste;
    private javax.swing.JMenuBar mbMenubar;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miClose;
    private javax.swing.JMenuItem miNew;
    private javax.swing.JMenuItem miOpen;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JPanel pMain;
    private javax.swing.JPanel pMenu;
    private javax.swing.JPanel pMenuButtons;
    private javax.swing.JPopupMenu.Separator sepFile;
    // End of variables declaration//GEN-END:variables
}
