package com.adrian.jchess.core.model;

public enum ChessPieceColor {
	WHITE("w"), BLACK("b"), NONE("-");

	private final String colorNotation;

	ChessPieceColor(final String colorNotation) {
		this.colorNotation = colorNotation;
	}

	public String getColorNotation() {
		return colorNotation;
	}

	public static ChessPieceColor getByColorNotation(final String colorNotation) {
		for (ChessPieceColor value : values()) {
			if (value.colorNotation.equals(colorNotation)) {
				return value;
			}
		}

		return null;
	}
}
