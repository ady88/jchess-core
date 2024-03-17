package com.adrian.jchess.core.model;

//public class BoardSquareFactory {
//	private static final BoardSquare[] CACHE = new BoardSquare[ChessGameConstants.BOARD_SQUARES];
//	
//	static {
//		for (RankNotation rankIndex : RankNotation.values()) {
//			for (FileNotation fileIndex : FileNotation.values()) {
//
//				BoardSquare boardSquare = new BoardSquare(BoardCoordinates.of(fileIndex, rankIndex).orElseThrow());
//				boardSquare.setChessPiece(NoChessPiece.instance());
//				CACHE[(fileIndex.getFileIntNotation() * ChessGameConstants.BOARD_DIMENSION)
//						+ rankIndex.getRankIntNotation()] = boardSquare;
//			}
//		}
//	}
//	
//	public static BoardSquare getSquare(final BoardCoordinates coordinates) {
//		BoardSquare boardSquare = CACHE[(coordinates.getFile().getFileIntNotation() * ChessGameConstants.BOARD_DIMENSION)
//		        						+ coordinates.getRank().getRankIntNotation()];
//		return boardSquare;
//	}
//}
