package View;

import Model.Application;
import Model.ExtensionFileFilter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

/**
 * Sets the projectsettings of the application
 */
public class ProjectSettings extends JDialog {

    // Variables
    private Map map;
    private Main main;
    private String tempFont;
    private int tempFontStyle;
    private int tempFontSize;
    private Color tempFontColor;

    /**
     * Create a new projectsettings dialog
     *
     * @param main Main the main component
     * @param map Map the map component
     */
    public ProjectSettings(Main main, Map map) {
        super(main, true);

        initComponents();

        // Set variables
        this.map = map;
        this.main = main;

        // Setup choosable font properties
        setupChoosableFontProperties();

        // Set standard font, style and size from given project settings
        this.cbFonts.setSelectedItem(this.main.getDocumentStyleSettings().getCurrentFont());

        if (this.main.getDocumentStyleSettings().getCurrentFontStyle() == 0) {
            this.cbFontStyle.setSelectedItem("Plain");
        } else if (this.main.getDocumentStyleSettings().getCurrentFontStyle() == 1) {
            this.cbFontStyle.setSelectedItem("Bold");
        } else if (this.main.getDocumentStyleSettings().getCurrentFontStyle() == 2) {
            this.cbFontStyle.setSelectedItem("Italic");
        }

        this.cbFontSize.setSelectedItem(this.main.getDocumentStyleSettings().getCurrentFontSize());

        // Get the size of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        // Determine the new location of the window
        int x = (dim.width - (this.getSize().width)) / 2;
        int y = (dim.height - (this.getSize().height)) / 2;

        // Center the window
        this.setLocation(x, y);

        // Set Main icon
        java.net.URL url = ClassLoader.getSystemResource("View/Images/72x72.png");
        java.awt.Image img = tk.createImage(url);
        this.setIconImage(img);

        // Set the storyname
        tfStoryName.setText(map.getStory().getName());

        // Set the storyimage
        if (map.getStory().getImage() != null) {
            tfStoryImage.setText(map.getStory().getImage().getAbsolutePath());
        }

        // Setup temp variables
        this.tempFont = this.main.getDocumentStyleSettings().getCurrentFont();
        this.tempFontStyle = this.main.getDocumentStyleSettings().getCurrentFontStyle();
        this.tempFontSize = this.main.getDocumentStyleSettings().getCurrentFontSize();
        this.tempFontColor = this.main.getDocumentStyleSettings().getCurrentFontColor();
    }

    /**
     * Setting up all possible font properties
     */
    private void setupChoosableFontProperties() {

        // Get an array of all font names
        String[] fontNames = this.main.getAvailableFonts();

        // Add Fonts
        for (String font : fontNames) {
            this.cbFonts.addItem(font);
        }
        this.cbFonts.setEditable(false);

        // Add Font styles
        this.cbFontStyle.addItem("Plain");
        this.cbFontStyle.addItem("Bold");
        this.cbFontStyle.addItem("Italic");
        this.cbFontStyle.setEditable(false);

        // Add Font sizes
        for (int size = 8; size <= 72; size++) {
            this.cbFontSize.addItem(size);
        }
        this.cbFontSize.setEditable(false);
    }

    /* DO NOT TOUCH */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pProjectSettings = new javax.swing.JPanel();
        lTitle = new javax.swing.JLabel();
        bSave = new javax.swing.JButton();
        pStorySettings = new javax.swing.JPanel();
        lStoryName = new javax.swing.JLabel();
        lStoryImage = new javax.swing.JLabel();
        tfStoryName = new javax.swing.JTextField();
        tfStoryImage = new javax.swing.JTextField();
        bImageBrowse = new javax.swing.JButton();
        pTextSettings = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbFonts = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbFontStyle = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbFontSize = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        bColorChooser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitle.setText("Project settings");

        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        pStorySettings.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Story settings   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        lStoryName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStoryName.setText("Story name:");

        lStoryImage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lStoryImage.setText("Story image:");

        tfStoryImage.setEditable(false);
        tfStoryImage.setMaximumSize(new java.awt.Dimension(6, 20));

        bImageBrowse.setText("Browse..");
        bImageBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bImageBrowseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pStorySettingsLayout = new javax.swing.GroupLayout(pStorySettings);
        pStorySettings.setLayout(pStorySettingsLayout);
        pStorySettingsLayout.setHorizontalGroup(
            pStorySettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pStorySettingsLayout.createSequentialGroup()
                .addGroup(pStorySettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lStoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lStoryImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pStorySettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfStoryName)
                    .addComponent(tfStoryImage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bImageBrowse))
        );

        pStorySettingsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfStoryImage, tfStoryName});

        pStorySettingsLayout.setVerticalGroup(
            pStorySettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pStorySettingsLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(pStorySettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lStoryName)
                    .addComponent(tfStoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pStorySettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfStoryImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lStoryImage)
                    .addComponent(bImageBrowse))
                .addContainerGap())
        );

        pStorySettingsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tfStoryImage, tfStoryName});

        pTextSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Media item Text settings   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Font:");

        cbFonts.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbFonts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontsActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Style:");

        cbFontStyle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbFontStyle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontStyleActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Size:");

        cbFontSize.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontSizeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Color:");

        bColorChooser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/colorwheel.png"))); // NOI18N
        bColorChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bColorChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pTextSettingsLayout = new javax.swing.GroupLayout(pTextSettings);
        pTextSettings.setLayout(pTextSettingsLayout);
        pTextSettingsLayout.setHorizontalGroup(
            pTextSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTextSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pTextSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pTextSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFonts, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bColorChooser))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pTextSettingsLayout.setVerticalGroup(
            pTextSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTextSettingsLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pTextSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbFonts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pTextSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFontStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pTextSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pTextSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bColorChooser))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pProjectSettingsLayout = new javax.swing.GroupLayout(pProjectSettings);
        pProjectSettings.setLayout(pProjectSettingsLayout);
        pProjectSettingsLayout.setHorizontalGroup(
            pProjectSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProjectSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pProjectSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pProjectSettingsLayout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(pProjectSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pStorySettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bSave)
                            .addComponent(pTextSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );
        pProjectSettingsLayout.setVerticalGroup(
            pProjectSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pProjectSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pStorySettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pTextSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(bSave)
                .addContainerGap())
        );

        getContentPane().add(pProjectSettings, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed

        // Set a new story name. If it is set to an empty string, it will set the default name
        if (!tfStoryName.getText().equals("")) {
            this.map.getStory().setName(tfStoryName.getText());
        } else {
            this.map.getStory().setName(main.getDefaultStoryName());
        }

        // Add an astrix if the story has been changed
        if (map.getStory().isSomethingChanged()) {
            main.setTitle("*" + this.map.getStory().getName() + " - iStory designer " + Application.getVersion());
        } else {
            main.setTitle(this.map.getStory().getName() + " - iStory designer " + Application.getVersion());
        }

        // Save mediaitem text settings
        this.main.getDocumentStyleSettings().setCurrentFont(this.tempFont);
        this.main.getDocumentStyleSettings().setCurrentFontStyle(this.tempFontStyle);
        this.main.getDocumentStyleSettings().setCurrentFontSize(this.tempFontSize);
        this.main.getDocumentStyleSettings().setCurrentFontColor(this.tempFontColor);

        // Refresh the routes panel
        main.getPanelRoutes().refreshList(map.getStory().getRoutes());
        dispose();
    }//GEN-LAST:event_bSaveActionPerformed

    private void bImageBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bImageBrowseActionPerformed

        JFileChooser j = new JFileChooser();

        // Add Image filter to the chooser
        j.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".JPG", ".JPEG", ".PNG", ".BMP"},
                "Images (*.JPG|JPEG|PNG|BMP)"));

        // The user selected an imagefile
        if (j.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File projectFile = j.getSelectedFile();
            map.getStory().setImage(projectFile);
            tfStoryImage.setText(map.getStory().getImage().getAbsolutePath());
        }
    }//GEN-LAST:event_bImageBrowseActionPerformed

    private void cbFontsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFontsActionPerformed
        // Get selected font
        JComboBox source = (JComboBox) evt.getSource();
        String font = (String) source.getSelectedItem();

        // Apply new font variables to document
        this.tempFont = font;
    }//GEN-LAST:event_cbFontsActionPerformed

    private void cbFontStyleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFontStyleActionPerformed
        // Get selected font style
        JComboBox source = (JComboBox) evt.getSource();
        String style = (String) source.getSelectedItem();

        // Set current font style
        if ("Plain".equals(style)) {
            this.tempFontStyle = Font.PLAIN;
        } else if ("Bold".equals(style)) {
            this.tempFontStyle = Font.BOLD;
        } else if ("Italic".equals(style)) {
            this.tempFontStyle = Font.ITALIC;
        }
    }//GEN-LAST:event_cbFontStyleActionPerformed

    private void cbFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFontSizeActionPerformed
        // Get selected font size
        JComboBox source = (JComboBox) evt.getSource();
        int size = Integer.parseInt(source.getSelectedItem().toString());

        // Apply new font variables to document
        this.tempFontSize = size;
    }//GEN-LAST:event_cbFontSizeActionPerformed

    private void bColorChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bColorChooserActionPerformed
        // Get selected font color
        Color fontColor = JColorChooser.showDialog(this, "Choose font color", this.main.getDocumentStyleSettings().getCurrentFontColor());

        // Apply new font variables to document
        this.tempFontColor = fontColor;
    }//GEN-LAST:event_bColorChooserActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bColorChooser;
    private javax.swing.JButton bImageBrowse;
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox cbFontSize;
    private javax.swing.JComboBox cbFontStyle;
    private javax.swing.JComboBox cbFonts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lStoryImage;
    private javax.swing.JLabel lStoryName;
    private javax.swing.JLabel lTitle;
    private javax.swing.JPanel pProjectSettings;
    private javax.swing.JPanel pStorySettings;
    private javax.swing.JPanel pTextSettings;
    private javax.swing.JTextField tfStoryImage;
    private javax.swing.JTextField tfStoryName;
    // End of variables declaration//GEN-END:variables
}
