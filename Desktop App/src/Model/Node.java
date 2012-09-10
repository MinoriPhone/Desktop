package Model;

/**
 * The node is a spot which can be placed on a map. You can say that a spot
 * represents a location.
 */
public class Node {

    // Variables
    private double longitude;  // horizontal coördinaat
    private double latitude;   // vertical coordinaat

    /**
     * Overload Constructor
     *
     * @param longitude double
     * @param latitude double
     */
    public Node(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Get the longitude from the Node. If no longitude is set, null will be
     * returned.
     *
     * @return double longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the longitude for the Node.
     *
     * @param longitude double longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the latitude from the Node.
     *
     * @return double longitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the latitude for the Node.
     *
     * @param latitude double latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Print the GPS coördinates of the Node
     *
     * @return String
     */
    @Override
    public String toString() {
        return "" + this.longitude + " : " + this.latitude;
    }
}
