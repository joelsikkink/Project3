import java.awt.Color;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice = Title.titleScreen();
		if (choice == 1) {
			EZ.initialize(500, 600);
			Puzzle sudoku = new Puzzle(50, 50);
			sudoku.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.buttonFunctionA()) {
				sudoku.interaction();
				EZ.refreshScreen();
			}
			while (sudoku.buttonFunctionB()) {
				sudoku.playing();
				EZ.refreshScreen();
				
			}
		}
		else if (choice == 2) {
			EZ.initialize(500, 600);
			

		} else {

		}
	}
}
