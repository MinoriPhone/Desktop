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
     * Print to XML
     *
     */
    public String printXML();
}
