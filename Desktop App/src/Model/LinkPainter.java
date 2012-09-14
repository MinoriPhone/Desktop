/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Plugins.jxmap.swingx.JXMapViewer;
import Plugins.jxmap.swingx.painter.Painter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Paints a link
 *
 * @author Reinier
 */
public class LinkPainter implements Painter<JXMapViewer> {

    private Color color = Color.RED;
    private boolean antiAlias = true;
    private ArrayList<Link> links;
    private Point2D mousePos;

    /**
     * @param track the track
     */
    public LinkPainter() {
        // copy the list so that changes in the 
        // original list do not have an effect here
        this.links = new ArrayList();
    }

    public void addLink(Link link) {
        this.links.add(link);
    }

    public Link getLastLink() {
        return links.get(links.size() - 1);
    }

    public void removeLastLink() {
        links.remove(links.size() - 1);
    }

    public Point2D getMousePos() {
        return mousePos;
    }

    public void setMousePos(Point2D mousePos) {
        this.mousePos = mousePos;
    }

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

        g.dispose();
    }

    /**
     * @param g the graphics object
     * @param map the map
     */
    private void drawLink(Graphics2D g, JXMapViewer map) {
        for (Link link : links) {
            g.setColor(color);
            Point2D pt1;
            Point2D pt2;
            double length = 10;
            if (link.getP2() == null && link.getP1() != null) {
                pt1 = map.getTileFactory().geoToPixel(link.getP1().getGeoposition(), map.getZoom());
                pt2 = map.getTileFactory().geoToPixel(map.convertPointToGeoPosition(mousePos), map.getZoom());
            } else {
                pt1 = map.getTileFactory().geoToPixel(link.getP1().getGeoposition(), map.getZoom());
                pt2 = map.getTileFactory().geoToPixel(link.getP2().getGeoposition(), map.getZoom());
            }
            g.drawLine((int) pt1.getX(), (int) pt1.getY(), (int) pt2.getX(), (int) pt2.getY());

            Point2D middle;
            if (pt1.getX() == pt2.getX()) {
                middle = new Point2D.Double(pt1.getX(), (pt1.getY() + pt2.getY()) / 2);
            } else if (pt1.getY() == pt2.getY()) {
                middle = new Point2D.Double((pt1.getX() + pt2.getX()) / 2, pt1.getY());
            } else {
                middle = new Point2D.Double((pt1.getX() + pt2.getX()) / 2, (pt1.getY() + pt2.getY()) / 2);
            }

            double difX = (double) pt1.getX() - (double) pt2.getX();
            double difY = (double) pt1.getY() - (double) pt2.getY();

            double angle = Math.atan(difX / difY);

            Point2D arrowLineY;
            Point2D arrowLineX;
            
            double arrowAngle = angle - 95;
            
            double deltaX = length * Math.sin(arrowAngle);
            double deltaY = length * Math.cos(arrowAngle);
            
            if (difY > 0) {
                arrowLineX = new Point2D.Double(middle.getX() + deltaX, middle.getY() + deltaY);
                arrowLineY = new Point2D.Double(middle.getX() + deltaY, middle.getY() - deltaX);
            } else {
                arrowLineX = new Point2D.Double(middle.getX() - deltaY, middle.getY() + deltaX);
                arrowLineY = new Point2D.Double(middle.getX() - deltaX, middle.getY() - deltaY);
            }
            
            g.drawLine((int) middle.getX(), (int) middle.getY(), (int) arrowLineY.getX(), (int) arrowLineY.getY());
            g.drawLine((int) middle.getX(), (int) middle.getY(), (int) arrowLineX.getX(), (int) arrowLineX.getY());
        }
    }
}
