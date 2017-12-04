import java.awt.Color;

public class Samurai extends Puzzle {

	Samurai(int x, int y) {
		
		boards = 5;
		cellPositionX = x;
		cellPositionY = y;
		puzzle = new Cells(cellPositionX, cellPositionY, boards);
		buttonUI();
	}
	
	Answer Solution() {
		return new AnswerSamurai();
	}
	
	// Easier user experience
	public void buttonUI() {
		button[0] = EZ.addRectangle(450, 925, 150, 50, Color.WHITE, true);
		buttonText[0] = EZ.addText(450, 925, "Puzzle Initialized", Color.BLACK, 20);
		button[1] = EZ.addRectangle(350, 925, 150, 50, Color.WHITE, true);
		buttonText[1] = EZ.addText(350, 925, "Check", Color.BLACK, 20);
		button[1].hide();
		buttonText[1].hide();
		button[2] = EZ.addRectangle(550, 925, 150, 50, Color.WHITE, true);
		buttonText[2] = EZ.addText(550, 925, "Solve", Color.BLACK, 20);
		button[2].hide();
		buttonText[2].hide();
	}
}