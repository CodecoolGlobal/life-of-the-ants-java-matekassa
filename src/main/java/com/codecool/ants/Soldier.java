package com.codecool.ants;

import com.codecool.ants.geometry.Direction;
import com.codecool.ants.geometry.Square;
import com.codecool.ants.geometry.SquareStatus;

import java.util.ArrayList;

public class Soldier extends Ant{

    private static final int moveLength = 1;

    public static ArrayList<Square> getPositions(Square[][] area, ArrayList<Square> arrayList) {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                if (area[i][j].getActiveOnSquare().contains(SquareStatus.SOLDIER)) {
                    arrayList.add(area[i][j]);
                }
            }
        }
        return arrayList;
    }


    public static void move(ArrayList<Square> soldierSquares, Square[][] area) {
        for (Square square: soldierSquares) {
            Direction direction = Direction.randomDirection();
            for (int i = 0; i < area.length; i++) {
                if (direction.equals(Direction.EAST) &&
                        Colony.isValidMoveEast(square, moveLength+1) &&
                        Colony.isValidMoveNorth(square, moveLength+1)) {
                    Soldier.moveNorth(square, area);
                } else if (direction.equals(Direction.WEST) &&
                        Colony.isValidMoveWest(square, moveLength+1) &&
                        Colony.isValidMoveSouth(square, moveLength+1)) {
                    Soldier.moveSouth(square, area);
                } else if (direction.equals(Direction.NORTH) &&
                        Colony.isValidMoveNorth(square, moveLength+1) &&
                        Colony.isValidMoveWest(square, moveLength+1)) {
                    Soldier.moveWest(square, area);
                } else if (direction.equals(Direction.SOUTH) &&
                        Colony.isValidMoveSouth(square, moveLength+1) &&
                        Colony.isValidMoveEast(square, moveLength+1)) {
                    Soldier.moveEast(square, area);
                }
            }
        }
    }

    public static void moveEast(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.SOLDIER);
        area[square.getX()][square.getY()+moveLength].addSquareStatus(SquareStatus.SOLDIER);
    }

    public static void moveWest(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.SOLDIER);
        area[square.getX()][square.getY()-moveLength].addSquareStatus(SquareStatus.SOLDIER);
    }

    public static void moveNorth(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.SOLDIER);
        area[square.getX()-moveLength][square.getY()].addSquareStatus(SquareStatus.SOLDIER);
    }

    public static void moveSouth(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.SOLDIER);
        area[square.getX()+moveLength][square.getY()].addSquareStatus(SquareStatus.SOLDIER);
    }

    public Soldier() {
    }

}
