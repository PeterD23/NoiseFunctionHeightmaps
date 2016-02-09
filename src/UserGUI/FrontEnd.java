package UserGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Maps.HumidMap;
import Maps.ReliefMap;
import Maps.TempMap;

public class FrontEnd extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField seaField, widthField, heightField, seedField, octavesField, featuresField;
	private JButton btnRelief, btnTemp, btnHumid;
	private GeneratedImage generatedImage;
	private JLabel lblPreview;
	private JButton btnSaveImage;
	private JButton btnBiome;
	private JTextField biomeInField;
	private JLabel lblBiomeInput;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FrontEnd() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 773);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 50, 35, 0, 0, 0, 42, 0, 0, 0, 66, 70, 57, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 526, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblPreview = new JLabel("Preview");
		GridBagConstraints gbc_lblPreview = new GridBagConstraints();
		gbc_lblPreview.gridwidth = 13;
		gbc_lblPreview.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreview.gridx = 1;
		gbc_lblPreview.gridy = 1;
		contentPane.add(lblPreview, gbc_lblPreview);

		generatedImage = new GeneratedImage();
		GridBagConstraints gbc_generatedImage = new GridBagConstraints();
		gbc_generatedImage.gridwidth = 13;
		gbc_generatedImage.insets = new Insets(0, 0, 5, 5);
		gbc_generatedImage.fill = GridBagConstraints.BOTH;
		gbc_generatedImage.gridx = 1;
		gbc_generatedImage.gridy = 2;
		contentPane.add(generatedImage, gbc_generatedImage);

		JLabel lblWidth = new JLabel("Width");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 1;
		gbc_lblWidth.gridy = 3;
		contentPane.add(lblWidth, gbc_lblWidth);

		widthField = new JTextField();
		GridBagConstraints gbc_widthField = new GridBagConstraints();
		gbc_widthField.insets = new Insets(0, 0, 5, 5);
		gbc_widthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_widthField.gridx = 2;
		gbc_widthField.gridy = 3;
		contentPane.add(widthField, gbc_widthField);
		widthField.setColumns(4);

		btnRelief = new JButton("Relief");
		btnRelief.addActionListener(this);

		JLabel lblSeaLevel = new JLabel("Sea Level");
		GridBagConstraints gbc_lblSeaLevel = new GridBagConstraints();
		gbc_lblSeaLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeaLevel.gridx = 5;
		gbc_lblSeaLevel.gridy = 3;
		contentPane.add(lblSeaLevel, gbc_lblSeaLevel);

		seaField = new JTextField(4);
		GridBagConstraints gbc_seaField = new GridBagConstraints();
		gbc_seaField.fill = GridBagConstraints.HORIZONTAL;
		gbc_seaField.insets = new Insets(0, 0, 5, 5);
		gbc_seaField.gridx = 6;
		gbc_seaField.gridy = 3;
		contentPane.add(seaField, gbc_seaField);
		GridBagConstraints gbc_btnRelief = new GridBagConstraints();
		gbc_btnRelief.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRelief.insets = new Insets(0, 0, 5, 5);
		gbc_btnRelief.gridx = 10;
		gbc_btnRelief.gridy = 3;
		contentPane.add(btnRelief, gbc_btnRelief);

		btnTemp = new JButton("Temperature");
		btnTemp.addActionListener(this);
		GridBagConstraints gbc_btnTemp = new GridBagConstraints();
		gbc_btnTemp.insets = new Insets(0, 0, 5, 5);
		gbc_btnTemp.gridx = 11;
		gbc_btnTemp.gridy = 3;
		contentPane.add(btnTemp, gbc_btnTemp);

		btnHumid = new JButton("Humidity");
		btnHumid.addActionListener(this);
		GridBagConstraints gbc_btnHumid = new GridBagConstraints();
		gbc_btnHumid.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnHumid.insets = new Insets(0, 0, 5, 5);
		gbc_btnHumid.gridx = 12;
		gbc_btnHumid.gridy = 3;
		contentPane.add(btnHumid, gbc_btnHumid);

		btnBiome = new JButton("Biome");
		btnBiome.addActionListener(this);
		GridBagConstraints gbc_btnBiome = new GridBagConstraints();
		gbc_btnBiome.insets = new Insets(0, 0, 5, 5);
		gbc_btnBiome.gridx = 13;
		gbc_btnBiome.gridy = 3;
		contentPane.add(btnBiome, gbc_btnBiome);

		JLabel lblHeight = new JLabel("Height");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 4;
		contentPane.add(lblHeight, gbc_lblHeight);

		heightField = new JTextField();
		GridBagConstraints gbc_heightField = new GridBagConstraints();
		gbc_heightField.insets = new Insets(0, 0, 5, 5);
		gbc_heightField.fill = GridBagConstraints.HORIZONTAL;
		gbc_heightField.gridx = 2;
		gbc_heightField.gridy = 4;
		contentPane.add(heightField, gbc_heightField);
		heightField.setColumns(4);

		JLabel lblOctaves = new JLabel("Octaves");
		GridBagConstraints gbc_lblOctaves = new GridBagConstraints();
		gbc_lblOctaves.insets = new Insets(0, 0, 5, 5);
		gbc_lblOctaves.gridx = 5;
		gbc_lblOctaves.gridy = 4;
		contentPane.add(lblOctaves, gbc_lblOctaves);

		octavesField = new JTextField();
		GridBagConstraints gbc_octavesField = new GridBagConstraints();
		gbc_octavesField.fill = GridBagConstraints.HORIZONTAL;
		gbc_octavesField.insets = new Insets(0, 0, 5, 5);
		gbc_octavesField.gridx = 6;
		gbc_octavesField.gridy = 4;
		contentPane.add(octavesField, gbc_octavesField);
		octavesField.setColumns(4);

		JLabel lblSeed = new JLabel("Seed");
		GridBagConstraints gbc_lblSeed = new GridBagConstraints();
		gbc_lblSeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeed.gridx = 1;
		gbc_lblSeed.gridy = 5;
		contentPane.add(lblSeed, gbc_lblSeed);

		seedField = new JTextField();
		GridBagConstraints gbc_seedField = new GridBagConstraints();
		gbc_seedField.insets = new Insets(0, 0, 5, 5);
		gbc_seedField.fill = GridBagConstraints.HORIZONTAL;
		gbc_seedField.gridx = 2;
		gbc_seedField.gridy = 5;
		contentPane.add(seedField, gbc_seedField);
		seedField.setColumns(4);

		btnSaveImage = new JButton("Save Image");
		btnSaveImage.addActionListener(this);

		lblBiomeInput = new JLabel("Biome Input");
		GridBagConstraints gbc_lblBiomeInput = new GridBagConstraints();
		gbc_lblBiomeInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblBiomeInput.anchor = GridBagConstraints.EAST;
		gbc_lblBiomeInput.gridx = 5;
		gbc_lblBiomeInput.gridy = 5;
		contentPane.add(lblBiomeInput, gbc_lblBiomeInput);

		biomeInField = new JTextField();
		GridBagConstraints gbc_biomeInField = new GridBagConstraints();
		gbc_biomeInField.gridwidth = 3;
		gbc_biomeInField.anchor = GridBagConstraints.SOUTH;
		gbc_biomeInField.insets = new Insets(0, 0, 5, 5);
		gbc_biomeInField.fill = GridBagConstraints.HORIZONTAL;
		gbc_biomeInField.gridx = 6;
		gbc_biomeInField.gridy = 5;
		contentPane.add(biomeInField, gbc_biomeInField);
		biomeInField.setColumns(10);
		GridBagConstraints gbc_btnSaveImage = new GridBagConstraints();
		gbc_btnSaveImage.gridwidth = 2;
		gbc_btnSaveImage.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveImage.gridx = 11;
		gbc_btnSaveImage.gridy = 5;
		contentPane.add(btnSaveImage, gbc_btnSaveImage);

		JLabel lblFeatureSize = new JLabel("Feature Size");
		GridBagConstraints gbc_lblFeatureSize = new GridBagConstraints();
		gbc_lblFeatureSize.insets = new Insets(0, 0, 0, 5);
		gbc_lblFeatureSize.gridx = 1;
		gbc_lblFeatureSize.gridy = 6;
		contentPane.add(lblFeatureSize, gbc_lblFeatureSize);

		featuresField = new JTextField();
		GridBagConstraints gbc_featuresField = new GridBagConstraints();
		gbc_featuresField.fill = GridBagConstraints.HORIZONTAL;
		gbc_featuresField.insets = new Insets(0, 0, 0, 5);
		gbc_featuresField.gridx = 2;
		gbc_featuresField.gridy = 6;
		contentPane.add(featuresField, gbc_featuresField);
		featuresField.setColumns(4);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnSaveImage && generatedImage.getSeed() != -1) {
			generatedImage.saveImage();
		} else {

			try {
				lblPreview.setText("Processing...");
				int width = Integer.parseInt(widthField.getText());
				int height = Integer.parseInt(heightField.getText());
				double features = Double.parseDouble(featuresField.getText());
				long seed = Long.parseLong(seedField.getText());
				int octaves = Integer.parseInt(octavesField.getText());
				double seaLevel = Double.parseDouble(seaField.getText());
				double[][] reliefMap = new ReliefMap(width, height, features, octaves).generate(seed, 0, 0);
				if (evt.getSource() == btnRelief) {
					generatedImage.generateRelief(reliefMap, width, height, seaLevel);
					lblPreview.setText("Relief Map/ Seed: " + seed);
					generatedImage.setInfo(0, seed);
				} else if (evt.getSource() == btnTemp) {
					generatedImage.generateTemp(reliefMap, width, height);
					lblPreview.setText("Temperature Map/ Seed: " + seed);
					generatedImage.setInfo(1, seed);
				} else if (evt.getSource() == btnHumid) {
					generatedImage.generateHumidity(reliefMap, width, height);
					lblPreview.setText("Humidity Map / Seed: " + seed);
					generatedImage.setInfo(2, seed);
				} else if (evt.getSource() == btnBiome) {
					BufferedImage bioMap = ImageIO.read(new File("biomap//"+biomeInField.getText()+".png"));
					double[][] tempMap = new TempMap(width,height,reliefMap).generate();
					double[][] humidMap = new HumidMap(width,height,reliefMap).generate();
					generatedImage.generateBiome(reliefMap,tempMap,humidMap,width,height,bioMap);
					lblPreview.setText("Biome Map / Seed: " + seed);
					generatedImage.setInfo(3, seed);
				}

			} catch (Exception e) {
				e.printStackTrace();
				lblPreview.setText("Invalid parameters!");

			}
		}

	}

}
