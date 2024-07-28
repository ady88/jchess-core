package com.adrian.jchess.core.model.moves;

import com.adrian.jchess.core.model.ChessPieceNotation;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;

import java.util.List;
import java.util.Map;

public class ChessMove {
    private BoardCoordinates initialLocation;
    private BoardCoordinates finalLocation;

    private final int initial50MoveCounter;

    private Map.Entry<MoveMetadata, List<Object>> moveMetadata;

    public ChessMove(int initial50MoveCounter) {
        this.initial50MoveCounter = initial50MoveCounter;
    }

    public int getInitial50MoveCounter() {
        return initial50MoveCounter;
    }

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

    public Map.Entry<MoveMetadata, List<Object>> getMoveMetadata() {
        return moveMetadata;
    }

    public void setMoveMetadata(Map.Entry<MoveMetadata, List<Object>> moveMetadata) {
        this.moveMetadata = moveMetadata;
    }

    @Override
    public String toString() {
        if (moveMetadata == null) {
            return "[" + initialLocation + finalLocation + "]";
        }
        String promotionNotation = MoveMetadata.PROMOTION.equals(moveMetadata.getKey())
                || MoveMetadata.CAPTURE_PROMOTION.equals(moveMetadata.getKey())
                ? ((ChessPieceNotation) (moveMetadata.getValue().getFirst())).getFenNotationBlack()
                : "";

        return "[" + initialLocation + finalLocation + promotionNotation + "]";
    }
}
