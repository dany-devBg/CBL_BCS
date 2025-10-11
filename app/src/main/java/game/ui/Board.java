package game.ui;

import java.awt.Color;
import java.io.Console;

public class Board {
    public static final int ROWS = GamePanel.ROWS;
    public static final int COLS = GamePanel.COLS;

    private Color[][] grid = new Color[ROWS][COLS];

    public boolean isValidPosition(TetrisPiece piece, int newX, int newY, boolean[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {
                    int boardX = newX + j;
                    int boardY = newY + i;

                    if (boardX < 0 || boardX >= COLS || boardY >= ROWS) {
                        return false;
                    }

                    if (boardY >= 0 && grid[boardY][boardX] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void placePiece(TetrisPiece piece) {
        boolean[][] shape = piece.getShape();
        Color color = piece.getColor();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {
                    int x = piece.getX() + j;
                    int y = piece.getY() + i;
                    if (x >= 0 && x < COLS && y >= 0 && y < ROWS) {
                        grid[y][x] = color;
                    }
                }
            }
        }
    }

    public boolean isLineFull(int i) {
        for (int j = 0; j < COLS; j++) {
            System.out.println(j);
            if (grid[i][j] == null) {
                return false;
            } 
        }
        return true;
    }

    public void clearFullLine(int i) {
        for (int j = 0; j < COLS; j++) {
            grid[i][j] = null;
        }
    }

    public void movePieces(int ind) {
        for (int i = ind; i > -1; i--) {
            for (int j = 0; j < COLS; j++) {
                Color tmp = grid[i][j];
                grid[i][j] = grid[i + 1][j];
                grid[i + 1][j] = tmp;

            }
        }
    }
    
    public Color[][] getGrid() {
        return grid;
    }
}
