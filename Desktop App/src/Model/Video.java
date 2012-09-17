package Model;

/**
 * Instance of MediaItem
 *
 * This is an object of Video. Users kan link a Video-object to a certain Link object
 */
public class Video implements MediaItem {

    // Variables
    private String fileName;
    private int showDurationInSeconds = -1;

    /**
     * Constructor
     *
     * @param fileName String Name of the Video-file
     */
    public Video(String fileName) {
        this.fileName = fileName;
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

    @Override
    public String printXML() {
        String XMLString = "";
        XMLString += "<video>";
        XMLString += "<filename>"+this.fileName+"</filename>";
        XMLString += "</video>";
        return XMLString;
    }
}
