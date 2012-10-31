package Model;

import Plugins.jxmap.swingx.mapviewer.DefaultWaypoint;
import Plugins.jxmap.swingx.mapviewer.GeoPosition;
import java.awt.Color;

/**
 * The node is a spot which can be placed on a map. You can say that a spot represents a location.
 */
public class Node extends DefaultWaypoint {

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
     * Constructor with geolocation
     *
     * @param geoposition The jxMapView geoposition
     */
    public Node(GeoPosition geoposition) {
        super(geoposition); // Creates a waypoint with the geoposition           
        this.radius = 25.0;
    }

    /**
     *
     * @return
     */
    public GeoPosition getGeoposition() {
        return super.getPosition();
    }

    /**
     *
     * @param geoposition
     */
    public void setGeoposition(GeoPosition geoposition) {
        super.setPosition(geoposition);
    }

    /**
     *
     * @return color
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public void setStart() {
        this.color = Color.GREEN;
        this.label = "S";
    }

    public Boolean getStart() {
        if (this.color == Color.GREEN && this.label.equals("S")) {
            return true;
        } else {
            return false;
        }
    }

    public void setEnd() {
        this.color = Color.RED;
        this.label = "E";
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        if (radius <= 500) {
            this.radius = radius;
        } else {
            this.radius = 500.0;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return super.getPosition().getLatitude() + " : " + super.getPosition().getLongitude();
    }

    /**
     * Print XML
     */
    public String printXML(boolean XMLProject) {
        String XMLString = "";
        XMLString += "<longitude>" + this.getGeoposition().getLongitude() + "</longitude>\r\n";
        XMLString += "<latitude>" + this.getGeoposition().getLatitude() + "</latitude>\r\n";
        XMLString += "<radius>" + this.getRadius() + "</radius>\r\n";
        return XMLString;
    }
}
