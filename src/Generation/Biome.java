package Generation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Biome implements Constants {

	private double[][] reliefMap;
	private double[][] tempMap;
	private double[][] humidMap;

	public Biome(double[][] reliefMap, double[][] tempMap, double[][] humidMap) {
		this.reliefMap = reliefMap;
		this.tempMap = tempMap;
		this.humidMap = humidMap;
	}

	/**
	 * Returns the biome based on a certain humidity and temp.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getBiomeType(int x, int y) {
		double height = reliefMap[x][y];
		double temp = tempMap[x][y];
		double humidity = humidMap[x][y];

		if (temp < 20 && height > 0) {
			// Try returning higher weighted grayscale values, if you go out of
			// color bounds,
			// just set RGB to 255,255,255 (White)
			if ((SNOW_MIN + height * 255) <= 255)
				return new Color((int) (SNOW_MIN + height * 255), (int) (SNOW_MIN + height * 255),
						(int) (SNOW_MIN + height * 255)).getRGB();
			else
				return new Color(255, 255, 255).getRGB();
		} else if (tempMap[x][y] > 60 && height > 0.2 && height < 0.4) {
			int mod = (int) (height * 150);
			return new Color(240 - mod, 220 - mod, 150 - mod).getRGB();
		}

		// Return heightmap RGB

		if (height < 0.0)
			return oceanBiome(height);
		else if (height < 0.6)
			return new Color(0, 30 + (int) (height / 2 * 255), 0).getRGB();
		else
			return new Color((int) ((height / 1.5 * 255)), (int) ((height / 3 * 255)), (int) ((height / 5 * 255)))
					.getRGB();
	}

	public double normalise(double min, double max, double val) {
		return (val - min) / (max - min);
	}

	public int getBiomeTypeFromImage(int x, int y, BufferedImage img, int xFuzz, int yFuzz) {
		Random random = new Random();
		int randomX = 0, randomY = 0;
		/**
		 * Fuzzes the biome values by a range between -value to +value.
		 */
		if (xFuzz > 0)
			randomX = random.nextInt(xFuzz * 2) - xFuzz;
		if (yFuzz > 0)
			randomY = random.nextInt(yFuzz * 2) - yFuzz;

		double height = reliefMap[x][y];

		// Crunch the values of the temperature and humidity map to ensure they
		// fit within the bounds of the lookup image
		double xTemp = normalise(0, 100, tempMap[x][y]) * (img.getWidth(null) - 1);
		double yHumidity = normalise(0, 100, humidMap[x][y]) * (img.getHeight(null) - 1);

		// Enforce a limit on the fuzzed coordinates so they don't fall out of
		// bounds
		int hum = (int) limit(0, img.getWidth(null) - 1, xTemp + randomX);
		int temp = (int) limit(0, img.getHeight(null) - 1, yHumidity + randomY);

		try {
			if (height > 0) {
				Color biome = new Color(img.getRGB(hum, temp));
				int mod = (int) ((height) * 175);
				
				// Ternaries <3
				return new Color(biome.getRed() - mod > 0 ? biome.getRed() - mod : 0,
						biome.getGreen() - mod > 0 ? biome.getGreen() - mod : 0,
						biome.getBlue() - mod > 0 ? biome.getBlue() - mod : 0).getRGB();
			}

			else
				return oceanBiome(height);
		} catch (Exception e) {
			// System.out.println(hum + "," + temp);
			return 0;
		}

	}

	public double limit(double min, double max, double val) {
		if (val < min)
			return min;
		if (val > max)
			return max;
		return val;
	}

	/*
	 * This creates an ocean biome with darker coloured water for deeper levels
	 * below 0.
	 */
	public int oceanBiome(double height) {
		return new Color(0, Math.abs(140 - (OCEAN_MIN + (int) Math.abs(height / 2 * 160))),
				Math.abs(210 - (OCEAN_MIN + (int) Math.abs(height * 150)))).getRGB();
	}

}
