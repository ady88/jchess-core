package com.adrian.jchess.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.adrian.jchess.core.model.AbstractChessPiece;
import com.adrian.jchess.core.model.BoardSquare;
import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.ChessPosition;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.model.moves.ChessMove;

public class ChessPositionTest {

	private static final String FEN1 = "1rbq1r2/5p1p/p2p2p1/2p1N1P1/1nP1N3/1P3B1k/P4Q1P/R2R2K1 w - - 3 24";
	private static final String FEN2 = "r7/ppkn3Q/2p5/8/1q6/3B4/2PKRrPP/7R w - - 2 23";
	private static final String FEN3 = "2r5/RP4b1/6pk/4p2p/1B2N2P/P2K1nP1/8/8 w - - 7 48";
	private static final String FEN4 = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq - 0 1";
	private static final String FEN5 = "rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2";

	@Test
	public void testFenInitialPositionConversion() {
		ChessPosition position = ChessPositionUtils.getNewChessPosition();
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();

		System.out.println(getBoardDiagramReversed(position.getSquares()));

		assertEquals(ChessGameUtils.FEN_INITIAL, ChessPositionUtils.getFenFromChessPosition(position));
	}

	@Test
	public void testAvailableMovesFromInitialPosition() {
		ChessPosition position = ChessPositionUtils.getNewChessPosition();
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();
		List<ChessMove> moves = ChessPositionUtils.getAvailableMoves(position);
		System.out.println(moves);
	}

	@Test
	public void testAvailableMovesFromCustomPosition() {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(FEN4);
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();
		List<ChessMove> moves = ChessPositionUtils.getAvailableMoves(position);
		System.out.println(moves);
	}

	@Test
	public void testAvailableMovesFromCustomPosition2() {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(FEN5);
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();
		List<ChessMove> moves = ChessPositionUtils.getAvailableMoves(position);
		System.out.println(moves);
	}

	@Test
	public void testAvailableMovesFromCustomPosition3() {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(FEN2);
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();
		List<ChessMove> moves = ChessPositionUtils.getAvailableMoves(position);
		System.out.println(moves);
	}

	@Test
	public void testAvailableMovesFromCustomPosition4() {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(FEN3);
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();
		List<ChessMove> moves = ChessPositionUtils.getAvailableMoves(position);
		System.out.println(moves);
		System.out.println(moves.size());
	}

	@Test
	public void testAvailableMovesFromInitialPositionForCoordinate() {
		ChessPosition position = ChessPositionUtils.getNewChessPosition();
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();
		List<ChessMove> moves = ChessPositionUtils.getAvailableMovesPerCoordinates(position, BoardCoordinates.of("a2"));
		System.out.println(moves);
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#availableMovesForPositionAndCoordinates")
	public void testGetAvailableMovesForPositionAndCoordinates(String fen, BoardCoordinates coordinates, String expectedResult) {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(fen);
		List<ChessMove> moves = ChessPositionUtils.getAvailableMovesPerCoordinates(position, coordinates);
		assertEquals(expectedResult, moves.toString());
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#availableMovesForPosition")
	public void testGetAvailableMovesForPosition(String fen, String expectedResult) {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(fen);
		List<ChessMove> moves = ChessPositionUtils.getAvailableMoves(position);
		assertEquals(expectedResult, moves.toString());
	}


	@Test
	public void testAvailableMovesFromInitialPositionForCoordinate2() {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(FEN2);
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();
		List<ChessMove> moves = ChessPositionUtils.getAvailableMovesPerCoordinates(position, BoardCoordinates.of("d2"));
		System.out.println(moves);
	}

	
	@Test
	public void testAvailableMovesFromInitialPositionForCoordinate3() {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(FEN3);
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();
		List<ChessMove> moves = ChessPositionUtils.getAvailableMovesPerCoordinates(position, BoardCoordinates.of("b7"));
		System.out.println(moves);
	}

	@Test
	public void testFenPositionConversion() {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(ChessGameUtils.FEN_INITIAL);
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();

		System.out.println(getBoardDiagramReversed(position.getSquares()));

		assertEquals(ChessGameUtils.FEN_INITIAL, ChessPositionUtils.getFenFromChessPosition(position));
	}

	@ParameterizedTest
	@ValueSource(strings = { FEN1, FEN2, FEN3 })
	public void testFenPositionConversion(String fen) {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(fen);
		System.out.println(getBoardDiagram(position.getSquares()));
		System.out.println();
		System.out.println();

		System.out.println(getBoardDiagramReversed(position.getSquares()));

		assertEquals(fen, ChessPositionUtils.getFenFromChessPosition(position));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#kingChecked")
	public void testisKingChecked(String fen, ChessPieceColor color, Boolean expectedResult) {
		ChessPosition position = ChessPositionUtils.getChessPositionFromFen(fen);
		boolean result = ChessPositionUtils.isKingChecked(position, color);
		assertEquals(expectedResult, Boolean.valueOf(result));
	}

	private static String getBoardDiagram(final BoardSquare[] board) {
		String result = "";

		for (int i = ChessGameUtils.BOARD_DIMENSION - 1; i >= 0; i--) {
			result += System.lineSeparator();
			for (int j = 0; j < ChessGameUtils.BOARD_DIMENSION; j++) {

				AbstractChessPiece piece = board[ChessGameUtils.getLocationIn64Array(i, j)].getChessPiece();

				if (ChessPieceColor.WHITE.equals(piece.getColor())) {
					result += piece.getNotation().getFenNotationWhite();
				} else {
					result += piece.getNotation().getFenNotationBlack();
				}

				result += " ";
			}
		}

		return result;
	}

	private static String getBoardDiagramReversed(final BoardSquare[] board) {
		String result = "";

		for (int i = 0; i < ChessGameUtils.BOARD_DIMENSION; i++) {
			result += System.lineSeparator();
			for (int j = 0; j < ChessGameUtils.BOARD_DIMENSION; j++) {

				AbstractChessPiece piece = board[ChessGameUtils.getLocationIn64Array(i, j)].getChessPiece();

				if (ChessPieceColor.WHITE.equals(piece.getColor())) {
					result += piece.getNotation().getFenNotationWhite();
				} else {
					result += piece.getNotation().getFenNotationBlack();
				}

				result += " ";
			}
		}

		return result;
	}

}
