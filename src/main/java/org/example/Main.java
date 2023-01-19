package org.example;

import java.util.Scanner;

public class Main {
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

        do {
            manager.fieldOutput();
            System.out.println("\n\n1: Uncover field");
            System.out.println("2: Set flag");
            try {
                Scanner scan = new Scanner(System.in);
                int action = scan.nextInt();

                switch (action) {
                    case 1 -> {
                        System.out.println("Please type in the y coordinate of your field");
                        y = scan.nextInt();
                        System.out.println("Please type in the x coordinate of your field");
                        x = scan.nextInt();
                        manager.uncover(y, x);
                        if (manager.getFieldByCoordinates(y, x).getStatus() == Field.FieldStatus.BOMB) {
                            gameover = true;
                        }
                    }
                    case 2 -> {
                        System.out.println("Please type in the y coordinate of your field");
                        y = scan.nextInt();
                        System.out.println("Please type in the x coordinate of your field");
                        x = scan.nextInt();
                        manager.placeOrRemoveFlag(y,x);
                    }
                    default -> System.out.println("Please choose from the options above");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }while(!gameover);
 }
}
