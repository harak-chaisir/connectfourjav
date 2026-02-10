package org.connect.controller;

import org.connect.domain.Disc;
import org.connect.domain.Game;
import org.connect.domain.Player;
import org.connect.domain.strategy.AiMoveStrategy;
import org.connect.domain.strategy.HumanMoveStrategy;
import org.connect.domain.strategy.MoveStrategy;

public class GameController {
    private  Game game;
    private final MoveStrategy player1Strategy;
    private final MoveStrategy player2Strategy;

    public GameController() {
//        Player p1 = new Player(1,"Player 1", org.connect.domain.Disc.RED);
//        Player p2 = new Player(2,"Computer", org.connect.domain.Disc.YELLOW);
//        this.game = new Game(p1, p2);
        resetGame();
        this.player1Strategy = new HumanMoveStrategy();
        this.player2Strategy = new AiMoveStrategy();
    }

    public Game getGame() {
        return game;
    }

    public void playMove(int column) {
        if (column >= 0) {
            game.playMove(column);
        }

        // If game ended , stop
        if (game.isOver()) {
            return;
        }

        // If next player is AI, let AI play automatically
        if (isAiTurn()) {
            int aiColumn = player2Strategy.chooseColumn(game.getBoard(), game.getCurrentPlayer().disc());
            game.playMove(aiColumn);
        }
    }

    private boolean isAiTurn() {
        return game.getCurrentPlayer().name().equals("Computer");
    }

    public void resetGame() {
        Player p1 = new Player(1,"Player 1", Disc.RED);
        Player p2 = new Player(2,"Computer", Disc.YELLOW);
        this.game = new Game(p1, p2);
    }
}
