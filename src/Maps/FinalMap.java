package Maps;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Generation.Biome;
import Generation.Constants;

public class FinalMap implements Constants {

	private double[][] reliefMap, humidMap, tempMap;
	private final int WIDTH, HEIGHT;


	public FinalMap(double[][] reliefMap, double[][] tempMap, double[][] humidMap, int width, int height) {
		this.reliefMap = reliefMap;
		this.tempMap = tempMap;
		this.humidMap = humidMap;
		this.WIDTH = width;
		this.HEIGHT = height;
	}

	public void generate() {
		Biome biome = new Biome(reliefMap, tempMap, humidMap);
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				image.setRGB(x, y, biome.getBiomeType(x, y));
			}
		}
		try {
			ImageIO.write(image, "png", new File(String.format("tex//%d,%d-final.png", 0, 0)));
		} catch (Exception e) {
		}
	}

	public void generateFromImage(String imageFile) {
		Biome biome = new Biome(reliefMap, tempMap, humidMap);
		BufferedImage biomap  = null;
		try
		{
		  biomap = ImageIO.read(new File(imageFile));
		}
		catch (Exception e)
		{
			System.err.println("Failed to read image mapping file " + e);
		}
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				image.setRGB(x, y,biome.getBiomeTypeFromImage(x, y,biomap));
			}
		}
		try {
			ImageIO.write(image, "png", new File(String.format("tex//%d,%d-final.png", 0, 0)));
		} catch (Exception e) {
		}
	}
}
