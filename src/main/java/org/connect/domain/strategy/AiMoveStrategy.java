package org.connect.domain.strategy;

import org.connect.domain.Board;
import org.connect.domain.Disc;

public class AiMoveStrategy implements MoveStrategy {
    @Override
    public int chooseColumn(Board board, Disc aiDisc) {
        // Simple AI: choose the first available column
        // 1. Try wining move
        for (int col = 0; col < Board.COLUMNS; col++) {
            if (canWin(board, col, aiDisc)) {
                return col;
            }
        }

        // 2. Try blocking opponent's winning move
        Disc opponent = (aiDisc == Disc.RED) ? Disc.YELLOW : Disc.RED;
        for (int col = 0; col < Board.COLUMNS; col++) {
            if (canWin(board, col, opponent)) {
                return col;
            }
        }

        // 3. Otherwise, pick the first available column
        for (int col = 0; col < Board.COLUMNS; col++) {
            if (board.getCell(0, col) == Disc.EMPTY) {
                return col;
            }
        }

        throw new IllegalStateException("No valid moves available");
    }

    private boolean canWin(Board board, int column, Disc disc) {
        try {
            Board simulation = board.copy();
            int row = simulation.dropDisc(column, disc);
            return simulation.hasConnectFour(row, column, disc);
        } catch (Exception e) {
            return false;
        }
    }
}
