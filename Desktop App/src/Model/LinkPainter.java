package Model;

import Plugins.jxmap.swingx.JXMapViewer;
import Plugins.jxmap.swingx.painter.Painter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LinkPainter implements Painter<JXMapViewer> {

    // Variables
    private Color color = Color.RED;
    private Color hoverColor = Color.GREEN;
    private boolean antiAlias = true;
    private ArrayList<Link> links;
    private Point2D mousePos;

    /**
     * Constructor
     */
    public LinkPainter() {
        // copy the list so that changes in the 
        // original list do not have an effect here
        this.links = new ArrayList<Link>();
    }

    /**
     * Add a Link to this painter
     *
     * @param link Link
     */
    public void addLink(Link link) {
        this.links.add(link);
    }

    /**
     * Clear all links this painter knows
     */
    public void clearLinks() {
        this.links.clear();
    }

    /**
     * Get the last Link this painter knows
     *
     * @return Link
     */
    public Link getLastLink() {
        return links.get(links.size() - 1);
    }

    /**
     * Remove last Link the painter knows
     */
    public void removeLastLink() {
        links.remove(links.size() - 1);
    }

    /**
     * Return the Point2D mouse position
     *
     * @return Point2D
     */
    public Point2D getMousePos() {
        return mousePos;
    }

    /**
     * Set the Point2D mouse position
     *
     * @param mousePos Point2D
     */
    public void setMousePos(Point2D mousePos) {
        this.mousePos = mousePos;
    }

    /**
     * Override the painter. Paints all known Links
     *
     * @param g Graphics2D
     * @param map JXMapViewer
     * @param w int
     * @param h int
     */
    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
        g = (Graphics2D) g.create();

        // convert from viewport to world bitmap
        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);

        if (antiAlias) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        // do the drawing
        g.setStroke(new BasicStroke(4));
        drawLink(g, map);

        // Dispose the Graphics2D
        g.dispose();
    }

    /**
     * Paint a Link
     *
     * @param g Graphics2D
     * @param map JXMapViewer
     */
    private void drawLink(Graphics2D g, JXMapViewer map) {
        for (Link link : links) {
            g.setColor(link.getColor());
            if (mousePos != null) {
                for (Line2D line : link.getLines(map, mousePos)) {
                    g.draw(line);
                }
            }
        }
    }

    /**
     * Get all Links that intersect with the current mouse position
     *
     * @param mapViewer JXMapViewer
     *
     * @return ArrayList<Link>
     */
    public ArrayList<Link> intersects(JXMapViewer mapViewer) {
        ArrayList<Link> clickedLinkArray = new ArrayList<Link>();
        for (Link link : links) {
            if (mousePos != null) {
                for (Line2D line : link.getLines(mapViewer, mousePos)) {
                    if (line.ptSegDist(mapViewer.getTileFactory().geoToPixel(mapViewer.convertPointToGeoPosition(mousePos), mapViewer.getZoom())) < 3) {
                        link.setColor(this.hoverColor);
                        mapViewer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        clickedLinkArray.add(link);
                        break;
                    } else {
                        link.setColor(this.color);
                    }
                }
            }
        }
        return clickedLinkArray;
    }
}
