package View;

import Model.DocumentStyleSettings;
import Model.ExtensionFileFilter;
import Model.Filename;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLEditorKit;

public class CreateText extends JDialog {

    // Variables
    private AddMedia parent;
    private StyleContext context;
    private final DefaultStyledDocument document;
    private JScrollPane scrollpane;
    private JTextPane textPane;
    private Style currentDocumentStyle;
    private String docStyle;
    private String currentFont;
    private int currentFontStyle;
    private int currentFontSize;
    private Color currentFontColor;
    private DocumentStyleSettings dss;

    /**
     * Creates new form CreateText
     */
    public CreateText(AddMedia parent) {
        super(parent, true);
        this.parent = parent;
        this.dss = parent.getParentView().getDocumentStyleSettings();

        initComponents();

        // Create the StyleContext, the document and add it to the pane
        this.docStyle = "style";
        this.context = new StyleContext();
        this.document = new DefaultStyledDocument(this.context);
        this.textPane = new JTextPane(this.document);
        this.scrollpane = new JScrollPane(this.textPane);
        this.pDocument.add(this.scrollpane, BorderLayout.CENTER);
        this.pDocument.revalidate();

        // Get the size of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        // Determine the new location of the window
        int x = (dim.width - (this.getSize().width)) / 2;
        int y = (dim.height - (this.getSize().height)) / 2;

        // Center this window
        this.setLocation(x, y);

        // Initialize standard font variables
        this.currentFont = this.dss.getCurrentFont();
        this.currentFontStyle = this.dss.getCurrentFontStyle();
        this.currentFontSize = this.dss.getCurrentFontSize();
        this.currentFontColor = this.dss.getCurrentFontColor();

        // Setup choosable font properties
        setupChoosableFontProperties();
        applyNewStyle();
    }

    /* DO NOT TOUCH */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pMain = new javax.swing.JPanel();
        pNorth = new javax.swing.JPanel();
        lTitle = new javax.swing.JLabel();
        pWest = new javax.swing.JPanel();
        pFontProps = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbFonts = new javax.swing.JComboBox();
        cbFontStyle = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbFontSize = new javax.swing.JComboBox();
        bColorChooser = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pCenter = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pDocument = new javax.swing.JPanel();
        bSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pMain.setLayout(new java.awt.BorderLayout());

        lTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lTitle.setText("Create text file");

        org.jdesktop.layout.GroupLayout pNorthLayout = new org.jdesktop.layout.GroupLayout(pNorth);
        pNorth.setLayout(pNorthLayout);
        pNorthLayout.setHorizontalGroup(
            pNorthLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(lTitle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
        );
        pNorthLayout.setVerticalGroup(
            pNorthLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pNorthLayout.createSequentialGroup()
                .addContainerGap()
                .add(lTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pMain.add(pNorth, java.awt.BorderLayout.PAGE_START);

        pFontProps.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Font Properties   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Font:");

        cbFonts.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbFonts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontsActionPerformed(evt);
            }
        });

        cbFontStyle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbFontStyle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontStyleActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Style:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Size:");

        cbFontSize.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFontSizeActionPerformed(evt);
            }
        });

        bColorChooser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/colorwheel.png"))); // NOI18N
        bColorChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bColorChooserActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Color:");

        org.jdesktop.layout.GroupLayout pFontPropsLayout = new org.jdesktop.layout.GroupLayout(pFontProps);
        pFontProps.setLayout(pFontPropsLayout);
        pFontPropsLayout.setHorizontalGroup(
            pFontPropsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pFontPropsLayout.createSequentialGroup()
                .add(pFontPropsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(pFontPropsLayout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pFontPropsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbFontStyle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cbFonts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cbFontSize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 93, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bColorChooser))
                .add(0, 0, Short.MAX_VALUE))
        );
        pFontPropsLayout.setVerticalGroup(
            pFontPropsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pFontPropsLayout.createSequentialGroup()
                .addContainerGap()
                .add(pFontPropsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(cbFonts, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pFontPropsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cbFontStyle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pFontPropsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(cbFontSize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pFontPropsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bColorChooser))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pFontPropsLayout.linkSize(new java.awt.Component[] {bColorChooser, cbFontSize, cbFontStyle, cbFonts, jLabel1, jLabel2, jLabel3}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout pWestLayout = new org.jdesktop.layout.GroupLayout(pWest);
        pWest.setLayout(pWestLayout);
        pWestLayout.setHorizontalGroup(
            pWestLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pWestLayout.createSequentialGroup()
                .addContainerGap()
                .add(pWestLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pFontProps, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pWestLayout.setVerticalGroup(
            pWestLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pWestLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pFontProps, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        pMain.add(pWest, java.awt.BorderLayout.LINE_START);

        pDocument.setLayout(new java.awt.BorderLayout());

        bSave.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pCenterLayout = new org.jdesktop.layout.GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
            pCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pCenterLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pCenterLayout.createSequentialGroup()
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 585, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 22, Short.MAX_VALUE))
                    .add(pCenterLayout.createSequentialGroup()
                        .add(pCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(pDocument, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(pCenterLayout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(bSave)))
                        .add(35, 35, 35))))
        );
        pCenterLayout.setVerticalGroup(
            pCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pCenterLayout.createSequentialGroup()
                .addContainerGap()
                .add(pCenterLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(pCenterLayout.createSequentialGroup()
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pDocument, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(bSave)
                        .add(6, 6, 6))
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pMain.add(pCenter, java.awt.BorderLayout.CENTER);

        getContentPane().add(pMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbFontsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFontsActionPerformed
        // Get selected font
        JComboBox source = (JComboBox) evt.getSource();
        String font = (String) source.getSelectedItem();

        // Apply new font variables to document
        this.currentFont = font;
        applyNewStyle();
    }//GEN-LAST:event_cbFontsActionPerformed
    private void cbFontStyleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFontStyleActionPerformed
        // Get selected font style
        JComboBox source = (JComboBox) evt.getSource();
        String style = (String) source.getSelectedItem();

        // Set current font style   
        if ("Plain".equals(style)) {
            this.currentFontStyle = Font.PLAIN;
        } else if ("Bold".equals(style)) {
            this.currentFontStyle = Font.BOLD;
        } else if ("Italic".equals(style)) {
            this.currentFontStyle = Font.ITALIC;
        }

        // Apply new font variables to document
        applyNewStyle();
    }//GEN-LAST:event_cbFontStyleActionPerformed
    private void cbFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFontSizeActionPerformed
        // Get selected font size
        JComboBox source = (JComboBox) evt.getSource();
        int size = Integer.parseInt(source.getSelectedItem().toString());

        // Apply new font variables to document
        this.currentFontSize = size;
        applyNewStyle();
    }//GEN-LAST:event_cbFontSizeActionPerformed

    private void bColorChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bColorChooserActionPerformed
        // Get selected font color
        Color color = JColorChooser.showDialog(this, "Choose font color", this.currentFontColor);

        // Apply new font variables to document
        if (color != null) {
            this.currentFontColor = color;
            applyNewStyle();
        }
    }//GEN-LAST:event_bColorChooserActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        // Create document
        exportToHTML();
    }//GEN-LAST:event_bSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bColorChooser;
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox cbFontSize;
    private javax.swing.JComboBox cbFontStyle;
    private javax.swing.JComboBox cbFonts;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lTitle;
    private javax.swing.JPanel pCenter;
    private javax.swing.JPanel pDocument;
    private javax.swing.JPanel pFontProps;
    private javax.swing.JPanel pMain;
    private javax.swing.JPanel pNorth;
    private javax.swing.JPanel pWest;
    // End of variables declaration//GEN-END:variables

    /**
     * Setting up all possible font properties
     */
    private void setupChoosableFontProperties() {

        // Load fonts and add them to the combobox 'cbFonts'
        String[] fontNames = parent.getParentView().getAvailableFonts();
        for (String font : fontNames) {
            this.cbFonts.addItem(font);
        }
        this.cbFonts.setSelectedItem(this.dss.getCurrentFont());
        this.cbFonts.setEditable(false);

        // Add Font styles
        this.cbFontStyle.addItem("Plain");
        this.cbFontStyle.addItem("Bold");
        this.cbFontStyle.addItem("Italic");
        this.cbFontStyle.setSelectedItem(this.dss.getCurrentFontStyle());
        this.cbFontStyle.setEditable(false);

        // Add Font sizes
        for (int size = 8; size <= 72; size++) {
            this.cbFontSize.addItem(size);
        }
        this.cbFontSize.setSelectedItem(this.dss.getCurrentFontSize());
        this.cbFontSize.setEditable(false);
    }

    /**
     * Check if current font style is Bold
     *
     * @return boolean
     */
    private boolean isCurrentFontStyleBold() {
        if (Font.BOLD == this.currentFontStyle) {
            return true;
        }
        return false;
    }

    /**
     * Check if current font style is Italic
     *
     * @return boolean
     */
    private boolean isCurrentFontStyleItalic() {
        if (Font.ITALIC == this.currentFontStyle) {
            return true;
        }
        return false;
    }

    /**
     * Apply style to document
     */
    private void applyNewStyle() {
        // Remove old style
        this.context.removeStyle(this.docStyle);

        // Create new style
        this.currentDocumentStyle = this.context.addStyle(this.docStyle, null);
        this.currentDocumentStyle.addAttribute(StyleConstants.FontFamily, this.currentFont);
        this.currentDocumentStyle.addAttribute(StyleConstants.Bold, isCurrentFontStyleBold());
        this.currentDocumentStyle.addAttribute(StyleConstants.Italic, isCurrentFontStyleItalic());
        this.currentDocumentStyle.addAttribute(StyleConstants.FontSize, this.currentFontSize);
        this.currentDocumentStyle.addAttribute(StyleConstants.Foreground, this.currentFontColor);

        // Finally, apply the new style to the heading
        this.document.setParagraphAttributes(0, this.document.getLength(), this.currentDocumentStyle, true);
    }

    /**
     * Export style document to html
     */
    private void exportToHTML() {
        // Prompt save dialog
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);

        chooser.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".html"}, // Extensions we accept
                "HTML files (*.html)"));
        // Catch action of the File Chooser Dialog Window
        int option = chooser.showSaveDialog(this);

        // User pressed save
        if (option == JFileChooser.APPROVE_OPTION) {

            // Initialize output stream
            BufferedOutputStream out = null;

            try {
                // Create kit to export
                HTMLEditorKit kit = new HTMLEditorKit();

                // Get path
                String filePath = chooser.getSelectedFile().getAbsolutePath();

                // Add ".html  at the end of the string (only if it doesn't exist)
                if (!filePath.endsWith(".html")) {
                    filePath += ".html";
                }

                // Check if the html file already exists
                File HTMLFile = new File(filePath);
                if (!HTMLFile.isFile()) {
                    out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                } else {
                    // Confirm the save
                    int confirmoption = JOptionPane.showConfirmDialog(null,
                            "There is already a file with the same name in the selected folder. \n "
                            + "Do you want to overwrite this file?",
                            "File already exists",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (confirmoption == JOptionPane.YES_OPTION) {
                        File deletedFile = new File(filePath);
                        deletedFile.delete();
                        out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                    } else {
                        exportToHTML();
                        return;
                    }
                }
                // Get style document
                StyledDocument doc = this.textPane.getStyledDocument();
                String unescapedText = doc.getText(0, doc.getLength());
                doc.remove(0, doc.getLength());
                doc.insertString(0, escapeHTML(unescapedText), this.currentDocumentStyle);

                // Export
                kit.write(out, doc, doc.getStartPosition().getOffset(), doc.getLength());
                out.close();

                // Get filename and extension
                Filename fn = new Filename(filePath);
                String fileName = fn.filename();
                String extension = fn.extension().toLowerCase();

                // Get absolute path to the file (so path without filename and extension)
                String abspath = filePath.substring(0, filePath.length() - fileName.length() - extension.length() - 1);

                // Close this screen
                this.parent.setVisible(true);
                this.dispose();

                // Return parameters for creating Text Item
                this.parent.addTextMediaItemToList(fileName + "." + extension, abspath);

            } catch (IOException ex) {
                Logger.getLogger(CreateText.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
                Logger.getLogger(CreateText.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Escape HTML string
     *
     * @param s String we want to escape
     *
     * @return String
     */
    public static String escapeHTML(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\"':
                    sb.append("&quot;");
                    break;
                case ' ':
                    sb.append("&nbsp;");
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }
}
