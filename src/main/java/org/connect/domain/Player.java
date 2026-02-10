package org.connect.domain;

/**
 * Represents a player in the Connect Four game, identified by a unique ID, name, and assigned disc color.
 * @param id
 * @param name
 * @param disc
 */
public record Player(
        int id,
        String name,
        Disc disc
) {
    public Player {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be null or blank");
        }
        if (disc == Disc.EMPTY) {
            throw new IllegalArgumentException("Player disc cannot be empty");
        }
    }
}
// Why use a record for Player?
// 1. Immutability: Records are immutable by default, which is ideal for a Player object that should not change after creation.
// 2. Conciseness: Records automatically generate constructors, accessors, equals(), hashCode(), and toString() methods, reducing boilerplate code.
// 3. Clarity: Using a record clearly indicates that Player is a simple data carrier without complex behavior, improving code readability.
