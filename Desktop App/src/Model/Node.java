package Model;

import Plugins.jxmap.swingx.mapviewer.DefaultWaypoint;
import Plugins.jxmap.swingx.mapviewer.GeoPosition;
import java.awt.Color;

public class Node extends DefaultWaypoint {

    // Variables
    private Color color = Color.BLUE;
    private String label;
    private Double radius;

    /**
     * Constructor with doubles
     *
     * @param latitude double
     * @param longitude double
     */
    public Node(double latitude, double longitude) {
        super(new GeoPosition(latitude, longitude)); // Creates a waypoint with the geoposition        
        this.radius = 25.0;
    }

    /**
     * Overload constructor with geolocation
     *
     * @param geoposition GeoPosition The jxMapView geoposition
     */
    public Node(GeoPosition geoposition) {
        super(geoposition); // Creates a waypoint with the geoposition           
        this.radius = 25.0;
    }

    /**
     * Call the function Geoposition() of the parent (DefaultWaypoint)
     *
     * @return GeoPosition
     */
    public GeoPosition getGeoposition() {
        return super.getPosition();
    }

    /**
     * Call the function setGeoposition(..) of the parent (DefaultWaypoint)
     *
     * @param geoposition GeoPosition
     */
    public void setGeoposition(GeoPosition geoposition) {
        super.setPosition(geoposition);
    }

    /**
     * Get the label of a Node (if exists)
     *
     * @note A startNode has a 'S'
     *
     * @return String
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the label of a Node
     *
     * @param label String
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Get the color of a Node
     *
     * @return color Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set the Color of a Node
     *
     * @param color Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Set variables for a startNode
     */
    public void setStart() {
        this.color = Color.GREEN;
        this.label = "S";
    }

    /**
     * Returns true if the given Node is a startNode
     *
     * @return boolean
     */
    public Boolean getStart() {
        if (this.color == Color.GREEN && this.label.equals("S")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the current radius of a Node
     *
     * @return double
     */
    public Double getRadius() {
        return radius;
    }

    /**
     * Set the current radius of a Node
     *
     * @param radius Double
     */
    public void setRadius(Double radius) {
        if (radius <= 500) {
            this.radius = radius;
        } else {
            this.radius = 500.0;
        }
    }

    /**
     * Show the current GPS-location of a Node
     *
     * @return String
     */
    @Override
    public String toString() {
        return super.getPosition().getLatitude() + " : " + super.getPosition().getLongitude();
    }

    /**
     * Get XML
     *
     * @param XMLProject boolean Used to define difference between shortcut or not
     *
     * @return String
     */
    public String printXML(boolean XMLProject) {
        String XMLString = "";
        XMLString += "<longitude>" + this.getGeoposition().getLongitude() + "</longitude>\r\n";
        XMLString += "<latitude>" + this.getGeoposition().getLatitude() + "</latitude>\r\n";
        XMLString += "<radius>" + this.getRadius() + "</radius>\r\n";
        return XMLString;
    }
}
