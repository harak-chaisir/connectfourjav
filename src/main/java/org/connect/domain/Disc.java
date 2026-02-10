package org.connect.domain;

public enum Disc {
    EMPTY("."),
    RED("R"),
    YELLOW("Y");

    private final String symbol;

    Disc(String symbol) {
        this.symbol = symbol;
    }
    public String symbol() {
        return symbol;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
