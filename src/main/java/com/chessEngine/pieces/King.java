package com.chessEngine.pieces;

import com.chessEngine.game.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17.26.4.
 */
public class King extends Piece {
    private boolean check;

    public King(char file, int rank, Color color) throws Exception {
        super(file, rank, color);
        check = false;
    }

    public void toggleCheck() {
        check = !check;
    }

    public boolean getCheck() {
        return check;
    }


    public boolean checkForCheck(Square squareBeforeMove, Square squareAfterMove) {
        List<Square> moves = new ArrayList<Square>();
        Square checkSquare;
        int rankDirection = 0;
        int fileDirection = 0;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    rankDirection = -1;
                    fileDirection = -1;
                    break;
                case 1:
                    rankDirection = -1;
                    fileDirection = 0;
                    break;
                case 2:
                    rankDirection = -1;
                    fileDirection = 1;
                    break;
                case 3:
                    rankDirection = 0;
                    fileDirection = -1;
                    break;
                case 4:
                    rankDirection = 0;
                    fileDirection = 1;
                    break;
                case 5:
                    rankDirection = 1;
                    fileDirection = -1;
                    break;
                case 6:
                    rankDirection = 1;
                    fileDirection = 0;
                    break;
                case 7:
                    rankDirection = 1;
                    fileDirection = 1;
                    break;
            }
            for (int j = 0; j < 8; j++) {
                checkSquare = Square.getSquare((char) (this.getSquare().getFile() + (j + 1) * fileDirection), this.getSquare().getRank() + (j + 1) * rankDirection);
                if (checkSquare == null) {
                    break;
                }
                if (checkSquare.equals(squareBeforeMove)) {
                    continue;
                }
                if (checkSquare != null) {
                    if (checkSquare.equals(squareAfterMove)) {
                        break;
                    }
                    if (checkSquare.isOccupied()) {
                        if (checkSquare.getPiece().getColor() != this.getColor()) {
                            moves.add(checkSquare);
                        } else break;
                    }
                }
            }
        }
        //TODO castling
        for (Square pieceSquare : moves) {
            if (pieceSquare.getPiece().getMoves().contains(this.getSquare())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Square> getMoves() {
        List<Square> moves = new ArrayList<Square>();
        Square checkSquare;
        // directions in witch to check for legal moves
        int rankDirection = 0;
        int fileDirection = 0;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    rankDirection = -1;
                    fileDirection = -1;
                    break;
                case 1:
                    rankDirection = -1;
                    fileDirection = 0;
                    break;
                case 2:
                    rankDirection = -1;
                    fileDirection = 1;
                    break;
                case 3:
                    rankDirection = 0;
                    fileDirection = -1;
                    break;
                case 4:
                    rankDirection = 0;
                    fileDirection = 1;
                    break;
                case 5:
                    rankDirection = 1;
                    fileDirection = -1;
                    break;
                case 6:
                    rankDirection = 1;
                    fileDirection = 0;
                    break;
                case 7:
                    rankDirection = 1;
                    fileDirection = 1;
                    break;
            }

            checkSquare = Square.getSquare((char) (this.getSquare().getFile() + fileDirection), this.getSquare().getRank() + rankDirection);
            if (checkSquare != null) {
                if (!checkForCheck(this.getSquare(), checkSquare)) { // check if after this setHasMoved king will be under check
                    if (!checkSquare.isOccupied()) {
                        moves.add(checkSquare);
                    } else if (checkSquare.getPiece().getColor() != this.getColor()) {
                        moves.add(checkSquare);
                    }
                }
            }
        }

        return moves;
    }

    public String getPieceName() {
        return "King";
    }
}