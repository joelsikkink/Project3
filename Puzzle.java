import java.awt.Color;

public class Puzzle {

	int[][] number = new int[9][9];
	
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
						number[row][column]++;
						if(number[row][column] > 9) {
							number[row][column] = 0;
						}
						puzzle.setSlot(row, column, number[row][column]);
					}
				}
			}
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
