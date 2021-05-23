package mazegenerator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JComponent;

public class DrawingCanvas extends JComponent implements KeyListener {
	protected static final int ROWS = 20;
	protected static final int COLS = 20;
	protected static final int SIZE = 24;
	public static Cell[][] cell = new Cell[ROWS][COLS];
	private static Stack<Cell> stack;
	private Cell current, next, player;
	Random random;

	private int i, j, padding, ballsize;

	public DrawingCanvas() {
		addKeyListener(this);
		ballsize = (SIZE - 4);
		padding = (SIZE - ballsize) / 2;
		this.setPreferredSize(new Dimension(COLS * SIZE + 2 * SIZE, ROWS * SIZE + 2 * SIZE));
		generateGrid();

	}

	private void generateGrid() {
		for (i = 0; i < ROWS; i++) {
			for (j = 0; j < COLS; j++) {
				cell[i][j] = new Cell((i * SIZE) + SIZE, (j * SIZE) + SIZE, i, j, SIZE);
			}
		}
		dfs();

	}

	private void dfs() {
		stack = new Stack<>();
		current = cell[0][0];
		// current.leftwall = false;
		cell[ROWS - 1][COLS - 1].rightwall = false;
		current.visited = true;
		do {
			next = getNeighbour(current);

			if (next != null) {
				removeWall(current, next);
				stack.push(current);
				current = next;

				current.visited = true;
			} else {
				current = stack.pop();
			}
		} while (!stack.isEmpty());
		player = new Cell(SIZE, SIZE, 0, 0, SIZE);
	}

	private void removeWall(Cell current, Cell next) {
		// current is left of next
		if (current.row == next.row && current.col == next.col - 1) {
			current.rightwall = false;
			next.leftwall = false;
		}
		// current is right of next
		if (current.row == next.row && current.col == next.col + 1) {
			current.leftwall = false;
			next.rightwall = false;
		}
		// current is top of next
		if (current.row == next.row - 1 && current.col == next.col) {
			current.bottomwall = false;
			next.topwall = false;
		}

		// current is bottom of next
		if (current.row == next.row + 1 && current.col == next.col) {
			current.topwall = false;
			next.bottomwall = false;
		}
	}

	private Cell getNeighbour(Cell curr) {
		ArrayList<Cell> al = new ArrayList<>();
		random = new Random();
		// top neighbour
		if (curr.row > 0 && cell[curr.row - 1][curr.col].visited == false) {
			al.add(cell[curr.row - 1][curr.col]);
		}
		// right neighbour
		if (curr.col < COLS - 1 && cell[curr.row][curr.col + 1].visited == false) {
			al.add(cell[curr.row][curr.col + 1]);
		}
		// bottom neighbour
		if (curr.row < ROWS - 1 && cell[curr.row + 1][curr.col].visited == false) {
			al.add(cell[curr.row + 1][curr.col]);
		}
		// left neighbour
		if (curr.col > 0 && cell[curr.row][curr.col - 1].visited == false) {
			al.add(cell[curr.row][curr.col - 1]);
		}
		if (al.size() > 0) {
			Cell r = al.get(random.nextInt(al.size()));
			return r;
		} else {
			return null;
		}

	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, COLS * SIZE + 2 * SIZE, ROWS * SIZE + 2 * SIZE);
		paintgrid(g2d);
		g2d.setColor(Color.yellow);
		g2d.fillOval(player.x + padding, player.y + padding, ballsize, ballsize);
	}

	private void paintgrid(Graphics2D g2d) {

		g2d.setColor(Color.white);
		g2d.setStroke(new BasicStroke(2));
		for (i = 0; i < COLS; i++) {
			for (j = 0; j < ROWS; j++) {
				if (cell[i][j].topwall) {
					g2d.drawLine(cell[j][i].x, cell[j][i].y, cell[j][i].x + SIZE, cell[j][i].y);
				}
				if (cell[i][j].rightwall) {
					g2d.drawLine(cell[j][i].x + SIZE, cell[j][i].y, cell[j][i].x + SIZE, cell[j][i].y + SIZE);
				}
				if (cell[i][j].bottomwall) {
					g2d.drawLine(cell[j][i].x, cell[j][i].y + SIZE, cell[j][i].x + SIZE, cell[j][i].y + SIZE);
				}
				if (cell[i][j].leftwall) {
					g2d.drawLine(cell[j][i].x, cell[j][i].y, cell[j][i].x, cell[j][i].y + SIZE);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		repaint();
		int key = e.getKeyCode();
		switch (key) {
		case (KeyEvent.VK_DOWN):
			if (cell[player.row][player.col].bottomwall == false) {
				player.y += SIZE;
				player.row += 1;
			}
			break;
		case (KeyEvent.VK_UP):
			if (cell[player.row][player.col].topwall == false) {
				player.y -= SIZE;
				player.row -= 1;
			}
			break;
		case (KeyEvent.VK_RIGHT):
			if (player.row == ROWS - 1 && player.col == COLS - 1) {
				break;
			}
			if (cell[player.row][player.col].rightwall == false) {
				player.x += SIZE;
				player.col += 1;
			}
			break;
		case (KeyEvent.VK_LEFT):
			if (player.row == 0 && player.col == 0) {
				break;
			}
			if (cell[player.row][player.col].leftwall == false) {
				player.x -= SIZE;
				player.col -= 1;
			}
			break;

		default:
			break;
		}
	}

}
