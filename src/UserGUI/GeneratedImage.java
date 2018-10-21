package UserGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Generation.Constants;

public class GeneratedImage extends JPanel implements Constants {

	private BufferedImage seaImg = null;
	private BufferedImage landImg = null;

	private int mode = 0;
	private long seed = -1;
	private String[] type = { "relief", "temp", "humid", "biome" };

	public GeneratedImage() {
		setSize(600, 600);
		landImg = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
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

	public void generateSea(double[][] seaMap, int square) {
		seaImg = new BufferedImage(square, square, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < square; y++) {
			for (int x = 0; x < square; x++) {

				double value = seaMap[x][y];

				if (value > 0.0)
					value *= -1;
				int rgb = new Color(0, Math.abs(140 - (OCEAN_MIN + (int) Math.abs(value / 2 * 160))),
						Math.abs(210 - (OCEAN_MIN + (int) Math.abs(value * 150)))).getRGB();
				seaImg.setRGB(x, y, rgb);

				repaint();
			}
		}

	}

	public void generateLand(double[][] landMap, double sea, double mount, int square, String landType) {

		landImg = new BufferedImage(square, square, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < square; y++) {
			for (int x = 0; x < square; x++) {

				double value = landMap[x][y];

				int rgb = 0;
				if (value <= sea) {
					rgb = new Color(0, 0, 0, 0).getRGB();
				} else if (value < mount) {
					if ("forest".equals(landType))
						rgb = getForest(value);
					else if ("desert".equals(landType))
						rgb = getDesert(value);
					else
						rgb = getTundra(value);
				} else {
					rgb = new Color((int) ((value / 1.5 * 255)), (int) ((value / 3 * 255)), (int) ((value / 5 * 255)),
							255).getRGB();
				}
				landImg.setRGB(x, y, rgb);

				repaint();
			}
		}

	}

	public int getForest(double value) {
		return new Color(0, 15 + (int) (value / 1.5 * 255), 0, 255).getRGB();
	}

	public int getDesert(double value) {
		if (value > 0.1) {
			try {
				return new Color(237 - (int) (value * 237), 201 - (int) (value * 300), 120 - (int) (value * 300), 255)
						.getRGB();
			} catch (Exception e) {
				return new Color(0, 0, 0, 0).getRGB();
			}
		} else
			return new Color(40 + (int) (value / 1.1 * 255), 40 + (int) (value / 1.2 * 255),0, 255)
					.getRGB();
	}

	public int getTundra(double value) {
		return new Color(10 + (int) (value / 2 * 255), 10 + (int) (value / 2 * 255), 10 + (int) (value / 2 * 255), 255)
				.getRGB();
	}

	public void saveImage() {
		try {
			char sn = File.separatorChar;

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			chooser.setDialogTitle("Set directory save");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int accept = chooser.showSaveDialog(this);
			String dir = null;
			if (accept == JFileChooser.APPROVE_OPTION) {
				dir = chooser.getSelectedFile().getAbsolutePath();
				ImageIO.write(seaImg, "png", new File(String.format(dir + sn + "%d-%s.png", seed, "sea")));
				ImageIO.write(landImg, "png", new File(String.format(dir + sn + "%d-%s.png", seed, "land")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveImageToDir(String dir, String radial, int seed, float feature) throws IOException {
		char sn = File.separatorChar;
		ImageIO.write(landImg, "png", new File(String.format(dir + sn + "%d-%.1f-%s.png", seed, feature, radial)));
	}

	public long getSeed() {
		return seed;
	}

}
