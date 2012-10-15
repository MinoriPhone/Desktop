package Model;

/**
 * Get the filename or extension of a filepath
 */
public class Filename {

    // Variables
    private String fullPath;
    private String pathSeparator;
    private char extensionSeparator;

    /**
     * Constructor
     *
     * @param str Fullpath of the filename
     */
    public Filename(String str) {
        this.fullPath = str;
        this.pathSeparator = System.getProperty("file.separator");
        this.extensionSeparator = '.';
    }

    /**
     * Get the extension of a filepath
     *
     * @return String extension
     */
    public String extension() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        return fullPath.substring(dot + 1);
    }

    /**
     * Get the filename of a filepath without extension
     *
     * @return String filename
     */
    public String filename() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(sep + 1, dot);
    }
}