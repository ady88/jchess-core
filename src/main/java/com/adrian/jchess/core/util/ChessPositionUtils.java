package com.adrian.jchess.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.adrian.jchess.core.model.AbstractChessPiece;
import com.adrian.jchess.core.model.BoardSquare;
import com.adrian.jchess.core.model.ChessPiece;
import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.ChessPieceNotation;
import com.adrian.jchess.core.model.ChessPosition;
import com.adrian.jchess.core.model.NoChessPiece;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.model.coordinates.BoardCoordinatesUtils;
import com.adrian.jchess.core.model.coordinates.FileNotation;
import com.adrian.jchess.core.model.coordinates.RankNotation;
import com.adrian.jchess.core.model.moves.ChessMove;
import com.adrian.jchess.core.model.moves.MoveGeneratorByMoveRules;

public class ChessPositionUtils {
	private static final Logger LOG = Logger.getLogger(ChessPositionUtils.class.getName());

	private static final String EMPTY_SQUARE = " ";

	public static ChessPosition getNewChessPosition() {
		BoardSquare[] squares = new BoardSquare[ChessGameUtils.BOARD_SQUARES];
		ChessPieceColor gameSideToMove = ChessPieceColor.WHITE;

		final ChessPosition position = new ChessPosition(squares, gameSideToMove, true, true, true, true, null, 0, 1,
				null, null);

		position.setInitialPosition();

		return position;
	}

	public static void updatePositionFromFenString(final ChessPosition position, final String fen) {
		BoardSquare[] squares = position.getSquares();

		for (RankNotation rankIndex : RankNotation.values()) {
			for (FileNotation fileIndex : FileNotation.values()) {
				position.setPiece(NoChessPiece.instance(), BoardCoordinates.of(fileIndex, rankIndex));
			}
		}

		BoardSquare[] kingSquares = setupSquaresFromFen(squares, fen);
		position.setBlackKingSquare(kingSquares[1]);
		position.setWhiteKingSquare(kingSquares[0]);
		ChessPieceColor gameSideToMove = null;
		boolean castleKingSideWhite = false;
		boolean castleQueenSideWhite = false;
		boolean castleKingSideBlack = false;
		boolean castleQueenSideBlack = false;
		if (StringUtils.isEmpty(fen)) {
			LOG.info("Fen is empty, position will not be updated!");
			return;
		}

		String[] fenParts = fen.split(EMPTY_SQUARE);

		final String sideToMove = fenParts[1];
		if (StringUtils.isNotBlank(sideToMove)) {
			gameSideToMove = ChessPieceColor.getByColorNotation(sideToMove);
		}

		final String catleRights = fenParts[2];

		for (int i = 0; i < catleRights.length(); i++) {
			char c = catleRights.charAt(i);
			switch (c) {
			case 'K':
				castleKingSideWhite = true;
				break;
			case 'k':
				castleKingSideBlack = true;
				break;
			case 'Q':
				castleQueenSideWhite = true;
				break;
			case 'q':
				castleQueenSideBlack = true;
				break;
			default:
				castleQueenSideBlack = false;
				castleKingSideBlack = false;
				castleQueenSideWhite = false;
				castleKingSideWhite = false;
				break;
			}
		}

		final String enPassant = fenParts[3];

		BoardSquare enPassantSquare;
		if ("-".equals(enPassant)) {
			enPassantSquare = null;
		} else {
			enPassantSquare = squares[ChessGameUtils.getLocationIn64Array(BoardCoordinates.of(enPassant))];
		}

		position.setFiftyMoveCounter(Integer.valueOf(fenParts[4]));
		position.setFullMoves(Integer.valueOf(fenParts[5]));
		position.setEnPassantTarget(enPassantSquare);
		position.setBlackKingSideCastle(castleKingSideBlack);
		position.setBlackQueenSideCastle(castleQueenSideBlack);
		position.setWhiteKingSideCastle(castleKingSideWhite);
		position.setWhiteQueenSideCastle(castleQueenSideWhite);
		position.setPlayerToMove(gameSideToMove);
	}

	/**
	 * Returns the {@link ChessPosition} instance from the given fen string. Will
	 * return null if a empty fen is provided.
	 * 
	 * @param fen
	 * @return {@link ChessPosition} instance
	 */
	public static ChessPosition getChessPositionFromFen(final String fen) {
		BoardSquare[] squares = new BoardSquare[ChessGameUtils.BOARD_SQUARES];

		for (RankNotation rankIndex : RankNotation.values()) {
			for (FileNotation fileIndex : FileNotation.values()) {
				squares[ChessGameUtils.getLocationIn64Array(BoardCoordinates.of(fileIndex, rankIndex))] = BoardSquare
						.of(BoardCoordinates.of(fileIndex, rankIndex));
				squares[ChessGameUtils.getLocationIn64Array(BoardCoordinates.of(fileIndex, rankIndex))]
						.setChessPiece(NoChessPiece.instance());
			}
		}

		BoardSquare[] kingSquares = setupSquaresFromFen(squares, fen);
		ChessPieceColor gameSideToMove = null;
		boolean castleKingSideWhite = false;
		boolean castleQueenSideWhite = false;
		boolean castleKingSideBlack = false;
		boolean castleQueenSideBlack = false;
		if (StringUtils.isEmpty(fen)) {
			LOG.info("Fen is empty, position will not be created!");
			return null;
		}

		String[] fenParts = fen.split(EMPTY_SQUARE);

		final String sideToMove = fenParts[1];
		if (StringUtils.isNotBlank(sideToMove)) {
			gameSideToMove = ChessPieceColor.getByColorNotation(sideToMove);
		}

		final String catleRights = fenParts[2];

		for (int i = 0; i < catleRights.length(); i++) {
			char c = catleRights.charAt(i);
			switch (c) {
			case 'K':
				castleKingSideWhite = true;
				break;
			case 'k':
				castleKingSideBlack = true;
				break;
			case 'Q':
				castleQueenSideWhite = true;
				break;
			case 'q':
				castleQueenSideBlack = true;
				break;
			default:
				castleQueenSideBlack = false;
				castleKingSideBlack = false;
				castleQueenSideWhite = false;
				castleKingSideWhite = false;
				break;
			}
		}

		final String enPassant = fenParts[3];

		BoardSquare enPassantSquare;
		if ("-".equals(enPassant)) {
			enPassantSquare = null;
		} else {
			enPassantSquare = squares[ChessGameUtils.getLocationIn64Array(BoardCoordinates.of(enPassant))];
		}

		final Integer fiftyMoveCounter = Integer.valueOf(fenParts[4]);

		final Integer fullMoveCounter = Integer.valueOf(fenParts[5]);

		return new ChessPosition(squares, gameSideToMove, castleKingSideWhite, castleQueenSideWhite,
				castleKingSideBlack, castleQueenSideBlack, enPassantSquare, fiftyMoveCounter, fullMoveCounter,
				kingSquares[0], kingSquares[1]);
	}

	/**
	 * Returns the fen text representation of a {@link ChessPosition} instance.
	 * 
	 * @param position
	 * @return fen text representation of a {@link ChessPosition} instance
	 */
	public static String getFenFromChessPosition(final ChessPosition position) {

		if (position == null) {
			LOG.info("Position is empty, fen will not be created!");
			return null;
		}

		String fen = getFenFromBoard(position.getSquares());
		fen += EMPTY_SQUARE;

		fen += position.getPlayerToMove().getColorNotation();
		fen += EMPTY_SQUARE;

		boolean castleAvailable = false;

		if (position.isWhiteKingSideCastle()) {
			castleAvailable = true;
			fen += "K";
		}
		if (position.isWhiteQueenSideCastle()) {
			castleAvailable = true;
			fen += "Q";
		}

		if (position.isBlackKingSideCastle()) {
			castleAvailable = true;
			fen += "k";
		}
		if (position.isBlackQueenSideCastle()) {
			castleAvailable = true;
			fen += "q";
		}

		if (!castleAvailable) {
			fen += "-";
		}

		fen += EMPTY_SQUARE;

		BoardSquare enPassantTarget = position.getEnPassantTarget();
		fen += enPassantTarget == null ? "-" : enPassantTarget.getCoordinates().getFenNotation();

		fen += EMPTY_SQUARE;

		fen += position.getFiftyMoveCounter();

		fen += EMPTY_SQUARE;

		fen += position.getFullMoves();

		return fen;
	}

	public static boolean isKingChecked(final ChessPosition position, ChessPieceColor playerColor) {
		BoardSquare kingSquare = ChessPieceColor.WHITE.equals(playerColor) ? position.getWhiteKingSquare()
				: position.getBlackKingSquare();
		return isSquareAttacked(position, kingSquare.getCoordinates(), playerColor);
	}

	/**
	 * Checks if the player with the specified color has a specific square attacked.
	 * 
	 * @param position          the given chess position to check if a square is
	 *                          attacked
	 * @param squareCoordinates the square coordonates to be verified if attacked
	 * @param playerToMove      the player to check if the square is attacked - does
	 *                          not have to be the player to move
	 * @return true if the player with the specified color has the specified square
	 *         attacked, false otherwise
	 */
	public static boolean isSquareAttacked(final ChessPosition position, BoardCoordinates squareCoordinates,
			ChessPieceColor playerColor) {

		ChessPieceColor attackerColor = ChessPieceColor.WHITE.equals(playerColor) ? ChessPieceColor.BLACK
				: ChessPieceColor.WHITE;

		// is knight attacking the square
		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllKnightCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& ChessPieceNotation.KNIGHT.equals(attacker.getNotation())) {
					return true;
				}
			}
		}

		// is king attacking the square
		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllSurroundingCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& ChessPieceNotation.KING.equals(attacker.getNotation())) {
					return true;
				}
			}
		}

		// is pawn attacking the square
		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllPawnAttackCoordinates(squareCoordinates,
				playerColor)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& ChessPieceNotation.PAWN.equals(attacker.getNotation())) {
					return true;
				}
			}
		}

		// --- is queen or bishop attacking the square
		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopLeftCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& (ChessPieceNotation.QUEEN.equals(attacker.getNotation())
								|| ChessPieceNotation.BISHOP.equals(attacker.getNotation()))) {
					return true;
				} else {
					break;
				}
			}
		}

		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomLeftCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& (ChessPieceNotation.QUEEN.equals(attacker.getNotation())
								|| ChessPieceNotation.BISHOP.equals(attacker.getNotation()))) {
					return true;
				} else {
					break;
				}
			}
		}

		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopRightCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& (ChessPieceNotation.QUEEN.equals(attacker.getNotation())
								|| ChessPieceNotation.BISHOP.equals(attacker.getNotation()))) {
					return true;
				} else {
					break;
				}
			}
		}

		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomRightCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& (ChessPieceNotation.QUEEN.equals(attacker.getNotation())
								|| ChessPieceNotation.BISHOP.equals(attacker.getNotation()))) {
					return true;
				} else {
					break;
				}
			}
		}
		// --------------------------
		// --- is queen or rook attacking the square
		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& (ChessPieceNotation.QUEEN.equals(attacker.getNotation())
								|| ChessPieceNotation.ROOK.equals(attacker.getNotation()))) {
					return true;
				} else {
					break;
				}
			}
		}

		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& (ChessPieceNotation.QUEEN.equals(attacker.getNotation())
								|| ChessPieceNotation.ROOK.equals(attacker.getNotation()))) {
					return true;
				} else {
					break;
				}
			}
		}

		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllRightCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& (ChessPieceNotation.QUEEN.equals(attacker.getNotation())
								|| ChessPieceNotation.ROOK.equals(attacker.getNotation()))) {
					return true;
				} else {
					break;
				}
			}
		}

		for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllLeftCoordinates(squareCoordinates)) {
			if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
				if (attackerColor.equals(attacker.getColor())
						&& (ChessPieceNotation.QUEEN.equals(attacker.getNotation())
								|| ChessPieceNotation.ROOK.equals(attacker.getNotation()))) {
					return true;
				} else {
					break;
				}
			}
		}

		return false;
	}

	public static List<ChessMove> getAvailableMoves(ChessPosition position) {
		var availableMoves = new ArrayList<ChessMove>();
		MoveGeneratorByMoveRules moveGenerator = new MoveGeneratorByMoveRules();
		for (BoardSquare square : position.getSquares()) {
			availableMoves.addAll(moveGenerator.generate(position, square.getCoordinates()));
		}

		return availableMoves;
	}

	public static List<ChessMove> getAvailableMovesPerCoordinates(ChessPosition position,
			BoardCoordinates coordinates) {
		var availableMoves = new ArrayList<ChessMove>();
		MoveGeneratorByMoveRules moveGenerator = new MoveGeneratorByMoveRules();
		availableMoves.addAll(moveGenerator.generate(position, coordinates));

		return availableMoves;
	}

	private static String getFenFromBoard(final BoardSquare[] board) {
		if (board == null) {
			LOG.info("Board is empty cannot get fen.!");
			return "";
		}
		String fen = "";
		int currentEmptySquareCount = 0;

		for (int i = ChessGameUtils.BOARD_DIMENSION - 1; i >= 0; i--) {
			currentEmptySquareCount = 0;
			for (int j = 0; j < ChessGameUtils.BOARD_DIMENSION; j++) {

				AbstractChessPiece piece = board[ChessGameUtils.getLocationIn64Array(i, j)].getChessPiece();

				if (piece instanceof NoChessPiece) {
					currentEmptySquareCount++;
					continue;
				} else {
					if (currentEmptySquareCount > 0) {
						fen += currentEmptySquareCount;
						currentEmptySquareCount = 0;
					}

					fen += ChessPiece.getFenNotation(piece);
				}
			}

			if (currentEmptySquareCount > 0) {
				fen += currentEmptySquareCount;
			}

			if (i != 0) {
				fen += "/";
			}
		}

		return fen;
	}

	/**
	 * Sets up the board squares from the given fen notation.
	 * 
	 * @param the array of squares the squares to be updated
	 * @param fen the fen chess notation
	 * @return the board square array containing the white and black king
	 */
	private static BoardSquare[] setupSquaresFromFen(final BoardSquare[] squares, final String fen) {
		if (StringUtils.isEmpty(fen)) {
			return null;
		}

		final String fenBoard = fen.split(" ")[0];

		int rowInt = 7;
		int columnInt = 0;
		BoardSquare[] kingsLocation = new BoardSquare[2];
		for (String row : fenBoard.split("/")) {
			Integer numberEmptySquares = 0;
			columnInt = 0;
			for (char character : row.toCharArray()) {
				ChessPieceNotation notation = ChessPieceNotation.findByFenNotation(character);

				if (notation == null) {
					numberEmptySquares = Integer.valueOf(String.valueOf(character));

					while (numberEmptySquares > 0) {
						BoardCoordinates boardCoordinates = BoardCoordinates.of(columnInt, rowInt);
						BoardSquare square = squares[ChessGameUtils.getLocationIn64Array(boardCoordinates)];
						square.setChessPiece(NoChessPiece.instance());
						numberEmptySquares--;
						columnInt++;
					}
				} else {
					numberEmptySquares = 0;
					BoardCoordinates boardCoordinates = BoardCoordinates.of(columnInt, rowInt);
					BoardSquare square = squares[ChessGameUtils.getLocationIn64Array(boardCoordinates)];
					if (Character.isUpperCase(character)) {
						AbstractChessPiece chessPiece = ChessPiece.of(notation.getFenNotationWhite());
						square.setChessPiece(chessPiece);
						if (ChessPieceNotation.KING.equals(chessPiece.getNotation())) {
							kingsLocation[0] = square;
						}
					} else {
						AbstractChessPiece chessPiece = ChessPiece.of(notation.getFenNotationBlack());
						square.setChessPiece(chessPiece);
						if (ChessPieceNotation.KING.equals(chessPiece.getNotation())) {
							kingsLocation[1] = square;
						}

					}
					columnInt++;
				}
			}
			rowInt--;
		}
		return kingsLocation;
	}
}
