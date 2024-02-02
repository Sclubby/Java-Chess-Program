package Chess;

import java.util.ArrayList;

import javax.swing.JLabel;

public class chessPiece {
	String color, type;
	int row, column;
	JLabel image;
	ArrayList<boardSpace> possibleSpaces = new ArrayList<boardSpace>();

	public chessPiece() {
		color = "non";
		type = "non";
	}

	public chessPiece(String color, String type, int row, int column, ArrayList<boardSpace> possibleSpaces,
			JLabel image) {
		this.color = color;
		this.type = type;
		this.row = row;
		this.column = column;
		this.possibleSpaces = possibleSpaces;
		this.image = image;
	}

	public boolean checkSpace(ArrayList<boardSpace> boardSpaces, int row, int column, String color) {
		// if a space has a piece on it then returns false else adds space to
		// possibleSpaces
		for (boardSpace x : boardSpaces) {
			if (x.getRow() == row && x.getColumn() == column) {
				if (x.isPieceOnSpace() == false) {
					possibleSpaces.add(x);
					return true;
				} else {
					if (x.pieceOnSpace().getColor().equals("white") && color.equals("black")) {// add the space that
																								// hits a white piece
																								// then stop loop
						possibleSpaces.add(x);
						return false;
					}
					if (x.pieceOnSpace().getColor().equals("black") && color.equals("white")) {
						possibleSpaces.add(x);
						return false;
					}
				}
			}
		}
		return false;
	}

	public String toString() {
		if (type.equals("non") || color.equals("non")) {
			return "no piece";
		}
		return color + " " + type;
	}

	public String getName() {
		return type;
	}

	public String getColor() {
		return color;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return this.row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getColumn() {
		return this.column;
	}

	public ArrayList<boardSpace> getPossibleSpaces() {
		return possibleSpaces;
	}

	public void removeImage() {
		Main.gui.remove(image);

	}

	public JLabel getImage() {
		return image;
	}

	public void possibleSpaces(ArrayList<boardSpace> boardSpaces, int row2, int column2) {
		
	}

	public int adjustments() {
		return 0;
	}
}
