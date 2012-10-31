package Model;

/**
 * Super class for Video, Image, Message
 */
public interface MediaItem {

    /**
     * Get the name of the file including the extension
     *
     * @return String The name of the file
     */
    public String getFileName();

    /**
     * Set the name of the file including the extension
     *
     * @param fileName String The name of the file
     */
    public void setFileName(String fileName);

    /**
     * Get absolute path to the file
     */
    public String getAbsolutePath();

    /**
     * Set the absolute path of the file
     *
     * @param absPath String absolute path to file
     * @return String
     */
    public void setAbsolutePath(String absPath);

    /**
     * Get the number of seconds for how long we want to show the file
     *
     * @return int Number of seconds for showing the file
     */
    public int getShowDurationInSeconds();

    /**
     * Set the number of seconds for how long we want to show the file
     *
     * @param showDurationInSeconds int Number of seconds for showing the file
     */
    public void setShowDurationInSeconds(int showDurationInSeconds);

    /**
     * Set the shortcut Link
     *
     * @param isShortcut
     */
    public void setShortcut(Link isShortcut);

    /**
     * Get the shortcut Link
     *
     * @return true if it is a shortcut
     */
    public Link getShortcut();

    /**
     * Print to XML
     *
     */
    public String printXML(boolean XMLProject);

    public boolean isCorrupt();
    
    public void setCorrupt(boolean corrupt);
}
