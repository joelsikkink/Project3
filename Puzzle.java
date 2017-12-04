import java.awt.Color;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

public class Puzzle {
	// Main Coder: Jhun Heinrich Domingo

	// variables used in the Puzzle class
	int cellRow;
	int cellColumn;
	int cellBoard;
	int cellPositionX;
	int cellPositionY;
	int pastCellR;
	int pastCellC;
	int pastCellB;

	int boards;
	static Cells puzzle;
	static Answer solution;
	EZRectangle[] button = new EZRectangle[3];
	EZText[] buttonText = new EZText[3];

	Answer Solution() {
		return new AnswerSudoku();
	}

	// Interactive elements of the puzzle
	public void interaction() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if (EZInteraction.wasMouseLeftButtonReleased()) {
			for (int board = 0; board < boards; board++) {
				for (int row = 0; row < 9; row++) {
					for (int column = 0; column < 9; column++) {
						if (puzzle.cells[row][column][board].isPointInElement(x, y)) {
							cellRow = row;
							cellColumn = column;
							cellBoard = board;
						}
					}
				}
			}
		}
		userInput(cellRow, cellColumn, cellBoard, true);
		highlight(cellRow, cellColumn, cellBoard);
	}

	// Changes the values of the cells through user input
	public void playing() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if (EZInteraction.wasMouseLeftButtonReleased()) {
			for (int board = 0; board < boards; board++) {
				for (int row = 0; row < 9; row++) {
					for (int column = 0; column < 9; column++) {
						if (puzzle.cells[row][column][board].isPointInElement(x, y)) {
							if (puzzle.slotGiven[row][column][board] == false) {
								cellRow = row;
								cellColumn = column;
								cellBoard = board;
							}
						}
					}
				}
			}
		}
		userInput(cellRow, cellColumn, cellBoard, false);
		highlight(cellRow, cellColumn, cellBoard);
	}

	// Uses the setSlot function of the Cells class to change the cells value
	private void userInput(int cellRow, int cellColumn, int cellBoard, boolean setUp) {
		if (EZInteraction.wasKeyPressed('1')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 1, setUp);
		}
		if (EZInteraction.wasKeyPressed('2')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 2, setUp);
		}
		if (EZInteraction.wasKeyPressed('3')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 3, setUp);
		}
		if (EZInteraction.wasKeyPressed('4')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 4, setUp);
		}
		if (EZInteraction.wasKeyPressed('5')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 5, setUp);
		}
		if (EZInteraction.wasKeyPressed('6')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 6, setUp);
		}
		if (EZInteraction.wasKeyPressed('7')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 7, setUp);
		}
		if (EZInteraction.wasKeyPressed('8')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 8, setUp);
		}
		if (EZInteraction.wasKeyPressed('9')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 9, setUp);
		}
		if (EZInteraction.wasKeyPressed('0')) {
			puzzle.setSlot(cellRow, cellColumn, cellBoard, 0, setUp);
		}
	}

	// Dark mode (graphics not finalized)
	public void setDark(boolean trigger) {
		if (trigger) {
			for (int board = 0; board < boards; board++) {
				for (int row = 0; row < 9; row++) {
					for (int column = 0; column < 9; column++) {
						puzzle.cells[row][column][board].setColor(Color.LIGHT_GRAY);
						button[0].setColor(Color.LIGHT_GRAY);
						button[1].setColor(Color.LIGHT_GRAY);
						button[2].setColor(Color.LIGHT_GRAY);

					}
				}
			}
		} else {
			for (int board = 0; board < boards; board++) {
				for (int row = 0; row < 9; row++) {
					for (int column = 0; column < 9; column++) {
						puzzle.cells[row][column][board].setColor(Color.WHITE);
						button[0].setColor(Color.WHITE);
						button[1].setColor(Color.WHITE);
						button[2].setColor(Color.WHITE);

					}
				}
			}
		}
	}

	// Easier user experience
	public void buttonUI() {
		button[0] = EZ.addRectangle(250, 550, 150, 50, Color.WHITE, true);
		buttonText[0] = EZ.addText(250, 550, "Puzzle Initialized", Color.BLACK, 20);
		button[1] = EZ.addRectangle(150, 550, 150, 50, Color.WHITE, true);
		buttonText[1] = EZ.addText(150, 550, "Check", Color.BLACK, 20);
		button[1].hide();
		buttonText[1].hide();
		button[2] = EZ.addRectangle(350, 550, 150, 50, Color.WHITE, true);
		buttonText[2] = EZ.addText(350, 550, "Solve", Color.BLACK, 20);
		button[2].hide();
		buttonText[2].hide();
	}

	// Function for the buttons
	public boolean buttonFunctionA() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if (EZInteraction.wasMouseLeftButtonReleased()) {
			if (button[0].isPointInElement(x, y)) {
				solution = Solution();
				boolean impossible = solution.checkBoard();
				if (impossible == false) {
					button[0].hide();
					buttonText[0].hide();
					button[1].show();
					buttonText[1].show();
					button[2].show();
					buttonText[2].show();
					return false;
				} else if (impossible == true) {
					System.out.println("This board is not possible");
					return true;
				}
			}
		}
		return true;
	}

	// More functions of the buttons
	public boolean buttonFunctionB() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if (EZInteraction.wasMouseLeftButtonReleased()) {
			if (button[1].isPointInElement(x, y)) {
				puzzle.cellValue = solution.returnAnswer(true);
			} else if (button[2].isPointInElement(x, y)) {
				puzzle.cellValue = solution.returnAnswer(false);
				return false;
			}
		}
		return true;
	}

	// Cleaner visual experience(Highlights Selected Cell)
	public void highlight(int row, int column, int board) {
		puzzle.cells[pastCellR][pastCellC][pastCellB].setColor(Color.lightGray);
		puzzle.cells[row][column][board].setColor(Color.white);
		pastCellR = row;
		pastCellC = column;
		pastCellB = board;
	}

	// Creates an easy level pre-made board
	public boolean easyNormalBoard() throws java.io.IOException {
		Scanner scan = new Scanner(new FileReader("easyNormalBoard.txt"));
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				int number = scan.nextInt();
				System.out.print(number);
				puzzle.setSlot(row, column, 0, number, true);
			}
		}
		scan.close();
		boolean response = checkPreMadeBoard();
		return response;
	}

	// Creates a medium level pre-made board
	public boolean mediumNormalBoard() throws java.io.IOException {
		Scanner scan = new Scanner(new FileReader("mediumNormalBoard.txt"));
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				int number = scan.nextInt();
				System.out.print(number);
				puzzle.setSlot(row, column, 0, number, true);
			}
		}
		scan.close();
		boolean response = checkPreMadeBoard();
		return response;
	}

	// Creates a hard level pre-made board
	public boolean hardNormalBoard() throws java.io.IOException {
		Scanner scan = new Scanner(new FileReader("hardNormalBoard.txt"));
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				int number = scan.nextInt();
				System.out.print(number);
				puzzle.setSlot(row, column, 0, number, true);
			}
		}
		scan.close();
		boolean response = checkPreMadeBoard();
		return response;
	}

	// Creates an easy samurai level pre-made board
	public boolean samuraiEasyBoard() throws java.io.IOException {
		Scanner scan = new Scanner(new FileReader("samuraiEasyBoard.txt"));
		for (int board = 0; board < 5; board++) {
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					int number = scan.nextInt();
					System.out.print(number);
					puzzle.setSlot(row, column, board, number, true);
				}
			}
		}
		scan.close();
		boolean response = checkPreMadeBoard();
		return response;
	}

	// Creates a medium samurai level pre-made board
	public boolean samuraiMediumBoard() throws java.io.IOException {
		Scanner scan = new Scanner(new FileReader("samuraiMediumBoard.txt"));
		for (int board = 0; board < 5; board++) {
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					int number = scan.nextInt();
					System.out.print(number);
					puzzle.setSlot(row, column, board, number, true);
				}
			}
		}
		scan.close();
		boolean response = checkPreMadeBoard();
		return response;
	}

	// Creates a hard samurai level pre-made board
	public boolean samuraiHardBoard() throws java.io.IOException {
		Scanner scan = new Scanner(new FileReader("samuraiHardBoard.txt"));
		for (int board = 0; board < 5; board++) {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				int number = scan.nextInt();
				System.out.print(number);
				puzzle.setSlot(row, column, board, number, true);
			}
		}
		}
		scan.close();
		boolean response = checkPreMadeBoard();
		return response;
	}

	public boolean checkPreMadeBoard() {
		solution = Solution();
		boolean impossible = solution.checkBoard();
		if (impossible == false) {
			button[0].hide();
			buttonText[0].hide();
			button[1].show();
			buttonText[1].show();
			button[2].show();
			buttonText[2].show();
			return false;
		} else if (impossible == true) {
			System.out.println("This board is not possible");
			return true;
		}
		return true;
	}
}