package io.github.guiritter.center_of_mass.image;

import static io.github.guiritter.center_of_mass.image.CenterOfMassImageComputer.compute;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main {

	static {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
	}

	public static void main(String args[]) throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		int returnState = chooser.showOpenDialog(null);
		if (returnState != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File imageFile = chooser.getSelectedFile();
		if (imageFile == null) {
			return;
		}
		Point2D<BigDecimal> centerOfMass = compute(ImageIO.read(imageFile));
		JTextField field = new JTextField(String.format("Center of mass is at (%s, %s).", centerOfMass.x, centerOfMass.y));
		JOptionPane.showMessageDialog(null, field);
	}
}