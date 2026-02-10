package org.connect.app;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.connect.controller.GameController;
import org.connect.domain.Game;
import org.connect.domain.GameStatus;
import org.connect.ui.BoardView;

public class JavaFxApp extends Application {
    private GameController controller;
    private BoardView boardView;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.controller = new GameController();
        this.boardView = new BoardView();

        Button button = new Button("Restart Game");
        button.setOnAction(_ -> {
            controller.resetGame();
            boardView.render(controller.getGame().getBoard());
            // Re-enable column clicks in case they were disabled
            boardView.setOnColumnClick(this::handleColumnClick);
        });

        HBox controls = new HBox(button);
        controls.setPadding(new Insets(10));
        controls.setSpacing(10);

        boardView.setOnColumnClick(this::handleColumnClick);
        boardView.render(controller.getGame().getBoard());

        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(boardView);
        Scene scene = new Scene(root, 700, 600);

        stage.setTitle("Connect Four");
        stage.setScene(scene);
        stage.show();
    }

    private void handleColumnClick(int column) {
        try {
            // Play the human move
            Game game = controller.getGame();
            game.playMove(column);
            boardView.render(game.getBoard());

            // Check if game ended after human move
            if (game.isOver()) {
                handleGameEnd(game, primaryStage);
                return;
            }

            // If it's AI's turn, add a realistic pause before AI plays
            if (isAiTurn()) {
                // Disable further clicks while AI is thinking
                boardView.setOnColumnClick(null);

                PauseTransition aiThinking = new PauseTransition(Duration.seconds(0.6));
                aiThinking.setOnFinished(_ -> {
                    try {
                        playAiMove();
                        // Re-enable clicks after AI move
                        boardView.setOnColumnClick(this::handleColumnClick);
                    } catch (Exception e) {
                        showError(e.getMessage());
                        boardView.setOnColumnClick(this::handleColumnClick);
                    }
                });
                aiThinking.play();
            }
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private boolean isAiTurn() {
        return controller.getGame().getCurrentPlayer().name().equals("Computer");
    }

    private void playAiMove() {
        Game game = controller.getGame();
        // Let the controller handle the AI move
        controller.playMove(-1); // The controller will make AI choose the column
        boardView.render(game.getBoard());

        // Check if game ended after AI move
        if (game.isOver()) {
            handleGameEnd(game, primaryStage);
        }
    }

     public static void main(String[] args) {
        launch(args);
    }

    private void handleGameEnd(Game game, Stage stage) {
        if (!game.isOver()) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage);

        if (game.getStatus() == GameStatus.WON) {
            alert.setHeaderText("Game Over");
            alert.setContentText("🏆 Winner: " + game.getWinner().name());
        } else {
            alert.setHeaderText("Game Over");
            alert.setContentText("🤝 It's a draw!");
        }

        alert.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(primaryStage);
        alert.setHeaderText("Invalid Move");
        alert.setContentText(message);
        alert.show();
    }

}
