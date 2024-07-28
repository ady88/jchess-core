package com.adrian.jchess.core.model.coordinates;

public enum FileNotation {
	A(0, 7, "a"), B(1, 6, "b"), C(2, 5, "c"), D(3, 4, "d"), E(4, 3, "e"), F(5, 2, "f"), G(6, 1, "g"), H(7, 0, "h");

	private final int fileIntNotation;
	private final int fileIntReverseNotation;
	private final String filePgnNotation;

	FileNotation(int fileIntNotation, int fileIntReverseNotation, String filePgnNotation) {
		this.fileIntNotation = fileIntNotation;
		this.filePgnNotation = filePgnNotation;
		this.fileIntReverseNotation = fileIntReverseNotation;
	}

	public int getFileIntNotation() {
		return fileIntNotation;
	}

	public String getFilePgnNotation() {
		return filePgnNotation;
	}

	public int getFileIntReverseNotation() {
		return fileIntReverseNotation;
	}

	public static FileNotation findByIntValue(final int file) {
		for (FileNotation value : values()) {
			if (value.fileIntNotation == file) {
				return value;
			}
		}

		return null;
	}

	public static FileNotation findByIntReverseValue(final int reverseFile) {
		for (FileNotation value : values()) {
			if (value.fileIntReverseNotation == reverseFile) {
				return value;
			}
		}

		return null;
	}

	public static FileNotation findByPgnNotation(final char file) {
		for (FileNotation value : values()) {
			if (value.filePgnNotation.equals(String.valueOf(file).toLowerCase())) {
				return value;
			}
		}

		return null;
	}
}
