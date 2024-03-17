package com.adrian.jchess.core.model;

import java.util.Objects;

import com.adrian.jchess.core.model.coordinates.BoardCoordinates;

/**
 * Holds one board square information, this contains the coordinates of the
 * square, and a chess piece.
 */
public class BoardSquare {

	private final BoardCoordinates coordinates;
	private AbstractChessPiece chessPiece;

	private BoardSquare(final BoardCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public BoardCoordinates getCoordinates() {
		return coordinates;
	}

	public AbstractChessPiece getChessPiece() {
		return chessPiece;
	}

	public void setChessPiece(AbstractChessPiece chessPiece) {
		this.chessPiece = chessPiece;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chessPiece, coordinates);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardSquare other = (BoardSquare) obj;
		return coordinates.getFile().equals(other.getCoordinates().getFile())
				&& coordinates.getRank().equals(other.getCoordinates().getRank());
	}

	public static BoardSquare of(final BoardCoordinates coordinates) {

		BoardSquare boardSquare = new BoardSquare(coordinates);
		boardSquare.setChessPiece(NoChessPiece.instance());
		return boardSquare;
	}
}
