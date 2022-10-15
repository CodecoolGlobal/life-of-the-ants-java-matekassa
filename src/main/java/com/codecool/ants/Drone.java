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
        int[] queenPosition = Colony.getQueenPosition(area);
        for (Square square: droneSquares) {
            if (isOnQueenPosition(queenPosition, square)) {
                square.removeSquareStatus(SquareStatus.DRONE);
                Drone.kickAway(square, area);
                break;
            }
            for (int i = 0; i < area.length; i++) {
                if (queenPosition[0] > square.getX() && Colony.isValidMoveSouth(square, moveLength)) {
                    Drone.moveSouth(square, area);
                } else if (queenPosition[0] < square.getX() && Colony.isValidMoveNorth(square, moveLength)) {
                    Drone.moveNorth(square, area);
                } else if (queenPosition[1] > square.getY() && Colony.isValidMoveEast(square, moveLength)) {
                    Drone.moveEast(square, area);
                } else if (queenPosition[1] < square.getY() && Colony.isValidMoveWest(square, moveLength)) {
                    Drone.moveWest(square, area);
                }
            }
        }
    }

    public static boolean isOnQueenPosition(int[] queenPosition, Square square) {
        return queenPosition[0] == square.getX() && square.getY() == queenPosition[1];
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

    public static void kickAway(Square square, Square[][] area) { // TODO
        Random random = new Random();
        int moveLength = random.nextInt(area.length/2);
        Direction direction = Direction.randomDirection();
        if (direction.equals(Direction.EAST) && Colony.isValidMoveEast(square, moveLength)) {
            Drone.moveEast(square, area);
        } else if (direction.equals(Direction.WEST) && Colony.isValidMoveWest(square, moveLength)) {
            Drone.moveWest(square, area);
        } else if (direction.equals(Direction.NORTH) && Colony.isValidMoveNorth(square, moveLength)) {
            Drone.moveNorth(square, area);
        } else if (direction.equals(Direction.SOUTH) && Colony.isValidMoveSouth(square, moveLength)) {
            Drone.moveSouth(square, area);
        }
    }
}
