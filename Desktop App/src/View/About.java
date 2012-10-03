package View;

import Model.Application;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Frame containing all meta data of the application
 */
public class About extends JFrame {

    /**
     * Creates new form About
     */
    public About() {
        initComponents();

        // Get the size of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        // Determine the new location of the window
        int x = (dim.width - (this.getSize().width)) / 2;
        int y = (dim.height - (this.getSize().height)) / 2;

        // Center the window
        this.setLocation(x, y);

        // Set meta data application
        lVersionCode.setText(String.valueOf(Application.getVersion()));
    }

    /* DO NOT TOUCH */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pAbout = new javax.swing.JPanel();
        lVersion = new javax.swing.JLabel();
        lVersionCode = new javax.swing.JLabel();
        lCreators = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        setName("fAbout"); // NOI18N
        setResizable(false);

        lVersion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lVersion.setText("Version:");
        lVersion.setToolTipText("");

        lVersionCode.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lCreators.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lCreators.setText("Developed by:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setText("<html> Reinier van der Hoeven<br /> Tim Klaversma<br />Jeffrey van der Leije<br /> Gido Manders");

        javax.swing.GroupLayout pAboutLayout = new javax.swing.GroupLayout(pAbout);
        pAbout.setLayout(pAboutLayout);
        pAboutLayout.setHorizontalGroup(
            pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lCreators)
                    .addComponent(lVersion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lVersionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        pAboutLayout.setVerticalGroup(
            pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAboutLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lVersion)
                    .addComponent(lVersionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCreators)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(138, Short.MAX_VALUE))
        );

        getContentPane().add(pAbout, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lCreators;
    private javax.swing.JLabel lVersion;
    private javax.swing.JLabel lVersionCode;
    private javax.swing.JPanel pAbout;
    // End of variables declaration//GEN-END:variables
}
