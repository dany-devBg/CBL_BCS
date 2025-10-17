package game.model;

import java.awt.Color;

public enum Tetromino {
        I(new boolean[][] {
                        { false, false, false, false },
                        { true, true, true, true },
                        { false, false, false, false },
                        { false, false, false, false }
        }, Color.CYAN),

        O(new boolean[][] {
                        { false, false, false, false },
                        { false, true, true, false },
                        { false, true, true, false },
                        { false, false, false, false },
        }, Color.YELLOW),

        T(new boolean[][] {
                        { false, true, false },
                        { true, true, true },
                        { false, false, false },
        }, Color.MAGENTA),

        S(new boolean[][] {
                        { false, true, true },
                        { true, true, false },
                        { false, false, false },
        }, Color.GREEN),

        Z(new boolean[][] {
                        { true, true, false },
                        { false, true, true },
                        { false, false, false },
        }, Color.RED),

        J(new boolean[][] {
                        { true, false, false },
                        { true, true, true },
                        { false, false, false },
        }, Color.BLUE),

        L(new boolean[][] {
                        { false, false, true },
                        { true, true, true },
                        { false, false, false },
        }, Color.ORANGE);

        public enum Rotation {
                SPAWN, RIGHT, REVERSE, LEFT;

                record Offset(int x, int y) {
                };

                public Rotation clockwise() {
                        return switch (this) {
                                case SPAWN -> RIGHT;
                                case RIGHT -> REVERSE;
                                case REVERSE -> LEFT;
                                case LEFT -> SPAWN;
                        };
                }

                public Rotation counterClockwise() {
                        return switch (this) {
                                case SPAWN -> LEFT;
                                case LEFT -> REVERSE;
                                case REVERSE -> RIGHT;
                                case RIGHT -> SPAWN;
                        };
                }
        }

        private final boolean[][] shape;
        private final Color color;

        Tetromino(boolean[][] shape, Color color) {
                // Deep copy the shape to avoid shared references
                int rows = shape.length;
                int cols = shape[0].length;
                this.shape = new boolean[rows][cols];
                for (int i = 0; i < rows; i++) {
                        System.arraycopy(shape[i], 0, this.shape[i], 0, cols);
                }
                this.color = color;
        }

        public boolean[][] getShape() {
                // Return a new copy each time to avoid mutation of the enum constant
                int rows = shape.length;
                int cols = shape[0].length;
                boolean[][] copy = new boolean[rows][cols];
                for (int i = 0; i < rows; i++) {
                        System.arraycopy(shape[i], 0, copy[i], 0, cols);
                }
                return copy;
        }

        public Color getColor() {
                return color;
        }
}
