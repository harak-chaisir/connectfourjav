package org.connect.controller;

import org.connect.domain.Game;
import org.connect.domain.Player;

public class GameController {
    private final Game game;

    public GameController() {
        Player p1 = new Player(1,"Player 1", org.connect.domain.Disc.RED);
        Player p2 = new Player(2,"Player 2", org.connect.domain.Disc.YELLOW);
        this.game = new Game(p1, p2);
    }

    public Game getGame() {
        return game;
    }

    public void playMove(int column) {
        game.playMove(column);
    }
}
