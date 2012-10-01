package Model;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * Creates a new FileChooser Dialog Window
 */
public class FileChooser {

    // Variables
    private JFileChooser chooser;

    /**
     * Creates new form FileChooser
     */
    public FileChooser() {

        // Create a JFileChooser
        this.chooser = new JFileChooser();

        // Add Text filter to the chooser
        chooser.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".RTF", ".TXT"}, // Extensions we accept
                "Text Documents (*.RTF|TXT)")); // 'Files of Type' 

        // Add Image filter to the chooser
        chooser.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".JPG", ".JPEG", ".PNG", ".BMP"},
                "Images (*.JPG|JPEG|PNG|BMP)"));

        // Add Video filter to the chooser
        chooser.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".AVI", ".M4V", ".MOV", ".MP4"},
                "Video's (*.AVI|M4V|MOV|MP4)"));

        // Add a filter with all the supported files
        chooser.addChoosableFileFilter(new ExtensionFileFilter(
                new String[]{".RTF", ".TXT", ".JPG", ".JPEG", ".PNG", ".BMP", ".AVI", ".M4V", ".MOV", ".MP4"},
                "All supported files"));

        // Turn off 'All Files' capability of file chooser, so only our custom filter is used.
        chooser.setAcceptAllFileFilterUsed(false);
    }

    /**
     * Return File Chooser Dialog Window
     *
     * @return JFileChooser
     */
    public JFileChooser getChooser() {
        return chooser;
    }

    /**
     * Get object MediaItem (Video, Text or Image) by path of the file the user selected
     *
     * @param filePath String The path to the File
     * @return (instanceof Video || Image || Text) || null
     */
    public MediaItem getMediaItemFromFile(String filePath) {

        // Get filename and extension
        Filename fn = new Filename(filePath);
        String fileName = fn.filename();
        String extension = fn.extension().toLowerCase();

        // Get absolute path to the file (so path without filename and extension)
        String abspath = filePath.substring(0, filePath.length() - fileName.length() - extension.length() - 1);

        // Return Media Item object (Video, Image or Text)
        if (extension.equals("rtf") || extension.equals("txt")) {
            return new Text(fileName + "." + extension, abspath, 0);
        } else if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("bmp")) {
            return new Model.Image(fileName + "." + extension, abspath, 0);
        } else if (extension.equals("avi") || extension.equals("m4v") || extension.equals("mov") || extension.equals("mp4")) {
            return new Video(fileName + "." + extension, abspath);
        } else if(extension.equals("proj")){
            
        }
        return null;
    }
}

/**
 * Get the filename or extension of a filepath
 */
class Filename {

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
