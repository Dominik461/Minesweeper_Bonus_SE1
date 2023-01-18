package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


/**
 * A simple http://logging.apache.org/log4j/2.x demo,
 * see file src/main/resources/log4j2.xml for configuration options
 * and A1.log containing debugging output.
 */

public class Main {
    private static Logger log = LogManager.getLogger(Main.class);

    /**
     * Your application's main entry point.
     *
     * @param args Yet unused
     */

    public static void main( String[] args ) {
        GameManager manager = new GameManager();

        final int size = 10, bombs = 15;
        int x,y;
        Field [][] fieldArray = new Field[size][size];
        boolean gameover = false;
        manager.setSize(size);
        manager.setFieldArray(fieldArray);
        manager.setBombs(bombs);
        manager.fillArray(bombs);

        // X: covered
        // space = uncovered
        // Q = bomb
        do {
            manager.fieldOutput();
            System.out.println("1: Uncover field");
            System.out.println("2: Set flag");
            try {
                Scanner scan = new Scanner(System.in);
                int action = scan.nextInt();

                switch (action) {
                    case 1:
                        System.out.println("Please type in the x coordinate of your field");
                        x = scan.nextInt();
                        System.out.println("Please type in the y coordinate of your field");
                        y = scan.nextInt();
                        manager.uncover(x, y);

                        if(manager.getFieldByCoordinates(x,y).getType().equals("Q"))
                            gameover = true;
                        break;
                    case 2:
                        System.out.println("Please type in the x coordinate of your field");
                        x = scan.nextInt();
                        System.out.println("Please type in the y coordinate of your field");
                        y = scan.nextInt();
                        manager.placeOrRemoveFlag(x,y);
                        break;
                    default:
                        System.out.println("Please choose from the options above");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }while(!gameover);
 }

 //TODO updateFieldArray method
}
