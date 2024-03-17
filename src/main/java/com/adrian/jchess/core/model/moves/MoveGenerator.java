package com.adrian.jchess.core.model.moves;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.adrian.jchess.core.model.AbstractChessPiece;
import com.adrian.jchess.core.model.ChessPiece;
import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.ChessPosition;
import com.adrian.jchess.core.model.NoChessPiece;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.util.ChessGameUtils;

public class MoveGenerator {

	public List<ChessMove> generateMoves(ChessPosition position, BoardCoordinates initial) {
		List<ChessMove> result = new ArrayList<>();
		AbstractChessPiece piece = position.getPiece(initial);
		if (piece instanceof NoChessPiece) {
			return result;
		}

		var moveRules = ChessGameUtils.PIECE_MOVE_RULES.get(piece);
		List<BoardCoordinates> destinations = new ArrayList<>();
		for (MoveRule moveRule : moveRules) {
			destinations.addAll(applyMoveRule(position, initial, moveRule));
		}

		for (BoardCoordinates destination : destinations) {
			result.add(generateMove(position, initial, destination));
		}

		return result;
	}

	public ChessMove generateMove(ChessPosition position, BoardCoordinates initial, BoardCoordinates end) {
		AbstractChessPiece initialPiece = position.getPiece(initial);
		AbstractChessPiece capturedPiece = position.getPiece(end);
		if (capturedPiece.getColor().equals(initialPiece.getColor())) {
			return null;
		}

		Map.Entry<MoveMetadata, Stream<Object>> moveMetadata;

		ChessMove result = new ChessMove();

		switch (initialPiece.getNotation()) {
		case QUEEN:
		case ROOK:
		case BISHOP:
		case KNIGHT:

		case KING:

		case PAWN:

		case NONE:
		default:
			if (capturedPiece instanceof NoChessPiece) {
				moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.NONE, null);
			} else {
				moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.CAPTURE, Stream.of(capturedPiece));
			}
		}

		result.setInitialLocation(initial);
		result.setFinalLocation(end);

		return result;

	}

	private List<BoardCoordinates> applyMoveRule(ChessPosition position, BoardCoordinates initial, MoveRule moveRule) {
		int indexFile = initial.getFile().getFileIntNotation();
		int indexRank = initial.getRank().getRankIntNotation();
		if (indexFile + moveRule.getFileMove() < 0 || indexFile + moveRule.getFileMove() > 7
				|| indexRank + moveRule.getRankMove() < 0 || indexRank + moveRule.getRankMove() > 7) {
			return List.of();
		}
		List<BoardCoordinates> result = new ArrayList<>();
		if (MoveRuleType.UNLIMITED.equals(moveRule.getType())) {
			while (indexFile + moveRule.getFileMove() > 0 && indexFile + moveRule.getFileMove() < 7
					&& indexRank + moveRule.getRankMove() > 0 && indexRank + moveRule.getRankMove() < 7) {
				if (position.getPiece(BoardCoordinates.of(indexFile + moveRule.getFileMove(), indexRank)).getColor()
						.equals(position.getPiece(initial).getColor())) {
					break;
				}

				result.add(BoardCoordinates.of(indexFile + moveRule.getFileMove(), indexRank + moveRule.getRankMove()));
				indexFile = indexFile + moveRule.getFileMove();
				indexRank = indexRank + moveRule.getRankMove();
			}
		} else if (MoveRuleType.OCCASIONAL.equals(moveRule.getType())) {
			if (MoveRule.WHITE_PAWN_LEFT_CAPTURE.equals(moveRule) || MoveRule.WHITE_PAWN_RIGHT_CAPTURE.equals(moveRule)
					|| MoveRule.BLACK_PAWN_LEFT_CAPTURE.equals(moveRule)
					|| MoveRule.BLACK_PAWN_RIGHT_CAPTURE.equals(moveRule)) {

				if (position
						.getPiece(BoardCoordinates.of(indexFile + moveRule.getFileMove(),
								indexRank + moveRule.getRankMove())) instanceof ChessPiece endPiece
						&& !endPiece.getColor().equals(position.getPiece(initial).getColor())) {
					result.add(BoardCoordinates.of(indexFile + moveRule.getFileMove(),
							indexRank + moveRule.getRankMove()));
				}
			} else if (MoveRule.WHITE_PAWN_LEFT_ENPASSANT.equals(moveRule)
					|| MoveRule.WHITE_PAWN_RIGHT_ENPASSANT.equals(moveRule)
					|| MoveRule.BLACK_PAWN_LEFT_ENPASSANT.equals(moveRule)
					|| MoveRule.BLACK_PAWN_RIGHT_ENPASSANT.equals(moveRule)) {

				if (position.getEnPassantTarget().getCoordinates().equals(
						BoardCoordinates.of(indexFile + moveRule.getFileMove(), indexRank + moveRule.getRankMove()))) {
					result.add(BoardCoordinates.of(indexFile + moveRule.getFileMove(),
							indexRank + moveRule.getRankMove()));
				}
			} else if (MoveRule.WHITE_PAWN_FIRST_MOVE.equals(moveRule)
					|| MoveRule.BLACK_PAWN_FIRST_MOVE.equals(moveRule)) {
				AbstractChessPiece initialPiece = position.getPiece(BoardCoordinates.of(indexFile, indexRank));
				
				
				if (ChessPieceColor.WHITE.equals(initialPiece.getColor()))
				if (position.getPiece(BoardCoordinates.of(indexFile + moveRule.getFileMove(),
						indexRank + moveRule.getRankMove())) instanceof NoChessPiece) {

				}
			}
		} else if (indexFile + moveRule.getFileMove() > 0 && indexFile + moveRule.getFileMove() < 7
				&& indexRank + moveRule.getRankMove() > 0 && indexRank + moveRule.getRankMove() < 7) {
			result.add(BoardCoordinates.of(indexFile + moveRule.getFileMove(), indexRank + moveRule.getRankMove()));
		}

		return result;
	}
}
