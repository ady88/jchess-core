package com.adrian.jchess.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adrian.jchess.core.model.AbstractChessPiece;
import com.adrian.jchess.core.model.ChessPiece;
import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.ChessPieceNotation;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.model.moves.MoveRule;

/**
 * Holds some chess related constants and some utility methods.
 */
public class ChessGameUtils {

	public static final String FEN_INITIAL = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

	public static final int BOARD_DIMENSION = 8;

	public static final int BOARD_SQUARES = 64;

	public static final Map<ChessPiece, List<MoveRule>> PIECE_MOVE_RULES;

	static {
		PIECE_MOVE_RULES = new HashMap<>();

		for (ChessPieceNotation pieceNotation : ChessPieceNotation.values()) {
			if (ChessPieceNotation.NONE.equals(pieceNotation)) {
				continue;
			}
			for (ChessPieceColor pieceColor : ChessPieceColor.values()) {
				if (ChessPieceColor.NONE.equals(pieceColor)) {
					continue;
				}
				for (MoveRule moveRule : MoveRule.values()) {

					AbstractChessPiece chessPiece = ChessPiece.of(pieceNotation, pieceColor);
					if (moveRule.getPiecesApplicable().contains(chessPiece)) {
						PIECE_MOVE_RULES.computeIfAbsent((ChessPiece) chessPiece, e -> new ArrayList<>());
						PIECE_MOVE_RULES.get(chessPiece).add(moveRule);
					}
				}
			}
		}
	}

	public static int getLocationIn64Array(final int rank, final int file) {
		return (rank * ChessGameUtils.BOARD_DIMENSION) + file;
	}

	public static int getLocationIn64Array(BoardCoordinates coordinates) {
		return getLocationIn64Array(coordinates.getRank().getRankIntNotation(), coordinates.getFile().getFileIntNotation());
	}
}
