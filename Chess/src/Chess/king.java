package Chess;

import java.util.ArrayList;

import javax.swing.JLabel;

public class king extends chessPiece {

	public king(String color, String type, int row, int column, ArrayList <boardSpace> possibleSpaces, JLabel image) {
		super (color, type, row, column, possibleSpaces, image);
	}
	
	public void possibleSpaces(ArrayList <boardSpace> boardSpaces, int row, int column) {
		possibleSpaces.clear();
		int counter = 0;
		int OGcolumn = column;
		int OGrow = row;
		int change = 1;
		for(int i = 0; i < 2; i++) {
			row = OGrow;
			row += change;
			column = OGcolumn;
			column += change;
			if (column > 0 && column < 9 && row > 0 && row < 9) {
		 checkSpace(boardSpaces, row, OGcolumn, getColor());  //uses checkSpace which is a chesspiece method that checks a space if it has a piece on it
		 checkSpace(boardSpaces, OGrow, column, getColor());
		 checkSpace(boardSpaces, OGrow + change, column, getColor());
		 checkSpace(boardSpaces, row, OGcolumn - change, getColor());
			}
			change = -1;
		}
	}
	public int adjustments() {
		return 15;
	}
}