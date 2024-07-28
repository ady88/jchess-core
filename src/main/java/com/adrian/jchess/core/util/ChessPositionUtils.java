package com.adrian.jchess.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.adrian.jchess.core.model.moves.MoveMetadata;
import org.apache.commons.lang3.StringUtils;

import com.adrian.jchess.core.model.AbstractChessPiece;
import com.adrian.jchess.core.model.BoardSquare;
import com.adrian.jchess.core.model.ChessPiece;
import com.adrian.jchess.core.model.ChessPieceColor;
import com.adrian.jchess.core.model.ChessPieceNotation;
import com.adrian.jchess.core.model.ChessPosition;
import com.adrian.jchess.core.model.NoChessPiece;
import com.adrian.jchess.core.model.coordinates.BoardCoordinates;
import com.adrian.jchess.core.model.coordinates.BoardCoordinatesUtils;
import com.adrian.jchess.core.model.coordinates.FileNotation;
import com.adrian.jchess.core.model.coordinates.RankNotation;
import com.adrian.jchess.core.model.moves.ChessMove;
import com.adrian.jchess.core.model.moves.MoveGeneratorByMoveRules;

public class ChessPositionUtils {
    private static final Logger LOG = Logger.getLogger(ChessPositionUtils.class.getName());

    private static final String EMPTY_SQUARE = " ";

    public static ChessPosition getNewChessPosition() {
        BoardSquare[] squares = new BoardSquare[ChessGameUtils.BOARD_SQUARES];
        ChessPieceColor gameSideToMove = ChessPieceColor.WHITE;

        final ChessPosition position = new ChessPosition(squares, gameSideToMove, true, true, true, true, null, 0, 1, null, null);

        position.setInitialPosition();

        return position;
    }

    public static void updatePositionFromFenString(final ChessPosition position, final String fen) {
        BoardSquare[] squares = position.getSquares();

        for (RankNotation rankIndex : RankNotation.values()) {
            for (FileNotation fileIndex : FileNotation.values()) {
                position.setPiece(NoChessPiece.instance(), BoardCoordinates.of(fileIndex, rankIndex));
            }
        }

        BoardSquare[] kingSquares = setupSquaresFromFen(squares, fen);
        position.setBlackKingSquare(kingSquares[1]);
        position.setWhiteKingSquare(kingSquares[0]);
        ChessPieceColor gameSideToMove = null;
        boolean castleKingSideWhite = false;
        boolean castleQueenSideWhite = false;
        boolean castleKingSideBlack = false;
        boolean castleQueenSideBlack = false;
        if (StringUtils.isEmpty(fen)) {
            LOG.info("Fen is empty, position will not be updated!");
            return;
        }

        String[] fenParts = fen.split(EMPTY_SQUARE);

        final String sideToMove = fenParts[1];
        if (StringUtils.isNotBlank(sideToMove)) {
            gameSideToMove = ChessPieceColor.getByColorNotation(sideToMove);
        }

        final String catleRights = fenParts[2];

        for (int i = 0; i < catleRights.length(); i++) {
            char c = catleRights.charAt(i);
            switch (c) {
                case 'K':
                    castleKingSideWhite = true;
                    break;
                case 'k':
                    castleKingSideBlack = true;
                    break;
                case 'Q':
                    castleQueenSideWhite = true;
                    break;
                case 'q':
                    castleQueenSideBlack = true;
                    break;
                default:
                    castleQueenSideBlack = false;
                    castleKingSideBlack = false;
                    castleQueenSideWhite = false;
                    castleKingSideWhite = false;
                    break;
            }
        }

        final String enPassant = fenParts[3];

        BoardSquare enPassantSquare;
        if ("-".equals(enPassant)) {
            enPassantSquare = null;
        } else {
            enPassantSquare = squares[ChessGameUtils.getLocationIn64Array(BoardCoordinates.of(enPassant))];
        }

        position.setFiftyMoveCounter(Integer.parseInt(fenParts[4]));
        position.setFullMoves(Integer.parseInt(fenParts[5]));
        position.setEnPassantTarget(enPassantSquare);
        position.setBlackKingSideCastle(castleKingSideBlack);
        position.setBlackQueenSideCastle(castleQueenSideBlack);
        position.setWhiteKingSideCastle(castleKingSideWhite);
        position.setWhiteQueenSideCastle(castleQueenSideWhite);
        position.setPlayerToMove(gameSideToMove);
    }

    /**
     * Returns the {@link ChessPosition} instance from the given fen string. Will
     * return null if a empty fen is provided.
     *
     * @param fen
     * @return {@link ChessPosition} instance
     */
    public static ChessPosition getChessPositionFromFen(final String fen) {
        BoardSquare[] squares = new BoardSquare[ChessGameUtils.BOARD_SQUARES];

        for (RankNotation rankIndex : RankNotation.values()) {
            for (FileNotation fileIndex : FileNotation.values()) {
                squares[ChessGameUtils.getLocationIn64Array(BoardCoordinates.of(fileIndex, rankIndex))] = BoardSquare.of(BoardCoordinates.of(fileIndex, rankIndex));
                squares[ChessGameUtils.getLocationIn64Array(BoardCoordinates.of(fileIndex, rankIndex))].setChessPiece(NoChessPiece.instance());
            }
        }

        BoardSquare[] kingSquares = setupSquaresFromFen(squares, fen);
        ChessPieceColor gameSideToMove = null;
        boolean castleKingSideWhite = false;
        boolean castleQueenSideWhite = false;
        boolean castleKingSideBlack = false;
        boolean castleQueenSideBlack = false;
        if (StringUtils.isEmpty(fen)) {
            LOG.info("Fen is empty, position will not be created!");
            return null;
        }

        String[] fenParts = fen.split(EMPTY_SQUARE);

        final String sideToMove = fenParts[1];
        if (StringUtils.isNotBlank(sideToMove)) {
            gameSideToMove = ChessPieceColor.getByColorNotation(sideToMove);
        }

        final String catleRights = fenParts[2];

        for (int i = 0; i < catleRights.length(); i++) {
            char c = catleRights.charAt(i);
            switch (c) {
                case 'K':
                    castleKingSideWhite = true;
                    break;
                case 'k':
                    castleKingSideBlack = true;
                    break;
                case 'Q':
                    castleQueenSideWhite = true;
                    break;
                case 'q':
                    castleQueenSideBlack = true;
                    break;
                default:
                    castleQueenSideBlack = false;
                    castleKingSideBlack = false;
                    castleQueenSideWhite = false;
                    castleKingSideWhite = false;
                    break;
            }
        }

        final String enPassant = fenParts[3];

        BoardSquare enPassantSquare;
        if ("-".equals(enPassant)) {
            enPassantSquare = null;
        } else {
            enPassantSquare = squares[ChessGameUtils.getLocationIn64Array(BoardCoordinates.of(enPassant))];
        }

        final Integer fiftyMoveCounter = Integer.valueOf(fenParts[4]);

        final Integer fullMoveCounter = Integer.valueOf(fenParts[5]);

        return new ChessPosition(squares, gameSideToMove, castleKingSideWhite, castleQueenSideWhite, castleKingSideBlack, castleQueenSideBlack, enPassantSquare, fiftyMoveCounter, fullMoveCounter, kingSquares[0], kingSquares[1]);
    }

    /**
     * Returns the fen text representation of a {@link ChessPosition} instance.
     *
     * @param position
     * @return fen text representation of a {@link ChessPosition} instance
     */
    public static String getFenFromChessPosition(final ChessPosition position) {

        if (position == null) {
            LOG.info("Position is empty, fen will not be created!");
            return null;
        }

        String fen = getFenFromBoard(position.getSquares());
        fen += EMPTY_SQUARE;

        fen += position.getPlayerToMove().getColorNotation();
        fen += EMPTY_SQUARE;

        boolean castleAvailable = false;

        if (position.isWhiteKingSideCastle()) {
            castleAvailable = true;
            fen += "K";
        }
        if (position.isWhiteQueenSideCastle()) {
            castleAvailable = true;
            fen += "Q";
        }

        if (position.isBlackKingSideCastle()) {
            castleAvailable = true;
            fen += "k";
        }
        if (position.isBlackQueenSideCastle()) {
            castleAvailable = true;
            fen += "q";
        }

        if (!castleAvailable) {
            fen += "-";
        }

        fen += EMPTY_SQUARE;

        BoardSquare enPassantTarget = position.getEnPassantTarget();
        fen += enPassantTarget == null ? "-" : enPassantTarget.getCoordinates().getFenNotation();

        fen += EMPTY_SQUARE;

        fen += position.getFiftyMoveCounter();

        fen += EMPTY_SQUARE;

        fen += position.getFullMoves();

        return fen;
    }

    public static boolean isKingChecked(final ChessPosition position, ChessPieceColor playerColor) {
        BoardSquare kingSquare = ChessPieceColor.WHITE.equals(playerColor) ? position.getWhiteKingSquare() : position.getBlackKingSquare();
        return isSquareAttacked(position, kingSquare.getCoordinates(), playerColor);
    }

    /**
     * Checks if the player with the specified color has a specific square attacked.
     *
     * @param position          the given chess position to check if a square is
     *                          attacked
     * @param squareCoordinates the square coordonates to be verified if attacked
     * @return true if the player with the specified color has the specified square
     * attacked, false otherwise
     */
    public static boolean isSquareAttacked(final ChessPosition position, BoardCoordinates squareCoordinates, ChessPieceColor playerColor) {

        ChessPieceColor attackerColor = ChessPieceColor.WHITE.equals(playerColor) ? ChessPieceColor.BLACK : ChessPieceColor.WHITE;

        // is knight attacking the square
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllKnightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && ChessPieceNotation.KNIGHT.equals(attacker.getNotation())) {
                    return true;
                }
            }
        }

        // is king attacking the square
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllSurroundingCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && ChessPieceNotation.KING.equals(attacker.getNotation())) {
                    return true;
                }
            }
        }

        // is pawn attacking the square
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllPawnAttackCoordinates(squareCoordinates, playerColor)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && ChessPieceNotation.PAWN.equals(attacker.getNotation())) {
                    return true;
                }
            }
        }

        // --- is queen or bishop attacking the square
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && (ChessPieceNotation.QUEEN.equals(attacker.getNotation()) || ChessPieceNotation.BISHOP.equals(attacker.getNotation()))) {
                    return true;
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && (ChessPieceNotation.QUEEN.equals(attacker.getNotation()) || ChessPieceNotation.BISHOP.equals(attacker.getNotation()))) {
                    return true;
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && (ChessPieceNotation.QUEEN.equals(attacker.getNotation()) || ChessPieceNotation.BISHOP.equals(attacker.getNotation()))) {
                    return true;
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && (ChessPieceNotation.QUEEN.equals(attacker.getNotation()) || ChessPieceNotation.BISHOP.equals(attacker.getNotation()))) {
                    return true;
                } else {
                    break;
                }
            }
        }
        // --------------------------
        // --- is queen or rook attacking the square
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && (ChessPieceNotation.QUEEN.equals(attacker.getNotation()) || ChessPieceNotation.ROOK.equals(attacker.getNotation()))) {
                    return true;
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && (ChessPieceNotation.QUEEN.equals(attacker.getNotation()) || ChessPieceNotation.ROOK.equals(attacker.getNotation()))) {
                    return true;
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && (ChessPieceNotation.QUEEN.equals(attacker.getNotation()) || ChessPieceNotation.ROOK.equals(attacker.getNotation()))) {
                    return true;
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (attackerColor.equals(attacker.getColor()) && (ChessPieceNotation.QUEEN.equals(attacker.getNotation()) || ChessPieceNotation.ROOK.equals(attacker.getNotation()))) {
                    return true;
                } else {
                    break;
                }
            }
        }

        return false;
    }

    public static List<ChessMove> getAvailableMoves(ChessPosition position) {
        var availableMoves = new ArrayList<ChessMove>();
        for (BoardSquare square : position.getSquares()) {
            availableMoves.addAll(MoveGeneratorByMoveRules.generate(position, square.getCoordinates()));
        }

        return availableMoves;
    }

    public static void rollbackMove(ChessPosition position, ChessMove chessMove) {
        MoveMetadata key = chessMove.getMoveMetadata() == null ? MoveMetadata.NONE : chessMove.getMoveMetadata().getKey();
        AbstractChessPiece movedPiece = position.getPiece(chessMove.getFinalLocation());
        ChessPieceColor moveColor = movedPiece.getColor();
        boolean isPawnMove = ChessPieceNotation.PAWN.equals(movedPiece.getNotation());
        boolean isCapture = false;
        switch (key) {
            case NONE:
                position.setPiece(movedPiece, chessMove.getInitialLocation());
                position.setEmptySquare(chessMove.getFinalLocation());

                if (position.getEnPassantTarget() != null) {
                    position.setEnPassantTarget(null);
                }
                break;
            case CAPTURE:
                isCapture = true;
                ChessPiece capturedPiece = (ChessPiece) chessMove.getMoveMetadata().getValue().stream().findFirst().orElseThrow();
                position.setPiece(capturedPiece, chessMove.getFinalLocation());
                position.setPiece(movedPiece, chessMove.getInitialLocation());
                break;
            case ENPASSANT_ENABLED:
                position.setPiece(movedPiece, chessMove.getInitialLocation());
                position.setEmptySquare(chessMove.getFinalLocation());
                ChessPiece enPassantCapturedPiece = (ChessPiece) chessMove.getMoveMetadata().getValue().stream().findFirst().orElseThrow();
                position.setPiece(enPassantCapturedPiece, BoardCoordinates.of(chessMove.getFinalLocation().getFile(), chessMove.getInitialLocation().getRank()));
                BoardSquare enPassantTarget = BoardSquare.of(BoardCoordinates.of(chessMove.getFinalLocation().getFile(), chessMove.getFinalLocation().getRank()));
                enPassantTarget.setChessPiece(enPassantCapturedPiece);
                position.setEnPassantTarget(enPassantTarget);
                isCapture = true;
                break;
            case CASTLE_LONG:
                position.setPiece(movedPiece, chessMove.getInitialLocation());
                position.setEmptySquare(chessMove.getFinalLocation());
                if (ChessPieceColor.WHITE.equals(moveColor)) {
                    position.setPiece(position.getPiece(BoardCoordinates.of("d1")), BoardCoordinates.of("a1"));
                    position.setEmptySquare(BoardCoordinates.of("d1"));
                    position.setWhiteQueenSideCastle(Boolean.TRUE);
                    position.setWhiteKingSideCastle((Boolean) chessMove.getMoveMetadata().getValue().getFirst());
                } else {
                    position.setPiece(position.getPiece(BoardCoordinates.of("d8")), BoardCoordinates.of("a8"));
                    position.setEmptySquare(BoardCoordinates.of("d8"));
                    position.setBlackQueenSideCastle(Boolean.TRUE);
                    position.setBlackKingSideCastle((Boolean) chessMove.getMoveMetadata().getValue().getFirst());
                }
                break;
            case CASTLE_SHORT:
                position.setPiece(movedPiece, chessMove.getInitialLocation());
                position.setEmptySquare(chessMove.getFinalLocation());
                if (ChessPieceColor.WHITE.equals(moveColor)) {
                    position.setPiece(position.getPiece(BoardCoordinates.of("f1")), BoardCoordinates.of("h1"));
                    position.setEmptySquare(BoardCoordinates.of("f1"));
                    position.setWhiteKingSideCastle(true);
                    position.setWhiteQueenSideCastle((Boolean) chessMove.getMoveMetadata().getValue().getFirst());
                } else {
                    position.setPiece(position.getPiece(BoardCoordinates.of("f8")), BoardCoordinates.of("h8"));
                    position.setEmptySquare(BoardCoordinates.of("f8"));
                    position.setBlackKingSideCastle(true);
                    position.setBlackQueenSideCastle((Boolean) chessMove.getMoveMetadata().getValue().getFirst());
                }
                break;
            case PROMOTION:
                position.setEmptySquare(chessMove.getFinalLocation());
                position.setPiece(ChessPiece.of(ChessPieceNotation.PAWN, movedPiece.getColor()), chessMove.getInitialLocation());
                isCapture = true;
                break;
            case CAPTURE_PROMOTION:
                ChessPiece capturePromotionPiece = (ChessPiece) chessMove.getMoveMetadata().getValue().getFirst();
                position.setPiece(capturePromotionPiece, chessMove.getFinalLocation());
                position.setPiece(ChessPiece.of(ChessPieceNotation.PAWN, movedPiece.getColor()), chessMove.getInitialLocation());
                isCapture = true;
                break;
        }

        if (ChessPieceColor.WHITE.equals(position.getPlayerToMove())) {
            position.setFullMoves(position.getFullMoves() - 1);
        }

        position.setFiftyMoveCounter(chessMove.getInitial50MoveCounter());

        ChessPieceColor nextColor = ChessPieceColor.WHITE.equals(moveColor) ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;
        position.setPlayerToMove(nextColor);

        // update king position if king was moved
        if (ChessPieceColor.WHITE.equals(moveColor) && ChessPieceNotation.KING.equals(movedPiece.getNotation())) {
            position.setWhiteKingSquare(position.getSquare(chessMove.getInitialLocation()));
        } else if (ChessPieceColor.BLACK.equals(moveColor) && ChessPieceNotation.KING.equals(movedPiece.getNotation())) {
            position.setBlackKingSquare(position.getSquare(chessMove.getInitialLocation()));
        }
    }

    public static void applyMove(final ChessPosition position, final ChessMove move) {
        MoveMetadata key = move.getMoveMetadata().getKey();
        AbstractChessPiece movedPiece = position.getPiece(move.getInitialLocation());
        ChessPieceColor moveColor = movedPiece.getColor();
        boolean isPawnMove = ChessPieceNotation.PAWN.equals(movedPiece.getNotation());
        boolean isCapture = false;
        switch (key) {
            case NONE:
                position.setPiece(movedPiece, move.getFinalLocation());
                position.setEmptySquare(move.getInitialLocation());
                break;
            case CAPTURE:
                isCapture = true;
            case ENPASSANT_ENABLED:
                position.setPiece(movedPiece, move.getFinalLocation());
                position.setEmptySquare(move.getInitialLocation());
                position.setEmptySquare(BoardCoordinates.of(move.getFinalLocation().getFile(), move.getInitialLocation().getRank()));
                position.setEnPassantTarget(null);
                isCapture = true;
                break;
            case CASTLE_LONG:
                position.setPiece(movedPiece, move.getFinalLocation());
                position.setEmptySquare(move.getInitialLocation());
                if (ChessPieceColor.WHITE.equals(moveColor)) {
                    position.setPiece(position.getPiece(BoardCoordinates.of("a1")), BoardCoordinates.of("d1"));
                    position.setEmptySquare(BoardCoordinates.of("a1"));
                    position.setWhiteQueenSideCastle(false);
                    position.setWhiteKingSideCastle(false);
                } else {
                    position.setPiece(position.getPiece(BoardCoordinates.of("a8")), BoardCoordinates.of("d8"));
                    position.setEmptySquare(BoardCoordinates.of("a8"));
                    position.setBlackQueenSideCastle(false);
                    position.setBlackKingSideCastle(false);
                }
                break;
            case CASTLE_SHORT:
                position.setPiece(movedPiece, move.getFinalLocation());
                position.setEmptySquare(move.getInitialLocation());
                if (ChessPieceColor.WHITE.equals(moveColor)) {
                    position.setPiece(position.getPiece(BoardCoordinates.of("h1")), BoardCoordinates.of("f1"));
                    position.setEmptySquare(BoardCoordinates.of("h1"));
                    position.setWhiteKingSideCastle(false);
                    position.setWhiteQueenSideCastle(false);
                } else {
                    position.setPiece(position.getPiece(BoardCoordinates.of("h8")), BoardCoordinates.of("f8"));
                    position.setEmptySquare(BoardCoordinates.of("h8"));
                    position.setBlackKingSideCastle(false);
                    position.setBlackQueenSideCastle(false);
                }

                break;
            case PROMOTION:

            case CAPTURE_PROMOTION:
                ChessPieceNotation promotionPieceNotation = (ChessPieceNotation) move.getMoveMetadata().getValue().stream().findFirst().orElseThrow();
                ChessPiece piece = (ChessPiece) ChessPiece.of(promotionPieceNotation, moveColor);
                position.setPiece(piece, move.getFinalLocation());
                position.setEmptySquare(move.getInitialLocation());
                isCapture = true;
                break;
        }

        if (ChessPieceColor.BLACK.equals(position.getPlayerToMove())) {
            position.setFullMoves(position.getFullMoves() + 1);
        }

        if (isPawnMove || isCapture) {
            position.setFiftyMoveCounter(0);
        } else {
            position.setFiftyMoveCounter(position.getFiftyMoveCounter() + 1);
        }

        // update king position if king was moved
        if (ChessPieceColor.WHITE.equals(moveColor) && ChessPieceNotation.KING.equals(movedPiece.getNotation())) {
            position.setWhiteKingSquare(position.getSquare(move.getFinalLocation()));
        } else if (ChessPieceColor.BLACK.equals(moveColor) && ChessPieceNotation.KING.equals(movedPiece.getNotation())) {
            position.setBlackKingSquare(position.getSquare(move.getFinalLocation()));
        }

        if (isPawnMove) {
            if (ChessPieceColor.WHITE.equals(moveColor)) {
                if (move.getInitialLocation().getRank().equals(RankNotation.SECOND) && move.getFinalLocation().getRank().equals(RankNotation.FORTH)) {
                    var potentialPiece1 = position.getPiece(BoardCoordinates.of(move.getInitialLocation().getFile().getFileIntNotation() - 1, 3));
                    var potentialPiece2 = position.getPiece(BoardCoordinates.of(move.getInitialLocation().getFile().getFileIntNotation() + 1, 3));
                    if ((ChessPieceNotation.PAWN.equals(potentialPiece1.getNotation()) && ChessPieceColor.BLACK.equals(potentialPiece1.getColor())) || (ChessPieceNotation.PAWN.equals(potentialPiece2.getNotation()) && ChessPieceColor.BLACK.equals(potentialPiece2.getColor()))) {
                        position.setEnPassantTarget(position.getSquare(BoardCoordinates.of(move.getInitialLocation().getFile(), RankNotation.THIRD)));
                    }
                }
            } else {
                if (move.getInitialLocation().getRank().equals(RankNotation.SEVENTH) && move.getFinalLocation().getRank().equals(RankNotation.FIFTH)) {
                    var potentialPiece1 = position.getPiece(BoardCoordinates.of(move.getInitialLocation().getFile().getFileIntNotation() - 1, 4));
                    var potentialPiece2 = position.getPiece(BoardCoordinates.of(move.getInitialLocation().getFile().getFileIntNotation() + 1, 4));
                    if ((ChessPieceNotation.PAWN.equals(potentialPiece1.getNotation()) && ChessPieceColor.WHITE.equals(potentialPiece1.getColor())) || (ChessPieceNotation.PAWN.equals(potentialPiece2.getNotation()) && ChessPieceColor.WHITE.equals(potentialPiece2.getColor()))) {
                        position.setEnPassantTarget(position.getSquare(BoardCoordinates.of(move.getInitialLocation().getFile(), RankNotation.SISTH)));
                    }
                }
            }
        }

        ChessPieceColor nextColor = ChessPieceColor.WHITE.equals(moveColor) ? ChessPieceColor.BLACK : ChessPieceColor.WHITE;
        position.setPlayerToMove(nextColor);
    }

    public static List<ChessMove> getAvailableMovesPerCoordinates(ChessPosition position, BoardCoordinates coordinates) {
        var availableMoves = new ArrayList<ChessMove>();
        MoveGeneratorByMoveRules moveGenerator = new MoveGeneratorByMoveRules();
        availableMoves.addAll(MoveGeneratorByMoveRules.generate(position, coordinates));

        return availableMoves;
    }

    public static String getPgnNotation(final ChessPosition position, final ChessMove chessMove) {
        BoardCoordinates initialLocation = chessMove.getInitialLocation();
        if (position.getPiece(initialLocation) instanceof NoChessPiece) {
            return StringUtils.EMPTY;
        }

        BoardCoordinates finalLocation = chessMove.getFinalLocation();

        ChessPiece movedPiece = (ChessPiece) position.getPiece(initialLocation);
        MoveMetadata moveMetadataKey = chessMove.getMoveMetadata() != null ? chessMove.getMoveMetadata().getKey() : null;

        ChessPosition positionUpdated = new ChessPosition(position);
        positionUpdated.setPiece(movedPiece, finalLocation);
        positionUpdated.setEmptySquare(initialLocation);

        ChessPieceColor opositeSideCheckColor = ChessPieceColor.WHITE.equals(movedPiece.getColor()) ? ChessPieceColor.BLACK : ChessPieceColor.WHITE;


        if (MoveMetadata.CASTLE_SHORT.equals(moveMetadataKey)) {

            if (movedPiece.getColor().equals(ChessPieceColor.WHITE)) {
                positionUpdated.setEmptySquare(BoardCoordinates.of("h1"));
                positionUpdated.setPiece(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE), BoardCoordinates.of("f1"));
            } else {
                positionUpdated.setEmptySquare(BoardCoordinates.of("h8"));
                positionUpdated.setPiece(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.BLACK), BoardCoordinates.of("f8"));
            }

            boolean isKingChecked = isKingChecked(positionUpdated, opositeSideCheckColor);
            String kingCheckedSymbol = isKingChecked ? "+" : StringUtils.EMPTY;

            return "O-O" + kingCheckedSymbol;
        } else if (MoveMetadata.CASTLE_LONG.equals(moveMetadataKey)) {
            if (movedPiece.getColor().equals(ChessPieceColor.WHITE)) {
                positionUpdated.setEmptySquare(BoardCoordinates.of("a1"));
                positionUpdated.setPiece(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.WHITE), BoardCoordinates.of("d1"));
            } else {
                positionUpdated.setEmptySquare(BoardCoordinates.of("a8"));
                positionUpdated.setPiece(ChessPiece.of(ChessPieceNotation.ROOK, ChessPieceColor.BLACK), BoardCoordinates.of("d8"));
            }



            boolean isKingChecked = isKingChecked(positionUpdated, opositeSideCheckColor);
            String kingCheckedSymbol = isKingChecked ? "+" : StringUtils.EMPTY;

            return "O-O-O" + kingCheckedSymbol;
        }

        boolean isKingChecked = isKingChecked(positionUpdated, opositeSideCheckColor);
        String kingCheckedSymbol = isKingChecked ? "+" : StringUtils.EMPTY;

        boolean isCapture = MoveMetadata.CAPTURE.equals(moveMetadataKey) || MoveMetadata.CAPTURE_PROMOTION.equals(moveMetadataKey) || MoveMetadata.ENPASSANT_ENABLED.equals(moveMetadataKey);

        boolean isPromotion = MoveMetadata.PROMOTION.equals(moveMetadataKey) || MoveMetadata.CAPTURE_PROMOTION.equals(moveMetadataKey);


        String captureSymbol = isCapture ? "x" : StringUtils.EMPTY;
        String pawnCaptureInitialFile = isCapture && ChessPieceNotation.PAWN.equals(movedPiece.getNotation()) ? chessMove.getInitialLocation().getFile().getFilePgnNotation() : StringUtils.EMPTY;


        String promotionSymbol = isPromotion ? "=" + ((ChessPieceNotation) chessMove.getMoveMetadata().getValue().getLast()).getPgnNotation()
                : StringUtils.EMPTY;


        String additionalInitialNotation = additionalInitialCoordinateForPgnNotation(position, initialLocation, finalLocation);

        String result = movedPiece.getNotation().getPgnNotation() + additionalInitialNotation + pawnCaptureInitialFile + captureSymbol + finalLocation.getFile().getFilePgnNotation() +
                finalLocation.getRank().getRankTextNotation() + promotionSymbol + kingCheckedSymbol;

        return result;
    }

    private static String getFenFromBoard(final BoardSquare[] board) {
        if (board == null) {
            LOG.info("Board is empty cannot get fen.!");
            return "";
        }
        String fen = "";
        int currentEmptySquareCount = 0;

        for (int i = ChessGameUtils.BOARD_DIMENSION - 1; i >= 0; i--) {
            currentEmptySquareCount = 0;
            for (int j = 0; j < ChessGameUtils.BOARD_DIMENSION; j++) {

                AbstractChessPiece piece = board[ChessGameUtils.getLocationIn64Array(i, j)].getChessPiece();

                if (piece instanceof NoChessPiece) {
                    currentEmptySquareCount++;
                    continue;
                } else {
                    if (currentEmptySquareCount > 0) {
                        fen += currentEmptySquareCount;
                        currentEmptySquareCount = 0;
                    }

                    fen += ChessPiece.getFenNotation(piece);
                }
            }

            if (currentEmptySquareCount > 0) {
                fen += currentEmptySquareCount;
            }

            if (i != 0) {
                fen += "/";
            }
        }

        return fen;
    }

    /**
     * Sets up the board squares from the given fen notation.
     *
     * @param fen the fen chess notation
     * @return the board square array containing the white and black king
     */
    private static BoardSquare[] setupSquaresFromFen(final BoardSquare[] squares, final String fen) {
        if (StringUtils.isEmpty(fen)) {
            return null;
        }

        final String fenBoard = fen.split(" ")[0];

        int rowInt = 7;
        int columnInt = 0;
        BoardSquare[] kingsLocation = new BoardSquare[2];
        for (String row : fenBoard.split("/")) {
            Integer numberEmptySquares = 0;
            columnInt = 0;
            for (char character : row.toCharArray()) {
                ChessPieceNotation notation = ChessPieceNotation.findByFenNotation(character);

                if (notation == null) {
                    numberEmptySquares = Integer.valueOf(String.valueOf(character));

                    while (numberEmptySquares > 0) {
                        BoardCoordinates boardCoordinates = BoardCoordinates.of(columnInt, rowInt);
                        BoardSquare square = squares[ChessGameUtils.getLocationIn64Array(boardCoordinates)];
                        square.setChessPiece(NoChessPiece.instance());
                        numberEmptySquares--;
                        columnInt++;
                    }
                } else {
                    numberEmptySquares = 0;
                    BoardCoordinates boardCoordinates = BoardCoordinates.of(columnInt, rowInt);
                    BoardSquare square = squares[ChessGameUtils.getLocationIn64Array(boardCoordinates)];
                    if (Character.isUpperCase(character)) {
                        AbstractChessPiece chessPiece = ChessPiece.of(notation.getFenNotationWhite());
                        square.setChessPiece(chessPiece);
                        if (ChessPieceNotation.KING.equals(chessPiece.getNotation())) {
                            kingsLocation[0] = square;
                        }
                    } else {
                        AbstractChessPiece chessPiece = ChessPiece.of(notation.getFenNotationBlack());
                        square.setChessPiece(chessPiece);
                        if (ChessPieceNotation.KING.equals(chessPiece.getNotation())) {
                            kingsLocation[1] = square;
                        }

                    }
                    columnInt++;
                }
            }
            rowInt--;
        }
        return kingsLocation;
    }

    private static String additionalInitialCoordinateForPgnNotation(final ChessPosition position, final BoardCoordinates initialLocation, final BoardCoordinates endSquareCoordinates) {
        ChessPiece pieceMoved = (ChessPiece) position.getPiece(initialLocation);

        if (ChessPieceNotation.NONE.equals(pieceMoved.getNotation()) || ChessPieceNotation.PAWN.equals(pieceMoved.getNotation()) || ChessPieceNotation.KING.equals(pieceMoved.getNotation())) {
            return StringUtils.EMPTY;
        }

        String result = StringUtils.EMPTY;

        if (ChessPieceNotation.KNIGHT.equals(pieceMoved.getNotation())) {
            List<BoardCoordinates> knightAttackerSquares = getKnightAttackingSquare(position, endSquareCoordinates, pieceMoved.getColor());
            result = getAdditionalNotation(initialLocation, knightAttackerSquares);
        } else if (ChessPieceNotation.ROOK.equals(pieceMoved.getNotation())) {
            List<BoardCoordinates> rookAttackerSquares = getRookAttackingSquare(position, endSquareCoordinates, pieceMoved.getColor());
            result = getAdditionalNotation(initialLocation, rookAttackerSquares);
        } else if (ChessPieceNotation.BISHOP.equals(pieceMoved.getNotation())) {
            List<BoardCoordinates> bishopAttackerSquares = getBishopAttackingSquare(position, endSquareCoordinates, pieceMoved.getColor());
            result = getAdditionalNotation(initialLocation, bishopAttackerSquares);
        } else if (ChessPieceNotation.QUEEN.equals(pieceMoved.getNotation())) {
            List<BoardCoordinates> queenAttackerSquares = getQueenAttackingSquare(position, endSquareCoordinates, pieceMoved.getColor());
            result = getAdditionalNotation(initialLocation, queenAttackerSquares);
        }

        return result;
    }

    private static String getAdditionalNotation(BoardCoordinates initialLocation, List<BoardCoordinates> attackerSquares) {
        if (attackerSquares.size() <= 1) {
            return StringUtils.EMPTY;
        }
        boolean isSameFile = false;
        boolean isSameRank = false;

        Predicate<BoardCoordinates> boardCoordinatesPredicate = e -> initialLocation.getFile().equals(e.getFile()) && initialLocation.getRank().equals(e.getRank());
        attackerSquares = attackerSquares.stream().filter(boardCoordinatesPredicate.negate()).toList();
        isSameFile = attackerSquares.stream().anyMatch(e-> initialLocation.getFile().equals(e.getFile()));
        isSameRank = attackerSquares.stream().anyMatch(e-> initialLocation.getRank().equals(e.getRank()));
        if (isSameFile && isSameRank) {
            return initialLocation.getFile().getFilePgnNotation() + initialLocation.getRank().getRankTextNotation();
        } else if (isSameFile) {
            return initialLocation.getRank().getRankTextNotation();
        } else {
            return initialLocation.getFile().getFilePgnNotation();
        }
    }

    private static List<BoardCoordinates> getKnightAttackingSquare(final ChessPosition position, final BoardCoordinates squareCoordinates, final ChessPieceColor playerColor) {
        // is knight attacking the square
        List<BoardCoordinates> attackingSquareCoordinates = new ArrayList<>();
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllKnightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.KNIGHT.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                }
            }
        }

        return attackingSquareCoordinates;
    }

    private static List<BoardCoordinates> getRookAttackingSquare(final ChessPosition position, BoardCoordinates squareCoordinates, final ChessPieceColor playerColor) {
        // is rook attacking the square
        List<BoardCoordinates> attackingSquareCoordinates = new ArrayList<>();
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.ROOK.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.ROOK.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.ROOK.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.ROOK.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        return attackingSquareCoordinates;
    }

    private static List<BoardCoordinates> getQueenAttackingSquare(final ChessPosition position, BoardCoordinates squareCoordinates, final ChessPieceColor playerColor) {
        // is queen attacking the square
        List<BoardCoordinates> attackingSquareCoordinates = new ArrayList<>();
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.QUEEN.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.QUEEN.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.QUEEN.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.QUEEN.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.QUEEN.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.QUEEN.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.QUEEN.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.QUEEN.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        return attackingSquareCoordinates;
    }

    private static List<BoardCoordinates> getBishopAttackingSquare(final ChessPosition position, BoardCoordinates squareCoordinates, final ChessPieceColor playerColor) {
        // is bishop attacking the square
        List<BoardCoordinates> attackingSquareCoordinates = new ArrayList<>();
        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.BISHOP.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomRightCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.BISHOP.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllTopLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.BISHOP.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        for (BoardCoordinates coordinates : BoardCoordinatesUtils.getAllBottomLeftCoordinates(squareCoordinates)) {
            if (position.getPiece(coordinates) instanceof ChessPiece attacker) {
                if (playerColor.equals(attacker.getColor()) && ChessPieceNotation.BISHOP.equals(attacker.getNotation())) {
                    attackingSquareCoordinates.add(coordinates);
                } else {
                    break;
                }
            }
        }

        return attackingSquareCoordinates;
    }
}
