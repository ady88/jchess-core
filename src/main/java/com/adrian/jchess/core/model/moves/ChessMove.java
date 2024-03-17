package com.adrian.jchess.core.model.moves;

import java.util.Map;
import java.util.stream.Stream;

import com.adrian.jchess.core.model.ChessPieceNotation;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;

public class ChessMove {
	private BoardCoordinates initialLocation;
	private BoardCoordinates finalLocation;

	private Map.Entry<MoveMetadata, Stream<Object>> moveMetadata;

	public BoardCoordinates getInitialLocation() {
		return initialLocation;
	}

	public void setInitialLocation(BoardCoordinates initialLocation) {
		this.initialLocation = initialLocation;
	}

	public BoardCoordinates getFinalLocation() {
		return finalLocation;
	}

	public void setFinalLocation(BoardCoordinates finalLocation) {
		this.finalLocation = finalLocation;
	}

	public Map.Entry<MoveMetadata, Stream<Object>> getMoveMetadata() {
		return moveMetadata;
	}

	public void setMoveMetadata(Map.Entry<MoveMetadata, Stream<Object>> moveMetadata) {
		this.moveMetadata = moveMetadata;
	}

	@Override
	public String toString() {
		if (moveMetadata == null) {
			return "[" + initialLocation + finalLocation + "]";
		}
		String promotionNotation = MoveMetadata.PROMOTION.equals(moveMetadata.getKey())
				|| MoveMetadata.CAPTURE_PROMOTION.equals(moveMetadata.getKey())
						?  ((ChessPieceNotation) (moveMetadata.getValue().findFirst().get())).getFenNotationBlack()
						: "";

		return "[" + initialLocation + finalLocation + promotionNotation + "]";
	}
}
