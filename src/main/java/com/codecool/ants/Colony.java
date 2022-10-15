package com.codecool.ants;

import com.codecool.ants.geometry.Square;
import com.codecool.ants.geometry.SquareStatus;

import java.util.ArrayList;
import java.util.Random;

public class Colony {
    private final int width;
    private static Square[][] area;

    public Colony(int width) {
        this.width = width;
        area = new Square[width][width];
        this.fillArea();
        this.setQueenPosition();
    }

    public Square[][] getArea() {
        return area;
    }

    private void fillArea() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                area[i][j] = new Square(i, j);
            }
        }
    }

    private void setQueenPosition() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (i == (area.length/2) && j == (area.length/2)) {
                    area[i][j].setSquareStatus(SquareStatus.QUEEN);
                    area[i][j].addSquareStatus(SquareStatus.QUEEN);
                }
            }
        }
    }

    public static Square getQueenPosition(Square[][] area) {
        Square queenPosition = null;
        for (Square[] squares : area) {
            for (int j = 0; j < area.length; j++) {
                if (squares[j].getActiveOnSquare().contains(SquareStatus.QUEEN)) {
                    queenPosition = squares[j];
                    break;
                }
            }
        }
        return queenPosition;
    }

    public void generateAnts(int numberOfDrones, int numberOfWorkers, int numberOfSoldiers) {
        int[][] dronePositions = generatePositions(numberOfDrones);
        int[][] workerPositions = generatePositions(numberOfWorkers);
        int[][] soldierPositions = generatePositions(numberOfSoldiers);
        setAntPositions(SquareStatus.DRONE, dronePositions);
        setAntPositions(SquareStatus.WORKER, workerPositions);
        setAntPositions(SquareStatus.SOLDIER, soldierPositions);
    }

    private void setAntPositions(SquareStatus antChar, int[][] antPositions) {
        for (int[] coordinate : antPositions) {
            int X = coordinate[0];
            int Y = coordinate[1];
            do {
                area[X][Y].setSquareStatus(antChar);
                area[X][Y].addSquareStatus(antChar);
            }
            while (isPositionOK(antPositions));
        }
    }

    private int[][] generatePositions(int numberOfAnts) {
        int[][] antPositions = new int[numberOfAnts][2];
        for (int i = 0; i < numberOfAnts; i++) {
            for (int j = 0; j < 2; j++) {
                int randomNumber = generateRandomNumber();
                antPositions[i][j] = randomNumber;
            }
        }
        return antPositions;
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(this.getWidth());
    }

    public boolean isPositionOK(int[][] coordinates) {
        int X;
        int Y;
        try {
            for (int[] coordinate : coordinates) {
                X = coordinate[0];
                Y = coordinate[1];
                if (area[X][Y].getSquareStatus() != SquareStatus.EMPTY) {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            return false;
        }
        return true;
    }

    public static void updateColony(Square[][] area) {
        ArrayList<Square> workerSquares = Worker.getWorkerPositions(area, new ArrayList<>());
        ArrayList<Square> soldierSquares = Soldier.getSoldierPositions(area, new ArrayList<>());
        ArrayList<Square> droneSquares = Drone.getDronePositions(area, new ArrayList<>());
        Worker.move(workerSquares, area);
        Soldier.move(soldierSquares, area);
        Drone.move(droneSquares, area);
    }

    public static boolean isValidMove (Square square, int moveLength) {
        return ((square.getY()+moveLength < area.length || square.getX()-moveLength >= 0) ||
                (square.getX()+moveLength < area.length || square.getY()-moveLength >= 0));
    }

    public static boolean isValidMoveEast(Square square, int moveLength) {
        return square.getY() + moveLength < area.length;
    }

    public static boolean isValidMoveWest(Square square, int moveLength) {
        return square.getY() - moveLength >= 0;
    }

    public static boolean isValidMoveNorth(Square square, int moveLength) {
        return square.getX() - moveLength >= 0;
    }

    public static boolean isValidMoveSouth(Square square, int moveLength) {
        return square.getX() + moveLength < area.length;
    }

    public int getWidth() {
        return width;
    }
}
