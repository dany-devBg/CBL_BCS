package game.model;

import java.util.*;

import game.model.Tetromino.Rotation;

public class SRS {
    public record Offset(int x, int y) {
    }

    public record RotationTransition(Rotation from, Rotation to) {
    }

    private static final Map<RotationTransition, Offset[]> JLSTZ_KICKS = new HashMap<>();
    private static final Map<RotationTransition, Offset[]> I_KICKS = new HashMap<>();

    static {
        // SPAWN -> RIGHT
        JLSTZ_KICKS.put(
                new RotationTransition(Rotation.SPAWN, Rotation.RIGHT),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(-1, 0),
                        new Offset(-1, -1),
                        new Offset(0, 2),
                        new Offset(-1, 2)
                });

        // RIGHT -> SPAWN
        JLSTZ_KICKS.put(
                new RotationTransition(Rotation.RIGHT, Rotation.SPAWN),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(1, 0),
                        new Offset(1, 1),
                        new Offset(0, -2),
                        new Offset(1, -2)
                });

        // RIGHT -> REVERSE
        JLSTZ_KICKS.put(
                new RotationTransition(Rotation.RIGHT, Rotation.REVERSE),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(1, 0),
                        new Offset(1, 1),
                        new Offset(0, -2),
                        new Offset(1, -2)
                });

        // REVERSE -> RIGHT
        JLSTZ_KICKS.put(
                new RotationTransition(Rotation.REVERSE, Rotation.RIGHT),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(-1, 0),
                        new Offset(-1, -1),
                        new Offset(0, 2),
                        new Offset(-1, 2)
                });

        // REVERSE -> LEFT
        JLSTZ_KICKS.put(
                new RotationTransition(Rotation.REVERSE, Rotation.LEFT),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(1, 0),
                        new Offset(1, -1),
                        new Offset(0, 2),
                        new Offset(1, 2)
                });

        // LEFT -> REVERSE
        JLSTZ_KICKS.put(
                new RotationTransition(Rotation.LEFT, Rotation.REVERSE),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(-1, 0),
                        new Offset(-1, 1),
                        new Offset(0, -2),
                        new Offset(-1, -2)
                });

        // LEFT -> SPAWN
        JLSTZ_KICKS.put(
                new RotationTransition(Rotation.LEFT, Rotation.SPAWN),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(-1, 0),
                        new Offset(-1, 1),
                        new Offset(0, -2),
                        new Offset(-1, -2)
                });

        // SPAWN -> LEFT
        JLSTZ_KICKS.put(
                new RotationTransition(Rotation.SPAWN, Rotation.LEFT),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(1, 0),
                        new Offset(1, -1),
                        new Offset(0, 2),
                        new Offset(1, 2)
                });

        // SPAWN -> RIGHT
        I_KICKS.put(
                new RotationTransition(Rotation.SPAWN, Rotation.RIGHT),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(-2, 0),
                        new Offset(1, 0),
                        new Offset(-2, 1),
                        new Offset(1, -2)
                });

        // RIGHT -> SPAWN
        I_KICKS.put(
                new RotationTransition(Rotation.RIGHT, Rotation.SPAWN),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(2, 0),
                        new Offset(-1, 0),
                        new Offset(2, -1),
                        new Offset(-1, 2)
                });

        // RIGHT -> REVERSE
        I_KICKS.put(
                new RotationTransition(Rotation.RIGHT, Rotation.REVERSE),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(-1, 0),
                        new Offset(2, 0),
                        new Offset(-1, -2),
                        new Offset(2, 1)
                });

        // REVERSE -> RIGHT
        I_KICKS.put(
                new RotationTransition(Rotation.REVERSE, Rotation.RIGHT),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(1, 0),
                        new Offset(-2, 0),
                        new Offset(1, 2),
                        new Offset(-2, -1)
                });

        // REVERSE -> LEFT
        I_KICKS.put(
                new RotationTransition(Rotation.REVERSE, Rotation.LEFT),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(2, 0),
                        new Offset(-1, 0),
                        new Offset(2, -1),
                        new Offset(-1, 2)
                });

        // LEFT -> REVERSE
        I_KICKS.put(
                new RotationTransition(Rotation.LEFT, Rotation.REVERSE),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(-2, 0),
                        new Offset(1, 0),
                        new Offset(-2, 1),
                        new Offset(1, -2)
                });

        // LEFT -> SPAWN
        I_KICKS.put(
                new RotationTransition(Rotation.LEFT, Rotation.SPAWN),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(1, 0),
                        new Offset(-2, 0),
                        new Offset(1, 2),
                        new Offset(-2, -1)
                });

        // SPAWN -> LEFT
        I_KICKS.put(
                new RotationTransition(Rotation.SPAWN, Rotation.LEFT),
                new Offset[] {
                        new Offset(0, 0),
                        new Offset(-1, 0),
                        new Offset(2, 0),
                        new Offset(-1, -2),
                        new Offset(2, 1)
                });
    }

    public static Offset[] getJLSTZKicks(Rotation from, Rotation to) {
        return JLSTZ_KICKS.getOrDefault(new RotationTransition(from, to), new Offset[0]);
    }

    public static Offset[] getIKicks(Rotation from, Rotation to) {
        return I_KICKS.getOrDefault(new RotationTransition(from, to), new Offset[0]);
    }
}
