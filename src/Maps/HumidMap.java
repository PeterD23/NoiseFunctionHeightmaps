package Maps;

public class HumidMap {

	/**
	 * Unused for the time being. These were used for the original humidity map
	 * creation, but the generation method has since been moved to
	 * generatedImage.java.
	 */

	private int width, height;

	private double[][] reliefMap;

	public HumidMap(int width, int height, double[][] reliefMap) {
		this.width = width;
		this.height = height;
		this.reliefMap = reliefMap;
	}

	public double[][] generate() throws Exception {
		double[][] humidMap = new double[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				double value = 0;
				if (reliefMap[x][y] > 0)
					value = 100 - (reliefMap[x][y] * 100);
				else
					value = 100;
				// System.out.println(value);
				// (int)((255 * (100 - value)) / 100)
				// (int)((255 * value) / 100)

				humidMap[x][y] = value;
			}

		}
		return humidMap;
	}

	/**
	 * Reverse Pythagoras. Get the distance from two points in an array.
	 * 
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 * @return
	 */
	public double getDistance(double x1, double x2, double y1, double y2) {
		double x = Math.abs((x2 - x1) * (x2 - x1));
		double y = Math.abs((y2 - y1) * (y2 - y1));
		return Math.sqrt(x + y);
	}

}
