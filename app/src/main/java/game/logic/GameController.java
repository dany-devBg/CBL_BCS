package game.logic;

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

    private static final int ROWS = GamePanel.ROWS;
    private static final int COLS = GamePanel.COLS;

    private boolean gameOver = false;

    private int level = 1;
    private int linesCleared = 0;
    private int score = 0;

    public GameController(GamePanel panel) {
        this.panel = panel;
        this.board = new Board();
        this.currentPiece = TetrisPiece.randomPiece();
        this.nextPiece = TetrisPiece.randomPiece();
    }

    public void tryMove(TetrisPiece piece, int newX, int newY) {
        if (board.isValidPosition(piece, newX, newY, piece.getShape())) {
            piece.setX(newX);
            piece.setY(newY);
            panel.repaint();
        }
    }

    public void tryRotate(TetrisPiece piece, boolean clockwise) {
        if (currentPiece.getType() == Tetromino.O) {
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
                panel.repaint();

                return;
            }
        }

    }

    public void update() {

        // Check if the new position is valid
        if (board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY()
                + 1,
                currentPiece.getShape())) {
            currentPiece.moveDown();
        } else {
            board.placePiece(currentPiece);
            updateScore(0);
            clearFullLines();

            // Spawn new piece
            currentPiece = nextPiece;
            nextPiece = TetrisPiece.randomPiece();

            // Check for game over
            if (!board.isValidPosition(currentPiece, currentPiece.getX(), currentPiece.getY(),
                    currentPiece.getShape())) {
                gameOver = true;
                return;
            }
        }
    }

    public void clearFullLines() {
        int newLinesCleared = 0;
        for (int i = ROWS - 1; i > -1; i--) {
            while (board.isLineFull(i)) {
                board.clearFullLine(i);
                board.movePieces(i - 1);
                linesCleared++;
                newLinesCleared++;
            }
        }
        updateScore(newLinesCleared);
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
        return Math.max(1000 - (level - 1) * 100, 100);
    }

    public void updateScore(int lines) {
        switch (lines) {
            case 0:
                score += 10 * level; // Placed piece
                break;
            case 1:
                score += 100 * level;
                break;
            case 2:
                score += 300 * level;
                break;
            case 3:
                score += 500 * level;
                break;
            case 4:
                score += 800 * level;
            default:
                break;
        }
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
