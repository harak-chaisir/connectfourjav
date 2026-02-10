package org.connect.app;

import org.connect.domain.*;

import java.util.Scanner;

public class CliRunner {

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        Player player1 = new Player(1, "Player 1", Disc.RED);
        Player player2 = new Player(2, "Player 2", Disc.YELLOW);

        Game game = new Game(player1, player2);

        System.out.println("🎮 Connect Four (Java)");
        System.out.println("Enter column number (0-6)\n");

        while (!game.isOver()) {
            printBoard(game.getBoard());

            Player current = game.getCurrentPlayer();
            System.out.printf("%s (%s), choose column: ",
                    current.name(), current.disc().symbol());

            try {
                int column = Integer.parseInt(scanner.nextLine());
                game.playMove(column);
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }

        printBoard(game.getBoard());
        printResult(game);
    }

    private static void printBoard(Board board) {
        System.out.println();

        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLUMNS; col++) {
                System.out.print(board.getCell(row, col).symbol() + " ");
            }
            System.out.println();
        }

        for (int col = 0; col < Board.COLUMNS; col++) {
            System.out.print(col + " ");
        }
        System.out.println("\n");
    }

    private static void printResult(Game game) {
        if (game.getStatus() == GameStatus.WON) {
            System.out.println("🏆 Winner: " + game.getWinner().name());
        } else {
            System.out.println("🤝 It's a draw!");
        }
    }
}
