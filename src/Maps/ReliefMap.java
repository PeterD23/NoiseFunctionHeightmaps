
package Maps;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Generation.Constants;
import Generation.OpenSimplexNoise;

public class ReliefMap implements Constants {

	private int width;
	private int height;
	private double feature_size;

	private double[][] radial;

	private float total, frequency, amplitude;
	private int octaves = 16;
	private float gain = 0.65f;
	private float lacunarity = 2.1042f;

	private final int OCEAN_MIN = 60; // Minimum colour level for ocean

	// TODO: Add biomes

	public ReliefMap(int width, int height, double feature_size) {
		this.width = width;
		this.height = height;
		this.feature_size = feature_size;
	}

	public ReliefMap(int width, int height, double feature_size, int octaves) {
		this.width = width;
		this.height = height;
		this.feature_size = feature_size;
		this.octaves = octaves;
	}

	public double[][] generate(long seed, int heightIndex, int widthIndex, boolean land) throws IOException {
		OpenSimplexNoise noise = new OpenSimplexNoise(seed);
		double[][] map = new double[width][height];
		initRadial();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				process(noise, x + widthIndex, y + heightIndex);

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
			total += noise.eval((float) x * frequency, (float) y * frequency) * amplitude;
			frequency *= lacunarity;
			amplitude *= gain;
		}
	}
	// System.out.println(total);

	public void initRadial() {
		try {
			BufferedImage radialImg = ImageIO.read(new File("tex\\radial.png"));
			radialImg = resize(radialImg, width, height);
			radial = new double[width][height];
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					double rgb = (double) radialImg.getRGB(x, y);
					radial[x][y] = normaliseRGB(rgb, -16777216, -1, 0, 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public double normaliseRGB(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax) {
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
		return radial[x][y];
	}

}
