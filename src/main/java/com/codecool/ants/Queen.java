package com.codecool.ants;

public class Queen extends Ant {

    private static int matingMood = Input.getRandomNumber(50, 100);

    public static int getMatingMood() {
        return matingMood;
    }

    public static void changeMatingMood() {
        if (Queen.matingMood > 0) {
            Queen.matingMood = matingMood - 1;
        } else {
            Queen.matingMood = Input.getRandomNumber(50, 100);
        }
    }

    public Queen() {

    }

}
