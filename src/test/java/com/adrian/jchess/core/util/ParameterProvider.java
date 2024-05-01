package com.adrian.jchess.core.util;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;

public class ParameterProvider {
	static Stream<Arguments> topLeftCoordinates() {
		return Stream.of(Arguments.of("h1", "[g2, f3, e4, d5, c6, b7, a8]"), Arguments.of("d4", "[c5, b6, a7]"),
				Arguments.of("g6", "[f7, e8]"));
	}

	static Stream<Arguments> topRightCoordinates() {
		return Stream.of(Arguments.of("h1", "[]"), Arguments.of("a1", "[b2, c3, d4, e5, f6, g7, h8]"),
				Arguments.of("d4", "[e5, f6, g7, h8]"));
	}

	static Stream<Arguments> bottomLeftCoordinates() {
		return Stream.of(Arguments.of("h1", "[]"), Arguments.of("d4", "[c3, b2, a1]"),
				Arguments.of("g6", "[f5, e4, d3, c2, b1]"));
	}

	static Stream<Arguments> bottomRightCoordinates() {
		return Stream.of(Arguments.of("h1", "[]"), Arguments.of("d4", "[e3, f2, g1]"), Arguments.of("g6", "[h5]"));
	}

	static Stream<Arguments> topCoordinates() {
		return Stream.of(Arguments.of("h1", "[h2, h3, h4, h5, h6, h7, h8]"), Arguments.of("d4", "[d5, d6, d7, d8]"),
				Arguments.of("g6", "[g7, g8]"), Arguments.of("b8", "[]"));
	}

	static Stream<Arguments> bottomCoordinates() {
		return Stream.of(Arguments.of("h1", "[]"), Arguments.of("d4", "[d3, d2, d1]"),
				Arguments.of("g6", "[g5, g4, g3, g2, g1]"), Arguments.of("b8", "[b7, b6, b5, b4, b3, b2, b1]"));
	}

	static Stream<Arguments> leftCoordinates() {
		return Stream.of(Arguments.of("h1", "[g1, f1, e1, d1, c1, b1, a1]"), Arguments.of("d4", "[c4, b4, a4]"),
				Arguments.of("g6", "[f6, e6, d6, c6, b6, a6]"), Arguments.of("b8", "[a8]"), Arguments.of("a6", "[]"));
	}

	static Stream<Arguments> rightCoordinates() {
		return Stream.of(Arguments.of("h1", "[]"), Arguments.of("d4", "[e4, f4, g4, h4]"), Arguments.of("g6", "[h6]"),
				Arguments.of("b8", "[c8, d8, e8, f8, g8, h8]"), Arguments.of("a6", "[b6, c6, d6, e6, f6, g6, h6]"));
	}

	static Stream<Arguments> knightCoordinates() {
		return Stream.of(Arguments.of("h1", "[g3, f2]"), Arguments.of("d4", "[e6, e2, c6, c2, f5, f3, b5, b3]"),
				Arguments.of("a2", "[b4, c3, c1]"), Arguments.of("b2", "[c4, a4, d3, d1]"),
				Arguments.of("d2", "[e4, c4, f3, f1, b3, b1]"));
	}

	static Stream<Arguments> surroundingCoordinates() {
		return Stream.of(Arguments.of("h1", "[h2, g1, g2]"), Arguments.of("a2", "[a3, a1, b2, b3, b1]"),
				Arguments.of("b2", "[b3, b1, c2, c3, c1, a2, a3, a1]"));
	}

	static Stream<Arguments> pawnAttackCoordinates() {
		return Stream.of(Arguments.of("h1", ChessPieceColor.WHITE, "[g2]"),
				Arguments.of("a2", ChessPieceColor.WHITE, "[b3]"),
				Arguments.of("b2", ChessPieceColor.WHITE, "[c3, a3]"),
				Arguments.of("b2", ChessPieceColor.BLACK, "[c1, a1]"),
				Arguments.of("a7", ChessPieceColor.BLACK, "[b6]"), Arguments.of("a8", ChessPieceColor.WHITE, "[]"));
	}

	static Stream<Arguments> kingChecked() {
		return Stream.of(
				Arguments.of("8/2r3K1/1R4p1/5bk1/1p6/8/2r5/5q2 w - - 2 55", ChessPieceColor.WHITE, Boolean.TRUE),
				Arguments.of(ChessGameUtils.FEN_INITIAL, ChessPieceColor.WHITE, Boolean.FALSE),
				Arguments.of(ChessGameUtils.FEN_INITIAL, ChessPieceColor.BLACK, Boolean.FALSE),
				Arguments.of("8/8/R4kp1/1p1K1p2/2r3b1/8/2r5/8 b - - 0 47", ChessPieceColor.BLACK, Boolean.TRUE),
				Arguments.of("8/8/p4kp1/1p1K1p2/2r3b1/8/2r5/R7 w - - 2 47", ChessPieceColor.BLACK, Boolean.FALSE),
				Arguments.of("8/8/R5p1/1p1K1pk1/2r3b1/8/2r5/8 w - - 1 48", ChessPieceColor.WHITE, Boolean.FALSE));
	}

	static Stream<Arguments> availableMovesForPositionAndCoordinates() {
		return Stream
				.of(Arguments.of("2r5/RP4b1/6pk/4p2p/1B2N2P/P2K1nP1/8/8 w - - 7 48", BoardCoordinates.of("b7"),
						"[[b7b8r], [b7b8n], [b7b8b], [b7b8q], [b7c8r], [b7c8n], [b7c8b], [b7c8q]]"),
						Arguments.of("2k4r/ppp2pp1/n2p2q1/3P4/2P1r3/B4K2/P2Q1P1P/5R2 b - - 3 29", BoardCoordinates.of("a7"),"[]"));
	}

	static Stream<Arguments> availableMovesForPosition() {
		return Stream
				.of(Arguments.of("r7/ppkn3Q/2p5/8/1q6/3B4/2PKRrPP/7R w - - 2 23",
						"[[c2c3], [d2c1], [d2e3], [d2d1]]"),
						Arguments.of("2k4r/ppp2pp1/n2p2q1/3P4/2P1r3/B4K2/P2Q1P1P/5R2 b - - 3 29",
								"[[e4e5], [e4e6], [e4e7], [e4e8], [e4e3], [e4e2], [e4e1], [e4d4], [e4c4], [e4f4], [e4g4], [e4h4], [a6c5], [a6b8], [a6b4], [g6f5], [g6h7], [g6h5], [g6g5], [g6g4], [g6g3], [g6g2], [g6g1], [g6f6], [g6e6], [g6h6], [b7b6], [b7b5], [c7c6], [c7c5], [f7f6], [f7f5], [c8d7], [c8b8], [c8d8], [h8h7], [h8h6], [h8h5], [h8h4], [h8h3], [h8h2], [h8g8], [h8f8], [h8e8], [h8d8]]"));
	}

}
