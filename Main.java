import java.awt.Color;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// Initialize the title screen
		int choice = Title.titleScreen();
		System.out.print(choice);
		// Initializes based on user choice

		if (choice == 1) {// Initialize an easy normal board
			EZ.initialize(500, 600);
			Puzzle sudoku = new Normal(50, 50);
			sudoku.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.easyNormalBoard()) {

			}
			EZ.refreshScreen();
			while (sudoku.buttonFunctionB()) {// Play on new board
				sudoku.playing();
				EZ.refreshScreen();
			}
		}

		else if (choice == 2) {// Initialize a medium normal board
			EZ.initialize(500, 600);
			Puzzle sudoku = new Normal(50, 50);
			sudoku.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.mediumNormalBoard()) {
			}
			EZ.refreshScreen();
			while (sudoku.buttonFunctionB()) {// Play on new board
				sudoku.playing();
				EZ.refreshScreen();
			}
		} else if (choice == 3) {// Initialize a hard normal board
			EZ.initialize(500, 600);
			Puzzle sudoku = new Normal(50, 50);
			sudoku.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.hardNormalBoard()) {
			}
			EZ.refreshScreen();
			while (sudoku.buttonFunctionB()) {// Play on new board
				sudoku.playing();
				EZ.refreshScreen();
			}
		} else if (choice == 4) {// Initialize a "Choose Your Own" normal board
			EZ.initialize(500, 600);
			Puzzle sudoku = new Normal(50, 50);
			sudoku.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.buttonFunctionA()) {
				sudoku.interaction();
				EZ.refreshScreen();
			}
			EZ.refreshScreen();
			while (sudoku.buttonFunctionB()) {// Play on new board
				sudoku.playing();
				EZ.refreshScreen();
			}
		} else if (choice == 5) { // Initialize an easy samurai board
			EZ.initialize(900, 950);
			Puzzle samurai = new Samurai(50, 50);
			samurai.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (samurai.samuraiEasyBoard()) {
			}
			while (samurai.buttonFunctionB()) {// Play on new board
				samurai.playing();
				EZ.refreshScreen();
			}
		}

		else if (choice == 6) { // Initialize a medium samurai board
			EZ.initialize(900, 950);
			Puzzle samurai = new Samurai(50, 50);
			samurai.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (samurai.samuraiMediumBoard()) {
			}
			while (samurai.buttonFunctionB()) {// Play on new board
				samurai.playing();
				EZ.refreshScreen();
			}
		} else if (choice == 7) { // Initialize a hard samurai board
			EZ.initialize(900, 950);
			Puzzle samurai = new Samurai(50, 50);
			samurai.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (samurai.samuraiHardBoard()) {
			}
			while (samurai.buttonFunctionB()) {// Play on new board
				samurai.playing();
				EZ.refreshScreen();
			}
		} else if (choice == 8) { // Initialize a "Choose Your Own" samurai board
			EZ.initialize(900, 950);
			Puzzle samurai = new Samurai(50, 50);
			samurai.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (samurai.buttonFunctionA()) {
				samurai.interaction();
				EZ.refreshScreen();
			}
			while (samurai.buttonFunctionB()) {// Play on new board
				samurai.playing();
				EZ.refreshScreen();
			}
		} else {
		}
	}
}
