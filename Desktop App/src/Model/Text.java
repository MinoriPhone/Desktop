package Model;

/**
 * Instance of MediaItem
 *
 * This is an object of Text. Users kan link a Text-object to a certain Link object
 */
public class Text implements MediaItem {

    // Variables
    private String fileName;
    private int showDurationInSeconds;

    /**
     * Constructor
     *
     * @param fileName String Name of the Text-file
     * @param showDurationInSeconds String The number of seconds for how long we want to show the Text
     */
    public Text(String fileName, int showDurationInSeconds) {
        this.fileName = fileName;
        this.showDurationInSeconds = showDurationInSeconds;
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
    public String printXML() {
        String XMLString = "";
        XMLString += "<message>";
        XMLString += "<filename>"+this.fileName+"</filename>";
        XMLString += "<duration>"+this.showDurationInSeconds+"</duration>";
        XMLString += "</message>";
        return XMLString;
    }
}
