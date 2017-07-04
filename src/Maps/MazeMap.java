package Maps;

import java.io.IOException;

import Generation.OpenSimplexNoise;

public class MazeMap {

	private int width;
	private int height;
	private double feature_size;

	private float total, frequency, amplitude;
	private int octaves = 16;
	private float gain = 0.65f;
	private float lacunarity = 2.1042f;

	public MazeMap(int width, int height, double feature_size) {
		this.width = width;
		this.height = height;
		this.feature_size = feature_size;
	}

	public MazeMap(int width, int height, double feature_size, int octaves) {
		this.width = width;
		this.height = height;
		this.feature_size = feature_size;
		this.octaves = octaves;
	}

	public double[][] generate(long seed, boolean useBrownian) throws IOException {
		OpenSimplexNoise noise = new OpenSimplexNoise(seed);
		double[][] map = new double[width][height];

		//

		// TODO: FIX THIS

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				if (useBrownian)
					process(noise, x, y);
				else
					total += noise.eval(x, y);

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

}
