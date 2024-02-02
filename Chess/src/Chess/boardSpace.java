package Chess;

public class boardSpace {
	chessPiece piece = new chessPiece();
	int row, column;

	public boardSpace() {
		row = 0;
		column = 0;
		piece = new chessPiece();
	}

	public boardSpace(int row, int column, chessPiece piece) {
		this.row = row;
		this.column = column;
		this.piece = piece;
	}

	public void setPiece(chessPiece x) {
		piece = x;
	}

	public void addPiecetoSpace(chessPiece x) { // when adding a new piece
		piece = x;
		x.setRow(this.row);
		x.setColumn(this.column);
	}

	public chessPiece pieceOnSpace() { // if no piece then returns null
		return piece;
	}

	public boolean isPieceOnSpace() {
		if (piece == null) {
			return false;
		}
		return true;
	}

	public void removePieceOnSpace() { // used when a piece is overtaken by another
		if (piece.getName().equals("king")) {
			Main.endGame();
		}
		piece = null;
	}

	public void setPiecetoNull() {
		piece = null;
	} // used when a piece moves so that the old space doesn't still think it has the
		// same piece

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public String toString() {
		return "This space has a " + piece.toString() + " and is at row: " + row + " and column: " + column;
	}
}
