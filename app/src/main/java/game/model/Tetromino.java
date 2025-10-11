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
                        { false, true, true, false },
                        { false, true, true, false },
                        { false, false, false, false },
                        { false, false, false, false }
        }, Color.YELLOW),

        T(new boolean[][] {
                        { false, true, false, false },
                        { true, true, true, false },
                        { false, false, false, false },
                        { false, false, false, false }
        }, Color.MAGENTA),

        S(new boolean[][] {
                        { false, true, true, false },
                        { true, true, false, false },
                        { false, false, false, false },
                        { false, false, false, false }
        }, Color.GREEN),

        Z(new boolean[][] {
                        { true, true, false, false },
                        { false, true, true, false },
                        { false, false, false, false },
                        { false, false, false, false }
        }, Color.RED),

        J(new boolean[][] {
                        { true, false, false, false },
                        { true, true, true, false },
                        { false, false, false, false },
                        { false, false, false, false }
        }, Color.BLUE),

        L(new boolean[][] {
                        { false, false, true, false },
                        { true, true, true, false },
                        { false, false, false, false },
                        { false, false, false, false }
        }, Color.ORANGE);

        private final boolean[][] shape;
        private final Color color;

        Tetromino(boolean[][] shape, Color color) {
                // Deep copy the shape to avoid shared references
                int N = shape.length;
                this.shape = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                        System.arraycopy(shape[i], 0, this.shape[i], 0, N);
                }
                this.color = color;
        }

        public boolean[][] getShape() {
                // Return a new copy each time to avoid mutation of the enum constant
                int N = shape.length;
                boolean[][] copy = new boolean[N][N];
                for (int i = 0; i < N; i++) {
                        System.arraycopy(shape[i], 0, copy[i], 0, N);
                }
                return copy;
        }

        public Color getColor() {
                return color;
        }
}
