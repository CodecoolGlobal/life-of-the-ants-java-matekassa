package com.codecool.ants;

import com.codecool.ants.geometry.Direction;
import com.codecool.ants.geometry.Square;
import com.codecool.ants.geometry.SquareStatus;

import java.util.ArrayList;
import java.util.Random;

public class Drone extends Ant {

    private static final int moveLength = 1;

    public static ArrayList<Square> getDronePositions(Square[][] area, ArrayList<Square> arrayList) {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                if (area[i][j].getActiveOnSquare().contains(SquareStatus.DRONE)) {
                    arrayList.add(area[i][j]);
                }
            }
        }
        return arrayList;
    }
    public static void move(ArrayList<Square> droneSquares, Square[][] area) {
        Square queenPosition = Colony.getQueenPosition(area);
        for (Square square: droneSquares) {
            if (isOnQueenPosition(queenPosition, square)) {
                Drone.kickAway(square, area);
            }
            for (int i = 0; i < area.length; i++) {
                if (queenPosition.getX() > square.getX() && Colony.isValidMoveSouth(square, moveLength) && !isOnQueenPosition(queenPosition, square)) {
                    Drone.moveSouth(square, area);
                } else if (queenPosition.getX() < square.getX() && Colony.isValidMoveNorth(square, moveLength) && !isOnQueenPosition(queenPosition, square)) {
                    Drone.moveNorth(square, area);
                } else if (queenPosition.getY() > square.getY() && Colony.isValidMoveEast(square, moveLength) && !isOnQueenPosition(queenPosition, square)) {
                    Drone.moveEast(square, area);
                } else if (queenPosition.getY() < square.getY() && Colony.isValidMoveWest(square, moveLength) && !isOnQueenPosition(queenPosition, square)) {
                    Drone.moveWest(square, area);
                }
            }
        }
    }

    public static boolean isOnQueenPosition(Square queenPosition, Square square) {
        return queenPosition.equals(square);
    }

    public static void moveEast(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.DRONE);
        area[square.getX()][square.getY()+moveLength].addSquareStatus(SquareStatus.DRONE);
    }
    public static void moveWest(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.DRONE);
        area[square.getX()][square.getY()-moveLength].addSquareStatus(SquareStatus.DRONE);
    }
    public static void moveNorth(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.DRONE);
        area[square.getX()-moveLength][square.getY()].addSquareStatus(SquareStatus.DRONE);
    }
    public static void moveSouth(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.DRONE);
        area[square.getX()+moveLength][square.getY()].addSquareStatus(SquareStatus.DRONE);
    }

    public static void kickAway(Square square, Square[][] area) {
        square.removeSquareStatus(SquareStatus.DRONE);
        Random random = new Random();
        int moveLength = random.nextInt(area.length/2);
        Direction direction = Direction.randomDirection();
        if (direction.equals(Direction.EAST) && Colony.isValidMoveEast(square, moveLength)) {
            area[square.getX()][square.getY()+moveLength].addSquareStatus(SquareStatus.DRONE);
        } else if (direction.equals(Direction.WEST) && Colony.isValidMoveWest(square, moveLength)) {
            area[square.getX()][square.getY()-moveLength].addSquareStatus(SquareStatus.DRONE);
        } else if (direction.equals(Direction.NORTH) && Colony.isValidMoveNorth(square, moveLength)) {
            area[square.getX()-moveLength][square.getY()].addSquareStatus(SquareStatus.DRONE);
        } else if (direction.equals(Direction.SOUTH) && Colony.isValidMoveSouth(square, moveLength)) {
            area[square.getX()+moveLength][square.getY()].addSquareStatus(SquareStatus.DRONE);
        }
    }
}
