package game.ui;

import java.util.Random;

public class TetrisPiece {
    // A 4x4 matrix representing the shape of the piece
    private boolean[][] shape;
    // Coordinates of the top left corner of the matrix
    private int x;
    private int y;

    public TetrisPiece(boolean[][] shape, int x, int y) {
        this.shape = shape;
        this.x = 3;
        this.y = 0;
    }

    public static TetrisPiece randomPiece() {
        Tetromino[] values = Tetromino.values();
        int index = new Random().nextInt(values.length);
        return new TetrisPiece(values[index].getShape(), 3, 0);
    }

    public boolean[][] getShape() {
        return this.shape;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
