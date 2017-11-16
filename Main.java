import java.awt.Color;


public class Main {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		Puzzle sudoku = new Puzzle();
		sudoku.setDark(true);
		EZ.setBackgroundColor(Color.darkGray);
		while(sudoku.buttonFunctionA()) {
			sudoku.interaction();
			EZ.refreshScreen();
		}
		while(sudoku.buttonFunctionB()) {
			EZ.refreshScreen();
		}
	}
}
