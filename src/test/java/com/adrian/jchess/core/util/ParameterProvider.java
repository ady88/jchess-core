package com.adrian.jchess.core.util;

import com.adrian.jchess.core.model.ChessPiece;
import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.ChessPieceNotation;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.model.moves.ChessMove;
import com.adrian.jchess.core.model.moves.MoveMetadata;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    static Stream<Arguments> pgnNotationMove() {
        ChessMove initialMove1 = new ChessMove(0);
        initialMove1.setInitialLocation(BoardCoordinates.of("e2"));
        initialMove1.setFinalLocation(BoardCoordinates.of("e4"));

        ChessMove initialMove2 = new ChessMove(0);
        initialMove2.setInitialLocation(BoardCoordinates.of("b1"));
        initialMove2.setFinalLocation(BoardCoordinates.of("c3"));

        ChessMove initialMoveBlack1 = new ChessMove(1);
        initialMoveBlack1.setInitialLocation(BoardCoordinates.of("b8"));
        initialMoveBlack1.setFinalLocation(BoardCoordinates.of("c6"));

        ChessMove initialMoveBlack2 = new ChessMove(1);
        initialMoveBlack2.setInitialLocation(BoardCoordinates.of("d7"));
        initialMoveBlack2.setFinalLocation(BoardCoordinates.of("d5"));

        ChessMove intermediateMove1 = new ChessMove(4);
        intermediateMove1.setInitialLocation(BoardCoordinates.of("g2"));
        intermediateMove1.setFinalLocation(BoardCoordinates.of("e4"));
        intermediateMove1.setMoveMetadata(Map.entry(MoveMetadata.CAPTURE, List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))));

        ChessMove promotionMove1 = new ChessMove(5);
        promotionMove1.setInitialLocation(BoardCoordinates.of("d7"));
        promotionMove1.setFinalLocation(BoardCoordinates.of("d8"));
        promotionMove1.setMoveMetadata(Map.entry(MoveMetadata.PROMOTION, List.of(ChessPieceNotation.QUEEN)));

        ChessMove promotionMoveBlack1 = new ChessMove(16);
        promotionMoveBlack1.setInitialLocation(BoardCoordinates.of("c2"));
        promotionMoveBlack1.setFinalLocation(BoardCoordinates.of("c1"));
        promotionMoveBlack1.setMoveMetadata(Map.entry(MoveMetadata.PROMOTION, List.of(ChessPieceNotation.QUEEN)));

        ChessMove capturePromotionMoveBlack1 = new ChessMove(20);
        capturePromotionMoveBlack1.setInitialLocation(BoardCoordinates.of("c2"));
        capturePromotionMoveBlack1.setFinalLocation(BoardCoordinates.of("d1"));
        capturePromotionMoveBlack1.setMoveMetadata(Map.entry(MoveMetadata.CAPTURE_PROMOTION, List.of(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE), ChessPieceNotation.QUEEN)));

        ChessMove intermediateMove2 = new ChessMove(1);
        intermediateMove2.setInitialLocation(BoardCoordinates.of("c2"));
        intermediateMove2.setFinalLocation(BoardCoordinates.of("c4"));

        ChessMove blackIntermediateMove1 = new ChessMove(0);
        blackIntermediateMove1.setInitialLocation(BoardCoordinates.of("d4"));
        blackIntermediateMove1.setFinalLocation(BoardCoordinates.of("c3"));
        blackIntermediateMove1.setMoveMetadata(Map.entry(MoveMetadata.ENPASSANT_ENABLED, List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))));

        ChessMove blackIntermediateMove2 = new ChessMove(0);
        blackIntermediateMove2.setInitialLocation(BoardCoordinates.of("f7"));
        blackIntermediateMove2.setFinalLocation(BoardCoordinates.of("f5"));

        ChessMove intermediateMove3 = new ChessMove(0);
        intermediateMove3.setInitialLocation(BoardCoordinates.of("g2"));
        intermediateMove3.setFinalLocation(BoardCoordinates.of("g4"));

        ChessMove blackIntermediateMove3 = new ChessMove(0);
        blackIntermediateMove3.setInitialLocation(BoardCoordinates.of("f4"));
        blackIntermediateMove3.setFinalLocation(BoardCoordinates.of("g3"));
        blackIntermediateMove3.setMoveMetadata(Map.entry(MoveMetadata.ENPASSANT_ENABLED, List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))));

        ChessMove castleWhiteShort = new ChessMove(4);
        castleWhiteShort.setInitialLocation(BoardCoordinates.of("e1"));
        castleWhiteShort.setFinalLocation(BoardCoordinates.of("g1"));
        castleWhiteShort.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_SHORT, List.of(Boolean.TRUE)));

        ChessMove castleBlackShort = new ChessMove(0);
        castleBlackShort.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackShort.setFinalLocation(BoardCoordinates.of("g8"));
        castleBlackShort.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_SHORT, List.of(Boolean.TRUE)));

        ChessMove castleBlackLong = new ChessMove(4);
        castleBlackLong.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackLong.setFinalLocation(BoardCoordinates.of("c8"));
        castleBlackLong.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_LONG, List.of(Boolean.TRUE)));

        ChessMove castleBlackLongCheck = new ChessMove(4);
        castleBlackLongCheck.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackLongCheck.setFinalLocation(BoardCoordinates.of("c8"));
        castleBlackLongCheck.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_LONG, List.of(Boolean.TRUE)));

        ChessMove knightMove1 = new ChessMove(0);
        knightMove1.setInitialLocation(BoardCoordinates.of("b1"));
        knightMove1.setFinalLocation(BoardCoordinates.of("d2"));

        ChessMove rookMove1 = new ChessMove(0);
        rookMove1.setInitialLocation(BoardCoordinates.of("a1"));
        rookMove1.setFinalLocation(BoardCoordinates.of("b1"));

        ChessMove rookMove2 = new ChessMove(0);
        rookMove2.setInitialLocation(BoardCoordinates.of("f1"));
        rookMove2.setFinalLocation(BoardCoordinates.of("f2"));

        ChessMove bishopMove1 = new ChessMove(1);
        bishopMove1.setInitialLocation(BoardCoordinates.of("b2"));
        bishopMove1.setFinalLocation(BoardCoordinates.of("a3"));

        ChessMove queenMove1 = new ChessMove(0);
        queenMove1.setInitialLocation(BoardCoordinates.of("d2"));
        queenMove1.setFinalLocation(BoardCoordinates.of("d5"));
        queenMove1.setMoveMetadata(Map.entry(MoveMetadata.CAPTURE, List.of(ChessPiece.of(ChessPieceNotation.KNIGHT, ChessPieceColor.BLACK))));


        return Stream.of(
                Arguments.of(ChessGameUtils.FEN_INITIAL, initialMove1, "e4"),
                Arguments.of(ChessGameUtils.FEN_INITIAL, initialMove2, "Nc3"),
                Arguments.of("rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1", initialMoveBlack1, "Nc6"),
                Arguments.of("rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1", initialMoveBlack2, "d5"),
                Arguments.of("rn3rk1/ppq1bbpp/2p2p2/8/1PNPp3/P3P1PP/1B3PBK/2RQ1R2 w - - 4 19", intermediateMove1, "Bxe4"),
                Arguments.of("8/3P4/2BK1p2/5k2/8/R1P5/8/6r1 w - - 5 47", promotionMove1, "d8=Q"),
                Arguments.of("8/2R5/5r2/8/1N6/6KP/2p3P1/1k6 b - - 16 74", promotionMoveBlack1, "c1=Q"),
                Arguments.of("8/8/8/4r3/1N6/6KP/2p3P1/1k1R4 b - - 20 76", capturePromotionMoveBlack1, "cxd1=Q"),
                Arguments.of("6nk/2Q3R1/1p3p2/p4q1p/3p4/1P4P1/P1P3PK/8 w - - 1 40", intermediateMove2, "c4"),
                Arguments.of("6nk/2Q3R1/1p3p2/p4q1p/2Pp4/1P4P1/P5PK/8 b - c3 0 40", blackIntermediateMove1, "dxc3"),
                Arguments.of("rnbqkbnr/pp1ppppp/2p5/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2", blackIntermediateMove2, "f5"),
                Arguments.of("rnbqkbnr/pp1pp1pp/2p1P3/8/5p2/8/PPPP1PPP/RNBQKBNR w KQkq - 0 4", intermediateMove3, "g4"),
                Arguments.of("rnbqkbnr/pp1pp1pp/2p1P3/8/5pP1/8/PPPP1P1P/RNBQKBNR b KQkq g3 0 4", blackIntermediateMove3, "fxg3"),
                Arguments.of("rnb1kb1r/pp2p1pp/2p1pn2/3q4/8/5NP1/PPPP1PB1/RNBQK2R w KQkq - 4 8", castleWhiteShort, "O-O"),
                Arguments.of("rnbqk2r/pp2p1bp/2p1pnp1/3P4/8/2N2NP1/PPP2PB1/R1BQ1RK1 b kq - 0 11", castleBlackShort, "O-O"),
                Arguments.of("r3k2r/ppqbp1bp/n1p1pnp1/8/3P4/1PN3P1/PBPQ1PB1/R3NRK1 b kq - 4 14", castleBlackLong, "O-O-O"),
                Arguments.of("r3k2r/1pq1p1bp/p1p1b1pn/4P3/1n6/1PN2PP1/PBP1Q1B1/R2KNR2 b kq - 4 20", castleBlackLongCheck, "O-O-O+"),
                Arguments.of("rnbqkb1r/ppp2ppp/3p1n2/4p3/4P3/3P1N2/PPP2PPP/RNBQKB1R w KQkq - 0 4", knightMove1, "Nbd2"),
                Arguments.of("rnbqkb1r/pp3p1p/2p2np1/3pp3/4P3/2NPBN2/PPPQBPPP/R4RK1 w kq - 0 9", rookMove1, "Rab1"),
                Arguments.of("rnbqkb1r/pp5p/2p3p1/3n4/5P2/2NpBR2/PPPQB1PP/5RK1 w kq - 0 14", rookMove2, "R1f2"),
                Arguments.of("rnbqkb1r/pp5p/2p3p1/3n4/5P2/2N1B1R1/Pb1QBRPP/6K1 b kq - 1 17", bishopMove1, "Bba3"),
                Arguments.of("rnb1q1Q1/p2kb3/1pp4p/3n4/8/b1N1B1R1/P2QBRPP/6K1 w - - 0 23", queenMove1, "Qdxd5+")
        );
    }

    static Stream<Arguments> positionRollback() {
        ChessMove initialMove1 = new ChessMove(0);
        initialMove1.setInitialLocation(BoardCoordinates.of("e2"));
        initialMove1.setFinalLocation(BoardCoordinates.of("e4"));

        ChessMove initialMove2 = new ChessMove(0);
        initialMove2.setInitialLocation(BoardCoordinates.of("b1"));
        initialMove2.setFinalLocation(BoardCoordinates.of("c3"));

        ChessMove initialMoveBlack1 = new ChessMove(1);
        initialMoveBlack1.setInitialLocation(BoardCoordinates.of("b8"));
        initialMoveBlack1.setFinalLocation(BoardCoordinates.of("c6"));

        ChessMove initialMoveBlack2 = new ChessMove(1);
        initialMoveBlack2.setInitialLocation(BoardCoordinates.of("d7"));
        initialMoveBlack2.setFinalLocation(BoardCoordinates.of("d5"));

        ChessMove intermediateMove1 = new ChessMove(4);
        intermediateMove1.setInitialLocation(BoardCoordinates.of("g2"));
        intermediateMove1.setFinalLocation(BoardCoordinates.of("e4"));
        intermediateMove1.setMoveMetadata(Map.entry(MoveMetadata.CAPTURE, List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))));

        ChessMove promotionMove1 = new ChessMove(5);
        promotionMove1.setInitialLocation(BoardCoordinates.of("d7"));
        promotionMove1.setFinalLocation(BoardCoordinates.of("d8"));
        promotionMove1.setMoveMetadata(Map.entry(MoveMetadata.PROMOTION, List.of(ChessPieceNotation.QUEEN)));

        ChessMove promotionMoveBlack1 = new ChessMove(16);
        promotionMoveBlack1.setInitialLocation(BoardCoordinates.of("c2"));
        promotionMoveBlack1.setFinalLocation(BoardCoordinates.of("c1"));
        promotionMoveBlack1.setMoveMetadata(Map.entry(MoveMetadata.PROMOTION, List.of(ChessPieceNotation.QUEEN)));

        ChessMove capturePromotionMoveBlack1 = new ChessMove(20);
        capturePromotionMoveBlack1.setInitialLocation(BoardCoordinates.of("c2"));
        capturePromotionMoveBlack1.setFinalLocation(BoardCoordinates.of("d1"));
        capturePromotionMoveBlack1.setMoveMetadata(Map.entry(MoveMetadata.CAPTURE_PROMOTION, List.of(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE), ChessPieceNotation.QUEEN)));

        ChessMove intermediateMove2 = new ChessMove(1);
        intermediateMove2.setInitialLocation(BoardCoordinates.of("c2"));
        intermediateMove2.setFinalLocation(BoardCoordinates.of("c4"));

        ChessMove blackIntermediateMove1 = new ChessMove(0);
        blackIntermediateMove1.setInitialLocation(BoardCoordinates.of("d4"));
        blackIntermediateMove1.setFinalLocation(BoardCoordinates.of("c3"));
        blackIntermediateMove1.setMoveMetadata(Map.entry(MoveMetadata.ENPASSANT_ENABLED, List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))));

        ChessMove blackIntermediateMove2 = new ChessMove(0);
        blackIntermediateMove2.setInitialLocation(BoardCoordinates.of("f7"));
        blackIntermediateMove2.setFinalLocation(BoardCoordinates.of("f5"));

        ChessMove intermediateMove3 = new ChessMove(0);
        intermediateMove3.setInitialLocation(BoardCoordinates.of("g2"));
        intermediateMove3.setFinalLocation(BoardCoordinates.of("g4"));

        ChessMove blackIntermediateMove3 = new ChessMove(0);
        blackIntermediateMove3.setInitialLocation(BoardCoordinates.of("f4"));
        blackIntermediateMove3.setFinalLocation(BoardCoordinates.of("g3"));

        blackIntermediateMove3.setMoveMetadata(Map.entry(MoveMetadata.ENPASSANT_ENABLED, List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.WHITE))));

        ChessMove castleWhiteShort = new ChessMove(4);
        castleWhiteShort.setInitialLocation(BoardCoordinates.of("e1"));
        castleWhiteShort.setFinalLocation(BoardCoordinates.of("g1"));
        castleWhiteShort.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_SHORT, List.of(Boolean.TRUE)));

        ChessMove castleBlackShort = new ChessMove(0);
        castleBlackShort.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackShort.setFinalLocation(BoardCoordinates.of("g8"));
        castleBlackShort.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_SHORT, List.of(Boolean.TRUE)));

        ChessMove castleBlackLong = new ChessMove(4);
        castleBlackLong.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackLong.setFinalLocation(BoardCoordinates.of("c8"));
        castleBlackLong.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_LONG, List.of(Boolean.TRUE)));

        ChessMove intermediateMove4 = new ChessMove(0);
        intermediateMove4.setInitialLocation(BoardCoordinates.of("a5"));
        intermediateMove4.setFinalLocation(BoardCoordinates.of("b6"));
        intermediateMove4.setMoveMetadata(Map.entry(MoveMetadata.ENPASSANT_ENABLED, List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))));

        ChessMove castleBlackLong2 = new ChessMove(6);
        castleBlackLong2.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackLong2.setFinalLocation(BoardCoordinates.of("c8"));
        castleBlackLong2.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_LONG, List.of(Boolean.FALSE)));

        return Stream.of(
                Arguments.of("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq - 0 1", initialMove1, ChessGameUtils.FEN_INITIAL),
                Arguments.of("rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1", initialMove2, ChessGameUtils.FEN_INITIAL),
                Arguments.of("r1bqkbnr/pppppppp/2n5/8/8/2N5/PPPPPPPP/R1BQKBNR w KQkq - 2 2", initialMoveBlack1, "rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1"),
                Arguments.of("rnbqkbnr/ppp1pppp/8/3p4/8/2N5/PPPPPPPP/R1BQKBNR w KQkq - 0 2", initialMoveBlack2, "rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1"),
                Arguments.of("rn3rk1/ppq1bbpp/2p2p2/8/1PNPB3/P3P1PP/1B3P1K/2RQ1R2 b - - 0 19", intermediateMove1, "rn3rk1/ppq1bbpp/2p2p2/8/1PNPp3/P3P1PP/1B3PBK/2RQ1R2 w - - 4 19"),
                Arguments.of("3Q4/8/2BK1p2/5k2/8/R1P5/8/6r1 b - - 0 47", promotionMove1, "8/3P4/2BK1p2/5k2/8/R1P5/8/6r1 w - - 5 47"),
                Arguments.of("8/2R5/5r2/8/1N6/6KP/6P1/1kq5 w - - 0 75", promotionMoveBlack1, "8/2R5/5r2/8/1N6/6KP/2p3P1/1k6 b - - 16 74"),
                Arguments.of("8/8/8/4r3/1N6/6KP/6P1/1k1q4 w - - 0 77", capturePromotionMoveBlack1, "8/8/8/4r3/1N6/6KP/2p3P1/1k1R4 b - - 20 76"),
                Arguments.of("6nk/2Q3R1/1p3p2/p4q1p/2Pp4/1P4P1/P5PK/8 b - c3 0 40", intermediateMove2, "6nk/2Q3R1/1p3p2/p4q1p/3p4/1P4P1/P1P3PK/8 w - - 1 40"),
                Arguments.of("6nk/2Q3R1/1p3p2/p4q1p/8/1Pp3P1/P5PK/8 w - - 0 41", blackIntermediateMove1, "6nk/2Q3R1/1p3p2/p4q1p/2Pp4/1P4P1/P5PK/8 b - c3 0 40"),
                Arguments.of("rnbqkbnr/pp1pp1pp/2p5/4Pp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3", blackIntermediateMove2, "rnbqkbnr/pp1ppppp/2p5/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2"),
                Arguments.of("rnbqkbnr/pp1pp1pp/2p1P3/8/5pP1/8/PPPP1P1P/RNBQKBNR b KQkq g3 0 4", intermediateMove3, "rnbqkbnr/pp1pp1pp/2p1P3/8/5p2/8/PPPP1PPP/RNBQKBNR w KQkq - 0 4"),
                Arguments.of("rnbqkbnr/pp1pp1pp/2p1P3/8/8/6p1/PPPP1P1P/RNBQKBNR w KQkq - 0 5", blackIntermediateMove3, "rnbqkbnr/pp1pp1pp/2p1P3/8/5pP1/8/PPPP1P1P/RNBQKBNR b KQkq g3 0 4"),
                Arguments.of("rnb1kb1r/pp2p1pp/2p1pn2/3q4/8/5NP1/PPPP1PB1/RNBQ1RK1 b kq - 5 8", castleWhiteShort, "rnb1kb1r/pp2p1pp/2p1pn2/3q4/8/5NP1/PPPP1PB1/RNBQK2R w KQkq - 4 8"),
                Arguments.of("rnbq1rk1/pp2p1bp/2p1pnp1/3P4/8/2N2NP1/PPP2PB1/R1BQ1RK1 w - - 1 12", castleBlackShort, "rnbqk2r/pp2p1bp/2p1pnp1/3P4/8/2N2NP1/PPP2PB1/R1BQ1RK1 b kq - 0 11"),
                Arguments.of("2kr3r/ppqbp1bp/n1p1pnp1/8/3P4/1PN3P1/PBPQ1PB1/R3NRK1 w - - 5 15", castleBlackLong, "r3k2r/ppqbp1bp/n1p1pnp1/8/3P4/1PN3P1/PBPQ1PB1/R3NRK1 b kq - 4 14"),
                Arguments.of("2kr3r/p2bp1bp/nPpqpnp1/8/3P4/1PN3P1/1BPQ1PB1/R3NRK1 b - - 0 17", intermediateMove4, "2kr3r/p2bp1bp/n1pqpnp1/Pp6/3P4/1PN3P1/1BPQ1PB1/R3NRK1 w - b6 0 17"),
                Arguments.of("2kr2r1/ppqbp1bp/n1p1pnp1/8/3P4/1PN1Q1P1/PBP2PB1/R3NRK1 w - - 7 16", castleBlackLong2, "r3k1r1/ppqbp1bp/n1p1pnp1/8/3P4/1PN1Q1P1/PBP2PB1/R3NRK1 b q - 6 15")
        );
    }

    static Stream<Arguments> positionUpdate() {
        ChessMove initialMove1 = new ChessMove(0);
        initialMove1.setInitialLocation(BoardCoordinates.of("e2"));
        initialMove1.setFinalLocation(BoardCoordinates.of("e4"));
        ChessMove initialMove2 = new ChessMove(0);
        initialMove2.setInitialLocation(BoardCoordinates.of("b1"));
        initialMove2.setFinalLocation(BoardCoordinates.of("c3"));

        ChessMove initialMoveBlack1 = new ChessMove(1);
        initialMoveBlack1.setInitialLocation(BoardCoordinates.of("b8"));
        initialMoveBlack1.setFinalLocation(BoardCoordinates.of("c6"));

        ChessMove initialMoveBlack2 = new ChessMove(1);
        initialMoveBlack2.setInitialLocation(BoardCoordinates.of("d7"));
        initialMoveBlack2.setFinalLocation(BoardCoordinates.of("d5"));

        ChessMove intermediateMove1 = new ChessMove(4);
        intermediateMove1.setInitialLocation(BoardCoordinates.of("g2"));
        intermediateMove1.setFinalLocation(BoardCoordinates.of("e4"));

        ChessMove intermediateMove2 = new ChessMove(1);
        intermediateMove2.setInitialLocation(BoardCoordinates.of("c2"));
        intermediateMove2.setFinalLocation(BoardCoordinates.of("c4"));

        ChessMove intermediateMove3 = new ChessMove(0);
        intermediateMove3.setInitialLocation(BoardCoordinates.of("g2"));
        intermediateMove3.setFinalLocation(BoardCoordinates.of("g4"));

        ChessMove blackIntermediateMove1 = new ChessMove(0);
        blackIntermediateMove1.setInitialLocation(BoardCoordinates.of("d4"));
        blackIntermediateMove1.setFinalLocation(BoardCoordinates.of("c3"));

        ChessMove blackIntermediateMove2 = new ChessMove(0);
        blackIntermediateMove2.setInitialLocation(BoardCoordinates.of("f7"));
        blackIntermediateMove2.setFinalLocation(BoardCoordinates.of("f5"));

        ChessMove blackIntermediateMove3 = new ChessMove(0);
        blackIntermediateMove3.setInitialLocation(BoardCoordinates.of("f4"));
        blackIntermediateMove3.setFinalLocation(BoardCoordinates.of("g3"));


        ChessMove promotionMove1 = new ChessMove(5);
        promotionMove1.setInitialLocation(BoardCoordinates.of("d7"));
        promotionMove1.setFinalLocation(BoardCoordinates.of("d8"));
        promotionMove1.setMoveMetadata(Map.entry(MoveMetadata.PROMOTION, List.of(ChessPieceNotation.QUEEN)));

        ChessMove promotionMoveBlack1 = new ChessMove(16);
        promotionMoveBlack1.setInitialLocation(BoardCoordinates.of("c2"));
        promotionMoveBlack1.setFinalLocation(BoardCoordinates.of("c1"));
        promotionMoveBlack1.setMoveMetadata(Map.entry(MoveMetadata.PROMOTION, List.of(ChessPieceNotation.QUEEN)));

        ChessMove capturePromotionMoveBlack1 = new ChessMove(20);
        capturePromotionMoveBlack1.setInitialLocation(BoardCoordinates.of("c2"));
        capturePromotionMoveBlack1.setFinalLocation(BoardCoordinates.of("d1"));
        capturePromotionMoveBlack1.setMoveMetadata(Map.entry(MoveMetadata.CAPTURE_PROMOTION, List.of(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE), ChessPieceNotation.QUEEN)));

        ChessMove castleWhiteShort = new ChessMove(4);
        castleWhiteShort.setInitialLocation(BoardCoordinates.of("e1"));
        castleWhiteShort.setFinalLocation(BoardCoordinates.of("g1"));
        castleWhiteShort.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_SHORT, List.of()));

        ChessMove castleBlackShort = new ChessMove(0);
        castleBlackShort.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackShort.setFinalLocation(BoardCoordinates.of("g8"));
        castleBlackShort.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_SHORT, List.of()));

        ChessMove castleBlackLong = new ChessMove(4);
        castleBlackLong.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackLong.setFinalLocation(BoardCoordinates.of("c8"));
        castleBlackLong.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_LONG, List.of()));

        ChessMove intermediateMove4 = new ChessMove(0);
        intermediateMove4.setInitialLocation(BoardCoordinates.of("a5"));
        intermediateMove4.setFinalLocation(BoardCoordinates.of("b6"));
        intermediateMove4.setMoveMetadata(Map.entry(MoveMetadata.ENPASSANT_ENABLED, List.of(ChessPiece.of(ChessPieceNotation.PAWN, ChessPieceColor.BLACK))));


        ChessMove castleBlackLong2 = new ChessMove(6);
        castleBlackLong2.setInitialLocation(BoardCoordinates.of("e8"));
        castleBlackLong2.setFinalLocation(BoardCoordinates.of("c8"));
        castleBlackLong2.setMoveMetadata(Map.entry(MoveMetadata.CASTLE_LONG, List.of()));


        return Stream.of(
                Arguments.of(ChessGameUtils.FEN_INITIAL, initialMove1, "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq - 0 1"),
                Arguments.of(ChessGameUtils.FEN_INITIAL, initialMove2, "rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1"),
                Arguments.of("rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1", initialMoveBlack1, "r1bqkbnr/pppppppp/2n5/8/8/2N5/PPPPPPPP/R1BQKBNR w KQkq - 2 2"),
                Arguments.of("rnbqkbnr/pppppppp/8/8/8/2N5/PPPPPPPP/R1BQKBNR b KQkq - 1 1", initialMoveBlack2, "rnbqkbnr/ppp1pppp/8/3p4/8/2N5/PPPPPPPP/R1BQKBNR w KQkq - 0 2"),
                Arguments.of("rn3rk1/ppq1bbpp/2p2p2/8/1PNPp3/P3P1PP/1B3PBK/2RQ1R2 w - - 4 19", intermediateMove1, "rn3rk1/ppq1bbpp/2p2p2/8/1PNPB3/P3P1PP/1B3P1K/2RQ1R2 b - - 0 19"),
                Arguments.of("8/3P4/2BK1p2/5k2/8/R1P5/8/6r1 w - - 5 47", promotionMove1, "3Q4/8/2BK1p2/5k2/8/R1P5/8/6r1 b - - 0 47"),
                Arguments.of("8/2R5/5r2/8/1N6/6KP/2p3P1/1k6 b - - 16 74", promotionMoveBlack1, "8/2R5/5r2/8/1N6/6KP/6P1/1kq5 w - - 0 75"),
                Arguments.of("8/8/8/4r3/1N6/6KP/2p3P1/1k1R4 b - - 20 76", capturePromotionMoveBlack1, "8/8/8/4r3/1N6/6KP/6P1/1k1q4 w - - 0 77"),
                Arguments.of("6nk/2Q3R1/1p3p2/p4q1p/3p4/1P4P1/P1P3PK/8 w - - 1 40", intermediateMove2, "6nk/2Q3R1/1p3p2/p4q1p/2Pp4/1P4P1/P5PK/8 b - c3 0 40"),
                Arguments.of("6nk/2Q3R1/1p3p2/p4q1p/2Pp4/1P4P1/P5PK/8 b - c3 0 40", blackIntermediateMove1, "6nk/2Q3R1/1p3p2/p4q1p/8/1Pp3P1/P5PK/8 w - - 0 41"),
                Arguments.of("rnbqkbnr/pp1ppppp/2p5/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2", blackIntermediateMove2, "rnbqkbnr/pp1pp1pp/2p5/4Pp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3"),
                Arguments.of("rnbqkbnr/pp1pp1pp/2p1P3/8/5p2/8/PPPP1PPP/RNBQKBNR w KQkq - 0 4", intermediateMove3, "rnbqkbnr/pp1pp1pp/2p1P3/8/5pP1/8/PPPP1P1P/RNBQKBNR b KQkq g3 0 4"),
                Arguments.of("rnbqkbnr/pp1pp1pp/2p1P3/8/5pP1/8/PPPP1P1P/RNBQKBNR b KQkq g3 0 4", blackIntermediateMove3, "rnbqkbnr/pp1pp1pp/2p1P3/8/8/6p1/PPPP1P1P/RNBQKBNR w KQkq - 0 5"),
                Arguments.of("rnb1kb1r/pp2p1pp/2p1pn2/3q4/8/5NP1/PPPP1PB1/RNBQK2R w KQkq - 4 8", castleWhiteShort, "rnb1kb1r/pp2p1pp/2p1pn2/3q4/8/5NP1/PPPP1PB1/RNBQ1RK1 b kq - 5 8"),
                Arguments.of("rnbqk2r/pp2p1bp/2p1pnp1/3P4/8/2N2NP1/PPP2PB1/R1BQ1RK1 b kq - 0 11", castleBlackShort, "rnbq1rk1/pp2p1bp/2p1pnp1/3P4/8/2N2NP1/PPP2PB1/R1BQ1RK1 w - - 1 12"),
                Arguments.of("r3k2r/ppqbp1bp/n1p1pnp1/8/3P4/1PN3P1/PBPQ1PB1/R3NRK1 b kq - 4 14", castleBlackLong, "2kr3r/ppqbp1bp/n1p1pnp1/8/3P4/1PN3P1/PBPQ1PB1/R3NRK1 w - - 5 15"),
                Arguments.of("2kr3r/p2bp1bp/n1pqpnp1/Pp6/3P4/1PN3P1/1BPQ1PB1/R3NRK1 w - b6 0 17", intermediateMove4, "2kr3r/p2bp1bp/nPpqpnp1/8/3P4/1PN3P1/1BPQ1PB1/R3NRK1 b - - 0 17"),
                Arguments.of("r3k1r1/ppqbp1bp/n1p1pnp1/8/3P4/1PN1Q1P1/PBP2PB1/R3NRK1 b q - 6 15", castleBlackLong2, "2kr2r1/ppqbp1bp/n1p1pnp1/8/3P4/1PN1Q1P1/PBP2PB1/R3NRK1 w - - 7 16")
        );
    }

    static Stream<Arguments> availableMovesForPositionAndCoordinates() {
        return Stream
                .of(Arguments.of("2r5/RP4b1/6pk/4p2p/1B2N2P/P2K1nP1/8/8 w - - 7 48", BoardCoordinates.of("b7"),
                                "[[b7b8r], [b7b8n], [b7b8b], [b7b8q], [b7c8r], [b7c8n], [b7c8b], [b7c8q]]"),
                        Arguments.of("2k4r/ppp2pp1/n2p2q1/3P4/2P1r3/B4K2/P2Q1P1P/5R2 b - - 3 29", BoardCoordinates.of("a7"), "[]"),
                        Arguments.of("6nk/2Q3R1/1p3p2/p4q1p/2Pp4/1P4P1/P5PK/8 b - c3 0 40", BoardCoordinates.of("d4"), "[[d4d3], [d4c3]]"),
                        Arguments.of("rnbqkbnr/pp1pp1pp/2p1P3/8/5pP1/8/PPPP1P1P/RNBQKBNR b KQkq g3 0 4", BoardCoordinates.of("f4"), "[[f4f3], [f4g3]]"));
    }

    static Stream<Arguments> availableMovesForPosition() {
        return Stream
                .of(Arguments.of("r7/ppkn3Q/2p5/8/1q6/3B4/2PKRrPP/7R w - - 2 23",
                                "[[c2c3], [d2c1], [d2e3], [d2d1]]"),
                        Arguments.of("2k4r/ppp2pp1/n2p2q1/3P4/2P1r3/B4K2/P2Q1P1P/5R2 b - - 3 29",
                                "[[e4e5], [e4e6], [e4e7], [e4e8], [e4e3], [e4e2], [e4e1], [e4d4], [e4c4], [e4f4], [e4g4], [e4h4], [a6c5], [a6b8], [a6b4], [g6f5], [g6h7], [g6h5], [g6g5], [g6g4], [g6g3], [g6g2], [g6g1], [g6f6], [g6e6], [g6h6], [b7b6], [b7b5], [c7c6], [c7c5], [f7f6], [f7f5], [c8d7], [c8b8], [c8d8], [h8h7], [h8h6], [h8h5], [h8h4], [h8h3], [h8h2], [h8g8], [h8f8], [h8e8], [h8d8]]"),
                        Arguments.of("6nk/2Q3R1/1p3p2/p4q1p/2Pp4/1P4P1/P5PK/8 b - c3 0 40",
                                "[[d4d3], [d4c3], [a5a4], [f5e6], [f5d7], [f5c8], [f5e4], [f5d3], [f5c2], [f5b1], [f5g6], [f5h7], [f5g4], [f5h3], [f5f4], [f5f3], [f5f2], [f5f1], [f5e5], [f5d5], [f5c5], [f5b5], [f5g5], [h5h4], [b6b5], [g8e7], [g8h6]]"),
                        Arguments.of("rnbqkbnr/pp1pp1pp/2p5/4Pp1Q/8/8/PPPP1PPP/RNB1KBNR b KQkq - 1 3",
                                "[[g7g6]]"));
    }

}
