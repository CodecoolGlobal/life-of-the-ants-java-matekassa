package com.codecool.ants;

import com.codecool.ants.geometry.Direction;
import com.codecool.ants.geometry.Square;
import com.codecool.ants.geometry.SquareStatus;

import java.util.ArrayList;

public class Worker extends Ant{

    private static final int moveLength = 1;
    public static ArrayList<Square> getWorkerPositions(Square[][] area, ArrayList<Square> arrayList) {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                if (area[i][j].getActiveOnSquare().contains(SquareStatus.WORKER)) {
                    arrayList.add(area[i][j]);
                }
            }
        }
        return arrayList;
    }
    public static void move(ArrayList<Square> workerSquares, Square[][] area) {
        for (Square square: workerSquares) {
            Direction direction = Direction.randomDirection();
            for (int i = 0; i < area.length; i++) {
                if (direction.equals(Direction.EAST) && Colony.isValidMoveEast(square, moveLength)) {
                    Worker.moveEast(square, area);
                } else if (direction.equals(Direction.WEST) && Colony.isValidMoveWest(square, moveLength)) {
                    Worker.moveWest(square, area);
                } else if (direction.equals(Direction.NORTH) && Colony.isValidMoveNorth(square, moveLength)) {
                    Worker.moveNorth(square, area);
                } else if (direction.equals(Direction.SOUTH) && Colony.isValidMoveSouth(square, moveLength)) {
                    Worker.moveSouth(square, area);
                }
            }
        }
    }
    public static void moveEast(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.WORKER);
        area[square.getX()][square.getY()+moveLength].addSquareStatus(SquareStatus.WORKER);
    }
    public static void moveWest(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.WORKER);
        area[square.getX()][square.getY()-moveLength].addSquareStatus(SquareStatus.WORKER);
    }
    public static void moveNorth(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.WORKER);
        area[square.getX()-moveLength][square.getY()].addSquareStatus(SquareStatus.WORKER);
    }
    public static void moveSouth(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.WORKER);
        area[square.getX()+moveLength][square.getY()].addSquareStatus(SquareStatus.WORKER);
    }

    public Worker() {
    }

}
