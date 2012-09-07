package View;

import Model.Application;
import Model.Link;
import Model.Route;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * The Main screen of the application
 */
public class Main extends JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();

        // Set application version
        Application app = new Application();
        jLversion.setText("v" + app.getVersion());

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int x = (dim.width - (this.getSize().width)) / 2;
        int y = (dim.height - (this.getSize().height)) / 2;

        // Center the window
        this.setLocation(x, y);
        
        String startpunt = "Timmehh";
        DefaultMutableTreeNode startLink = new DefaultMutableTreeNode(startpunt, true);
        Link start = new Link("Start", null, null);
        Route route = new Route(startpunt, start);
        
        Link link2 = new Link("A", null, null);
        start.addLink(link2);
        
        Link link3 = new Link("A", null, null);
        link2.addLink(link3);
        
        Link link4 = new Link("AA", null, null);
        link3.addLink(link4);
        
        Link link5 = new Link("AA", null, null);
        link4.addLink(link5);
        
        Link link6 = new Link("AB", null, null);
        link2.addLink(link6);
        
        Link link7 = new Link("AB", null, null);
        link6.addLink(link7);
        
        
        route.print();
    }

    /* Do not touch */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPmain = new javax.swing.JPanel();
        jLversion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLversion.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLversion.setText("version");

        org.jdesktop.layout.GroupLayout jPmainLayout = new org.jdesktop.layout.GroupLayout(jPmain);
        jPmain.setLayout(jPmainLayout);
        jPmainLayout.setHorizontalGroup(
            jPmainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPmainLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLversion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPmainLayout.setVerticalGroup(
            jPmainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPmainLayout.createSequentialGroup()
                .addContainerGap(604, Short.MAX_VALUE)
                .add(jLversion)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPmain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPmain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLversion;
    private javax.swing.JPanel jPmain;
    // End of variables declaration//GEN-END:variables
}
