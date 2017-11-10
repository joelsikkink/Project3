import java.io.FileReader;
import java.util.Scanner;
import java.awt.Color;

public class Main2 {
	
	//variables
	static int[][] slot = new int[9][9];
	static int[][] answer = new int[9][9];
	static boolean[][] initial = new boolean[9][9]; //this is just to declare if the slot[][] is considered an initial given value
	static EZRectangle[][] clickBox= new EZRectangle[9][9];
	static EZText[][] numBox = new EZText[9][9];
	static EZRectangle button;
	static EZText buttonText;
	
	//Adds initial numbers
	public static EZText IsInitial(int r, int c, int num) {
		int x = clickBox[r][c].getXCenter();
		int y = clickBox[r][c].getYCenter();
		String number = "";
		number += num;
		return EZ.addText(x, y, number, Color.BLACK, 40);
	}
	
	//Changes guess number on click
	public static EZText IsClick(int x, int y, int num) {
		String number = "";
		number += num;
		return EZ.addText(x, y, number, Color.BLUE, 40);
	}
	
	//Reveals answer for every box, marks green if guess is correct, and red if incorrect or blank
	public static EZText revealAnswer(int r, int c, int num, int guess) {
		int x = clickBox[r][c].getXCenter();
		int y = clickBox[r][c].getYCenter();
		String number = "";
		number += num;
		if(guess == num) {
			return EZ.addText(x, y, number, Color.GREEN, 40);
		}
		else {
			return EZ.addText(x, y, number, Color.RED, 40);
		}
	}
	
	//Loads board design
	public static void BoardGraphic() {
		//Border lines
		EZ.addLine(25, 100, 25, 550, Color.BLACK);
		EZ.addLine(475, 100, 475, 550, Color.BLACK);
		EZ.addLine(25, 100, 475, 100, Color.BLACK);
		EZ.addLine(25, 550, 475, 550, Color.BLACK);
		
		//Inside lines, rectangles being the thicker lines
		EZ.addLine(75, 100, 75, 550, Color.BLACK);
		EZ.addLine(125, 100, 125, 550, Color.BLACK);
		EZ.addRectangle(175, 325, 3, 450, Color.BLACK, true);
		EZ.addLine(225, 100, 225, 550, Color.BLACK);
		EZ.addLine(275, 100, 275, 550, Color.BLACK);
		EZ.addRectangle(325, 325, 3, 450, Color.BLACK, true);
		EZ.addLine(375, 100, 375, 550, Color.BLACK);
		EZ.addLine(425, 100, 425, 550, Color.BLACK);
		EZ.addLine(25, 150, 475, 150, Color.BLACK);
		EZ.addLine(25, 200, 475, 200, Color.BLACK);
		EZ.addRectangle(250, 250, 450, 3, Color.BLACK, true);
		EZ.addLine(25, 300, 475, 300, Color.BLACK);
		EZ.addLine(25, 350, 475, 350, Color.BLACK);
		EZ.addRectangle(250, 400, 450, 3, Color.BLACK, true);
		EZ.addLine(25, 450, 475, 450, Color.BLACK);
		EZ.addLine(25, 500, 475, 500, Color.BLACK);
		
		//adds the boxes to click to change numbers
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				int rowS = (row*50) + 125;
				int columnS = (column*50) + 50;
				clickBox[row][column] = EZ.addRectangle(columnS, rowS, 48, 48, Color.WHITE, true);
			}
		}
	}
	
	//adds a UI button to check the answers
	public static void UIButton() {
		button = EZ.addRectangle(300, 600, 150, 50, Color.WHITE, true);
		EZText buttonText = EZ.addText(300, 600, "Check", Color.BLACK, 40);
	}
	
	//function for the solver algorithm and assigns it to answer[][] array
	public static void CheckAnswer() {
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				if(initial[r][c] == false) {
					int num = 0;
					boolean dupeNum = true;
					do {
						num++;
						if(num == 10) {
							boolean aboveTen;
							do {
								aboveTen = false;
								int subC = 0;
								int subR = 0;
								do {
									subC++;
									if(c - subC < 0) {
										subC = subC - 9;
										subR++;
									}
								} while(initial[r - subR][c - subC]);
								r = r - subR;
								c = c - subC;
								num = answer[r][c] + 1;
								answer[r][c] = 0;
								if(num > 9) {
									 aboveTen = true;
								}
							} while(aboveTen);
						}
						
						int s = ((r*9) + c);
						int[] cellCheck = new int[9];
						if(s==0||s==1||s==2||s==9||s==10||s==11||s==18||s==19||s==20) {
							int[] cells = {0, 1, 2, 9, 10, 11, 18, 19, 20} ; //using this to make an array of cells
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
							//since declared array cells is out of scope outside this if statement and
							//also because constants can only be added when initializing the array
							//this for() loop is to transfer to an array that can access outside of this scope
						}
						else if(s==3||s==4||s==5||s==12||s==13||s==14||s==21||s==22||s==23) {
							int[] cells = {3, 4, 5, 12, 13, 14, 21, 22, 23} ;
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
						}
						else if(s==6||s==7||s==8||s==15||s==16||s==17||s==24||s==25||s==26) {
							int[] cells = {6, 7, 8, 15, 16, 17, 24, 25, 26} ;
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
						}
						else if(s==27||s==28||s==29||s==36||s==37||s==38||s==45||s==46||s==47) {
							int[] cells = {27, 28, 29, 36, 37, 38, 45, 46, 47} ;
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
						}
						else if(s==30||s==31||s==32||s==39||s==40||s==41||s==48||s==49||s==50) {
							int[] cells = {30, 31, 32, 39, 40, 41, 48, 49, 50} ;
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
						}
						else if(s==33||s==34||s==35||s==42||s==43||s==44||s==51||s==52||s==53) {
							int[] cells = {33, 34, 35, 42, 43, 44, 51, 52, 53} ;
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
						}
						else if(s==54||s==55||s==56||s==63||s==64||s==65||s==72||s==73||s==74) {
							int[] cells = {54, 55, 56, 63, 64, 65, 72, 73, 74} ;
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
						}
						else if(s==57||s==58||s==59||s==66||s==67||s==68||s==75||s==76||s==77) {
							int[] cells = {57, 58, 59, 66, 67, 68, 75, 76, 77} ;
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
						}
						else if(s==60||s==61||s==62||s==69||s==70||s==71||s==78||s==79||s==80) {
							int[] cells  = {60, 61, 62, 69, 70, 71, 78, 79, 80} ;
							for(int n = 0; n < 9; n++) {
								cellCheck[n] = cells[n];
							}
						}
						for(int n = 0; n < 9; n++) { //Checks the 3x3 cells for duplicates
							int rCheck = cellCheck[n] / 9; //assigns them back into the r and c format
							int cCheck = cellCheck[n] % 9;
							if(answer[rCheck][cCheck] == num) {
								dupeNum = true;
								break;
							}
							else {
								dupeNum = false;
							}
						}
						
						if(dupeNum == false) { //Only pass through if pass the 3x3 cell check
							for(int rowCheck = -8; rowCheck < 9; rowCheck++) { //Checks if different rows in the same column has duplicates
								if(r + rowCheck >= 0 && r + rowCheck < 9) {
									if(answer[r + rowCheck][c] == num) {
										dupeNum = true;
										break;
									}
									else {
										dupeNum = false;
									}
								}
							}
						}
						
						if(dupeNum == false) { //only pass through if pass the 3x3 cell check and different rows check
							for(int clmCheck = -8; clmCheck < 9; clmCheck++) { //Checks if different columns in the same row has duplicates
								if(c + clmCheck >= 0 && c + clmCheck < 9) {
									if(answer[r][c + clmCheck] == num) {
										dupeNum = true;
										break;
									}
									else {
										dupeNum = false;
									}
								}
							}
						}
					} while(dupeNum);
					
					answer[r][c] = num;
				}
			}
		}
	}
	
	public static void main(String[] args) throws java.io.IOException {
		
		Scanner scan = new Scanner(new FileReader("SudokuPuzzle"));
		
		
		//scanning the file and all that.
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				int number = scan.nextInt();
				switch(number) {
				case 0:
					initial[row][column] = false;
					break;
				default:
					slot[row][column] = number;
					answer[row][column] = number;
					initial[row][column] = true;
					break;
				}
			}
		}
		scan.close();
		
		//loads the screen and buttons
		EZ.initialize(600,650);
		EZ.setBackgroundColor(new Color(225,225,255));
		BoardGraphic();
		UIButton();
		
		//sets the initial values from the scan
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				if(initial[row][column]) {
					numBox[row][column] = IsInitial(row, column, answer[row][column]);
				}
			}
		}
		
/*		//prints the board
		for(int row = 0; row < 9; row++) {
			System.out.print("|");
			for(int column = 0; column < 9; column++) {
					System.out.print(slot[row][column] + "|");
			}
			System.out.println();
		}
*/
		
		//game loop
		while(true) {
			int x = EZInteraction.getXMouse();
			int y = EZInteraction.getYMouse();
			if(EZInteraction.wasMouseLeftButtonPressed()) {
				for(int row = 0; row < 9; row++) {
					for(int column = 0; column < 9; column++) {
						if(clickBox[row][column].isPointInElement(x,y)) {
							int xBox = clickBox[row][column].getXCenter();
							int yBox = clickBox[row][column].getYCenter();
							if(initial[row][column] == false) {
								if(slot[row][column] != 0) {
									numBox[row][column].hide();
								}
								slot[row][column]++;
								if (slot[row][column] > 9) {
									slot[row][column] = 0;
									numBox[row][column].hide();
								}
								else {
									numBox[row][column] = IsClick(xBox, yBox, slot[row][column]);
								}
							}
						}
					}
				}
				//if UI button pressed end the game
				if(button.isPointInElement(x, y)) {
					break;
				}
			}
			EZ.refreshScreen();
		}
		
		//runs the function that finds the correct answer
		CheckAnswer();
		
		//prints out the correct answer, green if guess correct/red if incorrect or blank
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				if(initial[row][column] == false) {
					if(slot[row][column] != 0) {
						numBox[row][column].hide();
					}
					numBox[row][column] = revealAnswer(row, column, answer[row][column], slot[row][column]);
				}
			}
		}

/*		//prints the results
		for(int row = 0; row < 9; row++) {
			System.out.print("|");
			for(int column = 0; column < 9; column++) {
					System.out.print(slot[row][column] + "|");
			}
			System.out.println();
		} */
		
	}
}
