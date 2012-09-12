package View;

import Model.Node;
import Plugins.jxmap.swingx.JXMapViewer;
import Plugins.jxmap.swingx.OSMTileFactoryInfo;
import Plugins.jxmap.swingx.input.CenterMapListener;
import Plugins.jxmap.swingx.input.PanKeyListener;
import Plugins.jxmap.swingx.input.ZoomMouseWheelListenerCenter;
import Plugins.jxmap.swingx.mapviewer.DefaultTileFactory;
import Plugins.jxmap.swingx.mapviewer.GeoPosition;
import Plugins.jxmap.swingx.mapviewer.TileFactoryInfo;
import Plugins.jxmap.swingx.mapviewer.Waypoint;
import Plugins.jxmap.swingx.mapviewer.WaypointPainter;
import Plugins.jxmap.swingx.mapviewer.util.GeoUtil;
import Plugins.jxmap.swingx.painter.CompoundPainter;
import Plugins.jxmap.swingx.painter.Painter;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
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

        // Create waypoint from the geo-positions
        nodes = new HashSet<Node>();
        nodes.add(new Node(zHHS));

        // Create a waypoint painter that paints all the waypoints
        waypointPainter = new WaypointPainter<Waypoint>();
        waypointPainter.setWaypoints(nodes);

        // Add listeners to the map
        MouseInputListener mia = new MapListeners(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(waypointPainter);
        painter = new CompoundPainter<JXMapViewer>(painters);

        // Set overlay
        mapViewer.setOverlayPainter(painter);

        // Add map to this JPanel
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

/**
 * Class containing all listeners for the Map.
 */
class MapListeners extends MouseInputAdapter {

    // Variables
    private Point prev;
    private JXMapViewer viewer;

    /**
     * Constructor
     *
     * @param viewer JXMapViewer The map
     */
    public MapListeners(JXMapViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * Mouse clicked event
     * 
     * @param evt MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent evt) {
        
        // Get mouse clicked coordinates
        Point2D coord = new Point2D.Double(evt.getX(), evt.getY());
        
        GeoPosition geopos = GeoUtil.getPosition(coord, viewer.getZoom(), viewer.getTileFactory().getInfo());
        System.out.println(geopos);
        
        // Repaint Map
        viewer.repaint();
    }
    
    /**
     * Mouse pressed event
     *
     * @param evt MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent evt) {
        prev = evt.getPoint();
    }

    /**
     * Mouse dragged event
     *
     * @param evt MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent evt) {
        if (!SwingUtilities.isRightMouseButton(evt)) {
            return;
        }

        Point current = evt.getPoint();
        double x = viewer.getCenter().getX() - (current.x - prev.x);
        double y = viewer.getCenter().getY() - (current.y - prev.y);

        if (!viewer.isNegativeYAllowed()) {
            if (y < 0) {
                y = 0;
            }
        }

        int maxHeight = (int) (viewer.getTileFactory().getMapSize(viewer.getZoom()).getHeight() * viewer
                .getTileFactory().getTileSize(viewer.getZoom()));
        if (y > maxHeight) {
            y = maxHeight;
        }

        prev = current;
        viewer.setCenter(new Point2D.Double(x, y));
        viewer.repaint();
        viewer.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    /**
     * Mouse released event
     *
     * @param evt MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent evt) {
        if (!SwingUtilities.isRightMouseButton(evt)) {
            return;
        }

        prev = null;
        viewer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Mouse entered event
     *
     * @param evt MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                viewer.requestFocusInWindow();
            }
        });
    }
}
