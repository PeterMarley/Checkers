package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyJFrame extends JFrame {
	public MyJFrame() {
		ImageIcon icon = new ImageIcon("./icons/checkersIcon.png");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Checkers!");
		this.setIconImage(icon.getImage());
		this.getContentPane().setBackground(Color.GRAY);
		//this.setLayout(null);
	}
}
