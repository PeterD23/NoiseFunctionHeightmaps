package Initialisation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Generation.Constants;
import Generation.OpenSimplexNoise;
import Generation.Voronoi;
import UserGUI.FrontEnd;

public class Main implements Constants {
	
	public static void main(String[] args) throws Exception {

//		FrontEnd fEnd = new FrontEnd();
//		fEnd.init();
//		
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
		
//		ReliefMap relief = new ReliefMap(WIDTH, HEIGHT, 1024);
//		double[][] reliefMap = null;
//		
//		for (int heightIndex = 0; heightIndex < HEIGHT * BOX_HEIGHT; heightIndex += HEIGHT) { // Y
//			for (int widthIndex = 0; widthIndex < WIDTH * BOX_WIDTH; widthIndex += WIDTH) {
//				double time = System.currentTimeMillis();
//				reliefMap = relief.generate(seed, widthIndex, heightIndex);
//				
//				System.out.println("Relief Map generated in "+ ((System.currentTimeMillis()-time)/1000) + " seconds.");
//				
//			}
//		}
//		TempMap temp = new TempMap(WIDTH,HEIGHT,reliefMap);	
//		HumidMap humid = new HumidMap(WIDTH,HEIGHT,reliefMap);
//		double time = System.currentTimeMillis();
//		double[][] tempMap = temp.generate();
//		System.out.println("Temperature Map generated in "+ ((System.currentTimeMillis()-time)/1000) + " seconds.");
//		
//		time = System.currentTimeMillis();
//		double[][] humidMap = humid.generate();
//		System.out.println("Humidity Map generated in "+ ((System.currentTimeMillis()-time)/1000) + " seconds.");
//		
//		time = System.currentTimeMillis();
//		FinalMap finalMap = new FinalMap(reliefMap,tempMap,humidMap,WIDTH,HEIGHT);
//		finalMap.generateFromImage("biomemap.png");
//		System.out.println("Biome Map generated in "+ ((System.currentTimeMillis()-time)/1000) + " seconds.");
	}
	
}
