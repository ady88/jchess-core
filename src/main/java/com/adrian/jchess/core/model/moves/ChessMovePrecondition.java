package com.adrian.jchess.core.model.moves;

import com.adrian.jchess.core.model.ChessPosition;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;

@FunctionalInterface
public interface ChessMovePrecondition {

	boolean test(final ChessPosition position, final BoardCoordinates initial, final MoveRule moveRule);

}
