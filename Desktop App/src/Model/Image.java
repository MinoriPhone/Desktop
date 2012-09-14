package Model;

/**
 * Instance of MediaItem
 *
 * This is an object of Image. Users kan link a Image-object to a certain Link object
 */
public class Image implements MediaItem {

    // Variables
    private String fileName;
    private int showDurationInSeconds;

    /**
     * Constructor
     *
     * @param fileName String Name of the Image-file
     * @param showDurationInSeconds String The number of seconds for how long we want to show the Image
     */
    public Image(String fileName, int showDurationInSeconds) {
        this.fileName = fileName;
        this.showDurationInSeconds = showDurationInSeconds;
    }

    /**
     * Get the name of the Image-file
     *
     * @return String Name of the Image-file
     */
    @Override
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Set the name of the Image-file
     *
     * @param fileName String Name of the Image-file
     */
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the number of seconds for how long we want to show the Image-file
     *
     * @return int -1 This is always -1, because a Image has its own duration
     */
    @Override
    public int getShowDurationInSeconds() {
        return this.showDurationInSeconds;
    }

    /**
     * Set the number of seconds for how long we want to show the Image-file. Number of seconds can NOT be smaller than 2
     *
     * @param showDurationInSeconds int Number of seconds for showing the Image-file
     */
    @Override
    public void setShowDurationInSeconds(int showDurationInSeconds) {
        if (showDurationInSeconds < 2) {
            this.showDurationInSeconds = 2;
        } else {
            this.showDurationInSeconds = showDurationInSeconds;
        }
    }
}
