import java.awt.Color;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Initialize the title screen
		int choice = Title.titleScreen();

		// Initializes based on user choice
		if (choice == 1) {
			EZ.initialize(500, 600);
			Puzzle sudoku = new Puzzle(50, 50);
			sudoku.setDark(true); // Graphics under works, Dark Mode enabled for visibility of the cells
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.buttonFunctionA()) {
				sudoku.interaction();
				EZ.refreshScreen();
			}
			while (sudoku.buttonFunctionB()) {
				sudoku.playing();
				EZ.refreshScreen();
			}
		} else if (choice == 2) { // Code under construction
			EZ.initialize(500, 600);
			EZ.addText(250, 300, "PLACEHOLDER FOR SAMURAI", Color.black, 25);
		} else {
		}
	}
}
