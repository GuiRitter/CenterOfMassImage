package io.github.guiritter.center_of_mass.image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.math.BigDecimal;

public class CenterOfMassImageComputer {

	public static Point2D<BigDecimal> compute(BufferedImage image) {
		if (image == null) {
			return null;
		}
		WritableRaster raster = image.getRaster();
		int x;
		int y;
		int width = raster.getWidth();
		int height = raster.getHeight();
		int color[] = new int[raster.getNumBands()];
		/**
		 * Sum of the X of the particles.
		 */
		long rX = 0;
		/**
		 * Sum of the Y of the particles.
		 */
		long rY = 0;
		/**
		 * Sum of the particle amount.
		 */
		long M = 0;
		for (y = 0; y < height; y++) {
			for (x = 0; x < width; x++) {
				raster.getPixel(x, y, color);
				if (color[0] > 127) {
					rX += x;
					rY += y;
					M++;
				}
			}
		}
		return new Point2D<BigDecimal>(
			new BigDecimal(((double) rX) / ((double) M)),
			new BigDecimal(((double) rY) / ((double) M)));
	}
}
