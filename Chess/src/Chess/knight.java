package Chess;

import java.util.ArrayList;

import javax.swing.JLabel;

public class knight extends chessPiece {

	public knight(String color, String type, int row, int column, ArrayList<boardSpace> possibleSpaces, JLabel image) {
		super(color, type, row, column, possibleSpaces, image);
	}

	public void possibleSpaces(ArrayList<boardSpace> boardSpaces, int row, int column) {
		possibleSpaces.clear();
		int OGrow = row;
		int OGcolumn = column;
		int counter = 0;
		int[] rowChange = { 2, 2, -2, -2, -1, 1, -1, 1 };
		int[] columnChange = { -1, 1, -1, 1, -2, -2, 2, 2 };
		while (counter < 8) {
			row += rowChange[counter];
			column += columnChange[counter];
			checkSpace(boardSpaces, row, column, getColor());
			column = OGcolumn;
			row = OGrow;
			counter++;
		}
	}
	public int adjustments() {
		return 10;
	}
}
