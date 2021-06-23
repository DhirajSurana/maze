package mazegenerator;

/**
 * Cell class for defining all the required properties
 * 
 * @author DHIRAJ
 *
 */
public class Cell {
	int row, col, x, y, size;
	boolean topwall = true, rightwall = true, bottomwall = true, leftwall = true;
	boolean visited = false;

	public Cell(int i, int j, int row, int col, int size) {
		x = i;
		y = j;
		this.row = row;
		this.col = col;
		this.size = size;
	}

}
