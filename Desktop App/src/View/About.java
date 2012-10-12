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
        lNames = new javax.swing.JLabel();
        lLogo = new javax.swing.JLabel();

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

        lNames.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lNames.setText("<html> Reinier van der Hoeven<br /> Tim Klaversma<br />Jeffrey van der Leije<br /> Gido Manders");

        lLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/logo_about.png"))); // NOI18N

        javax.swing.GroupLayout pAboutLayout = new javax.swing.GroupLayout(pAbout);
        pAbout.setLayout(pAboutLayout);
        pAboutLayout.setHorizontalGroup(
            pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pAboutLayout.createSequentialGroup()
                        .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lCreators)
                            .addComponent(lVersion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lVersionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pAboutLayout.setVerticalGroup(
            pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAboutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lVersion)
                    .addComponent(lVersionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCreators)
                    .addComponent(lNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(pAbout, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lCreators;
    private javax.swing.JLabel lLogo;
    private javax.swing.JLabel lNames;
    private javax.swing.JLabel lVersion;
    private javax.swing.JLabel lVersionCode;
    private javax.swing.JPanel pAbout;
    // End of variables declaration//GEN-END:variables
}
