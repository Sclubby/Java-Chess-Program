package Chess;

import java.util.ArrayList;

import javax.swing.JLabel;

public class rook extends chessPiece {

	public rook(String color, String type, int row, int column, ArrayList<boardSpace> possibleSpaces, JLabel image) {
		super(color, type, row, column, possibleSpaces, image);
	}

	public void possibleSpaces(ArrayList<boardSpace> boardSpaces, int row, int column) { // algorithm to find possible
																							// spaces for rook
		possibleSpaces.clear();
		boolean loop = true;
		int OGrow = row;
		int OGcolumn = column;
		int counter = 0;
		while (counter < 4) {
			while (loop == true && counter == 0) { // checks above rook
				row--;
				loop = checkSpace(boardSpaces, row, column, getColor());
			}
			while (loop == true && counter == 1) {
				row++;
				loop = checkSpace(boardSpaces, row, column, getColor());
			}
			while (loop == true && counter == 2) {
				column++;
				loop = checkSpace(boardSpaces, row, column, getColor());
			}
			while (loop == true && counter == 3) {
				column--;
				loop = checkSpace(boardSpaces, row, column, getColor());
			}
			column = OGcolumn;
			row = OGrow;
			loop = true;
			counter++;
		}
	}
	public int adjustments() {
		return 0;
	}
}