package mazegenerator;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * class to Structure JFrame
 * 
 * @author DHIRAJ
 *
 */
public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MyFrame() {
		DrawingCanvas canvas = new DrawingCanvas();
		this.addKeyListener(canvas);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(canvas);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(getClass().getResource("/maze.png")).getImage());
		this.setTitle("Maze Game  || 'N'-> NEW MAZE || 'R'-> RESTART");
	}
}
