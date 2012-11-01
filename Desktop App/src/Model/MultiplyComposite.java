package Model;

import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class MultiplyComposite implements Composite {

    /**
     * The default implementation
     */
    public static final MultiplyComposite DEFAULT = new MultiplyComposite();

    /**
     * Constructor
     *
     * @note This class is ment to be Singleton
     */
    private MultiplyComposite() {
    }

    /**
     * Override CompositeContext()
     *
     * Create a Node with a given color
     *
     * @param srcColorModel ColorModel
     * @param dstColorModel ColorModel
     * @param hints RenderingHints
     *
     * @return CompositeContext interface
     */
    @Override
    public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
        return new CompositeContext() {
            /**
             * Composes the two source Raster objects and places the result in the destination WritableRaster.
             *
             * @note The destination can be the same object as either the first or second source.
             * @note dstIn and dstOut must be compatible with the dstColorModel passed to the createContext method of the Composite
             * interface.
             *
             * @param src Raster
             * @param dstIn Raster
             * @param dstOut WritableRaster
             */
            @Override
            public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
                if (src.getSampleModel().getDataType() != DataBuffer.TYPE_INT
                        || dstIn.getSampleModel().getDataType() != DataBuffer.TYPE_INT
                        || dstOut.getSampleModel().getDataType() != DataBuffer.TYPE_INT) {
                    throw new IllegalStateException("Source and destination must store pixels as an int.");
                }

                int width = Math.min(src.getWidth(), dstIn.getWidth());
                int height = Math.min(src.getHeight(), dstIn.getHeight());

                int[] srcPixel = new int[4];
                int[] dstPixel = new int[4];
                int[] srcPixels = new int[width];
                int[] dstPixels = new int[width];

                for (int y = 0; y < height; y++) {
                    src.getDataElements(0, y, width, 1, srcPixels);
                    dstIn.getDataElements(0, y, width, 1, dstPixels);

                    for (int x = 0; x < width; x++) {
                        // pixels are stored as INT_ARGB
                        // our arrays are [R, G, B, A]
                        int pixel = srcPixels[x];
                        srcPixel[0] = (pixel >> 16) & 0xFF;
                        srcPixel[1] = (pixel >> 8) & 0xFF;
                        srcPixel[2] = (pixel >> 0) & 0xFF;
                        srcPixel[3] = (pixel >> 24) & 0xFF;

                        pixel = dstPixels[x];
                        dstPixel[0] = (pixel >> 16) & 0xFF;
                        dstPixel[1] = (pixel >> 8) & 0xFF;
                        dstPixel[2] = (pixel >> 0) & 0xFF;
                        dstPixel[3] = (pixel >> 24) & 0xFF;

                        int[] result = new int[]{
                            (srcPixel[0] * dstPixel[0]) >> 8,
                            (srcPixel[1] * dstPixel[1]) >> 8,
                            (srcPixel[2] * dstPixel[2]) >> 8,
                            (srcPixel[3] * dstPixel[3]) >> 8
                        };

                        // mixes the result with the opacity
                        dstPixels[x] =
                                (result[3]) << 24
                                | (result[0]) << 16
                                | (result[1]) << 8
                                | (result[2]);
                    }
                    dstOut.setDataElements(0, y, width, 1, dstPixels);
                }
            }

            /**
             * No need to implement this!
             */
            @Override
            public void dispose() {
            }
        };
    }
}
