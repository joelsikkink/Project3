import java.awt.Color;

public class Puzzle {

	int cellRow;
	int cellColumn;
	int cellPositionX;
	int cellPositionY;
	int pastCellR;
	int pastCellC;

	static Cells puzzle;
	static Answer solution;
	EZRectangle[] button = new EZRectangle[3];
	EZText[] buttonText = new EZText[3];

	Puzzle(int x, int y) {
		cellPositionX = x;
		cellPositionY = y;
		puzzle = new Cells(cellPositionX, cellPositionY);
		buttonUI();
	}

	public void interaction() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if (EZInteraction.wasMouseLeftButtonReleased()) {
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					if (puzzle.cells[row][column].isPointInElement(x, y)) {
						cellRow = row;
						cellColumn = column;
					}
				}
			}
		}
		userInput(cellRow, cellColumn, true);
		highlight(cellRow, cellColumn);
	}
	
	public void playing() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if (EZInteraction.wasMouseLeftButtonReleased()) {
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					if (puzzle.cells[row][column].isPointInElement(x, y)) {
						if (puzzle.slotGiven[row][column] == false) {
							cellRow = row;
							cellColumn = column;
						}
					}
				}
			}
		}
		userInput(cellRow, cellColumn, false);
		highlight(cellRow, cellColumn);
	}

	private void userInput(int cellRow, int cellColumn, boolean setUp) {
		if (EZInteraction.wasKeyPressed('1')) {
			puzzle.setSlot(cellRow, cellColumn, 1, setUp);
		}
		if (EZInteraction.wasKeyPressed('2')) {
			puzzle.setSlot(cellRow, cellColumn, 2, setUp);
		}
		if (EZInteraction.wasKeyPressed('3')) {
			puzzle.setSlot(cellRow, cellColumn, 3, setUp);
		}
		if (EZInteraction.wasKeyPressed('4')) {
			puzzle.setSlot(cellRow, cellColumn, 4, setUp);
		}
		if (EZInteraction.wasKeyPressed('5')) {
			puzzle.setSlot(cellRow, cellColumn, 5, setUp);
		}
		if (EZInteraction.wasKeyPressed('6')) {
			puzzle.setSlot(cellRow, cellColumn, 6, setUp);
		}
		if (EZInteraction.wasKeyPressed('7')) {
			puzzle.setSlot(cellRow, cellColumn, 7, setUp);
		}
		if (EZInteraction.wasKeyPressed('8')) {
			puzzle.setSlot(cellRow, cellColumn, 8, setUp);
		}
		if (EZInteraction.wasKeyPressed('9')) {
			puzzle.setSlot(cellRow, cellColumn, 9, setUp);
		}
		if (EZInteraction.wasKeyPressed('0')) {
			puzzle.setSlot(cellRow, cellColumn, 0, setUp);
		}
	}

	public void setDark(boolean trigger) {
		if (trigger) {
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					puzzle.cells[row][column].setColor(Color.LIGHT_GRAY);
					button[0].setColor(Color.LIGHT_GRAY);
					button[1].setColor(Color.LIGHT_GRAY);
					button[2].setColor(Color.LIGHT_GRAY);

				}
			}
		} else {
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					puzzle.cells[row][column].setColor(Color.WHITE);
					button[0].setColor(Color.WHITE);
					button[1].setColor(Color.WHITE);
					button[2].setColor(Color.WHITE);

				}
			}
		}
	}

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

	public boolean buttonFunctionA() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if (EZInteraction.wasMouseLeftButtonPressed()) {
			if (button[0].isPointInElement(x, y)) {
				solution = new Answer();
				if (solution.solveAnswers() == false) {
					button[0].hide();
					buttonText[0].hide();
					button[1].show();
					buttonText[1].show();
					button[2].show();
					buttonText[2].show();
					return false;
				}
				else if (solution.solveAnswers()) {
					System.out.println("This board is not possible");
					//for some reason not working might be something in the answer code
					return true;
				}
			}
		}
		return true;
	}

	public boolean buttonFunctionB() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if (EZInteraction.wasMouseLeftButtonPressed()) {
			if (button[1].isPointInElement(x, y)) {
				puzzle.cellValue = solution.returnAnswer(true);
			}
			else if (button[2].isPointInElement(x, y)) {
				puzzle.cellValue = solution.returnAnswer(false);
				return false;
			}
		}
		return true;
	}
	public void highlight(int row, int column) {
		puzzle.cells[pastCellR][pastCellC].setColor(Color.lightGray);
		puzzle.cells[row][column].setColor(Color.white);
		pastCellR = row;
		pastCellC = column;
	}
}
