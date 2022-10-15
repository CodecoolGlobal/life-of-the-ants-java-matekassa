package com.codecool.ants.geometry;

public enum SquareStatus {
    EMPTY('E'), QUEEN('Q'), DRONE('D'), SOLDIER('S'), WORKER('W');
    private final char status;

    SquareStatus(char status) {
        this.status = status;
    }

    public char getCharacter() {
        return status;
    }
}
