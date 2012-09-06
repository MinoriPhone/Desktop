package Model;

/**
 * The node is a spot which can be placed on a map. You can say that a spot represents a location.
 */
public class Node {

    // Variables
    private int id;
    private Double longitude;
    private Double latitude;

    /**
     * Constructor
     */
    public Node() {
    }

    /**
     * Overload Constructor
     *
     * @param id int
     * @param longitude Double
     * @param latitude Double
     */
    public Node(int id, Double longitude, Double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Get the id of a Node
     *
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the Node
     *
     * @param id int The id of the Node
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the longitude from the Node. If no longitude is set, null will be returned.
     *
     * @return Double longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Set the longitude for the Node.
     *
     * @param longitude Double longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the latitude from the Node.
     *
     * @return Double longitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Set the latitude for the Node.
     *
     * @param latitude Double latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
