
public class Normal extends Puzzle {

	Normal(int x, int y) {
		
		boards = 1;
		cellPositionX = x;
		cellPositionY = y;
		puzzle = new Cells(cellPositionX, cellPositionY, boards);
		buttonUI();
	}
}
