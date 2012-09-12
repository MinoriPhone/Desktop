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
        return links.get(links.size()-1);
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
        g.setColor(color);
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
            Point2D pt1 = map.getTileFactory().geoToPixel(link.getP1().getGeoposition(), map.getZoom());
            Point2D pt2;
            if(link.getP2() != null)
            {
                pt2 = map.getTileFactory().geoToPixel(link.getP2().getGeoposition(), map.getZoom());
            }else{
                pt2 = map.getTileFactory().geoToPixel(map.convertPointToGeoPosition(mousePos), map.getZoom());
            }
            g.drawLine( (int) pt1.getX(), (int) pt1.getY(), (int) pt2.getX(), (int) pt2.getY());
        }
    }
}
