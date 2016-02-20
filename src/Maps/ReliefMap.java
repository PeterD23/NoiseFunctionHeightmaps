
package Maps;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Generation.Constants;
import Generation.OpenSimplexNoise;


public class ReliefMap implements Constants {

	private int width;
	private int height;
	private double feature_size;

	private float total, frequency, amplitude;
	private int octaves = 16;
	private float gain = 0.65f;
	private float lacunarity = 2.1042f;

	private final int OCEAN_MIN = 60; // Minimum colour level for ocean

	// TODO: Add biomes

	public ReliefMap(int width, int height, double feature_size){
		this.width = width;
		this.height = height;
		this.feature_size = feature_size;
	}
	
	public ReliefMap(int width, int height, double feature_size, int octaves){
		this.width = width;
		this.height = height;
		this.feature_size = feature_size;
		this.octaves = octaves;
	}

	
	public double[][] generate(long seed, int heightIndex, int widthIndex) throws IOException {
		OpenSimplexNoise noise = new OpenSimplexNoise(seed);
		double[][] map = new double[width][height];

		float midx = width / 2;
		float midy = height / 2;
		float rad = (float) Math.sqrt((midx * midx) + (midy * midy));

		//
		
				// TODO: FIX THIS

				for (int y = 0; y < height; y++) {
					for (int x = 0; x < width; x++) {

						process(noise, x + widthIndex, y + heightIndex, rad, midx, midy);

						double value = total;
						if (value > 1)
							value = 1;
						else if (value < -1)
							value = -1;

						// System.out.println(value);

						map[x][y] = value;		
						
					}
				}	
				return map;
			}

	public void process(OpenSimplexNoise noise, int x, int y, float rad, float midx, float midy) {
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

}

