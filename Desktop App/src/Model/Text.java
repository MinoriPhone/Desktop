package Model;

/**
 * Instance of MediaItem
 *
 * This is an object of Text. Users kan link a Text-object to a certain Link object
 */
public class Text implements MediaItem {

    // Variables
    private String fileName;
    private String absPath;
    private int showDurationInSeconds;
    private Link shortcut;
 
    /**
     * Constructor
     */
    public Text() {
    }

    /**
     * Overload Constructor
     *
     * @param fileName String Name of the Text-file
     * @param absPath String absolute path to Text-file
     * @param showDurationInSeconds String The number of seconds for how long we want to show the Text
     * @param shortcut Link the link to the main MediaItem
     */
    public Text(String fileName, String absPath, int showDurationInSeconds) {
        this.fileName = fileName;
        this.absPath = absPath;
        this.setShowDurationInSeconds(showDurationInSeconds);
        this.shortcut = null;
    }

    /**
     * Get the name of the Text-file
     *
     * @return String Name of the Text-file
     */
    @Override
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Set the name of the Text-file
     *
     * @param fileName String Name of the Text-file
     */
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the number of seconds for how long we want to show the Text-file
     *
     * @return int -1 This is always -1, because a Text has its own duration
     */
    @Override
    public int getShowDurationInSeconds() {
        return this.showDurationInSeconds;
    }

    /**
     * Set the number of seconds for how long we want to show the Text-file. Number of seconds can NOT be smaller than 2
     *
     * @param showDurationInSeconds int Number of seconds for showing the Text-file
     */
    @Override
    public void setShowDurationInSeconds(int showDurationInSeconds) {
        if (showDurationInSeconds < 2) {
            this.showDurationInSeconds = 2;
        } else {
            this.showDurationInSeconds = showDurationInSeconds;
        }
    }

    @Override
    public String printXML(boolean XMLProject) {
        String XMLString = "";
        XMLString += "<message>\r\n";

        if (shortcut != null) {
            XMLString += "<shortcut>" + this.shortcut.getId() + "</shortcut>\r\n";
        }

        // Full pathnames or just the file names
        if (XMLProject) {
            XMLString += "<filename>" + this.absPath + this.fileName + "</filename>\r\n";
        } else {
            XMLString += "<filename>" + this.fileName + "</filename>\r\n";
        }

        XMLString += "<duration>" + this.showDurationInSeconds + "</duration>\r\n";
        XMLString += "</message>\r\n";
        return XMLString;
    }

    /**
     * Get the absolute path of the file
     *
     * @return String absolute path
     */
    @Override
    public String getAbsolutePath() {
        return this.absPath;
    }

    /**
     * Set the absolute path to the file
     *
     * @param absPath String absolute path to file
     */
    @Override
    public void setAbsolutePath(String absPath) {
        this.absPath = absPath;
    }

    @Override
    public void setShortcut(Link isShortcut) {
        this.shortcut = isShortcut;
    }

    @Override
    public Link getShortcut() {
        return this.shortcut;
    }
}
