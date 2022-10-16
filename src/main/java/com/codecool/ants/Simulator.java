package com.codecool.ants;

public class Simulator {

    public static void main(String[] args) {
        System.out.println("You can use `mvn package; java -jar target/ants-1.0.jar` to run this app from the console");

        // Set colony area
        Display display = new Display();
        Colony colony = new Colony(11);
        colony.generateAnts(3, 4, 2);
        display.printArea(colony.getArea());

        Input input = new Input();

        // Create loop
        while (true) {
            int option = input.getKeys();
            switch (option) {
                case 1:
                    colony.updateColony(colony.getArea());
                    display.printArea(colony.getArea());
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }

    }




}
