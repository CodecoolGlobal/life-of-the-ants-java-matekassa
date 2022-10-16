package com.codecool.ants;

import com.codecool.ants.geometry.Square;
import com.codecool.ants.geometry.SquareStatus;

public class Display {

    private final String ANSI_GREEN = "\u001b[32m";
    private final String ANSI_RESET = "\u001b[0m";

    public void printArea(Square[][] area) {
        StringBuilder result = new StringBuilder();
        result.append(" --");
        for (int i = 0; i<area.length; i++) {
            if (i == 0) {
                result.append("---".repeat(area.length));
            }
            result.append('\n');
            for (int j = 0; j < area.length; j++) {
                if (j == 0) {
                    result.append("|  ");
                }
                if (area[i][j].getIsEmpty()) {
                    String ANSI_BLACK = "\u001b[30m";
                    result.append(ANSI_BLACK).append(SquareStatus.EMPTY.getCharacter()).append("  ").append(ANSI_RESET);
                } else if (area[i][j].getActiveOnSquare().contains(SquareStatus.QUEEN)) {
                    String ANSI_RED = "\u001b[31m";
                    result.append(ANSI_RED).append(SquareStatus.QUEEN.getCharacter()).append("  ").append(ANSI_RESET);
                } else if (area[i][j].getActiveOnSquare().contains(SquareStatus.SOLDIER)) {
                    result.append(ANSI_GREEN).append(SquareStatus.SOLDIER.getCharacter()).append("  ").append(ANSI_RESET);
                } else if (area[i][j].getActiveOnSquare().contains(SquareStatus.WORKER)) {
                    String ANSI_BLUE = "\u001b[34m";
                    result.append(ANSI_BLUE).append(SquareStatus.WORKER.getCharacter()).append("  ").append(ANSI_RESET);
                } else if (area[i][j].getActiveOnSquare().contains(SquareStatus.DRONE)) {
                    String ANSI_YELLOW = "\u001b[33m";
                    result.append(ANSI_YELLOW).append(SquareStatus.DRONE.getCharacter()).append("  ").append(ANSI_RESET);
                }
            }
            result.append('|');
        }
        System.out.println("\n" + result + "\n" + " --" + "---".repeat(area.length));
    }

    public void print(String text) {
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }

}
