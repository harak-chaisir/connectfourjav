package org.connect.domain;

import java.util.Arrays;

public class Board {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;

    private final Disc[][] grid;

    public Board() {
        this.grid = new Disc[ROWS][COLUMNS];
        initialize();
    }

    private void initialize() {
        for (Disc[] row : grid) {
            Arrays.fill(row, Disc.EMPTY);
        }
    }

    public int dropDisc(int column, Disc disc) {
        validateColumn(column);
        for (int row = ROWS-1; row >=0; row--) {
            if (grid[row][column].isEmpty()) {
                grid[row][column] = disc;
                return row;
            }
        }
        throw new IllegalStateException("Column " + column + " is full");
    }

    private void validateColumn(int column) {
        if (column <0 || column > COLUMNS) {
            throw new IllegalArgumentException("Column must be between 0 and " + (COLUMNS-1));
        }
    }

    // Check if board is full
    public boolean isFull() {
        for (int col = 0; col <COLUMNS; col++) {
            if (grid[0][col].isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public Disc getCell(int row, int column) {
        return grid[row][column];
    }

    private int countInDirection(int startRow, int startColumn, int rowDelta, int colDelta, Disc disc) {
        int count = 0;
        int row = startRow + rowDelta;
        int col = startColumn + colDelta;

        while(row>=0 && row < ROWS && col >= 0 && col < COLUMNS && grid[row][col] == disc) {
            count++;
            row += rowDelta;
            col += colDelta;
        }
        return  count;
    }

    public boolean hasConnectFour(int row, int column, Disc disc) {
        int[][] directions = {
                {0, 1}, // horizontal
                {1, 0}, // vertical
                {1, 1}, // diagonal down-right
                {1, -1} // diagonal down-left
        };
        for (int[] dir : directions) {
            int count = 1; // Count the current disc
            count += countInDirection(row, column, dir[0], dir[1], disc); // Count in one direction
            count += countInDirection(row, column, -dir[0], -dir[1], disc); // Count in the opposite direction
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }

    public Board copy() {
        Board copy = new Board();
        for (int row = 0; row < ROWS; row++) {
            System.arraycopy(this.grid[row], 0, copy.grid[row], 0, COLUMNS);
        }
        return copy;
    }
}
