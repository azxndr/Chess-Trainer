package com.chess.pieces;

import com.chess.Color;
import com.chess.game.Square;

import java.util.ArrayList;
import java.util.List;


public class Rook extends Piece {
    public Rook(char file, int rank, Color color) throws Exception {
        super(file, rank, color);
    }


    @Override
    public List<Square> legalMoves() {
        List<Square> legalMoves = new ArrayList<Square>();
        // directions in witch to check for legal moves
        int rankDirection = 0;
        int fileDirection = 0;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    rankDirection = 0;
                    fileDirection = 1;
                case 1:
                    rankDirection = 0;
                    fileDirection = -1;
                case 2:
                    rankDirection = -1;
                    fileDirection = 0;
                case 3:
                    rankDirection = -1;
                    fileDirection = -0;
            }
            legalMoves = LegalMoveUtil.getLegalMoves(legalMoves,this,fileDirection,rankDirection);
        }
        return legalMoves;
    }
}
