import java.awt.Color;

public class Puzzle {
	
	int cellRow;
	int cellColumn;
	
	Cells puzzle = new Cells();
	EZRectangle button;
	EZText buttonText;
	
	Puzzle(){
		buttonUI();
	}
	
	public void interaction(){
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if(EZInteraction.wasMouseLeftButtonReleased()) {
			for(int row = 0; row < 9; row++) {
				for(int column = 0; column < 9; column++) {
					if(puzzle.cells[row][column].isPointInElement(x,y)) {
						cellRow = row;
						cellColumn = column;
					}
				}
			}
		}
		userInput(cellRow,cellColumn);
	}

	private void userInput(int cellRow, int cellColumn) {
		if(EZInteraction.wasKeyPressed('1')) {
			puzzle.setSlot(cellRow, cellColumn, 1);
		}
		if(EZInteraction.wasKeyPressed('2')) {
			puzzle.setSlot(cellRow, cellColumn, 2);
		}
		if(EZInteraction.wasKeyPressed('3')) {
			puzzle.setSlot(cellRow, cellColumn, 3);
		}
		if(EZInteraction.wasKeyPressed('4')) {
			puzzle.setSlot(cellRow, cellColumn, 4);
		}
		if(EZInteraction.wasKeyPressed('5')) {
			puzzle.setSlot(cellRow, cellColumn, 5);
		}
		if(EZInteraction.wasKeyPressed('6')) {
			puzzle.setSlot(cellRow, cellColumn, 6);
		}
		if(EZInteraction.wasKeyPressed('7')) {
			puzzle.setSlot(cellRow, cellColumn, 7);
		}
		if(EZInteraction.wasKeyPressed('8')) {
			puzzle.setSlot(cellRow, cellColumn, 8);
		}
		if(EZInteraction.wasKeyPressed('9')) {
			puzzle.setSlot(cellRow, cellColumn, 9);
		}
		if(EZInteraction.wasKeyPressed('0')) {
			puzzle.setSlot(cellRow, cellColumn, 0);
		}
	}
	
	public void setDark(boolean trigger) {
		if(trigger) {
			for(int row = 0; row < 9; row++) {
				for(int column = 0; column < 9; column++) {
					puzzle.cells[row][column].setColor(Color.LIGHT_GRAY);
					button.setColor(Color.LIGHT_GRAY);
				}
			}
		}else {
			for(int row = 0; row < 9; row++) {
				for(int column = 0; column < 9; column++) {
					puzzle.cells[row][column].setColor(Color.WHITE);
					button.setColor(Color.WHITE);
				}
			}
		}
	}
	
	private void buttonUI() {
		button = EZ.addRectangle(250, 550, 150, 50, Color.WHITE, true);
		buttonText = EZ.addText(250, 550, "Puzzle Initialized", Color.black, 20);
	}

	public boolean buttonFunction() {
		int x = EZInteraction.getXMouse();
		int y = EZInteraction.getYMouse();
		if(EZInteraction.wasMouseLeftButtonReleased()) {
			if(button.isPointInElement(x, y)) {
				button.hide();
				buttonText.hide();
				return false;
			}
		}
		return true;
	}
}
