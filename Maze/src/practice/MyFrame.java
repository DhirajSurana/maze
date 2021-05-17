package practice;


import java.awt.Color;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

	private final int WIDTH = 660;
	private final int HEIGHT = 660;

	public MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(new DrawingCanvas());
		this.setVisible(true);
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.BLACK);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}
