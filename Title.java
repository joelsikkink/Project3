import java.awt.Color;

public class Title {
	public static int titleScreen() {
		EZ.initialize(500, 600);
		EZ.setBackgroundColor(Color.WHITE);
		EZ.addText(250, 100, "Sudoku", Color.DARK_GRAY, 130);
		// buttons for regular and samurai
		EZRectangle regular = EZ.addRectangle(250, 300, 350, 150, Color.green, true);
		EZRectangle samurai = EZ.addRectangle(250, 500, 350, 150, Color.pink, true);
		EZ.addText(250, 300, "Regular", Color.black, 80);
		EZ.addText(250, 500, "Samurai", Color.black, 80);
		boolean titleRun = true;
		while (titleRun == true) {
			int curX = EZInteraction.getXMouse();
			int curY = EZInteraction.getYMouse();
			if (regular.isPointInElement(curX, curY)) {
			if (EZInteraction.wasMouseLeftButtonPressed()) {
				EZ.closeWindowWithIndex(0);
				titleRun = false;
				return 1;
			}
			}
			if (samurai.isPointInElement(curX, curY)) {
			if (EZInteraction.wasMouseLeftButtonPressed()) {
				EZ.closeWindowWithIndex(0);
				titleRun = false;
				return 2;

			}
			}
		}
		return 0;
	}
}