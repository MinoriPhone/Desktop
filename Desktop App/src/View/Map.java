package View;

import Model.ContextMenuMap;
import Model.Link;
import Model.LinkPainter;
import Model.Node;
import Model.NodeRenderer;
import Model.Story;
import Plugins.jxmap.swingx.JXMapViewer;
import Plugins.jxmap.swingx.OSMTileFactoryInfo;
import Plugins.jxmap.swingx.input.CenterMapListener;
import Plugins.jxmap.swingx.input.PanKeyListener;
import Plugins.jxmap.swingx.input.ZoomMouseWheelListenerCenter;
import Plugins.jxmap.swingx.mapviewer.DefaultTileFactory;
import Plugins.jxmap.swingx.mapviewer.GeoPosition;
import Plugins.jxmap.swingx.mapviewer.TileFactoryInfo;
import Plugins.jxmap.swingx.mapviewer.WaypointPainter;
import Plugins.jxmap.swingx.painter.CompoundPainter;
import Plugins.jxmap.swingx.painter.Painter;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private WaypointPainter<Node> waypointPainter;
    private LinkPainter linkPainter;
    private List<Painter<JXMapViewer>> painters;
    private CompoundPainter<JXMapViewer> painter;
    private Set<Node> nodes;
    private Story story;
    private boolean buttonNodeClicked;
    private boolean buttonLinkClicked;
    private boolean buttonStartClicked;
    private boolean linkOnMouse;

    /**
     * Creates new form Map
     */
    public Map(Story story) {
        initComponents();

        // Create a story
        this.story = story;

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

        // Create a waypoint painter that paints all the waypoints
        waypointPainter = new WaypointPainter<Node>();
        waypointPainter.setWaypoints(nodes);
        waypointPainter.setRenderer(new NodeRenderer());

        linkPainter = new LinkPainter();

        // Add listeners to the map
        MouseInputListener mia = new MapListeners(mapViewer, this, linkPainter);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(linkPainter);
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

    /**
     * Delete Node from the mapViewer
     *
     * @param node Node The Node we want to delete from the mapViewer
     */
    public void deleteNode(Node node) {

        // Checks if the node has any links combined
        nodes.remove(node);

        waypointPainter.setWaypoints(nodes);
        mapViewer.repaint();
    }

    /**
     * Add Node to the mapViewer
     *
     * @param node Node The Node we want to add to the mapViewer
     */
    public Node getNodeAtCoord(Point2D point) {
        for (Node node : nodes) {
            if ((point.getX() - mapViewer.convertGeoPositionToPoint(node.getGeoposition()).getX()) < 10
                    && (point.getX() - mapViewer.convertGeoPositionToPoint(node.getGeoposition()).getX()) > -10
                    && (point.getY() - mapViewer.convertGeoPositionToPoint(node.getGeoposition()).getY()) < 0
                    && (point.getY() - mapViewer.convertGeoPositionToPoint(node.getGeoposition()).getY()) > -33) {
                return node;
            }
        }
        return null;
    }

    public Story getStory() {
        return story;
    }

    /**
     * Getter buttonNodeClicked
     *
     * @return buttonNodeClicked
     */
    public boolean isButtonNodeClicked() {
        return buttonNodeClicked;
    }

    /**
     * Getter buttonNodeClicked
     *
     * @return boolean buttonNodeClicked
     */
    public void setButtonNodeClicked(boolean buttonNodeClicked) {
        this.buttonNodeClicked = buttonNodeClicked;
        if (buttonNodeClicked) {
            Toolkit tk = Toolkit.getDefaultToolkit();
            /*try {
             mapViewer.setCursor(tk.createCustomCursor(ImageIO.read(this.getClass().getResourceAsStream("../View/Images/waypoint_white.png")), new Point(0, 0), "MyCursor"));
                
             } catch (IOException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }*/
        }
    }

    /**
     * Getter buttonLinkClicked
     *
     * @return buttonLinkClicked
     */
    public boolean isButtonLinkClicked() {
        return buttonLinkClicked;
    }

    /**
     * Getter buttonLinkClicked
     *
     * @return boolean buttonLinkClicked
     */
    public void setButtonLinkClicked(boolean buttonLinkClicked) {
        this.buttonLinkClicked = buttonLinkClicked;
    }

    public boolean isButtonStartClicked() {
        return buttonStartClicked;
    }

    public void setButtonStartClicked(boolean buttonStartClicked) {
        this.buttonStartClicked = buttonStartClicked;
    }

    public boolean isLinkOnMouse() {
        return linkOnMouse;
    }

    public void setLinkOnMouse(boolean linkOnMouse) {
        this.linkOnMouse = linkOnMouse;
    }
}

/**
 * Class containing all listeners for the Map.
 */
class MapListeners extends MouseInputAdapter {

    // Variables
    private Point prev;
    private JXMapViewer mapViewer;
    private Map map;
    private LinkPainter linkPainter;
    private Node draggingNode;

    /**
     * Constructor
     *
     * @param mapViewer JXMapViewer The map
     */
    public MapListeners(JXMapViewer mapViewer, Map map, LinkPainter linkPainter) {
        this.mapViewer = mapViewer;
        this.map = map;
        this.linkPainter = linkPainter;

    }

    /**
     * Mouse clicked event
     *
     * @param evt MouseEvent
     */
    @Override
    public void mouseClicked(final MouseEvent evt) {
        if (map.isButtonNodeClicked()) {
            // Get mouse clicked coordinates
            Point2D coord = new Point2D.Double(evt.getX(), evt.getY());

            // Get GEO Position where we want to place the node
            GeoPosition geopos = mapViewer.convertPointToGeoPosition(coord);

            // Add Node to Map
            map.addNode(new Node(geopos));

            // Repaint Map
            mapViewer.repaint();
            map.setButtonNodeClicked(false);
            mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        } else if (map.isButtonLinkClicked()) {
            // Get mouse clicked coordinates
            Point2D coord = new Point2D.Double(evt.getX(), evt.getY());
            Node clickedNode = map.getNodeAtCoord(coord);
            if (clickedNode != null) {
                if (map.getStory().getLinkForEndNode(clickedNode) != null) {
                    map.setLinkOnMouse(true);
                    map.setButtonLinkClicked(false);
                    //Add link to first node
                    Link link = new Link("A1", clickedNode, null);
                    linkPainter.addLink(link);
                } else {
                    System.out.println("No link on this Node");
                }
            }
        } else if (map.isLinkOnMouse()) {
            // Get mouse clicked coordinates
            Point2D coord = new Point2D.Double(evt.getX(), evt.getY());
            Node clickedNode = map.getNodeAtCoord(coord);
            if (clickedNode != null) {
                //Add second Node to Link
                Link link = linkPainter.getLastLink();
                if (!clickedNode.equals(link.getP1())) {
                    link.setP2(clickedNode);
                    map.getStory().getLinkForEndNode(link.getP1()).addLink(link);
                } else {
                    linkPainter.removeLastLink();
                }
            } else {
                linkPainter.removeLastLink();
            }
            map.setLinkOnMouse(false);
            mapViewer.repaint();
        } else if (map.isButtonStartClicked()) {
            // Get mouse clicked coordinates
            Point2D coord = new Point2D.Double(evt.getX(), evt.getY());
            Node clickedNode = map.getNodeAtCoord(coord);
            if (clickedNode != null) {
                // Create new route
                map.getStory().newRoute(clickedNode);
            }
            map.setButtonStartClicked(false);
            mapViewer.repaint();
        } else if (map.getNodeAtCoord(evt.getPoint()) != null) {
            ContextMenuMap contextMenuMap = new ContextMenuMap();
            final Node currentNode = map.getNodeAtCoord(evt.getPoint());

            // Check if the node got any links attached. If so, the item is disabled and no listener is created
            if (map.getStory().getLinkForEndNode(currentNode) != null) {
                contextMenuMap.getDeleteItem().setEnabled(false);

                // Listener for CreateLinkItem
                contextMenuMap.getCreateLinkItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        map.setLinkOnMouse(true);
                        //Add link to first node
                        Link link = new Link("A1", currentNode, null);
                        linkPainter.addLink(link);
                    }
                });
            } else {
                // Listener for DeleteItem
                contextMenuMap.getDeleteItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        map.deleteNode(currentNode);
                    }
                });
                contextMenuMap.getCreateLinkItem().setEnabled(false);
            }

            contextMenuMap.getSetStartItem().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    // ---------> Make code for user prompt window

                    map.getStory().newRoute(currentNode);
                    mapViewer.repaint();
                }
            });
            contextMenuMap.showContextMenuMap(evt);
        }
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
        if (SwingUtilities.isLeftMouseButton(evt)) {

            Point current = evt.getPoint();
            // Drag a Node from a location to another location
            Point2D coord = new Point2D.Double(current.x, current.y);
            if (draggingNode == null) {
                draggingNode = map.getNodeAtCoord(coord);
            }

            // If a Node was clicked and dragged
            // Check if no button from the left menu was clicked and still active
            if (draggingNode != null && !map.isButtonLinkClicked() && !map.isButtonNodeClicked() && !map.isButtonStartClicked()) {
                // Get mouse dragged coordinates
                Point2D draggedCoord = new Point2D.Double(evt.getX(), evt.getY());

                // Get GEO Position where we want to place the node
                GeoPosition geopos = mapViewer.convertPointToGeoPosition(draggedCoord);

                // Set the new coords and repaint
                draggingNode.setGeoposition(geopos);
                prev = current;
                mapViewer.repaint();
            }
            if (map.isButtonNodeClicked()) {
                mouseClicked(evt);
            }

        } else if (SwingUtilities.isRightMouseButton(evt)) {

            Point current = evt.getPoint();
            double x = mapViewer.getCenter().getX() - (current.x - prev.x);
            double y = mapViewer.getCenter().getY() - (current.y - prev.y);

            if (!mapViewer.isNegativeYAllowed()) {
                if (y < 0) {
                    y = 0;
                }
            }

            int maxHeight = (int) (mapViewer.getTileFactory().getMapSize(mapViewer.getZoom()).getHeight() * mapViewer
                    .getTileFactory().getTileSize(mapViewer.getZoom()));
            if (y > maxHeight) {
                y = maxHeight;
            }

            prev = current;
            mapViewer.setCenter(new Point2D.Double(x, y));
            mapViewer.repaint();
            mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    /**
     * Mouse released event
     *
     * @param evt MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent evt) {
        if (SwingUtilities.isLeftMouseButton(evt)) {
            draggingNode = null;
        } else if (SwingUtilities.isRightMouseButton(evt)) {
            mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        prev = null;

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
                mapViewer.requestFocusInWindow();
            }
        });
    }

    /**
     * Mouse moved event
     *
     * @param evt MouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (map.isLinkOnMouse()) {
            linkPainter.setMousePos(new Point2D.Double(e.getX(), e.getY()));
            mapViewer.repaint();
        }
        Node currentNode = map.getNodeAtCoord(e.getPoint());
        if (currentNode != null) {
            //currentNode.setColor(Color.getHSBColor(0.5f, 0.2f, 1f));
            mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}