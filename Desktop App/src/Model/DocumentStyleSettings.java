package Model;

import java.awt.Color;
import java.awt.Font;

public class DocumentStyleSettings {

    // Variables
    private String currentFont;
    private int currentFontStyle;
    private int currentFontSize;
    private Color currentFontColor;
    private Color currentBackgroundColor;

    /**
     * Constructor
     */
    public DocumentStyleSettings() {
        this.currentFont = "Arial";
        this.currentFontStyle = Font.PLAIN;
        this.currentFontSize = 14;
        this.currentFontColor = Color.BLACK;
        this.currentBackgroundColor = Color.WHITE;
    }

    /**
     * Get the current font
     *
     * @return String
     */
    public String getCurrentFont() {
        return currentFont;
    }

    /**
     * Set the current font
     *
     * @param currentFont String
     */
    public void setCurrentFont(String currentFont) {
        this.currentFont = currentFont;
    }

    /**
     * Get the current font style
     *
     * @return int
     */
    public int getCurrentFontStyle() {
        return currentFontStyle;
    }

    /**
     * Set the current font style
     *
     * @param currentFontStyle int
     */
    public void setCurrentFontStyle(int currentFontStyle) {
        this.currentFontStyle = currentFontStyle;
    }

    /**
     * Get the current font size
     *
     * @return int
     */
    public int getCurrentFontSize() {
        return currentFontSize;
    }

    /**
     * Set the current font size
     *
     * @param currentFontSize int
     */
    public void setCurrentFontSize(int currentFontSize) {
        this.currentFontSize = currentFontSize;
    }

    /**
     * Get the current font color
     *
     * @return Color
     */
    public Color getCurrentFontColor() {
        return currentFontColor;
    }

    /**
     * Set the current font color
     *
     * @param currentFontColor Color
     */
    public void setCurrentFontColor(Color currentFontColor) {
        this.currentFontColor = currentFontColor;
    }

    /**
     * Get the current background color
     *
     * @return Color
     */
    public Color getCurrentBackgroundColor() {
        return currentBackgroundColor;
    }

    /**
     * Set the current background color
     *
     * @param currentBackgroundColor Color
     */
    public void setCurrentBackgroundColor(Color currentBackgroundColor) {
        this.currentBackgroundColor = currentBackgroundColor;
    }
}
