package UserGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Generation.Biome;
import Generation.Constants;

public class GeneratedImage extends JPanel implements Constants {

	private BufferedImage seaImg = null;
	private BufferedImage landImg = null;

	private final double HEIGHT_MODIFIER = 1.1;// Affects how much temp will
												// drop from climbing heights
	private final int MID_HEAT_INDEX = 300; // Increases how much heat will be
											// at the centre that radiates out
											// to the north and south

	private int mode = 0;
	private long seed = -1;
	private String[] type = { "relief", "temp", "humid", "biome" };

	public GeneratedImage() {
		setSize(600, 600);
		try {
			landImg = ImageIO.read(new File("awesome.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setInfo(int mode, long seed) {
		this.mode = mode;
		this.seed = seed;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(seaImg, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(landImg, 0, 0, getWidth(), getHeight(), null);
	}

	public void generateSea(double relief[][], int width, int height) {
		seaImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				double value = relief[x][y];

				if (value > 0.0)
					value *= -1;
				int rgb = new Color(0, Math.abs(140 - (OCEAN_MIN + (int) Math.abs(value / 2 * 160))),
						Math.abs(210 - (OCEAN_MIN + (int) Math.abs(value * 150)))).getRGB();
				seaImg.setRGB(x, y, rgb);

				repaint();
			}
		}

	}

	public void generateLand(double relief[][], int width, int height) {

		landImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				double value = relief[x][y];

				int rgb = 0;
				if (value <= 0.0) {
					rgb = new Color(0, 0, 0, 0).getRGB();
				} else if (value < 0.6)
					rgb = new Color(0, 10 + (int) (value / 2 * 255), 0, 255).getRGB();
				else
					rgb = new Color((int) ((value / 1.5 * 255)), (int) ((value / 3 * 255)), (int) ((value / 5 * 255)),
							255).getRGB();
				landImg.setRGB(x, y, rgb);

				repaint();
			}
		}

	}

//	public void generateTemp(double relief[][], int width, int height) {
//		double midpoint = height / 2;
//		double ambientTemp = 0;
//
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//
//				double value = 0;
//				if (relief[x][y] > 0)
//					value = ambientTemp - (ambientTemp / HEIGHT_MODIFIER * relief[x][y]);
//				else
//					value = ambientTemp / 2;
//				// System.out.println(value);
//				int color = new Color((int) ((255 * value) / 100), 0, (int) ((255 * (100 - value)) / 100)).getRGB();
//
//				image.setRGB(x, y, color);
//
//				repaint();
//			}
//			if (y < midpoint)
//				ambientTemp = (y / midpoint) * MID_HEAT_INDEX / 2;
//			else
//				ambientTemp = MID_HEAT_INDEX - ((y / midpoint) * MID_HEAT_INDEX / 2);
//
//			if (ambientTemp > 100)
//				ambientTemp = 100; // Cap the ambient to 100%
//
//		}
//	}
//
//	public void generateHumidity(double relief[][], int width, int height) {
//
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//
//				double value = 0;
//				if (relief[x][y] > 0)
//					value = 100 - (relief[x][y] * 100);
//				else
//					value = 100;
//				// System.out.println(value);
//				// (int)((255 * (100 - value)) / 100)
//				// (int)((255 * value) / 100)
//
//				int color = new Color((int) ((255 * (100 - value)) / 100), (int) ((255 * (100 - value)) / 100),
//						(int) ((255 * value) / 100)).getRGB();
//
//				image.setRGB(x, y, color);
//
//				repaint();
//			}
//
//		}
//	}
//
//	public void generateBiomeFromLookup(double relief[][], double temp[][], double humid[][], int width, int height) {
//		Biome biome = new Biome(relief, temp, humid);
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				image.setRGB(x, y, biome.getBiomeType(x, y));
//
//				repaint();
//			}
//		}
//	}
//
//	public void generateBiomeFromImage(double relief[][], double temp[][], double humid[][], int width, int height,
//			BufferedImage bioMap, int xFuzz, int yFuzz) {
//		Biome biome = new Biome(relief, temp, humid);
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				image.setRGB(x, y, biome.getBiomeTypeFromImage(x, y, bioMap, xFuzz, yFuzz));
//
//				repaint();
//			}
//		}
//	}

	public void saveImage() {
		try {
			ImageIO.write(seaImg, "png", new File(String.format("tex//%d-%s.png", seed, "sea")));
			ImageIO.write(landImg, "png", new File(String.format("tex//%d-%s.png", seed, "land")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getSeed() {
		return seed;
	}

}
