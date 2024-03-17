package com.adrian.jchess.core.model;

public class NoChessPiece extends AbstractChessPiece {

	private static NoChessPiece piece;

	private NoChessPiece() {
	}

	@Override
	public ChessPieceNotation getNotation() {
		return ChessPieceNotation.NONE;
	}

	@Override
	public ChessPieceColor getColor() {
		return ChessPieceColor.NONE;
	}

	public static NoChessPiece instance() {
		if (piece == null) {
			piece = new NoChessPiece();
		}

		return piece;
	}

}
