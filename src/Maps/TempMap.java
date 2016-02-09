package Maps;

public class TempMap {

	/**
	 * Unused for the time being. These were used for the original temperature
	 * map creation, but the generation method has since been moved to
	 * generatedImage.java.
	 */

	private int width, height;
	private final double HEIGHT_MODIFIER = 1.1;// Affects how much temp will
												// drop from climbing heights
	private final int MID_HEAT_INDEX = 300; // Increases how much heat will be
											// at the centre that radiates out
											// to the north and south

	private double[][] reliefMap;

	public TempMap(int width, int height, double[][] reliefMap) {
		this.width = width;
		this.height = height;
		this.reliefMap = reliefMap;
	}

	public double[][] generate() throws Exception {
		double midpoint = height / 2;
		double ambientTemp = 0;

		double[][] tempMap = new double[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				double value = 0;
				if (reliefMap[x][y] > 0)
					value = ambientTemp - (ambientTemp / HEIGHT_MODIFIER * reliefMap[x][y]);
				else
					value = ambientTemp / 2;

				tempMap[x][y] = value;
			}
			if (y < midpoint)
				ambientTemp = (y / midpoint) * MID_HEAT_INDEX / 2;
			else
				ambientTemp = MID_HEAT_INDEX - ((y / midpoint) * MID_HEAT_INDEX / 2);

			if (ambientTemp > 100)
				ambientTemp = 100; // Cap the ambient to 100%

		}
		return tempMap;
	}

}
