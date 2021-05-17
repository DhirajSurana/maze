package mazegenerator;


import javax.swing.JComponent;

public class Cell extends JComponent {
	int row, col;
	int x, y;
	boolean topwall = true, rightwall = true, bottomwall = true, leftwall = true;
	boolean visited = false;

	public Cell(int i, int j, int row, int col) {
		x = i;
		y = j;
		this.row = row;
		this.col = col;
	}

}
