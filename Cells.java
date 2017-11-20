import java.awt.Color;

public class Cells {
	// Main Coder: Jhun Heinrich Domingo

	// variables used in the Cells class
	public EZRectangle[][] cells = new EZRectangle[9][9];
	public EZText[][] cellValue = new EZText[9][9];
	int[][] slot = new int[9][9];
	boolean[][] slotGiven = new boolean[9][9];
	private int positionX;
	private int positionY;

	// Cells constructor
	Cells(int x, int y) {
		positionX = x;
		positionY = y;
		initialize();
	}

	// initializing the cells into 9x9 for the sudoku puzzle
	private void initialize() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				int rowS = (row * 50) + positionX;
				int columnS = (column * 50) + positionY;
				cells[row][column] = EZ.addRectangle(columnS, rowS, 48, 48, Color.WHITE, true);
				cellValue[row][column] = EZ.addText(cells[row][column].getXCenter(), cells[row][column].getYCenter(),
						"", Color.black, 40);
			}
		}
	}

	// changes the visual and actual value of the cell
	public void setSlot(int r, int c, int num, boolean setUp) {
		slot[r][c] = num;
		if (setUp) {
			slotGiven[r][c] = true;
			if (slot[r][c] == 0) {
				slotGiven[r][c] = false;
			}
		}
		update(r, c, num);
	}

	// changes the visual number of the cell
	private void update(int r, int c, int num) {
		String number = "";
		number += num;
		if (num == 0) {
			cellValue[r][c].setMsg("");
		} else {
			cellValue[r][c].setMsg(number);
		}
	}
}
