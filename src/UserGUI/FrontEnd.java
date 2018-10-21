package UserGUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Maps.ReliefMap;

public class FrontEnd extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField seaField, widthField, heightField, seedField, octavesField, featuresField;
	private JButton btnRelief;
	private GeneratedImage generatedImage;
	private JLabel lblPreview;
	private JButton btnSaveImage;
	private JTextField radialField;
	private JLabel lblNewLabel;
	private JTextField mountainLevel;
	private JLabel lblMountainLevel;
	private JLabel lblLandType;
	private JComboBox<String> comboBox;
	private JProgressBar genProg;
	private JCheckBox genSea;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FrontEnd() {
		setTitle("Realms of Ashjana Map Generator 1.0: Cartography for Dumbasses");
		try {
			setIconImage(new ImageIcon("res\\RoA.png").getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 50, 35, 0, 0, 0, 72, 97, 0, 66, 70, 57, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 550, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblPreview = new JLabel("Preview");
		GridBagConstraints gbc_lblPreview = new GridBagConstraints();
		gbc_lblPreview.gridwidth = 12;
		gbc_lblPreview.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreview.gridx = 1;
		gbc_lblPreview.gridy = 1;
		contentPane.add(lblPreview, gbc_lblPreview);

		generatedImage = new GeneratedImage();
		GridBagConstraints gbc_generatedImage = new GridBagConstraints();
		gbc_generatedImage.gridwidth = 12;
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
		gbc_widthField.gridwidth = 2;
		gbc_widthField.insets = new Insets(0, 0, 5, 5);
		gbc_widthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_widthField.gridx = 2;
		gbc_widthField.gridy = 3;
		contentPane.add(widthField, gbc_widthField);
		widthField.setColumns(4);

		btnRelief = new JButton("Generate!");
		btnRelief.addActionListener(this);

		JLabel lblOctaves = new JLabel("Octaves");
		GridBagConstraints gbc_lblOctaves = new GridBagConstraints();
		gbc_lblOctaves.anchor = GridBagConstraints.WEST;
		gbc_lblOctaves.insets = new Insets(0, 0, 5, 5);
		gbc_lblOctaves.gridx = 5;
		gbc_lblOctaves.gridy = 3;
		contentPane.add(lblOctaves, gbc_lblOctaves);

		octavesField = new JTextField();
		GridBagConstraints gbc_octavesField = new GridBagConstraints();
		gbc_octavesField.fill = GridBagConstraints.HORIZONTAL;
		gbc_octavesField.insets = new Insets(0, 0, 5, 5);
		gbc_octavesField.gridx = 6;
		gbc_octavesField.gridy = 3;
		contentPane.add(octavesField, gbc_octavesField);
		octavesField.setColumns(4);
		GridBagConstraints gbc_btnRelief = new GridBagConstraints();
		gbc_btnRelief.gridwidth = 2;
		gbc_btnRelief.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRelief.insets = new Insets(0, 0, 5, 5);
		gbc_btnRelief.gridx = 9;
		gbc_btnRelief.gridy = 3;
		contentPane.add(btnRelief, gbc_btnRelief);

		genSea = new JCheckBox("S");
		GridBagConstraints gbc_genSea = new GridBagConstraints();
		gbc_genSea.anchor = GridBagConstraints.WEST;
		gbc_genSea.insets = new Insets(0, 0, 5, 5);
		gbc_genSea.gridx = 11;
		gbc_genSea.gridy = 3;
		contentPane.add(genSea, gbc_genSea);

		JLabel lblHeight = new JLabel("Height");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 1;
		gbc_lblHeight.gridy = 4;
		contentPane.add(lblHeight, gbc_lblHeight);

		heightField = new JTextField();
		GridBagConstraints gbc_heightField = new GridBagConstraints();
		gbc_heightField.gridwidth = 2;
		gbc_heightField.insets = new Insets(0, 0, 5, 5);
		gbc_heightField.fill = GridBagConstraints.HORIZONTAL;
		gbc_heightField.gridx = 2;
		gbc_heightField.gridy = 4;
		contentPane.add(heightField, gbc_heightField);
		heightField.setColumns(4);

		JLabel lblSeaLevel = new JLabel("Sea Level");
		GridBagConstraints gbc_lblSeaLevel = new GridBagConstraints();
		gbc_lblSeaLevel.anchor = GridBagConstraints.WEST;
		gbc_lblSeaLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeaLevel.gridx = 5;
		gbc_lblSeaLevel.gridy = 4;
		contentPane.add(lblSeaLevel, gbc_lblSeaLevel);

		seaField = new JTextField(4);
		GridBagConstraints gbc_seaField = new GridBagConstraints();
		gbc_seaField.fill = GridBagConstraints.HORIZONTAL;
		gbc_seaField.insets = new Insets(0, 0, 5, 5);
		gbc_seaField.gridx = 6;
		gbc_seaField.gridy = 4;
		contentPane.add(seaField, gbc_seaField);

		genProg = new JProgressBar();
		GridBagConstraints gbc_genProg = new GridBagConstraints();
		gbc_genProg.fill = GridBagConstraints.HORIZONTAL;
		gbc_genProg.gridwidth = 2;
		gbc_genProg.insets = new Insets(0, 0, 5, 5);
		gbc_genProg.gridx = 9;
		gbc_genProg.gridy = 4;
		contentPane.add(genProg, gbc_genProg);

		JLabel lblSeed = new JLabel("Seed");
		GridBagConstraints gbc_lblSeed = new GridBagConstraints();
		gbc_lblSeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeed.gridx = 1;
		gbc_lblSeed.gridy = 5;
		contentPane.add(lblSeed, gbc_lblSeed);

		seedField = new JTextField();
		GridBagConstraints gbc_seedField = new GridBagConstraints();
		gbc_seedField.gridwidth = 2;
		gbc_seedField.insets = new Insets(0, 0, 5, 5);
		gbc_seedField.fill = GridBagConstraints.HORIZONTAL;
		gbc_seedField.gridx = 2;
		gbc_seedField.gridy = 5;
		contentPane.add(seedField, gbc_seedField);
		seedField.setColumns(4);

		lblMountainLevel = new JLabel("Mountain Level");
		GridBagConstraints gbc_lblMountainLevel = new GridBagConstraints();
		gbc_lblMountainLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblMountainLevel.anchor = GridBagConstraints.WEST;
		gbc_lblMountainLevel.gridx = 5;
		gbc_lblMountainLevel.gridy = 5;
		contentPane.add(lblMountainLevel, gbc_lblMountainLevel);

		mountainLevel = new JTextField();
		GridBagConstraints gbc_mountainLevel = new GridBagConstraints();
		gbc_mountainLevel.insets = new Insets(0, 0, 5, 5);
		gbc_mountainLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_mountainLevel.gridx = 6;
		gbc_mountainLevel.gridy = 5;
		contentPane.add(mountainLevel, gbc_mountainLevel);
		mountainLevel.setColumns(10);

		btnSaveImage = new JButton("Save Image");
		btnSaveImage.addActionListener(this);
		GridBagConstraints gbc_btnSaveImage = new GridBagConstraints();
		gbc_btnSaveImage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveImage.gridwidth = 2;
		gbc_btnSaveImage.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveImage.gridx = 9;
		gbc_btnSaveImage.gridy = 5;
		contentPane.add(btnSaveImage, gbc_btnSaveImage);

		JLabel lblFeatureSize = new JLabel("Feature Size");
		GridBagConstraints gbc_lblFeatureSize = new GridBagConstraints();
		gbc_lblFeatureSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblFeatureSize.gridx = 1;
		gbc_lblFeatureSize.gridy = 6;
		contentPane.add(lblFeatureSize, gbc_lblFeatureSize);

		featuresField = new JTextField();
		GridBagConstraints gbc_featuresField = new GridBagConstraints();
		gbc_featuresField.gridwidth = 2;
		gbc_featuresField.fill = GridBagConstraints.HORIZONTAL;
		gbc_featuresField.insets = new Insets(0, 0, 5, 5);
		gbc_featuresField.gridx = 2;
		gbc_featuresField.gridy = 6;
		contentPane.add(featuresField, gbc_featuresField);
		featuresField.setColumns(4);

		lblNewLabel = new JLabel("Radial Map");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 6;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		radialField = new JTextField();
		radialField.setText("radial2");
		GridBagConstraints gbc_radialField = new GridBagConstraints();
		gbc_radialField.fill = GridBagConstraints.HORIZONTAL;
		gbc_radialField.anchor = GridBagConstraints.SOUTH;
		gbc_radialField.insets = new Insets(0, 0, 5, 5);
		gbc_radialField.gridx = 6;
		gbc_radialField.gridy = 6;
		contentPane.add(radialField, gbc_radialField);
		radialField.setColumns(10);

		lblLandType = new JLabel("Land Type");
		GridBagConstraints gbc_lblLandType = new GridBagConstraints();
		gbc_lblLandType.anchor = GridBagConstraints.EAST;
		gbc_lblLandType.insets = new Insets(0, 0, 0, 5);
		gbc_lblLandType.gridx = 5;
		gbc_lblLandType.gridy = 7;
		contentPane.add(lblLandType, gbc_lblLandType);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "forest", "desert", "tundra" }));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 6;
		gbc_comboBox.gridy = 7;
		contentPane.add(comboBox, gbc_comboBox);

		setVisible(true);
	}

	public void init() {
		heightField.setText("1024");
		widthField.setText("1024");
		seedField.setText("1");
		featuresField.setText("0.5");
		octavesField.setText("8");
		seaField.setText("0.04");
		mountainLevel.setText("0.5");
		btnRelief.doClick();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnSaveImage && generatedImage.getSeed() != -1) {
			generatedImage.saveImage();
		} else {
			try {
				genProg.setValue(0);

				new Thread(() -> {
					btnRelief.setEnabled(false);
					try {
						generate(evt, genSea.isSelected());
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						btnRelief.setEnabled(true);
					}
				}).start();

			} catch (Exception e) {
				e.printStackTrace();
				lblPreview.setText("Invalid parameters!");

			}
		}

	}

	private void generate(ActionEvent evt, boolean generateSea) throws IOException {
		lblPreview.setText("Processing...");
		int square = Integer.parseInt(widthField.getText());
		double features = Double.parseDouble(featuresField.getText());
		long seed = Long.parseLong(seedField.getText());
		int octaves = Integer.parseInt(octavesField.getText());
		double seaLevel = Math.abs(Double.parseDouble(seaField.getText()));
		double mountLevel = Math.abs(Double.parseDouble(mountainLevel.getText()));
		String radial = radialField.getText();
		if (generateSea) {
			double[][] seaMap = new ReliefMap(square, features, octaves).initBar(genProg).generate(seed, radial, false);
			generatedImage.generateSea(seaMap, square);
		}
		double[][] landMap = new ReliefMap(square, features, octaves).initBar(genProg).generate(seed, radial, true);
		generatedImage.generateLand(landMap, seaLevel, mountLevel, square, (String) comboBox.getSelectedItem());
		lblPreview.setText("Relief Map/ Seed: " + seed);
		generatedImage.setInfo(0, seed);

	}

}
