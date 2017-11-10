import java.awt.Color;
import java.util.Scanner;
import java.io.FileReader;

public class MainWithEZImage {
	
/*	public static int[] CellCheck(int s) {
		if(s==0||s==1||s==2||s==9||s==10||s==11||s==18||s==19||s==20) {
			int r[] = {0, 1, 2, 9, 10, 11, 18, 19, 20} ;
			return r;
		}
		else if(s==3||s==4||s==5||s==12||s==13||s==14||s==21||s==22||s==23) {
			int r[] = {3, 4, 5, 12, 13, 14, 21, 22, 23} ;
			return r;
		}
		else if(s==6||s==7||s==8||s==15||s==16||s==17||s==24||s==25||s==26) {
			int r[] = {6, 7, 8, 15, 16, 17, 24, 25, 26} ;
			return r;
		}
		else if(s==27||s==28||s==29||s==36||s==37||s==38||s==45||s==46||s==47) {
			int r[] = {27, 28, 29, 36, 37, 38, 45, 46, 47} ;
			return r;
		}
		else if(s==30||s==31||s==32||s==39||s==40||s==41||s==48||s==49||s==50) {
			int r[] = {30, 31, 32, 39, 40, 41, 48, 49, 50} ;
			return r;
		}
		else if(s==33||s==34||s==35||s==42||s==43||s==44||s==51||s==52||s==53) {
			int r[] = {33, 34, 35, 42, 43, 44, 51, 52, 53} ;
			return r;
		}
		else if(s==54||s==55||s==56||s==63||s==64||s==65||s==72||s==73||s==74) {
			int r[] = {54, 55, 56, 63, 64, 65, 72, 73, 74} ;
			return r;
		}
		else if(s==57||s==58||s==59||s==66||s==67||s==68||s==75||s==76||s==77) {
			int r[] = {57, 58, 59, 66, 67, 68, 75, 76, 77} ;
			return r;
		}
		else if(s==60||s==61||s==62||s==69||s==70||s==71||s==78||s==79||s==80) {
			int r[] = {60, 61, 62, 69, 70, 71, 78, 79, 80} ;
			return r;
		}
	} */
	
	public static EZText IsInitial(int x, int y, int num) {
		String number = "";
		number += num;
		return EZ.addText(x, y, number, Color.BLACK, 40);
	}
	
	public static EZText IsClick(int x, int y, int num) {
		String number = "";
		number += num;
		return EZ.addText(x, y, number, Color.BLUE, 40);
	}
	
	public static void main(String[] args) throws java.io.IOException {
		Scanner scan = new Scanner(new FileReader("SudokuPuzzle"));
		
		int slot[] = new int[81];
		final boolean initial[] = new boolean[81]; //this is just to declare if the slot[] is considered an initial given value
		
		//scanning the file and all that.
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				int s = ((row * 9) + column);
				int number = scan.nextInt();
				switch(number) {
				case 0:
					slot[s] = 0;
					initial[s] = false;
					break;
				default:
					slot[s] = number;
					initial[s] = true;
					break;
				}
			}
		}
		scan.close();
		
		EZ.initialize(450,450);
		EZ.addLine(50, 0, 50, 450, Color.BLACK);
		EZ.addLine(100, 0, 100, 450, Color.BLACK);
		EZ.addRectangle(150, 225, 3, 450, Color.BLACK, true);
		EZ.addLine(200, 0, 200, 450, Color.BLACK);
		EZ.addLine(250, 0, 250, 450, Color.BLACK);
		EZ.addRectangle(300, 225, 3, 450, Color.BLACK, true);
		EZ.addLine(350, 0, 350, 450, Color.BLACK);
		EZ.addLine(400, 0, 400, 450, Color.BLACK);
		EZ.addLine(0, 50, 450, 50, Color.BLACK);
		EZ.addLine(0, 100, 450, 100, Color.BLACK);
		EZ.addRectangle(225, 150, 450, 3, Color.BLACK, true);
		EZ.addLine(0, 200, 450, 200, Color.BLACK);
		EZ.addLine(0, 250, 450, 250, Color.BLACK);
		EZ.addRectangle(225, 300, 450, 3, Color.BLACK, true);
		EZ.addLine(0, 350, 450, 350, Color.BLACK);
		EZ.addLine(0, 400, 450, 400, Color.BLACK);
		
		EZRectangle clickBox[]= new EZRectangle[81];
		EZText numBox[] = new EZText[81];
		
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				int s = ((row * 9) + column);
				int rowS = (row*50) + 25;
				int columnS = (column*50) + 25;
				clickBox[s] = EZ.addRectangle(columnS, rowS, 48, 48, Color.WHITE, true);
				if(initial[s]) {
					numBox[s] = IsInitial(clickBox[s].getXCenter(), clickBox[s].getYCenter(), slot[s]);
				}
//				EZ.addRectangle(rowS, columnS, 48, 48, Color.WHITE, true);
			}
		}
		 while(true) {
			 int x = EZInteraction.getXMouse();
			 int y = EZInteraction.getYMouse();
			 if(EZInteraction.wasMouseLeftButtonReleased()) {
				 for(int s = 0; s < 81; s++) {
					 if(clickBox[s].isPointInElement(x, y)) {
					 	int xBox = clickBox[s].getXCenter();
					 	int yBox = clickBox[s].getYCenter();
					 	if (initial[s] == false) {
					 		if (slot[s] != 0) {
					 			numBox[s].hide();
					 		}
					 		slot[s]++;
					 		if (slot[s] > 9) {
					 			slot[s] = 0;
					 			numBox[s].hide();
					 		}
					 		else {
					 			numBox[s] = IsClick(xBox,yBox,slot[s]);
					 		}
					 	}
					 	System.out.println(s + " " + slot[s]);
				 	}
				 }
			 }
			 EZ.refreshScreen();
		 } 
	}
}
