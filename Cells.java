import java.awt.Color;

public class Cells {
	// Main Coder: Jhun Heinrich Domingo

	// Variables used in the Cells class
	public EZRectangle[][][] cells;
	public EZText[][][] cellValue;
	int[][][] slot;
	boolean[][][] slotGiven;
	private int boards;
	private int positionX;
	private int positionY;

	// Cells constructor
	Cells(int x, int y, int b) {
		boards = b;
		cells = new EZRectangle[9][9][boards];
		cellValue = new EZText[9][9][boards];
		slot = new int[9][9][boards];
		slotGiven = new boolean[9][9][boards];
		positionX = x;
		positionY = y;
		if (boards == 1) {
			initialize();
		} else if (boards == 5) {
			initializeSam();
		}
	}

	// Initializing the cells into 9x9 for the sudoku puzzle
	private void initialize() {
		for (int board = 0; board < boards; board++) {
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					int rowS = (row * 50) + positionX;
					int columnS = (column * 50) + positionY;
					cells[row][column][board] = EZ.addRectangle(columnS, rowS, 48, 48, Color.WHITE, true);
					cellValue[row][column][board] = EZ.addText(cells[row][column][board].getXCenter(),
							cells[row][column][board].getYCenter(), "", Color.black, 40);
				}
			}
		}
	}

	// Initializing the cells into 5 sudoku puzzles for the samurai puzzle
	private void initializeSam() {
		for (int board = 0; board < boards; board++) {
			int posX = positionX;
			int posY = positionY;
			switch (board) {
			case (1):
				posX += 480;
				break;
			case (2):
				posY += 480;
				break;
			case (3):
				posX += 480;
				posY += 480;
				break;
			case (4):
				posX += 240;
				posY += 240;
				break;
			default:
				break;
			}
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					int rowS = (row * 40) + posY;
					int columnS = (column * 40) + posX;
					if (board == 4) {
						if ((column >= 3 && column < 6) || (row >= 3 && row < 6)) {
							cells[row][column][board] = EZ.addRectangle(columnS, rowS, 38, 38, Color.WHITE, true);
							cellValue[row][column][board] = EZ.addText(cells[row][column][board].getXCenter(),
									cells[row][column][board].getYCenter(), "", Color.black, 35);
						} else {
							cells[row][column][board] = EZ.addRectangle(-50, -50, 38, 38, Color.WHITE, true); // position
																												// unwanted
																												// cells
																												// off
																												// screen
																												// to
																												// prevent
																												// null
																												// pointer
																												// exception
																												// errors
							cellValue[row][column][board] = EZ.addText(cells[row][column][board].getXCenter(),
									cells[row][column][board].getYCenter(), "", Color.black, 35);
						}
					} else {
						cells[row][column][board] = EZ.addRectangle(columnS, rowS, 38, 38, Color.WHITE, true);
						cellValue[row][column][board] = EZ.addText(cells[row][column][board].getXCenter(),
								cells[row][column][board].getYCenter(), "", Color.black, 35);
					}
				}
			}
		}
	}

	// Changes the visual and actual value of the cell
	public void setSlot(int r, int c, int b, int num, boolean setUp) {
		slot[r][c][b] = num;
		if (setUp) {
			slotGiven[r][c][b] = true;
			if (slot[r][c][b] == 0) {
				slotGiven[r][c][b] = false;
			}
		}
		update(r, c, b, num);
	}

	// Changes the visual number of the cell
	private void update(int r, int c, int b, int num) {
		String number = "";
		number += num;
		if (num == 0) {
			cellValue[r][c][b].setMsg("");
		} else {
			cellValue[r][c][b].setMsg(number);
		}
	}
}
