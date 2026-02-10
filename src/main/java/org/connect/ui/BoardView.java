package org.connect.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.connect.domain.Board;
import org.connect.domain.Disc;

import java.util.function.IntConsumer;

public class BoardView extends GridPane {
    private static final int CELL_SIZE = 70;
    private IntConsumer columnClickHandler;

    public BoardView() {
        setHgap(10);
        setVgap(10);
        // Add padding around the whole board and center the grid within its parent
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
    }

    public void setOnColumnClick(IntConsumer handler) {
        this.columnClickHandler = handler;
    }


    public void render(Board board) {
        getChildren().clear();
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col= 0; col < Board.COLUMNS; col++) {
                Circle circle = createDisc(board.getCell(row, col), col);
                // Wrap the circle in a StackPane so it stays centered in the cell
                StackPane cell = new StackPane(circle);
                cell.setPrefSize(CELL_SIZE, CELL_SIZE);
                cell.setMinSize(CELL_SIZE, CELL_SIZE);
                cell.setMaxSize(CELL_SIZE, CELL_SIZE);
                cell.setAlignment(Pos.CENTER);
                add(cell, col, row);
            }
        }
    }

    private Circle createDisc(Disc disc, int column) {
        Circle circle = new Circle(CELL_SIZE/2.0);
        circle.setFill(
                switch (disc) {
                    case RED -> Color.RED;
                    case YELLOW ->  Color.YELLOW;
                    default -> Color.LIGHTGRAY;
                }
        );
        circle.setOnMouseClicked(e ->{
            if (columnClickHandler != null) {
                columnClickHandler.accept(column);
            }
            // mark the event as handled so the parameter is used (avoids unused-parameter warning)
            e.consume();
        });
        return circle;
    }
}
