package Model;

import Plugins.jxmap.swingx.JXMapViewer;
import Plugins.jxmap.swingx.mapviewer.GeoPosition;
import Plugins.jxmap.swingx.mapviewer.WaypointRenderer;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * A fancy waypoint painter
 */
public class NodeRenderer implements WaypointRenderer<Node> {
    //private static final Log log = LogFactory.getLog(FancyWaypointRenderer.class);

    private final Map<Color, BufferedImage> map = new HashMap<Color, BufferedImage>();
    //private final Font font = new Font("Lucida Sans", Font.BOLD, 10);
    private BufferedImage origImage;

    /**
     * Uses a default waypoint image
     */
    public NodeRenderer() {
        URL resource = getClass().getResource("../View/Images/waypoint_white.png");

        try {
            origImage = ImageIO.read(resource);
        } catch (Exception ex) {
            System.out.println("couldn't read waypoint_white.png");
        }
    }

    /**
     * TODO
     *
     * @param loadImg
     * @param newColor
     * @return
     */
    private BufferedImage convert(BufferedImage loadImg, Color newColor) {
        int w = loadImg.getWidth();
        int h = loadImg.getHeight();
        BufferedImage imgOut = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        BufferedImage imgColor = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = imgColor.createGraphics();
        g.setColor(newColor);
        g.fillRect(0, 0, w + 1, h + 1);
        g.dispose();

        Graphics2D graphics = imgOut.createGraphics();
        graphics.drawImage(loadImg, 0, 0, null);
        graphics.setComposite(MultiplyComposite.Default);
        graphics.drawImage(imgColor, 0, 0, null);
        graphics.dispose();

        return imgOut;
    }

    /**
     * TODO
     *
     * @param g
     * @param viewer
     * @param node
     */
    @Override
    public void paintWaypoint(Graphics2D g, JXMapViewer viewer, Node node) {
        g = (Graphics2D) g.create();

        if (origImage == null) {
            return;
        }

        BufferedImage myImg = map.get(node.getColor());

        if (myImg == null) {
            myImg = convert(origImage, node.getColor());
            map.put(node.getColor(), myImg);
        }

        Point2D point = viewer.getTileFactory().geoToPixel(node.getPosition(), viewer.getZoom());

        int x = (int) point.getX();
        int y = (int) point.getY();

        // Paint radius
        double radius = 0.0002;

        GeoPosition leftTop = new GeoPosition(node.getPosition().getLatitude() + radius, node.getPosition().getLongitude() - radius);
        //GeoPosition rightBottom = new GeoPosition(node.getGeoposition().getLatitude()-radius, node.getGeoposition().getLongitude()+radius);
        
        //Point2D ltPoint = viewer.getTileFactory().geoToPixel(leftTop, viewer.getZoom());
        double diff = 20;

        //g.drawOval((int) ltPoint.getX(), (int) ltPoint.getY(), (int) diff, (int) diff);
        g.setColor(Color.yellow);
        g.fillOval((int) (point.getX()-diff), (int) (point.getY()-diff), (int) diff, (int) diff);

        //Draw node
        g.drawImage(myImg, x - myImg.getWidth() / 2, y - myImg.getHeight(), null);

        String label = node.getLabel();

        //g.setFont(font);
        if (label != null) {
            FontMetrics metrics = g.getFontMetrics();
            int tw = metrics.stringWidth(label);
            int th = 1 + metrics.getAscent();

            //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawString(label, x - tw / 2, y + th - myImg.getHeight());
        }
        g.dispose();
    }
}
