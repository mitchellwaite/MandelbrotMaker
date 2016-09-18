import java.awt.Color;
import java.awt.image.BufferedImage;


public class MandlebrotClass {

	private static int MAX_ITERATIONS = 32;
	private static double ESCAPE_MODULUS = 2.0;

	private MandlebrotClass() {
		// RESx and RESy are the resolution of the fractal plot
	}

	public static BufferedImage getPlot(MandlebrotSettings s) {
		int row, col;
		double xPos, yPos;
		ComplexNumber c, z;
		double modulus = 0;
		boolean escaped = false;
		int iterations = 0;
		int desiredColour;

		int height = s.getHeight();
		int width = s.getWidth();

		double yMax = s.getYmax();
		double xMin = s.getXmin();

		double yScale = s.getYScale();
		double xScale = s.getXScale();

		BufferedImage pretty = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);

		for (row = 0; row < height; row++) {
			// Calculate the actual y position
			yPos = yMax - row * yScale;
			for (col = 0; col < width; col++) {
				// Calculate the actual x position
				xPos = xMin + col * xScale;
				// Create the complex number for this position
				c = new ComplexNumber(xPos, yPos);
				z = new ComplexNumber(0, 0);
				iterations = 0;
				// Iterate the fractal equation z = z*z + c
				// until z either escapes or the maximum number of iterations
				// is reached
				do {
					z = (z.multiply(z)).add(c);
					modulus = z.abs();
					escaped = modulus > ESCAPE_MODULUS;
					iterations++;
				} while (iterations < MAX_ITERATIONS && !escaped);
				// Set the colour according to what stopped the above loop
				if (escaped) {
					desiredColour = setEscapeColour(iterations,s.getLightBack());
				} else {
					desiredColour = setColour(modulus,s.getHueAdjustmentFactor());
				}
				pretty.setRGB(col, row, desiredColour);

			} // end for
		} // end for

		return pretty;
	}

	public static BufferedImage getTest(MandlebrotSettings s) {
		int height = (int) s.getHeight();
		int width = (int) s.getWidth();
		BufferedImage tb = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
			int col = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					tb.setRGB(j, i, new Color(col, col, col).getRGB());
				}
				col = (col + 1) % 255;
			}
		
		return tb;
	}

	// Sets colour level for interior situation
	// The algorithm used here is *totally* empirical!
	private static int setColour(double modulus, int hueAdjustment) {
		//Get RGB color
		float factor = (float) (modulus / ESCAPE_MODULUS);
		float incr = (float) Math.log10(factor * 3.5);
		float b = 1 - Math.min(Math.abs(0.5F * factor + incr), 1.0F);
		float r = 0;
		float g = 1 - Math.min(Math.abs(6.0F * incr) * factor, 1.0F);
		Color c = new Color(r, g, b);
		
		if (hueAdjustment != 0){
		float[] hsbvals = new float[3];
		Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbvals);
		double newHue = (double)hsbvals[0];
		newHue = newHue + (hueAdjustment/100.0);
		if (newHue > 1)
		{
			newHue = newHue - 1;
		}
		else if (newHue < 0)
		{
			newHue = 1 + newHue;
		}
		hsbvals[0] = (float)newHue;
		c = Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]);
		}
		
		return c.getRGB();
	} // end setColour

	// Sets gray level for escape situation
	private static int setEscapeColour(int numIterations, boolean lightBack) {
		float grayLevel = (float) numIterations / MAX_ITERATIONS;
		if(!lightBack){
			grayLevel = Math.max(grayLevel, 0.1F);
		}
		else
		{
			grayLevel = 1 - Math.max(grayLevel, 0.1F);
		}
		return new Color(grayLevel, grayLevel, grayLevel).getRGB();
	} // end setEscapeColour

}
