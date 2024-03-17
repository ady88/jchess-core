package com.adrian.jchess.core.model.coordinates;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.adrian.jchess.core.util.ChessGameUtils;

public class BoardCoordinates {
	private final FileNotation file;
	private final RankNotation rank;

	private final static BoardCoordinates[][] cache = new BoardCoordinates[ChessGameUtils.BOARD_DIMENSION][ChessGameUtils.BOARD_DIMENSION];

	static {
		for (int i = 0; i < ChessGameUtils.BOARD_DIMENSION; i++) {
			for (int j = 0; j < ChessGameUtils.BOARD_DIMENSION; j++) {
				cache[i][j] = new BoardCoordinates(FileNotation.findByIntValue(i), RankNotation.findByIntValue(j));
			}
		}
	}

	private BoardCoordinates(final FileNotation file, final RankNotation rank) {
		this.file = file;
		this.rank = rank;
	}

	public FileNotation getFile() {
		return file;
	}

	public RankNotation getRank() {
		return rank;
	}

	public String getFenNotation() {
		return file.getFilePgnNotation() + rank.getRankTextNotation();
	}

	public static BoardCoordinates of(final FileNotation file, final RankNotation rank) {
		if (file == null || rank == null) {
			return null;
		}

		return cache[file.getFileIntNotation()][rank.getRankIntNotation()];
	}

	public static BoardCoordinates of(final int intFile, final int intRank) {
		if (intFile < 0 || intFile >= ChessGameUtils.BOARD_DIMENSION || intRank < 0
				|| intRank >= ChessGameUtils.BOARD_DIMENSION) {
			return null;
		}

		return cache[intFile][intRank];
	}

	public static BoardCoordinates of(final String squareNotation) {
		if (StringUtils.isBlank(squareNotation) || squareNotation.length() != 2) {
			return null;
		}

		FileNotation fileValue = FileNotation.findByPgnNotation(squareNotation.charAt(0));
		RankNotation rankValue = RankNotation.findByTextValue(String.valueOf(squareNotation.charAt(1)));

		return of(fileValue, rankValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, rank);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardCoordinates other = (BoardCoordinates) obj;
		return file == other.file && rank == other.rank;
	}

	@Override
	public String toString() {
		return file.getFilePgnNotation() + rank.getRankTextNotation();
	}
	
	
}
