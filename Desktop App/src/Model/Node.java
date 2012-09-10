package Model;

import Plugins.jxmap.swingx.mapviewer.DefaultWaypoint;
import Plugins.jxmap.swingx.mapviewer.GeoPosition;

/**
 * The node is a spot which can be placed on a map. You can say that a spot represents a location.
 */
public class Node extends DefaultWaypoint {

    /**
     * Constructor with doubles
     * 
     * @param latitude double
     * @param longitude double     * 
     */
    public Node(double latitude, double longitude) {
        super(new GeoPosition(latitude, longitude)); // Creates a waypoint with the geoposition        
    }

    /**
     * Constructor with geolocation
     *
     * @param geoposition The jxMapView geoposition
     */
    public Node(GeoPosition geoposition) {
        super(geoposition); // Creates a waypoint with the geoposition        
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
     * @return 
     */
    @Override
    public String toString() {
        return  super.getPosition().getLatitude()+ " : " + super.getPosition().getLongitude();
    }
}
