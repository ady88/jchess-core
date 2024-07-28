package com.adrian.jchess.core.model.moves;

import java.util.List;

import com.adrian.jchess.core.model.AbstractChessPiece;
import com.adrian.jchess.core.model.ChessPiece;
import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.ChessPieceNotation;

public enum MoveRule {
	TOP_LEFT_DIAGONAL_U(1, -1, MoveRuleType.UNLIMITED,
			List.of(ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK))),
	BOTTOM_LEFT_DIAGONAL_U(-1, -1, MoveRuleType.UNLIMITED,
			List.of(ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK))),
	TOP_RIGHT_DIAGONAL_U(1, 1, MoveRuleType.UNLIMITED,
			List.of(ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK))),
	BOTTOM_RIGHT_DIAGONAL_U(-1, 1, MoveRuleType.UNLIMITED,
			List.of(ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK))),
	TOP_VERTICAL_U(1, 0, MoveRuleType.UNLIMITED,
			List.of(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK))),
	BOTTOM_VERTICAL_U(-1, 0, MoveRuleType.UNLIMITED,
			List.of(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK))),
	LEFT_HORIZONTAL_U(0, -1, MoveRuleType.UNLIMITED,
			List.of(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK))),
	RIGHT_HORIZONTAL_U(0, 1, MoveRuleType.UNLIMITED,
			List.of(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK))),

	TOP_LEFT_DIAGONAL_O(1, -1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK))),
	BOTTOM_LEFT_DIAGONAL_O(-1, -1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK))),
	TOP_RIGHT_DIAGONAL_O(1, 1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK))),
	BOTTOM_RIGHT_DIAGONAL_O(-1, 1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK))),
	TOP_VERTICAL_O(1, 0, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))),
	BOTTOM_VERTICAL_O(-1, 0, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK),
					ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))),
	LEFT_HORIZONTAL_O(0, -1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK))),
	RIGHT_HORIZONTAL_O(0, 1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK))),

	KNIGHT_HOP_1(1, 2, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))),
	KNIGHT_HOP_2(1, -2, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))),
	KNIGHT_HOP_3(-1, 2, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))),
	KNIGHT_HOP_4(-1, -2, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))),
	KNIGHT_HOP_5(2, 1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))),
	KNIGHT_HOP_6(2, -1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))),
	KNIGHT_HOP_7(-2, 1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))),
	KNIGHT_HOP_8(-2, -1, MoveRuleType.ONCE,
			List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))),

	KINGSIDE_CASTLE(0, 2, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK))),
	QUEENSIDE_CASTLE(0, -2, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE),
					ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK))),

	WHITE_PAWN_LEFT_CAPTURE(1, -1, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))),
	WHITE_PAWN_RIGHT_CAPTURE(1, 1, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))),
	BLACK_PAWN_LEFT_CAPTURE(-1, -1, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))),
	BLACK_PAWN_RIGHT_CAPTURE(-1, 1, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))),

	WHITE_PAWN_LEFT_ENPASSANT(1, -1, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))),
	WHITE_PAWN_RIGHT_ENPASSANT(1, 1, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))),
	BLACK_PAWN_LEFT_ENPASSANT(-1, -1, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))),
	BLACK_PAWN_RIGHT_ENPASSANT(-1, 1, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))),
	WHITE_PAWN_FIRST_MOVE(2, 0, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))),
	BLACK_PAWN_FIRST_MOVE(-2, 0, MoveRuleType.OCCASIONAL,
			List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK)));

	private final int rankMove;
	private final int fileMove;
	private final MoveRuleType type;
	private final List<AbstractChessPiece> piecesApplicable;

	MoveRule(int rankMove, int fileMove, final MoveRuleType type, final List<AbstractChessPiece> pieces) {
		this.rankMove = rankMove;
		this.fileMove = fileMove;
		this.type = type;
		this.piecesApplicable = pieces;
	}

	public int getRankMove() {
		return rankMove;
	}

	public int getFileMove() {
		return fileMove;
	}

	public MoveRuleType getType() {
		return type;
	}

	public List<AbstractChessPiece> getPiecesApplicable() {
		return piecesApplicable;
	}
}
