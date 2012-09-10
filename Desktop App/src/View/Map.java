package View;

import Model.Node;
import Plugins.jxmap.swingx.JXMapViewer;
import Plugins.jxmap.swingx.OSMTileFactoryInfo;
import Plugins.jxmap.swingx.input.CenterMapListener;
import Plugins.jxmap.swingx.input.PanKeyListener;
import Plugins.jxmap.swingx.input.PanMouseInputListener;
import Plugins.jxmap.swingx.input.ZoomMouseWheelListenerCenter;
import Plugins.jxmap.swingx.mapviewer.DefaultTileFactory;
import Plugins.jxmap.swingx.mapviewer.GeoPosition;
import Plugins.jxmap.swingx.mapviewer.TileFactoryInfo;
import Plugins.jxmap.swingx.mapviewer.Waypoint;
import Plugins.jxmap.swingx.mapviewer.WaypointPainter;
import Plugins.jxmap.swingx.painter.CompoundPainter;
import Plugins.jxmap.swingx.painter.Painter;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 * Panel containing the Map
 */
public class Map extends JPanel {

    // Variables
    private JXMapViewer mapViewer;
    private WaypointPainter<Waypoint> waypointPainter;
    private List<Painter<JXMapViewer>> painters;
    private CompoundPainter<JXMapViewer> painter;
    private Set<Node> nodes;

    /**
     * Creates new form Map
     */
    public Map() {
        initComponents();

        // Create a map
        mapViewer = new JXMapViewer();

        // Create a TileFactoryInfo for the OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        tileFactory.setThreadPoolSize(8);
        mapViewer.setTileFactory(tileFactory);

        // Set the GEO position + Zoom
        GeoPosition zHHS = new GeoPosition(52.051194, 4.475518);
        mapViewer.setZoom(0);
        mapViewer.setAddressLocation(zHHS);

        // Create waypoints from the geo-positions
        nodes = new HashSet<Node>();
        nodes.add(new Node(zHHS));

        // Create a waypoint painter that takes all the waypoints
        waypointPainter = new WaypointPainter<Waypoint>();
        waypointPainter.setWaypoints(nodes);

        // Add interactions
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(waypointPainter);

        painter = new CompoundPainter<JXMapViewer>(painters);
        mapViewer.setOverlayPainter(painter);

        // Display the viewer in this Panel
        this.add(mapViewer, BorderLayout.CENTER);
    }

    /* DO NOT TOUCH */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Add Node to the mapViewer
     * 
     * @param node Node The Node we want to add to the mapViewer
     */
    public void addNode(Node node) {
        nodes.add(node);
        waypointPainter.setWaypoints(nodes);
        mapViewer.repaint();
    }
}
