import java.awt.Color;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// Initialize the title screen
		int choice = Title.titleScreen();
		System.out.print(choice);
		// Initializes based on user choice
		if (choice == 1) {
			EZ.initialize(500, 600);
			Puzzle sudoku = new Normal(50, 50);
			sudoku.setDark(true); // Graphics under works, Dark Mode enabled for visibility of the cells
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.easyNormalBoard()) {
				
			}
			EZ.refreshScreen();
			while (sudoku.buttonFunctionB()) {
				sudoku.playing();
				EZ.refreshScreen();
			}
		} 
		else if (choice == 2) {
			EZ.initialize(500, 600);
			Puzzle sudoku = new Normal(50, 50);
			sudoku.setDark(true); // Graphics under works, Dark Mode enabled for visibility of the cells
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.mediumNormalBoard()) {
			}
			EZ.refreshScreen();
			while (sudoku.buttonFunctionB()) {
				sudoku.playing();
				EZ.refreshScreen();
			}
		}
		else if (choice == 3) {
			EZ.initialize(500, 600);
			Puzzle sudoku = new Normal(50, 50);
			sudoku.setDark(true); // Graphics under works, Dark Mode enabled for visibility of the cells
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.hardNormalBoard()) {
			}
			EZ.refreshScreen();
			while (sudoku.buttonFunctionB()) {
				sudoku.playing();
				EZ.refreshScreen();
			}
		}
		else if (choice == 4) {
			EZ.initialize(500, 600);
			Puzzle sudoku = new Normal(50, 50);
			sudoku.setDark(true); // Graphics under works, Dark Mode enabled for visibility of the cells
			EZ.setBackgroundColor(Color.darkGray);
			while (sudoku.buttonFunctionA()) {
				sudoku.interaction();
				EZ.refreshScreen();
			}
			EZ.refreshScreen();
			while (sudoku.buttonFunctionB()) {
				sudoku.playing();
				EZ.refreshScreen();
			}
		}
		else if (choice == 5) { // Code under construction
			EZ.initialize(900, 950);
			Puzzle samurai = new Samurai(50, 50);
			samurai.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (samurai.samuraiEasyBoard()) {
			}
			while (samurai.buttonFunctionB()) {
				samurai.playing();
				EZ.refreshScreen();
			}
		}
		
		else if (choice == 6) { // Code under construction
			EZ.initialize(900, 950);
			Puzzle samurai = new Samurai(50, 50);
			samurai.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (samurai.samuraiMediumBoard()) {
			}
			while (samurai.buttonFunctionB()) {
				samurai.playing();
				EZ.refreshScreen();
			}
		}
		else if (choice == 7) { // Code under construction
			EZ.initialize(900, 950);
			Puzzle samurai = new Samurai(50, 50);
			samurai.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (samurai.samuraiHardBoard()) {
			}
			while (samurai.buttonFunctionB()) {
				samurai.playing();
				EZ.refreshScreen();
			}
		}
		else if (choice == 8) { // Code under construction
			EZ.initialize(900, 950);
			Puzzle samurai = new Samurai(50, 50);
			samurai.setDark(true);
			EZ.setBackgroundColor(Color.darkGray);
			while (samurai.buttonFunctionA()) {
				samurai.interaction();
				EZ.refreshScreen();
			}
			while (samurai.buttonFunctionB()) {
				samurai.playing();
				EZ.refreshScreen();
			}
		} else {
		}
	}
}
