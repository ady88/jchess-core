package com.adrian.jchess.core.model.moves;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.adrian.jchess.core.model.AbstractChessPiece;
import com.adrian.jchess.core.model.BoardSquare;
import com.adrian.jchess.core.model.ChessPiece;
import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.ChessPieceNotation;
import com.adrian.jchess.core.model.ChessPosition;
import com.adrian.jchess.core.model.NoChessPiece;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.model.coordinates.FileNotation;
import com.adrian.jchess.core.model.coordinates.RankNotation;
import com.adrian.jchess.core.util.ChessGameUtils;
import com.adrian.jchess.core.util.ChessPositionUtils;

/**
 * Holds method to generate chess moves on a given position based on defined
 * move rules. All rules should have the same precondition.
 */
public class MoveGeneratorByMoveRules {
	/**
	 * Validate if occasional move is valid in given position. The move is given via
	 * the initial and end coordinates.
	 * 
	 * @param position
	 * @param initial
	 * @return list of chess moves according to the given rules
	 */
	public List<ChessMove> generate(final ChessPosition position, final BoardCoordinates initial) {
		List<ChessMove> result = new ArrayList<>();
		AbstractChessPiece piece = position.getPiece(initial);
		if (!(piece instanceof ChessPiece chessPiece)) {
			return result;
		}

		var pieceMoveRules = ChessGameUtils.PIECE_MOVE_RULES.get(chessPiece);

		var moveRules = ChessGameUtils.PIECE_MOVE_RULES.get(chessPiece);

		for (MoveRule moveRule : moveRules) {
			boolean isCapture = false;
			if (pieceMoveRules.contains(moveRule)) {
				int indexFile = initial.getFile().getFileIntNotation();
				int indexRank = initial.getRank().getRankIntNotation();
				if (indexFile + moveRule.getFileMove() < 0 || indexFile + moveRule.getFileMove() > 7
						|| indexRank + moveRule.getRankMove() < 0 || indexRank + moveRule.getRankMove() > 7) {
					continue;
				}

				if (MoveRuleType.UNLIMITED.equals(moveRule.getType())) {
					while (indexFile + moveRule.getFileMove() >= 0 && indexFile + moveRule.getFileMove() <= 7
							&& indexRank + moveRule.getRankMove() >= 0 && indexRank + moveRule.getRankMove() <= 7) {
						if (position.getPiece(BoardCoordinates.of(indexFile + moveRule.getFileMove(),
								indexRank + moveRule.getRankMove())) instanceof ChessPiece endPiece) {
							if (endPiece.getColor().equals(chessPiece.getColor())) {
								break;
							} else {
								isCapture = true;
							}
						}

						if (allowMove(position, initial, moveRule)) {
							// TODO check test after checking the precondition
							ChessPosition updatedPosition = getUpdatedPosition(position, initial, BoardCoordinates
									.of(indexFile + moveRule.getFileMove(), indexRank + moveRule.getRankMove()));

							if (ChessPositionUtils.isKingChecked(updatedPosition, chessPiece.getColor())) {
								break;
							}

							List<ChessMove> move = generate(position, initial, BoardCoordinates.of(
									indexFile + moveRule.getFileMove(), indexRank + moveRule.getRankMove()), moveRule);
							result.addAll(move);
							indexFile = indexFile + moveRule.getFileMove();
							indexRank = indexRank + moveRule.getRankMove();
							if (isCapture) {
								break;
							}
						} else {
							System.out.println("Move not allowed");
							break;
						}
					}

				} else {
					if (position.getPiece(BoardCoordinates.of(indexFile + moveRule.getFileMove(),
							indexRank + moveRule.getRankMove())) instanceof ChessPiece endPiece) {
						if (endPiece.getColor().equals(chessPiece.getColor())) {
							continue;
						} else {
							isCapture = true;
						}
					}

					if (allowMove(position, initial, moveRule)) {
						// TODO add check test after checking the precondition
						// TODO check test after checking the precondition
						ChessPosition updatedPosition = getUpdatedPosition(position, initial, BoardCoordinates
								.of(indexFile + moveRule.getFileMove(), indexRank + moveRule.getRankMove()));

						if (ChessPositionUtils.isKingChecked(updatedPosition, chessPiece.getColor())) {
							continue;
						}

						List<ChessMove> move = generate(position, initial, BoardCoordinates
								.of(indexFile + moveRule.getFileMove(), indexRank + moveRule.getRankMove()), moveRule);
						result.addAll(move);
					}
				}
			}
		}

		return result;
	}

	private List<ChessMove> generate(ChessPosition position, BoardCoordinates initial, BoardCoordinates end,
			MoveRule rule) {
		Map.Entry<MoveMetadata, Stream<Object>> moveMetadata;
		var result = new ArrayList<ChessMove>();

		if (MoveRule.KINGSIDE_CASTLE.equals(rule)) {

			Stream<Object> rookMoveDetails = ChessPieceColor.WHITE.equals(position.getPiece(initial).getColor())
					? Stream.of(BoardCoordinates.of(FileNotation.H, RankNotation.FIRST),
							BoardCoordinates.of(FileNotation.F, RankNotation.FIRST))
					: Stream.of(BoardCoordinates.of(FileNotation.H, RankNotation.EIGHT),
							BoardCoordinates.of(FileNotation.F, RankNotation.EIGHT));

			moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.CASTLE_SHORT, rookMoveDetails);
			ChessMove move = new ChessMove();
			move.setInitialLocation(initial);
			move.setFinalLocation(end);
			move.setMoveMetadata(moveMetadata);
			result.add(move);
		} else if (MoveRule.QUEENSIDE_CASTLE.equals(rule)) {
			Stream<Object> rookMoveDetails = ChessPieceColor.WHITE.equals(position.getPiece(initial).getColor())
					? Stream.of(BoardCoordinates.of(FileNotation.A, RankNotation.FIRST),
							BoardCoordinates.of(FileNotation.D, RankNotation.FIRST))
					: Stream.of(BoardCoordinates.of(FileNotation.A, RankNotation.EIGHT),
							BoardCoordinates.of(FileNotation.D, RankNotation.EIGHT));

			moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.CASTLE_LONG, rookMoveDetails);
			ChessMove move = new ChessMove();
			move.setInitialLocation(initial);
			move.setFinalLocation(end);
			move.setMoveMetadata(moveMetadata);
			result.add(move);
		} else if (MoveRule.BLACK_PAWN_LEFT_ENPASSANT.equals(rule) || MoveRule.BLACK_PAWN_RIGHT_ENPASSANT.equals(rule)
				|| MoveRule.WHITE_PAWN_RIGHT_ENPASSANT.equals(rule)
				|| MoveRule.WHITE_PAWN_LEFT_ENPASSANT.equals(rule)) {
			AbstractChessPiece capturedPiece = position.getPiece(end);
			Stream<Object> enpassantMoveDetails = Stream.of(capturedPiece);
			moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.ENPASSANT_ENABLED, enpassantMoveDetails);
			ChessMove move = new ChessMove();
			move.setInitialLocation(initial);
			move.setFinalLocation(end);
			move.setMoveMetadata(moveMetadata);
			result.add(move);
		} else if ((position.getPiece(initial) instanceof ChessPiece piece1
				&& ChessPieceNotation.PAWN.equals(piece1.getNotation())
				&& RankNotation.SEVENTH.equals(initial.getRank())
				&& ChessPieceColor.WHITE.equals(piece1.getColor())
				&& position.getPiece(end) instanceof ChessPiece)
				|| (position.getPiece(initial) instanceof ChessPiece piece2
						&& ChessPieceNotation.PAWN.equals(piece2.getNotation())
						&& RankNotation.SECOND.equals(initial.getRank())
						&& ChessPieceColor.BLACK.equals(piece2.getColor())
						&& position.getPiece(end) instanceof ChessPiece)) {

			for (ChessPieceNotation pieceNotation : ChessPieceNotation.values()) {
				if (ChessPieceNotation.NONE.equals(pieceNotation) || ChessPieceNotation.KING.equals(pieceNotation)
						|| ChessPieceNotation.PAWN.equals(pieceNotation)) {
					continue;
				}
				Stream<Object> capturedPieceAndPromotion = Stream.of(pieceNotation);
				moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.CAPTURE_PROMOTION,
						capturedPieceAndPromotion);

				ChessMove capturePromotionMove = new ChessMove();
				capturePromotionMove.setInitialLocation(initial);
				capturePromotionMove.setFinalLocation(end);
				capturePromotionMove.setMoveMetadata(moveMetadata);
				result.add(capturePromotionMove);

			}
		} else if ((position.getPiece(initial) instanceof ChessPiece piece1
				&& ChessPieceNotation.PAWN.equals(piece1.getNotation())
				&& RankNotation.SEVENTH.equals(initial.getRank())
				&& ChessPieceColor.WHITE.equals(piece1.getColor()))
				|| (position.getPiece(initial) instanceof ChessPiece piece2
						&& ChessPieceNotation.PAWN.equals(piece2.getNotation())
						&& RankNotation.SECOND.equals(initial.getRank())
						&& ChessPieceColor.BLACK.equals(piece2.getColor()))) {
			for (ChessPieceNotation pieceNotation : ChessPieceNotation.values()) {
				if (ChessPieceNotation.NONE.equals(pieceNotation) || ChessPieceNotation.KING.equals(pieceNotation)
						|| ChessPieceNotation.PAWN.equals(pieceNotation)) {
					continue;
				}
				Stream<Object> promotionPiece = Stream.of(pieceNotation);
				moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.PROMOTION, promotionPiece);

				ChessMove promotionMove = new ChessMove();
				promotionMove.setInitialLocation(initial);
				promotionMove.setFinalLocation(end);
				promotionMove.setMoveMetadata(moveMetadata);
				result.add(promotionMove);

			}
		} else if (position.getPiece(end) instanceof ChessPiece endPiece) {
			moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.CAPTURE, Stream.of(endPiece));

			ChessMove promotionMove = new ChessMove();
			promotionMove.setInitialLocation(initial);
			promotionMove.setFinalLocation(end);
			promotionMove.setMoveMetadata(moveMetadata);
			result.add(promotionMove);
		} else {
			moveMetadata = new AbstractMap.SimpleImmutableEntry<>(MoveMetadata.NONE, Stream.of());

			ChessMove promotionMove = new ChessMove();
			promotionMove.setInitialLocation(initial);
			promotionMove.setFinalLocation(end);
			promotionMove.setMoveMetadata(moveMetadata);
			result.add(promotionMove);
		}

		return result;

	}

	public ChessPosition getUpdatedPosition(ChessPosition position, BoardCoordinates initial, BoardCoordinates end) {
		ChessPosition positionClone = new ChessPosition(position);
		if (!(positionClone.getPiece(initial) instanceof ChessPiece initialPiece)) {
			return positionClone;
		}
		positionClone.setEmptySquare(initial);
		positionClone.setPiece(initialPiece, end);
		if (initialPiece.getColor().equals(ChessPieceColor.WHITE)
				&& ChessPieceNotation.KING.equals(initialPiece.getNotation())) {
			BoardSquare endKingSquare = BoardSquare.of(end);
			positionClone.setWhiteKingSquare(endKingSquare);
			endKingSquare.setChessPiece(initialPiece);
		} else if (initialPiece.getColor().equals(ChessPieceColor.BLACK)
				&& ChessPieceNotation.KING.equals(initialPiece.getNotation())) {
			positionClone.setBlackKingSquare(BoardSquare.of(end));
			BoardSquare endKingSquare = BoardSquare.of(end);
			positionClone.setBlackKingSquare(endKingSquare);
			endKingSquare.setChessPiece(initialPiece);
		}
		return positionClone;
	}

	private boolean allowMove(ChessPosition position, BoardCoordinates initial, MoveRule moveRule) {
		boolean allowMove = true;
		if (!(position.getPiece(initial) instanceof ChessPiece piece)) {
			return false;
		}

		if (!position.getPlayerToMove().equals(piece.getColor())) {
			allowMove = false;
		} else if (ChessPieceNotation.PAWN.equals(piece.getNotation())
				&& (MoveRule.BLACK_PAWN_FIRST_MOVE.equals(moveRule)
						|| MoveRule.WHITE_PAWN_FIRST_MOVE.equals(moveRule))
				&& (!RankNotation.SECOND.equals(initial.getRank())
						&& !RankNotation.SEVENTH.equals(initial.getRank()))) {
			allowMove = false;
		} else if (ChessPieceNotation.PAWN.equals(piece.getNotation())
				&& (MoveRule.TOP_VERTICAL_O.equals(moveRule) || MoveRule.BOTTOM_VERTICAL_O.equals(moveRule)
						|| MoveRule.BLACK_PAWN_FIRST_MOVE.equals(moveRule)
						|| MoveRule.WHITE_PAWN_FIRST_MOVE.equals(moveRule))
				&& position.getPiece(BoardCoordinates.of(
						initial.getFile().getFileIntNotation() + moveRule.getFileMove(),
						initial.getRank().getRankIntNotation() + moveRule.getRankMove())) instanceof ChessPiece) {
			allowMove = false;
		} else if (ChessPieceNotation.PAWN.equals(piece.getNotation())
				&& (MoveRule.BLACK_PAWN_FIRST_MOVE.equals(moveRule)
						|| MoveRule.WHITE_PAWN_FIRST_MOVE.equals(moveRule))) {
			int rankMove = moveRule.getRankMove() < 0 ? moveRule.getRankMove() + 1 : moveRule.getRankMove() - 1;
			if (position.getPiece(BoardCoordinates.of(
					initial.getFile().getFileIntNotation(),
					initial.getRank().getRankIntNotation() + rankMove)) instanceof ChessPiece) {
				allowMove = false;
			}
		} else if (ChessPieceNotation.PAWN.equals(piece.getNotation())
				&& (MoveRule.BLACK_PAWN_LEFT_ENPASSANT.equals(moveRule)
						|| MoveRule.BLACK_PAWN_RIGHT_ENPASSANT.equals(moveRule)
						|| MoveRule.WHITE_PAWN_RIGHT_ENPASSANT.equals(moveRule)
						|| MoveRule.WHITE_PAWN_LEFT_ENPASSANT.equals(moveRule))
				&& position.getEnPassantTarget() == null) {
			allowMove = false;
		} else if (ChessPieceNotation.PAWN.equals(piece.getNotation())
				&& (MoveRule.WHITE_PAWN_LEFT_CAPTURE.equals(moveRule)
						|| MoveRule.WHITE_PAWN_RIGHT_CAPTURE.equals(moveRule)
						|| MoveRule.BLACK_PAWN_LEFT_CAPTURE.equals(moveRule)
						|| MoveRule.BLACK_PAWN_RIGHT_CAPTURE.equals(moveRule)
						|| MoveRule.BLACK_PAWN_LEFT_ENPASSANT.equals(moveRule)
						|| MoveRule.BLACK_PAWN_RIGHT_ENPASSANT.equals(moveRule)
						|| MoveRule.WHITE_PAWN_RIGHT_ENPASSANT.equals(moveRule)
						|| MoveRule.WHITE_PAWN_LEFT_ENPASSANT.equals(moveRule))
				&& position.getPiece(BoardCoordinates.of(
						initial.getFile().getFileIntNotation() + moveRule.getFileMove(),
						initial.getRank().getRankIntNotation() + moveRule.getRankMove())) instanceof NoChessPiece) {
			allowMove = false;
		} else if ((!MoveRule.KINGSIDE_CASTLE.equals(moveRule) && !MoveRule.QUEENSIDE_CASTLE.equals(moveRule))
				|| (ChessPieceNotation.KING.equals(piece.getNotation())
						&& ((ChessPieceColor.WHITE.equals(piece.getColor()) && moveRule.equals(MoveRule.KINGSIDE_CASTLE)
								&& position.isWhiteKingSideCastle())
								|| (ChessPieceColor.WHITE.equals(piece.getColor())
										&& moveRule.equals(MoveRule.QUEENSIDE_CASTLE)
										&& position.isWhiteQueenSideCastle())
								|| (ChessPieceColor.BLACK.equals(piece.getColor())
										&& moveRule.equals(MoveRule.KINGSIDE_CASTLE)
										&& position.isBlackKingSideCastle())
								|| (ChessPieceColor.BLACK.equals(piece.getColor())
										&& moveRule.equals(MoveRule.QUEENSIDE_CASTLE)
										&& position.isBlackQueenSideCastle())))) {
			allowMove = true;
		} else {
			allowMove = false;
		}
		return allowMove;
	}

	// abstract ChessMovePrecondition checkPreCondition();
}
