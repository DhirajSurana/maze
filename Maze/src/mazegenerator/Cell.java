package mazegenerator;

/**
 * Cell class for defining all the required properties
 * 
 * @author DHIRAJ
 *
 */
public class Cell {
	protected int row;
	protected int col;
	protected int x;
	protected int y;
	protected int size;
	protected boolean topwall = true;
	protected boolean rightwall = true;
	protected boolean bottomwall = true;
	protected boolean leftwall = true;
	protected boolean visited = false;

	public Cell(int i, int j, int row, int col, int size) {
		x = i;
		y = j;
		this.row = row;
		this.col = col;
		this.size = size;
	}

}
