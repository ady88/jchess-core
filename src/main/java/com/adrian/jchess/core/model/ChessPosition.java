package com.adrian.jchess.core.model;

import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.model.coordinates.FileNotation;
import com.adrian.jchess.core.model.coordinates.RankNotation;
import com.adrian.jchess.core.util.ChessGameUtils;

public class ChessPosition {

	private final BoardSquare[] squares;

	private ChessPieceColor playerToMove;

	private boolean whiteKingSideCastle;
	private boolean whiteQueenSideCastle;

	private boolean blackKingSideCastle;
	private boolean blackQueenSideCastle;

	private BoardSquare enPassantTarget;

	private int fiftyMoveCounter;

	private int fullMoves;

	private BoardSquare whiteKingSquare;
	private BoardSquare blackKingSquare;

	public ChessPosition(BoardSquare[] squares, ChessPieceColor playerToMove, boolean whiteKingSideCaste,
			boolean whiteQueenSideCaste, boolean blackKingSideCaste, boolean blackQueenSideCaste,
			BoardSquare enPassantTarget, int fiftyMoveCounter, int fullMoves, BoardSquare whiteKingSquare,
			BoardSquare blackKingSquare) {
		this.squares = squares;
		this.playerToMove = playerToMove;
		this.whiteKingSideCastle = whiteKingSideCaste;
		this.whiteQueenSideCastle = whiteQueenSideCaste;
		this.blackKingSideCastle = blackKingSideCaste;
		this.blackQueenSideCastle = blackQueenSideCaste;
		this.enPassantTarget = enPassantTarget;
		this.fiftyMoveCounter = fiftyMoveCounter;
		this.fullMoves = fullMoves;
		this.whiteKingSquare = whiteKingSquare;
		this.blackKingSquare = blackKingSquare;
	}

	public ChessPosition(final ChessPosition position) {
		this.squares = new BoardSquare[position.getSquares().length];
		for (int i = 0; i < position.getSquares().length; i++) {
			this.squares[i] = BoardSquare.of(position.getSquares()[i].getCoordinates());
			this.squares[i].setChessPiece(position.getSquares()[i].getChessPiece());
		}
		this.playerToMove = position.getPlayerToMove();
		this.whiteKingSideCastle = position.isWhiteKingSideCastle();
		this.whiteQueenSideCastle = position.isWhiteQueenSideCastle();
		this.blackKingSideCastle = position.isBlackKingSideCastle();
		this.blackQueenSideCastle = position.isBlackQueenSideCastle();
		if (position.getEnPassantTarget() != null) {
			this.enPassantTarget = BoardSquare.of(position.getEnPassantTarget().getCoordinates());
		}
		this.fiftyMoveCounter = position.getFiftyMoveCounter();
		this.fullMoves = position.getFullMoves();
		this.whiteKingSquare = position.getWhiteKingSquare();
		this.blackKingSquare = position.getBlackKingSquare();
	}

	public BoardSquare[] getSquares() {
		return squares;
	}

	public ChessPieceColor getPlayerToMove() {
		return playerToMove;
	}

	public boolean isWhiteKingSideCastle() {
		return whiteKingSideCastle;
	}

	public boolean isWhiteQueenSideCastle() {
		return whiteQueenSideCastle;
	}

	public boolean isBlackKingSideCastle() {
		return blackKingSideCastle;
	}

	public boolean isBlackQueenSideCastle() {
		return blackQueenSideCastle;
	}

	public BoardSquare getEnPassantTarget() {
		return enPassantTarget;
	}

	public int getFiftyMoveCounter() {
		return fiftyMoveCounter;
	}

	public int getFullMoves() {
		return fullMoves;
	}

	public void setPlayerToMove(ChessPieceColor playerToMove) {
		this.playerToMove = playerToMove;
	}

	public void setWhiteKingSideCastle(boolean whiteKingSideCastle) {
		this.whiteKingSideCastle = whiteKingSideCastle;
	}

	public void setWhiteQueenSideCastle(boolean whiteQueenSideCastle) {
		this.whiteQueenSideCastle = whiteQueenSideCastle;
	}

	public void setBlackKingSideCastle(boolean blackKingSideCastle) {
		this.blackKingSideCastle = blackKingSideCastle;
	}

	public void setBlackQueenSideCastle(boolean blackQueenSideCastle) {
		this.blackQueenSideCastle = blackQueenSideCastle;
	}

	public void setEnPassantTarget(BoardSquare enPassantTarget) {
		this.enPassantTarget = enPassantTarget;
	}

	public void setFiftyMoveCounter(int fiftyMoveCounter) {
		this.fiftyMoveCounter = fiftyMoveCounter;
	}

	public void setFullMoves(int fullMoves) {
		this.fullMoves = fullMoves;
	}

	public BoardSquare getWhiteKingSquare() {
		return whiteKingSquare;
	}

	public void setWhiteKingSquare(BoardSquare whiteKingSquare) {
		this.whiteKingSquare = whiteKingSquare;
	}

	public BoardSquare getBlackKingSquare() {
		return blackKingSquare;
	}

	public void setBlackKingSquare(BoardSquare blackKingSquare) {
		this.blackKingSquare = blackKingSquare;
	}

	public BoardSquare getSquare(final BoardCoordinates coordinates) {
		BoardSquare boardSquare = squares[ChessGameUtils.getLocationIn64Array(coordinates)];
		return boardSquare;
	}

	public AbstractChessPiece getPiece(final BoardCoordinates coordinates) {
		return squares[ChessGameUtils.getLocationIn64Array(coordinates)].getChessPiece();
	}

	public void setPiece(final AbstractChessPiece chessPiece, final BoardCoordinates coordinates) {
		getSquare(coordinates).setChessPiece(chessPiece);
	}

	public void setEmptySquare(final BoardCoordinates coordinates) {
		BoardSquare square = getSquare(coordinates);

		if (square == null) {
			square = BoardSquare.of(coordinates);
			squares[ChessGameUtils.getLocationIn64Array(coordinates)] = square;
		}

		square.setChessPiece(NoChessPiece.instance());
	}

	public void setupEmptyBoard() {
		for (int i = 0; i < ChessGameUtils.BOARD_DIMENSION; i++) {
			for (int j = 0; j < ChessGameUtils.BOARD_DIMENSION; j++) {
				setEmptySquare(BoardCoordinates.of(i, j));
			}
		}
	}

	public void setInitialPosition() {
		setupEmptyBoard();

		final ChessPiece wRook = (ChessPiece) ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE);

		setPiece(wRook, BoardCoordinates.of(FileNotation.A, RankNotation.FIRST));
		setPiece(wRook, BoardCoordinates.of(FileNotation.H, RankNotation.FIRST));

		final ChessPiece wKnight = (ChessPiece) ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.WHITE);

		setPiece(wKnight, BoardCoordinates.of(FileNotation.B, RankNotation.FIRST));
		setPiece(wKnight, BoardCoordinates.of(FileNotation.G, RankNotation.FIRST));

		final ChessPiece wBishop = (ChessPiece) ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.WHITE);

		setPiece(wBishop, BoardCoordinates.of(FileNotation.C, RankNotation.FIRST));
		setPiece(wBishop, BoardCoordinates.of(FileNotation.F, RankNotation.FIRST));

		final ChessPiece wQueen = (ChessPiece) ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.WHITE);
		setPiece(wQueen, BoardCoordinates.of(FileNotation.D, RankNotation.FIRST));

		final ChessPiece wKing = (ChessPiece) ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.WHITE);
		setPiece(wKing, BoardCoordinates.of(FileNotation.E, RankNotation.FIRST));
		this.whiteKingSquare = getSquare(BoardCoordinates.of(FileNotation.E, RankNotation.FIRST));

		final ChessPiece wPawn = (ChessPiece) ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE);
		for (FileNotation notation : FileNotation.values()) {
			setPiece(wPawn, BoardCoordinates.of(notation, RankNotation.SECOND));
		}

		final ChessPiece bRook = (ChessPiece) ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.BLACK);

		setPiece(bRook, BoardCoordinates.of(FileNotation.A, RankNotation.EIGHT));
		setPiece(bRook, BoardCoordinates.of(FileNotation.H, RankNotation.EIGHT));

		final ChessPiece bKnight = (ChessPiece) ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK);

		setPiece(bKnight, BoardCoordinates.of(FileNotation.B, RankNotation.EIGHT));
		setPiece(bKnight, BoardCoordinates.of(FileNotation.G, RankNotation.EIGHT));

		final ChessPiece bBishop = (ChessPiece) ChessPiece.of(ChessPieceNotation.BISHOP, ChessPieceColor.BLACK);

		setPiece(bBishop, BoardCoordinates.of(FileNotation.C, RankNotation.EIGHT));
		setPiece(bBishop, BoardCoordinates.of(FileNotation.F, RankNotation.EIGHT));

		final ChessPiece bQueen = (ChessPiece) ChessPiece.of(ChessPieceNotation.QUEEN, ChessPieceColor.BLACK);
		setPiece(bQueen, BoardCoordinates.of(FileNotation.D, RankNotation.EIGHT));

		final ChessPiece bKing = (ChessPiece) ChessPiece.of(ChessPieceNotation.KING, ChessPieceColor.BLACK);
		setPiece(bKing, BoardCoordinates.of(FileNotation.E, RankNotation.EIGHT));
		this.blackKingSquare = getSquare(BoardCoordinates.of(FileNotation.E, RankNotation.EIGHT));

		final ChessPiece bPawn = (ChessPiece) ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK);

		for (FileNotation notation : FileNotation.values()) {
			setPiece(bPawn, BoardCoordinates.of(notation, RankNotation.SEVENTH));
		}
	}
}
