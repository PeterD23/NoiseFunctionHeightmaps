package Initialisation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Generation.Constants;
import Generation.Voronoi;
import UserGUI.FrontEnd;

public class Main implements Constants {
	
	public static void main(String[] args) throws Exception {	
		FrontEnd fEnd = new FrontEnd();
		fEnd.init();
	}
	
	public static void generateVoronoi() throws Exception {
		int width = 1024;
		int height = 1024;
		Voronoi voronoi = new Voronoi(1,(short)0);
		
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				
				double value = voronoi.noise(x, y, 0.1f);
				int rgb = 0;
				if (value < 0)
					rgb = new Color(0, Math.abs(140 - (OCEAN_MIN + (int) Math.abs(value / 2 * 160))),
							Math.abs(210 - (OCEAN_MIN + (int) Math.abs(value * 150)))).getRGB(); 
				else
					rgb = new Color(0, 10 + (int) (value / 2 * 255), 0).getRGB();
				image.setRGB(x,y,rgb);
			}
		}
		ImageIO.write(image,"png", new File("image.png"));
	}
	
}
