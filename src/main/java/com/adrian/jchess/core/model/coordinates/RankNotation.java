package com.adrian.jchess.core.model.coordinates;

public enum RankNotation {
	FIRST(0, 7, "1"), SECOND(1, 6, "2"), THIRD(2, 5, "3"), FORTH(3, 4, "4"), FIFTH(4, 3, "5"), SISTH(5, 2, "6"),
	SEVENTH(6, 1, "7"), EIGHT(7, 0, "8");

	private final int rankIntNotation;
	private final int rankIntReverseNotation;
	private final String rankTextNotation;

	RankNotation(final int rankIntNotation, final int rankIntReverseNotation, final String rankTextNotation) {
		this.rankIntNotation = rankIntNotation;
		this.rankTextNotation = rankTextNotation;
		this.rankIntReverseNotation = rankIntReverseNotation;
	}

	public int getRankIntNotation() {
		return rankIntNotation;
	}

	public String getRankTextNotation() {
		return rankTextNotation;
	}

	public int getRankIntReverseNotation() {
		return rankIntReverseNotation;
	}

	public static RankNotation findByIntValue(final int rank) {
		for (RankNotation value : values()) {
			if (value.rankIntNotation == rank) {
				return value;
			}
		}

		return null;
	}

	public static RankNotation findByIntReverseValue(final int rank) {
		for (RankNotation value : values()) {
			if (value.rankIntReverseNotation == rank) {
				return value;
			}
		}

		return null;
	}

	public static RankNotation findByTextValue(final String rank) {
		for (RankNotation value : values()) {
			if (value.rankTextNotation.equals(rank)) {
				return value;
			}
		}

		return null;
	}
}
