package Chess;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Main {
	public static ArrayList<boardSpace> boardSpaces = new ArrayList<boardSpace>();
	public static String[] pieces = { "rook", "knight", "bishop", "king", "queen", "bishop", "knight", "rook", "pawn" };
	public static display gui = new display();

	public static void main(String[] args) {
		gui.createJpanel();
		createSpaces();
		setUpPieces();
		gui.refresh();
	}

	private static void createSpaces() { // creates a list of every space including it row column and piece on it
		int row = 1;
		int column = 1;
		for (int i = 0; i < 64; i++) {
			chessPiece a = null; // creates a empty space
			boardSpace x = new boardSpace(row, column, a);
			boardSpaces.add(x);
			column++;
			if (column > 8) {
				row++;
				column = 1;
			}
		}

	}

	public static boardSpace getBoardSpace(int row, int column) { // gets a specific boardspace from the main list
		for (boardSpace x : boardSpaces) {
			if (x.getRow() == row && x.getColumn() == column) {
				return x;
			}
		}
		return null;
	}

	private static void setUpPieces() { // sets up original formation
		String color = "white";
		int listPos = 0;
		int row = 1;
		int column = 1;
		boolean loop = true;

		while (loop == true) { // will run twice for white and black
			for (int counter = 0; counter < pieces.length + 7; counter++) { // needs to run 7 extra time for the pawns
				JLabel image = new JLabel();
				chessPiece piece = new chessPiece();
				ArrayList<boardSpace> possibleSpaces = new ArrayList<boardSpace>();
				if (counter < 8) {// for regular pieces
					listPos = counter;
				} else { // for pawns
					if (counter == 8) {
						column = 1;
					}
					listPos = 8;
					if (color.equals("white")) {
						row = 2;
					} else {
						row = 7;
					}
				}
				// this sections creates an object of what exact piece is at the boardSpace
				if (pieces[listPos].equals("pawn")) {
					piece = new pawn(color, pieces[listPos], true, row, column, possibleSpaces, image);
				}
				if (pieces[listPos].equals("bishop")) {
					piece = new bishop(color, pieces[listPos], row, column, possibleSpaces, image);
				}
				if (pieces[listPos].equals("knight")) {
					piece = new knight(color, pieces[listPos], row, column, possibleSpaces, image);
				}
				if (pieces[listPos].equals("rook")) {
					piece = new rook(color, pieces[listPos], row, column, possibleSpaces, image);
				}
				if (pieces[listPos].equals("king")) {
					piece = new king(color, pieces[listPos], row, column, possibleSpaces, image);
				}
				if (pieces[listPos].equals("queen")) {
					piece = new queen(color, pieces[listPos], row, column, possibleSpaces, image);
				}
				for (boardSpace x : boardSpaces) {
					if (x.getRow() == row && x.getColumn() == column) {
						gui.placePiece(x, piece); // used to place pieces in specific places
					}
				}
				column++;
			}
			if (color.equals("white")) {
				color = "black";
				row = 8;
				column = 1;
			} else {
				loop = false;
			}
		}
	}

	public static void findPossibleSpaces(chessPiece piece) { // figures out what piece is on the board and uses its
		int row = piece.getRow();
		int column = piece.getColumn();
		System.out.println(piece.getClass());
		piece.possibleSpaces(boardSpaces, row, column);
		gui.displayPossibleSpaces(piece.getPossibleSpaces());
	}

	public static void endGame() {
		System.out.println("Game end");
		gui.displayGameEnd();
	}

	public static void computersTurn() {
		boolean loop = true;
		while (loop == true) { // will loop until a space is found with a piece that can move
			int randRow = (int) (Math.random() * 7) + 1; // rand num 1-8
			int randCol = (int) (Math.random() * 7) + 1;
			boardSpace randSpace = getBoardSpace(randRow, randCol);
			if (randSpace.isPieceOnSpace() == true) {
				if (randSpace.pieceOnSpace().getColor().equals("white")) {
					findPossibleSpaces(randSpace.pieceOnSpace());
					ArrayList<boardSpace> possibleSpaces = randSpace.pieceOnSpace().getPossibleSpaces();
					if (possibleSpaces.size() != 0) { // picks a random space from the possible spaces to move to
						int randNum = (int) (Math.random() * possibleSpaces.size());
						gui.MovingPiece(possibleSpaces.get(randNum), randSpace);
						loop = false;
					}
				}
			}
		}
	}

}
