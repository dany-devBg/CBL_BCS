package game.logic;

import javax.swing.Timer;

import game.model.Board;
import game.model.TetrisPiece;
import game.model.Tetromino;
import game.model.SRS.Offset;
import game.model.SRS;
import game.model.Tetromino.Rotation;
import game.ui.GamePanel;

public class GameController {
    private Board board;
    private TetrisPiece currentPiece;
    private TetrisPiece nextPiece;
    private GamePanel panel; // for repaint
    private Timer frameTimer;
    private Timer gravityTimer;

    private static final int ROWS = GamePanel.ROWS;
    private static final int COLS = GamePanel.COLS;

    private boolean gameOver = false;

    private int level = 1;
    private int linesCleared = 0;

    public GameController(GamePanel panel) {
        this.panel = panel;
        this.board = new Board();
        this.currentPiece = TetrisPiece.randomPiece();
        this.nextPiece = TetrisPiece.randomPiece();
    }

    public void startGame() {
        this.frameTimer = new Timer(16, panel);
        this.frameTimer.start();

        this.gravityTimer = new Timer(800, e -> this.currentPiece.moveDown());
        this.gravityTimer.start();
    }

    public void tryMove(TetrisPiece piece, int newX, int newY) {
        if (board.isValidPosition(piece, newX, newY, piece.getShape())) {
            piece.setX(newX);
            piece.setY(newY);
        }
    }

    public void tryRotate(TetrisPiece piece, boolean clockwise) {
        if (piece.getType() == Tetromino.O) {
            return;
        }

        boolean[][] rotated;
        Offset[] rotationsOffset = {};
        Rotation to;
        Rotation from = currentPiece.getRotation();

        if (clockwise) {
            rotated = piece.getRotatedClockwise();
            to = currentPiece.getRotation().clockwise();
        } else {
            rotated = piece.getRotatedCounterClockwise();
            to = currentPiece.getRotation().counterClockwise();

        }

        switch (currentPiece.getType()) {
            case I: // I has special rotation rules
                rotationsOffset = SRS.getIKicks(from, to);
                break;
            default:
                rotationsOffset = SRS.getJLSTZKicks(from, to);
                break;
        }

        for (Offset offset : rotationsOffset) {
            int testX = currentPiece.getX() + offset.x();
            int testY = currentPiece.getY() + offset.y();

            if (board.isValidPosition(piece, testX, testY, rotated)) {
                piece.setShape(rotated);
                piece.setX(testX);
                piece.setY(testY);
                piece.setRotation(to);

                return;
            }
        }

    }

    public void hardDrop(TetrisPiece piece) {
        while (board.isValidPosition(piece, piece.getX(), piece.getY() + 1, piece.getShape())) {
            piece.moveDown();
        }
    }

    public void update() {

        // Check if the new position is valid
        if (!board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY()
                + 1,
                currentPiece.getShape())) {

            board.placePiece(currentPiece);
            clearFullLines();

            // Spawn new piece
            currentPiece = nextPiece;
            nextPiece = TetrisPiece.randomPiece();

            // Check for game over
            if (!board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY(),
                    currentPiece.getShape())) {
                gameOver = true;
                frameTimer.stop();
                gravityTimer.stop();
                return;
            }
        }
    }

    public void clearFullLines() {
        for (int i = ROWS - 1; i > -1; i--) {
            while (board.isLineFull(i)) {
                board.clearFullLine(i);
                board.movePieces(i - 1);
                linesCleared++;
                gravityTimer.setDelay(this.getSpeedDelay());

            }
        }
        level = linesCleared / 10 + 1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void resetGame() {
        this.board = new Board();
        this.currentPiece = TetrisPiece.randomPiece();
        this.nextPiece = TetrisPiece.randomPiece();
        this.gameOver = false;
        this.panel.repaint();
    }

    public int getSpeedDelay() {
        return Math.max(800 - (level - 1) * 50, 100);
    }

    // Getters
    public TetrisPiece getCurrentPiece() {
        return this.currentPiece;
    }

    public TetrisPiece getNextPiece() {
        return this.nextPiece;
    }

    public Board getBoard() {
        return this.board;
    }
}
