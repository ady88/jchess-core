package com.adrian.jchess.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.model.coordinates.BoardCoordinatesUtils;

public class BoardCoordinatesUtilsTest {

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#topLeftCoordinates")
	public void testGetAllTopLeftCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllTopLeftCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#topRightCoordinates")
	public void testGetAllTopRightCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllTopRightCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#bottomLeftCoordinates")
	public void testGetAllBottomLeftCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllBottomLeftCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#bottomRightCoordinates")
	public void testGetAllBottomRightCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils
				.getAllBottomRightCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#topCoordinates")
	public void testGetAllTopCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllTopCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#bottomCoordinates")
	public void testGetAllBottomCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllBottomCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#leftCoordinates")
	public void testGetAllLeftCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllLeftCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#rightCoordinates")
	public void testGetAllRightCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllRightCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#knightCoordinates")
	public void testGetAllKnightCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllKnightCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#surroundingCoordinates")
	public void testGetAllSurroundingCoordinates(String squareNotation, String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils
				.getAllSurroundingCoordinates(BoardCoordinates.of(squareNotation));
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}

	@ParameterizedTest
	@MethodSource("com.adrian.jchess.core.util.ParameterProvider#pawnAttackCoordinates")
	public void testGetPawnAttackCoordinates(String squareNotation, ChessPieceColor attackerColor,
			String expectedResult) {
		BoardCoordinates[] res = BoardCoordinatesUtils.getAllPawnAttackCoordinates(BoardCoordinates.of(squareNotation),
				attackerColor);
		System.out.println(Arrays.toString(res));
		assertEquals(expectedResult, Arrays.toString(res));
	}
}
