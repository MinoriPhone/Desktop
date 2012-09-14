package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileFilter;

/**
 * Inherited FileFilter class to facilitate reuse when multiple file filter selections are required.
 */
public class ExtensionFileFilter extends FileFilter {

    // Variables
    private List<String> extensions;
    private String description;

    /**
     * Constructor
     *
     * @param exts String[] Extensions we want to get
     * @param description String The description we will see when selecting file type
     */
    public ExtensionFileFilter(String[] extensions, String description) {
        if (extensions != null) {
            this.extensions = new ArrayList<String>();

            // Clean array of extensions to remove "." and transform to lowercase.
            for (String ext : extensions) {
                this.extensions.add(ext.replace(".", "").trim().toLowerCase());
            }
        }

        // Handle null extensions to avoid NullPointerException when using trim()
        this.description = (description != null) ? description.trim() : "Custom File List";
    }

    /**
     * Handle which files are allowed by the filter
     *
     * @param f The File we want to upload
     * @return boolean True if File is correct (having an extension that we allow)
     */
    @Override
    public boolean accept(File f) {

        // Allow directories to be seen
        if (f.isDirectory()) {
            return true;
        }

        // Exit if no extensions exist
        if (extensions == null) {
            return false;
        }

        // Only show files with extensions we defined
        for (String ext : extensions) {
            if (f.getName().toLowerCase().endsWith("." + ext)) {
                return true;
            }
        }

        // Otherwise file is not shown
        return false;
    }

    /**
     * Get description 'Files of Type'
     *
     * @return String The description 'Files of Type
     */
    @Override
    public String getDescription() {
        return description;
    }
}
