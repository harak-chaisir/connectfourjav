package org.connect.domain.strategy;

import org.connect.domain.Board;
import org.connect.domain.Disc;

public class HumanMoveStrategy implements MoveStrategy{
    @Override
    public int chooseColumn(Board board, Disc disc) {
        throw new UnsupportedOperationException("Human move must come from UI interaction");
    }
}
