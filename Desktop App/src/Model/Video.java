package Model;

public class Video implements MediaItem {

    // Variables
    private String fileName;
    private String absPath;
    private int showDurationInSeconds = -1;
    private Link shortcut;
    private boolean isCorrupt;

    /**
     * Constructor
     */
    public Video() {
    }

    /**
     * Overload Constructor
     *
     * @param fileName String Name of the Video-file
     * @param absPath String absolute path to Text-file
     */
    public Video(String fileName, String absPath) {
        this.fileName = fileName;
        this.absPath = absPath;
        this.isCorrupt = false;
    }

    /**
     * Get the name of the Video-file
     *
     * @return String Name of the Video-file
     */
    @Override
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Set the name of the Video-file
     *
     * @param fileName String Name of the Video-file
     */
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the number of seconds for how long we want to show the Video-file
     *
     * @return int -1 This is always -1, because a video has its own duration
     */
    @Override
    public int getShowDurationInSeconds() {
        return this.showDurationInSeconds;
    }

    /**
     * Can NOT modify show duration for a Video-file
     *
     * @param showDurationInSeconds int
     */
    @Override
    public void setShowDurationInSeconds(int showDurationInSeconds) {
    }

    /**
     * Get XML
     *
     * @param XMLProject boolean Used to define difference between shortcut or not
     *
     * @return String
     */
    @Override
    public String printXML(boolean XMLProject) {
        String XMLString = "";
        XMLString += "<video>\r\n";

        if (shortcut != null) {
            XMLString += "<shortcut>" + this.shortcut.getId() + "</shortcut>\r\n";
        }

        if (XMLProject) {
            XMLString += "<filename>" + this.absPath + this.fileName + "</filename>\r\n";
        } else {
            XMLString += "<filename>" + this.fileName + "</filename>\r\n";
        }
        XMLString += "</video>\r\n";
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

    /**
     * Set whether this Text object is a shortcut to a Link or not
     *
     * @param isShortcut Link
     */
    @Override
    public void setShortcut(Link shortcut) {
        this.shortcut = shortcut;
    }

    /**
     * Get shortcut for this Link
     *
     * @return Link
     */
    @Override
    public Link getShortcut() {
        return shortcut;
    }

    /**
     * Indicates if this Text object is corrupt. Is true if the path to file doesn't exists anymore
     *
     * @return boolean
     */
    @Override
    public boolean isCorrupt() {
        return this.isCorrupt;
    }

    /**
     * Set if this Text object is corrupt or not. Is true if the path to file doesn't exists anymore
     *
     * @param corrupt boolean
     */
    @Override
    public void setCorrupt(boolean corrupt) {
        this.isCorrupt = corrupt;
    }
}
