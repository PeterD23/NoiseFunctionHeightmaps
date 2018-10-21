
package Maps;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JProgressBar;

import Generation.Constants;
import Generation.OpenSimplexNoise;

public class ReliefMap implements Constants {

	private int square;
	private double feature_size;

	private double[][] radial;

	private float total, frequency, amplitude;
	private int octaves = 16;
	private float gain = 0.65f;
	private float lacunarity = 2.1042f;

	private JProgressBar bar;
	
	private final int OCEAN_MIN = 60; // Minimum colour level for ocean

	// TODO: Add biomes

	public ReliefMap(int square, double feature_size) {
		this.square = square;
		this.feature_size = feature_size;
	}

	public ReliefMap(int square, double feature_size, int octaves) {
		this.square = square;
		this.feature_size = square * feature_size;
		this.octaves = octaves;
	}

	public ReliefMap initBar(JProgressBar bar) {
		this.bar = bar;
		return this;
	}
	
	public double[][] generate(long seed, String radialStr, boolean land)
			throws IOException {
		OpenSimplexNoise noise = new OpenSimplexNoise(seed);
		double[][] map = new double[square][square];
		initRadial(radialStr);
		
		if(bar == null) {
			bar = new JProgressBar(); // Empty invisible bar
		}
		
		bar.setMaximum(square);
		
		for (int y = 0; y < square; y++) {
			bar.setValue(y);
			
			for (int x = 0; x < square; x++) {

				process(noise, x, y);

				double value = total;
				if (value > 1)
					value = 1;
				else if (value < -1)
					value = -1;

				// System.out.println(value);

				map[x][y] = value * (land ? getRadialFactor(x, y) : 1);

			}
		}
		return map;
	}

	public void process(OpenSimplexNoise noise, int x, int y) {
		total = 0.0f;
		frequency = 1.0f / (float) feature_size;
		amplitude = gain;

		for (int i = 0; i < octaves; ++i) {
			total += noise.eval(x * frequency, y * frequency) * amplitude;
			frequency *= lacunarity;
			amplitude *= gain;
		}
	}
	// System.out.println(total);

	public void initRadial(String radialStr) {
		try {
			BufferedImage radialImg = ImageIO.read(new File("tex\\" + radialStr + ".png"));
			radialImg = resize(radialImg, square, square);
			radial = new double[square][square];
			for (int x = 0; x < square; x++) {
				for (int y = 0; y < square; y++) {
					double rgb = (double) radialImg.getRGB(x, y);
					radial[x][y] = normaliseRGB(rgb, -16777216, -1, 0, 1);
				}
			}
		} catch (IOException e) {
			radial = null;
		}
	}

	public double normaliseRGB(final double valueIn, final double baseMin, final double baseMax, final double limitMin,
			final double limitMax) {
		return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	public double getRadialFactor(int x, int y) {
		if (radial != null)
			return radial[x][y];
		else
			return 1;
	}

}
