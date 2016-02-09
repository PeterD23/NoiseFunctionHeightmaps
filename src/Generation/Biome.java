package Generation;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Biome implements Constants {

	private double[][] reliefMap;
	private double[][] tempMap;
	private double[][] humidMap;

	public Biome(double[][] reliefMap, double[][] tempMap, double[][] humidMap) {
		this.reliefMap = reliefMap;
		this.tempMap = tempMap;
		this.humidMap = humidMap;
	}

	public int getBiomeType(int x, int y) {
		double height = reliefMap[x][y];
		double temp = tempMap[x][y];
		double humidity = humidMap[x][y];

		if (temp < 20 && height > 0)
		{
			// Try returning higher weighted grayscale values, if you go out of color bounds,
			// just set RGB to 255,255,255 (White)
			if ((SNOW_MIN + height*255) <= 255)
				return new Color((int) (SNOW_MIN + height*255), (int) (SNOW_MIN + height*255), (int) (SNOW_MIN + height*255)).getRGB();
			else
				return new Color(255, 255, 255).getRGB();
		}
		else if (tempMap[x][y] > 60 && height > 0.2 && height < 0.4){
			return new Color(240,(int)(220+(height*75)),150).getRGB();
		}
		
		// Return heightmap RGB

		if (height < 0.0)
			// Uhhh.... What did I write here? It works, but I'd appreciate if
			// you figured it out, future me!
			return new Color(0, Math.abs(140 - (OCEAN_MIN + (int) Math.abs(height / 2 * 160))),
					Math.abs(210 - (OCEAN_MIN + (int) Math.abs(height * 150)))).getRGB();
		else if (height < 0.6)
			return new Color(0, 30 + (int) (height / 2 * 255), 0).getRGB();
		else
			return new Color((int) ((height / 1.5 * 255)), (int) ((height / 3 * 255)), (int) ((height / 5 * 255)))
					.getRGB();
	}
	
	public double normalise(double min, double max, double val)
	{
		return (val - min)/(max-min);
	}
	
	public int getBiomeTypeFromImage(int x, int y, BufferedImage img) {
		double height = reliefMap[x][y];

		
		double xTemp =  normalise(0,100,tempMap[x][y]) * img.getWidth(null);
		double yHumidity = normalise(0,100,humidMap[x][y]-1) * img.getHeight(null);
		
		//System.out.println(xTemp+","+yHumidity);
		
		if(height > 0)
		return img.getRGB((int)xTemp, (int)yHumidity);
		else
			return new Color(0, Math.abs(140 - (OCEAN_MIN + (int) Math.abs(height / 2 * 160))),
					Math.abs(210 - (OCEAN_MIN + (int) Math.abs(height * 150)))).getRGB();
	}

}
