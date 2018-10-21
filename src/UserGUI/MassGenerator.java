package UserGUI;

import java.awt.Color;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import Maps.ReliefMap;
import net.miginfocom.swing.MigLayout;

public class MassGenerator extends JFrame {

	private JTextField params;
	private JButton btnGenerate;
	private JProgressBar progressBar;
	private GeneratedImage image;

	private int square = 256;
	
	public MassGenerator() {
		getContentPane().setLayout(new MigLayout("", "[101.00][][][][grow]", "[][][][][][][]"));
		setSize(400, 300);

		JLabel lblMassGen = new JLabel("MASS GENERATOR 1.0");
		getContentPane().add(lblMassGen, "cell 2 1,alignx center,aligny center");

		params = new JTextField();
		params.setToolTipText("Syntax: Range int-int, F-Size float, RadialMap string ");
		getContentPane().add(params, "cell 2 4,growx");
		params.setColumns(10);

		btnGenerate = new JButton("Execute");
		btnGenerate.addActionListener(evt -> {
			massGenerate();
		});
		getContentPane().add(btnGenerate, "cell 2 5,grow");

		progressBar = new JProgressBar();
		progressBar.setBackground(Color.RED);
		progressBar.setForeground(Color.GREEN);
		getContentPane().add(progressBar, "cell 2 6");

		image = new GeneratedImage();

		File dir = new File("mass");
		if (!dir.exists()) {
			dir.mkdir();
		}

		setResizable(false);
		setVisible(true);
	}

	private void massGenerate() {
		btnGenerate.setText("Running");

		String[] data = params.getText().split(" ");

		String[] range = data[0].split("-");
		if (range.length != 2)
			return;

		int startSeed = Integer.parseInt(range[0]);
		int endSeed = Integer.parseInt(range[1]);

		String[] steps = data[1].split("-");
		float startSize = Float.parseFloat(steps[0]);
		float endSize = steps.length > 1 ? Float.parseFloat(steps[1]) : Float.parseFloat(steps[0]);

		String radial = data.length == 3 ? data[2] : "";

		try {
			progressBar.setMinimum(startSeed);
			progressBar.setValue(startSeed);
			progressBar.setMaximum(endSeed);

			new Thread(() -> {
				btnGenerate.setEnabled(false);
				try {

					for (int seed = startSeed; seed <= endSeed; seed++) {
						for (float feature = startSize; feature <= endSize; feature += 0.1) {
							double[][] landMap = new ReliefMap(square, feature, 8).generate(seed, radial, true);
							image.generateLand(landMap, 0.04, 0.5, square, "forest");
							image.saveImageToDir("mass", radial, seed, feature);
							progressBar.setValue(seed);
						}
					}

					btnGenerate.setText("Success!");
				} catch (Exception e) {
					btnGenerate.setText("Failed!");
					e.printStackTrace();
				} finally {
					btnGenerate.setEnabled(true);
				}
			}).start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
