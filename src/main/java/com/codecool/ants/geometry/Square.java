package com.codecool.ants.geometry;

import java.util.ArrayList;
import java.util.List;

public class Square {

    private final int X;
    private final int Y;

    private List<SquareStatus> activeOnSquare =  new ArrayList<>();
    private SquareStatus squareStatus = SquareStatus.EMPTY;

    public Square(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public boolean getIsEmpty() {
        return !activeOnSquare.contains(SquareStatus.DRONE) &&
                !activeOnSquare.contains(SquareStatus.WORKER) &&
                !activeOnSquare.contains(SquareStatus.QUEEN) &&
                !activeOnSquare.contains(SquareStatus.SOLDIER);
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public void addSquareStatus(SquareStatus squareStatus) {
        activeOnSquare.add(squareStatus);
    }

    public void removeSquareStatus(SquareStatus squareStatus) {
        activeOnSquare.remove(squareStatus);
    }

    public List<SquareStatus> getActiveOnSquare() {
        return this.activeOnSquare;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setSquareStatus(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }
}
