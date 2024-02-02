package Chess;

import java.util.ArrayList;

import javax.swing.JLabel;

public class pawn extends chessPiece {
	boolean firstMove = true;

	public pawn(String color, String type, boolean firstMove, int row, int column, ArrayList<boardSpace> possibleSpaces,
			JLabel image) {
		super(color, type, row, column, possibleSpaces, image);
		this.firstMove = firstMove;
	}

	public void moved() {
		firstMove = false;
	}

	public void possibleSpaces(ArrayList<boardSpace> boardSpaces, int row, int column) {
		// algorithm to find spaces pawn can go
		possibleSpaces.clear();
		boolean loop = true;
		boolean pieceOnFrontSpace = false;
		int counter = 0;
		while (loop == true) {
			if (getColor().equals("black")) {
				row--;
			}
			else { row++; }
			for (boardSpace x : boardSpaces) {
				if (x.getRow() == row && x.getColumn() == column) {
					if (x.isPieceOnSpace() == false) {
						possibleSpaces.add(x);
					}
					else { pieceOnFrontSpace = true; }
				}
			}
			if (counter == 0) {
				for (boardSpace x : boardSpaces) {  //makes it so a pawn can diagonally take another piece
					if (x.getRow() == row && (x.getColumn() == column + 1 || x.getColumn() == column - 1)) {
						if (x.isPieceOnSpace() == true  && x.pieceOnSpace().getColor().equals("white")  && getColor().equals("black")) {
								possibleSpaces.add(x);
							}
						if (x.isPieceOnSpace() == true  && x.pieceOnSpace().getColor().equals("black")  && getColor().equals("white")) {
							possibleSpaces.add(x);
						}
						}
					}
				}

				if (firstMove == false || counter == 1 || pieceOnFrontSpace == true) {
					//will loop again if pawn hasn't moved, but if it has or theirs a piece in the way no loop
					loop = false;
				}
				counter++;
			}
		}
	public int adjustments() {
		return 0;
	}
}
