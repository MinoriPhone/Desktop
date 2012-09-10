package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Button;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JPanel;

/**
 * The Main screen of the application
 */
public class Main extends JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int x = (dim.width - (this.getSize().width)) / 2;
        int y = (dim.height - (this.getSize().height)) / 2;

        // Center the window
        this.setLocation(x, y);

        // Add Map
        JPanel map = new Map();
        pMain.add(map, BorderLayout.CENTER);
        
        // Add Routes
        JPanel route = new Routes();
        pMenu.add(route, BorderLayout.CENTER);

//
//        String startpunt = "Timmehh";
//        Link start = new Link("Start", null, null);
//        Routes route = new Routes(startpunt, start);
//
//        Link link2 = new Link("A", null, null);
//        start.addLink(link2);
//
//        Link link3 = new Link("A", null, null);
//        link2.addLink(link3);
//
//        Link link4 = new Link("AA", null, null);
//        link3.addLink(link4);
//
//        Link link5 = new Link("AA", null, null);
//        link4.addLink(link5);
//
//        Link link6 = new Link("AB", null, null);
//        link2.addLink(link6);
//
//        Link link7 = new Link("AB", null, null);
//        link6.addLink(link7);
//
//        route.print();






        
        this.pack();
    }

    /* Do not touch */
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
        setPreferredSize(new java.awt.Dimension(1024, 768));

        pMain.setLayout(new java.awt.BorderLayout());

        pMenu.setPreferredSize(new java.awt.Dimension(200, 500));
        pMenu.setLayout(new java.awt.BorderLayout());

        bNode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/waypoint_white.png"))); // NOI18N
        bNode.setText("Node");
        bNode.setToolTipText("");
        bNode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        bLink.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/link.png"))); // NOI18N
        bLink.setText("Link");
        bLink.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        bStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/start.png"))); // NOI18N
        bStart.setText("Start");
        bStart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

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
                .add(bLink, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(bStart)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        // Nimbus look and feel
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


