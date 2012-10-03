package View;

import Model.ContextMenuMap;
import Model.Link;
import Model.LinkPainter;
import Model.MediaItem;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

/**
 * Panel containing the Map
 */
public class Map extends JPanel {

    // Variables
    private static final Logger LOGGER = Logger.getLogger(Map.class.getName());
    public JXMapViewer mapViewer;
    private WaypointPainter<Node> waypointPainter;
    private LinkPainter linkPainter;
    private List<Painter<JXMapViewer>> painters;
    private CompoundPainter<JXMapViewer> painter;
    private Set<Node> nodes;
    private Story story;
    private Main parent;
    private boolean buttonNodeClicked;
    private boolean buttonLinkClicked;
    private boolean buttonStartClicked;
    private boolean linkOnMouse;

    /**
     * Creates new form Map
     */
    public Map(Story story, Main parent) {
        initComponents();

        // Create a story
        this.story = story;

        // Remember the parent
        this.parent = parent;

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

        // Create set to remember the geo-positions
        nodes = new HashSet<Node>();

        // Create a waypoint painter that paints all the waypoints
        waypointPainter = new WaypointPainter<Node>();
        waypointPainter.setWaypoints(nodes);
        waypointPainter.setRenderer(new NodeRenderer());

        // Create a link painter that paints all the links
        linkPainter = new LinkPainter();

        // Add listeners to the map
        MouseInputListener mia = new MapListeners(mapViewer, this, linkPainter, this.parent);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        // Create a compound painter that uses both the route-painter, the waypoint-painter and the link-painter
        painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(linkPainter);
        painters.add(waypointPainter);
        painter = new CompoundPainter<JXMapViewer>(painters);

        // Set overlay
        mapViewer.setOverlayPainter(painter);

        // Add map to this JPanel
        this.add(mapViewer, BorderLayout.CENTER);
        mapViewer.repaint();
    }

    /* Clear this map and all nodes and links that are painted on it and init it with a new story.
     * 
     */
    public void Clear(Story newStory) {
        nodes.clear();
        waypointPainter.setWaypoints(nodes);
        linkPainter.clearLinks();
        story = newStory;
        mapViewer.repaint();
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

        // Set changeboolean to true
        story.setSomethingChanged(true);
    }

    /**
     * Add Link to the mapViewer
     *
     * @param link Link The Link we want to add to the mapViewer
     */
    public void addLink(Link link) {
        linkPainter.addLink(link);
        mapViewer.repaint();

        // Set changeboolean to true
        story.setSomethingChanged(true);
    }

    /**
     * Delete Node from the mapViewer
     *
     * @param node Node The Node we want to delete from the mapViewer
     */
    public void deleteNode(Node node) {
        nodes.remove(node);
        waypointPainter.setWaypoints(nodes);
        mapViewer.repaint();

        // Set changeboolean to true
        story.setSomethingChanged(true);
    }

    /**
     * Get Node at a certain Point2D
     *
     * @param point Point2D
     * @return Node
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

    /**
     * Get Story
     *
     * @return Story
     */
    public Story getStory() {
        return story;
    }

    /**
     * Get buttonNodeClicked
     *
     * @return boolean
     */
    public boolean isButtonNodeClicked() {
        return buttonNodeClicked;
    }

    /**
     * Get buttonNodeClicked
     *
     * @return boolean
     */
    public void setButtonNodeClicked(boolean buttonNodeClicked) {
        this.buttonNodeClicked = buttonNodeClicked;
        if (buttonNodeClicked) {
            /*Toolkit tk = Toolkit.getDefaultToolkit();
             try {
             mapViewer.setCursor(tk.createCustomCursor(ImageIO.read(this.getClass().getResourceAsStream("../View/Images/waypoint_white.png")), new Point(0, 0), "MyCursor"));
                
             } catch (IOException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }*/
        }
    }

    /**
     * Get buttonLinkClicked
     *
     * @return boolean
     */
    public boolean isButtonLinkClicked() {
        return buttonLinkClicked;
    }

    /**
     * Get buttonLinkClicked
     *
     * @return boolean
     */
    public void setButtonLinkClicked(boolean buttonLinkClicked) {
        this.buttonLinkClicked = buttonLinkClicked;
    }

    /**
     * Get buttonStartClicked
     *
     * @return boolean
     */
    public boolean isButtonStartClicked() {
        return buttonStartClicked;
    }

    /**
     * Set buttonStartClicked
     *
     * @param buttonStartClicked boolean
     */
    public void setButtonStartClicked(boolean buttonStartClicked) {
        this.buttonStartClicked = buttonStartClicked;
    }

    /**
     * Get isLinkOnMouse
     *
     * @return boolean
     */
    public boolean isLinkOnMouse() {
        return linkOnMouse;
    }

    /**
     * Set linkOnMouse
     *
     * @param linkOnMouse boolean
     */
    public void setLinkOnMouse(boolean linkOnMouse) {
        this.linkOnMouse = linkOnMouse;
    }

    public String printNodeXML() {
        String xmlString = "";
        xmlString += "<!-- These are extra nodes (which are not linked)  -->\r\n";
        xmlString += "<extranodes>\r\n";
        for (Node node : nodes) {
            //check if the node doesn't have any routes
            if (story.getRoutesFromNode(node).isEmpty()) {
                xmlString += "<node>\r\n";
                xmlString += node.printXML(true);
                xmlString += "</node>\r\n";
            }
        }
        xmlString += "</extranodes>\r\n";
        return xmlString;
    }
}

/**
 * Class containing all listeners for the Map.
 */
class MapListeners extends MouseInputAdapter {

    // Variables
    private static final Logger LOGGER = Logger.getLogger(Map.class.getName());
    private Point prev;
    private JXMapViewer mapViewer;
    private Map map;
    private LinkPainter linkPainter;
    private Node draggingNode;
    private Main parent;

    /**
     * Constructor
     *
     * @param mapViewer JXMapViewer The map
     */
    public MapListeners(JXMapViewer mapViewer, Map map, LinkPainter linkPainter, Main parent) {
        this.mapViewer = mapViewer;
        this.map = map;
        this.linkPainter = linkPainter;
        this.parent = parent;
    }

    /**
     * Mouse clicked event
     *
     * @param evt MouseEvent
     */
    @Override
    public void mouseClicked(final MouseEvent evt) {

        // Get mouse clicked coordinates
        Point2D coord = new Point2D.Double(evt.getX(), evt.getY());

        // Get clicked link
        Link clickedLink = linkPainter.intersects(mapViewer);

        // Place Node
        if (map.isButtonNodeClicked()) {

            // Get GEO Position where we want to place the node
            GeoPosition geopos = mapViewer.convertPointToGeoPosition(coord);

            // Add Node to Map
            map.addNode(new Node(geopos));

            // Repaint Map
            mapViewer.repaint();
            map.setButtonNodeClicked(false);
            mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        } // Place Link
        else if (map.isButtonLinkClicked()) {

            // Get clicked Node
            Node clickedNode = map.getNodeAtCoord(coord);

            // Clicked Node exists
            if (clickedNode != null) {

                // Check if node already has a Link. If not, we can not
                // make the Link, because it is not linked to a existing Route
                if (map.getStory().getLinkForEndNode(clickedNode) != null) {
                    map.setLinkOnMouse(true);
                    map.setButtonLinkClicked(false);

                    // Add link to the first node
                    Link link = new Link(null, clickedNode, null, map.getStory().getLinkCounter());
                    map.addLink(link);

                } else {
                    JOptionPane.showMessageDialog(null, "There is no route leading to this node.");
                }
            }

        } // Link is stuck on mouse
        else if (map.isLinkOnMouse()) {

            // Get clicked Node
            Node clickedNode = map.getNodeAtCoord(coord);

            // Clicked Node exists
            if (clickedNode != null) {

                // Add second Node to Link
                Link link = linkPainter.getLastLink();

                // Can not make a Link to itself
                if (!clickedNode.equals(link.getP1())) {

                    // Create Link
                    link.setP2(clickedNode);
                    this.mapViewer.repaint();

                    // Get previous links for this Node (p1)
                    ArrayList<Link> prevLinks = this.map.getStory().getPreviousLinksForStartNode(link.getP1());

                    // Delete possible previous links that are already chosen
                    ArrayList<Link> prevLinksForP2 = this.map.getStory().getPreviousLinksForStartNode(link.getP2());
                    boolean found = false;
                    for (Link prevP2 : prevLinksForP2) {
                        found = false;
                        for (Link prevP1 : prevLinks) {
                            for (Link l : prevP1.getLinks()) {
                                if (l.equals(prevP2)) {
                                    found = true;
                                    prevLinks.remove(prevP1);
                                    break;
                                }
                            }
                            if (found) {
                                break;
                            }
                        }
                    }

                    // If there are previous links to choose from
                    if (prevLinks.size() > 0) {

                        // Open window to add media to this Link
                        // 0 indicates that we are creating a Link and not a StartNode (Link)
                        openAddMediaDialog(clickedNode, prevLinks, 0, link);

                    } else {
                        // There are no previous links to choose from
                        JOptionPane.showMessageDialog(null, "This link already exists.");
                        linkPainter.removeLastLink();
                    }
                } else {
                    // Can NOT make Link to itself
                    JOptionPane.showMessageDialog(null, "Can not make Link to itself");
                    linkPainter.removeLastLink();
                }
            } else {
                // User did NOT click on a Node
                linkPainter.removeLastLink();
            }

            // Repaint
            map.setLinkOnMouse(false);
            mapViewer.repaint();

        } // Place startpoint
        else if (map.isButtonStartClicked()) {

            // Get clicked Node
            Node clickedNode = map.getNodeAtCoord(coord);

            // Clicked Node exists
            if (clickedNode != null) {

                // Open window to add media to this Link
                openAddMediaDialog(clickedNode, null, 1, null);
            }

            // Repaint
            map.setButtonStartClicked(false);
            mapViewer.repaint();

        } // No buttons are clicked, but we still clicked on the map
        else if (map.getNodeAtCoord(evt.getPoint()) != null) {

            // Create node menu
            ContextMenuMap contextMenuMap = new ContextMenuMap();
            final Node currentNode = map.getNodeAtCoord(evt.getPoint());

            // Check if the node got any links attached. If so, the
            // item is disabled and the delete function will be added.
            if (map.getStory().getLinkForEndNode(currentNode) != null) {
                contextMenuMap.getDeleteItem().setEnabled(false);

                // Listener for CreateLinkItem
                contextMenuMap.getCreateLinkItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        map.setLinkOnMouse(true);
                        Link link = new Link(null, currentNode, null, map.getStory().getLinkCounter());
                        map.addLink(link);
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
            if (!currentNode.getStart()) {
                contextMenuMap.getChangeLinkItem().setEnabled(false);
                contextMenuMap.getSetStartItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        openAddMediaDialog(currentNode, null, 1, null);
                        mapViewer.repaint();
                    }
                });
            } else {
                contextMenuMap.getSetStartItem().setEnabled(false);
                contextMenuMap.getChangeLinkItem().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Link clickedLink = map.getStory().getStartLinkForNode(currentNode);
                        openMediaDialogByLinkClick(clickedLink);
                    }
                });
            }
            contextMenuMap.showContextMenuMap(evt);

        } // Open media dialog if user clicks on a Link or a startNode
        else if (clickedLink != null) {
            openMediaDialogByLinkClick(clickedLink);
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

        // Left mouse dragged event
        if (SwingUtilities.isLeftMouseButton(evt)) {

            // Get mouse clicked coords
            Point current = evt.getPoint();

            // Drag a Node from a location to another location
            Point2D coord = new Point2D.Double(current.x, current.y);
            if (draggingNode == null) {
                draggingNode = map.getNodeAtCoord(coord);
            }

            // If a Node was clicked and dragged
            // Check if no button from the left menu was clicked and still active
            if (draggingNode != null && !map.isButtonLinkClicked() && !map.isButtonNodeClicked() && !map.isButtonStartClicked() && !map.isLinkOnMouse()) {
                // Get mouse dragged coordinates
                Point2D draggedCoord = new Point2D.Double(evt.getX(), evt.getY());

                // Get GEO Position where we want to place the node
                GeoPosition geopos = mapViewer.convertPointToGeoPosition(draggedCoord);

                // Set the new coords and repaint
                draggingNode.setGeoposition(geopos);
                prev = current;

                // Set changeboolean to true
                map.getStory().setSomethingChanged(true);

                mapViewer.repaint();
            }
            if (map.isButtonNodeClicked() || map.isLinkOnMouse()) {
                mouseClicked(evt);
            }

        } // Right mouse dragged event -> drag map
        else if (SwingUtilities.isRightMouseButton(evt)) {

            // Get coords
            Point current = evt.getPoint();
            double x = mapViewer.getCenter().getX() - (current.x - prev.x);
            double y = mapViewer.getCenter().getY() - (current.y - prev.y);

            if (!mapViewer.isNegativeYAllowed()) {
                if (y < 0) {
                    y = 0;
                }
            }

            // Get max height
            int maxHeight = (int) (mapViewer.getTileFactory().getMapSize(mapViewer.getZoom()).getHeight()
                    * mapViewer.getTileFactory().getTileSize(mapViewer.getZoom()));
            if (y > maxHeight) {
                y = maxHeight;
            }

            // Repaint map
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
        Point2D coord = new Point2D.Double(e.getX(), e.getY());
        linkPainter.setMousePos(coord);
        Node currentNode = map.getNodeAtCoord(e.getPoint());
        if (currentNode != null) {
            //currentNode.setColor(Color.getHSBColor(0.5f, 0.2f, 1f));
            mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        if (map.isLinkOnMouse()) {
            mapViewer.repaint();
        } else {
            linkPainter.intersects(mapViewer);
            mapViewer.repaint();
        }
    }

    /**
     * TODO
     *
     * @param link
     */
    private void openMediaDialogByLinkClick(Link link) {

        // Open AddMedia
        final AddMedia popup = new AddMedia(parent, map, link);
        popup.setVisible(true);

        // Add window listener to the popup dialog window, so we
        // can get the added MediaItems in the right order
        popup.addWindowListener(new WindowAdapter() {
            /**
             * Window closed event
             */
            @Override
            public void windowClosed(WindowEvent we) {

                // Get all added media items in the right order!
                ArrayList<MediaItem> items = popup.getAddedItems();

                // User saved link properties
                if (popup.isClosedBySave()) {

                    // If user did not add any media to the link,
                    if (items.isEmpty()) {

                        // then show popup.
                        JOptionPane.showMessageDialog(popup,
                                "You didn't add any media to this Link." + "\n"
                                + "You can still add media to this Link by clicking on this Link!", // message
                                "Info", // title
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * Open AddMedia Dialog
     *
     * @param node
     * @param prevLinks
     * @param startOrLink
     */
    private void openAddMediaDialog(Node node, ArrayList<Link> prevLinks, final int startOrLink, Link currentLink) {
        if (startOrLink != 1 || !node.getStart()) {
            // Create startLink
            if (currentLink == null) {
                currentLink = new Link(null, null, node, map.getStory().getLinkCounter());
            }
            // Show popup window for adding media to the startnode (Link)
            final AddMedia popup = new AddMedia(parent, map, prevLinks, currentLink, startOrLink);
            popup.setVisible(true);

            // Add window listener to the popup dialog window, so we
            // can get the added MediaItems in the right order
            popup.addWindowListener(new WindowAdapter() {
                /**
                 * Window closed event
                 */
                @Override
                public void windowClosed(WindowEvent we) {

                    // Get all added media items in the right order!
                    ArrayList<MediaItem> items = popup.getAddedItems();

                    // User saved link properties
                    if (popup.isClosedBySave()) {

                        // If user did not add any media to the link,
                        if (items.isEmpty()) {

                            // then show popup.
                            JOptionPane.showMessageDialog(popup,
                                    "You didn't add any media to this Link." + "\n"
                                    + "You can still add media to this Link by clicking on this Link!", // message
                                    "Info", // title
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        // User closed window and did NOT save anything.
                        if (startOrLink == 0) {
                            linkPainter.removeLastLink();
                        }
                    }
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "This node already has a start.");
        }
    }
}
