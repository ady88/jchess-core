package com.adrian.jchess.core.model.coordinates;

import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.util.ChessGameUtils;

public class BoardCoordinatesUtils {

	private static final int[] KNIGHT_MOVE_RULES = new int[] { 1, -1, 2, -2 };
	private static final int[] KING_MOVE_RULES = new int[] { 0, 1, -1 };
	private static final int[] PAWN_MOVE_RULES = new int[] { 1, -1 };

	public static BoardCoordinates[] getAllTopLeftCoordinates(final BoardCoordinates coordinates) {
		if (FileNotation.A.equals(coordinates.getFile()) || RankNotation.EIGHT.equals(coordinates.getRank())) {
			return new BoardCoordinates[0];
		}

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		BoardCoordinates[] result = new BoardCoordinates[Math.min(ChessGameUtils.BOARD_DIMENSION - rank - 1, file)];
		int index = 0;
		while (file != 0 && rank != 7) {
			result[index++] = BoardCoordinates.of(--file, ++rank);
		}

		return result;
	}

	public static BoardCoordinates[] getAllTopRightCoordinates(final BoardCoordinates coordinates) {
		if (FileNotation.H.equals(coordinates.getFile()) || RankNotation.EIGHT.equals(coordinates.getRank())) {
			return new BoardCoordinates[0];
		}

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		BoardCoordinates[] result = new BoardCoordinates[Math.min(ChessGameUtils.BOARD_DIMENSION - rank - 1,
				ChessGameUtils.BOARD_DIMENSION - file - 1)];

		int index = 0;
		while (file != 7 && rank != 7) {
			result[index++] = BoardCoordinates.of(++file, ++rank);
		}

		return result;
	}

	public static BoardCoordinates[] getAllBottomLeftCoordinates(final BoardCoordinates coordinates) {
		if (FileNotation.A.equals(coordinates.getFile()) || RankNotation.FIRST.equals(coordinates.getRank())) {
			return new BoardCoordinates[0];
		}

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		BoardCoordinates[] result = new BoardCoordinates[Math.min(rank, file)];
		int index = 0;
		while (file != 0 && rank != 0) {
			result[index++] = BoardCoordinates.of(--file, --rank);
		}

		return result;
	}

	public static BoardCoordinates[] getAllBottomRightCoordinates(final BoardCoordinates coordinates) {
		if (FileNotation.H.equals(coordinates.getFile()) || RankNotation.FIRST.equals(coordinates.getRank())) {
			return new BoardCoordinates[0];
		}

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		BoardCoordinates[] result = new BoardCoordinates[Math.min(rank, ChessGameUtils.BOARD_DIMENSION - file - 1)];

		int index = 0;
		while (file != 7 && rank != 0) {
			result[index++] = BoardCoordinates.of(++file, --rank);
		}

		return result;
	}

	public static BoardCoordinates[] getAllTopCoordinates(final BoardCoordinates coordinates) {
		if (RankNotation.EIGHT.equals(coordinates.getRank())) {
			return new BoardCoordinates[0];
		}

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		BoardCoordinates[] result = new BoardCoordinates[ChessGameUtils.BOARD_DIMENSION - rank - 1];
		int index = 0;
		while (rank != 7) {
			result[index++] = BoardCoordinates.of(file, ++rank);
		}

		return result;
	}

	public static BoardCoordinates[] getAllBottomCoordinates(final BoardCoordinates coordinates) {
		if (RankNotation.FIRST.equals(coordinates.getRank())) {
			return new BoardCoordinates[0];
		}

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		BoardCoordinates[] result = new BoardCoordinates[rank];
		int index = 0;
		while (rank != 0) {
			result[index++] = BoardCoordinates.of(file, --rank);
		}

		return result;
	}

	public static BoardCoordinates[] getAllLeftCoordinates(final BoardCoordinates coordinates) {
		if (FileNotation.A.equals(coordinates.getFile())) {
			return new BoardCoordinates[0];
		}

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		BoardCoordinates[] result = new BoardCoordinates[file];
		int index = 0;
		while (file != 0) {
			result[index++] = BoardCoordinates.of(--file, rank);
		}

		return result;
	}

	public static BoardCoordinates[] getAllRightCoordinates(final BoardCoordinates coordinates) {
		if (FileNotation.H.equals(coordinates.getFile())) {
			return new BoardCoordinates[0];
		}

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		BoardCoordinates[] result = new BoardCoordinates[ChessGameUtils.BOARD_DIMENSION - file - 1];
		int index = 0;
		while (file != 7) {
			result[index++] = BoardCoordinates.of(++file, rank);
		}

		return result;
	}

	public static BoardCoordinates[] getAllKnightCoordinates(final BoardCoordinates coordinates) {
		int size;

		if (BoardCoordinates.of("a1").equals(coordinates) || BoardCoordinates.of("h1").equals(coordinates)
				|| BoardCoordinates.of("a8").equals(coordinates) || BoardCoordinates.of("h8").equals(coordinates)) {
			size = 2;
		} else if (BoardCoordinates.of("b1").equals(coordinates) || BoardCoordinates.of("a2").equals(coordinates)
				|| BoardCoordinates.of("h2").equals(coordinates) || BoardCoordinates.of("g1").equals(coordinates)
				|| BoardCoordinates.of("a7").equals(coordinates) || BoardCoordinates.of("b8").equals(coordinates)
				|| BoardCoordinates.of("h7").equals(coordinates) || BoardCoordinates.of("g8").equals(coordinates)) {
			size = 3;
		} else if (BoardCoordinates.of("b2").equals(coordinates) || BoardCoordinates.of("g2").equals(coordinates)
				|| BoardCoordinates.of("b7").equals(coordinates) || BoardCoordinates.of("g7").equals(coordinates)
				|| FileNotation.A.equals(coordinates.getFile()) || FileNotation.H.equals(coordinates.getFile())
				|| RankNotation.FIRST.equals(coordinates.getRank())
				|| RankNotation.EIGHT.equals(coordinates.getRank())) {
			size = 4;
		} else if (FileNotation.B.equals(coordinates.getFile()) || FileNotation.G.equals(coordinates.getFile())
				|| RankNotation.SECOND.equals(coordinates.getRank())
				|| RankNotation.SEVENTH.equals(coordinates.getRank())) {
			size = 6;
		} else {
			size = 8;
		}

		BoardCoordinates[] result = new BoardCoordinates[size];

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		int index = 0;

		for (int i = 0; i < 4; i++) {
			if (Math.abs(KNIGHT_MOVE_RULES[i]) == 1) {
				for (int j = 2; j < 4; j++) {
					if (file + KNIGHT_MOVE_RULES[i] >= 0 && file + KNIGHT_MOVE_RULES[i] < ChessGameUtils.BOARD_DIMENSION
							&& rank + KNIGHT_MOVE_RULES[j] >= 0
							&& rank + KNIGHT_MOVE_RULES[j] < ChessGameUtils.BOARD_DIMENSION) {
						result[index++] = BoardCoordinates.of(file + KNIGHT_MOVE_RULES[i], rank + KNIGHT_MOVE_RULES[j]);
					}
				}
			} else {
				for (int j = 0; j < 2; j++) {
					if (file + KNIGHT_MOVE_RULES[i] >= 0 && file + KNIGHT_MOVE_RULES[i] < ChessGameUtils.BOARD_DIMENSION
							&& rank + KNIGHT_MOVE_RULES[j] >= 0
							&& rank + KNIGHT_MOVE_RULES[j] < ChessGameUtils.BOARD_DIMENSION) {
						result[index++] = BoardCoordinates.of(file + KNIGHT_MOVE_RULES[i], rank + KNIGHT_MOVE_RULES[j]);
					}
				}
			}
		}

		return result;
	}

	public static BoardCoordinates[] getAllSurroundingCoordinates(final BoardCoordinates coordinates) {
		int size;
		if (BoardCoordinates.of("a1").equals(coordinates) || BoardCoordinates.of("h1").equals(coordinates)
				|| BoardCoordinates.of("a8").equals(coordinates) || BoardCoordinates.of("h8").equals(coordinates)) {
			size = 3;
		} else if (FileNotation.A.equals(coordinates.getFile()) || FileNotation.H.equals(coordinates.getFile())
				|| RankNotation.FIRST.equals(coordinates.getRank())
				|| RankNotation.EIGHT.equals(coordinates.getRank())) {
			size = 5;
		} else {
			size = 8;
		}

		BoardCoordinates[] result = new BoardCoordinates[size];

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		int index = 0;

		for (int i = 0; i < KING_MOVE_RULES.length; i++) {
			for (int j = 0; j < KING_MOVE_RULES.length; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				if (file + KING_MOVE_RULES[i] >= 0 && file + KING_MOVE_RULES[i] < ChessGameUtils.BOARD_DIMENSION
						&& rank + KING_MOVE_RULES[j] >= 0
						&& rank + KING_MOVE_RULES[j] < ChessGameUtils.BOARD_DIMENSION) {
					result[index++] = BoardCoordinates.of(file + KING_MOVE_RULES[i], rank + KING_MOVE_RULES[j]);
				}
			}
		}

		return result;
	}

	public static BoardCoordinates[] getAllPawnAttackCoordinates(final BoardCoordinates coordinates,
			final ChessPieceColor color) {
		int size;
		if ((RankNotation.FIRST.equals(coordinates.getRank()) && ChessPieceColor.BLACK.equals(color))
				|| (RankNotation.EIGHT.equals(coordinates.getRank()) && ChessPieceColor.WHITE.equals(color))) {
			return new BoardCoordinates[0];
		}

		if (FileNotation.A.equals(coordinates.getFile()) || FileNotation.H.equals(coordinates.getFile())) {
			size = 1;
		} else {
			size = 2;
		}

		BoardCoordinates[] result = new BoardCoordinates[size];

		int file = coordinates.getFile().getFileIntNotation();
		int rank = coordinates.getRank().getRankIntNotation();

		int index = 0;
		if (ChessPieceColor.WHITE.equals(color)) {
			for (int i = 0; i < PAWN_MOVE_RULES.length; i++) {
				if (file + PAWN_MOVE_RULES[i] >= 0 && file + PAWN_MOVE_RULES[i] < ChessGameUtils.BOARD_DIMENSION
						&& rank + PAWN_MOVE_RULES[0] >= 0
						&& rank + PAWN_MOVE_RULES[0] < ChessGameUtils.BOARD_DIMENSION) {
					result[index++] = BoardCoordinates.of(file + PAWN_MOVE_RULES[i], rank + PAWN_MOVE_RULES[0]);
				}
			}
		} else {
			for (int i = 0; i < PAWN_MOVE_RULES.length; i++) {
				if (file + PAWN_MOVE_RULES[i] >= 0 && file + PAWN_MOVE_RULES[i] < ChessGameUtils.BOARD_DIMENSION
						&& rank + PAWN_MOVE_RULES[1] >= 0
						&& rank + PAWN_MOVE_RULES[1] < ChessGameUtils.BOARD_DIMENSION) {
					result[index++] = BoardCoordinates.of(file + PAWN_MOVE_RULES[i], rank + PAWN_MOVE_RULES[1]);
				}
			}
		}

		return result;
	}
}
