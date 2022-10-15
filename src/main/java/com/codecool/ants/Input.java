package com.codecool.ants;

import java.util.Objects;
import java.util.Scanner;

public class Input{

    public int getKeys(){
        Display display = new Display();
        Scanner input = new Scanner(System.in);
        int action;
        display.print("Press 'Enter' to update or 'Q' to quit");
        if (Objects.equals(input.nextLine().toLowerCase(), "q")) {
            action = 0;
        } else {
            action = 1;
        }
        return action;
    }
}
