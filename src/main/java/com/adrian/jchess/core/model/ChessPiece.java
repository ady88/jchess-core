package com.adrian.jchess.core.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessPiece extends AbstractChessPiece {

	private final ChessPieceNotation notation;
	private final ChessPieceColor color;

	private final static Map<ChessPieceNotation, ChessPiece[]> CACHE = new HashMap<>();

	static {

		for (ChessPieceNotation chessNotation : ChessPieceNotation.values()) {
			if (ChessPieceNotation.NONE.equals(chessNotation)) {
				continue;
			}

			CACHE.put(chessNotation, new ChessPiece[] { new ChessPiece(chessNotation, ChessPieceColor.WHITE),
					new ChessPiece(chessNotation, ChessPieceColor.BLACK) });
		}
	}

	private ChessPiece(final ChessPieceNotation notation, final ChessPieceColor color) {
		this.notation = notation;
		this.color = color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, notation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChessPiece other = (ChessPiece) obj;
		return color == other.color && notation == other.notation;
	}

	@Override
	public ChessPieceNotation getNotation() {
		return this.notation;
	}

	@Override
	public ChessPieceColor getColor() {
		return this.color;
	}

	public static AbstractChessPiece of(final ChessPieceNotation notation, final ChessPieceColor color) {
		if (notation == null || color == null || ChessPieceNotation.NONE.equals(notation)
				|| ChessPieceColor.NONE.equals(color)) {
			return NoChessPiece.instance();
		}

		return ChessPieceColor.WHITE.equals(color) ? CACHE.get(notation)[0] : CACHE.get(notation)[1];
	}

	public static AbstractChessPiece of(final String fenNotation) {
		ChessPieceNotation chessPieceNotation = ChessPieceNotation.findByFenNotation(fenNotation);
		if (chessPieceNotation == null || chessPieceNotation.equals(ChessPieceNotation.NONE)) {
			return NoChessPiece.instance();
		}
		ChessPieceColor color;
		Character c = fenNotation.charAt(0);
		color = Character.isLowerCase(c) ? ChessPieceColor.BLACK : ChessPieceColor.WHITE;
		return ChessPieceColor.WHITE.equals(color) ? CACHE.get(chessPieceNotation)[0]
				: CACHE.get(chessPieceNotation)[1];
	}

	public static String getFenNotation(AbstractChessPiece piece) {
		if (piece == null || piece instanceof NoChessPiece) {
			return NoChessPiece.instance().getNotation().getFenNotationWhite();
		}

		return ChessPieceColor.WHITE.equals(piece.getColor()) ? piece.getNotation().getFenNotationWhite()
				: piece.getNotation().getFenNotationBlack();
	}

}
