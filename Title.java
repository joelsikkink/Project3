import java.awt.Color;

public class Title {
	// Main Coder: Joel Sikkink
	public static EZRectangle regular;
	public static EZRectangle samurai;
	public static EZText regText;
	public static EZText samText;
	public static boolean titleRun = true;
	public static boolean titleRun2 = true;
	public static EZRectangle easy;
	public static EZRectangle medium;
	public static EZRectangle hard;
	public static EZRectangle createYO;
	public static EZText easyText;
	public static EZText mediumText;
	public static EZText hardText;
	public static EZText createYOText1;
	public static EZText createYOText2;
	
	public static int titleScreen() {
		
		//initializes title window
		EZ.initialize(500, 600);
		EZ.setBackgroundColor(Color.WHITE);
		EZ.addText(250, 100, "Sudoku", Color.DARK_GRAY, 130);

		// buttons for regular and samuraieasy.hide();
		
		regular = EZ.addRectangle(250, 300, 350, 150, Color.green, true);
		samurai = EZ.addRectangle(250, 500, 350, 150, Color.pink, true);
		regText = EZ.addText(250, 300, "Regular", Color.black, 80);
		samText = EZ.addText(250, 500, "Samurai", Color.black, 80);
		EZ.addLine(50, 170, 450, 170, Color.black, 15);
		easy = EZ.addRectangle(150, 300, 150, 100, Color.GREEN, true);
		medium = EZ.addRectangle(350, 300, 150, 100, Color.PINK, true);
		hard = EZ.addRectangle(150, 500, 150, 100, Color.RED, true);
		createYO = EZ.addRectangle(350, 500, 150, 100, Color.GRAY, true);
		easyText = EZ.addText(150, 300, "EASY", Color.black, 35);
		mediumText = EZ.addText(350, 300, "MEDIUM", Color.black, 35);
		hardText = EZ.addText(150, 500, "HARD", Color.black, 35);
		createYOText1 = EZ.addText(350, 485, "CREATE YOUR", Color.black, 20);
		createYOText2 = EZ.addText(350, 515, "OWN", Color.black, 20);
		easy.hide();
		medium.hide();
		hard.hide();
		createYO.hide();
		easyText.hide();
		mediumText.hide();
		hardText.hide();
		createYOText1.hide();
		createYOText2.hide();
		//allows user to click buttons to open different modes
		while (titleRun == true) {
			int curX = EZInteraction.getXMouse();
			int curY = EZInteraction.getYMouse();
			if (regular.isPointInElement(curX, curY)) {
				if (EZInteraction.wasMouseLeftButtonReleased()) {
					hideAll();
					showDifficulties();
					while(titleRun2 == true) {
					System.out.print("ez");
					curX = EZInteraction.getXMouse();
					curY = EZInteraction.getYMouse();
					int returnNum = diffButtons(curX, curY);
					if (returnNum > 0) {
						closeTitle();
						return returnNum;
						
						}
					}
				}
			}
			if (samurai.isPointInElement(curX, curY)) {
				if (EZInteraction.wasMouseLeftButtonReleased()) {
					hideAll();
					showDifficulties();
					while(titleRun2 == true) {
					curX = EZInteraction.getXMouse();
					curY = EZInteraction.getYMouse();
					int returnNum = diffButtons(curX, curY);
					if (returnNum > 0) {
						closeTitle();
						return returnNum + 4;
						}
						}
					}
				
				}		
		}
		return 0;
		
		
	}
	public static void hideAll() {
		regular.hide();
		samurai.hide();
		regText.hide();
		samText.hide();
	}
	public static void closeTitle() {
		EZ.closeWindowWithIndex(0);
		titleRun = false;
		titleRun2 = false;
	}
	public static void showDifficulties() {
		easy.show();
		medium.show();
		hard.show();
		createYO.show();
		easyText.show();
		mediumText.show();
		hardText.show();
		createYOText1.show();
		createYOText2.show();
	}
	public static int diffButtons(int curX, int curY) {
		if (easy.isPointInElement(curX, curY)) {
			if (EZInteraction.wasMouseLeftButtonPressed()) {
				return 1;}
		}
		if (medium.isPointInElement(curX, curY)) {
			if (EZInteraction.wasMouseLeftButtonPressed()) {
				return 2;}
		}
		if (hard.isPointInElement(curX, curY)) {
			if (EZInteraction.wasMouseLeftButtonPressed()) {
				return 3;}
		}
		if (createYO.isPointInElement(curX, curY)) {
			if (EZInteraction.wasMouseLeftButtonPressed()) {
				return 4;}
		}
		return 0;
	}
}
