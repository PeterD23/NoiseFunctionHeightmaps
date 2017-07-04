package Generation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Wraparound {

	/**
	 * This is a fairly complex system and probably won't work. Here's how it goes:
	 * Create a min size of image X, and a max size X2, init array as max size.
	 * On loop, generate to X, after X generate towards X2 but check if edge disparity with x=0 < 0.1
	 * Terminate generation if max size reached 
	 */
	private float total, frequency, amplitude;
	private int octaves = 16;
	private float gain = 0.65f;
	private float lacunarity = 2.1042f;
	
	private int minX, maxX, minY, maxY, seed, feature_size;
	private float disparityCheck;
	
	private int winX, winY = 0;
	
	private OpenSimplexNoise noise;
	
	public Wraparound(int minX, int maxX, int minY, int maxY, int seed, int feature_size, float disparityCheck){
		this.seed = seed;
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.disparityCheck = disparityCheck;
		this.feature_size = feature_size;
	}
	
	public void run(){
		double[][] map;
		
		while(true){
			boolean failed = false;
			int passPoint = maxX;  // Map must be rectangular, so if 0th element passes, this is what element 1 must pass at too
			
			map = new double[maxX][maxY];
			noise = new OpenSimplexNoise(seed);
			System.out.println("Attempting seed "+seed);
			
			// Check all the X's
			for (int y = 0; y < maxY; y++) {
				for (int x = 0; x < maxX; x++) {

					process(noise, x, y);

					double value = total;
					
					if (value > 1)
						value = 1;
					else if (value < -1)
						value = -1;
					
					if(passPoint == maxX && x > minX && Math.abs(value - map[0][y]) <= disparityCheck ){
						map[x][y] = value;
						passPoint = x;
						minX = passPoint-1;
						winX = x;
						break;
					} else if(x > passPoint){
						failed = true;
						System.out.println("Failure at "+x+","+y+",disparity was "+Math.abs(value - map[0][y]));
						break;
					} else if(x > minX && Math.abs(value - map[0][y]) <= disparityCheck){
						map[x][y] = value;
						break;
					}
					
					map[x][y] = value;
					
					if(x == maxX-1)
						failed = true;
				}
				if(failed)
					break;
				else if(y > minY){
					for(int x = 0; x < passPoint; x++){
						if(Math.abs(map[x][y]-map[x][0]) > disparityCheck){
							break;
						}
						winY = y;
					}
					if(winY != 0)
						break;
				}
			}
			if(winY == 0)
				failed = true;	
			if(failed)
				seed++;
			else {
				break;
			}
				
		}
		System.out.println("X and Y wraparound found on seed "+seed+ " with length "+winX+ " and height "+winY);
		generateRelief(map, winX, winY, 0);
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
	
	public void generateRelief(double relief[][], int width, int height, double seaLevel) {
		int OCEAN_MIN = 60;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		if (seaLevel > 1)
			seaLevel = 1;
		else if (seaLevel < -1)
			seaLevel = -1;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				double value = relief[x][y];

				int rgb = 0;
				if (value < 0.0 && value > seaLevel) {
					value = Math.abs(value);
					int mod = (int) (value * 150);
					rgb = new Color(240 - mod, 220 - mod, 150 - mod).getRGB();
				} else if (value < seaLevel)
					rgb = new Color(0, Math.abs(140 - (OCEAN_MIN + (int) Math.abs(value / 2 * 160))),
							Math.abs(210 - (OCEAN_MIN + (int) Math.abs(value * 150)))).getRGB();
				else if (value < 0.6)
					rgb = new Color(0, 10 + (int) (value / 2 * 255), 0).getRGB();
				else
					rgb = new Color((int) ((value / 1.5 * 255)), (int) ((value / 3 * 255)), (int) ((value / 5 * 255)))
							.getRGB();
				image.setRGB(x, y, rgb);

			}
		}
		
		try {
			ImageIO.write(image, "png", new File(String.format("tex//%d-%s.png", seed, "wrapped")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
