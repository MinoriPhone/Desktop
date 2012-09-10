package View;

import Model.Application;

/**
 * Frame containing all meta data of the application
 */
public class About extends javax.swing.JFrame {

    // Variables
    private Application app;

    /**
     * Creates new form About
     */
    public About() {
        initComponents();

        // Get Object with all Application mete data
        app = new Application();

        // Set meta data application
        lVersionCode.setText(String.valueOf(app.getVersion()));
    }

    /* DO NOT TOUCH */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pAbout = new javax.swing.JPanel();
        lVersion = new javax.swing.JLabel();
        lVersionCode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        setName("fAbout"); // NOI18N

        lVersion.setText("Version:");
        lVersion.setToolTipText("");

        javax.swing.GroupLayout pAboutLayout = new javax.swing.GroupLayout(pAbout);
        pAbout.setLayout(pAboutLayout);
        pAboutLayout.setHorizontalGroup(
            pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAboutLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lVersionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );
        pAboutLayout.setVerticalGroup(
            pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAboutLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(pAboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lVersion)
                    .addComponent(lVersionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(210, Short.MAX_VALUE))
        );

        getContentPane().add(pAbout, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lVersion;
    private javax.swing.JLabel lVersionCode;
    private javax.swing.JPanel pAbout;
    // End of variables declaration//GEN-END:variables
}
