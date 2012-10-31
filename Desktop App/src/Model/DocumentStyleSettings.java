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

    public String getCurrentFont() {
        return currentFont;
    }

    public void setCurrentFont(String currentFont) {
        this.currentFont = currentFont;
    }

    public int getCurrentFontStyle() {
        return currentFontStyle;
    }

    public void setCurrentFontStyle(int currentFontStyle) {
        this.currentFontStyle = currentFontStyle;
    }

    public int getCurrentFontSize() {
        return currentFontSize;
    }

    public void setCurrentFontSize(int currentFontSize) {
        this.currentFontSize = currentFontSize;
    }

    public Color getCurrentFontColor() {
        return currentFontColor;
    }

    public void setCurrentFontColor(Color currentFontColor) {
        this.currentFontColor = currentFontColor;
    }

    public Color getCurrentBackgroundColor() {
        return currentBackgroundColor;
    }

    public void setCurrentBackgroundColor(Color currentBackgroundColor) {
        this.currentBackgroundColor = currentBackgroundColor;
    }
}
