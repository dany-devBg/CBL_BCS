package game.model;

import java.awt.Color;
import java.util.Random;

import game.model.Tetromino.Rotation;

public class TetrisPiece {
    // A 4x4 matrix representing the shape of the piece
    private boolean[][] shape;
    // Coordinates of the top left corner of the matrix
    private int x;
    private int y;
    // Color of the piece
    private Color color;
    private Rotation rotation;
    private Tetromino type;

    public TetrisPiece(Tetromino tetromino) {
        this.shape = tetromino.getShape();
        this.x = 3;
        this.y = 0;
        this.color = tetromino.getColor();
        this.rotation = Rotation.SPAWN;
        this.type = tetromino;
    }

    public static TetrisPiece randomPiece() {
        Tetromino[] values = Tetromino.values();
        int index = new Random().nextInt(values.length);
        Tetromino tetromino = values[index];
        return new TetrisPiece(tetromino);
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return this.color;
    }

    public Rotation getRotation() {
        return this.rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Tetromino getType() {
        return this.type;
    }

    public void moveDown() {
        y += 1;
    }

    public void moveLeft() {
        x -= 1;
    }

    public void moveRight() {
        x += 1;
    }

    public boolean[][] getRotatedCounterClockwise() {
        int rows = this.shape.length;
        int cols = this.shape[0].length;
        boolean[][] rotated = new boolean[rows][cols];

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                boolean value = this.shape[j][i];
                rotated[rows - 1 - i][j] = value;
            }
        }
        return rotated;
    }

    public void setShape(boolean[][] newShape) {
        this.shape = newShape;
    }

    public boolean[][] getRotatedClockwise() {
        int rows = this.shape.length;
        int cols = this.shape[0].length;
        boolean[][] rotated = new boolean[rows][cols];

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                boolean value = this.shape[j][i];
                rotated[i][cols - 1 - j] = value;
            }
        }
        return rotated;
    }

}