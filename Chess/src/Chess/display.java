
package Chess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class display extends JFrame implements ActionListener, MouseListener {

	public JLabel board = new JLabel();
	public JLabel numbers = new JLabel();
	private JLabel highlight = new JLabel();
	private boardSpace highlightedSpace = null;
	private int PiecesOutBlack = 0, PiecesOutWhite = 0;
	private ArrayList<JLabel> possibleSpacesDisplayed = new ArrayList<JLabel>();

	public void createJpanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		setLayout(null);
		setVisible(true);
		setTitle("Chess");
		addMouseListener(this);
		getContentPane().setBackground(Color.DARK_GRAY);
	}

	public void refresh() { // resets the board to the back keeping the new changes on top
		placeImage(board, "board", 125, 80, 4);
		repaint();
	}

	public void placePiece(boardSpace space, chessPiece piece) { // used to place pieces in specific squares
		// note rows and column both go from 1-8 starting from the top left
		space.addPiecetoSpace(piece); // formally adds piece to space
		// adds the jlabel to the board
		int row = space.getRow();
		int column = space.getColumn();
		int xpos = 0;
		int ypos = 0;
		ypos = 18 + (row * 88);
		xpos = 64 + (column * 88);
		int adjustment = piece.adjustments();  //each piece has a value to make sure it centers in the space
		ypos -= adjustment;
		xpos -= adjustment;
		String fullPieceName = piece.getColor() + "_" + piece.getName();
		placeImage(piece.getImage(), fullPieceName, xpos, ypos, 4);
	}

	public void placeImage(JLabel x, String y, int xpos, int ypos, int scale) { // used to display a png
		ImageIcon image1 = createImg("/res/" + y + ".png");
		Image scaledImage1 = image1.getImage();
		x.setBounds(xpos, ypos, scaledImage1.getWidth(null) * scale, scaledImage1.getWidth(null) * scale);
		ImageIcon finalImage = new ImageIcon(
				scaledImage1.getScaledInstance(x.getHeight(), x.getWidth(), Image.SCALE_FAST));
		x.setIcon(finalImage);
		add(x);
	}

	private ImageIcon createImg(String path) {
		ImageIcon temp = new ImageIcon(getClass().getResource(path));
		return temp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) { // when mouse is clicked these actions happen
		int x = e.getX();
		int y = e.getY();
		int column = 0;
		int row = 0;
		column = ((int) ((x - 55) / 88.5)); // equation the finds what column the mouse is in based on the x cord
		row = ((int) ((y - 30) / 88.5));
		if (column >= 1 && column < 9 && row >= 1 && row < 9) { // keeps highlight in chess board region
			for (boardSpace a : Main.boardSpaces) {
				if (a.getRow() == row && a.getColumn() == column) {
					checkClickedSpace(a);
				}
			}
		}
		// places a highlighted square around a chosen piece
		refresh();
	}

	public void checkClickedSpace(boardSpace space) { // needs work
		if (space.isPieceOnSpace() == true && space.pieceOnSpace().getColor().equals("black")) {
			highlightedSpace = space;
			int row = space.getRow();
			int column = space.getColumn();
			placeImage(highlight, "highlight", 75 + ((int) (88.5 * (column - 1))), 30 + ((int) (88.5 * (row - 1))), 1);
			Main.findPossibleSpaces(space.pieceOnSpace());
		} else {
			if (highlightedSpace != null) { // this runs a piece is highlighted a it is being moved to a new space
				for (boardSpace x : highlightedSpace.pieceOnSpace().getPossibleSpaces()) {// checks all possible moves
																							// for piece
					if (space == x) {
						MovingPiece(space, highlightedSpace);
						Main.computersTurn();
					}
				}
				// even when a normal tile is pressed highlight is removed
				remove(highlight); // removes green highlight
				for (JLabel a : possibleSpacesDisplayed) { // removes all highlights of a possible space
					remove(a);
				}
				highlightedSpace = null;
			}
		}
	}

	public void MovingPiece(boardSpace newSpace, boardSpace oldSpace) { // used to move a piece to a new boardSpace
		if (newSpace.isPieceOnSpace() == true) { // removes piece from new space if there is one and moves it to margin
			pieceTakenOut(newSpace.pieceOnSpace());
			newSpace.removePieceOnSpace();
		}
		placePiece(newSpace, oldSpace.pieceOnSpace()); // puts piece from old space to new space
		if (newSpace.pieceOnSpace().getName().equals("pawn")) { // registers if a pawn moves so it cant
			// move 2 spaces anymore
			pawn a = (pawn) newSpace.pieceOnSpace();
			a.moved();
		}

		oldSpace.setPiecetoNull(); // removes old piece
		for (JLabel a : possibleSpacesDisplayed) { // removes all highlights displayed of a possible space
			remove(a);
		}
	}

	private void pieceTakenOut(chessPiece piece) { // if a piece is taken out of the game this places it on the margins
		String fullPieceName = piece.getColor() + "_" + piece.getName();
		int xpos = 0, ypos = 10;
		int adjustment = piece.adjustments();
		xpos -= adjustment;
		ypos -= adjustment;
		if (piece.getColor().equals("white")) {
			xpos = 890;
			PiecesOutWhite++;
			placeImage(piece.getImage(), fullPieceName, xpos, ypos + (60 * PiecesOutWhite), 4);
		} else {
			xpos = 50;
			PiecesOutBlack++;
			placeImage(piece.getImage(), fullPieceName, xpos, ypos + (60 * PiecesOutBlack), 4);
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void displayPossibleSpaces(ArrayList<boardSpace> possibleSpaces) {
		// adds a small marker on any space given (representing possible spaces to move
		// to)
		int row, column;
		for (boardSpace x : possibleSpaces) {
			JLabel marker = new JLabel();
			row = x.getRow();
			column = x.getColumn();
			placeImage(marker, "possibleSpace", 122 + ((int) (88.5 * (column - 1))), 74 + ((int) (88.5 * (row - 1))),
					1);
			possibleSpacesDisplayed.add(marker);
			refresh();
		}

	}

	public void displayGameEnd() {
		for (boardSpace x : Main.boardSpaces) {
			if (x.isPieceOnSpace() == true) {
				x.pieceOnSpace().removeImage();
			}
		}

	}
}
