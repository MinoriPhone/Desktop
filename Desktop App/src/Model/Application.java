package Model;

/**
 * Meta data of this Desktop Application
 */
public class Application {

    // Variables
    private double version = 0.2;

    /**
     * Get the current version of this application
     *
     * @return double The current application version
     */
    public double getVersion() {
        return version;
    }

    /**
     * Set the current version of this application
     *
     * @param version double
     */
    public void setVersion(double version) {
        this.version = version;
    }
}
