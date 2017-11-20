import java.awt.Color;

public class Answer {
	// Main Coder: Kelsey Kobayashi
	
	public static int[][] values = new int[9][9];
	public static boolean[][] given = new boolean[9][9];
	
	
	public Answer() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				values[r][c] = Puzzle.puzzle.slot[r][c];
				given[r][c] = Puzzle.puzzle.slotGiven[r][c];
			}
		}
	}
	
	public boolean solveAnswers() {
		boolean impossibleBoard = false;
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (given[r][c] == false) {
					int num = 0;
					boolean dupeNum = true;
					do {
						/*the way this algorithm works is it tries every number in order trying the next possible number
						*if it runs out of possible numbers to use, it rewinds to a previous cell trying the next possible number there
						*and rewinds even more if those cells run out of possible numbers
						*/
						num++;
						if (num == 10) {
							boolean rewind; //boolean to check if to continue rewinding or not if the previous value is
							do {
								rewind = false;
								int subC = 0; //value for how many columns to rewind
								int subR = 0; //value for how many rows to rewind
								do {
									subC++;
									if (c - subC < 0) {
										//if the array is going to be out of bounds, shift back a row and have column go back to rightmost column
										subC = subC - 9;
										subR++;
									}
									if (r - subR < 0) {
										//if the array for the rows is going to be out of bounds, then this board is not possible
										//TODO somehow this is might not be returning properly
										impossibleBoard = true;
										return impossibleBoard;
									}
								} while(given[r - subR][c - subC]); //keep going back if the cell contains a given number
								c = c - subC; //rewinds the column for loop
								r = r - subR; //rewinds the row for loop
								num = values[r][c] + 1;
								//sets the number back to the previous cell number but +1 since the num+1 is earlier in the loop
								values[r][c] = 0;
								//so it doesn't screw up the checker with an incorrect value, it resets the value back to 0 if it rewinds
								if (num > 9) {
									//if the previous cell value +1 ends up being out of the 1-9 range, continue the rewind loop
									rewind = true;
								}
							} while(rewind);
						}
						
						// This is the where it starts checking for duplicate numbers
						
						dupeNum = checkBlock(r, c, num);
						
						if(dupeNum == false) { //Only pass through if pass the 3x3 block check
							dupeNum = checkRow(r, c, num);
						}
						
						if(dupeNum == false) { //Only pass through if pass the 3x3 block check and row check
							dupeNum = checkColumn(r, c, num);
						}
					} while(dupeNum);
					//If it kept the false boolean for the duplicate number, it leaves this loop
					//If it had a duplicate number at any point, it continues in the loop with the next number
					
					values[r][c] = num; //assigns the 
				}
				
			}
		}
		return impossibleBoard; //this is to tell the other class that the board is possible
	}

	public boolean checkBlock(int row, int col, int num) {
		//checks the 3x3 cell blocks for any duplicate numbers
		int[] cells = whatBlock(row, col);
		for (int i = 0; i < 9; i++) {
			int cellRow = cells[i] / 9; //changes the cell number to row number
			int cellCol = cells[i] % 9; //changes the cell number to column number
			if (values[cellRow][cellCol] == num) { //if it finds a duplicate number it immediately returns true
				return true;
			}
		}
		//if it did not find a duplicate number it returns false
		return false;
	}
	
	public int[] whatBlock(int row, int col) { 
		/* returns the cell number based on the positions of the cells
		 * it counts across the row then continues the count on the next row where i can use
		 * cell-number divided by 9 for the row number and cell-number modulo by 9 for the column number */ 
		if (row >= 0 && row < 3 && col >=0 && col < 3) { //the top-left 3x3 block of cells
			int[] cells = {0, 1, 2, 9, 10, 11, 18, 19, 20};
			return cells;
		}
		else if (row >= 0 && row < 3 && col >= 3 && col < 6) { //the top-center 3x3 block
			int[] cells = {3, 4, 5, 12, 13, 14, 21, 22, 23};
			return cells;
		}
		else if (row >= 0 && row < 3 && col >= 6 && col < 9) { //the top-right 3x3 block
			int[] cells = {6, 7, 8, 15, 16, 17, 24, 25, 26};
			return cells;
		}
		else if (row >= 3 && row < 6 && col >= 0 && col < 3) { //the mid-left 3x3 block
			int[] cells = {27, 28, 29, 36, 37, 38, 45, 46, 47};
			return cells;
		}
		else if (row >= 3 && row < 6 && col >= 3 && col < 6) { //the middle 3x3 block
			int[] cells = {30, 31, 32, 39, 40, 41, 48, 49, 50};
			return cells;
		}
		else if (row >= 3 && row < 6 && col >= 6 && col < 9) { //the mid-right 3x3 block
			int[] cells = {33, 34, 35, 42, 43, 44, 51, 52, 53};
			return cells;
		}
		else if (row >= 6 && row < 9 && col >= 0 && col < 3) { //the bottom-left 3x3 block
			int[] cells = {54, 55, 56, 63, 64, 65, 72, 73, 74};
			return cells;
		}
		else if (row >= 6 && row < 9 && col >= 3 && col < 6) { //the bottom-mid 3x3 block
			int[] cells = {57, 58, 59, 66, 67, 68, 75, 76, 77};
			return cells;
		}
		else { //the bottom-right 3x3 block (as just an else statement so the function doesn't yell at me)
			int[] cells  = {60, 61, 62, 69, 70, 71, 78, 79, 80};
			return cells;
		}
	}
	
	public boolean checkRow(int row, int col, int num) {
		//Checks the different rows in the same column for duplicates
		for (int rowCheck = -8; rowCheck < 9; rowCheck++) {
			//from -8 so the last row can check the first row
			//to 8 so the first row can check the last row
			if (row + rowCheck >=0 && row + rowCheck < 9) { //this checks the make sure the array is in bounds of 0 to 8
				if (values[row + rowCheck][col] == num) { //if it finds a duplicate number it immediately returns true
					return true;
				}
			}
		}
		//if it did not find a duplicate number it returns false
		return false;
	}
	
	public boolean checkColumn(int row, int col, int num) {
		//Checks the different columns in the same row for duplicates
		for (int colCheck = -8; colCheck < 9; colCheck++) {
			//from -8 so the last column can check the first column
			//to 8 so the first column can check the last column
			if (col + colCheck >=0 && col + colCheck < 9) { //this checks the make sure the array is in bounds of 0 to 8
				if (values[row][col + colCheck] == num) { //if it finds a duplicate number it immediately returns true
					return true;
				}
			}
		}
		//if it did not find a duplicate number it returns false
		return false;
	}
	
	public EZText[][] returnAnswer(boolean checkGuess) {
		//changes font colors if their guess is correct or incorrect
		int[][] guess = new int[9][9];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				guess[r][c] = Puzzle.puzzle.slot[r][c];
				if (Puzzle.puzzle.slotGiven[r][c] == false) {
					if (checkGuess == true) {
						//if the user wishes to check just their guesses
						if (guess[r][c] == 0) {
							//does nothing if no guess
						}
						else if (guess[r][c] == values[r][c]) {
							//shows numbers in green if correct
							Puzzle.puzzle.cellValue[r][c].setColor(Color.green);
						}
						else {
							//shows numbers in red if incorrect
							Puzzle.puzzle.cellValue[r][c].setColor(Color.red);
						}
					}
					else if (checkGuess == false) {
						//if the user wants to end the game
						String answer = "";
						answer += values[r][c];
						if (guess[r][c] == values[r][c]) {
							//shows numbers in green if correct
							Puzzle.puzzle.cellValue[r][c].setColor(Color.green);
						}
						else {
							//shows numbers in red if incorrect and shows the correct number
							Puzzle.puzzle.cellValue[r][c].setColor(Color.red);
							Puzzle.puzzle.cellValue[r][c].setMsg(answer);
						}
					}
				}
			}
		}
		//Test print function
//		printBoard(Puzzle.puzzle.slot);
		
		//returns the changes to the font
		return Puzzle.puzzle.cellValue;
	}	
	
/*	public void printBoard(int[][] value) {
	//prints the board as a test of getting the correct values
		for(int row = 0; row < 9; row++) {
			System.out.print("|");
			for(int column = 0; column < 9; column++) {
					System.out.print(value[row][column] + "|");
			}
			System.out.println();
		}
	} */
}
