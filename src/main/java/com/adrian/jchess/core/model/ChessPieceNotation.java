package com.adrian.jchess.core.model;

public enum ChessPieceNotation {
	PAWN("", "p", "P"), ROOK("R", "r", "R"), KNIGHT("N", "n", "N"), BISHOP("B", "b", "B"), QUEEN("Q", "q", "Q"),
	KING("K", "k", "K"), NONE("_", "_", "_");

	private final String pgnNotation;
	private final String fenNotationBlack;
	private final String fenNotationWhite;

	ChessPieceNotation(String pgnNotation, String fenNotationBlack, String fenNotationWhite) {
		this.pgnNotation = pgnNotation;
		this.fenNotationBlack = fenNotationBlack;
		this.fenNotationWhite = fenNotationWhite;
	}

	public String getPgnNotation() {
		return pgnNotation;
	}

	public String getFenNotationBlack() {
		return fenNotationBlack;
	}

	public String getFenNotationWhite() {
		return fenNotationWhite;
	}

	public static ChessPieceNotation findByFenNotation(final char fenNotation) {
		for (ChessPieceNotation value : values()) {
			if (value.fenNotationBlack.equals(String.valueOf(fenNotation).toLowerCase())) {
				return value;
			}
		}

		return null;
	}

	public static ChessPieceNotation findByFenNotation(final String fenNotation) {
		for (ChessPieceNotation value : values()) {
			if (value.fenNotationBlack.equals(fenNotation.toLowerCase())) {
				return value;
			}
		}

		return null;
	}

}
