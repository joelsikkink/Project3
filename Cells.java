import java.awt.Color;

public class Cells {

	public EZRectangle[][] cells = new EZRectangle[9][9];
	public EZText[][] cellValue = new EZText[9][9];
	private int[][] slot = new int[9][9];
	private boolean[][] slotGiven = new boolean[9][9];
	
	Cells(){
		initialize();
	}
	
	private void initialize() {
		EZ.initialize(500,600);
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				int rowS = (row*50) + 50;
				int columnS= (column*50) + 50;
				cells[row][column] = EZ.addRectangle(columnS, rowS, 48, 48, Color.WHITE, true);
				cellValue[row][column] = EZ.addText(cells[row][column].getXCenter(), cells[row][column].getYCenter(), "", Color.black, 40);
			}
		}
	}

	public void setSlot(int r, int c, int num) {
		slot[r][c] = num;
		slotGiven[r][c] = true;
		if(slot[r][c] == 0) {
			slotGiven[r][c] = false;
		}
		update(r,c,num);
	}

	private void update(int r, int c, int num) {
		String number = "";
		number += num;
		if(num == 0) {
			cellValue[r][c].setMsg("");
		}else {
			cellValue[r][c].setMsg(number);
		}
	}
}
