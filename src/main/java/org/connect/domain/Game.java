package org.connect.domain;

import java.util.List;

public class Game {
    private final Board board;
    private final List<Player> players;

    private int currentPlayerIndex;
    private GameStatus status;
    private Player winner;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.players = List.of(player1, player2);
        this.currentPlayerIndex = 0;
        this.status = GameStatus.IN_PROGRESS;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void playMove(int column) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is already over");
        }
        Player player = getCurrentPlayer();
        int row = board.dropDisc(column, player.disc());

        if(board.hasConnectFour(row, column, player.disc())) {
            status = GameStatus.WON;
            winner = player;
            return;
        }
        if (board.isFull()) {
            status = GameStatus.DRAW;
            return;
        }
        switchTurn();
    }

    public void switchTurn() {
        currentPlayerIndex = 1 - currentPlayerIndex; // Toggle between 0 and 1
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean isOver() {
        return status != GameStatus.IN_PROGRESS;
    }
}
