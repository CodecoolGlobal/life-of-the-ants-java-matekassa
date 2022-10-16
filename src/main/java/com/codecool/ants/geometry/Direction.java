package com.codecool.ants.geometry;

import java.util.Random;

public enum Direction {
        NORTH, EAST, SOUTH, WEST;

        private static final Random PRNG = new Random();

        public static Direction randomDirection() {
            Direction[] directions = values();
            return directions[PRNG.nextInt(directions.length)];
        }

    }
