package org.connect.domain.strategy;

import org.connect.domain.Board;
import org.connect.domain.Disc;

public interface MoveStrategy {
    /**
     * Decide which column to play based on the current board state and the disc color.
     * @param board current state of the game board
     * @param disc disc color of the player making the move
     * @return column index (0-based) where the disc should be dropped
     */
    int chooseColumn(Board board, Disc disc);
}
