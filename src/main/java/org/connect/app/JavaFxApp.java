package org.connect.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.connect.controller.GameController;
import org.connect.domain.Game;
import org.connect.domain.GameStatus;
import org.connect.ui.BoardView;

public class JavaFxApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GameController controller = new GameController();
        BoardView boardView = new BoardView();

        boardView.setOnColumnClick(column -> {
            try {
                controller.playMove(column);
                boardView.render(controller.getGame().getBoard());
                handleGameEnd(controller.getGame(), stage);
            } catch (Exception e) {
                showError(e.getMessage());
            }
        });

        boardView.render(controller.getGame().getBoard());
        BorderPane root = new BorderPane();
        root.setCenter(boardView);
        Scene scene = new Scene(root, 700, 600);
        stage.setTitle("Connect Four");
        stage.setScene(scene);
        stage.show();
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

        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Invalid Move");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
